<template>
  <div class="queryRemindView">
    <navigationItem title="星级权益" rightText="等级规则" @clickRight="howToIntegral"/>
    <div class="contentStyle">
      <div class="startLevelView">
        <div class="tipView">当前星级</div>
        <van-rate
          v-model="customerInfoData.customerStartLevel.level"
          allow-half
          void-icon="star"
          void-color="#eee"
          color="#3B8AFE"
          readonly
        />
      </div>
      <div class="tipTopView">
        <div>
          <span class="stareNumber">{{+customerInfoData.customerStartLevel.integral}} </span>
          <span class="numberStyle" v-if="customerInfoData.customerStartLevel.level!=5">/ {{+customerInfoData.customerStartLevel.integral?customerInfoData.customerStartLevel.nextLevelIntegral.split('-')[0]-1:0}}</span>
        </div>
        <div @click="toIntegralInfo(customerInfoData.customerStartLevel.integral)">
          <span class="infoStyle">积分详情</span>
          <van-icon name="arrow"/>
        </div>
      </div>
      <div class="progressView">
        <van-progress
          :percentage="Math.round(+customerInfoData.customerStartLevel.integral/(customerInfoData.customerStartLevel.level!=5?(+customerInfoData.customerStartLevel.nextLevelIntegral.split('-')[0]-1):+customerInfoData.customerStartLevel.integral)*100)"
          stroke-width="8"/>
      </div>
      <!--      <div @click="howToIntegral">-->
      <!--        如何提升积分？-->
      <!--      </div>-->
      <div class="titleView">
        星级权益
      </div>

      <van-collapse v-model="customerInfoData.customerStartLevel.activeName" accordion>
        <van-collapse-item v-for="(item_,index) in customerInfoData.integralList"
                           :title="123" :name="item_.level">
          <div slot="title">
            <div class="itemTitle" :style="{'backgroundImage':'url('+item_.tempImguRL+')'}">
              <span class="titleStyle" :style="item_.tempStyle">{{item_.levelTitle}}</span>
              <van-rate
                v-model="item_.startValue"
                allow-half
                void-icon="star"
                void-color="transparent"
                :color=item_.tempColor
                :size='10'
                readonly
              />
            </div>
          </div>
          <div slot="right-icon"></div>
          <div slot="default">
            <div v-for="(item,i) in item_.serviceItemList" class="queryRemindView_iconView">
              <div class="iconItem">
                <img :src="$imageUrl.lvicon" alt="" class="iconStyle">
                <span>{{item.serviceName}}</span>
              </div>
            </div>
          </div>
        </van-collapse-item>
      </van-collapse>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import navigationItem from "../followUpremind/navigationItem";
import { Toast } from 'vant';
export default {
  components: {
    navigationItem
  },
  data() {
    return {
      boxshow: false,
    };
  },

  computed: {
    ...mapState(["customerInfoData"])
  },

  async mounted() {
    const { comCode, custNo } = JSON.parse(this.$route.query.item);
    let params = {
      comCode: this.$localStorage.comCode,
      custNo: custNo
    };
    this.$store.dispatch('customerInfoData/getQueryCustomerLevel', params);
    this.$store.dispatch('customerInfoData/getGetIntegralList', params);
    console.log(this.customerInfoData.integralList, 'customerStartLevel===');
  },
  methods: {
    clickBack() {
      this.$router.back();
    },
    toIntegralInfo(value) {
      if (+value) {
        this.$router.push({ name: 'integralInfoView', query: { item: this.$route.query.item } });
      } else {
        Toast('您还没有积分');
      }
    },
    howToIntegral() {
      this.$router.push({ name: 'howToIntegralView' });
    }
  }
};
</script>

<style lang="less" scoped>
.queryRemindView {
  position: fixed;
  width: 100%;

  /deep/ .van-cell {
    padding: 0px;
  }
}

.contentStyle {
  background: #ffffff;
  margin-top: 80px;
  overflow: scroll;
  height: calc(100vh - 85px);
  display: flex;
  padding: 0 25px;
  flex-direction: column;
}

.startLevelView {
  display: flex;
  align-items: center;
  margin: 20px 0px;

  .tipView {
    margin-right: 10px;
    color: #333333;
    font-weight: bold;
    font-size: 16px;
  }
}

.tipTopView {
  display: flex;
  justify-content: space-between;
  align-items: center;

  .stareNumber {
    color: #3B8AFE;
    font-size: 18px;
  }

  .numberStyle {
    color: #666666;
    font-size: 15px;
  }
}

.progressView {
  margin: 20px 0px;
}

.infoStyle {
  color: #666666;
  font-size: 13px;
  margin-right: 5px;
}

.titleView {
  color: #333333;
  font-size: 15px;
  font-weight: bold;
  margin: 10px 0px;
}

.itemTitle {
  width: 100%;
  background-color: #C6DFFF;
  background-size: cover;
  height: 39px;
  border-radius: 10px 10px 0px 0px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}

.queryRemindView .titleStyle {
  font-size: 14px;
  margin: 0px 15px;
}

.queryRemindView_iconView {
  display: flex;
  align-items: center;
}

.iconItem {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.iconStyle {
  width: 28px;
  height: 28px;
  background-size: contain;
  margin: 5px 10px;
}

</style>


