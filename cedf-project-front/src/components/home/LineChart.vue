<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme
  import { debounce } from '@/utils'

  export default {
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '350px'
      },
      autoResize: {
        type: Boolean,
        default: true
      },
      chartData: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        chart: null,
        sidebarElm: null
      }
    },
    watch: {
      chartData: {
        deep: true,
        handler(val) {
          this.setOptions(val)
        }
      }
    },
    mounted() {
      this.initChart()
      if (this.autoResize) {
        this.__resizeHandler = debounce(() => {
          if (this.chart) {
            this.chart.resize()
          }
        }, 100)
        window.addEventListener('resize', this.__resizeHandler)
      }

      // 监听侧边栏的变化
      this.sidebarElm = document.getElementsByClassName('sidebar-container')[0]
      this.sidebarElm && this.sidebarElm.addEventListener('transitionend', this.sidebarResizeHandler)
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      if (this.autoResize) {
        window.removeEventListener('resize', this.__resizeHandler)
      }

      this.sidebarElm && this.sidebarElm.removeEventListener('transitionend', this.sidebarResizeHandler)

      this.chart.dispose()
      this.chart = null
    },
    methods: {
      sidebarResizeHandler(e) {
        if (e.propertyName === 'width') {
          this.__resizeHandler()
        }
      },
      setOptions({ expectedData, actualData } = {}) {
        this.chart.setOption({
          xAxis: {
            data: ['1', '3', '5', '7', '9', '11', '13', '15', '17', '19', '21', '23'],
            boundaryGap: false,
            name: '时/h',
            axisTick: {
              show: false
            }
          },
          grid: {
            left: 10,
            right: 40,
            bottom: 20,
            top: 30,
            containLabel: true
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross'
            },
            padding: [5, 10]
          },
          yAxis: {
            type: 'value',
            name: '流量/m2',
            axisTick: {
              show: false
            }
          },
          legend: {
            // data: ['时流量值', '流量平均值']
            data: ['时流量值']
          },
          series: [{
            name: '时流量值',
            itemStyle: {
              normal: {
                color: '#FF005A',
                lineStyle: {
                  color: '#FF005A',
                  width: 2
                }
              }
            },
            smooth: true,
            type: 'line',
              data: [11500, 11200, 11900, 11700, 11750, 11790,
                    10950, 10850, 10600, 10750, 10900,10500,
                    10950, 10850, 10600, 10750, 10900,10500,
                    10950, 10850, 10600, 10750, 10900,10500,
                    10950, 10850, 10600, 10750, 10900
                   ],
            animationDuration: 2800,
            animationEasing: 'cubicInOut'
          }
          // ,
          //   {
          //     name: '流量平均值',
          //     smooth: true,
          //     type: 'line',
          //     itemStyle: {
          //       normal: {
          //         color: '#3888fa',
          //         lineStyle: {
          //           color: '#3888fa',
          //           width: 2
          //         },
          //         areaStyle: {
          //           color: '#f3f8ff'
          //         }
          //       }
          //     },
          //     data: actualData,
          //     animationDuration: 2800,
          //     animationEasing: 'quadraticOut'
          //   }

            ]
        })
      },
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons')
        this.setOptions(this.chartData)
      }
    }
  }
</script>
