<template>
  <div>
    <van-field
      readonly
      clickable
      name="area"
      :value="value"
      label="地区选择"
      placeholder="点击选择省市区"
      required
      @click="showArea = true"
    />
    <van-popup
      v-model="showArea"
      position="bottom"
    >
      <van-area
        :area-list="areaList"
        @confirm="onConfirm"
        @cancel="showArea = false"
      />
    </van-popup>
  </div>
</template>

<script>
import areaList from "@src/common/json/areaY.js";

export default {
  data () {
    return {
      value: "",
      showArea: false,
      areaList: areaList,
      addressInfo: {
        provinceCode: "",
        prefectureCode: "",
        countyCode: "",
        comcode: "",
        addressName: ""
      }
    };
  },
  created () { },
  methods: {
    onConfirm (values) {
      this.value = values.map(item => item.name).join("");
      this.showArea = false;
      for (let value of values) {
        if (value.code.length == 8) {
          this.addressInfo.comcode = values[0].code;
        } else if (value.code.length == 4) {
          this.addressInfo.prefectureCode = values[1].code;
        } else if (value.code.length == 6) {
          this.addressInfo.countyCode = values[2].code;
        }
        this.addressInfo.provinceCode = values[0].code.substring(0, 2);
      }
      if (this.addressInfo.provinceCode == "71" || this.addressInfo.provinceCode == "81" || this.addressInfo.provinceCode == "82") {
        this.addressInfo.comcode = "31090008"
        this.addressInfo.prefectureCode = ""
        if (this.addressInfo.provinceCode == "71") {
          this.addressInfo.countyCode = "710000"
        }
        if (this.addressInfo.provinceCode == "81") {
          this.addressInfo.countyCode = "810000"
        }
        if (this.addressInfo.provinceCode == "82") {
          this.addressInfo.countyCode = "820000"
        }
      }
      if (this.addressInfo.provinceCode == "54") {
        this.addressInfo.comcode = "31090008"
      }
      this.addressInfo.addressName = values.map(item => item.name).join("");
      this.$emit('addressInfo', this.addressInfo)
    }
  }
};
</script>

<style>
</style>