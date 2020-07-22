
<template>
  <div
    class="prPlan vantabClass"
    id="prPlan"
    v-show="showPrPlan"
  >
    <prHeader
      :proHeader="proHeader"
      :isScroll="isScroll"
      :isSharePage="isSharePage"
      ref="prHeader"
      :headerImgLen="headerImgLen"
      @sharePage="sharePage"
    />

    <div class="prdouctInfo">
      <prInfo
        ref="prInfo"
        :isSharePage="isSharePage"
        :safeguardPlanList="safeguardPlanList"
        :commonProblem="commonProblem"
        :backReasoningFactor="backReasoningFactor"
        :fromRoute="fromRoute"
        @getIsNeedhealNotice="getIsNeedhealNotice"
      />
      <prBottom
        :title="title"
        :isSharePage="isSharePage"
        :proBottom="proBottom"
        @goProduct="goProduct"
        @doInsure="doInsure"
      />
    </div>
  </div>
</template>

<script>
import prHeader from "../autoConfig/common/prHeaderPlan"
import prInfo from "../autoConfig/common/prInfoPlan"
import prBottom from "../autoConfig/common/prBottomPlan"
import { getShareUrl } from "@src/http/module/customerApi"
import { getPlanElementDetail } from "@src/http/module/nonCar"

