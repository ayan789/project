<template>
  <body>
    <globalNav title="星级权益" v-on:clickBack="clickBack" />
    <div id="topImage"></div>
    <div id="superFather">
      <div
        class="box"
        v-for="(item, index) in datas"
        :key="index"
        :style="item.status == 0 ? nowFatherStyle : afterFatherStyle"
      >
        <div class="top">
          <h3
            id="insuredPerson"
            :style="item.status == 0 ? nowPersonStyle : afterPersonStyle"
          >
            被保人：{{ item.insuredPerson }}
          </h3>
          <h3
            id="status"
            :style="item.status == 0 ? nowStatusStyle : afterStatusStyle"
          >
            {{ item.status == 0 ? "现有" : "出单后" }}
          </h3>
        </div>
        <div class="middle">
          <div class="left">
            <div>
              <img
                :src="
                  item.status == 0
                    ? require('../../../assets/vehicle/insureConfirm/nowStar.png')
                    : require('../../../assets/vehicle/insureConfirm/afterStar.png')
                "
              />
            </div>
            <h3>{{ item.status == 0 ? "现有星级" : "出单后星级" }}</h3>
          </div>
          <div class="center">
            <h3>{{ item.points }}</h3>
            <h3>{{ item.status == 0 ? "现有积分" : "本次出单所获积分" }}</h3>
          </div>
          <div class="right">
            <h3>{{ item.status == 1 ? item.totalPoints : "" }}</h3>
            <h3>{{ item.status == 1 ? "积分总计" : "" }}</h3>
          </div>
        </div>
        <div class="last">
          <div class="scoreNumber">
            <div v-for="(number, i) in scoreNumbers" :key="i">{{ number }}</div>
          </div>
          <div class="scoreProgress">
            <div class="progress" :style="progressWidth(item)"></div>
            <div class="dotFather" v-for="(it, idx) in scoreNumbers" :key="idx">
              <div class="dot" v-show="item.starLevel == idx + 1"></div>
            </div>
          </div>
          <div class="scoreChinese">
            <div v-for="(chi, idx) in scoreChinese" :key="idx">{{ chi }}</div>
          </div>
        </div>
      </div>
      <div class="share">
        <div>分享至</div>
        <div @click="shareToWechat">
          <img
            src="../../../assets/vehicle/insureConfirm/wechat.png"
            id="wechat"
          />
        </div>
        <div>微信好友</div>
      </div>
    </div>
  </body>
</template>

<script>
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "insureStarRight",
  components: {
    globalNav
  },
  methods: {
    shareToWechat: function() {
      //console.log("分享给客户");
    },
    progressWidth: function(idx) {
      var wid = idx.statusPrecent * (document.body.clientWidth - 66);
      //console.log("计算出来的宽度为：" + wid);
      return {
        width: JSON.stringify(wid) + "px"
      };
    },
    clickBack() {
      this.$router.go(-1);
    }
  },

  data() {
    return {
      scoreNumbers: [0, 3001, 6001, 12001, 20001],
      scoreChinese: ["1星级", "2星级", "3星级", "4星级", "5星级"],
      nowStatusStyle: {
        //已完成的样式
        background:
          "linear-gradient(90deg,rgba(203,125,96,1) 0%,rgba(216,163,126,1) 100%)"
      },
      nowPersonStyle: {
        color: "#815333"
      },
      afterPersonStyle: {
        color: "#384F9E"
      },
      afterStatusStyle: {
        //已完成的样式
        background:
          "linear-gradient(125deg,rgba(30,70,246,1) 0%,rgba(39,154,255,1) 100%)"
      },
      nowFatherStyle: {
        color: "#B9835E",
        background:
          "linear-gradient(135deg,rgba(247,232,215,1) 0%,rgba(247,232,215,1) 29%,rgba(230,188,161,1) 66%,rgba(247,227,198,1) 100%)"
      },
      afterFatherStyle: {
        color: "#3C59B9",
        background:
          "linear-gradient(135deg,rgba(174,198,255,1) 0%,rgba(160,189,255,1) 32%,rgba(69,125,255,1) 67%,rgba(135,171,255,1) 100%)"
      },
      datas: [
        {
          insuredPerson: "张三丰", //被保人
          status: 0, //现有
          starLevel: 1, //星级
          points: 101, //积分
          totalPoints: 0, //积分总计
          statusPrecent: 0.1 //星级百分比
        },
        {
          insuredPerson: "张三丰", //被保人
          status: 1, //出单后
          starLevel: 4, //星级
          points: 1010, //积分
          totalPoints: 4399, //积分总计
          statusPrecent: 0.88 //星级百分比
        }
      ]
    };
  }
};
</script>
<style scoped>
body,
#app,
html {
  background: #f7f7f7;
  flex: 1;
  position: relative;
}
#topImage {
  width: 100%;
  height: 168px;
  background: linear-gradient(
    125deg,
    rgba(30, 70, 246, 1) 0%,
    rgba(39, 154, 255, 1) 100%
  );
}
#superFather {
  margin: 0px 15px;
  width: calc(100% - 30px);
}
.box {
  height: 213px;
  width: 100%;
  background: linear-gradient(
    135deg,
    rgba(247, 232, 215, 1) 0%,
    rgba(247, 232, 215, 1) 29%,
    rgba(230, 188, 161, 1) 66%,
    rgba(247, 227, 198, 1) 100%
  );
  border-radius: 4px 4px 0px 0px;
  box-shadow: 0px 6px 6px 0px rgba(231, 231, 231, 0.5);
  /* margin-left: 15px; */
  margin-bottom: 16px;
}

