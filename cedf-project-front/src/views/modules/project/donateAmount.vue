<template>
  <div class="mod-user">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="协议号：" >
        <el-input v-model="dataForm.contractNo" placeholder="协议号" clearable></el-input>
      </el-form-item>
      <el-form-item label="汇款单位名称：">
        <el-input v-model="dataForm.donator" placeholder="汇款单位名称" clearable></el-input>
      </el-form-item>
      <el-form-item label="到账日期：">
      <el-col :span="11">
          <el-date-picker type="date" placeholder="到账开始日期" v-model="dataForm.startDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateStart"></el-date-picker>
      </el-col>
      <el-col class="line" :span="2">至</el-col>
      <el-col :span="11">
          <el-date-picker type="date" placeholder="到账结束日期" v-model="dataForm.endDate" style="width: 100%;" value-format="yyyy-MM-dd"
          format="yyyy-MM-dd" :picker-options="pickerValidateEnd"></el-date-picker>
      </el-col>
     </el-form-item>
     <el-form-item label="到账类型：">
       <el-select v-model="dataForm.amountType"  placeholder="请选择" clearable>
           <el-option label="现金" value="0"></el-option>
           <el-option label="物资" value="1"></el-option>
        </el-select>
     </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="getDataList()">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="isAuth('project:donateamount:save')" type="primary" @click="addOrUpdateHandle()">添加到账信息</el-button>
        <el-button v-if="isAuth('project:donateamount:import')" type="primary" @click="importHandle()">批量导入到账信息</el-button>
        <el-button  v-if="isAuth('project:donateamount:downloadTemplate')" type="primary" @click="downloadTemplateHandle()">下载导入模板</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        prop="contractNo"
        header-align="center"
        show-overflow-tooltip
        align="center"
        width="150"
        label="协议号">
         <template slot-scope="scope">
            <router-link  :to="{path:'/project-summaryStatistics',query: {projectNo:scope.row.contractNo}}"  class="link-des">{{scope.row.contractNo}}</router-link>
          </template>
      </el-table-column>
      <el-table-column
        prop="projectName"
        header-align="center"
        show-overflow-tooltip
        align="center"
        label="项目名称">
      </el-table-column>
      <el-table-column
        prop="cashAmount"
        header-align="center"
        align="center"
        label="到账金额"
        width="150"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="amountType"
        header-align="center"
        align="center"
        label="到账类型">
        <template slot-scope="scope">
            <span v-if="scope.row.amountType === 0">现金</span>
            <span v-else>物资</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="donator"
        header-align="center"
        align="center"
        show-overflow-tooltip
        label="汇款单位名称">
      </el-table-column>
      <el-table-column
        prop="donateDate"
        header-align="center"
        align="center"
        label="到账日期">
      </el-table-column>
      <el-table-column
        prop="deliveryDate"
        header-align="center"
        align="center"
        width="180"
        label="收据邮寄日期">
      </el-table-column>
      <el-table-column
        prop="deliveryNo"
        header-align="center"
        align="center"
        width="180"
        label="快递编号">
      </el-table-column>
      <el-table-column
        prop="remark"
        header-align="center"
        align="center"
        show-overflow-tooltip
        width="180"
        label="备注">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('project:donateamount:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">编辑</el-button>
          <el-button v-if="isAuth('project:donateamount:delete')" type="text" size="small" class="el-button--text-gray" @click="deleteHandle(scope.row.id)">删除</el-button>
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
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
    <!-- 上传文件-->
    <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
  </div>
</template>

<script>
  import AddOrUpdate from './donateAmount-add-or-update'
  import Upload from './donateAmount-file-upload'
  import { getFinaceAmount } from '@/utils'
  import { validateDate } from '@/utils/validate'
  export default {
    data () {
      return {
        dataForm: {
          contractNo: '',
          donator: '',
          startDate: '',
          endDate: '',
          amountType:'',
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        uploadVisible: false,
        addOrUpdateVisible: false,
        isRouteQuery: this.$route.query,
        pickerValidateStart: this.beginDate(),
        pickerValidateEnd: this.processDate()
      }
    },
    components: {
      AddOrUpdate,
      Upload
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

      importHandle () {
        this.uploadVisible = true;
        this.$nextTick(() => {
          this.$refs.upload.init()
        })
      },

      downloadTemplateHandle() {
        var url = this.$http.adornUrl(`/project/donateamount/downloadTemplate?token=${this.$cookie.get('token')}`);
        window.open(url);
      },

      // 获取数据列表
      getDataList () {
        if (this.isRouteQuery && this.$route.query.projectNo) {
          this.dataForm.contractNo = this.$route.query.projectNo
        }
        if (this.$route.query.amountType!=null) {
          this.dataForm.amountType = this.$route.query.amountType+'';
        }
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/project/donateamount/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'contractNo': this.dataForm.contractNo,
            'donator': this.dataForm.donator,
            'startDate': this.dataForm.startDate,
            'endDate': this.dataForm.endDate,
            'amountType':this.dataForm.amountType,
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
          this.isRouteQuery = false
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
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },

      // 删除
      deleteHandle (id) {
        var userIds = id ? [id] : this.dataListSelections.map(item => {
          return item.userId
        })
        this.$confirm(`确定要删除此条数据吗？删除后将把相关数据一并删除。`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/project/donateamount/delete'),
            method: 'post',
            data: this.$http.adornData(userIds, false)
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
      finaceAmountFormat(row, column, cellValue, index) {
        return getFinaceAmount(cellValue);
      }
    }
  }
</script>
