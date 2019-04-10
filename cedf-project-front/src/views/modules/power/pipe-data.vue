<!-- 压力曲线数据 -->
<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-col class="line" :span="3">选择日期</el-col>
        <el-col :span="8">
          <el-date-picker type="date" placeholder="开始日期" v-model="dataForm.startDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateStart"></el-date-picker>

        </el-col>
        <el-col class="line" :span="1">至</el-col>
        <el-col :span="8">
          <el-date-picker type="date" placeholder="结束日期" v-model="dataForm.endDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateEnd"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="searchDataList()">查询</el-button>
        <el-button type="warning" @click="searchDataList()">导出数据</el-button>
        <el-button type="warning" @click="searchDataList()">导入数据</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">

      <el-table-column
        prop="collectType"
        header-align="center"
        align="center"
        width="200"
        visible="false"
        label="采集数据名称">
      </el-table-column>
      <el-table-column
        prop="collectDate"
        header-align="center"
        align="center"
        label="采集日期" >
      </el-table-column>
      <el-table-column
        prop="collectData"
        header-align="center"
        align="center"
        label="采集结果" >
      </el-table-column>
      <el-table-column
        prop="collectUnit"
        header-align="center"
        align="center"
        label="采集单位" >
      </el-table-column>
      <el-table-column
        prop="collectHour"
        header-align="center"
        align="center"
        width="180"
        label="采集时间">
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
  </div>
</template>

