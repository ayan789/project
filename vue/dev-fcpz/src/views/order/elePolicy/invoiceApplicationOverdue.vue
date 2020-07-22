<!--
 * @Description: 发票链接过期
 * @Date: 2020-03-31 10:47:47
 * @Author: zhangminjie
 * @LastEditTime: 2020-03-31 11:53:05
 * @FilePath: \vue-sales\src\views\order\elePolicy\invoiceApplicationOverdue.vue
 -->
<template>
  <div class="invoiceApplicationOverdue" id="invoiceApplicationOverdue">
    <globalNav title="发票详情" @clickBack="backToPrePage" />
    <div class="top">
      <img :src="imgSrc" alt="top" />
    </div>
    <p>
      您当前使用的链接已过期。如需查询下载，可以到公司官网（www.95590.cn）进行操作。
    </p>
    <p>如需帮助，请点击“智能客服”按钮进行咨询</p>
  </div>
</template>
<script>
import { Overlay, Divider } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  components: {
    [Overlay.name]: Overlay,
    [Divider.name]: Divider,
    [globalNav.name]: globalNav
  },
  data() {
    return {
      imgSrc: require("@/assets/order/elePolicy/invoiceTip.png")
    };
  },
  created() {},
  methods: {
    // 返回上一页
    backToPrePage() {
      this.$globalBack();
    }
  }
};
</script>
<style lang="less" scoped>
#invoiceApplicationOverdue {
  padding-top: 150px;
  background: rgba(255, 255, 255, 1);
  div {
    box-sizing: border-box;
  }
  .top {
    width: 140px;
    height: 135px;
    margin: 0 auto;
    img {
      width: 100%;
      height: auto;
    }
  }
  p {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
    padding: 0 35px;
    margin-top: 40px;
  }
  p:last-child {
    margin-top: 30px;
  }
}
</style>
