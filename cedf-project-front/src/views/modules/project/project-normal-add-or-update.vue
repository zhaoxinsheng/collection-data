<template>
  <el-dialog
    :title="!dataForm.id ? '新增项目' : '修改项目'"
    :close-on-click-modal="false"
    @close="clearDataForm"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
      <el-row >
        <!-- <el-col :span="24"><h4>基本信息</h4></el-col> -->
        <div class="addForm_gray_bg">
          <el-col :span="12">
            <el-form-item label="捐赠人" prop="donatorName">
              <el-input v-model="dataForm.donatorName" placeholder="捐赠人" maxlength="20"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="到账金额" prop="cashAmount">
              <el-input v-model="dataForm.cashAmount" placeholder="到账金额"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="到账日期" prop="donateDate">
              <el-date-picker type="date" placeholder="到账日期" v-model="dataForm.donateDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支出金额" prop="withAmount">
              <el-input v-model="dataForm.withAmount" placeholder="支出金额"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支出报告日期" prop="withDate">
              <el-date-picker type="date" placeholder="支出报告日期" v-model="dataForm.withDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
          </el-col>
        </div>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  var defaultDataForm = {
          id: 0,
          donatorName: '',
          cashAmount: '',
          donateDate: '',
          withAmount: '',
          withDate: ''
      }
  import { isEmail, isMobile ,isAmount} from '@/utils/validate'
  export default {
    data () {
      var validateMoney = (rule, value, callback) => {
        if (!value) {
          callback();
          return;
        }
        if (!isAmount(value)) {
          callback(new Error('金额最大长度为小数点前10+小数点后2位数字！'));
        } else {
          callback();
        }
      }
      return {
        visible: false,
        dataForm: defaultDataForm,
        dataRule: {
          donatorName: [
            { required: true, message: '捐赠人不能为空', trigger: 'blur' },
          ],
          cashAmount: [
            { required: true, message: '到账金额不能为空', trigger: 'blur' },
            {validator: validateMoney, message: '到账金额最大长度为小数点前10+小数点后2位数字', trigger: 'blur'}
          ],
          withAmount: [
            {validator: validateMoney, message: '支出金额最大长度为小数点前10+小数点后2位数字', trigger: 'blur'}
          ],
          donateDate: [
            { required: true, message: '到账日期不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0;
        this.visible = true;

        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`/project/normal/info/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm = data.projectNormal
            }
          })
        }
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/project/normal/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                //基本信息
                id: this.dataForm.id || undefined,
                donatorName: this.dataForm.donatorName,
                cashAmount: this.dataForm.cashAmount,
                withAmount: this.dataForm.withAmount,
                donateDate: this.dataForm.donateDate,
                withDate: this.dataForm.withDate
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$refs['dataForm'].resetFields();
                this.clearDataForm();
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      clearDataForm () {
          this.dataForm = defaultDataForm;
          this.$refs['dataForm'].resetFields();
      }
    }
  }
</script>
