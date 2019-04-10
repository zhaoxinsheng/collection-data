<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="设备名称">
        <el-select v-model="dataForm.value5" multiple placeholder="请选择设备">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="选择时间">
        <el-row>
          <el-button type="primary">当天</el-button>
          <el-button type="primary">本周</el-button>
          <el-button type="primary">本月</el-button>
          <el-button type="primary">本年</el-button>
        </el-row>
      </el-form-item>

      <el-form-item label="">
        <el-date-picker
          v-model="value4"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>

       <el-form-item>
        <el-button @click="getDataList()" type="warning">查询</el-button>
         <el-button @click="getDataList()" type="warning">导出报表</el-button>
      </el-form-item>
    </el-form>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="图表展示" name="first">
<!--         <daypowerChart height='100%' width='100%'></daypowerChart> -->
        <monthlyairChart height='100%' width='100%'></monthlyairChart>

      </el-tab-pane>
      <el-tab-pane label="列表展示" name="second">
        <el-table
          :data="dataList"
          border
          show-summary
          :summary-method="getSummaries"
          v-loading="dataListLoading"
          @selection-change="selectionChangeHandle"
          style="width: 100%;">

          <el-table-column
            prop="id"
            header-align="center"
            align="center"
            width="80"
            visible="false"
            v-if="false"
            label="ID">
          </el-table-column>
          <el-table-column
            prop="equipName"
            header-align="center"
            align="center"
            label="设备名称">
          </el-table-column>
          <el-table-column
            prop="equipTime"
            header-align="center"
            align="center"
            label="时间">
          </el-table-column>
          <el-table-column
            prop="collectName"
            header-align="center"
            show-overflow-tooltip
            align="center"
            label="用电量(kwh)">
          </el-table-column>
          <el-table-column
            prop="airName"
            header-align="center"
            show-overflow-tooltip
            align="center"
            label="排气量(m³)">
          </el-table-column>
          <el-table-column
            prop="powPrice"
            header-align="center"
            show-overflow-tooltip
            align="center"
            label="电价(元)">
          </el-table-column>
          <el-table-column
            fixed="right"
            header-align="center"
            align="center"
            prop="airRate"
            width="150"
            label="成本(元/m³)">
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
      </el-tab-pane>
    </el-tabs>

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
  .chart-container{
    position: relative;
    padding:20px;
    width: 100%;
    height:300px;
  }
</style>