<style>
  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    /** background: #99a9bf; */
  }
  .bg-purple {
    /** background: #d3dce6; */
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-input__inner {
    width: 100%;
  }
</style>

<script>
  import { getFinaceAmount } from '@/utils'
  import { validateDate } from '@/utils/validate'
  export default {
    data () {
      return {
        dataForm: {
          totalDonateAmount: '',
          totalDonateCashAmount: '',
          totalDonateMaterialAmount: '',
          projecName: '',
          startDate: '',
          endDate: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        pickerValidateStart: this.beginDate(),
        pickerValidateEnd: this.processDate()
      }
    },
    activated () {
      this.getDataList()
    },
    methods: {

      beginDate(){
        let self = this;
        return {
          disabledDate(time){
            if (time != null && time !='' && self.dataForm.endDate != null && self.dataForm.endDate !='') {
              if(validateDate(time, new Date(self.dataForm.endDate))) {
                return true;
              }
            }
          }
        }
      },

      processDate(){
        let self = this;
        return {
          disabledDate(time){
            if (time != null && time !='' &&  self.dataForm.startDate != null && self.dataForm.startDate !='') {
              if ((validateDate(new Date(self.dataForm.startDate),time))) {
                return true;
              }

            }
          }
        }
      },

      searchDataList () {
        if (this.firstSearch) {
          this.pageSize = 10
          this.pageIndex = 1
        }
        this.dataListLoading = true
        this.dataList = [{
          collectType:'压力',
          collectDate:'2018-07-12',
          collectData:'1500',
          collectUnit:'m2',
          collectHour:'10:18:30'
        },
          {
            collectType:'压力',
            collectDate:'2018-07-12',
            collectData:'1600',
            collectUnit:'m2',
            collectHour:'10:20:30'
          },
          {
            collectType:'压力',
            collectDate:'2018-07-12',
            collectData:'1700',
            collectUnit:'m2',
            collectHour:'10:22:30'
          },
          {
            collectType:'压力',
            collectDate:'2018-07-12',
            collectData:'1400',
            collectUnit:'m2',
            collectHour:'10:23:30'
          },
          {
            collectType:'压力',
            collectDate:'2018-07-12',
            collectData:'1300',
            collectUnit:'m2',
            collectHour:'10:5:30'
          }];
        this.totalPage = 5;
        this.dataListLoading = false
        //
        // this.$http({
        //   url: this.$http.adornUrl('/project/statistics/donateamount'),
        //   method: 'get',
        //   params: this.$http.adornParams({
        //     'page': this.pageIndex,
        //     'limit': this.pageSize,
        //     'projectName': this.dataForm.projectName,
        //     'startDate': this.dataForm.startDate,
        //     'endDate': this.dataForm.endDate
        //   })
        // }).then(({data}) => {
        //   if (data && data.code === 0) {
        //     this.firstSearch = false;
        //     var arrayObject = data.page.listData ;
        //     this.dataForm.totalDonateAmount = '0';
        //     this.dataForm.totalDonateCashAmount = '0';
        //     this.dataForm.totalDonateMaterialAmount = '0';
        //     this.dataForm.totalBalanceAmount = '0';
        //     this.dataList = [];
        //     this.totalPage = 0;
        //
        //     if (arrayObject != null ) {
        //       this.dataForm.totalDonateAmount = (arrayObject.totalDonateAmount == null ? 0 : arrayObject.totalDonateAmount);
        //       this.dataForm.totalDonateCashAmount = (arrayObject.totalDonateCashAmount == null ? 0 : arrayObject.totalDonateCashAmount);
        //       this.dataForm.totalDonateMaterialAmount = (arrayObject.totalDonateMaterialAmount == null ? 0 : arrayObject.totalDonateMaterialAmount);
        //       this.dataForm.totalBalanceAmount = (arrayObject.totalBalanceAmount == null ? 0 : arrayObject.totalBalanceAmount);
        //     }
        //
        //     if (data.page != null && data.page.page != null) {
        //       this.dataList = data.page.page.list;
        //       this.totalPage = data.page.page.totalCount;
        //     }
        //
        //   } else {
        //     this.dataList = []
        //     this.totalPage = 0
        //   }
        //   this.dataListLoading = false
        // })
      },

      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.dataList = [{
          collectType:'流量',
          collectDate:'2018-07-12',
          collectData:'1500',
          collectUnit:'m2',
          collectHour:'10:18:30'
        },
          {
            collectType:'流量',
            collectDate:'2018-07-12',
            collectData:'1600',
            collectUnit:'m2',
            collectHour:'10:20:30'
          },
          {
            collectType:'流量',
            collectDate:'2018-07-12',
            collectData:'1700',
            collectUnit:'m2',
            collectHour:'10:22:30'
          },
          {
            collectType:'流量',
            collectDate:'2018-07-12',
            collectData:'1400',
            collectUnit:'m2',
            collectHour:'10:23:30'
          },
          {
            collectType:'流量',
            collectDate:'2018-07-12',
            collectData:'1300',
            collectUnit:'m2',
            collectHour:'10:5:30'
          }];
        this.totalPage = 5;
        this.dataListLoading = false

        //
        // this.$http({
        //   url: this.$http.adornUrl('/project/statistics/donateamount'),
        //   method: 'get',
        //   params: this.$http.adornParams({
        //     'page': this.pageIndex,
        //     'limit': this.pageSize,
        //     'projectName': this.dataForm.projectName,
        //     'startDate': this.dataForm.startDate,
        //     'endDate': this.dataForm.endDate
        //   })
        // }).then(({data}) => {
        //   if (data && data.code === 0) {
        //     var arrayObject = data.page.listData ;
        //     this.dataForm.totalDonateAmount = '0';
        //     this.dataForm.totalDonateCashAmount = '0';
        //     this.dataForm.totalDonateMaterialAmount = '0';
        //     this.dataForm.totalBalanceAmount = '0';
        //     this.dataList = [];
        //     this.totalPage = 0;
        //
        //     if (arrayObject != null ) {
        //       this.dataForm.totalDonateAmount = (arrayObject.totalDonateAmount == null ? 0 : arrayObject.totalDonateAmount);
        //       this.dataForm.totalDonateCashAmount = (arrayObject.totalDonateCashAmount == null ? 0 : arrayObject.totalDonateCashAmount);
        //       this.dataForm.totalDonateMaterialAmount = (arrayObject.totalDonateMaterialAmount == null ? 0 : arrayObject.totalDonateMaterialAmount);
        //       this.dataForm.totalBalanceAmount = (arrayObject.totalBalanceAmount == null ? 0 : arrayObject.totalBalanceAmount);
        //     }
        //
        //     if (data.page != null && data.page.page != null) {
        //       this.dataList = data.page.page.list;
        //       this.totalPage = data.page.page.totalCount;
        //     }
        //
        //   } else {
        //     this.dataList = []
        //     this.totalPage = 0
        //   }
        //   this.dataListLoading = false
        // })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      } ,
      finaceAmountFormat(row, column, cellValue, index) {
        return getFinaceAmount(cellValue);
      }
    }
  }
</script>
