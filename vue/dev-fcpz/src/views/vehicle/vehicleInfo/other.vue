<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-03-28 15:37:19
 * @Last Modified by: King
 * @Last Modified time: 2020-04-02 11:57:26
 * @FilePath: /src/views/vehicle/vehicleInfo/other.vue
-->
<template>
  <div class="cover">
    <van-row class="carInfoCard">
      <h3 class="carId">沪A88888</h3>
      <p class="commonText14">荣威CSA7180AB-DL轿车</p>
    </van-row>
    <!-- 车辆信息 -->
    <div class="cardItem">
      <van-cell title="车辆信息">
        <template slot="icon">
          <img width="22px" height="22px" style="margin-right:8px" src="../../../assets/vehicle/vehicleInfo/vehicle.png" />
        </template>
        <template #right-icon>
          <img width="18px" height="18px" src="../../../assets/vehicle/vehicleInfo/edit.png" />
        </template>
      </van-cell>
      <p class="contentItem">
        <span class="commonTit14">车架号</span>
        <span class="commonText14">9090909090</span>
      </p>
      <p class="contentItem">
        <span class="commonTit14">初登日期</span>
        <span class="commonText14">2020/01/20</span>
      </p>
      <p class="contentItem">
        <span class="commonTit14">发动机号</span>
        <span class="commonText14">111111</span>
      </p>
      <p class="tip">更多信息<van-icon size="10px" color="rgb(191,191,191)" style="margin-left:6px" name="arrow" /></p>
    </div>
    <!-- 车主信息 -->
    <div class="cardItem">
      <van-cell title="车主信息">
        <template slot="icon">
          <img width="22px" height="22px" style="margin-right:8px" src="../../../assets/vehicle/vehicleInfo/carowner.png" />
        </template>
        <template #right-icon>
          <img width="18px" height="18px" src="../../../assets/vehicle/vehicleInfo/edit.png" />
        </template>
      </van-cell>
      <p class="contentItem">
        <span class="commonTit14">姓名</span>
        <span class="commonText14">xxx</span>
      </p>
      <p class="contentItem">
        <span class="commonTit14">证件类型</span>
        <span class="commonText14">身份证</span>
      </p>
      <p class="contentItem">
        <span class="commonTit14">证件号码</span>
        <span class="commonText14">111111</span>
      </p>
    </div>
    <!-- 投保方案 -->
    <div class="cardItem">
      <van-cell title="投保方案">
        <template slot="icon">
          <img width="22px" height="22px" style="margin-right:8px" src="../../../assets/vehicle/vehicleInfo/plan.png" />
        </template>
        <template #right-icon>
          <img width="18px" height="18px" src="../../../assets/vehicle/vehicleInfo/edit.png" />
        </template>
      </van-cell>
      <!-- 交强险保险期限 -->
      <div class="limitDateItem">
        <div class="topDate">
          <p class="commonTit14">交强险保险期限</p>
          <p class="commonText12">2019/01/10 00:00 - 2019/01/10 00:00</p>
        </div>
        <div class="midInfo">
          <p class="lineItem">
            <span class="commonText12 color8E8E8E">险种</span>
            <span class="commonText12 color8E8E8E">保费(元)</span>
          </p>
          <p class="lineItem">
            <span class="commonText14 color545454">交强险</span>
            <span class="commonText14 color545454">900.00</span>
          </p>
          <p class="lineItem">
            <span class="commonText14 color545454">车船险</span>
            <span class="commonText14 color545454">400.00</span>
          </p>
        </div>
      </div>
      <!-- 商业险保险期限 -->
      <div class="limitDateItem">
        <div class="topDate">
          <p class="commonTit14">商业险保险期限</p>
          <p class="commonText12">2019/01/10 00:00 - 2019/01/10 00:00</p>
        </div>
        <div class="midInfo">
          <p class="lineItem">
            <span class="commonText12 color8E8E8E" style="width:30%">险种</span>
            <span class="commonText12 color8E8E8E">保额(万元)</span>
            <span class="commonText12 color8E8E8E">保费(元)</span>
          </p>
          <p class="lineItem">
            <span class="commonText14 color545454" style="width:30%">车辆损失险</span>
            <span class="commonText14 color545454">50.00</span>
            <span class="commonText14 color545454">900.00</span>
          </p>
          <p class="lineItem">
            <span class="commonText14 color545454" style="width:30%">第三者责任险</span>
            <span class="commonText14 color545454">50.00</span>
            <span class="commonText14 color545454">400.00</span>
          </p>
          <van-dropdown-menu style="background:#FAFAFA">
            <van-dropdown-item>
              <template slot="default">
                <div>
                  <p>more info</p>
                </div>
              </template>
            </van-dropdown-item>
          </van-dropdown-menu>
        </div>
        <!-- 金额 -->
        <van-cell>
          <template slot="title">
            <span class="commonText14" style="font-weight:bold">合计保费(元)</span>
          </template>
          <template slot="default">
            <span class="size16 colorFC820E commonText14">300,000.00</span>
          </template>
        </van-cell>
      </div>
    </div>
    <!-- 特别约定 -->
    <div class="cardItem">
      <van-cell title="特别约定">
        <template slot="icon">
          <img width="22px" height="22px" style="margin-right:8px" src="../../../assets/vehicle/vehicleInfo/appoint.png" />
        </template>
        <template #right-icon>
          <van-icon name="arrow" color="#BFBFBF" size="16px" />
        </template>
      </van-cell>
      <div class="limitDateItem">
        <div class="midInfo">
          <p class="lineItem">
            <span class="commonText12 color8E8E8E">特约名称</span>
            <span class="commonText12 color8E8E8E">特约内容</span>
          </p>
          <p class="lineItem">
            <span class="commonText14 color545454">新增设备特别约定</span>
            <span class="commonText14 color545454">特约内容AAAAAA</span>
          </p>
          <p class="lineItem">
            <span class="commonText14 color545454">新增设备特别约定</span>
            <span class="commonText14 color545454">特约内容BBBBB</span>
          </p>
        </div>
      </div>
    </div>
    <!-- 反洗钱信息 -->
    <div class="cardItem">
      <van-cell title="反洗钱信息">
        <template slot="icon">
          <img width="22px" height="22px" style="margin-right:8px" src="../../../assets/vehicle/vehicleInfo/anti.png" />
        </template>
        <template #right-icon>
          <van-icon name="arrow" color="#BFBFBF" size="16px" />
        </template>
      </van-cell>
    </div>
    <!-- 星级权益 -->
    <div class="cardItem" style="margin-bottom:62px">
      <van-cell title="星级权益">
        <template slot="icon">
          <img width="22px" height="22px" style="margin-right:8px" src="../../../assets/vehicle/vehicleInfo/rightsinterests.png" />
        </template>
        <template #right-icon>
          <van-icon name="arrow" color="#BFBFBF" size="16px" />
        </template>
      </van-cell>
      <div class="limitDateItem">
        <div class="midInfo">
          <div class="lineItem" style="padding: 0 40px">
            <div class="starPart">
              <img v-for="i in 3" style="width:20px" :key="i" src="../../../assets/vehicle/vehicleInfo/star.png" alt="" />
            </div>
            <span class="commonText14 color3B8AFE size18">4394</span>
          </div>
          <p class="lineItem" style="padding: 0 10px 0 40px">
            <span class="commonText12 color8E8E8E">出单后星级</span>
            <span class="commonText12 color8E8E8E">出单后所获得积分</span>
          </p>
        </div>
      </div>
    </div>
    <!-- 底部按钮 -->
    <div class="bottomPart">
      <p class="defaultBtn">商业计划书</p>
      <p class="submitBtn">提交订单</p>
    </div>
  </div>