<script>
  import AddOrUpdate from './equip-add-or-update'
  import { validateDate } from '@/utils/validate'
  import { getFinaceAmount } from '@/utils'
  import daypowerChart from '@/components/Charts/daypower'
  import monthlyairChart from '@/components/Charts/monthlyairtout'
  export default {
    data () {
      return {

        value2: new Date(2016, 9, 10, 18, 40),
        value3: new Date(2016, 9, 10, 18, 40),
        value1: '',
        input1: ' 累计 300kwh',
        startTime:'',
        endTime: '',
        activeName: 'first',
        value4: [new Date(2018, 11, 1, 0, 0), new Date(2018, 11, 31, 23, 59)],
        options: [{
          value: '1',
          label: '空压机1'
        }, {
          value: '2',
          label: '空压机2'
        }, {
          value: '3',
          label: '空压机3'
        }, {
          value: '4',
          label: '空压机4'
        }, {
          value: '5',
          label: '空压机5'
        }],
        dataForm: {
          equipName: '',
          donatorName: '',
          receiverName: '',
          projectType: '',
          startDate: '',
          endDate: '',
          value5:'',
          equipTime:'',
          airName:''
        },
        projectTypes: [],
        dataList: [],
        pageIndex: 1,
        pageSize: 20,
        totalPage: 0,
        firstVisible:true,
        secondVisible:false,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        uploadVisible: false
      }

      name: 'daypowerChart1'
    },
    components: {
      // daypowerChart
      monthlyairChart
    },
    activated () {
      this.getDataList()
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
        if (tab.name === 'first') {
         // firstVisible = true;
          //secondVisible = false;
          activeName: 'first'
        } else {
          //secondVisible = true;
         // firstVisible = false;
          activeName: 'second'
        }
      },

      getSummaries(param) {
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '平均值 (kwh/m³)';
            return;
          }
          const values = data.map(item => Number(item[column.property]));
          if (!values.every(value => isNaN(value))) {
            const countValue = 0 ;
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              countValue = countValue +1;
              if (!isNaN(value)) {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
            sums[index] = sums[index]/countValue ;
          } else {
            sums[index] = '';
          }
          if (index === 5 ) {
            sums[5] =  sums[2] /  sums[3] *  sums[4]   ;
          }
        });
        return sums;
      },

      initTypeSelect () {
          this.$http({
            url: this.$http.adornUrl('/project/base/typeSelect'),
            method: 'get',
            params: this.$http.adornParams()
        }).then(({data}) => {
          this.projectTypes = data && data.code === 0 ? data.list : []
        })
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true

        this.$http({
          url: this.$http.adornUrl('/project/base/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'projectNo': this.dataForm.projectNo,
            'donatorName': this.dataForm.donatorName,
            'receiverName': this.dataForm.receiverName,
            'projectType': this.dataForm.projectType,
            'startDate': this.dataForm.startDate,
            'endDate': this.dataForm.endDate,
            'sidx': 'projectNo',
            'order': 'DESC'
          })
        }).then(({data}) => {


          // if (data && data.code === 0) {
          //   this.dataList = data.page.list
          //   this.totalPage = data.page.totalCount
          // } else {
          //   this.dataList = []
          //   this.totalPage = 0
          // }

          this.dataList = [{
            id:1,
            equipName:'空压机1',
            collectName:'80',
            airName:'6000',
            equipTime:'2018-12-01 00:00 -- 2018-12-07 23:59',
            powPrice:'0.854',
            airRate:'0.01'
          },
            {
              id:1,
              equipName:'空压机1',
              collectName:'60',
              airName:'2000',
              equipTime:'2018-12-08 00:00 -- 2018-12-14 23:59',
              powPrice:'0.853',
              airRate:'0.02'
            },
            {
              id:1,
              equipName:'空压机1',
              collectName:'90',
              airName:'4000',
              equipTime:'2018-12-15 00:00 -- 2018-12-21 23:59',
              powPrice:'0.860',
              airRate:'0.02'
            },
            {
              id:1,
              equipName:'空压机1',
              collectName:'95',
              airName:'4000',
              powPrice:'0.861',
              equipTime:'2018-12-22 00:00 -- 2018-12-31 23:59',
              airRate:'0.02'

            }];
          this.totalPage = 4;
          this.dataListLoading = false
          this.dataListLoading = false
        })
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
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        //加载typeSelect
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定要删除此条数据吗？删除后将把相关数据一并删除`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/project/base/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },

      getValue() {
         return this.dataForm.type
      },// 上传文件
      importHandle () {
        this.uploadVisible = true;
        this.$nextTick(() => {
          this.$refs.upload.init()
        })
      },

      downloadTemplateHandle() {
        var url = this.$http.adornUrl(`/project/base/downloadTemplate?token=${this.$cookie.get('token')}`);
        window.open(url);
      },
      finaceAmountFormat(row, column, cellValue, index) {
          return getFinaceAmount(cellValue);
        },

      showReceivers(receiveId) {
          this.receiverVisible = true
          this.$nextTick(() => {
            this.visible = true
            this.$refs.receiverDetailModal.init(receiveId)
          })
        },
       showDonator (donatorId) {
          this.donatorVisible = true
          this.$nextTick(() => {
            this.visible = true
            this.$refs.donatorDetailModal.init(donatorId)
          })
         }
    },
    mounted(){
      this.initTypeSelect()
    }
  }
</script>
