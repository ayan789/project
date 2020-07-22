<template>
  <div style="position:relative">
    <div class="mark" v-if="tabs.length > 1 && activeTab == '个人目标' && changeStatus !== '1' && signStatus == '1'" ></div>
    <div class="card">
      <div class="card-title-container">
        <div class="card-title-text">年实收目标（元）</div>
        <div class="card-title-right">
          <div class="card-title-right-name">总计</div>
          <div class="card-title-right-value">{{ yearTotalPremium }}</div>
        </div>
      </div>
      <div class="card-items-container">
        <div
          v-for="(key, index) in insuranceKindKey"
          :key="key"
          class="card-item-container"
        >
          <div class="card-item-title">{{ insuranceKind[index] }}</div>
          <input
            class="card-item-input"
            :readonly="displayType && pre === 'personal' ? true : false"
            :ref="pre + key"
            @input="e => onYearInputChange(e, key)"
          />
        </div>
      </div>
    </div>
    <div class="card">
      <div class="card-title-container">
        <div class="card-title-text">月度目标（元）</div>
        <div class="card-title-right">
          <div class="card-title-right-name right-month">总保费平均分配</div>
          <van-switch
            v-model="data.isAverage"
            active-color="#3B8AFE"
            inactive-color="#f7f7f7"
            size="20"
            :disabled="displayType && pre === 'personal' ? true : false"
            @change="onAverageChange"
          />
        </div>
      </div>
      <div class="card-items-container">
        <div
          v-for="(month, index) in months"
          :key="month"
          class="card-item-container item-month"
        >
          <div
            class="card-item-title title-month"
            v-popover.bottom="{ name: month }"
          >
            {{ index + 1 + "月" }}
          </div>
          <popover :name="month" v-if="index >= newdate ? true : false ">
            <div class="details-wrap" >
              <van-row
                class="item-premium"
                type="flex"
                justify="space-between"
                align="center"
              >
                <van-col class="item-premium-name">车险保费</van-col>
                <van-col class="item-premium-value">
                  {{
                    Object.keys(data).length
                      ? data[month].monthTotalPremiumDtail.carInsurePremium
                      : 0
                  }}元
                </van-col>
              </van-row>
              <van-row
                class="item-premium"
                type="flex"
                justify="space-between"
                align="center"
              >
                <van-col class="item-premium-name">意外险保费</van-col>
                <van-col class="item-premium-value">
                  {{
                    Object.keys(data).length
                      ? data[month].monthTotalPremiumDtail.accInsurePremium
                      : 0
                  }}元
                </van-col>
              </van-row>
              <van-row
                class="item-premium"
                type="flex"
                justify="space-between"
                align="center"
              >
                <van-col class="item-premium-name">健康险保费</van-col>
                <van-col class="item-premium-value">
                  {{
                    Object.keys(data).length
                      ? data[month].monthTotalPremiumDtail.heaInsurePremium
                      : 0
                  }}元
                </van-col>
              </van-row>
              <van-row
                class="item-premium"
                type="flex"
                justify="space-between"
                align="center"
              >
                <van-col class="item-premium-name">财产险保费</van-col>
                <van-col class="item-premium-value">
                  {{
                    Object.keys(data).length
                      ? data[month].monthTotalPremiumDtail.propInsurePremium
                      : 0
                  }}元
                </van-col>
              </van-row>
            </div>
          </popover>
          <input
            class="card-item-input input-month"
            :ref="pre + month"
            :readonly="displayType && pre === 'personal' ? true : false"
            @input="e => onMonthInputChange(e, month)"
            :class="index < newdate? 'disabledColor': '' "
            :disabled="index < newdate? true : false "
          />
        </div>
      </div>
    </div>
    <!-- <div class="card">
      <div class="card-title-container signature-pad-container">
        <div class="card-title-text">签名确认</div>
        <div class="card-title-right" @click="onSignaturePadClear">
          <van-icon name="replay" />
          <div class="card-title-right-name right-signature-pad">清除</div>
        </div>
      </div>
      <canvas
        id="canvasId"
        :ref="pre + 'sigCanvas'"
        class="signature-pad-canvas"
      ></canvas>
    </div> -->
  </div>
</template>

<script>
import { Switch, Toast } from "vant";
import SignaturePad from "signature_pad";

