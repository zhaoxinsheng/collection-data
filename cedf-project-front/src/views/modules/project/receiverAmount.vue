<template>
  <div class="mod-user">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item label="协议号：">
        <el-input v-model="dataForm.projectNo" placeholder="协议号" clearable></el-input>
      </el-form-item>
      <el-form-item label="接受单位名称：">
        <el-input v-model="dataForm.receiveCompany" placeholder="接受单位名称" clearable></el-input>
      </el-form-item>
      <el-form-item label="支出日期：">
        <el-date-picker type="date" v-model="dataForm.startDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" :picker-options="pickerValidateStart"/>
      </el-form-item>
      <el-form-item label="至">
        <el-date-picker type="date" v-model="dataForm.endDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd"   :picker-options="pickerValidateEnd"/>
      </el-form-item>
      <br/>
      <el-form-item label="支出类型：">
        <el-select v-model="dataForm.amountType"  placeholder="请选择" clearable>
            <el-option label="现金" value="0"></el-option>
            <el-option label="物资" value="1"></el-option>
         </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('project:receiver:save')" type="primary" @click="addOrUpdateHandle()">添加支出信息</el-button>
        <el-button  v-if="isAuth('project:receiver:import')"  type="primary" @click="importHandle()">批量导入支出信息</el-button><!--v-if="isAuth('project:receiver:import')"-->
        <el-button   v-if="isAuth('project:receiver:downloadTemplate')"  type="primary" @click="downloadTemplateHandle()">下载导入模板</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList" border v-loading="dataListLoading" @selection-change="selectionChangeHandle" style="width: 100%;">
        <el-table-column prop="projectNo" header-align="center" align="center" label="协议号">
          <template slot-scope="scope">
            <router-link  :to="{path:'/project-summaryStatistics',query: {projectNo:scope.row.projectNo}}"  class="link-des">{{scope.row.projectNo}}</router-link>
          </template>
        </el-table-column>
        <el-table-column prop="projectName" show-overflow-tooltip header-align="center" align="center" label="项目名称"/>
        <el-table-column prop="cashAmount" header-align="center" align="center" label="支出金额"
        width="150"
        :formatter="finaceAmountFormat"/>
        <el-table-column prop="amountType" header-align="center" align="center" label="支出类型">
            <template slot-scope="scope">
                <span v-if="scope.row.amountType === '0'">现金</span>
                <span v-else>物资</span>
            </template>
        </el-table-column>
        <el-table-column prop="receiveCompany" header-align="center" show-overflow-tooltip align="center" label="接受捐赠单位名称">
            <template slot-scope="scope">
                <span v-if="!scope.row.secondUnit">{{scope.row.receiveCompany}}</span>
                <span v-else>{{scope.row.secondUnit}}</span>
            </template>
        </el-table-column>
        <el-table-column prop="relatePerson" header-align="center" align="center" label="联系人"/>
        <el-table-column prop="receiveTelephone" header-align="center" align="center" label="电话"/>
        <el-table-column prop="receiveDate" header-align="center" align="center" label="支出日期"/>
        <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('project:receiver:update')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">编辑</el-button>
          <el-button v-if="isAuth('project:receiver:delete')" type="text" size="small" class="el-button--text-gray" @click="deleteHandle(scope.row.id)">删除</el-button>
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

<script>
  import AddOrUpdate from './receiver-amount-add-or-update'
  import { getFinaceAmount } from '@/utils'
  import { validateDate } from '@/utils/validate'
  import Upload from './receiver-file-upload'
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
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false,
        uploadVisible: false,
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


      // 获取数据列表
      getDataList () {
        if (this.isRouteQuery && this.$route.query.projectNo) {
          this.dataForm.projectNo = this.$route.query.projectNo
        }

        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/project/receiverAmount/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'projectId': this.dataForm.projectId,
            'projectNo': this.dataForm.projectNo,
            'projectName': this.dataForm.projectName,
            'receiveCompany': this.dataForm.receiveCompany,
            'amountType': this.dataForm.amountType,
            'startDate': this.dataForm.startDate,
            'startDate': this.dataForm.startDate,
            'endDate': this.dataForm.endDate
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.list.list
            this.totalPage = data.list.totalCount
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
            url: this.$http.adornUrl('/project/receiverAmount/delete/' + id),
            method: 'post'
          }).then(({data}) => {
            if (data && data.code === 0) {
            this.getDataList();
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      },
      importHandle () {
        this.uploadVisible = true;
        this.$nextTick(() => {
          this.$refs.upload.init()
        })
      },
      finaceAmountFormat(row, column, cellValue, index) {
        return getFinaceAmount(cellValue);
      },
      downloadTemplateHandle() {
        var url = this.$http.adornUrl(`/project/receiver/downloadTemplate?token=${this.$cookie.get('token')}`);
        window.open(url);
      },
    }
  }
</script>
