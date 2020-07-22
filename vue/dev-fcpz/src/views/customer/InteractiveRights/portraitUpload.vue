<template>
  <div id="app">
    <globalNav title="影像上传" @clickBack="clickBack" />
    <div class="top">请选择影像</div>
    <ul class="cates">
      <li
        class="idCard"
        :style="chooseType == 0 ? selectStyle : unSelectStyle"
        @click="selectType()"
      >
        身份证bbcc
      </li>
      <li
        class="vehicalCard"
        :style="chooseType == 1 ? selectStyle : unSelectStyle"
      >
        行驶证
      </li>
      <li
        class="other"
        :style="chooseType == 2 ? selectStyle : unSelectStyle"
      >
        其他a
      </li>
    </ul>
    <ul class="imgList" v-for="(item, index) in imageDatas" v-bind:key="index">
      <div class="photo" v-if="item != 'last'">
        <img :src="item" />
      </div>
      <div class="takephoto" v-if="item == 'last'" @click="chooseImage">
        <img src="@/assets/customer/InteractiveRights/photo.png" />
      </div>
    </ul>
  </div>
</template>

<script>
//  影像上传页面
import globalNav from "@/components/globalNav.vue";
import { uploadImages, queryImages } from "@src/http/module/customerApi.js";
import { mapState } from "vuex";
export default {
  name: "portraitUpload",
  computed: {
    ...mapState(["customerInfoData"]),
  },
  props: {
    userCode: String,
    custNo: String
  },
  components: {
    globalNav
  },
  data() {
    return {
      //数据源：
      responseDatas: [],
      imageDatas: [
        // "last"
      ],
      chooseType: 0,
      selectStyle: {
        background: "#3b8afe",
        color: "#fff"
      },
      unSelectStyle: {
        background: "rgba(247, 247, 247, 1)",
        color: "#333333"
      }
    };
  },
  created() {
    this.requestData();
    this.imageUpload();
  },

  methods: {
    async imageUpload(){
      await this.$store.dispatch(
        "customerInfoData/imageUpload",
        {
          "comCode":"000000",
          "bussNo":"80291534157_3411124",
          "busiDate":"20180100",
          "imgType":"CJXS"
        }
      );
      let imageUpload = this.customerInfoData.imageUpload
      console.info(imageUpload,"打开的");
      if( imageUpload && imageUpload.url){
        window.location.href=imageUpload.url;
      }
    },
    clickBack() {
      this.$router.go(-1); //返回上一层
    },
    chooseImage: function() {
      //console.log("点击了一下拍照");
      this.uploadImage("");
    },
    selectType: function(index) {
      let _this = this;
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.setAvatar("1", data => {
        // console.log(JSON.parse(data).responseBody ,'231231')
        if (JSON.parse(data).responseBody) {
          setTimeout(() => {
            _this.upload(JSON.parse(data).responseBody);
          }, 200);
        }
      });
    },
    async requestData() {
      let params = {
        userCode: this.userCode, //业务号
        custNo: this.custNo //客户号
      };
      let res = await queryImages(params);
      if (res != undefined && res.code == "1") {
        var response = JSON.parse(JSON.stringify(res.data));
        this.responseDatas = response;
      }
      this.getShowImages();
    },

    getShowImages() {
      this.imageDatas = [];
      var showImages = [];
      for (var i = 0; i < this.responseDatas.length; i++) {
        let item = this.responseDatas[i];
        if (item.type == this.chooseType) {
          showImages.push(item.imageUrl);
        }
      }
      showImages.push("last");
      this.imageDatas = showImages;
    },
    //上传图片：
    async uploadImage(base64) {
      console.log("aaaaaaaaaauploaduploaduploaduploadupload",base64);
      let params = {
        userCode: this.userCode, //业务号
        custNo: this.custNo, //客户号
        file: base64, //文件(base64或者其他)
        certificateType: this.chooseType //证件类型
      };
      let res = await uploadImages(params);
      if (res != undefined && res.code == "1") {
        //console.log("上传成功");
      } else {
        //console.log("上传失败");
      }
    }
  }
};
</script>

<style lang="less"  scoped>
* {
  margin: 0;
  padding: 0;
  list-style: none;
}
.top {
  height: 48px;
  margin-top: 44px;
  width: 100%;
  background-color: rgba(247, 247, 247, 1);
  font-size: 14px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 48px;
  text-align: left;
  text-indent: 1em;
}
li {
  line-height: 28px;
  font-size: 13px;
  font-weight: 400;
  color: #333333;
  width: 80px;
  height: 28px;
  margin: 16px 0px 16px 16px;
  border-radius: 4px;
}
.cates {
  /* background: yellow; */
  height: 60px;
  color: #fff;
}
.idCard,
.vehicalCard,
.other {
  float: left;
  text-align: center;
}

.takephoto,
.photo {
  background: #f7f7f7;
  height: 28.53%;
  width: 28.53%;
  margin: 3.6% 0 0 3.6%;
  display: block;
  float: left;
  text-align: center;
}
img {
  object-fit: fill;
  display: block;
  margin: 0 auto;
  height: 100%;
  width: 100%;
}
.takephoto > img {
  object-fit: none;
  background: #f7f7f7;
}
</style>
