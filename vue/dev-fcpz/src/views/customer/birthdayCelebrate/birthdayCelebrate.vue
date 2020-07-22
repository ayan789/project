<template>
  <div id="birthdayCelebrate">
    <globalNav
      title="生日祝福"
      @clickBack="clickBack"
    />
    <div class="hello">
      <ul>
        <div class="separate"></div>
        <div class="center">
          <p>祝福语</p>
          <van-field
            v-model="message"
            rows="2"
            autosize
            label=""
            type="textarea"
            maxlength="30"
            placeholder="请输入祝福语"
            show-word-limit
          />
        </div>
        <div class="bottom">
          <p>贺卡效果</p>

          <van-swipe
            loop
            fit="contain"
            @change="onChange"
          >
            <van-swipe-item
              v-for="(image, index) in images"
              :key="index"
            >
              <div
                class="imageView"
                :style="{ backgroundImage: 'url(' + image + ')' }"
              >
                <div class="textView">
                  <div>亲爱的{{ $route.query.item.custName }}:</div>
                  <div class="birthdayCelebrate_message">
                    {{ `${message}` }}
                  </div>
                  <div class="birthdayCelebrate_3View">
                    大地保险:{{ $localStorage.userName }}
                  </div>
                  <div class="birthdayCelebrate_3View">
                    联系方式:{{ $localStorage.userPhone }}
                  </div>
                </div>
              </div>
            </van-swipe-item>
          </van-swipe>
        </div>
      </ul>
    </div>
    <div class="birthdayCelebrate_buttonStyle">
      <div
        class="birthdayCelebrate_buttonView"
        @click="saveImageShare"
      >
        <div class="birthdayCelebrate_buttonText">保存图片分享</div>
      </div>
    </div>
  </div>
</template>

<script>
import globalNav from "@/components/globalNav.vue";
import {
  getQueryConfigPhotoList,
  doneTaskClick
} from "@src/http/module/customerApi.js";
import { mapState } from "vuex";
import { trackMap } from "@src/utils/index.js";
export default {
  name: "birthdabirthdayCelebrateyCelebrate",
  components: {
    globalNav
  },
  props: {
    // msg: String,
  },
  data () {
    return {
      message: "",
      currentSrc: "",
      tempImages: [
        "https://img.yzcdn.cn/vant/apple-1.jpg",
        "https://img.yzcdn.cn/vant/apple-2.jpg"
      ],
      images: [],
      imgUrl: "",
      index_type: 0
    };
  },

  computed: {
    ...mapState(["customerData"])
  },

  created () {
    this.requestDatas();
  },

  methods: {
    clickBack () {
      this.$router.back();
    },
    onChange (index) {
      this.index_type = index;
    },

    saveImageShare: function () {
      const { custName, remindTimeNode } = this.$route.query.item;
      const jsonVal = {
        imageURL: this.images[this.index_type], //图片链接
        qrCodeuURLString:
          "https://custapp.95590.cn/?cl_sr=Customer&chInfo=ch_share__chsub_CopyLink",
        blessInfo: "亲爱的" + custName, //被祝福人
        content: this.message, //祝福内容
        contactPer: "大地保险：" + this.$localStorage.userName, //联系人
        contactPhone: "联系方式:" + this.$localStorage.userPhone //联系电话
      };

      console.log(jsonVal, "jsonVal====");

      this.$native.birthdayWishes(jsonVal, data => {
        let res = JSON.parse(data);
        if (res.resultCode == "1") {
          const { custNo, id } = this.$route.query.item;
          let params = {
            remindId: id
          };
          doneTaskClick(params);
          //刷新跟进提醒页面
          let params_ = {
            userCode: localStorage.getItem("userCode"), //业务员号
            custNo: "", //客户号
            remindType: this.customerData.active //提醒类型
          };
          //埋点
          trackMap("c_customer_serve_birthday_wish", "客户服务生日祝福", {
            c_salesperson_type: "Y",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "客户管理"
          });
          this.$store.dispatch("customerData/getfollowRemindDatat", params_);
          this.$router.back();
        }
      });
    },

    async requestDatas () {
      let params = {
        orgCode: this.$localStorage.comCode //分公司机构代码
      };
      let res = await getQueryConfigPhotoList(params);
      if (res.code == "1") {
        let tempImages = [];
        if (res.code == "1") {
          res.data.map((v, i) => {
            tempImages.push(v.image);
          });
          this.images = tempImages;
        }
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">
#birthdayCelebrate {
  height: calc(100vh);
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
}

.imageView {
  width: 100%;
  height: 100%;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: flex-end;

  .textView {
    font-size: 16px;
    height: 120px;
    width: 100vw;
    justify-content: space-between;
    align-items: flex-start;
    display: flex;
    flex-direction: column;
    word-break: break-all;
    background-color: rgba(247, 247, 247, 0.7);

    .birthdayCelebrate_3View {
      font-size: 16px;
      display: flex;
      align-self: flex-start;
      margin-left: 45vw;
    }
  }
}

.birthdayCelebrate_buttonStyle {
  width: 100vw;
  display: flex;
  display: -webkit-flex;
  flex-direction: row;
  -webkit-flex-direction: row;
  justify-content: center;
  -webkit-justify-content: center;
  background-color: #ffffff;
  padding: 20px 0px;

  .birthdayCelebrate_buttonView {
    width: 100%;
    margin: 0px 10px;
    height: 7vh;
    display: flex;
    display: -webkit-flex;
    flex-direction: row;
    -webkit-flex-direction: row;
    justify-content: center;
    -webkit-justify-content: center;
    align-items: center;
    -webkit-align-items: center;
    border-radius: 30px;
    background: linear-gradient(to right, #2a80ff, #2aa5ff);

    .birthdayCelebrate_buttonText {
      font-size: 16px;
      color: #ffffff;
    }
  }
}

* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
}

body {
  background: white;
}

.top {
  margin-top: 64px;
  height: 132px;
}

.separate {
  height: 30px;
}

.center {
  margin-top: 43px;
  height: 144px;
}

h1 {
  color: white;
}

.top,
.center,
.bottom {
  background: white;
}

.item_img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  border-radius: 4px;
}

.bottomShare > h3 {
  height: 44px;
  margin: 21px 15px;
  display: block;
  color: white;
  line-height: 44px;
  background: rgba(59, 138, 254, 1);
  border-radius: 46px;
  text-align: center;
}

.van-swipe {
  height: 504px;
  background: #f5f5f5;
  margin: 0px 15px;
}

#bottom > img {
  height: 503px;
  /* margin */
}

#top,
#center,
#bottom {
  margin: 0 11.5px;
}

.van-field {
  margin: 0 15px;
  width: calc(100% - 30px);
  background: rgba(245, 245, 245, 1);
  border-radius: 4px;
  padding: 15px;
}

#top > ul {
  display: inline-block;
  height: 70px;
}

#top > ul > img {
  /* margin:0px 13px; */
  width: 33.3%;
  height: 70px;
}

p {
  text-align: left;
  text-indent: 1em;
  line-height: 41px;
  font-size: 15px;
  /* font-family: PingFang-SC-Medium, PingFang-SC; */
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
}

.birthdayCelebrate_message {
  display: flex;
  flex: 1;
  margin: 5px 0px;
}
</style>
