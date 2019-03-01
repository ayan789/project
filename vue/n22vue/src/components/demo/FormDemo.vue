<template>
  <div class="con scrolldiv">
    <uploader :options="options" class="uploader-example" >
    <uploader-unsupport></uploader-unsupport>
    <uploader-drop>
    <uploader-btn>上传文件</uploader-btn>
    <!-- <uploader-btn :attrs="attrs">select images</uploader-btn>
    <uploader-btn :directory="true">select folder</uploader-btn>-->
    </uploader-drop>
    <uploader-list></uploader-list>
    </uploader>

  </div>
</template>

<script type="text/ecmascript-6">
import $ from "jquery";
export default {
  name: 'formDemo',
  data () {
    return {
      msg: 'Hello formDemo',
      options: {
      // https://github.com/simple-uploader/Uploader/tree/develop/samples/Node.js
      target: "http://localhost:8080/Excel/reada3",
      // target:"//192.168.1.108:8080/reada",
      testChunks: false, //是否开启服务器分片校验
      chunkSize: '10485760', //分块大小1gb = 1073741824 10mb = 10485760
      },
      attrs: {
      accept: "image/*"
      }
    }
  },
  created() {
       console.log($);
  }
}
$(function() {
  console.log('jquery...');
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/main.scss";
.bit {
  width: 270px;
}
.scrolldiv{
    height: 100%;
    overflow-y: scroll;
}
</style>
