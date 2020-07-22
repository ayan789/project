<template>
  <div class="ocr-container">
    <globalNav title="车辆证件扫描" @clickBack="clickBack" />
    <div class="ocrimage" @click="scanImage">
      <img src="@/assets/vehicle/businessPlan/idCard_front.png" />
    </div>
    <div class="scan" @click="scanImage">行驶证扫一扫</div>
    <div class="jump" @click="jump">跳过</div>
    <div class="last" v-html="tips"></div>
  </div>
</template>

<script>
//  补录验证码页面
import globalNav from "@/components/globalNav.vue";
export default {
  name: "orderVehicleOCR",
  components: {
    globalNav
  },
  data() {
    return {
      tips:
        "<span>说明：</span><br/> 1.仅支持家用车进行投保。<br/>2.仅支持投被保人与车主为同一人且为自然人；",
      imageData: require("@/assets/vehicle/businessPlan/idCard_front.png")
    };
  },
  methods: {
    scanImage: function() {
      const jsonObject = {
        identifyType: "0", //"0" - 识别行驶证; "1" - 识别身份证
        side: "face" //"face"：正面；"back": 反面;
      };
      /**
       *姓名                  name                String
       *姓名位置图片          namePoints          Base64
       *号牌号码              cardNo              String
       *号牌号码位置图片      cardNoPoints        Base64
       *住址                  address             String
       *住址位置图片          addressPoints       Base64
       *发证日期              issueDate           String
       *发证日期位置图片      issueDatePoints     Base64
       *车辆类型              vehicleType         String
       *车辆类型位置图片      vehicleTypePoints   Base64
       *使用性质              useCharacte         String
       *使用性质位置图片      useCharactePoints   Base64
       *品牌型号              model               String
       *品牌型号位置图片      modelPoints         Base64
       *车辆识别代号          vin                 String
       *车辆识别代号位置图片  vinPoints           Base64
       *车辆识别代号          enginePN            String
       *车辆识别代号位置图片  enginePNPoints      Base64
       *注册日期              registerDate        String
       *注册日期位置图片      registerDatePoints  Base64
       *正反面                side                String
       */
      this.$native.identifyOCR(jsonObject, data => {
        data = JSON.parse(data);
        if (!this.$isNull(data.responseBody)) {
          let stringParams = {};
          // 车主姓名
          stringParams.carOwner = data.responseBody.name
            ? data.responseBody.name
            : "";
          stringParams.carOwnerImage = data.responseBody.namePoints
            ? "data:image/png;base64," + data.responseBody.namePoints
            : "";
          // 车牌号
          stringParams.licenseNo = data.responseBody.cardNo
            ? data.responseBody.cardNo
            : "";
          stringParams.licenseNoImage = data.responseBody.cardNoPoints
            ? "data:image/png;base64," + data.responseBody.cardNoPoints
            : "";
          //车架号
          stringParams.frameNo = data.responseBody.vin
            ? data.responseBody.vin
            : "";
          stringParams.frameNoImage = data.responseBody.vinPoints
            ? "data:image/png;base64," + data.responseBody.vinPoints
            : "";
          //初登日期
          stringParams.enrollDate = data.responseBody.registerDate
            ? data.responseBody.registerDate + " 00:00:00"
            : "";
          stringParams.enrollDateImage = data.responseBody.registerDatePoints
            ? "data:image/png;base64," + data.responseBody.registerDatePoints
            : "";
          // 发动机号
          stringParams.engineNo = data.responseBody.enginePN
            ? data.responseBody.enginePN
            : "";
          stringParams.engineNoImage = data.responseBody.enginePNPoints
            ? "data:image/png;base64," + data.responseBody.enginePNPoints
            : "";
          //厂牌型号
          stringParams.vehicleBrand = data.responseBody.model
            ? data.responseBody.model.replace("牌", "")
            : "";
          stringParams.vehicleBrandImage = data.responseBody.modelPoints
            ? "data:image/png;base64," + data.responseBody.modelPoints
            : "";
          stringParams = JSON.stringify(stringParams);
          this.$router.push({
            name: "carOCR",
            params: { stringParams: stringParams }
          });
        }
      });
    },
    clickBack() {
      this.$globalBack();
    },
    jump: function() {
      this.$router.push("/vehicle");
    }
  }
};
</script>
<style lang="less" scoped>
.ocr-container {
  background-color: #fff;
  .ocrimage {
    width: 100%;
    padding-top: 50px;
  }
  .ocrimage > img {
    display: block;
    margin: 50px auto;
    width: calc(100% - 66px);
  }
  .scan {
    margin: 0 auto;
    width: 265px;
    height: 46px;
    font-size: 16px;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 46px;
    background: rgba(59, 138, 254, 1);
    border-radius: 23px;
    text-align: center;
  }
  .jump {
    width: 100px;
    height: 40px;
    margin: 0 auto;
    margin-top: 30px;
    font-size: 14px;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
    line-height: 40px;
    text-align: center;
  }
  .last {
    border-radius: 5px;
    margin: 47px 20px 20px 20px;
    padding: 15px;
    background: #f7f7f7;
    font-size: 14px;
    font-weight: 400;
    color: rgba(102, 102, 102, 1);
    line-height: 1.5;
    text-align: left;
  }
}
</style>
