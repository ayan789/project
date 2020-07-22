<template>
  <div>
    <van-field
      readonly
      clickable
      :value=" lnfa=='T' ? insuredAgesInfo2.insuredAgesTypeVal : insuredAgesInfo.insuredAgesTypeVal"
      placeholder="请选择"
      @click="lnfa=='T' ? insuredAgesInfo2.insuredAgesPicker = true : insuredAgesInfo.insuredAgesPicker = true "
      input-align="right"
    >
      <div slot="label">
        <span>被保人{{ num }}年龄</span>
      </div>
      <div
        slot="right-icon"
        class="rightCenter"
      >
        <van-icon
          name="arrow"
          class="grayBf"
        />
      </div>
    </van-field>
    <van-popup
      v-model="insuredAgesInfo.insuredAgesPicker"
      position="bottom"
      v-if="!lnfa"
    >
      <van-picker
        show-toolbar
        :columns="insuredAgesInfo.insuredAgestype"
        @cancel="insuredAgesInfo.insuredAgesPicker = false"
        @confirm="insured_onConfirm"
      />
    </van-popup>
    <van-popup
      v-model="insuredAgesInfo2.insuredAgesPicker"
      position="bottom"
      v-else
    >
      <van-picker
        show-toolbar
        :columns="insuredAgesInfo2.insuredAgestype"
        @cancel="insuredAgesInfo2.insuredAgesPicker = false"
        @confirm="insured_onConfirm"
      />
    </van-popup>
  </div>
</template>
<script>
export default {
  name: "agePicker",
  props: {
    num: {
      type: [Number, String],
      default: ""
    },
    lnfa: {
      type: String
    },
    defaultInsuredAgesVal: String
  },
  created () {
    this.insuredAgesInfo.insuredAgesTypeVal =
      (this.$props.defaultInsuredAgesVal || "0-4 ") + "岁";
  },
  data () {
    return {
      appliIdentity: {
        "11": "本人",
        "12": "配偶",
        "13": "父母",
        "14": "子女",
        "15": "其他近亲属",
        "16": "雇佣或劳务",
        "17": "其他"
      },
      insuredAgesInfo: {
        // 被保人年龄信息
        insuredAgestype: [
          "0-4岁",
          "5-10岁",
          "11-15岁",
          "16-20岁",
          "21-25岁",
          "26-30岁",
          "31-35岁",
          "36-40岁",
          "41-45岁",
          "46-50岁",
          "51-55岁",
          "56-60岁"
        ],
        insuredAgesPicker: false,
        insuredAgesTypeVal: "0-4岁"
      },
      // lnfa
      insuredAgesInfo2: {
        // 被保人年龄信息
        insuredAgestype: [
          "45岁",
          "46-50岁",
          "51-55岁",
          "56-60岁",
          "61-65岁",
          "66-70岁",
          "71-75岁",
          "76-80岁"
          // "81-85岁",
          // "86-90岁",
          // "91-95岁",
          // "96-100岁"
        ],
        insuredAgesPicker: false,
        insuredAgesTypeVal: "45岁"
      }
    };
  },
  methods: {
    insured_onConfirm (value, index) {
      if (!this.lnfa) {
        this.insuredAgesInfo.insuredAgesTypeVal = value;
        this.$emit(
          "changeAges",
          index + 1,
          this.num,
          value.substring(0, value.length - 1)
        );
        this.insuredAgesInfo.insuredAgesPicker = false;
      } else {
        this.insuredAgesInfo2.insuredAgesTypeVal = value;
        this.$emit(
          "changeAges",
          index + 1,
          this.num,
          value.substring(0, value.length - 1)
        );
        this.insuredAgesInfo2.insuredAgesPicker = false;
      }
    }
  }
};
</script>
<style lang="less" scoped></style>
