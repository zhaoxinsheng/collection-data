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

        this.chart.setOption({
          backgroundColor: 'white',
          title: {
            top: 20,
            text: '月排气曲线',
            subtext: '空压机1 总用电量32Kwh 总排气量：16000m³\n\r\n 空压机2 总用电量100Kwh 总排气量：26000m³',
            align: 'right',
            x:'center',
            textStyle: {
              fontWeight: 'normal',
              fontSize: 16,
              color: 'black'
            }
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
            data: ['空压机1','空压机2'],
            right: '4%',
            textStyle: {
              fontSize: 12,
              color: 'black'
            }
          },
          grid: {
            top: 100,
            left: '5%',
            right: '5%',
            bottom: '5%',
            containLabel: true
          },
          xAxis: [{
            type: 'category',
            boundaryGap: false,
            name: '日',
            axisLine: {
              lineStyle: {
                color: '#57617B'
              }
            },
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12','13','14','15','16','17','18','19','20',
              '21','22','23','24','25','26','27','28','29','30']
          }],
          yAxis: [{
            type: 'value',
            name: '千立方米km³',
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
              name: '日',
              textStyle: {
                fontSize: 14
              }
            },
            splitLine: {
              lineStyle: {
                color: '#57617B'
              }
            }
          } ],
          series: [
            {
              name: '空压机2',
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
              data: [0.82, 0.86, 0.85, 0.82, 0.80, 0.82, 0.85, 0.80, 0.86, 0.84, 0.82,0.90,0.91,0.85,0.88
                ,0.80,0.83,0.84,0.88,0.80,0.85,0.88,0.82,0.80,0.85,0.88,0.90,0.80,0.80,0.80]
            }, {
              name: '空压机1',
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
              data: [0.75, 0.83, 0.85, 0.85, 0.82, 0.87, 0.83, 0.87, 0.85, 0.83, 0.88,0.85,0.87,0.83,0.85,0.87,0.85,0.83,0.85,
                0.87,0.88,0.85,0.87,0.88,0.85,0.88,0.90,0.85,0.83,0.85]
            }]
        })
      }
    }
  }
</script>
