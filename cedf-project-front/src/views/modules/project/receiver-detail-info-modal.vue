<!--
<template>
  <el-dialog
    title="单位信息"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form ref="dataForm" label-width="150px">
      &lt;!&ndash; 接收单位信息 &ndash;&gt;
      <el-row v-for="(receiverEntity,idx) in receiverEntitys">
          <div >
            <el-col :span="24">
              <el-form-item label="接收捐赠单位名称">
                <el-input v-model="receiverEntity.receiveCompany" :disabled="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="单位地址">
                <el-input v-model="receiverEntity.addressDetail" :disabled="true"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="24">
              <el-form-item label="联系人">
                <el-input v-model="receiverEntity.relatePerson" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="电话">
                <el-input v-model="receiverEntity.receiveTelephone" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
          </div>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        receiveId:'',
        receiverEntitys: [
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
        this.receiveId = id || 0
        this.$http({
          url: this.$http.adornUrl(`/project/receiver/infoList/${this.receiveId}`),
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.visible = true;
          this.receiverEntitys = data && data.code === 0 ? data.receivers : ""
        })
      },
    }
  }
</script>
-->
