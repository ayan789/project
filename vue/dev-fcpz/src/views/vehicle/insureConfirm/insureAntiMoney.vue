<!--
 * @Author: King
 * @Page: 反洗钱信息--个人
 * @Date: 2020-03-28 12:19:46
 * @Last Modified by: King
 * @Last Modified time: 2020-06-16 15:29:38
 * @FilePath: /src/views/vehicle/insureConfirm/insureAntiMoney.vue
-->
<template>
  <div>
    <nav-bar title="反洗钱信息" type="vehicle" @clickBack="clickBack" />
    <div class="content">
      <!-- 投保人客户类型---个人 -->
      <van-cell-group v-if="policyHolder.custType == '01'">
        <van-cell
          title-class="biaotitle biaotitles"
          :border="false"
          title="投保人补录信息"
        />
        <van-cell title="客户类型" value="自然人" />
        <van-field
          v-model="policyHolder.name"
          label="姓名"
          name="姓名"
          disabled
          required
          placeholder="请输入姓名"
          input-align="right"
        />
        <van-cell title="证件类型" required>
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, policyHolder.identityType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <!-- <div
            class="item-right-button"
            v-if="policyHolder.identityType == '111'"
            @click.stop="OCR('policyHolder-identityType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div> -->
        </van-cell>
        <van-field
          v-model="policyHolder.identityNo"
          label="证件号码"
          name="证件号码"
          disabled
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell
          title="证件有效止期"
          @click="
            identityLong == '1' || policyHolderIdFlag
              ? ''
              : setPicker('identifyEndDate')
          "
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.identifyEndDate
                  ? policyHolder.identifyEndDate
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="identityLong"
                @click.stop="
                  policyHolder.age > 46 && !policyHolderIdFlag
                    ? (policyHolder.identifyEndDate = '')
                    : ''
                "
                :disabled="
                  policyHolder.age > 46 && !policyHolderIdFlag ? false : true
                "
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
        <van-cell title="性别">
          <whether-select v-model="policyHolder.sex" :options="genderList" />
        </van-cell>
        <van-field
          v-model="policyHolder.age"
          label="年龄"
          name="年龄"
          type="number"
          maxlength="2"
          input-align="right"
          :disabled="policyHolder.identityType == '111'"
          placeholder="请输入年龄"
          @input="handelPolicyHolderAge"
        />
        <van-cell
          title="国籍"
          is-link
          :value="$utils.findText(nationality, policyHolder.nationality)"
        />
        <!-- @click="setPicker('nationality')" -->
        <van-field
          v-model="policyHolder.address"
          label="联系地址"
          name="联系地址"
          :readonly="policyHolderAddressFlag"
          right-icon="question-o"
          input-align="right"
          placeholder="请输入联系地址"
          @click-right-icon="handelAddressTap"
        />
        <van-cell
          title="单位性质"
          required
          is-link
          :value="$utils.findText(unitType, policyHolder.unitType)"
          @click="setPicker('unitType')"
        />
        <van-cell
          title="职业"
          is-link
          :value="$utils.findText(antiprofession, policyHolder.profession)"
          @click="setPicker('profession')"
        />
        <van-cell
          title="职务"
          is-link
          :value="$utils.findText(position, policyHolder.unitOccupationName)"
          @click="setPicker('unitOccupationName')"
        />
      </van-cell-group>

      <!-- 投保人客户类型---单位 -->
      <van-cell-group v-if="policyHolder.custType == '02'">
        <van-cell
          title-class="biaotitle biaotitles"
          :border="false"
          title="投保人补录信息"
        />
        <van-cell title="客户类型" value="法人" />
        <van-field
          v-model="policyHolder.taxRegistryNo"
          label="税务登记证号码"
          name="税务登记证号码"
          placeholder="请输入税务登记证号码"
          input-align="right"
        />
        <van-field
          v-model="policyHolder.identityNo"
          label="营业执照号码"
          name="营业执照号码"
          disabled
          input-align="right"
          placeholder="营业执照号码"
        />
        <van-cell
          title="证件有效起期"
          @click="identityLong == '1' ? '' : setPicker('identifyStartDate')"
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.identifyStartDate
                  ? policyHolder.identifyStartDate
                  : "请选择有效起期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
          </van-row>
        </van-cell>
        <van-cell
          title="证件有效止期"
          @click="identityLong == '1' ? '' : setPicker('identifyEndDate')"
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.identifyEndDate
                  ? policyHolder.identifyEndDate
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="identityLong"
                @click.stop="
                  policyHolder.age > 46
                    ? (policyHolder.identifyEndDate = '')
                    : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
        <van-field
          v-model="policyHolder.businessRange"
          label="经营范围"
          name="经营范围"
          input-align="right"
          placeholder="请输入经营范围"
        />
        <van-cell title="股东类型" @click="setPicker('shareholdertype')">
          <div class="item-right-button">
            {{
              $utils.findText(shareholderTypes, policyHolder.shareholdertype)
            }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-field
          v-model="policyHolder.address"
          label="联系地址"
          name="联系地址"
          right-icon="question-o"
          input-align="right"
          placeholder="请输入联系地址"
          @click-right-icon="handelAddressTap"
        />
        <van-cell
          title="国籍"
          is-link
          :value="$utils.findText(nationality, policyHolder.nationality)"
          @click="setPicker('nationality')"
        />
        <van-cell
          title="单位性质"
          required
          is-link
          :value="$utils.findText(unitType, policyHolder.unitType)"
          @click="setPicker('unitType')"
        />
        <van-cell
          title="职业/行业"
          is-link
          :value="$utils.findText(antiprofession1, policyHolder.profession)"
          @click="setPicker('profession')"
        />
        <van-cell
          title="法人/负责人职务"
          is-link
          :value="$utils.findText(position, policyHolder.unitOccupationName)"
          @click="setPicker('unitOccupationName')"
        />
      </van-cell-group>
      <!-- 投保人--单位--法人代表/负责人 -->
      <van-cell-group v-if="policyHolder.custType == '02'">
        <van-cell
          title-class="biaotitle"
          :border="false"
          title="法人代表/负责人"
        />
        <van-field
          v-model="policyHolder.legalReprese"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入法人代表姓名"
          maxlength="50"
          input-align="right"
        />
        <van-cell
          title="证件类型"
          required
          @click="setPicker('legalRepreseType')"
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, policyHolder.legalRepreseType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <div
            class="item-right-button"
            v-if="policyHolder.legalRepreseType == '111'"
            @click.stop="OCR('policyHolder-legalRepreseType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div>
        </van-cell>
        <van-field
          v-model="policyHolder.legalRepreseNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell title="证件有效止期" @click="setPicker('legalRepreseED')">
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.legalRepreseED
                  ? policyHolder.legalRepreseED
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="legalIdentityLong"
                @click.stop="
                  policyHolder.age > 46
                    ? (policyHolder.legalRepreseED = '')
                    : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
      </van-cell-group>
      <!-- 投保人--单位--控股股东/实际控制人 -->
      <van-cell-group v-if="policyHolder.custType == '02'">
        <van-cell
          title-class="biaotitle"
          :border="false"
          title="控股股东/实际控制人"
        />
        <van-field
          v-model="policyHolder.controlHolder"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入控股股东姓名"
          maxlength="50"
          input-align="right"
        />
        <van-cell
          title="证件类型"
          required
          @click="setPicker('controlHolderType')"
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, policyHolder.controlHolderType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <div
            class="item-right-button"
            v-if="policyHolder.controlHolderType == '111'"
            @click.stop="OCR('policyHolder-controlHolderType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div>
        </van-cell>
        <van-field
          v-model="policyHolder.controlHolderNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell title="证件有效止期" @click="setPicker('controlHolderED')">
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.controlHolderED
                  ? policyHolder.controlHolderED
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="controlIdentityLong"
                @click.stop="
                  policyHolder.age > 46
                    ? (policyHolder.controlHolderED = '')
                    : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
      </van-cell-group>
      <!-- 投保人--单位--授权经办人 -->
      <van-cell-group v-if="policyHolder.custType == '02'">
        <van-cell title-class="biaotitle" :border="false" title="授权经办人" />
        <van-field
          v-model="policyHolder.authorization"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入授权经办人姓名"
          maxlength="50"
          input-align="right"
        />
        <van-cell
          title="证件类型"
          required
          @click="setPicker('authorizationType')"
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, policyHolder.authorizationType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <div
            class="item-right-button"
            v-if="policyHolder.authorizationType == '111'"
            @click.stop="OCR('policyHolder-authorizationType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div>
        </van-cell>
        <van-field
          v-model="policyHolder.authorizationNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell title="证件有效止期" @click="setPicker('authorizationED')">
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.authorizationED
                  ? policyHolder.authorizationED
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="authorIdentityLong"
                @click.stop="
                  policyHolder.age > 46
                    ? (policyHolder.authorizationED = '')
                    : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
      </van-cell-group>
      <!-- 被保人客户类型---个人 -->
      <van-cell-group
        v-if="recognizee.relationFlag != '2' && recognizee.custType == '01'"
      >
        <van-cell
          title-class="biaotitle biaotitles"
          :border="false"
          title="被保人补录信息"
        />
        <van-cell title="客户类型" value="自然人" />
        <van-field
          v-model="recognizee.name"
          label="姓名"
          name="姓名"
          disabled
          required
          placeholder="请输入"
          input-align="right"
        />
        <van-cell title="证件类型" required>
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, recognizee.identityType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <!-- <div
            class="item-right-button"
            v-if="recognizee.identityType == '111'"
            @click.stop="OCR('recognizee-identityType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div> -->
        </van-cell>
        <van-field
          v-model="recognizee.identityNo"
          label="证件号码"
          name="证件号码"
          disabled
          required
          input-align="right"
          placeholder="请输入"
        />
        <van-cell
          title="证件有效止期"
          @click="
            identityLong1 == '1' || recognizeeIdFlag
              ? ''
              : setPicker1('identifyEndDate')
          "
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                recognizee.identifyEndDate
                  ? recognizee.identifyEndDate
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="identityLong1"
                @click.stop="
                  policyHolder.age > 46 && !recognizeeIdFlag
                    ? (recognizee.identifyEndDate = '')
                    : ''
                "
                :disabled="
                  recognizee.age > 46 && !recognizeeIdFlag ? false : true
                "
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
        <van-cell title="性别">
          <whether-select v-model="recognizee.sex" :options="genderList" />
        </van-cell>
        <van-field
          v-model="recognizee.age"
          label="年龄"
          name="年龄"
          maxlength="2"
          input-align="right"
          placeholder="请输入年龄"
          :disabled="recognizee.identityType == '111'"
          @input="handelRecognizeeAge"
        />
        <van-cell
          title="国籍"
          is-link
          :value="$utils.findText(nationality, recognizee.nationality)"
        />
        <!-- @click="setPicker1('nationality')" -->
        <van-field
          v-model="recognizee.address"
          label="联系地址"
          name="联系地址"
          right-icon="question-o"
          :readonly="recognizeeAddressFlag"
          input-align="right"
          placeholder="请输入联系地址"
          @click-right-icon="handelAddressTap"
        />
        <van-cell
          title="单位性质"
          required
          is-link
          :value="$utils.findText(unitType, recognizee.unitType)"
          @click="setPicker1('unitType')"
        />
        <van-cell
          title="职业"
          is-link
          :value="$utils.findText(antiprofession, recognizee.profession)"
          @click="setPicker1('profession')"
        />
        <van-cell
          title="职务"
          is-link
          :value="$utils.findText(position, recognizee.unitOccupationName)"
          @click="setPicker1('unitOccupationName')"
        />
        <van-cell
          title="被保人与投保人之间关系"
          is-link
          :value="
            this.policyHolder.custType == '01'
              ? $utils.findText(appliIdentity, recognizee.relation)
              : $utils.findText(appliIdentitycomp, recognizee.relation)
          "
          @click="setPicker1('relation')"
        />
      </van-cell-group>
      <!-- 被保人保人客户类型---单位 -->
      <van-cell-group
        v-if="recognizee.relationFlag != '2' && recognizee.custType == '02'"
      >
        <van-cell
          title-class="biaotitle biaotitles"
          :border="false"
          title="被保人补录信息"
        />
        <van-cell title="客户类型" value="法人" />
        <van-field
          v-model="recognizee.taxRegistryNo"
          label="税务登记证号码"
          name="税务登记证号码"
          placeholder="请输入税务登记证号码"
          input-align="right"
        />
        <van-field
          v-model="recognizee.identityNo"
          label="营业执照号码"
          name="营业执照号码"
          disabled
          input-align="right"
          placeholder="营业执照号码"
        />
        <van-cell
          title="证件有效起期"
          @click="identityLong1 == '1' ? '' : setPicker1('identifyStartDate')"
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                recognizee.identifyStartDate
                  ? recognizee.identifyStartDate
                  : "请选择有效起期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
          </van-row>
        </van-cell>
        <van-cell
          title="证件有效止期"
          @click="identityLong1 == '1' ? '' : setPicker1('identifyEndDate')"
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                recognizee.identifyEndDate
                  ? recognizee.identifyEndDate
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="identityLong1"
                @click.stop="
                  policyHolder.age > 46 ? (recognizee.identifyEndDate = '') : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
        <van-field
          v-model="recognizee.businessRange"
          label="经营范围"
          name="经营范围"
          input-align="right"
          placeholder="请输入经营范围"
        />
        <van-cell title="股东类型" @click="setPicker1('shareholdertype')">
          <div class="item-right-button">
            {{ $utils.findText(shareholderTypes, recognizee.shareholdertype) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-field
          v-model="recognizee.address"
          label="联系地址"
          name="联系地址"
          right-icon="question-o"
          input-align="right"
          placeholder="请输入联系地址"
          @click-right-icon="handelAddressTap"
        />
        <van-cell
          title="国籍"
          is-link
          :value="$utils.findText(nationality, recognizee.nationality)"
          @click="setPicker1('nationality')"
        />
        <van-cell
          title="单位性质"
          required
          is-link
          :value="$utils.findText(unitType, recognizee.unitType)"
          @click="setPicker1('unitType')"
        />
        <van-cell
          title="职业/行业"
          is-link
          :value="$utils.findText(antiprofession1, recognizee.profession)"
          @click="setPicker1('profession')"
        />
        <van-cell
          title="法人/负责人职务"
          is-link
          :value="$utils.findText(position, recognizee.unitOccupationName)"
          @click="setPicker1('unitOccupationName')"
        />
      </van-cell-group>
      <!-- 被保人--单位--法人代表/负责人 -->
      <van-cell-group
        v-if="recognizee.relationFlag != '2' && recognizee.custType == '02'"
      >
        <van-cell
          title-class="biaotitle"
          :border="false"
          title="法人代表/负责人"
        />
        <van-field
          v-model="recognizee.legalReprese"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入法人代表姓名"
          maxlength="50"
          input-align="right"
        />
        <van-cell
          title="证件类型"
          required
          @click="setPicker1('legalRepreseType')"
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, recognizee.legalRepreseType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <div
            class="item-right-button"
            v-if="recognizee.legalRepreseType == '111'"
            @click.stop="OCR('recognizee-legalRepreseType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div>
        </van-cell>
        <van-field
          v-model="recognizee.legalRepreseNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell title="证件有效止期" @click="setPicker1('legalRepreseED')">
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                recognizee.legalRepreseED
                  ? recognizee.legalRepreseED
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="legalIdentityLong1"
                @click.stop="
                  policyHolder.age > 46 ? (recognizee.legalRepreseED = '') : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
      </van-cell-group>
      <!-- 被保人--单位--控股股东/实际控制人 -->
      <van-cell-group
        v-if="recognizee.relationFlag != '2' && recognizee.custType == '02'"
      >
        <van-cell
          title-class="biaotitle"
          :border="false"
          title="控股股东/实际控制人"
        />
        <van-field
          v-model="recognizee.controlHolder"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入控股股东姓名"
          maxlength="50"
          input-align="right"
        />
        <van-cell
          title="证件类型"
          required
          @click="setPicker1('controlHolderType')"
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, recognizee.controlHolderType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <div
            class="item-right-button"
            v-if="recognizee.controlHolderType == '111'"
            @click.stop="OCR('recognizee-controlHolderType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div>
        </van-cell>
        <van-field
          v-model="recognizee.controlHolderNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell title="证件有效止期" @click="setPicker1('controlHolderED')">
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                recognizee.controlHolderED
                  ? recognizee.controlHolderED
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="controlIdentityLong1"
                @click.stop="
                  policyHolder.age > 46 ? (recognizee.controlHolderED = '') : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
      </van-cell-group>
      <!-- 投保人--单位--授权经办人 -->
      <van-cell-group
        v-if="recognizee.relationFlag != '2' && recognizee.custType == '02'"
      >
        <van-cell title-class="biaotitle" :border="false" title="授权经办人" />
        <van-field
          v-model="recognizee.authorization"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入授权经办人姓名"
          maxlength="50"
          input-align="right"
        />
        <van-cell
          title="证件类型"
          required
          @click="setPicker1('authorizationType')"
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, recognizee.authorizationType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
          <div
            class="item-right-button"
            v-if="recognizee.authorizationType == '111'"
            @click.stop="OCR('recognizee-authorizationType')"
          >
            <span class="item-right-button-text" style="color: #3B8AFE"
              >证件识别</span
            >
            <van-icon name="photograph" color="#3B8AFE" />
          </div>
        </van-cell>
        <van-field
          v-model="recognizee.authorizationNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell title="证件有效止期" @click="setPicker1('authorizationED')">
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                recognizee.authorizationED
                  ? recognizee.authorizationED
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col>
              <van-checkbox
                v-model="authorIdentityLong1"
                @click.stop="
                  policyHolder.age > 46 ? (recognizee.authorizationED = '') : ''
                "
                :disabled="policyHolder.age > 46 ? false : true"
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
      </van-cell-group>

      <van-cell-group>
        <van-cell
          title-class="biaotitle biaotitles"
          :border="false"
          title="代办人"
        />
        <van-cell title="代办人身份" required>
          <whether-select
            v-model="policyHolder.agentType"
            :options="policyHolder.custType == '01' ? agenttypes : agenttypes1"
            @change="changeAgentType"
          />
        </van-cell>
        <van-field
          :disabled="policyHolder.agentType == '01'"
          v-model="policyHolder.agentName"
          label="姓名"
          name="姓名"
          required
          placeholder="请输入姓名"
          input-align="right"
          maxlength="50"
        />
        <!-- 当代办人是其他单位 -->
        <van-cell
          title="证件类型"
          required
          v-if="policyHolder.agentType == '06'"
          @click="setPicker('agentIdentityType')"
        >
          <div class="item-right-button">
            {{
              $utils.findText(identityTypes1, policyHolder.agentIdentityType)
            }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
          </div>
        </van-cell>
        <van-cell
          title="证件类型"
          required
          v-else
          @click="
            policyHolder.agentType == '01' ? '' : setPicker('agentIdentityType')
          "
        >
          <div class="item-right-button">
            {{ $utils.findText(identityTypes, policyHolder.agentIdentityType) }}
            <van-icon class="item-right-arrow" name="arrow" color="#BFBFBF" />
            <div
              class="item-right-button"
              v-if="policyHolder.agentIdentityType == '111'"
              @click.stop="OCR('anti-identityType')"
            >
              <span class="item-right-button-text" style="color: #3B8AFE"
                >证件识别</span
              >
              <van-icon name="photograph" color="#3B8AFE" />
            </div>
          </div>
        </van-cell>
        <van-field
          :disabled="policyHolder.agentType == '01'"
          v-model="policyHolder.agentIdentityNo"
          label="证件号码"
          name="证件号码"
          required
          input-align="right"
          placeholder="请输入证件号码"
        />
        <van-cell
          title="证件有效止期"
          @click="
            identityLong2 == '1' || policyHolder.agentType == '01'
              ? ''
              : setPicker('agentIdentifyEndDate')
          "
        >
          <van-row
            type="flex"
            justify="end"
            gutter="4"
            style="word-break:keep-all;white-space: nowrap;"
          >
            <van-col
              >{{
                policyHolder.agentIdentifyEndDate
                  ? policyHolder.agentIdentifyEndDate
                  : "请选择止期" | date
              }}<van-icon
                class="item-right-arrow"
                name="arrow"
                color="#BFBFBF"
                style="vertical-align: middle;margin:0 5px"
            /></van-col>
            <van-col v-if="policyHolder.agentIdentityType == '111'">
              <van-checkbox
                v-model="identityLong2"
                @click.stop="
                  policyHolder.age > 46 && policyHolder.agentType !== '01'
                    ? (policyHolder.agentIdentifyEndDate = '')
                    : ''
                "
                :disabled="
                  policyHolder.age > 46 && policyHolder.agentType !== '01'
                    ? false
                    : true
                "
                class="cellCheckbox"
                style="margin-top:2px"
              >
                长期有效
              </van-checkbox>
            </van-col>
          </van-row>
        </van-cell>
        <van-field
          v-model="policyHolder.agentPhone"
          label="联系方式"
          name="联系方式"
          input-align="right"
          placeholder="请输入代办人联系方式"
          right-icon="question-o"
          @click-right-icon="handelContactTapC"
        />
        <van-cell
          title="证件核查情况"
          is-link
          :value="
            $utils.findText(
              agentisidentitycheck,
              policyHolder.agentIdentityCheck
            )
          "
          @click="setPicker('agentIdentityCheck')"
        />
        <van-cell title="代理业务手续是否齐全">
          <whether-select
            v-model="policyHolder.agentProcessCheck"
            :options="iscompletes"
          />
        </van-cell>
      </van-cell-group>
      <div class="recognizeeSame-box">
        <div class="checkBox">
          <span></span>
          <van-checkbox v-model="recognizeeSame" disabled="disabled">
            被保人补录信息-同投保人
          </van-checkbox>
        </div>
      </div>
    </div>
    <div style="height:80px;width:100%;background:#f7f7f7">
      <confirmBottom @click="sureAction" />
    </div>
    <!-- 日期下拉框---投保人 -->
    <van-popup v-model="showDatePicker" position="bottom" :overlay="true">
      <van-datetime-picker
        v-model="currentDate"
        type="date"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="datePickerConfirm"
        @cancel="datePickerCancel"
      />
    </van-popup>
    <!-- 日期下拉框---被保人 -->
    <van-popup v-model="showDatePicker1" position="bottom" :overlay="true">
      <van-datetime-picker
        v-model="currentDate"
        type="date"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="datePickerConfirm1"
        @cancel="datePickerCancel1"
      />
    </van-popup>
    <!-- 数据下拉框---投保人 -->
    <van-popup v-model="showDataPicker" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="pickerData"
        @confirm="dataPickerConfirm"
        @cancel="dataPickerCancel"
      />
    </van-popup>
    <!-- 数据下拉框---被保人 -->
    <van-popup v-model="showDataPicker1" position="bottom" :overlay="true">
      <van-picker
        show-toolbar
        :columns="pickerData1"
        @confirm="dataPickerConfirm1"
        @cancel="dataPickerCancel1"
      />
    </van-popup>
  </div>
</template>
<script>
import WhetherSelect from "../vehicleInfo/whetherSelect";
import confirmBottom from "@/components/confirmBottom.vue";
import Config from "@src/common/json/newCoreDataDictionary";

import { mapState, mapMutations } from "vuex";
export default {
  components: {
    WhetherSelect,
    confirmBottom
  },
  data() {
    const placeHolder = { text: "请选择", value: "" };
    // 证件类型枚举--个人
    let identityTypes = Object.keys(Config.identifyType01).map(key => ({
      text: Config.identifyType01[key],
      value: key
    }));
    identityTypes.unshift(placeHolder);
    // 证件类型枚举--单位
    let identityTypes1 = Object.keys(Config.identifyType02).map(key => ({
      text: Config.identifyType02[key],
      value: key
    }));
    identityTypes1.unshift(placeHolder);
    // 国籍枚举
    let nationality = Object.keys(Config.nationality2).map(key => ({
      text: Config.nationality2[key],
      value: key
    }));
    nationality.unshift(placeHolder);
    // 单位性质
    let unitType = Object.keys(Config.unitType).map(key => ({
      text: Config.unitType[key],
      value: key
    }));
    unitType.unshift(placeHolder);
    // 职业--个人
    let antiprofession = Object.keys(Config.antiprofession).map(key => ({
      text: Config.antiprofession[key],
      value: key
    }));
    antiprofession.unshift(placeHolder);
    // 职业--单位
    let antiprofession1 = Object.keys(Config.antiprofession1).map(key => ({
      text: Config.antiprofession1[key],
      value: key
    }));
    antiprofession1.unshift(placeHolder);
    // 职务
    let position = Object.keys(Config.position).map(key => ({
      text: Config.position[key],
      value: key
    }));
    position.unshift(placeHolder);
    // 与投保人关系--个人
    let appliIdentity = Object.keys(Config.appliIdentity).map(key => ({
      text: Config.appliIdentity[key],
      value: key
    }));
    // 与投保人关系--企业
    let appliIdentitycomp = Object.keys(Config.appliIdentity01).map(key => ({
      text: Config.appliIdentity[key],
      value: key
    }));
    appliIdentity.unshift(placeHolder);
    return {
      identityTypes,
      identityTypes1,
      nationality,
      unitType,
      antiprofession,
      antiprofession1,
      position,
      appliIdentity,
      appliIdentitycomp,
      datePickerType: "",
      datePickerType1: "",
      dataPickerType: "",
      //代理人资料是否齐全
      iscompletes: Object.keys(Config.iscomplete).map(key => ({
        text: Config.iscomplete[key],
        value: key
      })),
      //证件核查情况
      agentisidentitycheck: Object.keys(Config.agentisidentitycheck).map(
        key => ({
          text: Config.agentisidentitycheck[key],
          value: key
        })
      ),
      //代办人身份--投保人为自然人
      agenttypes: Object.keys(Config.agenttype).map(key => ({
        text: Config.agenttype[key],
        value: key
      })),
      //代办人身份--投保人为法人
      agenttypes1: Object.keys(Config.agenttype1).map(key => ({
        text: Config.agenttype1[key],
        value: key
      })),
      // 股东类型
      shareholderTypes: Object.keys(Config.shareholdertype).map(key => ({
        text: Config.shareholdertype[key],
        value: key
      })),
      minDate: new Date(1990, 1, 1), //时间选择器最小时间
      maxDate: new Date(2050, 1, 1), //时间选择器最大时间
      currentDate: new Date(), //当前时间
      genderList: [
        { text: "男", value: "1" },
        { text: "女", value: "2" }
      ], //性别
      recognizeeSame: "",
      pickerData: [],
      pickerData1: [],
      policyHolderIdFlag: false,
      policyHolderAddressFlag: false,
      recognizeeIdFlag: false,
      recognizeeAddressFlag: false
    };
  },
  filters: {
    date(date) {
      return (date || "").split(" ")[0];
    }
  },
  created() {
    console.log(this.policyHolder, "初始化带入投保人=============");
    console.log(this.recognizee, "初始化带入被保人=============");
    console.log(this.antiInfo, "初始化带入反洗钱==============");
    this.policyHolder.profession = "";
    this.policyHolder.unitOccupationName = "";
    this.recognizee.profession = "";
    this.recognizee.unitOccupationName = "";
    if (this.policyHolder.identifyEndDate) {
      this.policyHolderIdFlag = true;
    }
    if (this.recognizee.identifyEndDate) {
      this.recognizeeIdFlag = true;
    }
    if (this.policyHolder.address) {
      this.policyHolderAddressFlag = true;
    }
    if (this.recognizee.address) {
      this.recognizeeAddressFlag = true;
    }
  },
  mounted() {
    // 初始化
    this.init();
  },
  computed: {
    ...mapState("vehicle", {
      orderId: state => state.orderId,
      policyHolder: ({ policyHolder }) => policyHolder,
      recognizee: ({ recognizee }) => recognizee,
      antiInfo: ({ antiInfo }) => antiInfo
    }),
    showDatePicker: {
      get: function() {
        return !!this.datePickerType;
      },
      set: function() {
        this.datePickerType = "";
      }
    },
    showDatePicker1: {
      get: function() {
        return !!this.datePickerType1;
      },
      set: function() {
        this.datePickerType1 = "";
      }
    },
    showDataPicker: {
      get: function() {
        return !!this.pickerData.length;
      },
      set: function() {
        this.pickerData = [];
      }
    },
    showDataPicker1: {
      get: function() {
        return !!this.pickerData1.length;
      },
      set: function() {
        this.pickerData1 = [];
      }
    },
    identityLong: {
      get: function() {
        return this.policyHolder.identifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.policyHolder.identifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    identityLong1: {
      get: function() {
        return this.recognizee.identifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.recognizee.identifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    identityLong2: {
      get: function() {
        return this.policyHolder.agentIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.policyHolder.agentIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    agentIdentifyLong: {
      get: function() {
        return this.policyHolder.agentIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.policyHolder.agentIdentifyLongTerm = v ? "1" : "0";
      }
    },
    legalIdentityLong: {
      get: function() {
        return this.policyHolder.legalIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.policyHolder.legalIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    legalIdentityLong1: {
      get: function() {
        return this.recognizee.legalIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.recognizee.legalIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    controlIdentityLong: {
      get: function() {
        return this.policyHolder.controlIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.policyHolder.controlIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    controlIdentityLong1: {
      get: function() {
        return this.recognizee.controlIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.recognizee.controlIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    authorIdentityLong: {
      get: function() {
        return this.policyHolder.authorIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.policyHolder.authorIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    },
    authorIdentityLong1: {
      get: function() {
        return this.recognizee.authorIdentifyLongTerm == "1" ? true : false;
      },
      set: function(v) {
        this.recognizee.authorIdentifyLongTerm = v ? "1" : "0";
        this.$forceUpdate();
      }
    }
  },
  methods: {
    ...mapMutations("vehicle", [
      "setAntiInfo",
      "setPolicyHolder",
      "setRecognizee"
    ]),
    init() {
      //判断是否被保人同投保人
      if (this.recognizee.relationFlag === "2") {
        this.recognizeeSame = true;
      }
      //当证件类型不为身份证时---初始化年龄为空
      // suncao 投被保人年龄计算问题
      this.policyHolder.age =
        this.policyHolder.identityType != "111"
          ? ""
          : this.$getAge(this.$getIdNum(this.policyHolder.identityNo));
      this.recognizee.age =
        this.recognizee.identityType != "111"
          ? ""
          : this.$getAge(this.$getIdNum(this.recognizee.identityNo));
      // 默认国籍为中国
      this.policyHolder.nationality = !this.policyHolder.nationality
        ? "CHN"
        : this.policyHolder.nationality;
      this.recognizee.nationality = !this.recognizee.nationality
        ? "CHN"
        : this.recognizee.nationality;
      // 默认代办人身份
      if (this.policyHolder.custType == "01") {
        this.policyHolder.agentType = !this.policyHolder.agentType
          ? "01"
          : this.policyHolder.agentType;
      } else {
        this.policyHolder.agentType = !this.policyHolder.agentType
          ? "05"
          : this.policyHolder.agentType;
      }
      if (this.policyHolder.agentType == "01") {
        this.policyHolder.agentName = this.policyHolder.name; //代办人姓名
        this.policyHolder.agentIdentityNo = this.policyHolder.identityNo; //代办人证件号码
        this.policyHolder.agentIdentityType = this.policyHolder.identityType; //身份证明文件种类
        this.policyHolder.agentIdentifyEndDate = this.policyHolder.identifyEndDate; //身份证明文件有效止期
        this.policyHolder.agentIdentifyLongTerm = this.policyHolder.identifyLongTerm; //身份证明长期有效
      }
      //默认设置代办人证件类型为身份证
      this.policyHolder.agentIdentityType = !this.policyHolder.agentIdentityType
        ? "111"
        : this.policyHolder.agentIdentityType;
      // 默认代办人证件核查情况属实
      this.policyHolder.agentIdentityCheck = !this.policyHolder
        .agentIdentityCheck
        ? "100"
        : this.policyHolder.agentIdentityCheck;
      // 默认代办人长期有效为0
      this.policyHolder.agentIdentifyLongTerm = !this.policyHolder
        .agentIdentifyLongTerm
        ? "0"
        : this.policyHolder.agentIdentifyLongTerm;
      // 默认代办人代理业务手续是否齐全
      this.policyHolder.agentProcessCheck = !this.policyHolder.agentProcessCheck
        ? "1"
        : this.policyHolder.agentProcessCheck;
      //判断当证件类型不是身份证的时候，性别默认为男："1",是身份证的时候重新计算性别
      this.policyHolder.sex =
        this.policyHolder.identityType != "111"
          ? "1"
          : this.parsingIdentifyNo(this.policyHolder.identityNo).sex;
      this.recognizee.sex =
        this.recognizee.identityType != "111"
          ? "1"
          : this.parsingIdentifyNo(this.recognizee.identityNo).sex;
    },
    // 计算性别方法
    parsingIdentifyNo(identifyNo) {
      let sex, gender;
      sex = identifyNo.substr(-2, 1);
      if (sex % 2) {
        //奇数为男
        gender = "1";
        sex = "1";
      } else {
        //偶数为女
        gender = "2";
        sex = "2";
      }
      return { sex, gender };
    },
    // OCR识别
    OCR(type) {
      switch (type) {
        case "policyHolder-identityType": {
          //识别投保人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.policyHolder.name = data.name;
                this.policyHolder.identityNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "policyHolder-legalRepreseType": {
          //识别投保人--法人代表/负责人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.policyHolder.legalReprese = data.name;
                this.policyHolder.legalRepreseNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "policyHolder-controlHolderType": {
          //识别投保人--控股股东/实际控制人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.policyHolder.controlHolder = data.name;
                this.policyHolder.controlHolderNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "policyHolder-authorizationType": {
          //识别投保人--授权经办人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.policyHolder.authorization = data.name;
                this.policyHolder.authorizationNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "recognizee-identityType": {
          //识别被保人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.recognizee.name = data.name;
                this.recognizee.identityNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "recognizee-legalRepreseType": {
          //识别被保人--法人代表/负责人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.recognizee.legalReprese = data.name;
                this.recognizee.legalRepreseNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "recognizee-controlHolderType": {
          //识别被保人--控股股东/实际控制人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.recognizee.controlHolder = data.name;
                this.recognizee.controlHolderNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "recognizee-authorizationType": {
          //识别被保人--授权经办人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.recognizee.authorization = data.name;
                this.recognizee.authorizationNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        case "anti-identityType": {
          //识别代办人OCR
          this.$native.identifyOCR(
            { identifyType: "1", side: "face" },
            data => {
              if (typeof data === "string") {
                data = JSON.parse(data);
              }
              if (data.resultCode == "1") {
                data = data.responseBody;
                this.policyHolder.agentName = data.name;
                this.policyHolder.agentIdentityNo = data.cardNo;
              } else {
                this.$dialog.alert({ message: data.resultMsg });
              }
            }
          );
          break;
        }
        default:
          break;
      }
    },
    setPicker(type) {
      this.pickerData = [];
      this.dataPickerType = type;
      switch (type) {
        case "identifyStartDate": {
          this.minDate = new Date(1949, 9, 1);
          this.maxDate = new Date();
          this.datePickerType = type;
          break;
        }
        case "identifyEndDate":
        case "legalRepreseED": //法人代表/负责人 证件止期
        case "controlHolderED": //控股股东/实际控制人 证件止期
        case "authorizationED": //授权经办人 证件止期
        case "agentIdentifyEndDate": {
          this.minDate = new Date();
          this.maxDate = new Date(2100, 11, 31);
          this.datePickerType = type;
          break;
        }
        case "identityType":
        case "legalRepreseType":
        case "controlHolderType":
        case "authorizationType": {
          this.pickerData = this.identityTypes;
          break;
        }
        case "agentIdentityType": {
          if (this.policyHolder.agentType == "06") {
            this.pickerData = this.identityTypes1;
          } else {
            this.pickerData = this.identityTypes;
          }
          break;
        }
        case "nationality": {
          //国籍
          this.pickerData = this.nationality;
          break;
        }
        case "profession": {
          if (this.policyHolder.custType == "01") {
            this.pickerData = this.antiprofession;
          } else if (this.policyHolder.custType == "02") {
            this.pickerData = this.antiprofession1;
          }
          //职业
          break;
        }
        case "unitType": {
          //单位性质
          this.pickerData = this.unitType;
          break;
        }
        case "unitOccupationName": {
          //单位性质/职业名称
          this.pickerData = this.position;
          break;
        }
        case "agentIdentityCheck": {
          // 证件核查情况
          this.pickerData = this.agentisidentitycheck;
          break;
        }
        case "shareholdertype":
          //股东类型
          this.pickerData = this.shareholderTypes;
          break;
      }
      // this.showPicker = true;
    },
    setPicker1(type) {
      this.pickerData1 = [];
      this.dataPickerType1 = type;
      switch (type) {
        case "identifyStartDate": {
          this.minDate = new Date(1949, 9, 1);
          this.maxDate = new Date();
          this.datePickerType1 = type;
          break;
        }
        case "identifyEndDate":
        case "legalRepreseED": //法人代表/负责人 证件止期
        case "controlHolderED": //控股股东/实际控制人 证件止期
        case "authorizationED": //授权经办人 证件止期
        case "agentIdentifyEndDate": {
          this.minDate = new Date();
          this.maxDate = new Date(2100, 11, 31);
          this.datePickerType1 = type;
          break;
        }
        case "identityType":
        case "legalRepreseType":
        case "controlHolderType":
        case "authorizationType":
        case "agentIdentityType": {
          this.pickerData1 = this.identityTypes;
          break;
        }
        case "nationality": {
          //国籍
          this.pickerData1 = this.nationality;
          break;
        }
        case "profession": {
          if (this.recognizee.custType == "01") {
            this.pickerData1 = this.antiprofession;
          } else if (this.recognizee.custType == "02") {
            this.pickerData1 = this.antiprofession1;
          }
          //职业
          break;
        }
        case "unitType": {
          //单位性质
          this.pickerData1 = this.unitType;
          break;
        }
        case "unitOccupationName": {
          //单位性质/职业名称
          this.pickerData1 = this.position;
          break;
        }
        case "relation": {
          // 与投保人关系
          if (this.policyHolder.custType === "01") {
            this.pickerData1 = this.appliIdentity;
          } else {
            this.pickerData1 = this.appliIdentitycomp;
          }
          break;
        }
        case "shareholdertype":
          //股东类型
          this.pickerData1 = this.shareholderTypes;
          break;
      }
    },
    datePickerConfirm(date) {
      this.policyHolder[this.datePickerType] = date.format();
      if (this.policyHolder.agentType == "01") {
        this.policyHolder.agentIdentifyEndDate = this.policyHolder.identifyEndDate;
      }
      this.datePickerType = "";
    },
    datePickerConfirm1(date) {
      this.recognizee[this.datePickerType1] = date.format();
      this.datePickerType1 = "";
    },
    datePickerCancel() {
      this.datePickerType = "";
    },
    datePickerCancel1() {
      this.datePickerType1 = "";
    },
    dataPickerConfirm(data) {
      this.policyHolder[this.dataPickerType] = data.value;
      this.dataPickerType = "";
      this.pickerData = [];
    },
    dataPickerConfirm1(data) {
      this.recognizee[this.dataPickerType1] = data.value;
      this.dataPickerType = "";
      this.pickerData1 = [];
    },
    dataPickerCancel() {
      this.dataPickerType = "";
      this.pickerData = [];
    },
    dataPickerCancel1() {
      this.dataPickerType = "";
      this.pickerData1 = [];
    },
    clickBack() {
      this.$router.go(-1);
    },
    // 监听投保人年龄输入
    handelPolicyHolderAge(value) {
      this.policyHolder.age = value.replace(/[^0-9]/g, "")
      if (value && Number(value) <= 46) {
        this.policyHolder.identifyLongTerm = "0";
      }
    },
    // 监听被保人年龄输入
    handelRecognizeeAge(value) {
      this.recognizee.age = value.replace(/[^0-9]/g, "")
      if (value && Number(value) <= 46) {
        this.recognizee.identifyLongTerm = "0";
      }
    },
    // 点击联系地址图标
    handelAddressTap() {
      this.$dialog.alert({ message: "多个地址之间请用^号分隔，最多不超过3个" });
    },
    // 点击联系方式图标
    handelContactTapC() {
      this.$dialog.alert({
        message: "多个联系方式之间请用^号分隔，最多不超过3个"
      });
    },
    // 判断代办人身份为本人
    changeAgentType(value) {
      if (value === "01") {
        this.policyHolder.agentName = this.policyHolder.name; //代办人姓名
        this.policyHolder.agentIdentityNo = this.policyHolder.identityNo; //代办人证件号码
        this.policyHolder.agentIdentityType = this.policyHolder.identityType; //身份证明文件种类
        this.policyHolder.agentIdentifyEndDate = this.policyHolder.identifyEndDate; //身份证明文件有效止期
        this.policyHolder.agentIdentifyLongTerm = this.policyHolder.identifyLongTerm; //身份证明长期有效
      } else {
        this.policyHolder.agentName = ""; //代办人姓名
        this.policyHolder.agentIdentityNo = ""; //代办人证件号码
        this.policyHolder.agentIdentityType = "111"; //身份证明文件种类
        this.policyHolder.agentIdentifyEndDate = ""; //身份证明文件有效止期
        this.policyHolder.agentIdentifyLongTerm = "0"; //身份证明长期有效
      }
    },
    // 提交校验
    validate() {
      // 个人
      if (this.policyHolder.custType == "01") {
        if (this.policyHolder.age && Number(this.policyHolder.age) < 18) {
          this.$toast.fail("投保人年龄不得小于18周岁");
          return false;
        }
        if (!this.policyHolder.unitType) {
          this.$toast.fail("请选择投保人单位性质");
          return false;
        }
      }
      // 个人
      if (
        this.recognizee.relationFlag != "2" &&
        this.recognizee.custType == "01"
      ) {
        //被投保人不同于投保人
        if (this.recognizee.age && Number(this.recognizee.age) < 18) {
          this.$toast.fail("被保人年龄不得小于18周岁");
          return false;
        }
        if (!this.recognizee.unitType) {
          this.$toast.fail("请选择被保人单位性质");
          return false;
        }
      }
      // 投保人--单位
      if (this.policyHolder.custType == "02") {
        if (!this.policyHolder.unitType) {
          this.$toast.fail("请选择投保人单位性质");
          return false;
        }
        let who = "投保人";
        let identify = "";
        let prop = {
          // 法人代表/负责人
          legalProps: {
            legalReprese: { required: "姓名" },
            legalRepreseType: { required: "证件类型" },
            legalRepreseNo: { required: "证件号码" }
          },
          // 控股股东/实际控制人
          controlProps: {
            controlHolder: { required: "姓名" },
            controlHolderType: { required: "证件类型" },
            controlHolderNo: { required: "证件号码" }
          },
          // 授权经办人
          authorProps: {
            authorization: { required: "姓名" },
            authorizationType: { required: "证件类型" },
            authorizationNo: { required: "证件号码" }
          }
        };
        let props = JSON.parse(JSON.stringify(prop));
        if (this.policyHolder.legalRepreseType === "111") {
          props.legalProps.legalRepreseNo.idcard = "不符合规则";
        }
        if (this.policyHolder.controlHolderType === "111") {
          props.controlProps.controlHolderNo.idcard = "不符合规则";
        }
        if (this.policyHolder.authorizationType === "111") {
          props.authorProps.authorizationNo.idcard = "不符合规则";
        }
        try {
          Object.entries(props).forEach(item => {
            if (item[0] == "legalProps") {
              identify = "法人代表/负责人";
            } else if (item[0] == "controlProps") {
              identify = "控股股东/实际控制人";
            } else if (item[0] == "authorProps") {
              identify = "授权经办人";
            }
            Object.keys(item[1]).forEach(prop => {
              let { status, msg } = this.$validate(
                props[item[0]][prop],
                this.policyHolder[prop]
              );
              if (!status) {
                console.error(prop, status, msg);
                throw msg;
              }
            });
          });
        } catch (error) {
          this.$toast.fail(who + identify + error);
          return false;
        }
      }
      // 被保人--单位
      if (
        this.recognizee.custType == "02" &&
        this.recognizee.relationFlag != "2"
      ) {
        if (!this.recognizee.unitType) {
          this.$toast.fail("请选择被保人单位性质");
          return false;
        }
        let who = "被保人";
        let identify = "";
        let prop = {
          // 法人代表/负责人
          legalProps: {
            legalReprese: { required: "姓名" },
            legalRepreseType: { required: "证件类型" },
            legalRepreseNo: { required: "证件号码" }
          },
          // 控股股东/实际控制人
          controlProps: {
            controlHolder: { required: "姓名" },
            controlHolderType: { required: "证件类型" },
            controlHolderNo: { required: "证件号码" }
          },
          // 授权经办人
          authorProps: {
            authorization: { required: "姓名" },
            authorizationType: { required: "证件类型" },
            authorizationNo: { required: "证件号码" }
          }
        };
        let props = JSON.parse(JSON.stringify(prop));
        if (this.recognizee.legalRepreseType === "111") {
          props.legalProps.legalRepreseNo.idcard = "不符合规则";
        }
        if (this.recognizee.controlHolderType === "111") {
          props.controlProps.controlHolderNo.idcard = "不符合规则";
        }
        if (this.recognizee.authorizationType === "111") {
          props.authorProps.authorizationNo.idcard = "不符合规则";
        }
        try {
          Object.entries(props).forEach(item => {
            if (item[0] == "legalProps") {
              identify = "法人代表/负责人";
            } else if (item[0] == "controlProps") {
              identify = "控股股东/实际控制人";
            } else if (item[0] == "authorProps") {
              identify = "授权经办人";
            }
            Object.keys(item[1]).forEach(prop => {
              let { status, msg } = this.$validate(
                props[item[0]][prop],
                this.recognizee[prop]
              );
              if (!status) {
                console.error(prop, status, msg);
                throw msg;
              }
            });
          });
        } catch (error) {
          this.$toast.fail(who + identify + error);
          return false;
        }
      }
      let who = "代办人";
      const base = {
        agentName: { required: "姓名" },
        agentIdentityType: { required: "证件类型" },
        agentIdentityNo: { required: "证件号码" }
      };
      let props = JSON.parse(JSON.stringify(base));
      if (this.policyHolder.agentIdentityType === "111") {
        //TODO: 港澳台通行证暂不处理
        //|| this.carOwner.identityType === "28";
        props.agentIdentityNo.idcard = "不符合规则";
      }
      try {
        Object.keys(props).forEach(prop => {
          let { status, msg } = this.$validate(
            props[prop],
            this.policyHolder[prop]
          );
          if (!status) {
            console.error(prop, status, msg);
            throw msg;
          }
        });
      } catch (error) {
        this.$toast.fail(who + error);
        return false;
      }
      return true;
    },
    sureAction() {
      if (!this.validate()) {
        return;
      }
      //代办人信息
      let agent = Object.assign(
        {},
        {
          agentName: this.policyHolder.agentName, //代办人姓名
          agentPhone: this.policyHolder.agentPhone, //代办人联系方式
          agentIdentityNo: this.policyHolder.agentIdentityNo, //代办人证件号码
          agentType: this.policyHolder.agentType, //代办人身份
          agentProcessCheck: this.policyHolder.agentProcessCheck, //代理业务手续是否齐全
          agentIdentityType: this.policyHolder.agentIdentityType, //身份证明文件种类
          agentidentityEd: this.policyHolder.agentIdentifyEndDate, //身份证明文件有效止期
          agentIdentityCheck: this.policyHolder.agentIdentityCheck, //证件核查情况
          agentIdentifyLongTerm: this.policyHolder.agentIdentifyLongTerm //身份证明长期有效
        }
      );
      // 将投保人中的代办人信息传到被保人中
      this.recognizee.agentName = this.policyHolder.agentName; //代办人姓名
      this.recognizee.agentPhone = this.policyHolder.agentPhone; //代办人联系方式
      this.recognizee.agentIdentityNo = this.policyHolder.agentIdentityNo; //代办人证件号码
      this.recognizee.agentType = this.policyHolder.agentType; //代办人身份
      this.recognizee.agentProcessCheck = this.policyHolder.agentProcessCheck; //代理业务手续是否齐全
      this.recognizee.agentIdentityType = this.policyHolder.agentIdentityType; //身份证明文件种类
      this.recognizee.agentIdentifyEndDate = this.policyHolder.agentIdentifyEndDate; //身份证明文件有效止期
      this.recognizee.agentIdentityCheck = this.policyHolder.agentIdentityCheck; //证件核查情况
      this.recognizee.agentIdentifyLongTerm = this.policyHolder.agentIdentifyLongTerm; //身份证明长期有效

      // 投保人信息
      let policyHolder = Object.assign(this.policyHolder, {
        orderId: this.orderId, //订单id
        relation: this.recognizee.relation, //投被保人关系
        custType: this.policyHolder.custType, //客户类型
        profession: this.policyHolder.profession, //职业
        position: this.policyHolder.unitOccupationName //职务
      });
      // 被保人信息
      let recognizee = Object.assign(this.recognizee, {
        orderId: this.orderId, //订单id
        relation: this.recognizee.relation, //投被保人关系
        custType: this.recognizee.custType, //客户类型
        profession: this.recognizee.profession, //职业
        position: this.recognizee.unitOccupationName //职务
      });
      if (this.recognizee.relationFlag === "2") {
        //被保人与投保人一致
        this.recognizee.name = this.policyHolder.name; //姓名
        this.recognizee.identityType = this.policyHolder.identityType; //证件类型
        this.recognizee.identityNo = this.policyHolder.identityNo; //证件号码
        this.recognizee.identifyEndDate = this.policyHolder.identifyEndDate; //证件有效止期
        this.recognizee.identifyLongTerm = this.policyHolder.identifyLongTerm; //证件长期有效
        this.recognizee.sex = this.policyHolder.sex; //性别
        this.recognizee.age = this.policyHolder.age; //年龄
        this.recognizee.nationality = this.policyHolder.nationality; //国籍
        this.recognizee.address = this.policyHolder.address; //地址
        this.recognizee.unitType = this.policyHolder.unitType; //单位性质
        this.recognizee.profession = this.policyHolder.profession; //职业
        this.recognizee.unitOccupationName = this.policyHolder.unitOccupationName; //职务
        this.recognizee.legalReprese = this.policyHolder.legalReprese; //法人代表/负责人--姓名
        this.recognizee.legalRepreseType = this.policyHolder.legalRepreseType; //法人代表/负责人--证件类型
        this.recognizee.legalRepreseNo = this.policyHolder.legalRepreseNo; //法人代表/负责人--证件号码
        this.recognizee.legalRepreseED = this.policyHolder.legalRepreseED; //法人代表/负责人--证件有效止期
        this.recognizee.legalIdentifyLongTerm = this.policyHolder.legalIdentifyLongTerm; //法人代表/负责人--证件长期有效
        this.recognizee.controlHolder = this.policyHolder.controlHolder; //控股股东/实际控制人--姓名
        this.recognizee.controlHolderType = this.policyHolder.controlHolderType; //控股股东/实际控制人--证件类型
        this.recognizee.controlHolderNo = this.policyHolder.controlHolderNo; //控股股东/实际控制人--证件号码
        this.recognizee.controlHolderED = this.policyHolder.controlHolderED; //控股股东/实际控制人--证件有效止期
        this.recognizee.controlIdentifyLongTerm = this.policyHolder.controlIdentifyLongTerm; //控股股东/实际控制人--证件长期有效
        this.recognizee.authorization = this.policyHolder.authorization; //授权经办人--姓名
        this.recognizee.authorizationType = this.policyHolder.authorizationType; //授权经办人--证件类型
        this.recognizee.authorizationNo = this.policyHolder.authorizationNo; //授权经办人--证件号码
        this.recognizee.authorizationED = this.policyHolder.authorizationED; //授权经办人--证件有效止期
        this.recognizee.authorIdentifyLongTerm = this.policyHolder.authorIdentifyLongTerm; //授权经办人--证件长期有效
      }
      let antiInfo = { relationFlag: this.recognizee.relationFlag };
      Object.assign(
        antiInfo,
        { applicant: { ...policyHolder } },
        { insured: { ...recognizee } },
        { agent: { ...agent } }
      );
      console.log(this.policyHolder, "提交投保人=============");
      console.log(this.recognizee, "提交被保人=============");
      // 存投保人信息
      this.setPolicyHolder(policyHolder);
      // 存被保人信息
      this.setRecognizee(recognizee);
      // 存反洗钱信息
      this.setAntiInfo(antiInfo);
      this.$router.push({
        name: "vehicleInsureConfirm",
        params: { antiFlag: "1" }
      });
    }
  }
};
</script>
<style lang="less" scoped>
/deep/ .van-cell__value {
  flex: 1.5;
  span {
    // width: 100%;
    // height: 24px;
    // display: block;
    // overflow: hidden;
  }
}
/deep/ .item-right-button {
  display: inline-block;
  margin-left: 8px;
  i.van-icon {
    margin-left: 4px;
    vertical-align: top;
    margin-top: 5px;
  }
}
/deep/.whichSelected {
  .whetherVehicle {
    .active {
      background: rgba(238, 245, 255, 1);
      border: none;
      border-radius: 3px;
    }
    .whetherBack {
      border: 1px solid rgba(238, 238, 238, 1);
    }
  }
}
.nav-bar-container {
  position: fixed;
  top: 0;
  width: 100%;
  left: 0;
  z-index: 999;
}
.content {
  margin-top: 77px;
  /deep/ .van-cell-group__title {
    padding: 12px 16px;
  }
  /deep/ .van-cell--borderless {
    background-color: #f7f7f7;
    .biaotitle {
      font-weight: bold;
    }
    .biaotitles {
      font-size: 16px;
    }
  }
}
.required {
  color: red;
}
.bg81 {
  padding-top: 20px;
  background: #f7f7f7;
  height: 61px;
}
.vehicleInformatization {
  .insurePerson {
    height: 48px;
    width: 100%;
    background: #f7f7f7;
    display: flex;
  }
  .whetherVehicle {
    float: right;
  }
  .beInsurePerson {
    margin-top: 0px;
  }

  .insurePerson > span {
    height: 9px;
    display: block;
  }

  .cellCheckbox {
    background-color: white;
  }

  .checkBox {
    height: 57px;
    width: 100%;
    background: white;
  }

  .checkBox > span {
    height: 9px;
    background: #f7f7f7;
    width: 100%;
    display: block;
  }

  .checkBox > :last-child {
    height: 48px;
    padding-left: 15px;
    line-height: 48px;
    width: calc(100% - 30px);
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(51, 51, 51, 1);
  }

  .insurePerson > h3 {
    display: block;
    margin-left: 10px;
    height: 39px;
    width: calc(100% - 15px);
    line-height: 39px;
    font-size: 15px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: rgba(51, 51, 51, 1);
  }

  .vehicleTop {
    height: 0px;
    background: #f7f7f7;
  }

  .informatization {
    .particulars,
    .nonlocalCar,
    .transfer {
      height: 48px;
      // border-bottom: 1px solid #e5e6e8;
      position: relative;

      .whether {
        float: left;
        font-size: 14px;
        color: rgba(51, 51, 51, 1);
        padding-top: 14px;
      }

      .whetherRight {
        padding-top: 11px;
        float: right;

        .agency,
        .noAgency {
          padding: 5px 24px;
        }

        .agency {
          float: left;
          margin-right: 10px;
          border-radius: 14px;
          border: 1px solid rgba(59, 138, 254, 1);
          color: #3b8afe;
        }

        .noAgency {
          float: left;
          border-radius: 14px;
          border: 1px solid rgba(229, 229, 229, 1);
          color: #e5e5e5;
        }
      }
    }

    .inforagent {
      position: relative;

      .inforagentIcon {
        position: absolute;
        top: 17px;
        right: 17px;
        color: #bfbfbf;
        font-size: 14px;
      }
    }

    .vehicleInfo,
    .vehicleOwner {
      height: 48px;
      background: #f7f7f7;
      overflow: hidden;
      padding: 0 15px 0 18px;

      .vehicleLeft,
      .recognition,
      .vehicleCamera {
        padding-top: 18px;
      }

      .vehicleLeft {
        float: left;
        font-size: 15px;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
      }

      .recognition {
        margin-left: 182px;
        float: left;
        font-size: 14px;
        color: rgba(59, 138, 254, 1);
      }

      .vehicleCamera {
        float: right;
        width: 16px;
        height: 20px;
      }
    }

    .certificateEnd {
      height: 48px;
      width: 100%;

      .van-icon-arrow::before {
        margin-top: 2px;
      }
    }

    .certificateEnd > :first-child {
      float: left;
      width: calc(100% - 101px);
      line-height: 29px;
    }

    .certificateEnd > :last-child {
      float: right;
      width: 101px;
      height: 47px;
      border-bottom: 1px solid #ebedf0;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
    }

    .licenseNumber {
      position: relative;

      .licenseNumberRight {
        position: absolute;
        right: 18px;
        top: 15px;
        overflow: hidden;

        .vehicleLine {
          width: 1px;
          height: 20px;
          background: #ededed;
          float: left;
          margin-right: 20px;
        }

        .radioButton {
          float: left;
          margin-right: 23px;
        }

        .radioSearch {
          padding-top: 3px;
          float: left;

          .img {
            background-size: 16px;
          }
        }
      }
    }

    .lineBotton {
      height: 1px;
      background: #e5e6e8;
      margin-left: 15px;
    }

    .vinNumber {
      position: relative;

      .vinNumberSearch {
        position: absolute;
        right: 18px;
        top: 16px;
      }
    }

    .nonlocalCar,
    .transfer {
      #whetherCar {
        padding: 7px 30px;
        border: 1px solid rgba(229, 229, 229, 1);
        color: #e5e5e5;
      }
    }

    .vehicleMore {
      text-align: center;
      line-height: 48px;

      span {
        border-bottom: 1px solid #ababab;
        color: rgba(153, 153, 153, 1);
      }
    }

    .certificate {
      position: relative;

      .licenseNumberRight {
        position: absolute;
        right: 48px;
        top: 13px;
        overflow: hidden;

        .radioButton {
          float: left;
          margin-right: 18px;
          padding-top: 4px;
          color: #bfbfbf;
        }

        span {
          font-size: 14px;
          color: rgba(59, 138, 254, 1);
        }
      }
    }

    .vehicleConfirm {
      height: 81px;
      padding-left: 15px;
      padding-right: 16px;
      padding-top: 20px;
      background: rgba(247, 247, 247, 1);

      .confirm {
        background: rgba(59, 138, 254, 1);
        border-radius: 23px;
        height: 46px;
        line-height: 46px;
        text-align: center;
        font-size: 16px;
        color: rgba(255, 255, 255, 1);
      }
    }
  }

  .next {
    height: 61px;
    width: 100%;
    background: #f7f7f7;
    padding-top: 20px;
  }

  .next > h3 {
    margin: 0px 15px 15px 15px;
    width: calc(100% - 30px);
    height: 46px;
    border-radius: 46px;
    background: #3b8afe;
    line-height: 46px;
    text-align: center;
    font-size: 16px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
  }
  .drop_down {
    height: auto;
  }
}
.recognizeeSame-box {
  margin-top: 10px;
  background: white;
  padding: 15px 16px;
  /deep/ .van-icon {
  }
  /deep/ .van-checkbox__label {
    font-size: 14px;
    color: #333333;
  }
}
</style>
