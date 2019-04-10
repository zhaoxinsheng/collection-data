<template>
  <div>
    <div id="gaugeContainer" style="width:400px; height:300px"></div>
  </div>
</template>

<style scoped>

</style>

<script>
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme
  require('echarts/lib/chart/gauge')
  import { debounce } from '@/utils'

  export default {
    name: 'Guage',
    data () {
      return {
        option:{
          tooltip: {
            formatter: "{a} <br/>{b} : {c}℃"
          },
          toolbox: {
            show: false,
            feature: {
              mark: {
                show: true
              },
              restore: {
                //show: true
              },
              saveAsImage: {
                //show: true
              }
            }
          },
          series: [
            {
              name: "空压机1 当前温度",
              type: "gauge",
              detail: {
                formatter: "{value}℃"
              },
              data: [
                {
                  value: 82,
                  name: "空压机1"
                }
              ]
            }
          ]
        }
      }
    },
    mounted() {
      let myChart = echarts.init(document.getElementById('gaugeContainer'))
      myChart.setOption(this.option);

    }
  }
</script>
