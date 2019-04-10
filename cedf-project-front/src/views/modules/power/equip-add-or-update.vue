<template>
  <el-dialog
    :title="!dataForm.id ? '添加设备信息' : '修改设备信息'"
    :close-on-click-modal="false"
    :visible.sync="visible"
    @close="clearDataForm">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
      <el-row >
        <el-col :span="14">
          <el-form-item label="设备名称：" prop="equipName">
            <el-input type="text" v-model.number="dataForm.equipName" placeholder="设备名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label="型号：" prop="equipType">
            <el-input type="text" v-model.number="dataForm.equipType" placeholder="设备型号"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label="厂家：" prop="equipVender">
            <el-input type="text" v-model.number="dataForm.equipVender" placeholder="设备厂家"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label="编号：" prop="equipNo">
            <el-input type="text" v-model.number="dataForm.equipNo" placeholder="设备编号"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label="出厂日期：" prop="equipDate">
            <el-input type="text" v-model.number="dataForm.equipDate" placeholder="出厂日期"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="14">
          <el-form-item label="采集参数：" prop="type">
            <el-select v-model="dataForm.value5" placeholder="请选择采集参数">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label="采集单位：" prop="equipPer">
            <el-input type="text" v-model.number="dataForm.equipPer" placeholder="采集单位"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="14">
          <el-form-item label="选择设备显示颜色：" prop="equipNameColor">
            <!--<el-color-picker v-model="color1"></el-color-picker>-->
            <colorPicker v-model="color" width="100px"  />
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
        color1: 'rgba(19, 206, 102, 0.8)',
        color:'#FF0000',
        options: [{
          value: '1',
          label: '进气温度'
        }, {
          value: '2',
          label: '压力'
        }, {
          value: '3',
          label: '用电量'
        }, {
          value: '4',
          label: '流量'
        }, {
          value: '5',
          label: '故障报警'
        },{
            value: '6',
            label: '排气温度'
          },
          {
            value: '7',
            label: '漏点'
          },
          {
            value: '8',
            label: '瞬间流量'
          },
          {
            value: '9',
            label: '累计流量'
          },
          {
            value: '10',
            label: '运行时间'
          }
        ],
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
          collectName: '',
          equipName:'',
          equipNameColor:'',
          type:[],
          equipType:'',
          equipVender:'',
          equipNo:'',
          equipDate:'',
          equipPer:''
        },
        dataRule: {
          projectId: [
            { required: true, message: '请选择协议号', trigger: 'blur' }
          ],
          cashAmount: [
            { required: true, message: '请输入到账金额', trigger: 'blur' },
            {validator: validateCashAmount, trigger: 'blur'}
          ],
          donator: [
            { required: true, message: '请输入汇款单位名称', trigger: 'blur' },
            {validator: validateDonator, trigger: 'blur'}
          ],
          donateDate: [
            { required: true, message: '请选择到账日期', trigger: 'blur' }
          ],
          deliveryDate: [
            { required: true, message: '请选择收据邮寄日期', trigger: 'blur' }
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
            // this.$http({
            //   url: this.$http.adornUrl(`/project/donateamount/info/${this.dataForm.id}`),
            //   method: 'get',
            //   params: this.$http.adornParams()
            // }).then(({data}) => {
            //   this.visible = true;
            //   this.dataForm =(data.donateAmount != null)?data.donateAmount:
            //     {
            //       id:'',
            //       projectId: '',
            //       cashAmount: '',
            //       donateDate: '',
            //       deliveryDate: '',
            //       deliveryNo: '',
            //       remark: '',
            //       insertUser:'',
            //       insertTime:'',
            //       updateTime:'',
            //       donator:'',
            //       meterialAmount:'',
            //       amountType: ''
            //     };
            //   //查询对应项目的接收单位
            //   this.getDonateor(this.dataForm.id);
           // })
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
          type:[]
        }
      }
    }
  }
</script>
