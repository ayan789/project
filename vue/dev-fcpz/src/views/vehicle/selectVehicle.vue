<template>
  <div>
    <van-nav-bar title="请选择车辆类型" left-arrow @click-left="goBack" />
    <div class="vehicle-block">
      <div @click="goVehicle('jyc')">家用车</div>
      <div @click="goVehicle('hkg')">货车/客车/挂车</div>
      <div @click="goVehicle('mts')">摩托车/拖拉机/三轮汽车</div>
      <div @click="goVehicle('tzc')">特种车</div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  name: "selectVehicle",
  data() {
    return {};
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      control: ({ control }) => control,
      car: ({ car }) => car,
      vehicleType: ({ vehicleType }) => vehicleType
    })
  },
  mounted() {
    console.log(this.vehicleType);
  },
  methods: {
    ...mapMutations("vehicle", ["setVehicleType", "setCarInfo"]),
    goBack() {
      this.$router.push({ path: "/" });
    },
    goVehicle(type) {
      let vehicleType = "";
      switch (type) {
        case "jyc":
          vehicleType = "1";
          break;
        case "hkg":
          vehicleType = "2";
          break;
        case "mts":
          vehicleType = "3";
          break;
        case "tzc":
          vehicleType = "4";
          break;
        default:
          break;
      }
      this.$router.push({
        path: "/vehicle",
        query: { vehicleType }
      });
      // this.$router.push({path: "/vehicle?vehicleType=" + vehicleType});
    }
  }
};
</script>

<style lang="less" scoped>
.vehicle-block {
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
  div {
    width: 80px;
    height: 80px;
    font-size: 14px;
    border-radius: 50%;
    border: 1px solid #ccc;
    text-align: center;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    display: flex;
  }
}
</style>
