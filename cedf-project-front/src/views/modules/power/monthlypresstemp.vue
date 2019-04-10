<template>
  <div class='chart-container'>
    <div style="padding-left: 200px" >
      <el-date-picker
        placeholder="选择日期"
        v-model="startTime" value-format="yyyy-MM" format="yyyy-MM">
      </el-date-picker>
      <el-button  type="primary">查询</el-button>
      <el-input
        placeholder="请输入内容"
        v-model="input1"
        :disabled="true" style="width: 30%;padding-left: 100px;color: red" >
      </el-input>
    </div>
    <monthlypresstemp height='100%' width='100%'></monthlypresstemp>
  </div>
</template>

<script>
  import monthlypresstemp from '@/components/Charts/monthlypresstemp'
  export default {
    data() {
      return {
        value2: new Date(2016, 9, 10, 18, 40),
        value3: new Date(2016, 9, 10, 18, 40),
        value1: '',
        input1:' 当月平均-30 ℃',
        startTime:''
      }
    },
    name: 'monthlypresstemp1',
    components: { monthlypresstemp }
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
