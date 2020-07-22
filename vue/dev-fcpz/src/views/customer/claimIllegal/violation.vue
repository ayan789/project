<template>
  <div class="violation">
    <consultLiterature></consultLiterature>
    <div class="violationType">
      <ul>
        <li v-for="(item, index) in violationTypeList" :key="index">
          <div class="violationLight">
            <div class="violationGenre">违章类型</div>
            <div class="park">{{ item.park }}</div>
          </div>
          <div class="violationLotion">
            <div class="violationStop">违法停车</div>
            <div class="specificLocation">{{ item.specificLocation }}</div>
          </div>
          <div class="violationTime">
            <div class="violationDay">违法时间</div>
            <div class="specificTime">{{ item.specificTime }}</div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import consultLiterature from "@/components/consultLiterature";
export default {
  components: {
    consultLiterature
  },
  data() {
    return {
      violationTypeList: [
        {
          park: "违法停车",
          specificLocation: "杨高中路名生路路口",
          specificTime: "2019-01-10  18:00:00"
        },
        {
          park: "违法停车",
          specificLocation: "杨高中路名生路路口",
          specificTime: "2019-01-10  18:00:00"
        }
      ]
    };
  }
};
</script>
<style lang="less" scoped>
.violation {
  height: 609px;
  background: #fafafa;
  .violationType {
    margin: 0 15px;
    ul {
      li {
        margin-bottom: 15px;
        padding: 10px 15px;
        border-radius: 5px;
        background: #fff;
        .violationLight {
          height: 40px;
          .violationGenre,
          .park {
            line-height: 40px;
          }
          .violationGenre {
            font-size: 14px;
            color: rgba(51, 51, 51, 1);
            float: left;
          }
          .park {
            float: right;
            font-size: 13px;
            color: rgba(102, 102, 102, 1);
          }
        }
        .violationLotion {
          height: 40px;
          .violationStop,
          .specificLocation {
            line-height: 40px;
          }
          .violationStop {
            font-size: 14px;
            color: rgba(51, 51, 51, 1);
            float: left;
          }
          .specificLocation {
            color: #666666;
            float: right;
            font-size: 13px;
          }
        }
        .violationTime {
          height: 40px;
          .violationDay,
          .specificTime {
            line-height: 40px;
          }
          .violationDay {
            font-size: 14px;
            color: rgba(51, 51, 51, 1);
            float: left;
          }
          .specificTime {
            color: #666666;
            float: right;
            font-size: 13px;
          }
        }
      }
    }
  }
}
</style>
