<template>
  <el-form    :model="dataForm"  ref= "dataForm"   @keyup.enter.native="onSubmit()" label-width="80px">
    <el-form-item label="单台设备功率公式" prop="collectNum" style="white-space : nowrap;margin-right: 10px" >
      <img src="~@/assets/img/single.png" style="margin-left: 100px"/>
    </el-form-item>
    <el-form-item label="比功率公式" prop="collectNum" style="white-space : nowrap;margin-right: 10px" >
        <img src="~@/assets/img/rate.png" style="margin-left: 100px"/>
    </el-form-item>
    <el-form-item label="压缩空气总气量计算公式" prop="collectNum" style="white-space : nowrap; margin-right: 10px" >
        <label  style="margin-left: 100px"> Q总 = Q1 + Q2 + Q3 + QN</label>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">确认</el-button>
    </el-form-item>
  </el-form>
</template>

<style>
  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
</style>

<style>

</style>
<script>
  export default {
    name:'quill-editor',
    data () {
      return {
        dataForm: {
          formulaValue: '',
          donatorName: ''
        }
      }
    },
    components: {

    },
    activated () {
      this.init()
    },
    methods: {
      init () {
        // this.$http({
        //   url: this.$http.adornUrl(`collect/paramcollect/info/stream`),
        //   method: 'get',
        //   params: this.$http.adornParams()
        // }).then(({data}) => {
        //   this.$refs['dataForm'].resetFields();
        //   if (data && data.code === 0) {
        //     this.dataForm.collectNum = data.paramCollect.collectNum;
        //     this.dataForm.collectStep = data.paramCollect.collectStep;
        //   }
        // })
      },

      // 表单提交
      onSubmit () {
        this.$http({
          url: this.$http.adornUrl(`/collect/paramcollect/save`),
          method: 'post',
          data: this.$http.adornData({
            'collectNum': this.dataForm.collectNum,
            'collectStep': this.dataForm.collectStep,
            'collectTypeId': 'stream'
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.visible = false
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })

      }
    }
  }
</script>
