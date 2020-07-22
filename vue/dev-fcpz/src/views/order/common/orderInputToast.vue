<template>
  <div class="super">
    <van-cell is-link @click="showpopup">展示输入框</van-cell>
    <van-popup
      v-model="show"
      :style="{
        height: '200px',
        width: 'calc(100% - 50px)',
        borderRadius: '8px'
      }"
      :overlay="true"
    >
      <orderInput @saveEvent = 'saveEvent' @cancel='cancel'/>
    </van-popup>
  </div>
</template>
<script>
import orderInput from "@/components/orderInput.vue";
export default {
  name: "orderInputToast",
  components: {
    orderInput
  },
  data() {
    return {
      show: false
    };
  },
  methods: {
    showpopup() {
      this.show = !this.show;
    },
    saveEvent(value) {
      this.show = !this.show;
      //console.log(value);
    },
    cancel() {
      this.show = !this.show;
    }
  }
};
</script>
