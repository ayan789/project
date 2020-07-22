<!--
 * @Author: heweiwei
 * @Page: 新增设备损失险
 * @Date: 2020-03-06 11:00:30
 * @Last Modified by: King
 * @Last Modified time: 2020-05-29 18:10:15
 * @FilePath: /src/views/vehicle/selectProduct/equipmentDamage.vue
-->
<template>
  <div class="equipment-damage">
    <van-sticky>
      <nav-bar
        type="vehicle"
        :title="risk.kindName"
        @clickBack="$router.back()"
      />
    </van-sticky>
    <div class="content">
      <div class="content-title">
        <div>新增设备损失险</div>
        <div class="content-add-button" @click="addNewDevice">
          <img
            class="img"
            src="../../../assets/vehicle/vehicleInfo/addnew.png"
            alt
          />
          <div>新增设备</div>
        </div>
      </div>
      <van-cell class="cell-item">
        <span slot="title" class="cell-item-title">不计免赔</span>
        <whether-select
          v-model="risk.kindFlag"
          :options="[
            { text: '是', value: '1' },
            { text: '否', value: '0' }
          ]"
        />
      </van-cell>
      <div class="list">
        <div class="item" v-for="(device, index) in newDevices" :key="index">
          <div class="item-left" @click="deleteDevice(device, index)">
            <div>设备{{ index + 1 }}</div>
            <van-icon name="delete" size="16" />
          </div>
          <div class="item-center">
            <input
              class="item-center-input"
              v-model="device.deviceName"
              placeholder="设备名称"
              maxlength="50"
            />
            <div @click="showDatePicker(index)">
              <input
                disabled
                class="item-center-input"
                v-model="device.deviceBuyDate"
                placeholder="购置日期"
              />
            </div>
          </div>
          <div class="item-right">
            <input
              class="item-right-input"
              v-model.number="device.deviceNum"
              @input="input"
              placeholder="数量"
              maxlength="3"
            />
            <input
              class="item-right-input"
              v-model.number="device.deviceAmount"
              @input="input"
              placeholder="购置价(元)"
              maxlength="10"
            />
          </div>
        </div>
      </div>
      <van-cell class="cell-item">
        <span slot="title" class="cell-item-title">保险金额</span>
        <div class="cell-item-right">{{ total }}元</div>
      </van-cell>
      <div style="height:100px;"></div>
    </div>
    <confirmBottom @click="saveRisk" />
    <van-popup v-model="datePickerShow" position="bottom">
      <van-datetime-picker
        type="date"
        v-model="currentDate"
        :max-date="maxDate"
        @confirm="datePickerConfirm"
        @cancel="datePickerShow = false"
      />
    </van-popup>
  </div>
