<!--
<template>
  <el-dialog
    :title="!dataForm.id ? '新增项目' : '修改项目'"
    :close-on-click-modal="false"
    @close="clearDataForm"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
      <el-row >
        <el-col :span="24"><h4>基本信息</h4></el-col>
        <div class="addForm_gray_bg">
          <el-col :span="12">
            <el-form-item label="协议号" prop="projectNo">
              <el-input v-model="dataForm.projectNo" placeholder="协议号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目名称" prop="projectName">
              <el-input v-model="dataForm.projectName" placeholder="项目名称"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="协议日期" prop="dateTime">
              <el-date-picker type="date" placeholder="协议日期" v-model="dataForm.dateTime" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目类型" prop="projectType">
              <el-select v-model="dataForm.projectType"  placeholder="请选择" clearable style="width: 100%">
                <el-option v-for="item in projectTypes" :key="item.id" :label="item.id + '.' + item.name" :value="item.id" >  </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="协议捐赠金额" prop="cashAmount">
              <el-input type="number" v-model.number="dataForm.cashAmount" placeholder="协议捐赠金额"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="助教号">
              <el-input v-model="dataForm.assistantNo" placeholder="助教号"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="协议捐赠内容">
              <el-input v-model="dataForm.projectContent" placeholder="捐赠内容" style="width:100%"></el-input>
            </el-form-item>
          </el-col>
        </div>
      </el-row>

      &lt;!&ndash; 捐赠单位信息 &ndash;&gt;
      <el-row>
        <el-col :span="24"><h4>捐赠单位信息</h4></el-col>
        <el-col :span="24">
          <el-form-item label="捐赠单位名称" prop="donatorEntity.companyName">
            <el-input v-model="dataForm.donatorEntity.companyName" placeholder="捐赠单位名称"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item label="捐赠单位地址" prop="donatorEntity.provinceId">
            &lt;!&ndash;<el-input v-model="dataForm.donatorEntity_areaCode.areaCode" placeholder="省"></el-input>&ndash;&gt;
            <el-select v-model="dataForm.donatorEntity.provinceId" default-first-option placeholder="省" style="width: 150px" @change="(value) => changeArea(value, 'p', 'donator')">
              <el-option
                v-for="item in provinceList"
                :key="item.baseAreaId"
                :label="item.areaName"
                :value="item.baseAreaId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item class="offset_left_80">
            <el-select v-model="dataForm.donatorEntity.cityId" placeholder="市" style="width: 150px"  @change="(value) => changeArea(value, 'c', 'donator')">
              <el-option
                v-for="item in dataForm.donatorEntity.cityList"
                :key="item.baseAreaId"
                :label="item.areaName"
                :value="item.baseAreaId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item class="offset_left_10">
            <el-select v-model="dataForm.donatorEntity.countyId" placeholder="区县" style="width: 150px">
              <el-option
                v-for="item in dataForm.donatorEntity.countyList"
                :key="item.baseAreaId"
                :label="item.areaName"
                :value="item.baseAreaId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item prop="donatorEntity.addressDetail">
            <el-input v-model="dataForm.donatorEntity.addressDetail"placeholder="详细地址" style="width: 288px"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="捐赠单位法人" prop="donatorEntity.legalPerson">
            <el-input v-model="dataForm.donatorEntity.legalPerson" placeholder="捐赠单位法人"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="捐赠单位邮编">
            <el-input v-model="dataForm.donatorEntity.postalCode" type="" placeholder="捐赠单位邮编"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="捐赠单位联系人" prop="donatorEntity.relatePerson">
            <el-input v-model="dataForm.donatorEntity.relatePerson" placeholder="捐赠单位联系人"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="捐赠单位电话" prop="donatorEntity.companyTelephone">
            <el-input v-model="dataForm.donatorEntity.companyTelephone" type="" placeholder="捐赠单位电话"></el-input>
          </el-form-item>
        </el-col>

      </el-row>

      &lt;!&ndash; 接收单位信息 &ndash;&gt;
      <el-row >
          <el-col :span="24"><h4>接受捐赠单位信息</h4></el-col>
            <el-card class="box-card"  shadow="hover"  v-for="(receiver,idx) in dataForm.receiverEntity" >
            <el-col :span="24">
              <el-form-item label="接受捐赠单位名称" :prop="'receiverEntity['+idx+'].receiveCompany'">
                <el-input v-model="receiver.receiveCompany" placeholder="接受捐赠单位名称"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-form-item label="接受捐赠单位地址" :prop="'receiverEntity['+idx+'].provinceId'">
                <el-select v-model="receiver.provinceId" placeholder="省" style="width: 150px" @change="(value) => changeArea(value, 'p', 'receiver', idx)">
                  <el-option
                    v-for="item in provinceList"
                    :key="item.baseAreaId"
                    :label="item.areaName"
                    :value="item.baseAreaId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item class="offset_left_80">
                <el-select v-model="receiver.cityId" placeholder="市" style="width: 150px" @change="(value) => changeArea(value, 'c', 'receiver', idx)">
                  <el-option
                    v-for="item in receiver.cityList"
                    :key="item.baseAreaId"
                    :label="item.areaName"
                    :value="item.baseAreaId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item class="offset_left_10">
                <el-select v-model="receiver.countyId" placeholder="区县" style="width: 150px">
                  <el-option
                    v-for="item in receiver.countyList"
                    :key="item.baseAreaId"
                    :label="item.areaName"
                    :value="item.baseAreaId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item :prop="'receiverEntity['+idx+'].addressDetail'">
                <el-input v-model="receiver.addressDetail" placeholder="详细地址" style="width: 288px"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="接受捐赠单位联系人">
                <el-input v-model="receiver.relatePerson" placeholder="接受捐赠单位联系人"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="接受捐赠单位电话" >
                <el-input v-model="receiver.receiveTelephone" type="" placeholder="接受捐赠单位电话"></el-input>
              </el-form-item>
            </el-col>
          <el-col :span="24" >
            <el-button type="text" class="el-button&#45;&#45;text-gray" @click="() => deleteReceiver(idx)"><i class="el-icon-close"></i>删除接受捐赠单位</el-button><br>
          </el-col>

            </el-card>
          <el-col :span="24">
            <el-button type="text"  @click="appendReceiver()"><i class="el-icon-plus"></i>增加接受捐赠单位</el-button><br>
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
          projectName: '',
          projectNo: '',
          projectContent: '',
          cashAmount: '',
          projectType: '',
          dateTime: '',
          assistantNo: '',

          donatorEntity: {
            id: 0,
            companyName:'',
            areaCode:'',
            addressDetail:'',
            legalPerson:'',
            relatePerson:'',
            postalCode:'',
            companyTelephone:'',
            provinceId:null,
            cityId:null,
            countyId:null,
            cityList:[
              { areaId:'',
                areaName:''
              }
            ],
            countyList:[
              { areaId:'',
                areaName:''
              }
            ],
          },

          receiverEntity: [
            {
                receiveCompany:'',
                areaCode:'',
                addressDetail:'',
                legalPerson:'',
                relatePerson:'',
                postalCode:'',
                receiveTelephone:'',
                provinceId:null,
                cityId:null,
                countyId:null,

                cityList:[
                  { areaId:'',
                    areaName:''
                  }
                ],
                countyList:[
                  { areaId:'',
                    areaName:''
                  }
                ],
              },
          ]

        }

  import { isEmail, isMobile ,isAmount} from '@/utils/validate'
  export default {

    data () {
      var validateEmail = (rule, value, callback) => {
        if (!value) {
          callback(new Error('邮箱格式错误'))
        } else {
          callback()
        }
      }

    var validateMoney = (rule, value, callback) => {
            if(!value) {
                callback();
                return;
            }
            if (!isAmount(value) ) {
                callback(new Error('协议金额最大长度为小数点前10+小数点后2位数字！'));
            } else {
                callback();
            }
        }

      return {
        visible: false,
        projectTypes: [],
        //省市县
        provinceList:[
          { areaId:'',
            areaName:''
          }
        ],



          dataForm:  defaultDataForm,

        dataRule: {
          projectName: [
            { required: true, message: '项目名称不能为空', trigger: 'blur' },
          ],
          projectNo: [
            { required: true, message: '协议号不能为空', trigger: 'blur' }
          ],
          projectType: [
            { required: true, message: '项目类型不能为空', trigger: 'blur' }
          ],
          dateTime: [
            { required: true, message: '协议日期不能为空', trigger: 'blur' }
          ],
          cashAmount: [
            { required: true, message: '协议捐赠金额不能为空', trigger: 'blur' },
             {validator: validateMoney, message: '协议捐赠金额必须为正数，最多两位小数', trigger: 'blur'}
          ],
          //&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;
          'donatorEntity.companyName': [
            { required: true, message: '捐赠单位名称不能为空', trigger: 'blur' },
          ],
          'donatorEntity.areaCode': [
            { required: true, message: '捐赠单位地址不能为空', trigger: 'blur' }
          ],
          'donatorEntity.provinceId': [
            { required: true, message: '省份不能为空', trigger: 'change' },
          ],
          'donatorEntity.addressDetail': [
            { required: true, message: '捐赠单位地址不能为空', trigger: 'blur' },
//            { validator:validateAddress, trigger: 'blur' },
          ],
          'donatorEntity.legalPerson': [
            { required: true, message: '捐赠单位法人不能为空', trigger: 'blur' }
          ],
          /**
          'donatorEntity.relatePerson': [
            { required: true, message: '捐赠单位联系人不能为空', trigger: 'blur' }
          ],

          'donatorEntity.companyTelephone': [
            { required: true, message: '捐赠单位电话不能为空', trigger: 'blur' }
          ],
          **/
          //&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;
          'receiverEntity[0].receiveCompany': [
            { required: true, message: '接收捐赠单位名称不能为空', trigger: 'blur' }
          ],
          'receiverEntity[0].provinceId': [
            { required: true, message: '省份不能为空', trigger: 'change' },
          ],
          'receiverEntity[0].addressDetail': [
            { required: true, message: '接收捐赠单位地址不能为空', trigger: 'blur' }
          ],

        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
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
        }).then(()=>{
          this.$http({
            url: this.$http.adornUrl(`/project/area/select`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data})=>{
              if (data && data.code === 0) {
                this.provinceList = data.areaList;
              }
          }).then(() => {
            if (this.dataForm.id) {
              this.$http({
                url: this.$http.adornUrl(`/project/base/info/${this.dataForm.id}`),
                method: 'get',
                params: this.$http.adornParams()
              }).then(({data}) => {
                if (data && data.code === 0) {
                  this.dataForm = data.project
                  for (var index=1; index< data.project.receiverEntity.length; index++) {
                    this.dataRule['receiverEntity['+index+'].receiveCompany']= [
                      { required: true, message: '接收捐赠单位名称不能为空', trigger: 'blur' }
                    ];
                    this.dataRule['receiverEntity['+index+'].provinceId']= [
                      { required: true, message: '省份不能为空', trigger: 'change' },
                    ];
                    this.dataRule['receiverEntity['+index+'].addressDetail']= [
                      { required: true, message: '接收捐赠单位地址不能为空', trigger: 'blur' }
                    ];

                  }
                }
              })
            }
          })
        })
      },
      filterReceiverData (receiverEntity) {
        var finalReceiverList = [];
        for (var i=0; i < receiverEntity.length; i++) {
          finalReceiverList.push({
            receiveCompany:receiverEntity[i].receiveCompany,
            addressDetail:receiverEntity[i].addressDetail,
            legalPerson:receiverEntity[i].legalPerson,
            relatePerson:receiverEntity[i].relatePerson,
            receiveTelephone:receiverEntity[i].receiveTelephone,
            provinceId:receiverEntity[i].provinceId,
            cityId:receiverEntity[i].cityId,
            countyId:receiverEntity[i].countyId,
          })
        }
        return finalReceiverList
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {

            this.$http({
              url: this.$http.adornUrl(`/project/base/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                //基本信息
                id: this.dataForm.id || undefined,
                projectName: this.dataForm.projectName,
                projectNo: this.dataForm.projectNo,
                projectContent: this.dataForm.projectContent,
                cashAmount: this.dataForm.cashAmount,
                projectType: this.dataForm.projectType,
                dateTime: this.dataForm.dateTime,
                projectContent: this.dataForm.projectContent,
                assistantNo: this.dataForm.assistantNo,
                //捐赠单位信息
                donatorEntity: {
                  companyName:this.dataForm.donatorEntity.companyName,
                  addressDetail:this.dataForm.donatorEntity.addressDetail,
                  legalPerson:this.dataForm.donatorEntity.legalPerson,
                  relatePerson:this.dataForm.donatorEntity.relatePerson,
                  postalCode:this.dataForm.donatorEntity.postalCode,
                  companyTelephone:this.dataForm.donatorEntity.companyTelephone,
                  provinceId:this.dataForm.donatorEntity.provinceId,
                  cityId:this.dataForm.donatorEntity.cityId,
                  countyId:this.dataForm.donatorEntity.countyId,
                },
                receiverEntity: this.filterReceiverData(this.dataForm.receiverEntity)
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.clearDataForm()
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
      //追加接收捐赠单位
      appendReceiver () {
          var receiverTpl = {
            receiveCompany:'',
            areaCode:'',
            addressDetail:'',
            legalPerson:'',
            relatePerson:'',
            receiveTelephone:'',
            provinceId:null,
            cityId:null,
            countyId:null,
            cityList:[
              { areaId:'',
                areaName:''
              }
            ],
            countyList:[
              { areaId:'',
                areaName:''
              }
            ],
          }

          //动态增加验证规则
          var index = this.dataForm.receiverEntity.length;
          this.dataRule['receiverEntity['+index+'].receiveCompany']= [
            { required: true, message: '接收捐赠单位名称不能为空', trigger: 'blur' }
          ];
          this.dataRule['receiverEntity['+index+'].provinceId']= [
            { required: true, message: '省份不能为空', trigger: 'change' },
          ];
          this.dataRule['receiverEntity['+index+'].addressDetail']= [
            { required: true, message: '接收捐赠单位地址不能为空', trigger: 'blur' }
          ];
          this.dataRule['receiverEntity['+index+'].relatePerson']= [
            { required: true, message: '接收捐赠单位联系人不能为空', trigger: 'blur' }
          ];
          this.dataRule['receiverEntity['+index+'].receiveTelephone']= [
            { required: true, message: '接收捐赠单位电话不能为空', trigger: 'blur' }
          ];

          this.dataForm.receiverEntity.push(receiverTpl);
      },

      //移除最后一个接收捐赠单位
      deleteReceiver (idx) {

        console.log("deleteReceiver idx:" + idx);
        if (this.dataForm.receiverEntity.length == 1) {
            this.$message.error('接收捐赠单位不能少于一条');
            return;
        }

        var index = idx;
        if (!idx && idx != 0) {
          index = this.dataForm.receiverEntity.length-1;
        }
        delete this.dataRule['receiverEntity['+index+'].receiveCompany'];
        delete this.dataRule['receiverEntity['+index+'].provinceId'];
        delete this.dataRule['receiverEntity['+index+'].addressDetail'];
        delete this.dataRule['receiverEntity['+index+'].relatePerson'];
        delete this.dataRule['receiverEntity['+index+'].receiveTelephone'];

        console.log("deleteReceiver index:" + index);
        this.dataForm.receiverEntity.splice(index, 1);
      },
      getAreaList (parentAreaId, areaType, entityType, index, callback) {
        this.$http({
          url: this.$http.adornUrl(`/project/area/select`),
          method: 'get',
          params: this.$http.adornParams({
            parentAreaId : parentAreaId?parentAreaId:''
          })
        }).then(({data})=>{
          if (data && data.code === 0) {
              callback(data.areaList, areaType, entityType, index)
          }
        })
      },
      areaCallback (areaList, areaType, entityType, index) {
        if (entityType === 'donator') {
          //捐赠单位部分
          if (areaType === 'p') {
            //省切换
            this.dataForm.donatorEntity.cityList = areaList
            //清理之前选择的
            this.dataForm.donatorEntity.cityId = null;

            this.dataForm.donatorEntity.countyList = [];
            this.dataForm.donatorEntity.countyId = null;
          } else if (areaType == 'c') {
            //==================================================================
            //市切换
            this.dataForm.donatorEntity.countyList = areaList
            //清理之前选择的
            this.dataForm.donatorEntity.countyId = null;
          }
        } else if (entityType === 'receiver'){
          //接收捐赠单位部分
          if (areaType === 'p') {
            //省切换
            this.dataForm.receiverEntity[index].cityList = areaList
            //清理之前选择的
            this.dataForm.receiverEntity[index].cityId = null;

            this.dataForm.receiverEntity[index].countyList = []
            this.dataForm.receiverEntity[index].countyId = null;
          } else if (areaType === 'c') {
            //市切换
            this.dataForm.receiverEntity[index].countyList = areaList
            //清理之前选择的
            this.dataForm.receiverEntity[index].countyId = null;
          }
        }
      },
      changeArea (value, areaType, entityType, index) {
        if (value && value !=null) {
            this.areaList = this.getAreaList(value, areaType, entityType, index, this.areaCallback)
        }
      },
      clearDataForm () {
          this.dataForm = defaultDataForm
      }
    }
  }
</script>
-->
