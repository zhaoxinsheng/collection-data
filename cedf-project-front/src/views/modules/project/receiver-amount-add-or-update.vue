<template>
  <el-dialog :title="!dataForm.id ? '添加支出信息' : '编辑支出信息'" :close-on-click-modal="false" :visible.sync="visible"
             @close="clearDataForm">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="180px">
      <el-row >
        <el-col :span="24"><h4>基本信息</h4></el-col>
        <div class="addForm_gray_bg">
          <el-col :span="12">
            <el-form-item label="协议号：" prop="projectId">
              <el-select v-model="dataForm.projectId" filterable placeholder="请选择" @change="(value) => changeProject(value)">
                <el-option v-for="item in projects" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支出类型：" prop="amountType">
              <el-radio-group v-model="dataForm.amountType">
                <el-radio :label="0">现金</el-radio>
                <el-radio :label="1">物资</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <!--<el-col :span="12">-->
          <!--<el-form-item label="支出金额：" prop="cashAmount">-->
          <!--<el-input v-model="dataForm.cashAmount"/>-->
          <!--</el-form-item>-->
          <!--</el-col>-->
          <el-col :span="12">
            <el-form-item label="协议接受捐赠单位：" prop="receiverId">
              <el-select v-model="dataForm.receiverId" placeholder="请选择" @change="(value) => changeReceiver(value)">
                <el-option v-for="item in receivers" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="下辖接受捐赠单位名称：" prop="secondUnit">
              <el-input v-model="dataForm.secondUnit" maxlength="30"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系人：" prop="relatePerson">
              <el-input v-model="dataForm.relatePerson" maxlength="10"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话：" prop="receiveTelephone">
              <el-input v-model="dataForm.receiveTelephone" maxlength="20"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支出日期：" prop="receiveDate" label-width="180px">
              <el-date-picker type="date" v-model="dataForm.receiveDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
          </el-col>
        </div>
      </el-row>
      <!-- -->
      <el-row >
        <el-col :span="24"><h4>金额信息</h4></el-col>
        <el-card class="box-card"  shadow="hover"  v-for="(amountEntity,idx) in dataForm.amountEntity" :key="idx" >
          <el-col :span="12">
            <el-form-item label="项目类型" :prop="'amountEntity['+idx+'].projectType'">
              <el-select v-model="amountEntity.projectType"  placeholder="请选择" clearable style="width: 100%"  @change="(value) => changeProjectType(value,idx)"  >
                <el-option v-for="item in projectTypes" :key="item.id" :label="item.id + '.' + item.name" :value="item.id" :disabled="dataForm.selected.includes(item.id)">  </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="支出金额"  :prop="'amountEntity['+idx+'].amount'">
              <el-input v-model="amountEntity.amount" maxlength="13"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目用途"  :prop="'amountEntity['+idx+'].projectPurpose'" v-if="amountEntity.projectPurposeVisible">
              <el-input v-model="amountEntity.projectPurpose" maxlength="50"/>
            </el-form-item>
          </el-col>
          <el-col :span="24" >
            <el-button type="text" class="el-button--text-gray" @click="() => deleteAmountEntity(idx)"><i class="el-icon-close"></i>删除此金额信息</el-button><br>
          </el-col>
        </el-card>
        <el-col :span="24">
          <el-button type="text"  @click="appendAmountEntity()"><i class="el-icon-plus"></i>增加金额信息</el-button><br>
        </el-col>
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
    projectId: '',
    amountType: 0,
    cashAmount: '',
    receiverId: '',
    secondUnit: '',
    relatePerson: '',
    receiveTelephone: '',
    receiveDate: '',
    selected: [],
    amountEntity: [{
      id: 0,
      receiverAmountId: '',
      projectType: '',
      amount: '',
      projectPurpose: '',
      projectPurposeVisible: false,
      disableFlag: false
    }]
  }

  import { isPhone, isMobile ,isAmount} from '@/utils/validate'
  var needChangeReceiver = true;//是否需要变更接收人
  var needChangePersonInfo = true;//是否需要变更联系人信息
  var validateAmount;
  export default {
    data () {
      var validateMoney = (rule, value, callback) => {
        if (/^\d{1,10}(\.\d{1,2}){0,1}$/.test(value)) {
          callback();
        } else {
          callback(new Error('支出金额最大长度为小数点前10位+小数点后两位数字'));
        }
      }

      validateAmount = (rule, value, callback) => {
        if(!value) {
          callback();
          return;
        }
        if (!isAmount(value) ) {
          callback(new Error('支出金额最大长度为小数点前10+小数点后2位数字！'));
        } else {
          callback();
        }
      }

      var validatePhone = (rule, value, callback) => {
        if(!value) {
          callback();
          return;
        }
        if (!isPhone(value) && !isMobile(value)) {
          if (/^((0\d{2,3})-)(\d{7,8})(-(\d{1,4}))?$/.test(value)) {
            callback();
          } else {
            callback();
          }
        } else {
          callback();
        }
      }
      return {
        visible: false,
        projectPurposeVisible: false,
        projectTypes: [],
        roleList: [],
        projects: [],
        receivers: [],

        dataForm: defaultDataForm,
        dataRule: {
          projectId: [
            {required: true, message: '请选择协议号', trigger: 'blur' }
          ],
          receiverId: [
            {required: true, message: '请选择协议接受捐赠单位', trigger: 'blur' }
          ],
          receiveDate: [
            {required: true, message: '请选择支出日期', trigger: 'blur' }
          ],
          cashAmount: [
            { required: true, message: '请输入支出金额', trigger: 'blur' },
            {validator: validateMoney, trigger: 'blur'}
          ],
          receiveTelephone: [
            {validator: validatePhone, trigger: 'blur'}
          ],
          amountType: [
            {required: true, message: '请选择支出类型', trigger: 'blur' }
          ],
          'amountEntity[0].projectType': [
            { required: true, message: '项目类型不能为空', trigger: 'blur' }
          ],
          'amountEntity[0].amount': [
            { required: true, message: '支出金额不能为空', trigger: 'blur' },
            {validator: validateAmount, trigger: 'blur'}
          ],
          'amountEntity[0].projectPurpose': [
            { required: true, message: '项目用途不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0;
        this.dataForm.id = id || 0;
        this.visible = true;
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        });
        //判断是否为编辑操作

          this.$http({
            url: this.$http.adornUrl('/project/base/typeSelect'),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            this.projectTypes = data && data.code === 0 ? data.list : []
          }).then(() => {
            this.visible = true
            this.$nextTick(() => {
              this.$refs['dataForm'].resetFields()
            })
          }).then(() => {
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/project/receiverAmount/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm.projectId = data.receiverAmount.projectId;
                  this.dataForm.amountType = parseInt(data.receiverAmount.amountType);
                  this.dataForm.cashAmount = data.receiverAmount.cashAmount;
                  this.dataForm.receiverId = data.receiverAmount.receiverId;
                  this.dataForm.secondUnit = data.receiverAmount.secondUnit;
                  this.dataForm.relatePerson = data.receiverAmount.relatePerson;
                  this.dataForm.receiveTelephone = data.receiverAmount.receiveTelephone;
                  this.dataForm.receiveDate = data.receiverAmount.receiveDate;

                  //编辑操作，第一次数据加载不允许变更接收单位，联系人信息
                  if (this.dataForm.receiverId) {
                    needChangeReceiver = false;
                    needChangePersonInfo = false;
                  }
                  //查询对应项目的接收单位
                  this.changeProject(this.dataForm.projectId);
                  // 查询对应的金额明细
                  this.queryAmountEntity(this.dataForm.id);

                } else {
                  this.dataForm.projectId = '',
                    this.dataForm.amountType = '',
                    this.dataForm.cashAmount = '',
                    this.dataForm.receiverId = '',
                    this.dataForm.secondUnit = '',
                    this.dataForm.relatePerson = '',
                    this.dataForm.receiveTelephone = '',
                    this.dataForm.receiveDate = ''
                }
              })
            }
          })
        //查询所有的项目列表
        this.projects = [],
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
      },
      //协议号发生变化时调用
      changeProject(id){
        this.receivers = [];
        this.$http({
          url: this.$http.adornUrl(`/project/receiver/listbyprojectid/` + id),
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            for(var i = 0; i < data.receivers.length; i++) {
              this.receivers.push({id:data.receivers[i].id + '', name:data.receivers[i].receiveCompany})
            }
          }
        }).then(() => {
          if(!needChangeReceiver) {
            needChangeReceiver = true;
            return;
          }
          //更新选中的接收单位，默认选择第一个选项
          if(this.receivers) {
            this.dataForm.receiverId = this.receivers[0].id + '';
          }
        }).then(() => {
          this.changeReceiver(this.dataForm.receiverId);
        });
      },

      queryAmountEntity(id){
        this.dataForm.amountEntity = [];
        this.$http({
          url: this.$http.adornUrl(`/project/receiveramountdetail/detail/` + id),
          method: 'get'
        }).then(({data}) => {
          if (data && data.code === 0) {
            for(var i = 0; i < data.amountEntity.length; i++) {
              var purpose = false;
              var projectPurposes = '';
              this.dataForm.selected.push(data.amountEntity[i].projectType);
              if (data.amountEntity[i].projectType != null && data.amountEntity[i].projectType === 15) {
                purpose = true;
              }
              if (data.amountEntity[i].projectPurpose != null) {
                 projectPurposes = data.amountEntity[i].projectPurpose
              }
              this.dataForm.amountEntity.push({id: data.amountEntity[i].id + '',
                receiverAmountId: data.amountEntity[i].receiverAmountId+'',
                projectType: data.amountEntity[i].projectType,
                amount: data.amountEntity[i].amount+'',
                projectPurpose: projectPurposes,
                projectPurposeVisible: purpose,
                disableFlag: false
                })
              // this.changeProjectType(data.amountEntity[i].projectType,i);
            }
          }
        })
      },
      //接收单位发生变化时调用
      changeReceiver(id) {
        if(!needChangePersonInfo) {
          needChangePersonInfo = true;
          return;
        }
        let obj = {};
        obj = this.receivers.find((item)=>{
          return item.id === id;
        });
        //更新联系人信息
        this.$http({
          url: this.$http.adornUrl(`/project/receiver/info/` + id),
          method: 'get'
        }).then((data) => {
          if(data.data && data.data.code == 0) {
            // 为啥要清空
            //  this.dataForm.secondUnit = '';
            this.dataForm.relatePerson = data.data.receiver.relatePerson;
            this.dataForm.receiveTelephone = data.data.receiver.receiveTelephone;
          }
        });

      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/project/receiverAmount/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id,
                'projectId': this.dataForm.projectId,
                'receiverId': this.dataForm.receiverId,
                'amountType': this.dataForm.amountType,
                'cashAmount': this.dataForm.cashAmount,
                'relatePerson': this.dataForm.relatePerson,
                'receiveTelephone': this.dataForm.receiveTelephone,
                'receiveDate': this.dataForm.receiveDate,
                'secondUnit': this.dataForm.secondUnit,
                'amountEntity': this.filterAmountData(this.dataForm.amountEntity)
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.clearDataForm ()
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

      changeProjectType(id,index){
         for (var i= 0; i < this.dataForm.amountEntity.length; i++) {
           if(index != i && this.dataForm.amountEntity[i].projectType === id){
             this.$message({
               message: '已经存在相同的金融信息',
               type: 'success',
               duration: 1500,
               onClose: () => {}
             })
           }
        }

        if (id && id === 15) {
            this.dataForm.amountEntity[index].projectPurposeVisible = true;
          }else {
            this.dataForm.amountEntity[index].projectPurposeVisible = false;
            this.dataForm.amountEntity[index].projectPurpose = '';
          }
        this.filterData();
      },
      //移除最后一个金融信息
      deleteAmountEntity (idx) {
        console.log("deleteAmountEntity idx:" + idx);
        if (this.dataForm.amountEntity.length == 1) {
          this.$message.error('金额信息不能少于一条');
          return;
        }

        var index = idx;
        if (!idx && idx != 0) {
          index = this.dataForm.amountEntity.length-1;
        }
        delete this.dataRule['amountEntity['+index+'].projectType'];
        delete this.dataRule['amountEntity['+index+'].amount'];
        delete this.dataRule['amountEntity['+index+'].projectPurpose'];
        delete this.dataForm.selected['amountEntity['+index+'].projectType'];

        console.log("deleteAmountEntity index:" + index);
        this.dataForm.amountEntity.splice(index, 1);
        // this.filterData();
      },
      //追加接收捐赠单位
      appendAmountEntity () {
        var amountObj = {
          projectType:'',
          amount:'',
          projectPurpose:'',
          projectPurposeVisible: false,
          disableFlag: false
        }

        //动态增加验证规则
        var index = this.dataForm.amountEntity.length;
        this.dataRule['amountEntity['+index+'].projectType']= [
          { required: true, message: '项目类型不能为空', trigger: 'blur' }
        ],
        this.dataRule['amountEntity['+index+'].amount']= [
          { required: true, message: '支出金额不能为空', trigger: 'blur' },
          {validator: validateAmount, trigger: 'blur'}
        ],
        this.dataRule['amountEntity['+index+'].projectPurpose']= [
          { required: true, message: '项目用途不能为空', trigger: 'blur' }
        ],
        this.dataForm.amountEntity.push(amountObj);
        this.filterData();
      },
      filterData () {
         this.dataForm.selected = [];
         for(var i = 0; i < this.dataForm.amountEntity.length; i++) {
            this.dataForm.selected.push(this.dataForm.amountEntity[i].projectType);
          }

          for (var i = 0; i <  this.dataForm.selected.length; i++){
            console.log('选择的项目类型是 ---------['+this.dataForm.selected[i]+']');
          }
      },

      filterAmountData  (amountEntity) {
        var amountEntityList = [];
        for (var i=0; i < amountEntity.length; i++) {
          amountEntityList.push({
            projectType:amountEntity[i].projectType,
            amount:amountEntity[i].amount,
            projectPurpose:amountEntity[i].projectPurpose
          })
        }
        return amountEntityList
      },
      clearDataForm () {
        this.dataForm = defaultDataForm
      }
    }
  }
</script>