export default {
  name: "responsibility-input",
  components: {
    [Toast.name]: Toast,
    [Switch.name]: Switch
  },
  model: {
    prop: "data",
    event: "change"
  },
  props: {
    data: Object,
    pre: String,
    displayType: Boolean,
    activeTab: String,
    tabs: Array
  },
  data(){
    return {
    newdate: "",
    changeStatus: (this.tabs.length > 1 && JSON.parse(localStorage.getItem("originalList")) && JSON.parse(localStorage.getItem("originalList"))[1].changeStatus) || '',
    signStatus: (this.tabs.length > 1 && JSON.parse(localStorage.getItem("originalList")) && JSON.parse(localStorage.getItem("originalList"))[1].signStatus) || '',
    insuranceKind: ["车险保费", "意外险保费", "健康险保费", "财产险保费"],
    insuranceKindKey: [
      "carInsurePremium",
      "accInsurePremium",
      "heaInsurePremium",
      "propInsurePremium"
    ],
    months: [
      "janTotalPremium",
      "febTotalPremium",
      "marTotalPremium",
      "aprTotalPremium",
      "mayTotalPremium",
      "junTotalPremium",
      "julTotalPremium",
      "augTotalPremium",
      "sepTotalPremium",
      "octTotalPremium",
      "novTotalPremium",
      "decTotalPremium"
    ],
    yearTotalPremiumNumber: 0.0
    }
  },
  // data: () => ({
  //   insuranceKind: ["车险保费", "意外险保费", "健康险保费", "财产险保费"],
  //   insuranceKindKey: [
  //     "carInsurePremium",
  //     "accInsurePremium",
  //     "heaInsurePremium",
  //     "propInsurePremium"
  //   ],
  //   months: [
  //     "janTotalPremium",
  //     "febTotalPremium",
  //     "marTotalPremium",
  //     "aprTotalPremium",
  //     "mayTotalPremium",
  //     "junTotalPremium",
  //     "julTotalPremium",
  //     "augTotalPremium",
  //     "sepTotalPremium",
  //     "octTotalPremium",
  //     "novTotalPremium",
  //     "decTotalPremium"
  //   ],
  //   yearTotalPremiumNumber: 0.0
  // }),
  created() {},
  mounted() {
    //构造data
    // let data = { ...this.$props.data };
    let data 
    this.newdate = JSON.parse(localStorage.getItem("newdate"))
    console.log("newdate------->",this.newdate)
    let originalListSe = JSON.parse(sessionStorage.getItem("originalListSe"))
    if(originalListSe && originalListSe[this.pre] && originalListSe[this.pre].accInsurePremium ){
      data = originalListSe[this.pre]
    }else {
        if (JSON.parse(localStorage.getItem("originalList")).length == 1) {
          data = { ...JSON.parse(localStorage.getItem("originalList"))[0]};
      }else if(JSON.parse(localStorage.getItem("originalList")).length == 2){
          data = { ...JSON.parse(localStorage.getItem("originalList"))[0]};
          data = { ...JSON.parse(localStorage.getItem("originalList"))[1]};
      }
    }
    console.log(data);
    //年分类总保费目标
    this.insuranceKindKey.map(kind => {
      if (!data[kind]) {
        data[kind] = "";
      }
      this.$refs[this.pre + kind][0].value = data[kind].toLocaleString();
    });
    //年总保费计算
    this.yearTotalPremiumNumber = this.insuranceKindKey.reduce(
      (previous, current) => (previous += +data[current]),
      0
    );
    //月总保费及分类保费目标
    this.months.map(month => {
      if (!data[month]) {
        data[month] = "{}";
      }
      let monthPremiumJson;
      if (typeof data[month] === "string") {
        let monthPremiumString = data[month];
        monthPremiumJson = JSON.parse(monthPremiumString);
      } else if (typeof data[month] === "object") {
        monthPremiumJson = data[month];
      }
      let {
        monthTotalPremium = "",
        monthTotalPremiumDtail = {}
      } = monthPremiumJson;
      this.insuranceKindKey.map(kind => {
        if (!monthTotalPremiumDtail[kind]) {
          monthTotalPremiumDtail[kind] = "";
        }
      });
      if (typeof data[month] === "string") {
        data[month] = {
          monthTotalPremium,
          monthTotalPremiumDtail
        };
      }
      console.log(this.pre,"team or personal");
      this.$refs[
        this.pre + month
      ][0].value = monthTotalPremium.toLocaleString();
    });
    // let key = this.pre + 'sigCanvas'
    // let signaturePad = new SignaturePad(this.$refs[key], {
    //   backgroundColor: 'transparent',
    //   penColor: 'black'
    // })
    // var ratio = Math.max(window.devicePixelRatio || 1, 1)
    // this.$refs[key].width = this.$refs[key].offsetWidth * ratio
    // this.$refs[key].height = this.$refs[key].offsetHeight * ratio
    // this.$refs[key].getContext('2d').scale(ratio, ratio)
    // if (data.signaturePad) {
    //   signaturePad.fromData(data.signaturePad.toData())
    // }
    // data.signaturePad = signaturePad
    this.$emit("change", data);
    if(data.signStatus === "1") {
      this.data.isAverage = true
      this.onAverageChange(this.data.isAverage,data)
    }
  },
  computed: {
    yearTotalPremium: function() {
      return this.yearTotalPremiumNumber.toFixed(2).toLocaleString();
    }
  },
  methods: {
    onYearInputChange(e, key) {
      let data = { ...this.data };
      let value = e.target.value.replace(/,/g, "");
      // eslint-disable-next-line prettier/prettier
      if (!/^\d{0,8}$/.test(value)) {
        e.target.value = data[key];
      } else {
        data[key] = value;
      }
      let lastDot = data[key].substr(-1, 1) === ".";
      if(data[key]){
        e.target.value = (+data[key]).toLocaleString() + (lastDot ? "." : "");
      }
      this.yearTotalPremiumNumber = this.insuranceKindKey.reduce(
        (previous, current) => (previous += +data[current]),
        0
      );
      this.onAverageChange(this.data.isAverage, data);
    },
    onAverageChange(isAverage, data) {
      data = { ...(data || this.data) };
      if (isAverage) {
        let total = this.yearTotalPremiumNumber;
        // let startMonth = 0;
        // let endMonth = 12;
        // let months = endMonth - startMonth;
        // let averageValue = parseInt(total / months);
        // let lastVaue = averageValue + (total % months);
        let startMonth,averageValue,lastVaue;
        if(this.newdate){
          startMonth = this.newdate;
        }else{
          startMonth = 0;
        }
        let endMonth = 12;
        let months = endMonth - startMonth;
        if(months === 1){
          averageValue = total
          lastVaue = total
        }else { 
          averageValue = parseInt(total / months);
          lastVaue = averageValue + (total % months);
        }
        for (let month = 0; month < endMonth; month++) {
          let isLast = month === endMonth - 1;
          let monthTotalPremium = isLast ? lastVaue : averageValue;
          let monthTotalPremiumDtail = {};
          console.log(startMonth,'startMonth',this.yearTotalPremiumNumber)
          if(month < startMonth){
            monthTotalPremium = '0'
          }else{
            this.insuranceKindKey.map(key => {
              if (this.yearTotalPremiumNumber) {
                monthTotalPremiumDtail[key] = (
                  monthTotalPremium *
                  (+data[key] / this.yearTotalPremiumNumber)
                ).toFixed(2);
              } else {
                monthTotalPremiumDtail[key] = 0;
              }
            });
          }
          data[this.months[month]] = {
            monthTotalPremium,
            monthTotalPremiumDtail
          };
          this.$refs[this.pre + this.months[month]][0].value = (+monthTotalPremium).toLocaleString();
          // this.$refs[
          //   this.pre + this.months[month]
          // ][0].value = (+monthTotalPremium).toLocaleString();
        }
        data.isAverage = true;
      } else {
        data.isAverage = false;
      }
      let obj = JSON.parse(sessionStorage.getItem('originalListSe')) || {}
      obj[this.pre] = data
      console.log(this.data,"存值处");
      sessionStorage.setItem("originalListSe",JSON.stringify(obj))
      this.$emit("change", data);
    },
    onMonthInputChange(e, key) {
      let data = { ...this.data };
      let value = e.target.value.replace(/,/g, "");
      // eslint-disable-next-line prettier/prettier
      if (!/^\d{0,8}$/.test(value)) {
        e.target.value = data[key].monthTotalPremium;
      } else {
        data[key].monthTotalPremium = value;
      }
      data.isAverage = false;
      let monthTotalPremium = data[key].monthTotalPremium;
      let lastDot = monthTotalPremium.substr(-1, 1) === ".";
      e.target.value =
        (+monthTotalPremium).toLocaleString() + (lastDot ? "." : "");

      let monthTotalPremiumDtail = {};
      this.insuranceKindKey.map(key => {
        if (this.yearTotalPremiumNumber) {
          monthTotalPremiumDtail[key] =
            monthTotalPremium *
            (+data[key] / this.yearTotalPremiumNumber).toFixed(2);
        } else {
          monthTotalPremiumDtail[key] = 0;
        }
      });
      data[key] = {
        monthTotalPremium,
        monthTotalPremiumDtail
      };
      //校验
      let monthsTotalPremium = this.months.reduce(
        (previous, current) => (previous += +data[current].monthTotalPremium),
        0
      );
      if (monthsTotalPremium > this.yearTotalPremiumNumber) {
        // Toast.fail('月总保费大于年总保费!')
        let cha = monthsTotalPremium - this.yearTotalPremiumNumber;
        Toast.fail("各月度保费累加值比年度总保费多" + cha + "元，请调整一致");
      } else if (monthsTotalPremium < this.yearTotalPremiumNumber) {
        let cha = this.yearTotalPremiumNumber - monthsTotalPremium;
        Toast.fail("各月度保费累加值比年度总保费少" + cha + "元，请调整一致");
      }
      this.$emit("change", data);
    },
    onSignaturePadClear() { 
      this.data.signaturePad.clear();
    },
    getSignaturePadImageData() {
      if (this.data.signaturePad.isEmpty()) {
        return null;
      }
      return this.data.signaturePad.toDataURL("image/png");
    }
  }
};
</script>

