<template>
  <nav class="site-navbar" :class="'site-navbar--' + navbarLayoutType" style="background-color: #26382c;">
    <div class="site-navbar__header" style="background-color: #4B90BF">
      <h1 class="site-navbar__brand" @click="$router.push({ name: 'home' })">
          <a class="site-navbar__brand-lg" href="javascript:;"><img src="~@/assets/img/logo-title.png" /></a>
         <a class="site-navbar__brand-mini" href="javascript:;"> <img src="~@/assets/img/logo.png" /></a>
      </h1>
    </div>
    <div class="site-navbar__body clearfix" style="background-color: #4490C1;">
      <el-menu
        class="site-navbar__menu"
        mode="horizontal">
        <el-menu-item class="site-navbar__switch" index="0" @click="sidebarFold = !sidebarFold" style="background-color: #4490C1;">
          <icon-svg name="zhedie"></icon-svg>
        </el-menu-item>
      </el-menu>

      <el-menu
        class="site-navbar__menu site-navbar__menu--right"
        mode="horizontal">
        <el-menu-item index="1" @click="$router.push({ name: 'theme' })">
          <template slot="title">
            <el-badge value="设置">
              <icon-svg name="shezhi" class="el-icon-setting"></icon-svg>
            </el-badge>
          </template>
        </el-menu-item>
        <el-menu-item class="site-navbar__switch" index="3" style="background-color: #4490C1;">
          值班员: {{ userName }}
        </el-menu-item>
        <el-menu-item class="site-navbar__switch" index="4" @click.native="logoutHandle()" style="background-color: #4490C1;">
          <icon-svg name="tuichu"></icon-svg>
        </el-menu-item>
      </el-menu>
    </div>
    <!-- 弹窗, 修改密码 -->
    <update-password v-if="updatePassowrdVisible" ref="updatePassowrd"></update-password>
  </nav>
</template>

<style>
  .icon-svg {
    width: 1em;
    height: 1em;
  }
</style>

<script>
  import UpdatePassword from './main-navbar-update-password'
  export default {
    data () {
      return {
        updatePassowrdVisible: false
      }
    },
    components: {
      UpdatePassword
    },
    computed: {
      navbarLayoutType: {
        get () { return this.$store.state.common.navbarLayoutType }
      },
      sidebarFold: {
        get () { return this.$store.state.common.sidebarFold },
        set (val) { this.$store.commit('common/updateSidebarFold', val) }
      },
      mainTabs: {
        get () { return this.$store.state.common.mainTabs },
        set (val) { this.$store.commit('common/updateMainTabs', val) }
      },
      userName: {
        get () { return this.$store.state.user.name }
      }
    },
    methods: {
      // 修改密码
      updatePasswordHandle () {
        this.updatePassowrdVisible = true
        this.$nextTick(() => {
          this.$refs.updatePassowrd.init()
        })
      },
      // 退出
      logoutHandle () {
        this.$confirm(`确定进行[退出]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/sys/logout'),
            method: 'post',
            data: this.$http.adornData()
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$cookie.delete('token')
              this.$router.push({ name: 'login' }, () => {
                location.reload() // 刷新页面, 清空整站临时存储数据
              })
            }
          })
        }).catch(() => {})
      }
    }
  }
</script>
