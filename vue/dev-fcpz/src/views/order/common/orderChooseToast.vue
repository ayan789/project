<template>
  <div class="super">
    <van-cell is-link @click="showpopup">展示弹出层</van-cell>
    <van-popup v-model="show" round :style="{ height: '624px',width:'100%' }" position="bottom" :overlay="true">
      <orderSelect v-on:selectSure = 'selectSure'/>
    </van-popup>
  </div>
</template>
<script>
//订单列表首页筛选弹窗：页面调用示例代码
import orderSelect from '@/components/orderSelect.vue';
export default {
  name: "orderChooseToast",
  components: {
    orderSelect
  },
  props: {},
  data() {
    return {
      show: false,
    };
  },
  methods: {
    showpopup: function() {
      this.show = !this.show;
    },
    //确定点击事件
    selectSure: function(params) {
      this.show = !this.show;
     //console.log(params);
    }
  }
};
</script>