#superFather {
  margin-top: -101px;
  /* z-index: 99; */
  height: 100%;
  position: relative;
}

#insuredPerson {
  color: #815333;
  font-style: oblique;
  float: left;
  margin: 26px 0px 0px 19px;
  width: calc(100% - 70px);
  text-align: left;
}
#status {
  float: right;
  height: 25px;
  width: 50px;
  color: rgba(255, 255, 255, 1);
  line-height: 25px;
  background: linear-gradient(
    90deg,
    rgba(203, 125, 96, 1) 0%,
    rgba(216, 163, 126, 1) 100%
  );
  border-radius: 100px 0px 0px 100px;
  font-size: 12px;
  font-weight: 500;
  text-indent: 0.4em;
  margin: 26px 0px 0px 0px;
}

.left,
.center,
.right {
  float: left;
  width: 33.3%;
  height: 94px;
  text-align: center;
}
.center > :first-child,
.right > :first-child {
  font-size: 15px;
  margin: 37px 0 5px 0;
  height: 17px;
}

.left > div {
  width: 100%;
  height: 24px;
  margin-top: 39px;
  /* background: yellow; */
}
img {
  display: block;
  width: 15px;
  height: 14px;
  margin: 0px calc(50% - 7.5px);
}
.left > h3,
.center > :last-child,
.right > :last-child {
  height: 18px;
  line-height: 18px;
  font-size: 13px;
  text-align: center;
}
.center > h3 {
  text-align: center;
}
.right > :first-child {
  margin-top: 30px;
}
.center > :last-child,
.right > :last-child {
  margin-top: 10px;
}
.top {
  height: 44px;
}
.middle {
  height: 94px;
}

.last {
  background: white;
  height: 75px;
  width: 100%;
}
.scoreNumber,
.scoreChinese {
  height: 30px;
  margin-left: 17.5px;
  margin-right: 17.5px;
}
.scoreNumber > div,
.scoreChinese > div {
  float: left;
  width: 20%;
  height: 30px;
  font-size: 12px;
  font-weight: 500;
  color: rgba(102, 102, 102, 1);
  line-height: 30px;
  text-align: center;
}
.scoreChinese > div {
  font-size: 10px;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 30px;
  height: 30px;
  text-align: center;
}

.scoreProgress {
  margin: 4.5px 17.5px;
  background: rgba(242, 242, 242, 1);
  width: calc(100% - 35px);
  height: 5px;
  overflow: hidden;
}
.progress {
  background: #2796ff;
  margin-top: 1px;
  margin-left: 0px;
  height: 3px;
  border-radius: 4px;
}
.dotFather {
  float: left;
  width: 20%;
  height: 5px;
  margin-top: -4.5px;
  position: relative;
  z-index: 22;
}
.dot {
  width: 5px;
  height: 5px;
  margin: 0px auto;
  background: #2796ff;
  border-radius: 5px;
}

.share {
  height: 134px;
  width: 100%;
  background: #f7f7f7;
}
.share > :first-child {
  color: rgba(153, 153, 153, 0.4);
  width: 100%;
  text-align: center;
  font-size: 14px;
  font-weight: 400;
  line-height: 20px;
  height: 20px;
}

.share > :nth-child(2) {
  width: 50px;
  height: 50px;
  margin-left: calc(50% - 25px);
  margin-top: 16px;
}
#wechat {
  width: 50px;
  height: 50px;
  display: block;
  margin: 0 auto;
}
.share > :last-child {
  height: 38px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(102, 102, 102, 1);
  line-height: 38px;
  text-align: center;
}
</style>
