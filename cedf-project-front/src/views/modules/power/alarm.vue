<template>
  <div class="mod-user">
    <el-table :data="dataList" border v-loading="dataListLoading"  style="width: 100%;">
      <el-table-column  label="当前提醒"  header-align="center" align="center" style="color:red">
      <el-table-column prop="tipDesc" header-align="center" align="center" label="提醒内容"> </el-table-column>
      <el-table-column prop="tipTime" header-align="center" align="center"  label="提醒时间"/>   </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button   type="text" size="small" class="el-button--text-gray" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
       </el-table-column>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>


    <el-table :data="dataListCancel" border v-loading="dataListLoading"  style="width: 100%;">
      <el-table-column label="取消的提醒"  header-align="center" align="center" style="color:red">
        <el-table-column prop="tipDesc" header-align="center" align="center" label="提醒内容"> </el-table-column>
        <el-table-column prop="tipTime" header-align="center" align="center"  label="提醒时间"/>   </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button   type="text" size="small" class="el-button--text-gray" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="cancelChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

    <el-table :data="dataListAlarm" border v-loading="dataListLoading" style="width: 100%;">
      <el-table-column label="设置的提醒项"  header-align="center" align="center" style="color:red">
        <el-table-column prop="alarmType" header-align="center" align="center" label="提醒项"> </el-table-column>
        <el-table-column prop="alarmDesc" header-align="center" align="center" label="提醒内容"> </el-table-column>
        <el-table-column prop="quartz" header-align="center" align="center"  label="提醒周期"/>   </el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button   type="text" size="small" class="el-button--text-gray" @click="editHandle(scope.row.id)">编辑</el-button>
          <el-button   type="text" size="small" class="el-button--text-gray" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="normalChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </div>
</template>
<script>
  export default {
    data () {
      return {
        dataForm: {
          projectId: '',
          projectNo: '',
          projectName: '',
          receiveCompany: '',
          amountType: '',
          startDate: '',
          endDate: ''
        },
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        dataList: [{
          'tipDesc': '运行压力异常，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
          'tipTime': '2018-07-10 10:20:24'
        }, {
            'tipDesc': '流量异常，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
            'tipTime': '2018-07-15 11:20:00'
          }],
        dataListCancel :[{
          'tipDesc': '排气温度过高，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
          'tipTime': '2018-07-12 10:30:24'
        },
          {
            'tipDesc': '流量异常，机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
            'tipTime': '2018-07-20 11:30:00'
          }],
        dataListAlarm: [{
          'alarmType': '电费设置',
          'alarmDesc': '电费是否发生变化，如果是，请对电费参数重新设置',
          'quartz': '每三个月一次'
        },
        {
          'alarmType': '压力露',
          'alarmDesc': '压力露点异常,机组运行出现异常，请通知相关工作人员进行排查，若无法查找出原因，可联系我方进行监测服务',
          'quartz': '每分钟一次'
        }]
      }
    },
    /**
    activated () {
      this.getDataListcurrent()
    },
     **/
    methods: {

      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataListcurrent()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataListcurrent()
      },

      // 取消的每页数
      cancelSizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataListCancel()
      },
      // 取消的当前页
      cancelChangeHandle (val) {
        this.pageIndex = val
        this.getDataListCancel()
      },

      // 正常的每页数
      normalSizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataListNormal()
      },
      // 正常的当前页
      normalChangeHandle (val) {
        this.pageIndex = val
        this.getDataListNormal()
      },

      // 获取数据列表
      getDataListcurrent () {
        this.$http({
          url: this.$http.adornUrl(`collect/tips/listtips`),
          method: 'get',
          params: this.$http.adornParams()
        }) .then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      cancelChangeHandle () {
        this.$http({
          url: this.$http.adornUrl(`collect/tips/listcancel`),
          method: 'get',
          params: this.$http.adornParams()
        }) .then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
              this.dataList = []
              this.totalPage = 0
            }
            this.dataListLoading = false
          })
      },
      normalChangeHandle () {
        this.$http({
          url: this.$http.adornUrl(`collect/tips/listnormal`),
          method: 'get',
          params: this.$http.adornParams()
        }) .then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 删除
      deleteHandle (id) {

      }
    }
  }
</script>
