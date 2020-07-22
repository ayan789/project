<template>
  <div class="super">
    <globalNav title="增值套餐A" />
    <div v-for="(item,index) in datas" :key="index">
      <div class="father" @click="clickItem(item)">
        <div class="leftContainer">
          <img :src="item.iconUrl" />
        </div>
        <div class="rightContainer">
          <h3>{{item.title}}</h3>
          <h3>{{item.detail}}</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../components/globalNav.vue";
export default {
  name: "insureAdditionService",
  components: {
    globalNav
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      datas: [
        {
          iconUrl:
            "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1582083778&di=37103b8740b90f4150280f09f1a5a5c2&src=http://image.huahuibk.com/uploads/20190222/23/1550850387-MFLyASvKBD.jpg",
          title: "安全停靠",
          detail:
            "被保车辆为家庭自用车时，为本保险车辆被保险人提供一次高铁贵宾厅停留服务（限一天）。",
          linkUrl: "https://www.bilibili.com/"
        },
        {
          iconUrl:
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2247383343,1188234882&fm=26&gp=0.jpg",
          title: "代为驾驶",
          detail:
            "被保车辆为家庭自用车时，为本保险车辆提供8次代为驾驶服务。每次代驾距离不超过20公里。",
          linkUrl: "https://www.baidu.com"
        }
      ]
    };
  },
  methods: {
    clickItem: function(item) {
    document.location.href = item.linkUrl;
    }
  }
};
</script>
<style scoped>

.super {
  width: 100%;
  height: 100%;
  position: absolute;
  background: #f7f7f7;
}

img {
  width: 80px;
  height: 80px;
  border-radius: 80px;
  display: block;
  margin: 22px;
}

.super > :nth-child(2) {
  margin-top: 64px;
}

.leftContainer,
.rightContainer {
  float: left;
}
.father {
  margin: 10px 15px 0 15px;
  height: 124px;
  background: white;
  border-radius: 8px;
}
.rightContainer {
  width: 55.2%;
  text-align: left;
  color: rgb(26, 5, 5);
  line-height: 17px;
  height: 124px;
  line-height: 20px;
}

.rightContainer > :first-child {
  font-size: 14px;
  font-weight: bold;
  line-height: 40px;
  margin-top: 10px;
}
.rightContainer > :last-child {
  font-size: 12px;
  font-weight: normal;
  overflow: hidden;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
}
.leftContainer {
  width: 124px;
}
</style>
