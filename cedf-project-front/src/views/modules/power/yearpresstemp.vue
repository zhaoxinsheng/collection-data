<template>
  <div class='chart-container'>
    <div style="padding-left: 200px" >
      <el-date-picker type="date" placeholder="年份" v-model="value1" style="width: 20%;" value-format="yyyy" format="yyyy"
      ></el-date-picker>
      <el-button  type="primary">查询</el-button>
      <el-input
        placeholder="请输入内容"
        v-model="input1"
        :disabled="true" style="width: 30%;padding-left: 100px;color: red" >
      </el-input>
    </div>
    <yearpresstemp height='100%' width='100%'></yearpresstemp>
  </div>
</template>

<script>
  import yearpresstemp from '@/components/Charts/yearpresstemp'
  export default {
    data() {
      return {
        value2: new Date(2016, 9, 10, 18, 40),
        value3: new Date(2016, 9, 10, 18, 40),
        input1: '年压力漏点平均值-40 ℃',
        value1: ''
      }
    },
    name: 'yearpresstemp1',
    components: { yearpresstemp }
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
