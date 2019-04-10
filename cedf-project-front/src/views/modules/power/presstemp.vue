<template>
  <div class='chart-container'>
    <daypipeChart height='100%' width='100%'></daypipeChart>
  </div>
</template>

<script>
  import dairlypresstemp from '@/components/Charts/dairlypresstemp'
  export default {
    data() {
      return {
        value2: new Date(2016, 9, 10, 18, 40),
        value3: new Date(2016, 9, 10, 18, 40)
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
