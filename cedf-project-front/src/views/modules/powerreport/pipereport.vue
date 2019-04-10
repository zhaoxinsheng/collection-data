<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
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
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">

      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        width="100"
        visible="false"
        v-if="false"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="lxjck"
        header-align="center"
        align="center"
        label="离心机出口">
        <el-table-column
          prop="lxjljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="lxjsjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="fdjzyq"
        header-align="center"
        align="center"
        label="1#2#发电机杂用气">
        <el-table-column
          prop="fdjljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="fdisjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="ccdzyq"
        header-align="center"
        align="center"
        label="1#2#发电机布袋除尘用气">
        <el-table-column
          prop="ccdljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="ccdsjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="ybzyq"
        header-align="center"
        align="center"
        label="1#2#发电机仪表用气A">
        <el-table-column
          prop="ybljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="ybsjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="ybbzyq"
        header-align="center"
        align="center"
        label="1#2#发电机仪表用气B">
        <el-table-column
          prop="ybbljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="ybbsjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="shzyq"
        header-align="center"
        align="center"
        label="2#发电机除尘输灰用气">
        <el-table-column
          prop="shljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="shsjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="shbzyq"
        header-align="center"
        align="center"
        label="1#发电机除尘输灰用气">
        <el-table-column
          prop="shbljll"
          header-align="center"
          align="center"
          min-width="60"
          label="累计流量(m³)">
        </el-table-column>
        <el-table-column
          prop="shbsjll"
          header-align="center"
          align="center"
          min-width="60"
          label="瞬时流量(m³)">
        </el-table-column>
      </el-table-column>

      <el-table-column
        prop="zll"
        header-align="center"
        align="center"
        label="总流量(m³)">
      </el-table-column>
      <el-table-column
        prop="collTime"
        header-align="center"
        align="center"
        label="时间">
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
  .chart-container{
    position: relative;
    padding:20px;
    width: 100%;
    height:300px;
  }
</style>

<script>
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

        dataForm: {
          equipName: '',
          donatorName: '',
          receiverName: '',
          projectType: '',
          startDate: '',
          endDate: '',
          value5:'',
          equipTime:'',
          airName:'',
          lxjljll:'50',
          lxjsjll:'30',
          fdjljll:'40',
          fdisjll:'15',
          ccdljll:'35',
          ccdsjll:'10',
          ybljll:'45',
          ybsjll:'20',
          ybbljll:'70',
          ybbsjll:'10',
          shljll:'82',
          shsjll:'12',
          shbljll:'43',
          shbsjll:'12',
          zll:'365',
          collTime:''
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
            sums[index] = '平均值 (kwh/(m³))';
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
            lxjljll:'60',
            lxjsjll:'10',
            fdjljll:'50',
            fdisjll:'10',
            ccdljll:'42',
            ccdsjll:'12',
            ybljll:'20',
            ybsjll:'3',
            ybbljll:'52',
            ybbsjll:'10',
            shljll:'95',
            shsjll:'10',
            shbljll:'60',
            shbsjll:'12',
            zll:'379',
            collTime:'2019-03-12 11:12:10'
          },
            {
              id:1,
              lxjljll:'50',
              lxjsjll:'12',
              fdjljll:'40',
              fdisjll:'11',
              ccdljll:'35',
              ccdsjll:'10',
              ybljll:'15',
              ybsjll:'2',
              ybbljll:'45',
              ybbsjll:'10',
              shljll:'85',
              shsjll:'11',
              shbljll:'52',
              shbsjll:'12',
              zll:'322',
              collTime:'2019-03-12 11:11:00'
            },
            {
              id:1,
              lxjljll:'40',
              lxjsjll:'10',
              fdjljll:'40',
              fdisjll:'30',
              ccdljll:'10',
              ccdsjll:'10',
              ybljll:'10',
              ybsjll:'4',
              ybbljll:'42',
              ybbsjll:'10',
              shljll:'72',
              shsjll:'12',
              shbljll:'40',
              shbsjll:'10',
              zll:'264',
              collTime:'2019-03-12 11:10:40'
            } ];
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
