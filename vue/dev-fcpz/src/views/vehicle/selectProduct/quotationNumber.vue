<!--
 * @Author: heweiwei
 * @Page: 报价次数
 * @Date: 2020-03-04 14:28:10
 * @Last Modified by: King
 * @Last Modified time: 2020-04-30 13:26:50
 * @FilePath: /src/views/vehicle/selectProduct/quotationNumber.vue
-->
<template>
  <div class="quotationNumber">
    <nav-bar type="vehicle" title="报价次数" @clickBack="$router.back()" />
    <div class="container">
      <ul>
        <li>
          <div class="quotationLeft">
            <span>交强险</span>
          </div>
          <div class="quotationRight">
            <div class="upperLimit">
              <span>报价次数上限</span>
              <span class="upperLimitcount">
                {{ quoteCount.compulsoryQuoteMaxNum || "0" }}次
              </span>
            </div>
            <div class="quotedPric">
              <span>已报价次数</span>
              <span class="quotedPricount">
                {{ quoteCount.compulsoryQuoteNum || "0" }}次
              </span>
            </div>
          </div>
        </li>
        <li>
          <div class="quotationLeft">
            <span>商业险</span>
          </div>
          <div class="quotationRight">
            <div class="upperLimit">
              <span>报价次数上限</span>
              <span class="upperLimitcount">
                {{ quoteCount.businessQuoteMaxNum || "0" }}次
              </span>
            </div>
            <div class="quotedPric">
              <span>已报价次数</span>
              <span class="quotedPricount">
                {{ quoteCount.businessQuoteNum || "0" }}次
              </span>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      businessQuoteMaxNum: "0", //商业险报价次数限制
      businessQuoteNum: "0", //商业险已报价次数
      compulsoryQuoteMaxNum: "0", //交强险报价次数
      compulsoryQuoteNum: "0", //交强险已报次数
      sumQuoteCount: "0" //总报价次数（交商报价次数之和）
    };
  },
  computed: {
    ...mapState("vehicle", {
      quoteCount: ({ quoteInfo: { quoteCount } }) => quoteCount
    })
  }
};
</script>
<style lang="less" scoped>
.quotationNumber {
  .container {
    height: calc(100% - 77px);
    overflow: scroll;
    -webkit-overflow-scrolling: touch;
    overflow-scrolling: touch;
    background: white;
    ul {
      li {
        position: relative;
        padding: 17px 0px 17px 18px;
        background: #fff;
        overflow: hidden;
        .quotationLeft {
          float: left;
          width: 66px;
          height: 66px;
          line-height: 66px;
          background: rgba(235, 243, 255, 1);
          border-radius: 50%;
          margin-right: 16px;
          span {
            font-size: 14px;
            font-weight: 500;
            display: block;
            color: rgba(59, 138, 254, 1);
            text-align: center;
          }
        }
        .quotationRight {
          margin-top: 5px;
          padding-left: 20px;
          float: left;
          .upperLimit,
          .quotedPric {
            padding-top: 2px;
            span {
              height: 20px;
              font-size: 14px;
              color: rgba(51, 51, 51, 1);
              line-height: 20px;
            }
            .upperLimitcount {
              margin-left: 20px;
            }
            .quotedPricount {
              margin-left: 34px;
            }
          }
          .upperLimit {
            margin-bottom: 10px;
          }
        }
      }
      li::after {
        position: absolute;
        box-sizing: border-box;
        content: " ";
        pointer-events: none;
        right: 20px;
        bottom: 0;
        left: 20px;
        border-bottom: 1px solid #ebedf0;
        -webkit-transform: scaleY(0.5);
        transform: scaleY(0.5);
      }
    }
  }
}
</style>
