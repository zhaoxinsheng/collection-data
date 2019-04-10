<template>
  <el-card class="box-card">
  <el-form :model="dataForm"   ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
    <el-row >
      <el-col :span="24"><h4>电量参数设定</h4></el-col>
      <div class="addForm_gray_bg">
        <el-col :span="10">
          <el-form-item label="用电总量基数上调：" prop="airrate">
            <el-input v-model="dataForm.airrate" placeholder="电量基数上调" style="width:30%">
              <template slot="append">%</template>
            </el-input>
          </el-form-item>

          <el-form-item label="基本电价基数上调：" prop="powerPrice">
            <el-input v-model="dataForm.powerPrice" placeholder="基本电价基数上调" style="width:30%">
              <template slot="append">%</template>
            </el-input>
          </el-form-item>
          <el-form-item label="基础电价：" prop="powerBasePrice">
            <el-input v-model="dataForm.powerBasePrice" placeholder="基础电价" style="width:30%">
              <template slot="append">元</template>
            </el-input>
          </el-form-item>
        </el-col>
      </div>
    </el-row>
  </el-form>
  </el-card>
</template>
<style>
  .item {
    padding: 18px 0;
  }
  .box-card {
    width: 95%;
    height: 400px;
  }
</style>
<script>
  export default {
    data () {
      return {
        show: false,
        dataForm: {
          airrate: '',
          airdust: '',
          actstream: '',
          airoil: '',
          airouttemp: '',
          airpress: '',
          airintemp : '',
          powerBasePrice:'',
          powerPrice:''
        }
      }
    },
    activated () {
      this.init()
    },
    methods: {
      init () {
          this.dataForm.airrate = 10,
          this.dataForm.airdust = 20,
          this.dataForm.actstream = 13,
          this.dataForm.airoil = 11,
          this.dataForm.airouttemp = 19,
          this.dataForm.airpress = 30,
          this.dataForm.airintemp = 35,
            this.dataForm.powerBasePrice= 0.85,
          this.dataForm.powerPrice=12
      },

      // 表单提交
      onSubmit () {

      }
    }
  }
</script>
