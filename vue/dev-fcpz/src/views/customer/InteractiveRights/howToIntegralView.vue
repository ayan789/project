<template>
  <div class="queryRemindView">
    <Global_globalNav title="积分明细" @clickBack="clickBack"/>
    <div class="contentStyle">
      中国大地保险个人客户享权计划说明
      <br>1、什么是大地客户享权计划？
      <br>中国大地保险为个人客户提供星级享权服务计划，任何个人客户注册后均可获取唯一账户，根据客户星级提供相应的服务权益。
      <br>2、如何确定您的初始星级？
      <br>您可通过多种方式获得初始星级（如购买保险产品、签到奖励、参与公众号活动等），并根据积分区间对应相应星级，获得差异化的增值服务。本积分不可消费提现。
      <br>3、如何快速提升您的星级？
      <br>我们推出多个积分奖励政策，帮助您快速提升积分，提高星级：
      <br>（1）家用车客户，首次购买其他险类产品最高可获6倍积分奖励；
      <br>（2）购买车险以外保险产品最高可获2倍积分奖励；
      <br>（3）未出险客户可按月获得积分奖励，最高可获保费收入积分翻倍奖励，出险后根据出险次数积分奖励递减；
      <br>（4）同一产品续保客户、补充完善个人信息、定期签到均可获额外积分奖励；
      <br>（5）关注公众号，参与定期/不定期的公众号互动获取奖励积分。
      <br>4、客户星级有效期限是多长时间？
      <br> 您的星级有效期为2年（积分发生变化时，重新计算星级），您的积分有效期为2年。
      <br>注：保费类积分按保费金额计算积分，只适用投保人、被保人均为个人的家庭自用车保单客户和非车险保单客户。
    </div>
  </div>
</template>

<script>
export default {
  name: "howToIntegralView",
  methods: {
    clickBack() {
      this.$router.back();
    },
  }
};
</script>

<style scoped>
.queryRemindView {
  position: fixed;
  width: 100%;
}

.contentStyle {
  background: #F6F6F6;
  margin-top: 80px;
  overflow: scroll;
  height: calc(100vh - 65px);
  display: flex;
  padding: 0 25px;
  flex-direction: column;
}
</style>
