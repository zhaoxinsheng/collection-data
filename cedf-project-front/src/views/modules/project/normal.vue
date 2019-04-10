initTypeSelect<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="捐赠人">
        <el-input v-model="dataForm.donatorName" placeholder="捐赠人" clearable></el-input>
      </el-form-item>
       <el-form-item label="到账日期">
      <el-col :span="11">
        <el-date-picker type="date" placeholder="开始日期" v-model="dataForm.startDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
                        :picker-options="pickerValidateStart"></el-date-picker>
      </el-col>
      <el-col class="line" :span="2">至</el-col>
      <el-col :span="11">
        <el-date-picker type="date" placeholder="结束日期" v-model="dataForm.endDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
                        :picker-options="pickerValidateEnd" ></el-date-picker>
      </el-col>
    </el-form-item>
      <el-form-item label="支出报告日期">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="开始日期" v-model="dataForm.withStartDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
                          :picker-options="pickerValidateWithStart"></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">至</el-col>
        <el-col :span="11">
          <el-date-picker type="date" placeholder="结束日期" v-model="dataForm.withendDate" style="width: 100%;" value-format="yyyy-MM-dd" format="yyyy-MM-dd"
                          :picker-options="pickerValidateWithEnd" ></el-date-picker>
        </el-col>
      </el-form-item>
       <el-form-item>
        <el-button @click="getDataList()" type="warning">查询</el-button>
        <el-button v-if="isAuth('project:normal:save')" type="primary" @click="addOrUpdateHandle()">添加项目</el-button>
        <el-button v-if="isAuth('project:normal:import')" type="primary" @click="importHandle()">批量导入项目</el-button>
        <el-button  v-if="isAuth('project:normal:export')"  type="primary" @click="downloadTemplateHandle()">下载导入模板</el-button>
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
        visible="false"
        v-if="false"
        label="ID">
      </el-table-column>
      <el-table-column
        prop="donatorName"
        header-align="center"
        align="center"
        label="捐赠人">
      </el-table-column>
      <el-table-column
        prop="cashAmount"
        header-align="center"
        align="center"
        label="到账金额"
        width="180"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="donateDate"
        header-align="center"
        align="center"
        label="到账日期">
      </el-table-column>
      <el-table-column
        prop="withAmount"
        header-align="center"
        align="center"
        label="支出金额"
        width="180"
        :formatter="finaceAmountFormat">
      </el-table-column>
      <el-table-column
        prop="withDate"
        header-align="center"
        align="center"
        label="支出报告日期">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('project:normal:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">编辑</el-button>
          <el-button v-if="isAuth('project:normal:delete')" type="text" size="small" @click="deleteHandle(scope.row.id)" class="el-button--text-gray">删除</el-button>
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
    <upload v-if="uploadVisible" ref="upload" @refreshDataList="getDataList"></upload>
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
</style>

<script>
  import AddOrUpdate from './project-normal-add-or-update'
  import Upload from './project-normal-file-upload'
  import { validateDate } from '@/utils/validate'
  import { getFinaceAmount } from '@/utils'
  export default {
    data () {
      return {
        dataForm: {
          donatorName: '',
          startDate: '',
          endDate: '',
          withStartDate: '',
          withendDate: ''
        },
        projectTypes: [],
        dataList: [],
        pageIndex: 1,
        pageSize: 20,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        uploadVisible: false,
        //弹框显示变量
        donatorVisible: false,
        receiverVisible:false,
        pickerValidateStart: this.beginDate(),
        pickerValidateEnd: this.processDate(),
        pickerValidateWithStart: this.beginWithDate(),
        pickerValidateWithEnd: this.processWithDate()
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
      beginWithDate(){
        let self = this;
        return {
          disabledDate(time){
            if (time != null && time !='' && self.dataForm.withendDate != null && self.dataForm.withendDate !='') {
              if(validateDate(time, new Date(self.dataForm.withendDate))) {
                return true;
              }
            }
          }
        }
      },

      processWithDate(){
        let self = this;
        return {
          disabledDate(time){
            if (time != null && time !='' &&  self.dataForm.withStartDate != null && self.dataForm.withStartDate !='') {
              if ((validateDate(new Date(self.dataForm.withStartDate),time))) {
                return true;
              }
            }
          }
        }
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true

        this.$http({
          url: this.$http.adornUrl('/project/normal/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'donatorName': this.dataForm.donatorName,
            'startDate': this.dataForm.startDate,
            'endDate': this.dataForm.endDate,
            'withStartDate': this.dataForm.withStartDate,
            'withendDate': this.dataForm.withendDate,
            'sidx': 'id',
            'order': 'DESC'
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
            url: this.$http.adornUrl('/project/normal/delete'),
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
      // 上传文件
      importHandle () {
        this.uploadVisible = true;
        this.$nextTick(() => {
          this.$refs.upload.init()
        })
      },

      downloadTemplateHandle() {
        var url = this.$http.adornUrl(`/project/normal/downloadTemplate?token=${this.$cookie.get('token')}`);
        window.open(url);
      },
      finaceAmountFormat(row, column, cellValue, index) {
          return getFinaceAmount(cellValue);
        }
    },
    mounted(){
      // this.initTypeSelect()
    }
  }
</script>
