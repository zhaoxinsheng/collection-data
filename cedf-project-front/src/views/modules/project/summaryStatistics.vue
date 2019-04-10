<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="协议号">
        <el-input v-model="dataForm.projectNo" placeholder="协议号" clearable></el-input>
      </el-form-item>
      <el-form-item label="捐赠单位名称">
         <el-input v-model="dataForm.donatorName" placeholder="捐赠单位名称" clearable></el-input>
      </el-form-item>
      <el-form-item label="接受捐赠单位名称">
         <el-input v-model="dataForm.receiverName" placeholder="接受捐赠单位名称" clearable></el-input>
      </el-form-item>
      <el-form-item label="项目类型">
         <el-select v-model="dataForm.projectType"  placeholder="请选择" clearable >
            <el-option v-for="item in projectTypes" :key="item.id" :label="item.name" :value="item.id">  </el-option>
         </el-select>
      </el-form-item>
       <el-form-item label="协议日期">
         <el-col :span="11">
             <el-date-picker type="date" placeholder="开始日期" v-model="dataForm.startDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateStart"></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">至</el-col>
             <el-col :span="11">
                <el-date-picker type="date" placeholder="结束日期" v-model="dataForm.endDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"  :picker-options="pickerValidateEnd"></el-date-picker>
             </el-col>
       </el-form-item>
       <el-form-item>
        <el-button @click="getDataList()" type="warning">查询</el-button>
        <el-button v-if="isAuth('project:summary:export')" type="primary" @click="exportHandle()">导出项目</el-button>
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
        width="80"
        v-if="false"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="projectNo"
        header-align="center"
        width="150"
        align="center"
        label="协议号">
      </el-table-column>
      <el-table-column
        prop="projectName"
        header-align="center"
        show-overflow-tooltip
         width="200"
        align="center"
        label="项目名称">
      </el-table-column>
      <el-table-column
        prop="donatorName"
        header-align="center"
        align="center"
        width="180"
        show-overflow-tooltip
        label="捐赠单位名称">
        <template slot-scope="scope">
          <el-button type="text" class="link-des" @click="showDonator(scope.row.donatorId)">{{scope.row.donatorName}}</el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="projectContent"
        header-align="center"
        align="center"
        show-overflow-tooltip
        width="180"
        label="协议捐赠内容">
      </el-table-column>
      <el-table-column
        prop="cashAmount"
        header-align="center"
        align="center"
        width="180"
        label="协议捐赠金额"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="inMeterialAmount"
        header-align="center"
        align="center"
        width="180"
        label="实物捐赠金额"
        :formatter="finaceAmountFormat">
        <template slot-scope="scope">
          <router-link  :to="{path:'/project-donateAmount',query: {projectNo:scope.row.projectNo,amountType:1}}"  class="link-des">{{scope.row.inMeterialAmount}}</router-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="inCashAmount"
        header-align="center"
        align="center"
        width="180"
        label="现金到账金额"
        :formatter="finaceAmountFormat">
        <template slot-scope="scope">
          <router-link  :to="{path:'/project-donateAmount',query: {projectNo:scope.row.projectNo,amountType:0}}"  class="link-des">{{scope.row.inCashAmount}}</router-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="totalOutAmount"
        header-align="center"
        align="center"
        width="180"
        label="支出金额"
        :formatter="finaceAmountFormat">
        <template slot-scope="scope">
          <router-link  :to="{path:'/project-receiverAmount',query: {projectNo:scope.row.projectNo}}"  class="link-des">{{scope.row.totalOutAmount}}</router-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="balance"
        header-align="center"
        align="center"
        width="180"
        label="余额"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="receiverName"
        header-align="center"
        align="center"
         width="250"
        show-overflow-tooltip
        label="接受捐赠单位名称">
        <template slot-scope="scope">
          <router-link to="#" @click.native="showReceivers(scope.row.id)" :title="scope.row.receiverName">{{scope.row.receiverName}}</router-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="typeName"
        header-align="center"
        align="center"
          width="150"
        label="项目类型">
      </el-table-column>
      <el-table-column
        prop="dateTime"
        header-align="center"
        align="center"
         width="150"
        label="协议日期">
      </el-table-column>
      <el-table-column
        prop="assistantNo"
        header-align="center"
        align="center"
         width="150"
        label="助教号">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('project:base:delete')" type="text" size="small" class="el-button--text-gray" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
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
    <!-- 弹窗 -->
    <donator-detail-modal v-if="donatorVisible" ref="donatorDetailModal"></donator-detail-modal>
    <receiver-detail-modal v-if="receiverVisible" ref="receiverDetailModal"></receiver-detail-modal>
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

  .el-button--text {
    color: #888888;
    padding-right: 0;
  }

  .el-button--hover {
    color:  #888888;
  }
  a.color1:link{
    color: #888888 ;
  }

</style>

<script>
  import DonatorDetailModal from './donator-detail-modal'
  import ReceiverDetailModal from './receiver-detail-modal'
  import { getFinaceAmount } from '@/utils'
  import { validateDate } from '@/utils/validate'
  export default {
    data () {
      return {
        dataForm: {
          projectNo: '',
          donatorName: '',
          receiverName: '',
          projectType: '',
          startDate: '',
          endDate: ''
        },
        projectTypes: [],
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        //弹框显示变量
        donatorVisible: false,
        receiverVisible:false,
        pickerValidateStart: this.beginDate(),
        pickerValidateEnd: this.processDate()
      }
    },
    components: {
      DonatorDetailModal,
      ReceiverDetailModal
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

      initTypeSelect () {
          this.$http({
            url: this.$http.adornUrl('/project/base/typeSelect'),
            method: 'get',
            params: this.$http.adornParams()
        }).then(({data}) => {
            if ( data.code === 0) {
              this.projectTypes = data.list;
              this.projectTypes.push({id: '99',name: '空'});
            }else {
              this.projectTypes = [];
            }
        })
      },
      // 获取数据列表
      getDataList () {
        if (this.$route.query && this.$route.query.projectNo) {
          this.dataForm.projectNo = this.$route.query.projectNo
        }
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/project/summary/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'projectNo': this.dataForm.projectNo,
            'donatorName': this.dataForm.donatorName,
            'receiverName': this.dataForm.receiverName,
            'projectType': this.dataForm.projectType,
            'startDate': this.dataForm.startDate,
            'endDate': this.dataForm.endDate
          })
        }).then(({data}) => {
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
      showDonator (donatorId) {
        this.donatorVisible = true
        this.$nextTick(() => {
          this.visible = true
          this.$refs.donatorDetailModal.init(donatorId)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定要删除此条数据吗？删除后将把相关数据一并删除。`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/project/summary/delete'),
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
      exportHandle() {
        var url = this.$http.adornUrl(`/project/summary/export?token=${this.$cookie.get('token')}`);
        url = url + "&projectNo=" + this.dataForm.projectNo;
        url = url + "&donatorName=" + this.dataForm.donatorName;
        url = url + "&receiverName=" + this.dataForm.receiverName;
        url = url + "&projectType=" + this.dataForm.projectType;
        url = url + "&startDate=" + this.dataForm.startDate;
        url = url + "&endDate=" + this.dataForm.endDate;
        window.open(url);
      },
      showReceivers(projectId) {
        this.receiverVisible = true
        this.$nextTick(() => {
          this.visible = true
          this.$refs.receiverDetailModal.init(projectId)
        })
      },
      finaceAmountFormat(row, column, cellValue, index) {
        return getFinaceAmount(cellValue);
      }
    },
    mounted(){
      this.initTypeSelect()
    }
  }
</script>