</template>

<style scoped>

// 通用部分
// 颜色
.color545454 {
  color: #545454 !important;
}
.color3B8AFE {
  color: #3b8afe !important;
}
.color8E8E8E {
  color: #8e8e8e !important;
}
.colorFC820E {
  color: #fc820e !important;
}
// 字号
.size16 {
  font-size: 16px !important;
}
.size18 {
  font-size: 18px !important;
}
// 字体
.commonTit14 {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 23px;
}
.commonText12 {
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(153, 153, 153, 1);
  line-height: 17px;
}
.commonText14 {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(68, 68, 68, 1);
  line-height: 20px;
}
.carInfoCard {
  position: absolute;
  top: 106px;
  width: 50%;
  z-index: 9;
  background: white;
  padding-left: 18px;
  .carId {
    font-size: 20px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(0, 0, 0, 1);
  }
}
.cardItem {
  background-color: white;
  padding-top: 5px;
  margin-bottom: 10px;
  padding-bottom: 15px;
  .tip {
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(76, 118, 181, 1);
    line-height: 17px;
    text-align: center;
  }
  .contentItem {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding-left: 21px;
    padding-right: 15px;
    span {
      display: inline-block;
    }
  }
  .limitDateItem {
    padding: 0 21px;
    margin-bottom: 20px;
    .topDate {
      margin-bottom: 12px;
    }
    .midInfo {
      padding: 7px 17px;
      background: #fafafa;
      border-radius: 2px;
      .lineItem {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 8px;
        span {
          display: inline-block;
        }
      }
    }
  }
}
.bottomPart {
  position: fixed;
  bottom: 0;
  // height: 46px;
  width: 100%;
  display: flex;
  align-content: center;
  vertical-align: middle;
  text-align: center;
  font-size: 16px;
  .defaultBtn {
    padding: 12px 0;
    width: 167px;
    background-color: white;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(59, 138, 254, 1);
  }
  .submitBtn {
    padding: 12px 0;
    width: 100%;
    color: white;
    background: linear-gradient(
      125deg,
      rgba(42, 128, 255, 1) 0%,
      rgba(42, 165, 255, 1) 100%
    );
  }
}
</style>