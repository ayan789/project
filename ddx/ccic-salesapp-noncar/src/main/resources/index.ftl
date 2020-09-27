<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
</head>
<body>
<div class = "div_01">
	<div class="div_v01">
		<div>
			<p class="tb_qrs">
				<h3>投保确认书</h3>
			</p>
		</div>
	</div>
	<div class="div_v1">
		<div>
			<p>
				<span>投保单号：</span>${INSURE_NO!}
			</p>
		</div>
	</div>

	<div class="div_v1">
		<div>
			<p>
				<h3><span class = "jb_mes">一、</span><span  class = "jb_mes">基本信息</span></h3>
			</p>
			<!-- 判断被保险人循环增加 -->
			<p>
					<table>
					<#if APPLICANTList??>
				    <#list APPLICANTList as noncarOrderAPPLICANTList>
				   			<tr class = "ins">
				   				<td class = "ins"><span>投保人名称：</span>${noncarOrderAPPLICANTList.customerName!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件类型：</span>${noncarOrderAPPLICANTList.idType!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件号：</span>${noncarOrderAPPLICANTList.idNo!}<br/></td>
				    		</tr>
				    </#list>
				    </#if>
			 		<#if INSUREDList??>
				    <#list INSUREDList as noncarOrderInsuredList>
				   			<tr class = "ins">
				   				<td class = "ins"><span>被保险人
				   				<#if (INSUREDList?size>1)>
				   					${noncarOrderInsuredList_index+1}：</span>${noncarOrderInsuredList.customerName!}&#160;&#160;&#160;&#160;</td>
				   				<#else>
				   					&#160;：</span>${noncarOrderInsuredList.customerName!}&#160;&#160;&#160;&#160;</td>
				   				</#if>
				   				<td class = "ins"><span>证件类型：</span>${noncarOrderInsuredList.idType!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件号：</span>${noncarOrderInsuredList.idNo!}<br/></td>
				    		</tr>
				    </#list>
				    </#if>
				    </table>
				    <!--  联络人 -->
				    <#if INSUREDROLEList??>
				    <#list INSUREDROLEList as noncarOrderInsur>
				    		<tr class = "ins">
				   				<td class = "ins"><span>关键联络人：</span>${noncarOrderInsur.customerName!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件类型：</span>${noncarOrderInsur.idType!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件号：</span>${noncarOrderInsur.idNo!}<br/></td>
				    		</tr>
				    </#list>
				    </#if>
				    <!-- 受益人 -->
				    <#if INSUREDRList??>
				    <#list INSUREDRList as noncarOrderIn>
				    		<tr class = "ins">
				   				<td class = "ins"><span>受益人：</span>${noncarOrderIn.customerName!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件类型：</span>${noncarOrderIn.idType!}&#160;&#160;&#160;&#160;</td>
				   				<td class = "ins"><span>证件号：</span>${noncarOrderIn.idNo!}<br/></td>
				    		</tr>
				    </#list>
				    </#if>
			</p>
		</div>
	</div>
	<div class="div_v2">
		<div>
			<p>
				<span>保险期间：</span>${START_DATE!}
			</p>
		</div>
	</div>
	<div class="div_v04">
		<div>
			<p>
				<h3><span class = "jb_mes">二、</span><span class = "jb_mes">保险责任</span></h3>
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
	</div>
	<div  class="div_v02">
		${insurance_notice!}${insurance_declaration!}${health_notification!} 
	</div>
	<#if health_notification??>
	<div class="div_v03">
		<div>
			<p  class = "tb_is">
				<img class="icon_image" src="${IcImage}"></img><span>是&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</span> 
				<img class="icon_image" src="${IImage}"></img><span class = "tb_is">否</span>
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
	<div class="div_v05">
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
	</div>
</div>
</body>
</html>