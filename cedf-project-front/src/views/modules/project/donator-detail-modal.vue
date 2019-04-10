<template>
  <el-dialog
    title="单位信息"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="donatorEntity" ref="dataForm" label-width="150px">
      <!-- 捐赠单位信息 -->
      <el-row>
        <el-col :span="24">
          <el-form-item label="捐赠单位名称">
            <el-input v-model="donatorEntity.companyName" :disabled="true"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="单位地址">
            <el-input v-model="donatorEntity.addressDetail" :disabled="true"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="法人">
            <el-input v-model="donatorEntity.legalPerson" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮编">
            <el-input v-model="donatorEntity.postalCode" :disabled="true"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="联系人">
            <el-input v-model="donatorEntity.relatePerson" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="电话">
            <el-input v-model="donatorEntity.companyTelephone" :disabled="true"></el-input>
          </el-form-item>
        </el-col>

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
        donatorEntity: {
          id:'',
          companyName:'',
          areaCode:'',
          addressDetail:'',
          legalPerson:'',
          relatePerson:'',
          postalCode:'',
          companyTelephone:'',
        },
      }
    },
    methods: {
      init (id) {
        this.donatorEntity.id = id || 0
        this.$http({
          url: this.$http.adornUrl(`/project/summary/donator/info/${this.donatorEntity.id}`),
          //`/sys/user/${!this.dataForm.id ? 'save' : 'update'}`
          method: 'get',
          params: this.$http.adornParams()
        }).then(({data}) => {
          this.visible = true;
          this.donatorEntity = data && data.code === 0 ? data.donatorEntity : {}
        })
      },
    }
  }
</script>
