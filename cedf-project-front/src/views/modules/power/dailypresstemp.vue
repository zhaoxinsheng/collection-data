<template>
  <div class='chart-container'>
    <div style="padding-left: 200px" >
      <el-date-picker
        placeholder="选择日期"
        v-model="startTime" value-format="yyyy-MM" format="yyyy-MM">
      </el-date-picker>
      <el-button  type="primary">查询</el-button>
      <el-input

        v-model="input1"
        :disabled="true" style="width: 30%;padding-left: 100px;color: red" >
      </el-input>
    </div>
    <dairlypresstemp height='100%' width='100%'></dairlypresstemp>
  </div>
</template>

<script>
  import dairlypresstemp from '@/components/Charts/dairlypresstemp'

  export default {
    data() {
      return {
        value2: new Date(2016, 9, 10, 18, 40),
        value3: new Date(2016, 9, 10, 18, 40),
        value1: '',
        input1:'日平均压力漏点 -40℃',
        startTime:''
      }
    },
    name: 'dairlypresstemp1',
    components: { dairlypresstemp }
  }
</script>

<style scoped>
  .chart-container{
    position: relative;
    padding:20px;
    width: 100%;
    height:85vh;
  }
</style>
