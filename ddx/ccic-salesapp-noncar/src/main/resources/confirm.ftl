<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
        body {
            font-family: SimSun;
        }

        ul li {
            list-style: none;
        }

        h1 {
            font-weight: lighter;
            text-align: center;
        }

        table {
            width: 100%;
        }

        thead {
            background: #9E9E9e;
        }

        tbody {
            text-align: center;
        }

        img {
            width: 219.47px;
            height: 110.27px;
        }
    </style>
</head>

<body>
    <div style="width:90%;margin: 0 auto;">
        <h1>投保确认书</h1>
        <div>合同号:${INSURE_NO!}</div>
        
        <#if str?? && str=="SXYZ20500270001">
            ${InsuranceNoticeSXYZ20500270001}
        </#if>
        <#if str?? && str=="SXYZ20500270002">
            ${InsuranceNoticeSXYZ20500270002}
        </#if>
        <#if str?? && str=="SXYZ20500270003">
            ${InsuranceNoticeSXYZ20500270003}
        </#if>
        <#if str?? && str=="SXYZ20500270004">
            ${InsuranceNoticeSXYZ20500270004}
        </#if>
        <#if str?? && str=="SXYZ20500270005">
            ${InsuranceNoticeSXYZ20500270005}
        </#if>
        <#if str?? && str=="SXYZ20500270006">
            ${InsuranceNoticeSXYZ20500270006}
        </#if>
        <#if str?? && str=="SXYZ20500270007">
            ${InsuranceNoticeSXYZ20500270007}
        </#if>
        <#if str?? && str=="SXYZ20500270008">
            ${InsuranceNoticeSXYZ20500270008}
        </#if>
        
        <h2>
            一、基本信息
        </h2>
        <table>
            <tr>
                <td>投保人名称：${APPLICANT_NAME!}</td>
                <td>证件类型：${APPLIIDENTIFY_TYPE!}</td>
                <td>证件号：${APPLI_IDENTIFY!}</td>
            </tr>
            <tr>
                <td>被保险人：${INSURED_NAME!}</td>
                <td>证件类型：${INSUREDIDENTIFY_TYPE!}</td>
                <td>证件号：${INSURED_IDENTIFY!}</td>
            </tr>
        </table>
        <h2>
            二、保险责任
        </h2>
        <div>
            保障计划:
        </div>
                <#if list??>
                    <#list list as ps>
        				<h3>${ps.productName!}保障责任明细</h3>
                        <table border="1" cellpadding="2" cellspacing="0">
                            <thead style="text-align:center;">
                                <tr>
                                    <th>保险责任</th>
                                    <th>保险金额</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<#if ps.noncarPlanCtList??>
                                <#list ps.noncarPlanCtList as pss>
                                <tr>
                                    <td>${pss.coverageName!}</td>
                                    <td>¥${pss.insured!}</td>
                                </tr>
                                </#list>
                                </#if>
                            </tbody>
                        </table>
                    </#list>
                </#if>
        <div>
            总保费：${SUMPREMIUM!}
        </div>
        <h2>
            三、投保声明
        </h2>
        <p>
            1、本投保人申明以上各项内容填写属实，也是本人所能提供的全部信息，并认可保险金额。我明白未真实、完全填写或故意隐瞒与此保险相关的事实会导致保险失效；如果我违背本声明，保险人有权终止保险合同且不退还保险费。
        </p>
        <p>
            2、本人已认真仔细阅读《中国大地保险个人医疗保险条款（C款）》、《大地通达公共交通工具意外伤害保险条款》《中国大地保险附加个人恶性肿瘤扩展质子重离子医疗保险条款》（如投保该附加险）、《中国大地保险个人医疗保险条款（C款）附加扩展特需医疗保险条款》（如投保该附加险）、《中国大地保险附加个人恶性肿瘤赴日医疗保险条款》（如投保该附加险）、《中国大地保险附加个人恶性肿瘤院外特种药品费用医疗保险条款》（如投保该附加险）、《投保须知》、《责任免除》、《特别约定》和投保人声明的全部内容，并确认已充分理解本保险保险责任、等待期约定、责任免除、如实告知、合同解除等重要事项后做出投保决定。本人接受并同意遵守保险条款和上述投保注意事项中的所有内容。
        </p>
        <p>
            3、本人明白且接受：既往症不属于保险责任。
        </p>
        <p>
            4、本人同意将电子保单发出之日的次日视为客户签收日。同意如发生有关网上投保险种、保险金额等方面的分歧，以贵公司的电子记录凭证等数据电文作为判断本保险合同的唯一合法有效凭证，该凭证具有完全证据效力。
        </p>
        <p>
            5、根据《中华人民共和国合同法》第十一条规定，数据电文是合法的合同表现形式。本人接受以中国大地财产保险股份有限公司提供的电子保单作为本投保书成立的合法有效凭证，具有完全证据效力。
        </p>
        <p>
            6、本人代表被保险人授权大地保险公司可以从任何单位、组织和个人就有关保险事宜查询、索取与被保险人有关的资料和证明，作为审核本投保申请及评估相关理赔申请的依据，大地保险公司对个人资料承担保密义务。
        </p>
        <p>
            8、在此声明后至保险正式生效日之前，如上述声明内容发生改变，本人应及时通知保险人，保险人有权进行重新评估。
        </p>
        <#if str?? && str=="SXYZ20500270001">
            ${BasicEdition}
        </#if>
        <#if str?? && str=="SXYZ20500270002">
            ${EliteEdition}
        </#if>
        <#if str?? && str=="SXYZ20500270003">
            ${OptionalVersionOne}
        </#if>
        <#if str?? && str=="SXYZ20500270004">
            ${OptionalVersionTwo}
        </#if>
        <#if str?? && str=="SXYZ20500270005">
            ${OptionalVersionThree}
        </#if>
        <#if str?? && str=="SXYZ20500270006">
            ${OptionalVersionFour}
        </#if>
        <#if str?? && str=="SXYZ20500270007">
            ${OptionalVersionFive}
        </#if>
        <#if str?? && str=="SXYZ20500270008">
            ${OptionalVersionSix}
        </#if>
        <p>
            投保人签名：
        </p>
        <p>
            <img src="${signImage}"></img>
        </p>
        <p>
            日期：${CREATE_DATE}
        </p>
    </div>
</body>

</html>