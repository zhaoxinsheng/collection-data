<template>
  <el-dialog
    :title="!dataForm.id ? '新增设备参数' : '修改设备参数'"
    :close-on-click-modal="false"
    @close="clearDataForm">
    <el-form :model="dataForm"   ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
      <el-row >
        <el-col :span="24"><h4>基本信息</h4></el-col>
        <div class="addForm_gray_bg">
          <el-col :span="12">
            <el-form-item label="设备名称" prop="equipmentName">
              <el-input v-model="dataForm.equipmentName" placeholder="设备名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="数据采集项目" prop="paramType">
              <el-select v-model="dataForm.collectTypeId"  placeholder="请选择" clearable style="width: 100%">
                <el-option v-for="item in collectTypeIds" :key="item.id" :label="item.id + '.' + item.name" :value="item.id" >  </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采集变量名称" prop="paramName">
              <el-input   v-model="dataForm.paramName" placeholder="采集变量名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="采集数量" prop="collectNum" style="margin-right: 1200px" >
              <el-input   type="text" v-model="dataForm.collectNum"  >
                <template slot="append">路</template>
              </el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="采集步长" prop="collectStep" style="margin-right: 1200px;" >
              <el-col :span="24">
                <el-input type="text"  v-model="dataForm.collectStep" clearable style="width: 100%">
                  <template slot="append">秒</template>
                </el-input>
              </el-col>
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
    equipmentName: '',
    collectTypeId: '',
    paramName: '',
    collectStep: '',
    collectNum: '',
    collectUnit:''
  }

  export default {
    data () {
      return {
        dataForm: defaultDataForm,
        collectTypeIds: [],
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.$http({
          url: this.$http.adornUrl('/collect/paramcollect/typeSelect'),
          dataType:'jsonp',
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.paramTypes = data && data.code === 0 ? data.list : []
        }).then(() => {
          // this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
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
      },
      clearDataForm () {
        this.dataForm = defaultDataForm
      }
    }
  }
</script>

