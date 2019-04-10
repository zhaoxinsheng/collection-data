<!--
<template>
  <el-dialog
    title="单位信息"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form ref="dataForm" label-width="150px">
      &lt;!&ndash; 接收单位信息 &ndash;&gt;
      <el-row>
          <div v-for="(receiver,idx) in receiverEntity">
            <el-col :span="24">
              <el-form-item label="接收捐赠单位名称">
                <el-input v-model="receiver.receiveCompany" :disabled="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="单位地址">
                <el-input v-model="receiver.addressDetail" :disabled="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="联系人">
                <el-input v-model="receiver.relatePerson" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="电话">
                <el-input v-model="receiver.receiveTelephone" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
          </div>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">关闭</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        projectId:'',
        receiverEntity: [
          {
            receiveCompany:'',
            areaCode:'',
            addressDetail:'',
            legalPerson:'',
            relatePerson:'',
            receiveTelephone:'',
          },
        ],
      }
    },
    methods: {
      init (id) {
        this.projectId = id || 0
        this.$http({
          url: this.$http.adornUrl(`/project/summary/receiver/info/${this.projectId}`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.visible = true;
          this.receiverEntity = data && data.code === 0 ? data.list : []
        })
      },
    }
  }
</script>
-->