export default {
  components: {
    prHeader,
    prInfo,
    prBottom,
  },
  data () {
    return {
      showPrPlan: false,
      proHeader: {}, //头部信息
      isScroll: false,//滚动
      isSharePage: "",
      headerImgLen: 0, //轮播数量

      safeguardPlanList: [], //方案
      commonProblem: {},//常见问题
      backReasoningFactor: [],//方案切换因素
      fromRoute: {},//路由
      title: "",//头部标题
      proBottom: [],//底部信息

      current: 0, // 轮播索引
      isNeedHealthNotice: false, //健康告知
      healthNoticeObj: {},//健康告知对象
      sumPremium: 0,//保费
      riskCode: "",
      isAgent: "",
      agentInfo: {},
      businessNatureName: "",
      businessNatureCode: "",
      orderId: 0,
      strategyId: 0

    }
  },

  updated () {
    const topPos = this.$store.state.config.topPos
    if (topPos) {
      document.getElementById("prPlan").scrollTop = `${topPos}`
      window.scrollTop = `${topPos}`
      window.scrollTo(0, `${topPos}`)
      // 返回完成 置0
      this.$store.commit("config/setPos", 0)
    }
  },
  mounted () {

    document.getElementById("app").style.overflow = "visible"
    // 监听滚动
    this.$nextTick(() => {
      document.getElementById("prPlan").addEventListener("scroll", () => {
        this.scrollFunction()
      })
    })
    // this.strategyId = this.$GetRequest("strategyId", true);
    if (this.$route.query) {
      this.strategyId = JSON.parse(this.$route.query).strategyId
    }
    this.orderId = this.$GetRequest("orderId", true) ? this.$GetRequest("orderId", true) : 0;
    this.isSharePage = this.$GetRequest("isSharePage", true)
      ? this.$GetRequest("isSharePage", true)
      : "";

    this.$store.dispatch(
      "config/updateStrategyId",
      this.strategyId ? parseInt(this.strategyId) : 0
    );

    let obj = {
      strategyId: this.strategyId,
      code: "prospectus",
      // code: "compute",

      orderId: parseInt(this.orderId)
    };

    if (this.orderId == 0) {
      delete obj.orderId;
    }
    let request = this.getFormulaDetail(obj);
    request
      .then(res => {
        if (res.code == "1") {
          let {
            formulaList,//方案
            header,//头部信息
            bottom,//底部信息
            commonProblem,//常见问题
            backReasoningFactor,//方案切换因素
            backReasoning, //1
            defaultPlanId
          } = res.data;
          this.safeguardPlanList = formulaList;
          this.proHeader = header;
          this.headerImgLen = header.swipper.length;
          this.title = header.title;
          this.proBottom = bottom;
          if (this.isSharePage) {
            let index = this.proBottom.findIndex(item => {
              return item.pbBox.length > 0;
            });
            this.proBottom.splice(index, 1);
          }
          this.commonProblem = commonProblem;
          if (!this.$isNull(defaultPlanId) && defaultPlanId != -1) {
            this.$store.dispatch("config/updateProductInfo", {
              planId: defaultPlanId
            });
          }
          if (backReasoning == "1") {
            this.backReasoningFactor = backReasoningFactor;
          }
          this.showPrPlan = true;
        } else {
          this.$dialog.alert({ message: res.msg });
        }
      })
      .catch(err => {
        console.log(err);
      });


    // 是分享页面
    if (this.isSharePage) {
      this.strategyId = this.$GetRequest("strategyId", true);
      let user = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : {};
      let storeCode = this.$GetRequest("storeCode", true)
        ? this.$GetRequest("storeCode", true)
        : user.storeCode;
      this.isAgent = this.$GetRequest("isAgent", true)
        ? this.$GetRequest("isAgent", true)
        : "0";
      if (this.$GetRequest("agentInfo", true)) {
        this.agentInfo =
          JSON.parse(decodeURIComponent(this.$GetRequest("agentInfo", true))) ||
          this.agentInfo;
      }
      this.businessNatureCode = this.$GetRequest("businessNatureCode", true)
        ? this.$GetRequest("businessNatureCode", true)
        : "";
      this.businessNatureName = this.$GetRequest("businessNatureName", true)
        ? this.$GetRequest("businessNatureName", true)
        : "";
      let o = {
        storeCode,
        isAgent: this.isAgent,
        agentInfo: this.agentInfo,
        businessNatureCode: this.businessNatureCode,
        businessNatureName: this.businessNatureName
      };
      this.$store.dispatch("config/updateDetailData", o);
    }
  },
  destroyed () {
    document.getElementById("prPlan") &&
      document
        .getElementById("prPlan")
        .removeEventListener("scroll", this.scrollFunction)
    document.getElementById("app").style.overflow = ""
  },
  beforeRouteEnter (to, from, next) {
    console.log(205, from)
    next((vm) => {
      vm.fromRoute = from
    })
  },
  methods: {
    // 获取方案详情数据
    async getFormulaDetail (obj) {
      return await getPlanElementDetail(obj)
    },
    // 返回上一页
    onClickLeft () {
      // this.$globalBack()
      this.$router.push({
        name: "insuranceProducts"
      });
    },
    //轮播图变化事件
    onChange (index) {
      this.current = index
    },
    onPlayerPause (player) {
      //监听暂停
      //console.log(player.duration());
      this.$emit("onPlayerPauseFun", player)
    },
    // 滚动事件
    scrollFunction () {
      let scrTop = document.getElementById("prPlan").scrollTop
      if (scrTop > 100) {
        this.isScroll = true
      } else {
        this.isScroll = false
      }
    },
    // 分享整个页面
    async sharePage () {
      let {
        isAgent,
        agentInfo,
        businessNatureName,
        businessNatureCode
      } = this.$store.state.config.productInfo;
      this.isAgent = isAgent;
      this.agentInfo = agentInfo;
      this.businessNatureName = businessNatureName;
      this.businessNatureCode = businessNatureCode;
      // let res = await getShareUrl({
      //   url: window.location.href + "&isSharePage=1"
      // });
      // let shareUrl =
      //   res.data.shortUrl || window.location.href + "&isSharePage=1";
      // console.log("分享短连接===" + shareUrl);
      // const desc = `尊敬的 ${this.name}，您的保险专家 ${this.businessName} 为您精心准备的计划书，请查阅！`;
      // const title = this.millionInfo.formulaName;
      //       proCode: "FM50"
      // storeCode: "GWznde8FEh3Fq2LdxFCK"
      // userCode: "8000506130"
      let shareUrl =
        window.location.href +
        "&proCode=FM50&storeCode=" +
        this.storeCode +
        "&isAgent=" +
        this.isAgent +
        "&agentInfo=" +
        JSON.stringify(this.agentInfo) +
        "&businessNatureName=" +
        this.businessNatureName +
        "&businessNatureCode=" +
        this.businessNatureCode +
        "&userCode=" +
        localStorage.getItem("userCode") +
        "&isSharePage=1" +
        "&comCode=" +
        this.comCode;
      // 转换短链接
      let res = await getShareUrl({ url: shareUrl });
      console.log("分享链接：" + res.data.shortUrl);
      if (res.code == "1") {
        //调用接口参数 TODO
        const jsonObject = {
          sharePlatform: ["0", "1"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
          shareType: "url", //"url" - 链接； "img" - 图片； "text" - 文字
          title: "天地关爱百万医疗(2020个人版)", //分享标题
          imgURL:
            "https://msupsale-uat.ccic-net.com.cn/api/cst/s3/browse/logo.png", //分享图片地址, 与image二选一
          image: "",
          url: res.data.shortUrl, //分享链接
          desc: "住院、特殊门诊、交通多重保障", //分享描述
          phoneNum: "" //电话号码,当需要短信分享时获取
        };
        //调用壳方法，传递调用参数和接受响应数据
        this.$native.share(jsonObject, data => {
          // Toast("share 接口调用成功，壳响应数据:" + data);
        });
      }
    },
    // 按钮分享海报和制作计划书
    goProduct (code) {
      let {
        isAgent,
        agentInfo,
        businessNatureName,
        businessNatureCode
      } = this.$store.state.config.productInfo;
      this.isAgent = isAgent;
      this.agentInfo = agentInfo;
      this.businessNatureName = businessNatureName;
      this.businessNatureCode = businessNatureCode;
      if (code == "poster") {
        this.goProductPoster();
      } else {
        this.goMillionPlan();
      }
    },

    // 获取短链接
    async getShortLink (obj) {
      return await getShareUrl(obj);
    },

    // 去分享海报页面
    goProductPoster () {
      let { swipper, title, desc } = this.proHeader;
      let img = "";
      for (let len = swipper.length, i = 0; i < len; i++) {
        if (swipper[i].type == "promotion_image") {
          img = swipper[i].value;
          break;
        }
      }
      let user = localStorage.getItem("userInfo")
        ? JSON.parse(localStorage.getItem("userInfo"))
        : {};
      let tempUrl = "";
      this.$refs.prInfo.setChannelInfo();
      let {
        isAgent,
        agentInfo,
        businessNatureCode,
        businessNatureName
      } = this.$store.state.config.productInfo;
      let isSharePage = "1";
      let url = window.location.href;
      if (this.$isNull(isAgent) || isAgent == "0") {
        tempUrl =
          url +
          "&storeCode=" +
          user.storeCode +
          "&comCode=" +
          user.comCode +
          "&isAgent=" +
          isAgent +
          "&agentInfo=" +
          agentInfo +
          "&businessNatureCode=" +
          businessNatureCode +
          "&businessNatureName=" +
          businessNatureName +
          "&isSharePage=" +
          isSharePage;
      } else {
        tempUrl =
          url +
          "&storeCode=" +
          user.storeCode +
          "&comCode=" +
          user.comCode +
          "&isAgent=" +
          isAgent +
          "&agentInfo=" +
          agentInfo +
          "&businessNatureCode=" +
          businessNatureCode +
          "&businessNatureName=" +
          businessNatureName +
          "&isSharePage=" +
          isSharePage;
      }
      let shortUrl = "";
      this.getShortLink({ url: tempUrl })
        .then(res => {
          shortUrl = res.data.shortUrl;
        })
        .catch()
        .finally(() => {
          const jsonObject = {
            proImg: img || "",
            proTitle: title || "",
            proDesc: desc || "",
            name: user.userName || "",
            phone: user.mobile || "",
            qrcodeURL: shortUrl || tempUrl
          };
          // console.log(411, jsonObject);
          //调用壳方法，传递调用参数和接受响应数据
          this.$native.openNativePosterShare(jsonObject, data => {
            console.log(
              "openNativePosterShare接口调用成功，壳响应数据:" + data
            );
            // Toast("share 接口调用成功，壳响应数据:" + data);
          });
        });
    },

    // 去制作计划书
    goMillionPlan () {
      let params = {
        strategyId: this.$route.query.strategyId, //id
        isAgent: this.isAgent, //是否代理
        agentInfo: this.agentInfo, //代理信息
        businessNatureCode: this.businessNatureCode,
        businessNatureName: this.businessNatureName
      };
      this.$router.push({ name: "prPlan", query: JSON.stringify(params) });
    },

    // 是否需要健康须知
    getIsNeedhealNotice (isNeedHealthNotice, healthNoticeObj) {
      this.isNeedHealthNotice = isNeedHealthNotice;
      this.healthNoticeObj = healthNoticeObj;
    },

    // // 我要投保按钮操作
    // doInsure () {
    //   this.$refs.prInfo.setChannelInfo();
    //   let isAgent = this.$store.state.config.productInfo.isAgent;
    //   let agentInfo = this.$store.state.config.productInfo.agentInfo;
    //   this.$store.dispatch("config/updateDetailData", { title: this.title });
    //   this.$store.dispatch(
    //     "config/updateStrategyId",
    //     this.strategyId ? parseInt(this.strategyId) : 0
    //   );
    //   if (isAgent == "1") {
    //     if (this.$isNull(agentInfo.agentName)) {
    //       this.$dialog.alert({
    //         message: "代理人信息不能为空"
    //       });
    //       return;
    //     }
    //     if (this.$isNull(agentInfo.businessNature)) {
    //       this.$dialog.alert({
    //         message: "代理人对应业务来源为空"
    //       });
    //       return;
    //     }
    //   }
    //   let {
    //     formulaCode,
    //     planId,
    //     sumPremium,
    //     selectedSubIn,
    //     selectedMainIn,
    //     riskCode
    //   } = this.$store.state.config.detailData;
    //   let obj = {
    //     storeCode: localStorage.getItem("storeCode") || "", //店铺code
    //     planId, //批次
    //     formulaCode, //方案code
    //     riskCode, //产品code
    //     formulaSize: 1, // 保险份数
    //     planCategory: "1", //方案险类
    //     sumPremium, //总保费
    //     // sumPremium: 306, //总保费  测试专用，以后删除
    //     sumAmount: 0, //总保额
    //     productList: [],
    //     metaDataList: []
    //   };
    //   let sumArr = [];
    //   for (let key in selectedMainIn) {
    //     let { clauseCode, kindCode, amount, title } = selectedMainIn[key];
    //     let o = {
    //       clauseCode,
    //       kindCode,
    //       productName: title
    //     };
    //     sumArr.push(amount);
    //     obj.productList.push(o);
    //   }
    //   for (let key in selectedSubIn) {
    //     let { clauseCode, kindCode, amount, title } = selectedSubIn[key];
    //     let o = {
    //       clauseCode: clauseCode,
    //       kindCode: kindCode,
    //       productName: title
    //     };
    //     obj.productList.push(o);
    //     sumArr.push(amount);
    //   }
    //   let sum = 0;
    //   sumArr.forEach(item => {
    //     sum = item + sum;
    //   });
    //   obj.sumAmount = sum;
    //   let metaDataList = [];
    //   let formData = this.$store.state.config.detailData.formData;
    //   for (let i = 0, len = formData.length; i < len; i++) {
    //     let {
    //       id,
    //       value,
    //       title,
    //       elementType,
    //       submitName,
    //       index,
    //       options,
    //       children,
    //       groupId,
    //       field
    //     } = formData[i];
    //     let obj = {
    //       id,
    //       name: title || "",
    //       dataType: elementType || "",
    //       submitName: submitName || "",
    //       displayValue: "",
    //       groupId,
    //       value: value,
    //       arrayIndex: 0
    //     };
    //     let arrayIndex = 0;
    //     if (index) {
    //       if (index == 0) {
    //         arrayIndex = 0;
    //       } else {
    //         arrayIndex = index - 1;
    //       }
    //     }
    //     if (field.indexOf("commNumOfCopies") >= 0) {
    //       obj.displayValue = title;
    //       metaDataList.push(obj);
    //       continue;
    //     }
    //     obj.arrayIndex = arrayIndex;
    //     if (options instanceof Array && options.length > 0) {
    //       let option = options.find(item => {
    //         return item.value == value;
    //       });
    //       obj.displayValue = option ? option.label : "";
    //     }
    //     if (children instanceof Array && children.length > 0) {
    //       let option = children.find(item => {
    //         return item.value == value;
    //       });

    //       obj.displayValue = option ? option.children[0] : "";
    //     }
    //     if (obj.id) {
    //       metaDataList.push(obj);
    //     }
    //   }

    //   obj.metaDataList.push(...metaDataList);
    //   this.$store.dispatch("config/updateProductInfo", obj);
    //   console.log(297, this.$store.state.config.productInfo);
    //   // return;
    //   // this.isNeedHealthNotice = false; //测试专用
    //   // return;
    //   if (this.isNeedHealthNotice) {
    //     // this.$router.push({
    //     //   name: "prHealthNotice",
    //     //   query: { obj: JSON.stringify(this.healthNoticeObj) }
    //     // });
    //     this.$native.openWebView({
    //       url: "https://view.officeapps.live.com/op/view.aspx?src=" + this.healthNoticeObj.url,
    //       type: "0",
    //       titleBarTag: "1",
    //       titleBar: "健康及职业告知",
    //       isBackHome: "1",
    //       noncarFormPath: "nonCar/insuranceInfoInputFormcreate"
    //     });
    //   } else {
    //     if (this.orderId == 0) {
    //       this.$router.push({
    //         name: "insuranceInfoInputFormcreate",
    //         query: {}
    //       });
    //     } else {
    //       this.$router.push({
    //         name: "insuranceInfoInputFormcreate",
    //         query: {
    //           orderId: this.orderId
    //         }
    //       });
    //     }
    //   }
    // }

    // 我要投保按钮操作
    doInsure () {
      let {
        formulaCode,
        plantId,
        sumPremium,
        selectedSubIn,
        selectedMainIn,
        riskCode,
      } = this.$store.state.config.detailData
      let obj = {
        storeCode: localStorage.getItem("storeCode") || "", //店铺code
        plantId, //批次
        formulaCode, //方案code
        riskCode, //产品code
        formulaSize: 1, // 保险份数
        planCategory: "1", //方案险类
        sumPremium, //总保费
        sumAmount: 0, //总保额
        productList: [],
        metaDataList: [],
      }
      let sumArr = []
      for (let key in selectedMainIn) {
        let { clauseCode, kindCode, amount, title } = selectedMainIn[key]
        let o = {
          clauseCode,
          kindCode,
          productName: title,
        }
        sumArr.push(amount)
        obj.productList.push(o)
      }
      for (let key in selectedSubIn) {
        let { clauseCode, kindCode, amount, title } = selectedSubIn[key]
        let o = {
          clauseCode: clauseCode,
          kindCode: kindCode,
          productName: title,
        }
        obj.productList.push(o)
        sumArr.push(amount)
      }
      let sum = 0
      sumArr.forEach((item) => {
        sum = item + sum
      })
      obj.sumAmount = sum
      let metaDataList = []
      let formData = this.$store.state.config.detailData.formData
      for (let i = 0, len = formData.length; i < len; i++) {
        let {
          id,
          value,
          title,
          elementType,
          submitName,
          index,
          options,
          children,
        } = formData[i]
        let obj = {
          id,
          name: title || "",
          dataType: elementType || "",
          submitName: submitName || "",
          displayValue: "",
          value: value,
          arrayIndex: 0,
        }
        let arrayIndex = 0
        if (index) {
          if (index == 0) {
            arrayIndex = 0
          } else {
            arrayIndex = index - 1
          }
        }
        obj.arrayIndex = arrayIndex
        if (options instanceof Array && options.length > 0) {
          let option = options.find((item) => {
            return item.value == value
          })
          obj.displayValue = option ? option.label : ""
        }
        if (children instanceof Array && children.length > 0) {
          let option = children.find((item) => {
            return item.value == value
          })
          obj.displayValue = option ? option.children[0] : ""
        }
        metaDataList.push(obj)
      }
      obj.metaDataList.push(...metaDataList)
      this.$store.dispatch("config/updateProductInfo", obj)
      console.log(297, this.$store.state.config.productInfo)

      this.$router.push({
        name: "prPlanBook",
        params: {
          from: "prPlanBook",
        },
      })
    },
  },
}
</script>
<style lang="less" scoped>
#prPlan {
  width: 100%;
  height: 100%;
  font-family: PingFangSC-Semibold, PingFang SC;
  overflow: auto;
  img {
    min-height: 100px;
  }
  .prdouctInfo {
    background: rgba(36, 128, 234, 1);
  }
}
</style>
