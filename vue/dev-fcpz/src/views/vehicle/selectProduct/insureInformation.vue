<!--
 * @Author: King
 * @Page: 投被保人信息
 * @Date: 2020-03-13 16:20:50
 * @Last Modified by: King
 * @Last Modified time: 2020-07-08 11:25:55
 * @FilePath: /src/views/vehicle/selectProduct/insureInformation.vue
-->

<template>
  <div class="insure-information">
    <nav-bar
      type="vehicle"
      title="投被保人信息"
      @clickBack="back"
      rightIcon="wap-home-o"
      @clickRight="$router.push({ path: '/' })"
    />
    <div class="content">
      <!-- 投保人 -->
      <div class="content-group">
        <van-cell class="group">
          <!-- <van-icon slot="icon" class="group-icon" name="user-o" size="16" /> -->
          <img
            slot="icon"
            class="group-icon"
            src="../../../assets/vehicle/vehicleInfo/policyholder.png"
          />
          <span slot="title" class="group-title">投保人</span>
        </van-cell>
        <van-cell class="item">
          <span slot="title" class="item-title">是否同车主</span>
          <div class="item-right">
            <whether-select
              v-model="policyHolder.relationFlag"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
              @change="flag => same(flag, 'policyHolder')"
            />
          </div>
        </van-cell>
        <div class="content-group">
          <!-- 客户类型 -->
          <van-cell class="item" required>
            <span slot="title" class="item-title">客户类型</span>
            <div class="item-right">
              <whether-select
                :disabled="policyHolder.relationFlag == '1'"
                v-model="policyHolder.custType"
                :options="[
                  { text: '个人', value: '01' },
                  { text: '单位', value: '02' }
                ]"
                @change="v => changeCustType('policyHolder', v)"
              />
            </div>
          </van-cell>
          <!-- 姓名/单位名称 -->
          <van-field
            class="item"
            required
            v-model="policyHolder.name"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            maxlength="50"
            ref="policyHolder.name"
            :disabled="policyHolder.relationFlag == '1'"
            :placeholder="
              realName == '1' && policyHolder.custType == '01'
                ? '请确保投保人姓名与缴费人姓名一致'
                : '请输入' +
                  (policyHolder.custType == '01' ? '姓名' : '单位名称')
            "
            @input="input('name')"
            @focus="e => (e.target.placeholder = '')"
            @blur="
              e =>
                (e.target.placeholder =
                  realName == '1' && policyHolder.custType == '01'
                    ? '请确保投保人姓名与缴费人姓名一致'
                    : '请输入' +
                      (policyHolder.custType == '01' ? '姓名' : '单位名称'))
            "
          >
            <span slot="label" class="item-title">
              {{ policyHolder.custType == "01" ? "姓名" : "单位名称" }}
            </span>
          </van-field>
          <!-- 证件类型 -->
          <van-cell
            class="item"
            required
            @click="
              policyHolder.relationFlag == '1'
                ? ''
                : setPicker('policyHolder.identityType')
            "
          >
            <span slot="title" class="item-title">证件类型</span>
            <div class="item-right">
              <span
                :class="{
                  'item-placeholder': !policyHolder.identityType
                }"
              >
                {{
                  $utils.findText(
                    policyHolder.custType == "01"
                      ? identityTypes01
                      : identityTypes02,
                    policyHolder.identityType
                  )
                }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
              <div
                class="item-right-button"
                v-if="policyHolder.identityType == '111'"
                @click.stop="OCR('policyHolder')"
              >
                <span class="item-right-button-text">证件识别</span>
                <van-icon name="photograph" size="14px" color="#3B8AFE" />
              </div>
            </div>
          </van-cell>
          <!-- 证件号码 -->
          <van-field
            class="item"
            required
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            v-model="policyHolder.identityNo"
            ref="policyHolder.identityNo"
            :placeholder="
              realName == '1' && policyHolder.identityType == '111'
                ? '请确保投保人身份证号与缴费人身份证号一致'
                : '请输入证件号码'
            "
            :disabled="policyHolder.relationFlag == '1'"
            :maxlength="policyHolder.identityType == '111' ? 18 : 32"
            @input="input('identityNo')"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => identityNoBlur(e, 'policyHolder')"
          >
            <span slot="label" class="item-title">
              证件号码
            </span>
          </van-field>
          <div class="content-group" v-if="policyHolder.custType == '01'">
            <!-- 安徽 贵州(安顺中支) 特有 -->
            <!-- 证件有效起期 -->
            <van-cell
              v-if="
                (areaCode == '3401' && policyHolder.custType == '01') ||
                  (areaCode == '5201' &&
                    comCName.indexOf('安顺中') !== -1 &&
                    policyHolder.custType == '01')
              "
              class="item"
              :required="policyHolder.identityType == '111'"
              @click="datePickerType = 'policyHolder.identityStartDate'"
            >
              <span slot="title" class="item-title">证件有效起期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !policyHolder.identifyStartDate
                  }"
                  >{{ policyHolder.identifyStartDate | date }}</span
                >
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 安徽 贵州(安顺中支) 特有 -->
            <!-- 证件有效止期/长期有效 -->
            <van-cell
              v-if="
                (areaCode == '3401' && policyHolder.custType == '01') ||
                  (areaCode == '5201' &&
                    comCName.indexOf('安顺中') !== -1 &&
                    policyHolder.custType == '01')
              "
              class="item"
              :required="policyHolder.identityType == '111'"
              @click="
                policyHolderIdentifyLongTerm
                  ? ''
                  : (datePickerType = 'policyHolder.identityEndDate')
              "
            >
              <span slot="title" class="item-title">证件有效止期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !policyHolder.identifyEndDate
                  }"
                >
                  {{ policyHolder.identifyEndDate | date }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
                <van-checkbox
                  :disabled="
                    !(
                      policyHolder.identityType == '111' &&
                      policyHolder.age > 46
                    )
                  "
                  class="item-right-checkbox"
                  icon-size="14"
                  v-model="policyHolderIdentifyLongTerm"
                  @click.stop=""
                  @change="
                    v => (policyHolder.identifyEndDate = v ? '9999-12-31' : '')
                  "
                >
                  <span>长期有效</span>
                </van-checkbox>
              </div>
            </van-cell>
            <!-- 云南 贵州 特有 -->
            <!-- 性别 -->
            <van-cell
              class="item"
              required
              v-if="areaCode == '5201' || areaCode == '5301'"
            >
              <span slot="title" class="item-title">性别</span>
              <div class="item-right">
                <!-- 身份证时自动带出,不可编辑 -->
                <whether-select
                  :disabled="
                    policyHolder.identityType == '111' ||
                      policyHolder.relationFlag == '1'
                  "
                  v-model="policyHolder.sex"
                  :options="[
                    { text: '男', value: '1' },
                    { text: '女', value: '2' }
                  ]"
                />
              </div>
            </van-cell>
            <!-- 北京 特有 -->
            <!-- 出生日期 -->
            <van-cell
              v-if="areaCode == '1101'"
              class="item"
              @click="datePickerType = 'policyHolder.birthDate'"
              required
            >
              <span slot="title" class="item-title">出生日期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !policyHolder.birthDate
                  }"
                >
                  {{ policyHolder.birthDate | date }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 民族 -->
            <van-cell
              v-if="areaCode == '1101'"
              class="item"
              @click="setPicker('policyHolder.nation')"
              required
            >
              <span slot="title" class="item-title">民族</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !policyHolder.nation
                  }"
                >
                  {{ $utils.findText(nations, policyHolder.nation) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 签发机关 -->
            <van-field
              class="item"
              required
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="areaCode == '1101'"
              v-model="policyHolder.identifyCom"
              ref="policyHolder.identifyCom"
              maxlength="50"
              placeholder="请输入签发机关"
              @input="input('identifyCom')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入签发机关')"
            >
              <span slot="label" class="item-title">
                签发机关
              </span>
            </van-field>
          </div>
          <div class="content-group" v-if="policyHolder.custType == '02'">
            <!-- 单位性质 -->
            <van-cell
              v-show="policyHolder.custType == '02'"
              class="item"
              required
              @click="setPicker('policyHolder.unitNature')"
            >
              <span slot="title" class="item-title">单位性质</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !policyHolder.unitNature
                  }"
                >
                  {{ $utils.findText(unitNatures, policyHolder.unitNature) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 联系人 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-model="policyHolder.contactPersonName"
              ref="policyHolder.contactPersonName"
              maxlength="50"
              placeholder="请输入联系人姓名"
              @input="input('contactPersonName')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入联系人姓名')"
            >
              <span slot="label" class="item-title">
                联系人
              </span>
            </van-field>
          </div>
          <!-- 移动电话 -->
          <van-field
            class="item"
            required
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            maxlength="11"
            :disabled="policyHolder.relationFlag == '1' ? false : false"
            v-model="policyHolderPhone"
            ref="policyHolder.phone"
            placeholder="请输入移动电话"
            @input="input('phone')"
            @focus="
              e => {
                e.target.placeholder = '';
                policyHolder.phone = '';
              }
            "
            @blur="e => (e.target.placeholder = '请输入移动电话')"
          >
            <span slot="label" class="item-title">
              移动电话
            </span>
          </van-field>
          <div class="content-group" v-if="policyHolder.custType == '01'">
            <!-- 深圳 特有 -->
            <!-- 客户经办人姓名 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="areaCode == '4403'"
              v-model="policyHolder.customerAgentName"
              ref="policyHolder.customerAgentName"
              maxlength="50"
              placeholder="请输入客户经办人姓名"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入客户经办人姓名')"
            >
              <span slot="label" class="item-title">
                客户经办人姓名
              </span>
            </van-field>
            <!-- 客户经办人证件类型 -->
            <van-cell
              v-if="areaCode == '4403'"
              class="item"
              @click="setPicker('customerAgentCertificatetype')"
            >
              <span slot="title" class="item-title">客户经办人证件类型</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !policyHolder.customerAgentCertificatetype
                  }"
                >
                  {{
                    $utils.findText(
                      customerAgentCertificatetypes,
                      policyHolder.customerAgentCertificatetype
                    )
                  }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 客户经办人证件号码 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="areaCode == '4403'"
              v-model="policyHolder.customerAgentCertificatenum"
              ref="policyHolder.customerAgentCertificatenum"
              maxlength="32"
              placeholder="请输入客户经办人证件号码"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入客户经办人证件号码')"
            >
              <span slot="label" class="item-title">
                客户经办人证件号码
              </span>
            </van-field>
            <!-- 上海 特有 -->
            <!-- 非手机持有人 -->
            <van-cell
              class="item"
              required
              v-if="areaCode == '3101' || areaCode == '3109'"
            >
              <span slot="title" class="item-title">非手机持有人</span>
              <div class="item-right">
                <whether-select
                  v-model="policyHolder.isPhoneHolder"
                  :options="[
                    { text: '是', value: '1' },
                    { text: '否', value: '0' }
                  ]"
                />
              </div>
            </van-cell>
            <!-- 持有人姓名 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="policyHolder.isPhoneHolder == '1'"
              v-model="policyHolder.phoneHolderName"
              ref="policyHolder.phoneHolderName"
              maxlength="20"
              placeholder="请输入持有人姓名"
              @input="input('phoneHolderName')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入持有人姓名')"
            >
              <span slot="label" class="item-title">
                持有人姓名
              </span>
            </van-field>
            <!-- 持有人身份证号 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="policyHolder.isPhoneHolder == '1'"
              v-model="policyHolder.phoneHolderIdno"
              ref="policyHolder.phoneHolderIdno"
              maxlength="18"
              placeholder="请输入持有人身份证号"
              @input="input('phoneHolderIdno')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入持有人身份证号')"
            >
              <span slot="label" class="item-title">
                持有人身份证号
              </span>
            </van-field>
          </div>
          <!-- 邮箱地址 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            v-model="policyHolder.email"
            ref="policyHolder.email"
            placeholder="请输入邮箱地址"
            maxlength="100"
            @input="input('email')"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => (e.target.placeholder = '请输入邮箱地址')"
          >
            <span slot="label" class="item-title">
              邮箱地址
            </span>
          </van-field>
          <div class="content-group" v-if="policyHolder.custType == '01'">
            <!-- 职业 -->
            <van-cell
              class="item"
              required
              @click="setPicker('policyHolder.occupation')"
            >
              <span slot="title" class="item-title">职业</span>
              <div class="item-right">
                <span :class="{ 'item-placeholder': !policyHolder.occupation }">
                  {{ $utils.findText(professions, policyHolder.occupation) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 国籍 -->
            <van-cell
              class="item"
              required
              @click="setPicker('policyHolder.nationality')"
            >
              <span slot="title" class="item-title">国籍</span>
              <div class="item-right">
                <span
                  :class="{ 'item-placeholder': !policyHolder.nationality }"
                >
                  {{ $utils.findText(nationalitys, policyHolder.nationality) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
          </div>
          <!-- 联系地址 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            :required="addressRequired && policyHolder.custType == '01'"
            v-model="policyHolder.address"
            ref="policyHolder.address"
            placeholder="请输入联系地址"
            maxlength="100"
            @input="input('address')"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => (e.target.placeholder = '请输入联系地址')"
          >
            <span slot="label" class="item-title">
              联系地址
            </span>
          </van-field>
          <!-- 投保险人与车辆关系 -->
          <!-- 吉林,山西,天津 无此字段 -->
          <van-cell
            class="item"
            required
            v-if="
              areaCode != '2201' && areaCode != '1401' && areaCode != '1201'
            "
          >
            <span slot="title" class="item-title">投保险人与车辆关系</span>
            <div class="item-right">
              <whether-select
                :disabled="policyHolder.relationFlag == '1'"
                v-model="policyHolder.vehicleRelation"
                :options="[
                  { text: '所有', value: '1', disabled: true },
                  { text: '使用', value: '2' },
                  { text: '管理', value: '3' }
                ]"
              />
            </div>
          </van-cell>
        </div>
      </div>
      <!-- 被保人 -->
      <div class="content-group">
        <van-cell class="group">
          <!-- <van-icon slot="icon" class="group-icon" name="user-o" size="16" /> -->
          <img
            slot="icon"
            class="group-icon"
            src="../../../assets/vehicle/vehicleInfo/recognizee.png"
          />
          <span slot="title" class="group-title">被保人</span>
        </van-cell>
        <!-- 是否同投保人 -->
        <van-cell class="item">
          <whether-select
            v-model="recognizee.relationFlag"
            :options="[
              { text: '同车主', value: '3' },
              { text: '同投保人', value: '2' },
              { text: '自定义', value: '0' }
            ]"
            @change="flag => same(flag, 'recognizee')"
          />
        </van-cell>
        <div class="content-group">
          <!-- 客户类型 -->
          <van-cell class="item" required>
            <span slot="title" class="item-title">客户类型</span>
            <div class="item-right">
              <whether-select
                :disabled="recognizee.relationFlag != '0'"
                v-model="recognizee.custType"
                :options="[
                  { text: '个人', value: '01' },
                  { text: '单位', value: '02' }
                ]"
                @change="v => changeCustType('recognizee', v)"
              />
            </div>
          </van-cell>
          <!-- 姓名/单位名称 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            :disabled="sameFlag"
            v-model="recognizee.name"
            ref="recognizee.name"
            maxlength="50"
            :placeholder="
              '请输入' + (recognizee.custType == '01' ? '姓名' : '单位名称')
            "
            @focus="e => (e.target.placeholder = '')"
            @blur="
              e =>
                (e.target.placeholder =
                  '请输入' +
                  (recognizee.custType == '01' ? '姓名' : '单位名称'))
            "
          >
            <span slot="label" class="item-title">
              {{ recognizee.custType == "01" ? "姓名" : "单位名称" }}
            </span>
          </van-field>
          <!-- 证件类型 -->
          <van-cell
            class="item"
            required
            @click="sameFlag ? '' : setPicker('recognizee.identityType')"
          >
            <span slot="title" class="item-title">证件类型</span>
            <div class="item-right">
              <span
                :class="{
                  'item-placeholder': !recognizee.identityType
                }"
              >
                {{
                  $utils.findText(
                    recognizee.custType == "01"
                      ? identityTypes01
                      : identityTypes02,
                    recognizee.identityType
                  )
                }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
              <div
                class="item-right-button"
                v-if="recognizee.identityType == '111'"
                @click.stop="OCR('recognizee')"
              >
                <span class="item-right-button-text">证件识别</span>
                <van-icon name="photograph" size="14px" color="#3B8AFE" />
              </div>
            </div>
          </van-cell>
          <!-- 证件号码 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            :disabled="sameFlag"
            v-model="recognizee.identityNo"
            ref="recognizee.identityNo"
            placeholder="请输入证件号码"
            :maxlength="recognizee.identityType == '111' ? 18 : 32"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => identityNoBlur(e, 'recognizee')"
          >
            <span slot="label" class="item-title">
              证件号码
            </span>
          </van-field>
          <div class="content-group" v-if="recognizee.custType == '01'">
            <!-- 安徽 贵州(安顺中支) 特有 -->
            <!-- 证件有效起期 -->
            <van-cell
              v-if="
                (areaCode == '3401' && recognizee.custType == '01') ||
                  (areaCode == '5201' &&
                    comCName.indexOf('安顺中') !== -1 &&
                    recognizee.custType == '01')
              "
              class="item"
              :required="
                comCName.indexOf('安顺中') !== -1 ||
                  recognizee.identityType == '111'
              "
              @click="
                sameFlag
                  ? ''
                  : (datePickerType = 'recognizee.identityStartDate')
              "
            >
              <span slot="title" class="item-title">证件有效起期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !recognizee.identifyStartDate
                  }"
                  >{{ recognizee.identifyStartDate | date }}</span
                >
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 安徽 贵州(安顺中支) 特有 -->
            <!-- 证件有效止期/长期有效 -->
            <van-cell
              v-if="
                (areaCode == '3401' && recognizee.custType == '01') ||
                  (areaCode == '5201' &&
                    comCName.indexOf('安顺中') !== -1 &&
                    recognizee.custType == '01')
              "
              class="item"
              :required="
                comCName.indexOf('安顺中') !== -1 ||
                  recognizee.identityType == '111'
              "
              @click="
                sameFlag
                  ? ''
                  : recognizeeIdentifyLongTerm
                  ? ''
                  : (datePickerType = 'recognizee.identityEndDate')
              "
            >
              <span slot="title" class="item-title">证件有效止期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !recognizee.identifyEndDate
                  }"
                >
                  {{ recognizee.identifyEndDate | date }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
                <van-checkbox
                  :disabled="
                    sameFlag ||
                      !(recognizee.identityType == '111' && recognizee.age > 46)
                  "
                  class="item-right-checkbox"
                  icon-size="14"
                  v-model="recognizeeIdentifyLongTerm"
                  @click.stop=""
                  @change="
                    v => (recognizee.identifyEndDate = v ? '9999-12-31' : '')
                  "
                >
                  <span>长期有效</span>
                </van-checkbox>
              </div>
            </van-cell>
            <!-- 云南 贵州 特有 -->
            <!-- 性别 -->
            <van-cell
              class="item"
              required
              v-if="areaCode == '5201' || areaCode == '5301'"
            >
              <span slot="title" class="item-title">性别</span>
              <div class="item-right">
                <!-- 身份证时自动带出,不可编辑 -->
                <whether-select
                  :disabled="sameFlag && recognizee.identityType == '111'"
                  v-model="recognizee.sex"
                  :options="[
                    { text: '男', value: '1' },
                    { text: '女', value: '2' }
                  ]"
                />
              </div>
            </van-cell>
            <!-- 北京 特有 -->
            <!-- 出生日期 -->
            <van-cell
              v-if="areaCode == '1101'"
              class="item"
              @click="sameFlag ? '' : (datePickerType = 'recognizee.birthDate')"
              required
            >
              <span slot="title" class="item-title">出生日期</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !recognizee.birthDate
                  }"
                >
                  {{ recognizee.birthDate | date }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 民族 -->
            <van-cell
              v-if="areaCode == '1101'"
              class="item"
              @click="sameFlag ? '' : setPicker('recognizee.nation')"
              required
            >
              <span slot="title" class="item-title">民族</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !recognizee.nation
                  }"
                >
                  {{ $utils.findText(nations, recognizee.nation) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 签发机关 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="areaCode == '1101'"
              required
              :disabled="sameFlag"
              v-model="recognizee.identifyCom"
              ref="recognizee.identifyCom"
              maxlength="50"
              placeholder="请输入签发机关"
              @input="input('identifyCom')"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入签发机关')"
            >
              <span slot="label" class="item-title">
                签发机关
              </span>
            </van-field>
          </div>
          <div class="content-group" v-if="recognizee.custType == '02'">
            <!-- 单位性质 -->
            <van-cell
              class="item"
              required
              @click="setPicker('recognizee.unitNature')"
            >
              <span slot="title" class="item-title">单位性质</span>
              <div class="item-right">
                <span
                  :class="{
                    'item-placeholder': !recognizee.unitNature
                  }"
                >
                  {{ $utils.findText(unitNatures, recognizee.unitNature) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 联系人 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              :disabled="sameFlag"
              v-model="recognizee.contactPersonName"
              ref="recognizee.contactPersonName"
              maxlength="50"
              placeholder="请输入联系人姓名"
            >
              <span slot="label" class="item-title">
                联系人
              </span>
            </van-field>
          </div>
          <!-- 移动电话 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            :disabled="sameFlag ? false : false"
            maxlength="11"
            v-model="recognizeePhone"
            ref="recognizee.phone"
            :placeholder="sameFlag ? '同投保人移动电话' : '请输入移动电话'"
            @input="input('name')"
            @focus="
              e => {
                e.target.placeholder = '';
                recognizee.phone = '';
              }
            "
            @blur="
              e =>
                (e.target.placeholder = sameFlag
                  ? '同投保人移动电话'
                  : '请输入移动电话')
            "
          >
            <span slot="label" class="item-title">
              移动电话
            </span>
          </van-field>
          <div class="content-group" v-if="recognizee.custType == '01'">
            <!-- 深圳 投保人 特有 -->
            <!-- 客户经办人姓名 -->
            <!-- 客户经办人证件类型 -->
            <!-- 客户经办人证件号码 -->
            <!-- 上海 特有 -->
            <!-- 非手机持有人 -->
            <van-cell
              class="item"
              required
              v-if="areaCode == '3101' || areaCode == '3109'"
            >
              <span slot="title" class="item-title">非手机持有人</span>
              <div class="item-right">
                <whether-select
                  :disabled="sameFlag"
                  v-model="recognizee.isPhoneHolder"
                  :options="[
                    { text: '是', value: '1' },
                    { text: '否', value: '0' }
                  ]"
                />
              </div>
            </van-cell>
            <!-- 持有人姓名 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="recognizee.isPhoneHolder == '1'"
              :disabled="sameFlag"
              v-model="recognizee.phoneHolderName"
              ref="recognizee.phoneHolderName"
              maxlength="20"
              placeholder="请输入持有人姓名"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入持有人姓名')"
            >
              <span slot="label" class="item-title">
                持有人姓名
              </span>
            </van-field>
            <!-- 持有人身份证号 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              v-if="recognizee.isPhoneHolder == '1'"
              :disabled="sameFlag"
              v-model="recognizee.phoneHolderIdno"
              ref="recognizee.phoneHolderIdno"
              maxlength="18"
              placeholder="请输入持有人身份证号"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入持有人身份证号')"
            >
              <span slot="label" class="item-title">
                持有人身份证号
              </span>
            </van-field>
          </div>
          <!-- 邮箱地址 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            :disabled="sameFlag"
            v-model="recognizee.email"
            ref="recognizee.email"
            :placeholder="sameFlag ? '同投保人邮箱地址' : '请输入邮箱地址'"
            maxlength="100"
            @focus="e => (e.target.placeholder = '')"
            @blur="
              e =>
                (e.target.placeholder = sameFlag
                  ? '同投保人邮箱地址'
                  : '请输入邮箱地址')
            "
          >
            <span slot="label" class="item-title">
              邮箱地址
            </span>
          </van-field>
          <div class="content-group" v-if="recognizee.custType == '01'">
            <!-- 职业 -->
            <van-cell
              class="item"
              required
              @click="sameFlag ? '' : setPicker('recognizee.occupation')"
            >
              <span slot="title" class="item-title">职业</span>
              <div class="item-right">
                <span :class="{ 'item-placeholder': !recognizee.occupation }">
                  {{ $utils.findText(professions, recognizee.occupation) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
            <!-- 国籍 -->
            <van-cell
              class="item"
              required
              @click="sameFlag ? '' : setPicker('recognizee.nationality')"
            >
              <span slot="title" class="item-title">国籍</span>
              <div class="item-right">
                <span :class="{ 'item-placeholder': !recognizee.nationality }">
                  {{ $utils.findText(nationalitys, recognizee.nationality) }}
                </span>
                <van-icon
                  class="item-right-arrow"
                  name="arrow"
                  color="#BFBFBF"
                />
              </div>
            </van-cell>
          </div>
          <!-- 联系地址 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            :required="addressRequired && recognizee.custType == '01'"
            :disabled="sameFlag"
            v-model="recognizee.address"
            ref="recognizee.address"
            :placeholder="sameFlag ? '同投保人联系地址' : '请输入联系地址'"
            maxlength="100"
            @focus="e => (e.target.placeholder = '')"
            @blur="
              e =>
                (e.target.placeholder = sameFlag
                  ? '同投保人联系地址'
                  : '请输入联系地址')
            "
          >
            <span slot="label" class="item-title">
              联系地址
            </span>
          </van-field>
          <!-- 被保险人与车辆关系 -->
          <van-cell class="item" required>
            <span slot="title" class="item-title">被保险人与车辆关系</span>
            <div class="item-right">
              <whether-select
                :disabled="sameFlag && policyHolder.relationFlag == '1'"
                v-model="recognizee.vehicleRelation"
                :options="[
                  {
                    text: '所有',
                    value: '1',
                    disabled: this.recognizee.relationFlag == '2' ? true : false
                  },
                  { text: '使用', value: '2' },
                  { text: '管理', value: '3' }
                ]"
              />
            </div>
          </van-cell>
          <div></div>
        </div>
        <!-- 反洗钱风险等级 -->
        <van-cell class="item">
          <span slot="title" class="item-title">反洗钱风险等级</span>
          <span class="item-right">{{ restOptions.clsLevel || "无" }}</span>
        </van-cell>
        <div class="separated"></div>
        <van-cell class="item" v-show="policyHolder.custType == '01'">
          <span slot="title" class="item-title">是否电子投保</span>
          <div class="item-right">
            <whether-select
              v-model="restOptions.needElectronicProposal"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
            />
          </div>
        </van-cell>
      </div>
      <!-- 开票信息 -->
      <div class="content-group">
        <van-cell class="group">
          <!-- <van-icon slot="icon" class="group-icon" name="user-o" size="16" /> -->
          <img
            slot="icon"
            class="group-icon"
            src="../../../assets/vehicle/vehicleInfo/invoice.png"
          />
          <span slot="title" class="group-title">开票信息</span>
        </van-cell>
        <!-- 是否需要电子发票 -->
        <van-cell class="item">
          <span slot="title" class="item-title">是否需要电子发票</span>
          <div class="item-right">
            <whether-select
              v-model="restOptions.needElectronicInvoice"
              :options="[
                { text: '是', value: '1' },
                { text: '否', value: '0' }
              ]"
              @change="changeIsNeedInvoice"
            />
          </div>
        </van-cell>
        <div
          class="content-group"
          v-if="restOptions.needElectronicInvoice == '1'"
        >
          <!-- 发票抬头 -->
          <van-cell class="item">
            <span slot="title" class="item-title">发票抬头</span>
            <div class="item-right">
              <whether-select
                v-model="invoice.invoiceRelation"
                :options="[
                  { text: '同投保人', value: '1' },
                  { text: '同被保人', value: '2' }
                ]"
                @change="changeInvoice"
              />
            </div>
          </van-cell>
          <!-- 移动电话 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            maxlength="11"
            v-model="invoicePhoneNo"
            ref="invoice.phoneNo"
            placeholder="请输入移动电话"
            @focus="
              e => {
                e.target.placeholder = '';
                invoice.phoneNo = '';
              }
            "
            @blur="e => (e.target.placeholder = '请输入移动电话')"
          >
            <span slot="label" class="item-title">
              移动电话
            </span>
          </van-field>
          <!-- 邮箱地址 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            v-model="invoice.email"
            ref="invoice.email"
            :placeholder="
              invoice.invoiceRelation == '1' ? '同投保人邮箱' : '同被保人邮箱'
            "
            maxlength="30"
            @focus="e => (e.target.placeholder = '')"
            @blur="
              e =>
                (e.target.placeholder =
                  invoice.invoiceRelation == '1'
                    ? '同投保人邮箱'
                    : '同被保人邮箱')
            "
          >
            <span slot="label" class="item-title">
              邮箱地址
            </span>
          </van-field>
          <!-- 身份证采集器编码 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            v-if="areaCode == '1101'"
            v-model="invoice.indiIdGatherCaptcha"
            ref="invoice.indiIdGatherCaptcha"
            :placeholder="'身份证采集器编码'"
            maxlength="50"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => (e.target.placeholder = '身份证采集器编码')"
          >
            <span slot="label" class="item-title">
              身份证采集器编码
            </span>
          </van-field>
        </div>
        <div
          class="content-group"
          v-if="!invoiceIsCompany && restOptions.needElectronicInvoice == '1'"
        >
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            v-model="invoice.taxPayerIdentification"
            ref="invoice.taxPayerIdentification"
            placeholder="请输入纳税人识别号"
            maxlength="50"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => (e.target.placeholder = '纳税人识别号')"
          >
            <span slot="label" class="item-title">
              纳税人识别号
            </span>
          </van-field>
        </div>
        <div
          class="content-group"
          v-if="invoiceIsCompany && restOptions.needElectronicInvoice == '1'"
        >
          <!-- 客户类型 -->
          <van-cell
            class="item"
            required
            @click="setPicker('invoice.clientType')"
          >
            <span slot="title" class="item-title">客户类型</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !invoice.clientType }">
                {{ $utils.findText(clientTypes, invoice.clientType) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            </div>
          </van-cell>
          <!-- 纳税人类型 -->
          <van-cell
            class="item"
            required
            @click="setPicker('invoice.taxPayerType')"
          >
            <span slot="title" class="item-title">纳税人类型</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !invoice.taxPayerType }">
                {{ $utils.findText(taxPayerTypes, invoice.taxPayerType) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            </div>
          </van-cell>
          <!-- 发票类型 -->
          <van-cell
            class="item"
            required
            @click="setPicker('invoice.invoiceType')"
          >
            <span slot="title" class="item-title">发票类型</span>
            <div class="item-right">
              <span :class="{ 'item-placeholder': !invoice.invoiceType }">
                {{ $utils.findText(invoiceTypes, invoice.invoiceType) }}
              </span>
              <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            </div>
          </van-cell>
          <!-- 公司名称 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            v-model="invoice.taxCompanyName"
            ref="invoice.taxCompanyName"
            placeholder="请输入公司名称"
            maxlength="50"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => (e.target.placeholder = '请输入公司名称')"
          >
            <span slot="label" class="item-title">
              公司名称
            </span>
          </van-field>
          <!-- 纳税人识别号 -->
          <van-field
            class="item"
            rows="1"
            autosize
            type="textarea"
            input-align="right"
            required
            v-model="invoice.taxPayerIdentification"
            ref="invoice.taxPayerIdentification"
            placeholder="请输入纳税人识别号"
            maxlength="50"
            @focus="e => (e.target.placeholder = '')"
            @blur="e => (e.target.placeholder = '请输入纳税人识别号')"
          >
            <span slot="label" class="item-title">
              纳税人识别号
            </span>
          </van-field>
          <div class="content-group" v-show="unfolding">
            <!-- 注册地址 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              :required="invoice.invoiceType == '1'"
              v-model="invoice.taxRegistryAddress"
              ref="invoice.taxRegistryAddress"
              placeholder="请输入注册地址"
              maxlength="100"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入注册地址')"
            >
              <span slot="label" class="item-title">
                注册地址
              </span>
            </van-field>
            <!-- 注册电话 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              :required="invoice.invoiceType == '1'"
              v-model="invoice.taxRegistryPhone"
              ref="invoice.taxRegistryPhone"
              placeholder="请输入注册电话"
              maxlength="20"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '注册电话')"
            >
              <span slot="label" class="item-title">
                注册电话
              </span>
            </van-field>
            <!-- 开户银行 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              :required="invoice.invoiceType == '1'"
              v-model="invoice.bankBranchesName"
              ref="invoice.bankBranchesName"
              placeholder="请输入开户银行"
              maxlength="50"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入开户银行')"
            >
              <span slot="label" class="item-title">
                开户银行
              </span>
            </van-field>
            <!-- 银行账号 -->
            <van-field
              class="item"
              rows="1"
              autosize
              type="textarea"
              input-align="right"
              :required="invoice.invoiceType == '1'"
              v-model="invoice.bankAccount"
              ref="invoice.bankAccount"
              placeholder="请输入银行账号"
              maxlength="30"
              @focus="e => (e.target.placeholder = '')"
              @blur="e => (e.target.placeholder = '请输入银行账号')"
            >
              <span slot="label" class="item-title">
                银行账号
              </span>
            </van-field>
          </div>
          <!-- 折叠按钮 -->
          <div
            class="item"
            v-show="invoice.invoiceType != '1'"
            style="justify-content:center;align-items:center;background:white;"
            @click="unfolding = !unfolding"
          >
            <van-icon :name="unfolding ? 'arrow-up' : 'arrow-down'" />
          </div>
        </div>
      </div>
    </div>
    <div style="height: 80px; width: 100%; overflow: hidden;">
      <confirmBottom title="下一步" @click="savePrivyInfo" />
    </div>
    <van-popup v-model="datePickerShow" position="bottom">
      <van-datetime-picker
        type="date"
        title="选择时间"
        v-model="currentDate"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="onDatePickerConfirm"
        @cancel="datePickerType = ''"
      />
    </van-popup>
    <van-popup v-model="dataPickerShow" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :default-index="defaultIndex"
        :columns="pickerData"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
  </div>
</template>

<script>
import Config from "@src/common/json/newCoreDataDictionary";
import confirmBottom from "@src/components/confirmBottom.vue";
import WhetherSelect from "../vehicleInfo/whetherSelect.vue";
import StepsNavBar from "@/components/stepsNavBar.vue";
import { RelationPeople } from "@src/store/modules/vehicle/vehicle";
import { mapState, mapMutations } from "vuex";
import { Toast } from "vant";
import {
  getRelationPeople,
  updateRelationPeople,
  saveQuote,
  getPeopleSwitch
} from "@src/http/module/vehicle";
import _ from "lodash";
import { trackMap } from "@src/utils/index.js";
export default {
  components: {
    confirmBottom,
    [StepsNavBar.name]: StepsNavBar,
    [WhetherSelect.name]: WhetherSelect
  },
  data: () => {
    return {
      datePickerType: "",
      currentDate: new Date(),
      minDate: new Date(1949, 9, 1),
      maxDate: new Date(2100, 11, 31),
      //证件类型 个人
      identityTypes01: Object.keys(Config.identifyType01).map(key => ({
        text: Config.identifyType01[key],
        value: key
      })),
      //证件类型 单位
      identityTypes02: Object.keys(Config.identifyType02).map(key => ({
        text: Config.identifyType02[key],
        value: key
      })),
      //职业
      professions: Object.keys(Config.profession).map(key => ({
        text: Config.profession[key],
        value: key
      })),
      //国籍
      //countryCodeTJ nationality nationality2
      nationalitys: Object.keys(Config.nationality).map(key => ({
        text: Config.nationality[key],
        value: key
      })),
      unitNatures: Object.keys(Config.unitType).map(key => ({
        text: Config.unitType[key],
        value: key
      })),
      nations: Object.keys(Config.nation).map(key => ({
        text: Config.nation[key],
        value: key
      })),
      dataPickerType: "",
      pickerData: [],
      defaultIndex: 0,
      areaCode: "",
      comCName: "",
      unfolding: false,
      realName: ""
    };
  },
  created() {
    this.initPrivyInfo();
  },
  activated() {
    const userInfo = this.localStorage.get("userInfo", {});
    const comCode = userInfo.comCode || "";
    this.areaCode = comCode.substr(0, 4);
    this.comCName = userInfo.comCName || "";
    if (this.areaCode == "3101" || this.areaCode == "3109") {
      if (!this.identityTypes01.find(item => item.value == "516")) {
        this.identityTypes01.push({
          text: "港澳居民来往内地通行证",
          value: "516"
        });
        this.identityTypes01.push({
          text: "台湾居民来往内地通行证",
          value: "511"
        });
      }
    }
    getPeopleSwitch({ comCode })
      .then(res => {
        if (res.code == "1") {
          this.realName = res.data.checkFlag;
        }
      })
      .catch(error => {});
    // if (!this.control.vehiclePrivyInfo) {
    //   this.initPrivyInfo();
    // } else {
    if (this.orderId) {
      getRelationPeople({ orderId: this.orderId }).then(
        res => {
          if (res.code === "1") {
            if (!res.data.applicant) return this.initPrivyInfo();
            let policyHolder = this.$utils.removeEmpty(res.data.applicant);
            if (res.data.applicant) {
              if (policyHolder.relationFlag == "1") {
                policyHolder = Object.assign(
                  policyHolder,
                  this.$utils.removeEmpty(this.relationPeopleFromCarOwner())
                );
              }
              if (policyHolder.custType == "01") {
                policyHolder.occupation = policyHolder.occupation || "900";
              }
              this.setPolicyHolder(policyHolder);
            }
            let recognizee = this.$utils.removeEmpty(res.data.insured);
            if (res.data.insured) {
              if (recognizee.relationFlag == "2") {
                recognizee = Object.assign(
                  recognizee,
                  this.$utils.removeEmpty(policyHolder),
                  { relationFlag: "2" }
                );
              }
              if (recognizee.relationFlag == "3") {
                recognizee = Object.assign(
                  recognizee,
                  this.$utils.removeEmpty(this.relationPeopleFromCarOwner()),
                  { relationFlag: "3" }
                );
              }
              if (recognizee.custType == "01") {
                recognizee.occupation = recognizee.occupation || "900";
              }
              this.setRecognizee(recognizee);
            }
            if (res.data.restOptions) {
              this.setRestOptions(res.data.restOptions);
            }
            let invoice = res.data.invoice;
            if (invoice) {
              this.setInvoice(invoice);
              if (invoice.invoiceRelation == "2") {
                //同被保人
                this.changeInvoice("2");
              } else {
                //同投保人
                this.changeInvoice("1");
              }
            } else {
              this.changeInvoice("1");
            }
          } else {
            if (!this.control.vehiclePrivyInfo) {
              this.initPrivyInfo();
            } else {
              this.$dialog.alert({ message: res.msg });
            }
          }
        },
        error => {}
      );
    }
    this.control.vehiclePrivyInfo = true;
  },
  filters: {
    date(date) {
      return (date || "").split(" ")[0].replace(/-/g, "/") || "请选择";
    }
  },
  watch: {
    datePickerType(type) {
      switch (type) {
        case "policyHolder.identityStartDate":
        case "recognizee.identityStartDate": {
          this.currentDate = new Date();
          this.minDate = new Date(1949, 9, 1);
          this.maxDate = new Date();
          break;
        }
        case "policyHolder.identityEndDate": {
          if (this.policyHolder.identifyStartDate) {
            let minDate = new Date();
            if (this.policyHolder.identifyStartDate) {
              minDate = new Date(
                this.policyHolder.identifyStartDate.replace(/-/g, "/")
              );
            }
            this.minDate = this.isValidDate(minDate) ? minDate : new Date();
          }
          this.maxDate = new Date(2100, 11, 31);
          break;
        }
        case "recognizee.identityEndDate": {
          if (this.recognizee.identifyStartDate) {
            let minDate = new Date(this.recognizee.identifyStartDate);
            if (this.recognizee.identifyStartDate) {
              minDate = new Date(
                this.recognizee.identifyStartDate.replace(/-/g, "/")
              );
            }
            this.minDate = this.isValidDate(minDate) ? minDate : new Date();
          }
          this.maxDate = new Date(2100, 11, 31);
          break;
        }
        case "policyHolder.birthDate":
        case "recognizee.birthDate": {
          let maxDate = new Date();
          maxDate.setFullYear(maxDate.getFullYear() - 18);
          this.currentDate = maxDate;
          this.maxDate = maxDate;
          break;
        }
      }
      if (_.result(this, type, "")) {
        this.currentDate = new Date(
          _.result(this, type, "").replace(/-/g, "/")
        );
      }
    },
    "policyHolder.vehicleRelation"(v) {
      if (this.sameFlag) {
        this.recognizee.vehicleRelation = v;
      }
    },
    "policyHolder.sex"(v) {
      if (this.sameFlag) {
        this.recognizee.sex = v;
      }
    },
    "policyHolder.identityNo"(v) {
      if (this.policyHolder.identityType == "111" && v) {
        if (v.length == 15 || v.length == 18) {
          let { status } = this.$validate({ idcard: "不符合规则" }, v);
          if (status) {
            const { birthday, age, sex } = this.parsingIdentifyNo(v);
            this.policyHolder.birthday = birthday;
            this.policyHolder.age = age;
            this.policyHolder.sex = sex;
          }
        }
      }
    },
    "recognizee.identityNo"(v) {
      if (this.recognizee.identityType == "111" && v) {
        if (v.length == 15 || v.length == 18) {
          let { status } = this.$validate({ idcard: "不符合规则" }, v);
          if (status) {
            const { birthday, age, sex } = this.parsingIdentifyNo(v);
            this.recognizee.birthday = birthday;
            this.recognizee.age = age;
            this.recognizee.sex = sex;
          }
        }
      }
    },
    "invoice.invoiceType"(v) {
      if (v == "1") {
        this.unfolding = true;
      } else {
        this.unfolding = false;
      }
    },
    "invoice.taxPayerType"(v) {
      if (v == "3" || v == "2") {
        this.invoice.invoiceType = "3";
      }
    },
    "invoice.clientType"(v) {
      if (v == "3") {
        this.invoice.invoiceType = "2";
      }
    }
  },
  computed: {
    ...mapState("vehicle", {
      orderId: ({ orderId }) => orderId,
      channel: ({ projectChannel }) => projectChannel,
      car: ({ car }) => car,
      carOwner: ({ carOwner }) => carOwner,
      policyHolder: ({ policyHolder }) => policyHolder,
      recognizee: ({ recognizee }) => recognizee,
      invoice: ({ invoice }) => invoice,
      restOptions: ({ restOptions }) => restOptions,
      quote: ({ quoteInfo }) => quoteInfo,
      insurancePlan: ({ insurancePlan }) => insurancePlan,
      historyPolicy: ({ historyPolicy }) => historyPolicy,
      control: ({ control }) => control,
      vehicleType: ({ vehicleType }) => vehicleType
    }),
    plan: {
      get: function() {
        return (
          this.quote.plans.find(
            plan => plan.planCode === this.quote.planCode
          ) ||
          this.quote.plans[0] ||
          {}
        );
      },
      set: function(plan) {
        this.$set(this.quote.plans, this.active, plan);
      }
    },
    invoiceIsCompany() {
      if (this.invoice.invoiceRelation == "1") {
        //同投保人
        return this.policyHolder.custType == "02";
      } else if (this.invoice.invoiceRelation == "2") {
        //同被保人
        return this.recognizee.custType == "02";
      }
      return false;
    },
    identifyDate() {
      if (this.areaCode == "3401" || this.comCName.indexOf("安顺中") != -1) {
        return true;
      }
      return false;
    },
    addressRequired() {
      //贵州 安徽、河南地区必填
      if (
        this.areaCode == "5201" ||
        this.areaCode == "3401" ||
        this.areaCode == "4101"
      ) {
        return true;
      }
      return false;
    },
    policyHolderIdentifyLongTerm: {
      get: function() {
        return this.policyHolder.identifyLongTerm == "1";
      },
      set: function(v) {
        this.policyHolder.identifyLongTerm = v ? "1" : "0";
        if (this.sameFlag) {
          this.recognizeeIdentifyLongTerm = v;
        }
      }
    },
    recognizeeIdentifyLongTerm: {
      get: function() {
        return this.recognizee.identifyLongTerm == "1";
      },
      set: function(v) {
        this.recognizee.identifyLongTerm = v ? "1" : "0";
      }
    },
    policyHolderPhone: {
      get: function() {
        if (!this.policyHolder.phone) return "";
        if (
          this.policyHolder.phone.length < 11 ||
          this.policyHolder.custType == "02" ||
          this.policyHolder.relationFlag == "0"
        ) {
          return this.policyHolder.phone;
        }
        return (
          this.policyHolder.phone.substr(0, 3) +
          "****" +
          this.policyHolder.phone.substr(7)
        );
      },
      set: function(v) {
        v = v || "";
        if (v && v.match(/\*/)) {
          if (v.length != 11) {
            this.policyHolder.phone = "";
          }
          return;
        }
        this.policyHolder.phone = v;
        this.policyHolder.phone = v.replace(/[^0-9]/g, "");
      }
    },
    recognizeePhone: {
      get: function() {
        if (!this.recognizee.phone) return "";
        if (
          this.recognizee.phone.length < 11 ||
          this.recognizee.custType == "02" ||
          this.recognizee.relationFlag == "0"
        ) {
          return this.recognizee.phone;
        }
        return (
          this.recognizee.phone.substr(0, 3) +
          "****" +
          this.recognizee.phone.substr(7)
        );
      },
      set: function(v) {
        v = v || "";
        if (v && v.match(/\*/)) {
          if (v.length != 11) {
            this.recognizee.phone = "";
          }
          return;
        }
        this.recognizee.phone = v;
        this.recognizee.phone = v.replace(/[^0-9]/g, "");
      }
    },
    invoicePhoneNo: {
      get: function() {
        if (!this.invoice.phoneNo) return "";
        if (this.invoice.phoneNo.length < 11) {
          return this.invoice.phoneNo;
        }
        return (
          this.invoice.phoneNo.substr(0, 3) +
          "****" +
          this.invoice.phoneNo.substr(7)
        );
      },
      set: function(v) {
        v = v || "";
        if (v && v.match(/\*/)) {
          if (v.length != 11) {
            this.invoice.phoneNo = "";
          }
          return;
        }
        this.invoice.phoneNo = v;
        this.invoice.phoneNo = v.replace(/[^0-9]/g, "");
      }
    },
    clientTypes() {
      return [
        { text: "境内个人、政府机关及事业单位", value: "1" },
        { text: "境内企业", value: "2" },
        { text: "境外个人或机构", value: "3" }
      ];
    },
    taxPayerTypes() {
      if (this.invoice.clientType == "1") {
        //境内个人、政府机关及事业单位
        return [{ text: "个人", value: "4" }];
      } else if (this.invoice.clientType == "2") {
        //境内企业
        return [
          { text: "一般纳税人", value: "1" },
          { text: "小规模纳税人", value: "2" },
          { text: "非增值税纳税人", value: "3" }
        ];
      } else if (this.invoice.clientType == "3") {
        //境外个人或机构
        return [{ text: "非增值税纳税人", value: "3" }];
      }
      return [];
    },
    invoiceTypes() {
      let invoiceTypes = [
        { text: "增值税普通发票", value: "2" },
        { text: "增值税电子普通发票", value: "3" },
        { text: "其他发票", value: "4" }
      ];
      if (this.invoice.clientType == "1") {
        //境内个人、政府机关及事业单位
      } else if (this.invoice.clientType == "2") {
        //境内企业
        if (this.invoice.taxPayerType == "1") {
          //一般纳税人
          invoiceTypes.unshift({ text: "增值税专用发票", value: "1" });
        }
      } else if (this.invoice.clientType == "3") {
        //境外个人或机构
      }
      return invoiceTypes;
    },
    taxPayerIdentificationRequired() {
      if (this.invoice.invoiceType == "1") {
        //增值税专用发票
        return true;
      } else if (
        this.invoice.invoiceType == "2" ||
        this.invoice.invoiceType == "3"
      ) {
        //增值税普通发票（包括纸质普通发票和电子普通发票）
        if (this.invoice.clientType == "2") {
          //客户类型选择为“境内企业”时
          return true;
        }
      }
      return false;
    },
    datePickerShow: {
      get: function() {
        return !!this.datePickerType;
      },
      set: function() {
        this.datePickerType = "";
      }
    },
    dataPickerShow: {
      get: function() {
        return !!this.dataPickerType;
      },
      set: function() {
        this.pickerData = [];
        this.dataPickerType = "";
      }
    },
    sameFlag() {
      return (
        (this.policyHolder.relationFlag == "1" &&
          this.recognizee.relationFlag == "3") ||
        this.recognizee.relationFlag == "2"
      );
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "setQuoteInfo",
      "setInsurancePlan",
      "initPolicyHolder",
      "setPolicyHolder",
      "initRecognizee",
      "setRecognizee",
      "setRestOptions",
      "initInvoice",
      "setInvoice"
    ]),
    parsingIdentifyNo(identifyNo) {
      let year, month, date, birthday, age, sex;
      if (identifyNo.length == 15) {
        year = "19" + identifyNo.substr(6, 2);
        month = identifyNo.substr(8, 2);
        date = identifyNo.substr(10, 2);
        birthday = new Date(year, month - 1, date).format();
        age = new Date().getFullYear() - year;
        sex = identifyNo.substr(-1, 1);
        if (sex % 2) {
          //奇数为男
          sex = "1";
        } else {
          //偶数为女
          sex = "2";
        }
      } else if (identifyNo.length == 18) {
        year = identifyNo.substr(6, 4);
        month = identifyNo.substr(10, 2);
        date = identifyNo.substr(12, 2);
        birthday = new Date(year, month - 1, date).format();
        age = new Date().getFullYear() - year;
        sex = identifyNo.substr(-2, 1);
        if (sex % 2) {
          //奇数为男
          sex = "1";
        } else {
          //偶数为女
          sex = "2";
        }
      }
      return { year, month, date, birthday, age, sex };
    },
    initPrivyInfo() {
      if (!this.policyHolder.name || this.policyHolder.relationFlag == "1") {
        this.policyHolder.relationFlag = "1";
        this.same("1", "policyHolder");
      }
      if (!this.recognizee.name || this.recognizee.relationFlag == "3") {
        this.recognizee.relationFlag = "3";
        this.same("3", "recognizee");
      }
      // 单位默认值 TUDO chenjiafeng
      if (this.policyHolder.custType == "02") {
        this.invoice.clientType = "2";
      }
      this.invoice.invoiceRelation = "1";
      this.changeInvoice("1");
      if (this.historyPolicy) {
        const { policyHolder, recognizee, invoice } = this.historyPolicy;
        if (this.historyPolicy.policyHolder) {
          const {
            identityType,
            identityNo,
            custType,
            relationFlag
          } = policyHolder;
          if (relationFlag == "1") {
            this.policyHolderPhone = this.carOwner.phoneNo;
            policyHolder.phone = this.carOwner.phoneNo;
          }
          if (identityType == "111") {
            const { birthday, age, sex } = this.parsingIdentifyNo(identityNo);
            policyHolder.birthday = birthday;
            policyHolder.age = age;
            policyHolder.sex = sex;
          }
          if (custType == "01") {
            policyHolder.occupation = policyHolder.occupation || "900";
            policyHolder.nationality = policyHolder.nationality || "CHN";
          }
          this.setPolicyHolder(policyHolder);
        }
        if (recognizee) {
          const {
            identityType,
            identityNo,
            custType,
            relationFlag
          } = recognizee;
          if (relationFlag == "2") {
            this.recognizeePhone = this.policyHolder.phone;
            recognizee.phone = this.policyHolder.phone;
          }
          if (relationFlag == "3") {
            this.recognizeePhone = this.carOwner.phoneNo;
            recognizee.phone = this.carOwner.phoneNo;
          }
          if (identityType == "111") {
            const { birthday, age, sex } = this.parsingIdentifyNo(identityNo);
            recognizee.birthday = birthday;
            recognizee.age = age;
            recognizee.sex = sex;
          }
          if (recognizee.custType == "01") {
            recognizee.occupation = recognizee.occupation || "900";
            recognizee.nationality = recognizee.nationality || "CHN";
          }
          this.setRecognizee(recognizee);
        }
        if (invoice) {
          this.setInvoice(invoice);
        }
      }
    },
    back() {
      this.$router.push({ name: "insurancePlans" });
    },
    identityNoBlur(e, type) {
      let identityType = "";
      let identityNo = "";
      if (type == "policyHolder") {
        identityType = this.policyHolder.identityType;
        identityNo = this.policyHolder.identityNo;
      } else if (type == "recognizee") {
        identityType = this.recognizee.identityType;
        identityNo = this.recognizee.identityNo;
      }
      if (identityType == "111") {
        const { status, msg } = this.$validate(
          { idcard: "不符合规则" },
          identityNo
        );
        if (!status) {
          this.$toast(msg);
          e.target.focus();
        }
      }
      e.target.placeholder = "请输入证件号码";
      if (type == "policyHolder") {
        e.target.placeholder =
          this.realName == "1" && this.policyHolder.identityType == "111"
            ? "请确保投保人身份证号与缴费人身份证号一致"
            : "请输入证件号码";
      }
    },
    setPicker(type) {
      if (
        type.includes("identityType") ||
        type.includes("customerAgentCertificatetype")
      ) {
        if (type.includes("policyHolder")) {
          if (this.policyHolder.custType == "01") {
            this.pickerData = this.identityTypes01;
          } else {
            this.pickerData = this.identityTypes02;
          }
        } else {
          if (this.recognizee.custType == "01") {
            this.pickerData = this.identityTypes01;
          } else {
            this.pickerData = this.identityTypes02;
          }
        }
      } else if (type.includes("unitNature")) {
        this.pickerData = this.unitNatures;
      } else if (type.includes("occupation")) {
        this.pickerData = this.professions;
      } else if (type.includes("nationality")) {
        this.pickerData = this.nationalitys;
      } else if (type.includes("nation")) {
        let nations = Object.keys(Config.nation).map(key => ({
          text: Config.nation[key],
          value: key
        }));
        this.pickerData = nations;
      } else if (type.includes("clientType")) {
        this.pickerData = [...this.clientTypes];
      } else if (type.includes("taxPayerType")) {
        this.pickerData = [...this.taxPayerTypes];
      } else if (type.includes("invoiceType")) {
        this.pickerData = [...this.invoiceTypes];
      }
      this.pickerData = [{ text: "请选择", value: "" }, ...this.pickerData];
      this.defaultIndex = this.pickerData.findIndex(
        item => item.value == _.result(this, type, "")
      );
      this.dataPickerType = type;
    },
    dataPickerConfirm(data) {
      const key = this.dataPickerType.split(".")[1];
      if (this.dataPickerType.includes("policyHolder")) {
        this.policyHolder[key] = data.value;
        if (this.sameFlag) {
          //同投保人
          this.recognizee[key] = data.value;
        }
      } else if (this.dataPickerType.includes("recognizee")) {
        this.recognizee[key] = data.value;
      } else if (this.dataPickerType.includes("invoice")) {
        this.invoice[key] = data.value;
        if (key == "clientType") {
          this.changeInvoiceClientType(data.value);
        } else if (key == "taxPayerType") {
          this.changeInvoiceTaxPayerType(data.value);
        }
      }
      this.dataPickerShow = false;
    },
    dataPickerCancel() {
      this.dataPickerShow = false;
    },
    input(type) {
      if (this.sameFlag) {
        //同被保人
        this.recognizee[type] = this.policyHolder[type];
      }
      // 1 投保人 2 被保人
      this.changeInvoice(this.invoice.invoiceRelation);
    },
    isValidDate(date) {
      return date instanceof Date && !isNaN(date.getTime());
    },
    changeCustType(type, value) {
      this[type].identityType = "";
      if (value == "01") {
        //个人
        if (this.areaCode == "5201" || this.areaCode == "5301") {
          //显示职业的地区
          this[type].occupation = "900";
        }
        // 投保人不同车主时，客户类型为个人时，证件类型默认为身份证 TUDO chenjiafeng
        this[type].identityType = "111";
        this.invoice.clientType = "";
      } else if (value == "02") {
        //单位
        this[type].unitNature = "300";
        this.invoice.clientType = "2";
        // 投保人不同车主时，客户类型为单位时，证件类型默认为组织机构代码证 TUDO chenjiafeng
        this[type].identityType = "801";
      }
      this.changeInvoice(this.invoice.invoiceRelation);
    },
    changeInvoice(value) {
      let invoiceObject = this.policyHolder;
      if (value === "1") {
        // 同投保人
        this.invoice.taxCompanyName = this.policyHolder.name;
      } else if (value === "2") {
        // 同被保人
        this.invoice.taxCompanyName = this.recognizee.name;
        invoiceObject = this.recognizee;
      }
      this.invoice.invoiceTitle = invoiceObject.name;
      this.invoice.email = invoiceObject.email;
      this.invoice.phoneNo = invoiceObject.phone;
      this.invoicePhoneNo = this.invoice.phoneNo;
      this.invoice.taxPayerIdentification = invoiceObject.identityNo;
      if (invoiceObject.custType == "01") {
        //默认 境内个人
        // this.changeInvoiceClientType("1");
        this.invoice.clientType = "";
        this.invoice.invoiceType = "";
        this.invoice.taxPayerType = "";
        this.invoice.bankAccount = ""; //开户行帐号
        this.invoice.bankAgentCode = ""; //开户银行代码
        this.invoice.bankAgentName = ""; //开户银行名称
        this.invoice.bankBranchesCode = ""; //开户银行支行代码
        this.invoice.bankBranchesName = ""; //开户银行支行名称
        this.invoice.companyNameType = ""; //公司名称类型
        this.invoice.taxCompanyName = ""; //公司名称
        this.invoice.taxRegistryAddress = ""; //税务登记地址
        this.invoice.taxRegistryPhone = "";//税务登记电话
      } else {
        this.invoice.bankAccount = this.invoice.accountNo; //开户行帐号
        this.invoice.bankAgentCode = ""; //开户银行代码
        this.invoice.bankAgentName = ""; //开户银行名称
        this.invoice.bankBranchesCode = ""; //开户银行支行代码
        this.invoice.bankBranchesName = this.invoice.bankCode; //开户银行支行名称
        this.invoice.companyNameType = this.invoice.companyNameType; //公司名称类型
        this.invoice.taxCompanyName = this.invoice.companyName; //公司名称
        this.invoice.taxRegistryAddress = this.invoice.taxRegisterAddress; //税务登记地址
        this.invoice.taxRegistryPhone = this.invoice.taxRegisterTel;//税务登记电话
        //默认 境内企业
        this.changeInvoiceClientType("2");
      }
    },
    changeIsNeedInvoice(isNeedInvoice) {
      if (isNeedInvoice == "1") {
        this.invoice.invoiceType = "3";
      } else {
        this.invoice.invoiceType = "2";
      }
    },
    same(relationFlag, who) {
      if (who === "policyHolder") {
        if (relationFlag == "1") {
          let mix = {
            vehicleRelation: "1",
            relationFlag: "1"
          };
          if (this.carOwner.custType == "01") {
            mix.occupation = this.carOwner.occupation || "900";
          }
          this.setPolicyHolder(
            Object.assign({}, this.relationPeopleFromCarOwner(), mix)
          );
          // 单位默认值 TUDO chenjiafeng
          if (this.policyHolder.custType == "02") {
            this.policyHolder.unitNature = "300";
          }
        } else {
          // this.initPolicyHolder();
          // 当投保人不同车主时，投保人与车辆关系不能“所有”,默认“使用” TODU chenjiafeng
          this.policyHolder.vehicleRelation = "2";
        }
        if (this.invoice.invoiceRelation == "1") {
          this.invoicePhoneNo = this.policyHolder.phone;
        }
        this.same(this.recognizee.relationFlag, "recognizee");
      } else if (who === "recognizee") {
        let vehicleRelation = "2";
        if (relationFlag == "2") {
          if (this.policyHolder.relationFlag == "1") {
            //投保人同车主
            vehicleRelation = "1";
          }
          let info = Object.assign({}, this.policyHolder, {
            vehicleRelation,
            relationFlag
          });
          if (this.policyHolder.custType == "01") {
            info.occupation = this.policyHolder.occupation || "900";
          }
          this.setRecognizee(info);
        } else if (relationFlag == "3") {
          if (this.policyHolder.relationFlag == "1") {
            //投保人同车主
            vehicleRelation = "1";
          }
          let mix = {
            vehicleRelation,
            relationFlag
          };
          if (this.carOwner.custType == "01") {
            mix.occupation = this.carOwner.occupation || "900";
          }
          this.setRecognizee(
            Object.assign({}, this.relationPeopleFromCarOwner(), mix)
          );
          // 单位默认值 TUDO chenjiafeng
          this.recognizee.unitNature = "300";
          this.recognizee.vehicleRelation = "1";
        } else {
          // this.initRecognizee();
        }
        if (this.invoice.invoiceRelation == "2") {
          this.invoicePhoneNo = this.recognizee.phone;
        }
      }
      this.changeInvoice(this.invoice.invoiceRelation);
    },
    relationPeopleFromCarOwner() {
      return Object.assign({}, RelationPeople, this.carOwner, {
        phone: this.carOwner.phoneNo,
        sex: this.carOwner.gender,
        vehicleRelation: "1"
      });
    },
    OCR(type) {
      if (type == "policyHolder" && this.policyHolder.relationFlag == "1") {
        return this.$toast("同车主不可修改");
      } else if (type == "recognizee" && this.sameFlag) {
        return this.$toast("同投保人不可修改");
      }
      //调用接口参数，
      //identifyType "0" - 识别行驶证; "1" - 识别身份证
      //side "face":正面 "back":反面
      //调用壳方法，传递调用参数和接受响应数据
      this.$native.identifyOCR({ identifyType: "1", side: "face" }, data => {
        /**
        姓名        name      String    N
        身份号码    cardNo    String    N
        地址        address   String    N
        性别        sex       String    N
        出生日期    birthday  String    N
        民族        folk      String    N
        正反面      side      String    N
        */
        // this.$toast("identifyOCR 接口调用成功，壳响应数据:" + data);
        if (typeof data === "string") {
          data = JSON.parse(data);
        }
        if (data.resultCode == "1") {
          data = data.responseBody;
          let result = {
            name: data.name,
            identityNo: data.cardNo,
            address: data.address,
            sex: data.sex,
            birthday: new Date(data.birthday).format(),
            folk: data.folk
          };
          if (type == "policyHolder") {
            this.setPolicyHolder(Object.assign({}, result));
            if (this.sameFlag) {
              this.setRecognizee(Object.assign({}, result, {}));
            }
          } else {
            this.setRecognizee(Object.assign({}, result, {}));
          }
          //埋点
          trackMap("c_car_personinfo_ocr", "车险投被保人OCR识别", {
            c_salesperson_type: "N",
            c_market_type: "Y",
            c_market_name: "",
            c_market_id: "",
            c_event_category: "车险出单"
          });
        } else {
          this.$dialog.alert({ message: data.resultMsg });
        }
      });
      switch (type) {
        case "policyHolder": {
          break;
        }
        case "recognizee": {
          break;
        }
        default:
          break;
      }
    },
    onDatePickerConfirm(date) {
      switch (this.datePickerType) {
        case "policyHolder.identityStartDate":
          this.policyHolder.identifyStartDate = date.format("yyyy-MM-dd");
          if (this.sameFlag) {
            //同被保人
            this.recognizee.identifyStartDate = date.format("yyyy-MM-dd");
          }
          break;
        case "policyHolder.identityEndDate":
          this.policyHolder.identifyEndDate = date.format("yyyy-MM-dd");
          if (this.sameFlag) {
            //同被保人
            this.recognizee.identifyEndDate = date.format("yyyy-MM-dd");
          }
          break;
        case "recognizee.identityStartDate":
          this.recognizee.identifyStartDate = date.format("yyyy-MM-dd");
          break;
        case "recognizee.identityEndDate":
          this.recognizee.identifyEndDate = date.format("yyyy-MM-dd");
          break;
        default:
          break;
      }
      this.datePickerType = "";
    },
    changeInvoiceClientType(clientType) {
      this.invoice.clientType = clientType;
      if (clientType == "1") {
        //境内个人、政府机关及事业单位
        this.invoice.taxPayerType = "4";
        this.invoice.invoiceType = "3";
      } else if (clientType == "2") {
        //境内企业
        this.invoice.taxPayerType = "1";
        this.invoice.invoiceType = "1";
      } else if (clientType == "3") {
        //境外个人或机构
        this.invoice.taxPayerType = "3";
        this.invoice.invoiceType = "2";
      }
    },
    changeInvoiceTaxPayerType(taxPayerType) {
      this.invoice.taxPayerType = taxPayerType;
      if (taxPayerType == "1") {
        //一般纳税人
        this.invoice.invoiceType = "1";
      } else if (taxPayerType == "2") {
        //小规模纳税人
        this.invoice.invoiceType = "2";
      } else if (taxPayerType == "3") {
        //非增值税纳税人
        this.invoice.invoiceType = "2";
        if (this.vehicleType == "4") {
          this.invoice.invoiceType = "2";
        }
      } else if (taxPayerType == "4") {
        //个人
        this.invoice.invoiceType = "3";
      }
    },
    savePrivyInfo() {
      //NOTE: 校验数据,提交数据,报价
      if (!this.validate()) {
        return;
      }
      //埋点
      trackMap("c_car_personinfo_next", "车险投被保人下一步", {
        c_salesperson_type: "N",
        c_market_type: "Y",
        c_market_name: "",
        c_market_id: "",
        c_event_category: "车险出单"
      });
      let plan = { ...this.insurancePlan };
      let quoteInfo = { ...this.quote };
      delete quoteInfo.plans;
      if (plan.trafficChecked != "1") {
        plan.trafficDuePremium = "";
      }
      if (plan.businessChecked != "1") {
        plan.businessDuePremium = "";
      }
      if (plan.wealthAccChecked != "1") {
        plan.wealthAccPremium = "";
      }

      let quoteRequest = {
        ...quoteInfo,
        insurancePlan: plan,
        owner: { ...this.carOwner },
        channel: { ...this.channel },
        vehicleCheck: { ...this.car, vinNo: this.car.frameNo },
        orderId: this.orderId,
        checks: [],
        comCode: this.localStorage.get("userInfo", {}).comCode
      };
      let quoteMixInfo = {};
      //  拖拉机费率
      if (this.car.rateClassification) {
        quoteMixInfo.regionPremRateCate = this.car.rateClassification;
      }
      let vehicleType = this.vehicleType;
      //  产品代码
      let codeSuffixValue = "A";
      if (vehicleType != "1") {
        if (vehicleType == "2") {
          codeSuffixValue = "A";
        }
        if (vehicleType == "3") {
          codeSuffixValue = "C";
        }
        if (vehicleType == "4") {
          codeSuffixValue = "B";
        }
      }
      if (this.plan.trafficChecked == "1") {
        quoteMixInfo.jqProductCode = "DF" + codeSuffixValue;
      }
      if (this.plan.businessChecked == "1") {
        quoteMixInfo.syProductCode = "DE" + codeSuffixValue;
      }
      quoteRequest = Object.assign({}, quoteRequest, quoteMixInfo);
      if (vehicleType != "1") {
        // quoteMixInfo.vehicleCheck.exhaustScale =
        //   quoteMixInfo.vehicleCheck.exhaustScale / 1000;
        //  写死
        // quoteRequest.vehicleCheck.exhaustScale = 0.025;
      }
      let params = {
        orderId: this.orderId,
        applicant: { ...this.policyHolder, orderId: this.orderId },
        insured: { ...this.recognizee, orderId: this.orderId },
        invoice: this.invoice,
        restOptions: this.restOptions,
        renewalFlag: this.car.renewalFlag,
        quoteRequest
      };
      updateRelationPeople(params).then(
        async res => {
          if (res.code === "1") {
            let {
              trafficLastClaims,
              businessLastClaims,
              floating,
              quoteCount,
              commission,
              insurancePlan,
              checks,
              uwResult
            } = res.data;
            if (insurancePlan) {
              if (insurancePlan.trafficIsEffective == "Y") {
                insurancePlan.trafficIsEffective = "1";
              } else if (insurancePlan.trafficIsEffective == "N") {
                insurancePlan.trafficIsEffective = "0";
              }
              if (insurancePlan.businessIsEffective == "Y") {
                insurancePlan.businessIsEffective = "1";
              } else if (insurancePlan.businessIsEffective == "N") {
                insurancePlan.businessIsEffective = "0";
              }
              let taxMixin = {};
              if (insurancePlan.tax) {
                taxMixin.adjustTaxValue =
                  insurancePlan.tax.adjustTaxValue < 1
                    ? insurancePlan.tax.adjustTaxValue * 100
                    : insurancePlan.tax.adjustTaxValue;
              }
              this.plan = Object.assign(
                {},
                this.plan,
                this.$utils.removeEmpty(insurancePlan),
                {
                  tax: Object.assign(
                    this.plan.tax,
                    this.$utils.removeEmpty(insurancePlan.tax),
                    taxMixin
                  )
                }
              );
              this.setQuoteInfo({
                ...this.quote,
                trafficLastClaims,
                businessLastClaims,
                floating,
                quoteCount,
                commission,
                plans: null
              });
              this.setInsurancePlan(Object.assign({}, this.plan));
              let total = 0;
              let plan = this.plan || {};
              if (plan.trafficChecked == "1") {
                if (Number(plan.trafficDuePremium)) {
                  total += plan.trafficDuePremium;
                }
                if (Number(plan.tax.sumPayment)) {
                  total += plan.tax.sumPayment;
                }
              }
              if (plan.businessChecked == "1") {
                if (Number(plan.businessDuePremium)) {
                  total += plan.businessDuePremium;
                }
              }
              if (plan.wealthAccChecked == "1") {
                if (Number(plan.wealthAccPremium)) {
                  total += plan.wealthAccPremium;
                }
              }
              let totalPremium = Number(total).toFixed(2);
              this.insurancePlan.totalPremium = totalPremium;
              this.plan.totalPremium = totalPremium;
              let userInfo = this.localStorage.get("userInfo", {});
              let { userCode, userName, comCode, staffCode, token } = userInfo;
              let saveQuoteParams = {
                ...this.quote,
                plans: [{ ...this.plan, ...this.insurancePlan }],
                comCode,
                staffCode,
                userCode,
                userName,
                orderId: this.orderId,
                token
              };
              saveQuoteParams = Object.assign(
                {},
                saveQuoteParams,
                quoteMixInfo
              );
              if (vehicleType != "1") {
                //  写死
                // saveQuoteParams.vehicleCheck.exhaustScale = 0.025;
              }
              saveQuote(saveQuoteParams).then(
                res => {
                  if (res.code === "1") {
                    this.$router.push({ name: "vehicleInsureConfirm" });
                  } else {
                    this.$dialog.alert({ message: res.msg });
                  }
                },
                error => {}
              );
            } else {
              this.$router
                .push({ name: "vehicleInsureConfirm" })
                .then(result => console.log(result))
                .catch(error => console.error(error));
            }
            // this.$toast.success("更新投被保人信息成功");
          } else {
            this.$dialog.alert({ message: res.msg });
          }
        },
        error => {
          // this.$dialog.alert({ title: error.name, message: error.message });
        }
      );
    },
    validate() {
      //验证投保人信息
      let who = "投保人";
      const base = {
        name: { required: "姓名" },
        identityType: { required: "证件类型" },
        identityNo: { required: "证件号码" },
        phone: { required: "移动电话", mobile2: "格式不正确" },
        vehicleRelation: { required: "与车辆的关系" }
      };
      let props = JSON.parse(JSON.stringify(base));
      if (this.policyHolder.custType == "01") {
        props.occupation = { required: "职业" };
        props.nationality = { required: "国籍" };
        //个人
        //北京 特有
        if (this.areaCode == "1101") {
          props.birthday = { required: "出生日期" };
        }
        if (this.policyHolder.sex == "男") {
          this.policyHolder.sex = "1";
        } else if (this.policyHolder.sex == "女") {
          this.policyHolder.sex = "2";
        }
        //云南 贵州 特有
        if (this.areaCode == "5201" || this.areaCode == "5301") {
          props.sex = { required: "性别" };
        }
        if (this.addressRequired) {
          props.address = { required: "联系地址" };
        }
        if (this.areaCode == "5201" && this.comCName.indexOf("安顺中") !== -1) {
          props.sex = { required: "性别" };
        }
        if (
          this.areaCode == "5201" &&
          this.comCName.indexOf("安顺中") !== -1 &&
          this.policyHolder.identityType === "111"
        ) {
          // 证件有效起期/证件有效止期
          props.identifyStartDate = { required: "证件有效起期" };
          props.identifyEndDate = { required: "证件有效止期" };
        }
        if (this.policyHolder.identityType === "111") {
          //TODO: 港澳台通行证暂不处理
          //|| this.carOwner.identityType === "28";
          props.identityNo.idcard = "不符合规则";
          if (this.areaCode == "3401") {
            // 证件有效起期/证件有效止期
            props.identifyStartDate = { required: "证件有效起期" };
            props.identifyEndDate = { required: "证件有效止期" };
          }
        }
        if (this.policyHolder.identityType === "03") {
          //TODO: 护照校验暂不处理
          // props.identityNo.passport = "不符合规则";;
        }
        if (this.policyHolder.identityType === "553") {
          // 外国人永久居留证
          props.identityNo.required = "外国人永久居留证";
          props.identityNo.length = {
            length: 15,
            msg: "长度为15位"
          };
        }
        if (this.policyHolder.isPhoneHolder == "1") {
          props.phoneHolderName = { required: "持有人姓名" };
          props.phoneHolderIdno = { required: "持有人身份证号" };
        }
      } else if (this.policyHolder.custType == "02") {
        //单位
        props.name = { required: "单位名称" };
        props.unitNature = { required: "单位性质" };
        props.identityNo = { companyId: "不符合规则" };
      }
      if (this.policyHolder.email) {
        this.policyHolder.email = this.policyHolder.email.trim();
        let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
        if (!reg.test(this.policyHolder.email.trim())) {
          Toast({
            forbidClick: true,
            message: "邮箱格式不正确",
            type: "fail"
          });
          return false;
        }
        // console.log(props.email,'11111111111')
        // props.email = { email: "邮箱格式不正确" };
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            this.policyHolder[prop]
          );
          if (!status) {
            console.error(prop, status, msg);
            if (this.$refs["policyHolder." + prop]) {
              this.$refs["policyHolder." + prop].focus();
            }
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(who + error);
        return false;
      }
      //验证被保人信息
      who = "被保人";
      props = JSON.parse(JSON.stringify(base));
      if (this.recognizee.custType == "01") {
        //个人
        props.occupation = { required: "职业" };
        props.nationality = { required: "国籍" };
        //北京 特有
        if (this.areaCode == "1101") {
          props.birthday = { required: "出生日期" };
        }
        //云南 贵州 特有
        if (this.recognizee.sex == "男") {
          this.recognizee.sex = "1";
        } else if (this.recognizee.sex == "女") {
          this.recognizee.sex = "2";
        }
        if (this.areaCode == "5201" || this.areaCode == "5301") {
          props.sex = { required: "性别" };
        }
        if (this.addressRequired) {
          props.address = { required: "联系地址" };
        }
        if (this.areaCode == "5201" && this.comCName.indexOf("安顺中") !== -1) {
          props.sex = { required: "性别" };
        }
        if (
          this.areaCode == "5201" &&
          this.comCName.indexOf("安顺中") !== -1 &&
          this.recognizee.identityType === "111"
        ) {
          // 证件有效起期/证件有效止期
          props.identifyStartDate = { required: "证件有效起期" };
          props.identifyEndDate = { required: "证件有效止期" };
        }
        if (this.recognizee.identityType === "111") {
          //TODO: 港澳台通行证暂不处理
          //|| this.carOwner.identityType === "28";
          props.identityNo.idcard = "不符合规则";
          if (this.areaCode == "3401") {
            // 证件有效起期/证件有效止期
            props.identifyStartDate = { required: "证件有效起期" };
            props.identifyEndDate = { required: "证件有效止期" };
          }
        }
        if (this.recognizee.identityType === "553") {
          // 外国人永久居留证
          props.identityNo.required = "外国人永久居留证";
          props.identityNo.length = {
            length: 15,
            msg: "长度为15位"
          };
        }
        if (this.recognizee.isPhoneHolder == "1") {
          props.phoneHolderName = { required: "持有人姓名" };
          props.phoneHolderIdno = { required: "持有人身份证号" };
        }
      } else if (this.recognizee.custType == "02") {
        //单位
        props.name = { required: "单位名称" };
        props.unitNature = { required: "单位性质" };
        props.identityNo = { companyId: "不符合规则" };
      }
      if (this.recognizee.email) {
        this.recognizee.emaild = this.recognizee.email.trim();
        let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
        if (!reg.test(this.recognizee.email.trim())) {
          Toast({
            forbidClick: true,
            message: "邮箱格式不正确",
            type: "fail"
          });
          return false;
        }
        // console.log(props.email,'22222222222222')
        // props.email = { email: "邮箱格式不正确" };
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            this.recognizee[prop]
          );
          if (!status) {
            console.error(prop, status, msg);
            if (this.$refs["recognizee." + prop]) {
              this.$refs["recognizee." + prop].focus();
            }
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(who + error);
        return false;
      }
      //验证开票信息
      props = {
        phoneNo: { required: "开票信息-移动电话", mobile: "不符合规则" }
      };
      if (this.invoice.email) {
        this.invoice.email = this.invoice.email.trim();
        let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
        if (!reg.test(this.invoice.email.trim())) {
          Toast({
            forbidClick: true,
            message: "邮箱格式不正确",
            type: "fail"
          });
          return false;
        }
        // props.email = { email: "邮箱格式不正确" };
      }
      if (this.areaCode == "1101") {
        // props.indiIdGatherCaptcha = { required: "身份证采集器编码" }
      }
      // if (this.taxPayerIdentificationRequired) {

      // }
      props.taxPayerIdentification = { required: "纳税人识别号" };
      // 开票信息对应单位/企业
      if (this.invoiceIsCompany) {
        props.clientType = { required: "客户类型" };
        props.taxPayerType = { required: "纳税人类型" };
        props.invoiceType = { required: "发票类型" };
        props.taxCompanyName = { required: "公司名称" };
        if (this.invoice.invoiceType == "1") {
          props.taxRegistryAddress = { required: "注册地址" };
          props.taxRegistryPhone = { required: "注册电话" };
          props.bankBranchesName = { required: "开户银行" };
          props.bankAccount = { required: "银行账号" };
        }
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(props[prop], this.invoice[prop]);
          if (!status) {
            console.error(prop, status, msg);
            if (this.$refs["invoice." + prop]) {
              this.$refs["invoice." + prop].focus();
            }
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail("开票信息" + error);
        return false;
      }
      return true;
    }
  }
};
</script>
<style lang="less" scoped>
.insure-information {
  background: #f7f7f7;
}
.content {
  width: 100%;
  height: calc(100% - 77px - 80px);
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  overflow-scrolling: touch;
  div {
    display: flex;
  }
  .content-group {
    position: relative;
    flex-direction: column;
  }
  .content-group:not(:last-child)::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 16px;
    border-bottom: 1px solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }
  .group {
    height: 42px;
    background: #f7f7f7;
    align-items: center;
    .group-icon {
      margin-right: 8px;
      width: 22px;
      height: 22px;
    }
    .group-title {
      font-size: 14px;
      color: #333333;
      font-weight: 600;
    }
  }
  .item-container {
    position: relative;
    flex-direction: column;
    background: white;
    .item-ocr-image {
      display: flex;
      align-self: flex-end;
      justify-self: flex-end;
      height: 27px;
      margin-top: 13px;
      margin-right: 15px;
      border: 1px solid;
    }
  }
  .item-container:not(:last-child)::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 16px;
    border-bottom: 1px solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }
  .item-placeholder {
    color: #ababab;
  }
  .separated {
    height: 10px;
    width: 100%;
    background: #f7f7f7;
  }
  .item {
    min-height: 48px;
    align-items: center;
    .van-cell__value {
      flex: 1.5;
    }
    .item-title {
      font-size: 14px;
      color: #333333;
    }
    .item-right {
      flex: 1;
      word-break: keep-all;
      align-items: center;
      justify-content: flex-end;
      font-size: 14px;
      color: #333333;
      overflow: hidden;
      .item-right-input {
        text-align: right;
        border: 0;
        background: transparent;
        width: 100%;
        opacity: 1;
        font-size: 14px;
        color: #333333;
      }
      .item-right-checkbox {
        height: 14px;
        color: #333333;
        padding-left: 8px;
        overflow: visible;
      }
      .item-right-button {
        align-items: center;
        pointer-events: auto;
      }
      .item-right-button-text {
        color: #3b8afe;
        font-size: 14px;
        margin: 0 8px;
      }
      .item-right-arrow {
        margin-left: 8px;
      }
    }
  }
}
input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
  color: #ababab;
  font-size: 14px;
}

input:-moz-placeholder,
textarea:-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}

input::-moz-placeholder,
textarea::-moz-placeholder {
  color: #ababab;
  font-size: 14px;
}
input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
  color: #ababab;
  font-size: 14px;
}
</style>
