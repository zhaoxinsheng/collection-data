<template>
  <el-dialog
    title="上传文件"
    :close-on-click-modal="false"
    @close="closeHandle"
    :visible.sync="visible">
    <el-upload
      class="upload-demo"
      drag
      :action="url"
      :before-upload="beforeUploadHandle"
      :on-success="successHandle"
      :show-file-list="false"
      style="text-align: center;"
      v-loading="loading"
      element-loading-text="文件上传中"
      >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只支持excel！</div>
    </el-upload>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        url: '',
        num: 0,
        successNum: 0,
        fileList: [],
        loading: false
      }
    },
    methods: {
      init (id) {
        this.url = this.$http.adornUrl(`/project/base/import?token=${this.$cookie.get('token')}`)
        this.visible = true
      },
      uploadAction() {
        return this.$http.adornUrl(`/project/base/import?token=${this.$cookie.get('token')}`);
      },
      // 上传之前
      beforeUploadHandle (file) {
        if (file.type !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' && file.type !== 'application/vnd.ms-excel') {
          this.$message.error('只支持excel格式！')
          return false
        }
        this.num++
        this.loading = true;
      },
      // 上传成功
      successHandle (response, file, fileList) {
         this.loading = false;
          if(response.code===0){
              this.$message({
                  message: '上传成功！',
                  type: 'success'
              });
              this.visible = false;
          }else {
              const h = this.$createElement;
              let resDatas = response.msg.split('\n');
              let newDatas = [];
            //3.通过循环data数组，调用h方法，将每项值传给h,h('标签名',样式,具体内容)
              for(let i in resDatas){
            //4.将data数据push进newDatas数组中
                newDatas.push(h('p',{
                  domProps: {
                    innerHTML: resDatas[i]
                  }
                },resDatas[i]));
              };
              this.$message({
                  message: h('div', null,  newDatas),
                  type: 'error',
                  duration: 20000,
                  showClose: true
              });
          }

      },
      // 弹窗关闭时
      closeHandle () {

        this.loading = false;
        this.fileList = []
        this.$emit('refreshDataList')
      }
    }
  }
</script>
