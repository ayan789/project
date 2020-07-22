
<!-- 百万医疗个人版保险条款- 附加扩展恶性肿瘤特需医疗保险条款 -->
<template>
  <div id="safeguard-detail" sticky>
    <globalNav :title="globalNav.title" @clickBack="backToPrePage"></globalNav>
    <div class="title-container content" style="padding:13% 6%;">
      <h3>中国大地财产保险股份有限公司<br />中国大地保险个人医疗保险（C 款）附加扩展恶性肿瘤特需医疗保险条款
				<br/>【注册编号：C00001032522019122304412】
			</h3>

			<h4>总则</h4>
			<p><b>第一条</b>　本保险是中国大地保险个人医疗保险（C 款）（以下简称“主险”）的附加险。</p>
			<p>凡主险合同内容与本附加合同相关的部分，以及本附加保险条款，均为本附加合同的构成部分。</p>
			<p>本附加保险条款未尽事项，以主险保险条款为准；若本附加保险条款与主险保险条款内容冲突，则以本附加保险条款为准。</p>
			<p>主险合同效力终止，本附加合同效力亦同时终止。</p>
			<p><b>第二条</b>　本附加险的投保人、被保险人同主险。</p>

			<h4>保险责任</h4>
			<p><b>第三条</b>　本附加险在主险的重大疾病医疗保险责任项下扩展承保被保险人因恶性肿瘤在特定的医疗机构接受治疗，由此发生的必要的、合理的主险保险条款第五条第（一）至（四）项列明的医疗费用。</p>

			<h4>责任免除</h4>
			<p><b>第四条　本附加险条款的“责任免除”同主险中的“责任免除”，除非为本附加合同所承保的。</b></p>

			<h4>释义</h4>
			<p><b>特定的医疗机构：</b>指中华人民共和国境内（不包括港澳台地区）依法经营的二级以上（含）公立医院的特需医疗部或VIP 部。该医疗机构具有符合有关医院管理规定设置标准的医疗设备，并且有合格医师及护士提供二十四小时医疗和护理等服务。<b>保险人有权对部分特定的医疗机构予以调整，但以不损害被保险人的利益为限。上述调整以保险人在官方正式渠道（包括但不限于官网）的通知为准。</b></p>
	
	  </div>
  </div>
</template>

<script>
import { NavBar } from "vant";
import globalNav from "../../../components/globalNav";
export default {
  name: "personalTerms_AdditionalExtensions",
  components: {
    [NavBar.name]: NavBar,
    [globalNav.name]: globalNav
  },
  data: () => ({
    appoint: "1",
    globalNav: {
      title: "保险条款"
    },
  }),

  methods: {
    backToPrePage() {
      this.$globalBack();
    },
  }
};
</script>

<style scoped>
#safeguard-detail {
  background-color: #f7f7f7;
  /* overflow: hidden; */
}
#tab-content {
  padding-top: 10px;
}
#safeguard-detail {
  background: white;
}
.title-container {
  padding: 12px;
  margin: 32px 0 10px 0;
  background-color: white;
  /* width: 96%; */
}
.item-title {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #333333;
}
.coverage-content {
  width: 100%;
}
.espent {
  width: 90%;
  line-height: 35px;
  border-bottom: 1px solid gainsboro;
}
.coverage {
  width: 90%;
  line-height: 40px;
  color: rgb(102, 102, 172);
  font-size: 16px;
  border-bottom: 1px solid gainsboro;
}
 .content{background: #fff}
	.content>div{margin:3px;}
	h3{font-size: 14px;text-align:center;}
	h4{font-size: 14px;text-align:center;}
	p{font-family:YouYuan;font-size:12px;}
	b{font-weight: 700;}
	table,table tr th, table tr td { 
		border:1px solid black;
		font-size: 12px;
		text-align: center;
	}
</style>
