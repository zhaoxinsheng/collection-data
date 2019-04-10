<template>
  <el-dialog
    :title="!dataForm.id ? '添加到账信息' : '编辑到账信息'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    @close="clearDataForm">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
            <el-row >
            <el-col :span="14">
            <el-form-item label="协议号："  prop="projectId">
              <el-select v-model="dataForm.projectId" filterable placeholder="请选择" @change="(value) => changeProject(value)">
                <el-option v-for="item in projects" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            </el-col>

            <el-col>
            <el-form-item label="到账类型：" prop="amountType">
              <el-radio-group v-model="dataForm.amountType">
                <el-radio :label="0">现金</el-radio>
                <el-radio :label="1">物资</el-radio>
              </el-radio-group>
            </el-form-item>
            </el-col>

            <el-col :span="14">
            <el-form-item label="到账金额：" prop="cashAmount">
              <el-input type="number" v-model.number="dataForm.cashAmount" placeholder="到账金额"></el-input>
            </el-form-item>
            </el-col>
            <el-col :span="14">
            <el-form-item label="汇款单位名称：" prop="donator">
                <el-input v-model="dataForm.donator" placeholder="汇款单位名称"></el-input>
            </el-form-item>
            </el-col>

            <el-col :span="14">
            <el-form-item label="到账日期：" prop="donateDate">
              <el-date-picker type="date" placeholder="到账日期" v-model="dataForm.donateDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            </el-col>

            <el-col :span="14">
            <el-form-item label="收据邮寄日期：" prop="deliveryDate">
              <el-date-picker type="date" placeholder="收据邮寄日期" v-model="dataForm.deliveryDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            </el-col>

            <el-col :span="14">
            <el-form-item label="快递编号：" prop="deliveryNo">
              <el-input v-model="dataForm.deliveryNo" placeholder="快递编号"></el-input>
            </el-form-item>
            </el-col>

            <el-col :span="14">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
            </el-form-item>
            </el-col>
</el-row >
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>
<style>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }

</style>

<script>
   import { validateLength, isAmount } from '@/utils/validate'

  export default {
    data () {

         var validateCashAmount = (rule, value, callback) => {
            if(!value) {
                callback();
                return;
            }
            if (!isAmount(value) ) {
                callback(new Error('到账金额最大长度为小数点前10+小数点后2位数字！'));
            } else {
                callback();
            }
        }

        var validateDonator = (rule, value, callback) => {
          if (!validateLength(value,30) ) {
                callback(new Error('汇款单位名称最大长度为30个字'));
            } else {
                callback();
            }
        }

        var validateDeliveryNo = (rule, value, callback) => {
          if (!validateLength(value,20) ) {
                callback(new Error('快递编号最大长度为20个字符'));
            } else {
                callback();
            }
        }

        var validateRemark = (rule, value, callback) => {
          if (!validateLength(value,50) ) {
                callback(new Error('备注最大长度为50个字'));
            } else {
                callback();
            }
        }

      return {
        visible: false,
        projects: [],
        dataForm: {
          id:0,
          projectId: '',
          cashAmount: '',
          donator: '',
          donateDate: '',
          deliveryDate: '',
          deliveryNo: '',
          remark: '',
          amountType: 0,
        },
        dataRule: {
          projectId: [
             { required: true, message: '请选择协议号', trigger: 'blur' }
          ],
          cashAmount: [
            { required: true, message: '请输入到账金额', trigger: 'blur' },
            {validator: validateCashAmount, trigger: 'blur'}
          ],
          amountType: [
            { required: true, message: '到账类型不能为空', trigger: 'blur' }
          ],
          donator: [
            {validator: validateDonator, trigger: 'blur'}
          ],
          donateDate: [
            { required: true, message: '请选择到账日期', trigger: 'blur' }
          ],
          deliveryNo: [
             {validator: validateDeliveryNo, trigger: 'blur'}
          ],
          remark: [
            {validator: validateRemark, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
          init (id) {
            this.dataForm.id = id || 0
            this.$http({
              url: this.$http.adornUrl('/sys/role/select'),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              this.roleList = data && data.code === 0 ? data.list : []
            }).then(() => {
              this.visible = true
              this.$nextTick(() => {
                this.$refs['dataForm'].resetFields()
              })
            }).then(() => {
             this.projects = []
              this.$http({
                  url: this.$http.adornUrl(`/project/base/listall`),
                  method: 'get'
                }).then(({data}) => {
                  if (data && data.code === 0) {
                    for(var i = 0; i < data.projects.length; i++) {
                      this.projects.push({id:data.projects[i].id, name:data.projects[i].projectNo+'('+data.projects[i].projectName +')'})
                    }
                  }
                })
            }).then(() => {
              if (this.dataForm.id) {
                this.$http({
                  url: this.$http.adornUrl(`/project/donateamount/info/${this.dataForm.id}`),
                  method: 'get',
                  params: this.$http.adornParams()
                }).then(({data}) => {
                  this.visible = true;
                    this.dataForm =(data.donateAmount != null)?data.donateAmount:
                     {
                       id:'',
                       projectId: '',
                       cashAmount: '',
                       donateDate: '',
                       deliveryDate: '',
                       deliveryNo: '',
                       remark: '',
                       insertUser:'',
                       insertTime:'',
                       updateTime:'',
                       donator:'',
                       meterialAmount:'',
                       amountType: ''
                     };
                    //查询对应项目的接收单位
//                    this.getDonateor(this.dataForm.id);
                })
              }
            })
          },
          //协议号发生变化   汇款单位变化
          changeProject(id) {
            this.$http({
                url: this.$http.adornUrl(`/project/donateamount/companyName/` + id),
                method: 'get'
            }).then((data) => {
            console.log(data)
                if(data.data && data.data.code == 0) {
                    this.dataForm.donator = data.data.donator.companyName;
                }
            });
          },

            //协议号发生变化   汇款单位变化
          getDonateor(id) {
            this.$http({
                url: this.$http.adornUrl(`/project/donateamount/donateName/` + id),
                method: 'get'
            }).then((data) => {
            console.log(data)
                if(data.data && data.data.code == 0) {
                    this.dataForm.donator = data.data.donator.companyName;
                }
            });
          },



      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/project/donateamount/${!this.dataForm.id ? 'save' : 'update'}`),
            method: 'post',
            data: this.$http.adornData({
              //基本信息
              id: this.dataForm.id || undefined,
              projectId: this.dataForm.projectId,
              cashAmount: this.dataForm.cashAmount,
              donator: this.dataForm.donator,
              donateDate: this.dataForm.donateDate,
              deliveryDate: this.dataForm.deliveryDate,
              deliveryNo: this.dataForm.deliveryNo,
              remark: this.dataForm.remark,
              amountType : this.dataForm.amountType,
            })
          }).then(({data}) => {
            this.clearDataForm();
            if (data && data.code === 0) {
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
      clearDataForm() {
        this.dataForm= {
          id:0,
          projectId: '',
          cashAmount: '',
          donator: '',
          donateDate: '',
          deliveryDate: '',
          deliveryNo: '',
          remark: '',
          amountType: 0,
        }
      }
    }
  }
</script>
