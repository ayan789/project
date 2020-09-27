<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>
	<div class="div_v1">
		<div>
			<p class="tb_qrs">
				<span>投保确认书</span>
			</p>
		</div>
		<div>
			<p>
				<span>投保单号：</span>${INSURE_NO!}
			</p>
		</div>
		${TouBaoXuZhi!}
	</div>

	<div class="div_v1">
		<div>
			<p>
				<span>一、</span><span>基本信息</span>
			</p>
			<p>
				<span>投保人名称：</span>${APPLICANT_NAME!}&#160;&#160;&#160;&#160;<span>证件类型：</span>${APPLIIDENTIFY_TYPE!}&#160;&#160;&#160;&#160;<span>证件号：</span>${APPLI_IDENTIFY!}
			</p>
			<!-- 判断被保险人循环增加 -->
			<p>
				<span>被保险人 1：</span>${INSURED_NAME!}&#160;&#160;&#160;&#160;<span>证件类型：</span>${INSUREDIDENTIFY_TYPE!}&#160;&#160;&#160;&#160;<span>证件号：</span>${INSURED_IDENTIFY!}
			</p>
		</div>
	<div class="div_v1">
			<p>
				<span>保险期间：</span>${START_DATE!}
			</p>
	</div>
		<div>
			<p>
				<span>二、</span><span>保险责任</span>
			</p>
			<p>
				<span>保障计划：</span>
			</p>
				<table class="tb_table">
					<tr class="tb_table">
						<th align="center" class="tb_table" width="250px">条款名称</th>
						<th align="center" class="tb_table" width="180px">保险责任</th>
						<th align="center" class="tb_table" width="120px">每人保额（元）</th>
					</tr>
				    <#if list??>
				    <#list list as ps>
				        <#if (ps_index+1) % 3 ==1><tr class="tb_table"></#if>
				        	<#list ps as la>
				            <td align="center" class="tb_table">${la}</td>
				            </#list>
				        <#if (ps_index+1) % 3 == 0> </tr></#if>
				    </#list>
				    </#if>
				</table>
			<p>
				<span>总保费：</span>${SUMPREMIUM!}
			</p>
		</div>
		${JianKangGaoZhi!} ${TouBaoShengMing!}
	</div>
	<#if JianKangGaoZhi??>
	<div class="div_v1">
		<div>
			<p  class = "tb_is">
				<img class="icon_image" src="${IImage}"></img><span>是&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span> 
				<img class="icon_image" src="${IcImage}"></img><span class = "tb_is">否</span>
			</p>
		</div>
		<div>
			<p>
				<img class="icon_image" src="${IconImage}"></img><span>本人确认以上填写的各项内容均真实，未发生健康与职业声明中所有情形；日后如发现有不实告</span>
			</p>
		</div>
		<div>
			<p>
				<span>知，保险公司有权按《保险法》等有关法律规定解除或部分解除保险合同。</span>
			</p>
		</div>
	</div>
	</#if>
	<div>
		<p class="tb_qm">
			<span>投保人签名：</span>
		</p>
	</div>
	<div>
		<p class="tb_qm_t">
			<img class="sing_image" src="${signImage}"></img>
		</p>
	</div>
	<div>
		<p class="tb_dat">
			<span>日期：</span>${CREATE_DATE}
		</p>
	</div>
</body>
</html>