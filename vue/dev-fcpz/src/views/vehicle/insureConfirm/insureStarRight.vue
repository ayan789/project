<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-04-01 15:18:26
 * @Last Modified by: King
 * @Last Modified time: 2020-04-16 19:14:02
 * @FilePath: /src/views/vehicle/insureConfirm/insureStarRight.vue
-->
<template>
  <div class="starCover">
    <div class="topCover">
      <van-icon
        class="setPos"
        name="arrow-left"
        color="#4A4A4A"
        size="18px"
        @click="$router.go(-1)"
      />
    </div>
    <div>
      <star-equity :starInfo="starInfo" :isShare="true" />
    </div>
  </div>
</template>
<script>
import starEquity from "@/components/starEquity.vue";
import { mapState } from "vuex";
export default {
  components: {
    starEquity
  },
  data() {
    return {};
  },
  computed: {
    ...mapState("vehicle", {
      starInfo: ({ rightsInterests }) => rightsInterests
    })
  }
};
</script>
<style lang="less" scoped>
.starCover {
  background-color: white;
  overflow: scroll;
  .topCover {
    width: 100%;
    height: 173px;
    background: linear-gradient(
      180deg,
      rgba(174, 215, 255, 1) 0%,
      rgba(255, 255, 255, 1) 100%
    );
    .setPos {
      position: relative;
      left: 16px;
      top: 34px;
    }
  }
  /deep/.starEquityCover {
    margin-top: -130px;
    .tarItem {
      /deep/.tipName {
        top: 5px;
        padding-right: 0.1rem;
      }
    }
  }
  /deep/.headImg {
    margin-left: 23px;
  }
}
</style>
