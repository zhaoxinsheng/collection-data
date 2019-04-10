import Vue from 'vue'
import App from '@/App'
import router from '@/router' // api: https://github.com/vuejs/vue-router
import store from '@/store' // api: https://github.com/vuejs/vuex
import VueCookie from 'vue-cookie' // api: https://github.com/alfhen/vue-cookie
import '@/element-ui' // api: https://github.com/ElemeFE/element
import '@/icons' // api: http://www.iconfont.cn/
import '@/element-ui-theme'
import '@/assets/scss/index.scss'
// import VueResource from 'vue-resource'
import httpRequest from '@/utils/httpRequest' // api: https://github.com/axios/axios

// import mockdata from './mock';
import echarts from 'echarts';


import {
  isAuth
} from '@/utils'

Vue.use(VueCookie)
// Vue.use(VueResource)
Vue.config.productionTip = false

// 非生产环境, 适配mockjs模拟数据                 // api: https://github.com/nuysoft/Mock
/**
if (process.env.NODE_ENV !== 'production') {
  require('@/mock')
}
**/
// 挂载全局
Vue.prototype.$http = httpRequest // ajax请求方法
Vue.prototype.isAuth = isAuth // 权限方法
Vue.prototype.$echarts = echarts

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {
    App
  }
})


Vue.filter('FinaceAmtFormat', function (value) {
  if (!value) {
    return '0.00'
  }
  // 获取整数部分
  var intPart = Number(value).toFixed(0)
  if (intPart > value) {
    intPart = intPart - 1
  }
  var intPartFormat = intPart.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') // 将整数部分逢三一断

  var floatPart = '.00' // 预定义小数部分
  var value2Array = String(value).split('.')

  // =2表示数据有小数位
  if (value2Array.length === 2) {
    floatPart = value2Array[1].toString() // 拿到小数部分

    if (floatPart.length === 1) { // 补0,实际上用不着
      return intPartFormat + '.' + floatPart + '0'
    } else {
      return intPartFormat + '.' + floatPart
    }
  } else {
    return intPartFormat + floatPart
  }
})
