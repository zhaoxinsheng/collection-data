<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
          <el-col class="line" :span="3">协议日期</el-col>
         <el-col :span="8">
             <el-date-picker type="date" placeholder="开始日期" v-model="dataForm.startDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateStart"></el-date-picker>

          </el-col>
          <el-col class="line" :span="1">至</el-col>
             <el-col :span="8">
                <el-date-picker type="date" placeholder="结束日期" v-model="dataForm.endDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateEnd"></el-date-picker>
             </el-col>
        </el-form-item>
       <el-form-item>
        <el-button type="warning" @click="getDataList()">查询</el-button>
      </el-form-item>
      <div class="el-row" style="margin-left: -6px; margin-right: -6px;">
        <div class="el-col el-col-6 el-col-xs-12" style="padding-left: 6px; padding-right: 6px;">
          <div class="demo-color-box bg-blue"><font class="div-font">{{dataForm.totalProjectAmount|FinaceAmtFormat}}</font><div class="value">协议捐赠金额</div>
           </div>
         </div>
        <div class="el-col el-col-6 el-col-xs-12" style="padding-left: 6px; padding-right: 6px;">
          <div class="demo-color-box bg-green "><font class="div-font">{{dataForm.totalDonateAmount|FinaceAmtFormat}}</font>
            <div class="value">到账金额</div>
          </div>
         </div>
        <div class="el-col el-col-6 el-col-xs-12"style="padding-left: 6px; padding-right: 6px;">
           <div class="demo-color-box bg-yellow"><font class="div-font">{{dataForm.totalReceiveAmount|FinaceAmtFormat}}</font><div class="value">支出金额</div>
            </div>
         </div>
        <div class="el-col el-col-6 el-col-xs-12"style="padding-left: 6px; padding-right: 6px;">
        <div class="demo-color-box bg-green-gray"><font class="div-font">{{dataForm.totalBalanceAmount|FinaceAmtFormat}}</font><div class="value">余额</div>
           </div>
         </div>
        </div>
    </el-form>

    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">

     <el-table-column
        prop="projetType"
        header-align="center"
        align="center"
         width="300"
        visible="false"
        label="项目分类">
      </el-table-column>
      <el-table-column
        prop="projectNum"
        header-align="center"
         width="100"
        align="center"
        label="协议个数">
      </el-table-column>
      <el-table-column
        prop="projectAmount"
        header-align="center"
        align="center"
        label="协议捐款金额"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="donateAmount"
        header-align="center"
        align="center"
 label="到账金额"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="receiveAmount"
        header-align="center"
        align="center"
        label="支出金额" :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="balanceAmount"
        header-align="center"
        align="center"
        label="余额"
        :formatter="finaceAmountFormat">
      </el-table-column>
    </el-table>
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
          startDate:'',
          endDate: '',
          totalBalanceAmount: '',
          totalDonateAmount: '',
          totalProjectAmount: '',
          totalReceiveAmount: ''
        },
        dataList: [],
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

   // 格式化数字
      formatDonateAmount (row, column) {
           if (row.donateAmount == null) {
                 return 0;
           }
           return row.donateAmount;
      },

      formatReceiveAmount (row, column) {
           if (row.receiveAmount == null) {
                 return 0;
           }
           return row.receiveAmount;
      },

      formatBalanceAmount (row, column) {
           if (row.balanceAmount == null) {
                 return 0;
           }
           return row.balanceAmount;
      },

      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/project/statistics/category'),
          method: 'get',
          params: this.$http.adornParams({
            'startDate': this.dataForm.startDate,
            'endDate': this.dataForm.endDate
          })
        }).then(({data}) => {
           console.log('\n%c!<-------------------- 查询统计分类 -------------------->', 'color:blue')
         if (data && data.code === 0) {
            if (data.data != null ) {
               this.dataForm.totalProjectAmount = data.data.totalProjectAmount;
               this.dataForm.totalDonateAmount = data.data.totalDonateAmount;
               this.dataForm.totalReceiveAmount = data.data.totalReceiveAmount;
               this.dataForm.totalBalanceAmount = data.data.totalBalanceAmount;
               this.dataList = data.data.data;
            }

          } else {
            this.dataList = []
          }
          this.dataListLoading = false
        })
      } ,
      finaceAmountFormat(row, column, cellValue, index) {
        return getFinaceAmount(cellValue);
      }
    },
    mounted(){
    },
  }
</script>
