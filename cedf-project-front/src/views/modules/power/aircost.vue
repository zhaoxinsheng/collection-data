<template>
  <el-card class="box-card">
  <el-form :model="dataForm"   ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="150px">
    <el-row >
      <el-col :span="24"><h4>空气成本</h4></el-col>
      <div class="addForm_gray_bg">
        <el-col :span="10">
          <el-form-item label="单位压缩空气能耗" prop="airrate">
            <el-input v-model="dataForm.airrate" placeholder="单位压缩空气能耗" style="width:30%">
              <template slot="append">Kwh/m<sup>3</sup></template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="含尘量" prop="airdust">
            <el-input v-model="dataForm.airdust" placeholder="含尘量" style="width:30%">
              <template slot="append">g/m<sup>3</sup></template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="10">
          <el-form-item label="实际流量" prop="actstream">
            <el-input v-model="dataForm.actstream" placeholder="实际流量" style="width:30%">
              <template slot="append">M<sup>3</sup>/min</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="含油量" prop="airoil">
            <el-input v-model="dataForm.airoil" placeholder="含油量" style="width:30%">
              <template slot="append">Ppm</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="10">
          <el-form-item label="排气温度" prop="airouttemp">
            <el-input type="number" v-model="dataForm.airouttemp" placeholder="排气温度" style="width:30%">
              <template slot="append">&#8451;</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="10">
          <el-form-item label="压力露点">
            <el-input v-model="dataForm.airpress" placeholder="压力露点" style="width:30%">
              <template slot="append">&#8451;</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="10">
          <el-form-item label="进气温度">
            <el-input v-model="dataForm.airintemp" placeholder="进气温度" style="width:30%">
              <template slot="append">&#8451;</template>
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
          airoil: "",
          airouttemp: "",
          airpress: "",
          airintemp : ""
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
          this.dataForm.airintemp = 35
      },

      // 表单提交
      onSubmit () {

      }
    }
  }
</script>
