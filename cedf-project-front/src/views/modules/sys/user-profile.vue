<template>
    <el-form  class="profileForm"  :model="dataForm"  ref= "dataForm" :rules="dataRule" @keyup.enter.native="onSubmit()" label-width="80px">
      <el-form-item label="用户名" prop="loginName">
        <el-input v-model="dataForm.userName" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="realName">
           <el-input v-model="dataForm.realName"   clearable></el-input>
      </el-form-item>
      <el-form-item label="联系电话"  prop="mobile">
          <el-input v-model="dataForm.mobile" clearable></el-input>
      </el-form-item>
      <el-form-item label="角色">
         <el-input v-model="dataForm.role" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
</template>
<style>
  .profileForm .el-input__inner {
    width: 30%;
  }
</style>


<script>
  import { isUserName, isMobile,isBlank } from '@/utils/validate'
  export default {
    data () {
       var validateUserName = (rule, value, callback) => {
        if (!isUserName(this.dataForm.realName)) {
           callback(new Error('姓名必须是1-20位字符'))
        } else {
           callback()
        }
      }
      var validateMobile = (rule, value, callback) => {
        if (!isMobile(this.dataForm.mobile)) {
          callback();
        } else {
          callback();
        }
     }

      return {
        visible: false,
        roleList: [],
        show: false,
        dataForm: {
          id: 0,
          userName: '',
          password: '',
          comfirmPassword: '',
          salt: '',
          email: '',
          mobile: '',
          realName: '',
          roleIdList: [],
          status: 1
        } ,
        dataRule: {
          realName: [
            { required: true, message: '姓名不能为空', trigger: 'blur' },
            { validator: validateUserName, trigger: 'blur'}
          ],
          mobile: [
            { required: true, message: '联系电话不能为空', trigger: 'blur' },
            { validator: validateMobile, trigger: 'blur' }
          ]
        }

      }
    },
    activated () {
      this.init();
    },
    methods: {

      init () {
           this.$http({
              url: this.$http.adornUrl(`/sys/user/info`),
              method: 'get',
              data: this.$http.adornData
            }).then(({data}) => {

               if (data && data.code === 0) {
                  var user = data.user;
                  this.dataForm.id = user.userId;
                   this.dataForm.userName = user.username;
                   this.dataForm.realName = user.realName;
                   this.dataForm.mobile = user.mobile;
                   this.dataForm.role =  user.roleName;

              } else {
                this.$message.error(data.msg)
              }
         })
      },

        // 表单提交
      onSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/user/updateUserName`),
              method: 'post',
              data: this.$http.adornData({
                'realName': this.dataForm.realName,
                'mobile': this.dataForm.mobile
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
        })
      }

    }
  }

</script>
