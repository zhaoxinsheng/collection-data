<template>

  <div :class="className" :id="dairlyPipe" :style="{height:height,width:width}"></div>

</template>


<script>
  import echarts from 'echarts'
  import resize from './mixins/daypipe'

  export default {
    mixins: [resize],
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      dairlyPipe: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '200px'
      },
      height: {
        type: String,
        default: '200px'
      }
    },
    data() {
      return {
        chart: null,
        value2: new Date(2016, 9, 10, 18, 40),
        value3: new Date(2016, 9, 10, 18, 40)
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
        this.chart = echarts.init(document.getElementById(this.dairlyPipe))

        this.chart.setOption({
          backgroundColor: 'white',
          title: {
            top: 20,
            text: '日流量曲线',
            textStyle: {
              fontWeight: 'normal',
              fontSize: 16,
              color: 'black'
            },
            left: '1%'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              lineStyle: {
                color: '#57617B'
              }
            }
          },
          legend: {
            top: 20,
            icon: 'rect',
            itemWidth: 14,
            itemHeight: 5,
            itemGap: 13,
            data: ['时流量值', '流量平均值'],
            right: '4%',
            textStyle: {
              fontSize: 12,
              color: 'black'
            }
          },
          grid: {
            top: 100,
            left: '3%',
            right: '4%',
            bottom: '2%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            boundaryGap: false,
            name: '时/h',
            axisLine: {
              lineStyle: {
                color: '#57617B'
              }
            },
            data: ['1', '3', '5', '7', '9', '11', '13', '15', '17', '19', '21', '23']
          }],
          yAxis: [{
            type: 'value',
            name: '流量/m2',
            axisTick: {
              show: false
            },
            axisLine: {
              lineStyle: {
                color: '#57617B'
              }
            },
            axisLabel: {
              margin: 10,
              textStyle: {
                fontSize: 14
              }
            },
            splitLine: {
              lineStyle: {
                color: '#57617B'
              }
            }
          }],
          series: [
            {
              name: '时流量值',
              type: 'line',
              smooth: true,
              symbol: 'circle',
              symbolSize: 5,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              },
              areaStyle: {
                normal: {
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(0, 136, 212, 0.3)'
                  }, {
                    offset: 0.8,
                    color: 'rgba(0, 136, 212, 0)'
                  }], false),
                  shadowColor: 'rgba(0, 0, 0, 0.1)',
                  shadowBlur: 10
                }
              },
              itemStyle: {
                normal: {
                  color: 'rgb(0,136,212)',
                  borderColor: 'rgba(0,136,212,0.2)',
                  borderWidth: 12

                }
              },
              data: [11500, 11200, 11900, 11700, 11750, 11790,
                     10950, 10850, 10600, 10750, 10900,10500,
                10950, 10850, 10600, 10750, 10900,10500,
                10950, 10850, 10600, 10750, 10900,10500,
                10950, 10850, 10600, 10750, 10900
                     ]
            }, {
              name: '流量平均值',
              type: 'line',
              smooth: true,
              symbol: 'circle',
              symbolSize: 5,
              showSymbol: false,
              lineStyle: {
                normal: {
                  width: 1
                }
              },
              areaStyle: {
                normal: {
                  color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(219, 50, 51, 0.3)'
                  }, {
                    offset: 0.8,
                    color: 'rgba(219, 50, 51, 0)'
                  }], false),
                  shadowColor: 'rgba(0, 0, 0, 0.1)',
                  shadowBlur: 10
                }
              },
              itemStyle: {
                normal: {
                  color: 'rgb(219,50,51)',
                  borderColor: 'rgba(219,50,51,0.2)',
                  borderWidth: 12
                }
              },
              data: [10700, 10700, 10700, 10700, 10700,
                10700, 10700, 10700, 10700, 10700,
                10700, 10700, 10700, 10700, 10700,
                10700, 10700, 10700, 10700, 10700,
                10700, 10700, 10700, 10700]
            }]
        })
      }
    }
  }
</script>
