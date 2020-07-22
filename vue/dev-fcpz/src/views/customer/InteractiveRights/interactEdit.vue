<template>
  <div class="interactEdit">
    <globalNav title="互动记录" @clickBack="clickBack"/>
    <van-popup v-model="dataTimeShow" position="bottom" @close="cancelClick">
      <van-datetime-picker
        v-model="currentTime"
        type="year-month"
        :filter="filter"
        :formatter="formatter"
        @confirm="confirmClick"
        @cancel="cancelClick"
      />
    </van-popup>
    <div class="interactEdit_dataView" @click="dataTimeShow=getCustomerInteractionData.length>0?true:false">
      <div style="color: #0D84F2;fontSize:16px;font-weight: bold">
        {{getCustomerInteractionData.length>0?'请选择时间日期':'暂无时间可选择'}}
      </div>
      <van-icon :name="dataTimeShow?'arrow-up':'arrow-down'" color="#0D84F2" size="20"/>
    </div>

    <div class="contentStyle">
      <van-swipe-cell v-for="(item_,index) in getCustomerInteractionData" class="itemView"
                      :disabled="item_.type!='12'" :key="item_.id">
        <div>
          <fieldItemView :item="item_" @clickItemEdit="clickItemEdit(item_)"/>
        </div>
        <van-button
          slot="right"
          square
          text="删除"
          type="danger"
          class="delete-button"
          @click="deleteClick(item_)"
        />
        <div class="lineStyle"/>
      </van-swipe-cell>
    </div>

    <div class="borView">
      <div class="addView">
        <van-field
          v-model="value"
          center
          rows="1"
          maxlength="100"
          clearable
          placeholder="自定义备注">
          <van-button slot="button" size="small" type="info" @click="addOrUpdate_Interactions">新增</van-button>
        </van-field>
      </div>
    </div>
  </div>
</template>

<script>
import globalNav from "../../../components/globalNav.vue";
import fieldItemView from "./fieldItemView";
import { mapState } from "vuex";
import { deleteInteractionClick, queryCustomerInteractionDate } from '../../../http/module/customerApi';
import { Toast } from 'vant';
export default {
  name: "interactEdit",
  components: {
    globalNav,
    fieldItemView
  },
  props: {
    // msg: String,
  },
  data() {
    return {
      value: "",
      statusValue: true,
      tempIndex: '',
      getCustomerInteractionData: [],
      currentTime: new Date(),
      dataTimeShow: false,
      yearArr: [],
      monthArr: [],
      dataTime: {},
      haveTime: true
    };
  },

  computed: {
    ...mapState(["customerData"]),
  },

  watch: {
    customerData: {
      handler: function (val, oldVal) {
        this.$nextTick(function () {
          console.log(val.getCustomerInteractionData, '====');
          this.getCustomerInteractionData = val.getCustomerInteractionData || [];
        });
      },
      // 深度观察监听
      deep: true,
      immediate: true
    }
  },

  created() {
    this.get_interaction();
    this.get_queryCustomerInteractionDate();
  },

  methods: {
    cancelClick() {
      this.dataTimeShow = false;
    },
    async confirmClick(item) {
      console.log(item.getFullYear(), item.getMonth() + 1);
      const { custNo } = this.$route.query.item;
      let year_ = item.getFullYear();
      let day_ = item.getMonth() + 1;
      let params = {
        userCode: localStorage.getItem("userCode"), //业务号
        custNo: custNo, //客户号
        date: year_ + '-' + day_
      };
      await this.$store.dispatch("customerData/getCustomerInteractions", params);
      this.dataTimeShow = false;
    },
    filter(type, options) {
      if (type === 'year') {
        return options = this.yearArr;
      } else if (type === 'month') {
        return options = Object.keys(this.dataTime[this.currentTime.getFullYear()]);
      }
      return options;
    },
    formatter(type, val) {
      if (type === 'year') {
        return `${val}年`;
      } else if (type === 'month') {
        return `${val}月`;
      }
      return val;
    },
    async get_queryCustomerInteractionDate() {
      let params = {
        userCode: localStorage.getItem("userCode"), //业务号
        custNo: this.$route.query.item.custNo
      };
      let res = await queryCustomerInteractionDate(params);
      this.dataTime = res.data;
      if (res.code == '1') {
        for (let key in res.data) {
          console.log(key, res.data[key], '==data==');
          this.yearArr = [];
          this.yearArr.push(key + "");
        }
      }
      console.log(res, '时间===');
    },
    async get_interaction() {
      const { userCode, custNo } = this.$route.query.item;
      let date = new Date();
      let year_ = date.getFullYear();
      let day_ = date.getMonth() + 1;
      let params = {
        userCode: localStorage.getItem("userCode"), //业务号
        custNo: custNo, //客户号
        date: year_ + '-' + day_
      };
      await this.$store.dispatch("customerData/getCustomerInteractions", params);
    },
    clickBack() {
      this.$router.back();
    },
    async clickItemEdit(item) {
      const { userCode, custNo } = this.$route.query.item;
      let params = {
        id: item.id,
        custNo: custNo,
        createUser: localStorage.getItem("userCode"),
        type: item.type,
        title: item.title,
        remark: item.remark,
        userCode: localStorage.getItem("userCode")
      };
      await this.$store.dispatch('customerData/addOrUpdateInteractions', params);
      await this.get_interaction();
    },
    //新增或者修改互动纪录
    async addOrUpdate_Interactions() {
      if (this.value.trim()) {
        const { userCode, custNo } = this.$route.query.item;
        let params = {
          custNo: custNo,
          createUser: localStorage.getItem("userCode"),
          type: '12',
          title: '自定义备注',
          remark: this.value,
          userCode: localStorage.getItem("userCode")
        };
        await this.$store.dispatch('customerData/addOrUpdateInteractions', params);
        await this.get_interaction();
        this.get_queryCustomerInteractionDate();
        this.value = "";
        this.$forceUpdate();
      } else {
        Toast('请先录入备注');
      }
    },
    async deleteClick(item) {
      await deleteInteractionClick({ id: item.id });
      await this.get_interaction();
    }
  }
};
</script>
<style scoped>
.interactEdit {
  position: fixed;
  width: 100%;
  background-color: white;
}

.contentStyle {
  margin-top: 120px;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  overflow-y: scroll;
  height: calc(100vh - 170px);
}

.borView {
  height: 51px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  bottom: 0px;
  border: 1px solid #E5E5E5;
}

.addView {
  height: 51px;
  width: 90%;
  display: flex;
}

.itemStyle {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px 20px;
}

.interactEdit.title {
  color: #333333;
  font-size: 14px;
  font-weight: bold;
}

.messageStyle {
  color: #545454;
  font-size: 14px;
  margin: 4px 0px;
}

.interactEdit.timeStyle {
  color: #999999;
  font-size: 12px;
  margin-bottom: 4px;
}

.lineStyle {
  width: 100%;
  height: 1px;
  background-color: #F1F1F1;
  margin: 5px 0px 5px 20px
}

.delete-button {
  height: 100%;
}

.editStyle {
  color: #3B8AFE;
  font-size: 12px;
}

.interactEdit.tipText {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.interactEdit >>> .van-field__label {
  width: 40px;
}

.interactEdit >>> .van-cell {
  padding: 5px 0px;
}

.interactEdit_dataView {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding: 0px 20px;
  align-items: center;
  margin-bottom: 20px;
  margin-top: 80px;
  position: absolute;
  height: 40px;
  z-index: 100;
  width: 100%;
}
</style>
