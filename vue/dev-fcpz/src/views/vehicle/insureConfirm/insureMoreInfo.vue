<template>
  <div class="container">
    <nav-bar type="vehicle" title="更多信息" @clickBack="clickBack" />
    <div class="father">
      <div class="item holder">
        <div class="left">
          <div class="icon">投</div>
        </div>
        <div class="right">
          <h3>{{ holder.name }}</h3>
          <div>
            <h3 class="card">证件类型：</h3>
            <h3 class="rightText">{{ identifyTypes[holder.identityType] }}</h3>
          </div>
          <div>
            <h3 class="card">证件号码：</h3>
            <h3 class="rightText">{{ holder.identityNo }}</h3>
          </div>
        </div>
      </div>
      <div class="item insureder">
        <div class="left">
          <div class="icon">被</div>
        </div>
        <div class="right">
          <h3>{{ insureder.name }}</h3>
          <div>
            <h3 class="card">证件类型：</h3>
            <h3 class="rightText">
              {{ identifyTypes[insureder.identityType] }}
            </h3>
          </div>
          <div>
            <h3 class="card">证件号码：</h3>
            <h3 class="rightText">{{ insureder.identityNo }}</h3>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Config from "@src/common/json/newCoreDataDictionary";
export default {
  name: "insureMoreInfo",
  components: {},
  props: {
    // msg: String,
  },
  data() {
    return {
      // 证件类型枚举
      identifyTypes: Config.identifyType
    };
  },
  computed: {
    ...mapState("vehicle", {
      holder: state => state.policyHolder,
      insureder: state => state.recognizee
    })
  },
  methods: {
    clickBack() {
      this.$router.go(-1);
    }
  }
};
</script>
<style lang="less" scoped>
* {
  margin: 0px;
  padding: 0px;
  text-align: left;
}
h3 {
  line-height: 20px;
  font-size: 14px;
  font-weight: 400;
  color: #333333;
  overflow: auto;
}
img {
  width: 50px;
  height: 50px;
  margin: 15px 19px 0 19px;
  border-radius: 50px;
}

.container {
  background: white;
}
#father {
  background: white;
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
}
.item {
  margin: 15px;
  // background: white;
  height: 100px;
  // border-radius: 5px;
  border-bottom: 1px solid #eeeeee;
}
#father {
  margin-top: 44px;
}
.left,
.line {
  float: left;
  height: 100px;
}
.right {
  float: left;
  height: 100px;
  width: calc(100% - 90px);
}
.left {
  width: 88px;
  height: 100px;
}
.left > :last-child {
  margin: 4px 0 0 0px;
  text-align: center;
  height: 14px;
  font-size: 14px;
  font-weight: 400;
  color: rgba(59, 138, 254, 1);
  line-height: 14px;
  background-clip: text;
}
.line {
  height: 54px;
  margin: 23px 0;
  width: 1px;
  background-color: rgba(229, 230, 232, 1);
}
.right > :first-child {
  font-size: 15px;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  line-height: 22px;
  margin: 15px 0 0 15px;
}
.right > div {
  display: flex;
  justify-content: left;
}
.card,
.rightText {
  float: left;
  text-align: left;
  line-height: 28px;
}
.card {
  width: 80px;
  height: 28px;
  padding-left: 15px;
  font-size: 12px;
  font-weight: 400;
  color: #666666;
}
.rightText {
  float: right;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: calc(100% - 60px);
  height: 28px;
}
.holder {
  .icon {
    background: #659cff;
  }
}
.insureder {
  .icon {
    background: #2ab6ff;
  }
}
.item {
  padding-left: 15px;
  .left {
    .icon {
      position: relative;
      top: 15px;
      width: 50px;
      height: 50px;
      border-radius: 50%;
      font-size: 20px;
      font-weight: 600;
      color: white;
      line-height: 50px;
      margin-left: 15px;
    }
  }
}
</style>