</template>
<script>
import confirmBottom from "@/components/confirmBottom.vue";
import WhetherSelect from "../vehicleInfo/whetherSelect";
import { Risk, NewDevice } from "@src/store/modules/vehicle/vehicle";
import { mapMutations, mapState, mapActions } from "vuex";
export default {
  components: {
    confirmBottom,
    [WhetherSelect.name]: WhetherSelect
  },
  model: {
    prop: "plan",
    event: "change"
  },
  data() {
    // let minDate = new Date();
    // minDate.setFullYear(minDate.getFullYear() - 5);
    let { params = {} } = this.$route;
    let { kindCode, kindName } = params;
    console.log(params);
    return {
      editIndex: -1,
      datePickerShow: false,
      currentDate: new Date(),
      // minDate: new Date(),
      maxDate: new Date(),
      newDevices: [],
      risk: Object.assign({}, Risk, {
        kindCode: kindCode,
        kindName: kindName,
        kindFlag: "1",
        amount: ""
      })
    };
  },
  created() {
    this.newDevices = this.plan.newDevices.filter(device => !!device);
    let { params = {} } = this.$route;
    let { kindCode, kindName } = params;
    this.kindCode = this.kindCode || kindCode;
    this.kindName = this.kindName || kindName;
  },
  computed: {
    ...mapState("vehicle", {
      quote: ({ quoteInfo }) => quoteInfo,
      devicesAmount: ({ devicesAmount }) => devicesAmount
    }),
    plan() {
      return (
        this.quote.plans.find(plan => plan.planCode === this.quote.planCode) ||
        this.quote.plans[0] ||
        {}
      );
    },
    total() {
      let total = this.newDevices.reduce((pre, cur) => {
        let n = +cur.deviceNum * +cur.deviceAmount;
        if (Number.isSafeInteger(n)) {
          return pre + n;
        }
        return pre;
      }, 0);
      return total;
    }
  },
  methods: {
    ...mapMutations("vehicle", ["setNewDevices", "addBusinessRisk"]),
    ...mapActions("vehicle", ["calculateActualValue"]),
    input() {},
    showDatePicker(index) {
      this.editIndex = index;
      this.datePickerShow = true;
    },
    datePickerConfirm(date) {
      this.newDevices[this.editIndex].deviceBuyDate = date.format("yyyy-MM-dd");
      this.editIndex = -1;
      this.datePickerShow = false;
    },
    datePickerCancel() {
      this.editIndex = -1;
      this.datePickerShow = false;
    },
    addNewDevice() {
      this.newDevices.push(Object.assign({}, NewDevice));
    },
    deleteDevice(device, index) {
      this.newDevices.splice(index, 1);
    },
    saveRisk() {
      //NOTE: 校验并写入
      const names = {
        deviceName: "设备名称",
        deviceBuyDate: "设备购买日期",
        deviceNum: "设备数量",
        deviceAmount: "设备保额"
      };
      try {
        this.newDevices.forEach((device, index) => {
          for (const key in names) {
            if (device.hasOwnProperty(key)) {
              const element = device[key];
              if (!element) throw names[key] + "不能为空";
            }
          }
          device.deviceBuyDate = new Date(device.deviceBuyDate).format();
        });
      } catch (error) {
        return this.$toast(error);
      }
      if (this.newDevices.length) {
        this.setNewDevices([...this.newDevices]);
        this.calculateActualValue({
          callback: () => {
            this.risk.amount = this.devicesAmount;
            console.log("新增设备", JSON.stringify(this.risk));
            this.addBusinessRisk(Object.assign({}, this.risk));
            this.$router.back();
          }
        });
      }
    }
  }
};
</script>
<style lang="less" scoped>
.equipment-damage {
  display: flex;
  flex-direction: column;
  height: 100%;
}
.content {
  position: relative;
  background: white;
  width: 100%;
  height: calc(100% - 77px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  .content-title {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: flex-start;
    padding: 0 15px;
    height: 30px;
    color: #333333;
    font-size: 14px;
    font-weight: 600;
    .content-add-button {
      display: flex;
      flex-direction: row;
      align-items: center;
      color: #3b8afe;
      font-weight: 400;
      img {
        width: 20px;
        height: 20px;
        margin-right: 6px;
      }
    }
  }
  .cell-item {
    display: flex;
    flex-direction: row;
    align-items: center;
    .cell-item-title {
      color: #333333;
      font-size: 14px;
    }
  }
  .list {
    padding: 10px 12px;
    .item {
      display: flex;
      flex-direction: row;
      margin-bottom: 10px;
      div {
        display: flex;
      }
      .item-left {
        width: 51px;
        height: 79px;
        background: #f0f2f5;
        border-radius: 3px;
        font-size: 14px;
        font-weight: 600;
        line-height: 18px;
        color: #333333;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;

        img {
          width: 16px;
          height: 16px;
        }
      }
      .item-center {
        flex-direction: column;
        justify-content: space-between;
        margin-left: 7px;
        input {
          box-sizing: border-box;
          width: 200px;
          height: 36px;
          background: white;
          border-radius: 3px;
          border: 1px solid #e5e5e5;
          font-size: 14px;
          color: #333333;
          padding: 0 10px;
        }
      }
      .item-right {
        flex-direction: column;
        justify-content: space-around;
        margin-left: 10px;
        input {
          box-sizing: border-box;
          width: 80px;
          height: 36px;
          background: white;
          border-radius: 3px;
          border: 1px solid #e5e5e5;
          font-size: 14px;
          color: #333333;
          padding: 0 10px;
        }
      }
    }
    .item:last-child {
      margin-bottom: 0;
    }
  }
}

input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ababab;
  font-size: 14px;
}
</style>