<style lang="less" scoped>
.disabledColor {
  background-color:#F4F4F4 !important;
  color: #F4F4F4 !important;
}
.mark {
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
  background-color: transparent;
  opacity: 0.5;
  z-index: 999;
}
.card {
  margin: 15px 15px 12px;
  padding: 10px 17px 25px 20px;
  background: white;
  border-radius: 10px;
  div {
    display: flex;
    align-items: center;
  }
  .card-title-container {
    padding-bottom: 8px;
    border-bottom: 1px solid #66666616;
    justify-content: space-between;
  }
  .signature-pad-container {
    border-bottom: 0;
  }
  .card-title-text {
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #333333;
    line-height: 25px;
  }
  .card-title-right {
    // align-items: center;
  }
  .card-title-right-name {
    margin-right: 5px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
  }
  .right-month {
    color: #333333;
  }
  .right-signature-pad {
    color: #8e8e8e;
  }
  .card-title-right-value {
    font-size: 16px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: #3b8afe;
  }
  .card-items-container {
    width: 100%;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .card-item-container {
    width: 141px;
    padding-top: 15px;
    flex-direction: column;
    align-items: flex-start;
  }
  .item-month {
    flex-direction: row;
    justify-content: space-between;
    position: relative;
  }
  .card-item-title {
    margin-bottom: 7px;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: #999999;
    line-height: 12px;
  }
  .title-month {
    margin-bottom: 0;
    font-size: 14px;
    color: #3c69ab;
    align-self: center;
  }
  .card-item-input {
    width: 141px;
    height: 38px;
    border: 1px solid #dddddd;
    background: white;
    text-align: center;
    font-size: 14px;
    font-family: MicrosoftYaHei;
    color: #575756;
  }
  .input-month {
    width: 100px;
    height: 36px;
  }
  .signature-pad-canvas {
    width: 100%;
    height: 200px;
  }
}
.item-premium {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  line-height: 27px;
}
.item-premium-name {
  color: #787878;
}
.item-premium-value {
  color: #333333;
}
.vue-popover {
  // width: 110px !important;
  // left: 38px !important;
  // top: -28px !important;
  width: 165px !important;
  left: -20px !important;
  top: 53px !important;
}
.details-wrap {
  display: block !important;
  width: 100%;
  div {
    // display: block !important;
  }
}
</style>
