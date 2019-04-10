<template>
    <el-form class="passwordForm"  :model="dataForm"  ref= "dataForm" :rules="dataRule" @keyup.enter.native="onSubmit()" label-width="80px">
      <el-form-item label="原密码" prop="password">
        <el-input   type="password" v-model="dataForm.password"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
           <el-input type="password"  v-model="dataForm.newPassword" clearable></el-input>
      </el-form-item>
      <el-form-item label="确认密码"  prop="comfirmPassword">
          <el-input type="password" v-model="dataForm.comfirmPassword" clearable></el-input>
      </el-form-item>




      <el-form-item>
          <el-button type="primary" @click="onSubmit">修改密码</el-button>
      </el-form-item>
    </el-form>
</template>
<style>
  .passwordForm .el-input__inner {
    width: 30%;
  }
</style>


<script>
  var pwdRgx = /^[0-9A-Za-z,.;~!@#$%^*()_+=/:]{6,18}$/;

  export default {
    data () {
     var validatePassword = (rule, value, callback) => {
        if (!/\S/.test(value)) {
          callback(new Error('密码不能为空'))
        } else {
          callback()
        }
      }

      var validateComfirmPassword = (rule, value, callback) => {
        if (!/\S/.test(value) || !isPassword(value)) {
          callback(new Error('确认密码不能为空并且长度在6到18个英文字符'))
          return;
        } else if (this.dataForm.newPassword !== value) {
          callback(new Error('确认密码与密码输入不一致'))
          return;
        } else {
           if (this.dataForm.newPassword !== '') {
              this.$refs.dataForm.validateField('newPassword');
           }
           callback();
        }
      }
      var validateNewPassword = (rule, value, callback) => {
        if (!/\S/.test(value) || !isPassword(value)) {
          callback(new Error('新密码不能为空并且长度在6到18个英文字符'))
        }  else if (!pwdRgx.test(value)) {
          callback(new Error('字节长度为6-18字符，只可包含字母、数字、常用英文符号,.;~!@#$%^*()_+-=/:'))
        }  else if (this.dataForm.comfirmPassword !== value) {
          callback(new Error('确认密码与密码输入不一致'))
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
          newPassword: '',
          comfirmPassword: '',
          salt: '',
          email: '',
          mobile: '',
          realName: '',
          roleIdList: [],
          status: 1
        } ,
        dataRule: {
          password: [
            { required: true, message: '原密码不能为空', trigger: 'blur' },
            { validator: validatePassword, trigger: 'blur' }
          ],
          newPassword: [
            { required: true, message: '新密码不能为空', trigger: 'blur' },
            { validator: validateNewPassword, trigger: 'blur' }
          ],
          comfirmPassword: [
            { required: true, message: '确认密码不能为空', trigger: 'blur' },
            { validator: validateComfirmPassword, trigger: 'blur' }
          ]
        }
      }
    },
    activated () {

    },
    methods: {
        // 表单提交
      onSubmit () {
            this.$http({
              url: this.$http.adornUrl(`/sys/user/password`),
              method: 'post',
              data: this.$http.adornData({
                'password': this.dataForm.password,
                'newPassword': this.dataForm.newPassword,
                'comfirmPassword':this.dataForm.comfirmPassword
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
