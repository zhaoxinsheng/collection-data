<template>
  <div :class="className" :id="monthlyair" :style="{height:height,width:width,minHeight:'500px'}"></div>
</template>

<script>
  import echarts from 'echarts'
  import resize from './mixins/airmonthly'

  export default {
    mixins: [resize],
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      monthlyair: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '200px'
      },
      height: {
        type: String,
        default: '500px'
      }
    },
    option : {
      tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
      },
      toolbox: {
        feature: {
          restore: {},
          saveAsImage: {}
        }
      },
      series: [
        {
          name: '业务指标',
          type: 'gauge',
          detail: {formatter:'{value}%'},
          data: [{value: 50, name: '完成率'}]
        }
      ]
    },
    data() {
      return {
        chart: null
      }
    },
    mounted() {
      this.initChart()
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.chart.dispose()
      this.chart = null
    },
    methods: {
     initChart() {
        this.chart = echarts.init(document.getElementById(this.monthlyair))

        this.chart.setOption(


        )
      }
    }
  }
</script>
