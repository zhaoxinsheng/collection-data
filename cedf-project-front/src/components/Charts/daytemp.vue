<template>

  <div :class="className" :id="dayTemp" :style="{height:height,width:width}"></div>

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
      dayTemp: {
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
        this.chart = echarts.init(document.getElementById(this.dayTemp))

        this.chart.setOption({
          backgroundColor: 'white',
          title: {
            top: 20,
            text: '日温度曲线',
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
            data: ['进气温度', '排气温度'],
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
            data: ['1','2','3','4','5',
                  '6','7','8','9','10',
                   '11','12','13','14',
                   '15','16','17','18','19',
                   '20','21','22','23','24',
            ]
          }],
          yAxis: [{
            type: 'value',
            name: '温度℃',
            axisTick: {
              show: false
            },
            axisLine: {
              lineStyle: {
                color: 'blue'
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
              name: '进气温度',
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
              data: [30, 20, 35, 37, 32,
                35, 35, 36, 40, 42,43,
                42, 50, 52, 55, 53,56,
                52, 53, 42, 40, 35,38,
                35, 36, 35, 35
              ]
            }, {
              name: '进气平均温度',
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
                    color: 'rgba(10, 60, 51, 0.3)'
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
              data: [40, 40, 40, 40, 40,
                40, 40, 40, 40, 40,
                40, 40, 40, 40, 40,
                40, 40, 40, 40, 40,
                40, 40, 40, 40]
            },

            {
              name: '排气温度',
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
                    color: 'rgba(200, 100, 212, 0.3)'
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
                  color: 'rgb(200,136,212)',
                  borderColor: 'rgba(0,136,212,0.2)',
                  borderWidth: 12
                }
              },
              data: [25, 20, 22, 30, 32,
                33, 34, 35, 37, 42,43,
                45, 46, 42, 40, 39,38,
                37, 36, 35, 45, 50,33,
                38, 42, 41, 43
              ]
            }, {
              name: '排气平均温度',
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
                  color: 'rgb(219,50,51,0.1)',
                  borderColor: 'rgba(219,50,51,0.2)',
                  borderWidth: 12
                }
              },
              data: [35, 35, 35, 35, 35,
                35, 35, 35, 35, 35,
                35, 35, 35, 35, 35,
                35, 35, 35, 35, 35,
                35, 35, 35, 35]
            }
          ]
        })
      }
    }
  }
</script>
