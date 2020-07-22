<template>
  <div class="guardianLifeForm">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
    />
    <van-row
      class="rowTitle"
      type="flex"
      align="center"
    >
      <van-col class="title-img">
        <img
          src="@/assets/common/default1.png"
          alt="edit"
        />
      </van-col>
      <van-col class="title-text">“守护一生”中老年防癌险</van-col>
    </van-row>
    <!-- 投保人信息 -->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="title-container"
        style="background:#f7f7f7;"
      >
        <template
          slot="title"
          style="background:#f7f7f7;"
        >
          <van-row
            type="flex"
            justify="space-between"
            align="center"
          >
            <van-col class="card-title-text">
              <img
                src="@/assets/common/icon1.png"
                alt="edit"
              />
              投保人信息
            </van-col>
            <van-row
              type="flex"
              align="center"
              @click="selectInsuredMethod(false)"
            >
              <van-col class="select-customer-text">
                {{formData.appliName? '修改' : '选择投保人'}}
                <img
                  src="@/assets/common/icon2.png"
                  alt="edit"
                  class="card-img"
                />
              </van-col>
              <!-- <van-col class="select-customer-icon">
                <van-icon name="contact" />
              </van-col>-->
            </van-row>
          </van-row>
        </template>
      </van-cell>
      <!-- 投保人姓名 -->
      <van-field
        label="投保人姓名"
        required
        placeholder="请输入"
        input-align="right"
        v-model="formData.appliName"
        name="appliName"
        :formatter="removeAllSpace"
        @blur="handler('投保人姓名',formData.appliName)"
      />
      <!-- 证件类型 -->
      <div>
        <van-field
          readonly
          clickable
          required
          :value="cardTypeInfo.typeVal"
          placeholder="请选择"
          @click="cardTypeInfo.picker = true"
          input-align="right"
          class="after"
        >
          <div slot="label">
            <span>证件类型</span>
          </div>
          <div
            slot="right-icon"
            class="rightCenter"
          >
            <van-icon
              name="arrow"
              class="grayBf"
            />
          </div>
        </van-field>
        <van-popup
          v-model="cardTypeInfo.picker"
          position="bottom"
        >
          <van-picker
            show-toolbar
            :columns="cardTypeInfo.type"
            @cancel="cardTypeInfo.picker = false"
            @confirm="card_confirm"
          />
        </van-popup>
      </div>
      <!-- 证件号码 -->
      <van-field
        label="证件号码"
        required
        placeholder="请输入证件号码"
        v-model="formData.appliIdentifyNumber"
        maxlength="18"
        :formatter="removeAllSpace"
        @blur="handlerIdNum('证件号码1',formData.appliIdentifyNumber,formData.appliIdentifyType,$event)"
      >
        <template slot="button">
          <van-row
            type="flex"
            align="center"
            @click="ocrDistinguish('appliCard')"
            v-if="showOCR"
          >
            <van-col class="select-customer-text">
              <!-- 身份证识别 -->
              <img
                src="@/assets/common/icon3.png"
                alt="edit"
                class="card-img"
              />
            </van-col>
            <!-- <van-col class="select-customer-icon">
              <van-icon name="photograph" />
            </van-col>-->
          </van-row>
        </template>
      </van-field>
      <!-- 出生日期 -->
      <div class="date">
        <van-cell
          :value="formData.appliBirthDate"
          required
          label-width="80px"
          class="rightCenter after"
          @click="roShowDate(true)"
        >
          <template slot="title">
            <span>出生日期</span>
          </template>
          <van-icon
            slot="right-icon"
            name="arrow"
            class="grayBf left13 verticalMid"
          />
        </van-cell>
        <van-popup
          v-model="showDate"
          position="bottom"
          :style="{ height: '70%' }"
        >
          <van-datetime-picker
            v-model="currentDate"
            type="date"
            @confirm="selectDateConfirm"
            @cancel="showDate = false"
            :min-date="minDate"
            :max-date="maxDate"
          />
        </van-popup>
      </div>
      <!-- 手机号 -->
      <van-field
        label="手机号"
        required
        type="tel"
        placeholder="请输入手机号"
        input-align="right"
        v-model="formData.appliMobile"
        maxlength="11"
        @blur="handlerMobile('手机号',formData.appliMobile)"
        :formatter="removeAllSpace"
      />
      <van-field
        label="邮箱"
        placeholder="请输入邮箱地址，可接收电子保单"
        input-align="right"
        v-model="formData.appliEMail"
        :formatter="removeAllSpace"
      />
    </div>
    <!-- 被保人信息 -->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="title-container"
        style="background:#f7f7f7;"
      >
        <template slot="title">
          <van-row
            type="flex"
            justify="space-between"
            align="center"
          >
            <van-col class="card-title-text">
              <img
                src="@/assets/common/icon5.png"
                alt="edit"
              />
              被保人信息
            </van-col>
            <van-row
              type="flex"
              align="center"
              @click="selectInsuredMethod(true)"
            >
              <van-col class="select-customer-text">
                {{formData.insuredName? '修改' : '选择被保人'}}
                <img
                  src="@/assets/common/icon2.png"
                  alt="edit"
                  class="card-img"
                />
              </van-col>
              <!-- <van-col class="select-customer-icon">
                <van-icon name="contact" />
              </van-col>-->
            </van-row>
          </van-row>
        </template>
      </van-cell>
      <div>
        <van-field
          readonly
          clickable
          required
          :value="relationInfo.typeVal"
          placeholder="请选择"
          @click="relationInfo.picker = true"
          input-align="right"
          class="after"
        >
          <div slot="label">
            <span>与投保人关系</span>
          </div>
          <div
            slot="right-icon"
            class="rightCenter"
          >
            <van-icon
              name="arrow"
              class="grayBf"
            />
          </div>
        </van-field>
        <van-popup
          v-model="relationInfo.picker"
          position="bottom"
        >
          <van-picker
            show-toolbar
            :columns="relationInfo.type"
            @cancel="relationInfo.picker = false"
            @confirm="card_confirm2"
          />
        </van-popup>
      </div>
      <div v-show="!checked2">
        <van-field
          required
          label="被保人姓名"
          placeholder="请输入"
          input-align="right"
          v-model="formData.insuredName"
          maxlength="50"
          :formatter="removeAllSpace"
          @blur="handler('被保人姓名',formData.insuredName)"
        />

        <div>
          <van-field
            readonly
            clickable
            required
            :value="cardTypeInfo2.typeVal"
            placeholder="请选择"
            @click="cardTypeInfo2.picker = true"
            input-align="right"
            class="after"
          >
            <div slot="label">
              <span>证件类型</span>
            </div>
            <div
              slot="right-icon"
              class="rightCenter"
            >
              <van-icon
                name="arrow"
                class="grayBf"
              />
            </div>
          </van-field>
          <van-popup
            v-model="cardTypeInfo2.picker"
            position="bottom"
          >
            <van-picker
              show-toolbar
              :columns="cardTypeInfo2.type"
              @cancel="cardTypeInfo2.picker = false"
              @confirm="card_confirm3"
            />
          </van-popup>
        </div>

        <van-field
          required
          label="证件号码"
          placeholder="请输入证件号码"
          v-model="formData.insuredIdentifyNumber"
          maxlength="18"
          :formatter="removeAllSpace"
          @blur="handlerIdNum('证件号码2',formData.insuredIdentifyNumber,formData.insuredIdentifyType,$event)"
        >
          <template slot="button">
            <van-row
              type="flex"
              align="center"
              @click="ocrDistinguish('insuredCard')"
              v-if="showOCR2"
            >
              <van-col class="select-customer-text">
                <!-- 身份证识别 -->
                <img
                  src="@/assets/common/icon3.png"
                  alt="edit"
                  class="card-img"
                />
              </van-col>
              <!-- <van-col class="select-customer-icon">
                <van-icon name="photograph" />
            </!-->
            </van-row>
          </template>
        </van-field>

        <div class="date">
          <van-cell
            :value="formData.insuredBirthDate"
            required
            label-width="80px"
            @click="roShowDate(false)"
            class="rightCenter after"
          >
            <template slot="title">
              <span>出生日期</span>
            </template>
            <van-icon
              slot="right-icon"
              name="arrow"
              class="grayBf left13 verticalMid"
            />
          </van-cell>
          <van-popup
            v-model="showDate2"
            position="bottom"
            :style="{ height: '70%' }"
          >
            <van-datetime-picker
              v-model="currentDate2"
              type="date"
              @confirm="selectDateConfirm2"
              @cancel="showDate2 = false"
              :min-date="minDate"
              :max-date="maxDate"
            />
          </van-popup>
        </div>

        <van-field
          label="手机号"
          placeholder="请输入手机号"
          input-align="right"
          v-model="formData.insuredMobile"
          maxlength="11"
          class="after"
          :formatter="removeAllSpace"
        />
      </div>

      <!-- 社保类型注释 -->
      <!-- <div v-if="formData.isSheBao!='W'">
        <van-field
          required
          readonly
          clickable
          :value="shebaoInfo.typeVal"
          placeholder="请选择"
          @click="shebaoInfo.picker = true"
          input-align="right"
        >
          <div slot="label">
            <span>社保类型</span>
          </div>
          <div
            slot="right-icon"
            class="rightCenter"
          >
            <van-icon
              name="arrow"
              class="grayBf"
            />
          </div>
        </van-field>
        <van-popup
          v-model="shebaoInfo.picker"
          position="bottom"
        >
          <van-picker
            show-toolbar
            :columns="shebaoInfo.type"
            @cancel="shebaoInfo.picker = false"
            @confirm="card_confirm4"
          />
        </van-popup>
      </div> -->
    </div>

    <!-- 保障期限 -->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="card-title-container"
        style="background:#f7f7f7;"
      >
        <template slot="title">
          <div class="card-title-text">
            <img
              src="@/assets/common/icon6.png"
              alt="edit"
            />
            保障期限
          </div>
        </template>
      </van-cell>
      <van-cell
        title="保障年限"
        value="1年"
      />
      <!--选择日期区间组件  -->
      <div class="date">
        <van-cell
          :value="selectDate"
          required
          label-width="80px"
          @click="showDate3 = true"
          class="rightCenter after"
        >
          <template slot="title">
            <span>起保日期</span>
          </template>
          <van-icon
            slot="right-icon"
            name="arrow"
            class="grayBf left13 verticalMid"
          />
        </van-cell>
        <van-popup
          v-model="showDate3"
          position="bottom"
          :style="{ height: '70%' }"
        >
          <van-datetime-picker
            v-model="currentDate3"
            :max-date="maxDate3"
            :min-date="minDate3"
            type="date"
            @confirm="selectDateConfirm3"
            @cancel="showDate3 = false"
          />
        </van-popup>
      </div>
    </div>
    <!-- <city-picker
      @addressInfo="addressInfo"
      ref="city"
    /> -->
    <!-- 开票信息 -->
    <div class="card">
      <van-cell
        :border="false"
        center
        class="card-title-container"
        @click="toggleChecked"
        style="background:#f7f7f7;"
      >
        <template slot="title">
          <van-row
            type="flex"
            justify="space-between"
            align="center"
          >
            <van-col class="card-title-text">
              <img
                src="@/assets/common/icon7.png"
                alt="edit"
              />
              开票信息
            </van-col>
            <van-switch
              v-model="checked"
              size="20px"
            />
          </van-row>
        </template>
      </van-cell>
      <div
        v-if="checked"
        style="background:#fff;padding-top:5px;"
      >
        <van-row
          type="flex"
          center
          align="center"
          class="tip-container"
        >
          <van-col class="tip-icon">
            <van-icon
              name="question"
              color="#3B8AFE"
              size="16px"
            />
          </van-col>
          <van-col class="tip-text">电子发票与纸质发票具有同等效力，可作为报销凭据</van-col>
        </van-row>

        <van-cell title="是否需要电子发票">
          <template slot="default">
            <van-button
              plain
              type="primary"
              :class="[isShould ? 'selected-button' : '', 'select-button']"
              @click="toggleShould(1)"
            >是</van-button>
            <van-button
              plain
              class="select-button"
              :class="[!isShould ? 'selected-button' : '', 'select-button']"
              @click="toggleShould(2)"
            >否</van-button>
          </template>
        </van-cell>

        <van-cell title="发票抬头">
          <template slot="default">
            <van-button
              plain
              type="primary"
              :class="[isSameInsured ? 'selected-button' : '', 'select-button']"
              @click="toggleSameInsured(1)"
              style="font-size:12px;"
            >同投保人</van-button>
            <van-button
              plain
              class="select-button"
              :class="[!isSameInsured ? 'selected-button' : '', 'select-button']"
              @click="toggleSameInsured(2)"
              style="font-size:12px;"
            >同被保人</van-button>
          </template>
        </van-cell>

        <van-field
          label="移动电话"
          v-model="formData.invoiceInfo.mobile"
          required
          placeholder="输入电话"
          input-align="right"
          v-if="isShould"
          @blur="handlerMobile('手机号', formData.invoiceInfo.mobile)"
          :formatter="removeAllSpace"
        />

        <van-field
          label="电子邮箱"
          v-model="formData.invoiceInfo.email"
          placeholder="输入邮箱"
          input-align="right"
          v-if="isShould"
          @blur="handlerEmail('发票邮箱',formData.invoiceInfo.email)"
          :formatter="removeAllSpace"
        />
      </div>
    </div>
    <van-cell
      class="agree"
      style="background:#f7f7f7;"
    >
      <template slot="title">
        <van-row
          type="flex"
          justify="space-between"
          align="center"
        >
          <van-col class="allow-protocol-check-box">
            <van-checkbox v-model="allowedProtocol" />
          </van-col>
          <van-col style="color: rgb(153, 153, 153);">
            本人已认真阅读并同意
            <a
              v-for="(protocol, index) in protocols"
              :key="protocol.name"
              style="color:#3b8afe;"
              @click="goProtocols(protocol.link)"
            >
              <span v-if="index === protocols.length - 1">和</span>
              {{ protocol.name }}
            </a>
            的全部内容，
            <span>并保证所填《健康告知》内容真实有效</span>
          </van-col>
        </van-row>
      </template>
    </van-cell>
    <!-- tabbar -->
    <div class="tabbar">
      <div class="price">
        保费
        <span style="color:#FC820E">{{newData.sumPremium}}</span>元
      </div>
      <div class="btn">
        <van-button
          type="info"
          @click="enter"
        >立即投保</van-button>
      </div>
    </div>
  </div>
</template>

<script>
import globalNav from "@/components/globalNav";
// import cityPicker from "../../healthInsurance/cityPicker";
import { placeOrder, insureStatusZc } from "../../../../http/module/nonCar.js";
import { mapMutations, mapGetters } from "vuex";
import { Toast } from "vant";
import { checkIdcard } from "@src/utils";

export default {
  data () {
    return {
      goBack: "prev",
      globalNav: {
        title: "投保信息填写"
      },
      //底部保险条款
      protocols: [
        { name: "《保险条款》", link: "bxtk" },
        { name: "《投保须知》", link: "InsureNotice" },
        { name: "《投保人声明》", link: "InsureStatement" },
        { name: "《特别约定》", link: "Aboutpersonal" },
        // { name: "《责任免赔》", link: "/nonCar/clauseFine/liabilityExempt" }
      ],
      // 证件类型
      cardTypeInfo: {
        type: ["身份证", "港澳台居民居住证"],
        picker: false,
        typeVal: "身份证"
      },
      cardTypeInfo2: {
        type: ["身份证", "港澳台居民居住证"],
        picker: false,
        typeVal: "身份证"
      },
      //与投保人关系
      relationInfo: {
        type: ["本人", "父母", "配偶", "子女"],
        picker: false,
        typeVal: ""
      },
      // 社保类型注释
      // shebaoInfo: {
      //   //证件类型
      //   //被保人年龄信息
      //   type: [
      //     "城镇职工基本医疗保险",
      //     "城乡居民基本医疗保险",
      //     "城镇居民基本医疗保险",
      //     "新农合医疗保险",
      //     "公费医疗",
      //     "民政优抚医疗保险",
      //     "其他"
      //   ],
      //   picker: false,
      //   typeVal: ""
      // },
      showDate: false,
      showDate2: false,
      showDate3: false,
      allowedProtocol: false, //底部勾选
      selectDate: "", //选择日期
      currentDate: new Date(),
      currentDate2: new Date(),
      currentDate3: new Date(),

      minDate: new Date(1930, 0, 1), //出生日期组件默认定义最小值
      maxDate: new Date(), //出生日期组件默认最大值
      minDate3: new Date(new Date().getTime() + 24 * 60 * 60 * 1000),
      maxDate3: new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * 30),

      formData: {
        appliName: "", //投保人姓名
        appliIdentifyType: "01", //证件类型
        appliIdentifyNumber: "", //证件号码
        appliBirthDate: "",//出生日期
        appliMobile: "",//手机号
        appliEMail: "", //投保人邮箱
        // yjAccount: "603.72", //佣金
        appliIdentity: "", //关系
        insuredName: "", //被保人姓名
        insuredIdentifyType: "01",//证件类型
        insuredIdentifyNumber: "",//证件号码
        insuredBirthDate: "",//出生日期
        insuredMobile: "",//手机号
        startDate: "",
        endDate: "",
        // insuredSheBaoType: "", 暂无
        invoiceInfo: {
          //发票信息
          taxPayerType: "", //纳税人类型 4 个人
          companyName: "", //发票抬头
          customerType: "", //客户类型1 境内个人、政府机关及事业单位
          invoiceType: "", //发票类型 2增值税普通发票 ,3 增值税电子普通发票
          mobile: "",
          taxPayerNo: ""

        },
        // sumPremium: "",
        ages: "",
        addressInfo: {
          provinceCode: "", //省code
          prefectureCode: "", //市code
          countyCode: "", //县code
          comcode: "", //省comcode
          addressName: ""//省市区文字
        },
        placeInfo: "", //所在地区
      },
      checked: false,
      checked2: false,
      isSameInsured: true,
      isShould: true,//是否需要电子发票
      allowedProtocol: false, //底部勾选

      showOCR: true,
      showOCR2: true,
    };
  },
  created () {
    document.getElementById('app').style.overflow = "visible"

    // 保存数据
    this.backSave()
  },
  mounted () {
    // OCR切换
    if (this.formData.appliIdentifyType != "01") {
      this.showOCR = false;
    }
    if (this.formData.insuredIdentifyType != "01") {
      this.showOCR2 = false;
    }
  },
  methods: {
    // 保存数据
    backSave () {
      let tS = this.newData;
      for (let k in tS) {
        this.formData[k] = tS[k]
      }
      // 证件类型数据
      this.cardTypeInfo.typeVal = this.$getCodeOrName(
        "bwIdCardList",
        this.formData.appliIdentifyType
      );
      this.cardTypeInfo2.typeVal = this.$getCodeOrName(
        "bwIdCardList",
        this.formData.insuredIdentifyType
      );
      // 与投保人关系
      this.relationInfo.typeVal = this.$getCodeOrName(
        "bwRelateInsured",
        this.formData.appliIdentity
      );
      if (this.formData.appliIdentity == "01") {
        this.checked2 = true;
      }
      // 选择地区
      // this.$nextTick(() => {
      //   this.$refs.city.value = this.formData.placeInfo
      // });
      // 设置最新起保日期默认值
      // if (this.newData.startDate == "" || this.newData.endDate == "") {
      let startDate = this.formatDate(
        new Date(new Date().getTime() + 24 * 60 * 60 * 1000)
      );
      this.formData.startDate = startDate;
      let endDate = new Date();
      endDate.setFullYear(endDate.getFullYear() + 1);
      endDate.setDate(endDate.getDate());
      endDate = this.formatDate(endDate);
      this.formData.endDate = endDate;
      this.selectDate = `${startDate} - ${endDate}`;
      this.setNewData({ startDate, endDate });
      // } else {
      // let startDate = this.newData.startDate;
      // let endDate = this.newData.endDate;
      // this.selectDate = `${startDate} - ${endDate}`;
      // }

      // 去客户列表
      if (this.$route.query.item) {
        if (this.$route.query.item.isInsured == false) {
          let tInfo = this.$route.query.item;
          this.formData.appliName = tInfo.custName;
          this.formData.appliIdentifyType = tInfo.certificateType;
          // if (tInfo.certificateType == "01") {
          this.cardTypeInfo.typeVal = this.$getCodeOrName(
            "bwIdCardList",
            this.formData.appliIdentifyType
          );
          this.formData.appliIdentifyNumber = tInfo.certificateNo;
          // }
          if (Array.isArray(tInfo.mobileList) && tInfo.mobileList.length > 0) {
            this.formData.appliMobile = tInfo.mobileList[0].phoneNumber;
          }
          this.formData.appliBirthDate = this.getNum(
            this.formData.appliIdentifyNumber
          );
          let params = {
            appliName: this.formData.appliName,
            appliIdentifyType: this.formData.appliIdentifyType,
            appliIdentifyNumber: this.formData.appliIdentifyNumber,
            appliMobile: this.formData.appliMobile,
            appliBirthDate: this.formData.appliBirthDate
          };
          this.setNewData(params);
        } else if (this.$route.query.item.isInsured == true) {
          let bInfo = this.$route.query.item;
          this.formData.insuredName = bInfo.custName;
          this.formData.insuredIdentifyType = bInfo.certificateType;
          this.cardTypeInfo2.typeVal = this.$getCodeOrName(
            "bwIdCardList",
            this.formData.insuredIdentifyType
          );
          // if (bInfo.certificateType == "01") {
          this.formData.insuredIdentifyNumber = bInfo.certificateNo;
          // }
          if (Array.isArray(bInfo.mobileList) && bInfo.mobileList.length > 0) {
            this.formData.insuredMobile = bInfo.mobileList[0].phoneNumber;
          }
          this.formData.insuredBirthDate = this.getNum(
            this.formData.insuredIdentifyNumber
          );

          // 判断
          let temporaryVariable = this.formData.insuredBirthDate;

          if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
            Toast("年龄不在范围内");
            this.formData.insuredName = "";
            this.formData.insuredIdentifyType = "01";
            this.formData.insuredIdentifyNumber = "";
            this.formData.insuredBirthDate = "";
            this.formData.insuredMobile = ""
            return;
          }


          // 


          let params = {
            insuredName: this.formData.insuredName,
            insuredIdentifyType: this.formData.insuredIdentifyType,
            insuredIdentifyNumber: this.formData.insuredIdentifyNumber,
            insuredMobile: this.formData.insuredMobile,
            insuredBirthDate: this.formData.insuredBirthDate
          };

          this.setNewData(params);
          this.againCompute();
        }
      }

    },
    // 投保信息填写验证规则
    // 1姓名非空验证
    handler (key, val) {
      if (val == "" || val.length >= 50) {
        Toast(key + "不能为空并且最大50字符");
      }
      if (this.formData.appliIdentity == "01") {
        this.formData.insuredName = this.formData.appliName;
        // this.formData.appliIdentity = ""
        // this.relationInfo.typeVal = ""
        // this.checked2 = false;
      }
    },
    parserDate (date) {
      var t = Date.parse(date);
      if (!isNaN(t)) {
        return new Date(Date.parse(date.replace(/-/g, "/")));
      } else {
        return new Date();
      }
    },
    // 2证件号码验证
    handlerIdNum (key, val, type, e) {
      if (key == "证件号码1") {
        if (
          this.formData.appliIdentifyType == "01"
          //  || this.formData.appliIdentifyType == "28"
        ) {
          Toast(checkIdcard(val));
          if (
            checkIdcard(val) == "证件号码位数不对" ||
            checkIdcard(val) == "证件号码出生日期超出范围或含有非法字符" ||
            checkIdcard(val) == "证件号码校验错误" ||
            checkIdcard(val) == "证件地区非法"
          ) {
            this.formData.appliIdentifyNumber = ""
            e.target.focus();
            return;
          }
          this.formData.appliBirthDate = this.getNum(val);
          this.currentDate = this.parserDate(this.getNum(val));

        }
      }
      if (key == "证件号码2") {
        if (
          this.formData.insuredIdentifyType == "01"
          // ||this.formData.insuredIdentifyType == "28"
        ) {
          Toast(checkIdcard(val));
          if (
            checkIdcard(val) == "证件号码位数不对" ||
            checkIdcard(val) == "证件号码出生日期超出范围或含有非法字符" ||
            checkIdcard(val) == "证件号码校验错误" ||
            checkIdcard(val) == "证件地区非法"
          ) {
            this.formData.insuredIdentifyNumber = ""
            e.target.focus();

          }

          this.formData.insuredBirthDate = this.getNum(val);
          let temporaryVariable = this.formData.insuredBirthDate;
          if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
            Toast("年龄不在范围内");
            this.formData.insuredIdentifyNumber = "";
            this.formData.insuredBirthDate = "";
            return;

          }
          this.againCompute();

          this.currentDate2 = this.parserDate(this.getNum(val));
        }
        // if (this.formData.insuredIdentifyType == "28") {
        //   this.formData.insuredBirthDate = this.getNum(val);
        // }
      }
      let temporaryVariable = this.formData.insuredBirthDate;

      if (this.formData.appliIdentity == "01") {

        // Toast('请重新选择与投保人关系')
        // this.formData.appliIdentity = "";
        // this.relationInfo.typeVal = ""
        // this.checked2 = false;

        let temporaryVariable = this.formData.appliBirthDate;

        if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
          // Toast("年龄不在范围内");
          // this.formData.insuredIdentifyNumber = "";
          // this.formData.insuredBirthDate = "";
          Toast('年龄不在范围内,请重新选择与投保人关系')
          this.relationInfo.typeVal = ""
          this.checked2 = false;
          return;

        } else {
          this.formData.insuredIdentifyNumber = this.formData.appliIdentifyNumber;
          this.formData.insuredBirthDate = this.formData.appliBirthDate;
          this.againCompute();

        }
      }

      switch (type) {
        case "01": //身份证验证
          if (val == "") {
            Toast("证件号码不能为空");
          }
          break;
        case "03": //护照验证
          if (val == "") {
            Toast("护照不能为空");
          }
          break;
        case "28": //港澳居住验证
          if (val == "") {
            Toast("港澳居住证不能为空");
          }
          break;
      }
    },

    // 3手机号验证
    handlerMobile (key, val) {


      if (val == "") {
        Toast("手机号不能为空");
      } else {
        let reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
        if (!reg.test(val)) {
          Toast("手机号格式不正确");
          if (val == this.formData.appliMobile) {
            this.formData.appliMobile = ""
          }
          if (val == this.formData.insuredMobile) {
            this.formData.insuredMobile = ""

          }
        }
      }
      if (this.formData.appliIdentity == "01") {
        this.formData.insuredMobile = this.formData.appliMobile;
        // this.formData.appliIdentity = "";
        // this.relationInfo.typeVal = ""
        // this.checked2 = false;

      }
    },

    // 4 邮箱验证
    handlerEmail (key, val) {
      return new Promise(resolve => {
        let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
        if (!reg.test(val)) {
          Toast(`${key}` + "格式不正确");
          resolve("emailnull");
        } else {
          resolve("ok");
        }
      });
    },
    // 地区选择
    addressInfo (addressInfo) {
      // this.formData.addressInfo = addressInfo;
      // this.formData.placeInfo = addressInfo.addressName;
    },
    // 保险条款
    goProtocols (a) {
      this.setNewData(this.formData)
      this.$router.push({ name: a, params: { isSheBao: this.newData.isSheBao } });

    },
    // 返回上一页
    goback () {
      if (this.goBack == "index") {
        this.$router.push({ name: "stopCancer" });
      } else {
        this.$router.go(-1);
      }
      document.getElementById('app').style.overflow = ""

    },
    // 选择投保人
    selectInsuredMethod (isInsured) {
      if (isInsured) {
        let params = {
          appliName: this.formData.appliName,
          appliIdentifyType: this.formData.appliIdentifyType,
          appliIdentifyNumber: this.formData.appliIdentifyNumber,
          appliMobile: this.formData.appliMobile,
          appliBirthDate: this.formData.appliBirthDate
        };
        this.setNewData(params);
      }
      let parms = {
        custType: "1",
        insureType: "5",
        isInsured: isInsured || false
      };
      this.$router.push({ name: "myCustomer", query: { item: parms } });
    },
    // ocr识别
    ocrDistinguish (type) {
      let _this = this;
      switch (type) {
        // 投保人身份证识别
        case "appliCard": {
          const jsonObject = {
            identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          console.log("=========" + JSON.stringify(jsonObject));
          this.$native.identifyOCR(jsonObject, data => {
            console.log("身份证识别调用成功，壳响应数据:" + data.resultMsg);
            let tempData = JSON.parse(data);
            if (tempData.resultCode == "1") {
              const { name, cardNo } = tempData.responseBody;
              _this.formData.appliIdentifyNumber = cardNo;
              _this.formData.appliName = name;
              _this.formData.appliIdentifyType = "01";
              _this.formData.appliBirthDate = _this.getNum(cardNo);

              if (_this.formData.appliIdentity == "01") {

                let temporaryVariable = _this.formData.appliBirthDate;

                if (_this.$getAge(temporaryVariable) <= 44 || _this.$getAge(temporaryVariable) > 80) {
                  // Toast("年龄不在范围内");
                  // this.formData.insuredIdentifyNumber = "";
                  // this.formData.insuredBirthDate = "";
                  Toast('年龄不在范围内,请重新选择与投保人关系')
                  _this.relationInfo.typeVal = ""
                  _this.checked2 = false;
                  return;

                } else {
                  _this.formData.insuredIdentifyNumber = this.formData.appliIdentifyNumber;
                  _this.formData.insuredBirthDate = this.formData.appliBirthDate;
                  _this.againCompute();

                }
              }


            }
          });
          break;
        }
        // 被保人身份证识别
        case "insuredCard": {
          const jsonObject = {
            identifyType: "1", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          console.log("=========" + JSON.stringify(jsonObject));
          this.$native.identifyOCR(jsonObject, data => {
            console.log("身份证识别调用成功，壳响应数据:" + data.resultMsg);
            let tempData = JSON.parse(data);
            if (tempData.resultCode == "1") {
              const { name, cardNo } = tempData.responseBody;
              _this.formData.insuredIdentifyNumber = cardNo;
              _this.formData.insuredName = name;
              _this.formData.insuredIdentifyType = "01";
              _this.formData.insuredBirthDate = _this.getNum(cardNo);
              let temporaryVariable = _this.formData.insuredBirthDate;

              if (_this.$getAge(temporaryVariable) <= 44 || _this.$getAge(temporaryVariable) > 80) {
                Toast("年龄不在范围内");
                _this.formData.insuredIdentifyNumber = "";
                _this.formData.insuredBirthDate = "";
                _this.formData.insuredName = "";
                return;
              }

              _this.againCompute();
            }
          });
          break;
        }
        // TODO suncao驾驶证识别
        case "vehicleCard": {
          const jsonObject = {
            identifyType: "0", //"0" - 识别行驶证; "1" - 识别身份证
            side: "face" //"face"：正面；"back": 反面;
          };
          this.$native.identifyOCR(jsonObject, data => {
            let dataParam = JSON.parse(data);
            console.log("==========" + dataParam.responseBody.cardNo);
            this.requestData.licenseNo = dataParam.responseBody.cardNo;
            this.requestData.brandModelName = dataParam.responseBody.model;
          });
          break;
        }
      }
    },
    // 得到日期
    getNum (idCard) {
      var birthday = "";
      if (idCard != null && idCard != "") {
        if (idCard.length == 15) {
          birthday = "19" + idCard.slice(6, 12);
        } else if (idCard.length == 18) {
          birthday = idCard.slice(6, 14);
        }
        birthday = birthday.replace(/(.{4})(.{2})/, "$1-$2-");
        //通过正则表达式来指定输出格式为:1990-01-01
      }
      return birthday;
    },
    removeAllSpace (str) {
      return str.replace(/\s+/g, "");
    },
    // 证件类型选择
    card_confirm (value, index) {
      this.cardTypeInfo.typeVal = value;

      switch (index) {
        case 0:
          this.formData.appliIdentifyType = "01";
          break;
        // case 1:
        //   this.formData.appliIdentifyType = "03";
        //   break;
        case 1:
          this.formData.appliIdentifyType = "28";
          break;
      }
      this.cardTypeInfo.picker = false;
      if (this.formData.appliIdentity == "01") {

        this.cardTypeInfo2.typeVal = value;
        switch (index) {
          case 0:
            this.formData.insuredIdentifyType = "01";
            break;
          // case 1:
          //   this.formData.insuredIdentifyType = "03";
          //   break;
          case 1:
            this.formData.insuredIdentifyType = "28";
            break;
        }
      }

      if (this.formData.appliIdentifyType != "01") {
        this.showOCR = false;
      } else {
        this.showOCR = true;
      }
    },
    // 投保人关系
    card_confirm2 (value, index) {
      this.relationInfo.typeVal = value;
      switch (index) {
        case 0:
          this.formData.appliIdentity = "01";
          this.checked2 = true;
          this.sameBefore();
          this.setNewData({ appliIdentity: "01" });
          break;
        case 1:
          this.formData.appliIdentity = "50";
          this.checked2 = false;
          this.deleteSameBefore();
          this.setNewData({ appliIdentity: "50" });
          break;
        case 2:
          this.formData.appliIdentity = "10";
          this.checked2 = false;
          this.deleteSameBefore();
          this.setNewData({ appliIdentity: "10" });
          break;
        case 3:
          this.formData.appliIdentity = "40";
          this.checked2 = false;
          this.deleteSameBefore();
          this.setNewData({ appliIdentity: "40" });
          break;
      }
      this.relationInfo.picker = false;
    },
    // 同投被保人
    sameBefore () {
      if (this.checked2) {
        if (
          this.formData.appliIdentifyType == "01"
          //|| this.formData.appliIdentifyType == "28"
        ) {
          this.formData.appliBirthDate = this.getNum(
            this.formData.appliIdentifyNumber
          );
          let temporaryVariable = this.formData.appliBirthDate;
          if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
            Toast("年龄不在范围内");
            this.formData.insuredBirthDate = "";
            this.checked2 = false;
            return;
          } else {
            this.formData.insuredName = this.formData.appliName;
            this.formData.insuredIdentifyType = this.formData.appliIdentifyType;
            this.formData.insuredIdentifyNumber = this.formData.appliIdentifyNumber;
            this.formData.insuredBirthDate = this.formData.appliBirthDate;
            this.formData.insuredMobile = this.formData.appliMobile;
            this.cardTypeInfo2.typeVal = this.$getCodeOrName(
              "bwIdCardList",
              this.formData.insuredIdentifyType
            );
          }
          this.againCompute();
        } else {
          let temporaryVariable = this.formData.appliBirthDate;
          if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
            Toast("年龄不在范围内");
            this.formData.insuredBirthDate = "";
            this.checked2 = false;
            return;
          } else {
            this.formData.insuredName = this.formData.appliName;
            this.formData.insuredIdentifyType = this.formData.appliIdentifyType;
            this.formData.insuredIdentifyNumber = this.formData.appliIdentifyNumber;
            this.formData.insuredBirthDate = this.formData.appliBirthDate;
            this.formData.insuredMobile = this.formData.appliMobile;
            this.cardTypeInfo2.typeVal = this.$getCodeOrName(
              "bwIdCardList",
              this.formData.insuredIdentifyType
            );
          }

          this.againCompute();
        }
      }
    },
    // 去除同投被保人
    deleteSameBefore () {
      if (!this.checked2) {
        this.formData.insuredName = "";
        this.formData.insuredIdentifyType = "";
        this.formData.insuredIdentifyNumber = "";
        this.formData.insuredBirthDate = "";
        this.formData.insuredMobile = "";
        this.cardTypeInfo2.typeVal = this.$getCodeOrName(
          "bwIdCardList",
          this.formData.insuredIdentifyType
        );
        this.againCompute();
      }
    },

    // 证件类型选择
    card_confirm3 (value, index) {
      this.cardTypeInfo2.typeVal = value;
      switch (index) {
        case 0:
          this.formData.insuredIdentifyType = "01";
          break;
        // case 1:
        //   this.formData.insuredIdentifyType = "03";
        //   break;
        case 1:
          this.formData.insuredIdentifyType = "28";
          break;
      }
      this.cardTypeInfo2.picker = false;
      if (this.formData.insuredIdentifyType != "01") {
        this.showOCR2 = false;
      } else {
        this.showOCR2 = true;
      }
    },
    // 社保类型注释
    // card_confirm4 (value, index) {
    //   this.shebaoInfo.typeVal = value;
    //   switch (index) {
    //     case 0:
    //       this.formData.insuredSheBaoType = "1";
    //       break;
    //     case 1:
    //       this.formData.insuredSheBaoType = "8";
    //       break;
    //     case 2:
    //       this.formData.insuredSheBaoType = "2";
    //       break;
    //     case 3:
    //       this.formData.insuredSheBaoType = "3";
    //       break;
    //     case 4:
    //       this.formData.insuredSheBaoType = "7";
    //       break;
    //     case 5:
    //       this.formData.insuredSheBaoType = "4";
    //       break;
    //     case 6:
    //       this.formData.insuredSheBaoType = "6";
    //       break;
    //   }
    //   this.shebaoInfo.picker = false;
    // },
    roShowDate (a) {
      if (a) {
        if (
          this.formData.appliIdentifyType == "01"
          // ||this.formData.appliIdentifyType == "28"
        ) {
          if (this.formData.appliIdentifyNumber != "") {
            this.showDate = false;
          } else {
            this.showDate = true;
          }
        } else {
          this.showDate = true;
        }
      } else {
        if (
          this.formData.insuredIdentifyType == "01"
          // ||this.formData.insuredIdentifyType == "28"
        ) {
          if (this.formData.insuredIdentifyNumber != "") {
            this.showDate2 = false;
          } else {
            this.showDate2 = true;
          }
        } else {
          this.showDate2 = true;
        }
      }
    },
    // 格式化时间
    formatDate (date) {
      let month = date.getMonth() + 1;
      if (month < 10) {
        month = "0" + month;
      }
      let beginDate = date.getDate();
      if (beginDate < 10) {
        beginDate = "0" + beginDate;
      }
      return `${date.getFullYear()}-${month}-${beginDate}`;
    },
    // 选择日期区间方法
    selectDateConfirm (date) {
      this.formData.appliBirthDate = this.formatDate(date);
      this.showDate = false;
      if (this.formData.appliIdentity == "01") {
        //   Toast('请重新选择与投保人关系')
        //   this.formData.appliIdentity = "";
        //   this.relationInfo.typeVal = ""
        //   this.checked2 = false;



        let temporaryVariable = this.formData.appliBirthDate;

        if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
          // Toast("年龄不在范围内");
          // this.formData.insuredIdentifyNumber = "";
          // this.formData.insuredBirthDate = "";
          Toast('年龄不在范围内,请重新选择与投保人关系')
          this.relationInfo.typeVal = ""
          this.checked2 = false;
          return;

        } else {
          // this.formData.insuredIdentifyNumber = this.formData.appliIdentifyNumber;
          this.formData.insuredBirthDate = this.formData.appliBirthDate;
          this.againCompute();

        }
      }

    },

    // 被保人选择日期区间方法
    selectDateConfirm2 (date) {
      this.formData.insuredBirthDate = this.formatDate(date);
      let temporaryVariable = this.formData.insuredBirthDate;

      this.showDate2 = false;
      if (this.$getAge(temporaryVariable) <= 44 || this.$getAge(temporaryVariable) > 80) {
        Toast("年龄不在范围内");
        this.formData.insuredBirthDate = "";
        return;
      }
      this.againCompute();
    },
    // 计算保费
    againCompute () {

      let getAge = this.$getAge(this.formData.insuredBirthDate);
      let interval = this.compare(getAge);
      if (interval == this.newData.ages) {
        return;
      } else {
        this.setNewData({ ages: interval });

        this.updtNewsumPremium();
        this.formData.sumPremium = this.newData.sumPremium;
        this.formData.ages = interval;
      }
    },
    // 比较年龄
    compare (getAge) {
      switch (true) {
        case getAge == 45:
          return "45";
          break;
        case getAge >= 46 && getAge <= 50:
          return "46-50";
          break;
        case getAge >= 51 && getAge <= 55:
          return "51-55";
          break;
        case getAge >= 56 && getAge <= 60:
          return "56-60";
          break;
        case getAge >= 61 && getAge <= 65:
          return "61-65";
          break;
        case getAge >= 66 && getAge <= 70:
          return "66-70";
          break;
        case getAge >= 71 && getAge <= 75:
          return "71-75";
          break;
        case getAge >= 76 && getAge <= 80:
          return "76-80";
          break;
      }
      switch (false) {
        case getAge == 45:
          return "45";
          break;
      }
    },
    // 选择日期区间
    selectDateConfirm3 (date) {
      let startDate = this.formatDate(date);
      let endDate = new Date(date.getTime());
      endDate.setFullYear(endDate.getFullYear() + 1);
      endDate.setDate(endDate.getDate() - 1);
      this.selectDate = `${startDate} - ${this.formatDate(endDate)}`;
      this.formData.startDate = startDate;
      this.formData.endDate = this.formatDate(endDate);
      this.showDate3 = false;
    },

    // 暂存方法
    zancun () {
      if (!localStorage.getItem("newDataOrderNo")) {
        var zcParams = {
          formulaCode: this.newData.formulaCode,
          insuranceCode: this.newData.riskCode,
          storeOracleRequestVO: this.newData
        };
      } else {
        var zcParams = {
          formulaCode: this.newData.formulaCode,
          insuranceCode: this.newData.riskCode,
          storeOracleRequestVO: {
            ...this.newData
          },
          orderNo: localStorage.getItem("newDataOrderNo")
        }
      }

      insureStatusZc(zcParams).then(res => {
        if (res.code == "1") {
          localStorage.setItem("newDataOrderNo", res.data.orderNo)
        }
      })
    },


    enter () {
      if (this.formData.appliName == "" || this.formData.insuredName == "") {
        if (this.formData.appliName != "") {
          Toast("被保人姓名不能为空");
          return
        }
        Toast("投保人姓名不能为空");
        return;
      }
      if (this.formData.appliIdentifyNumber == "" || this.formData.insuredIdentifyNumber == "") {
        if (this.formData.appliIdentifyNumber != "") {
          Toast("被保人证件号码不能为空");
          return
        }
        Toast("投保人证件号码不能为空");
        return;
      }
      if (this.formData.appliBirthDate == "" || this.formData.insuredBirthDate == "") {
        if (this.formData.appliBirthDate != "") {
          Toast("被保人出生日期不能为空");
          return
        }
        Toast("投保人出生日期不能为空");
        return;
      }
      if (this.formData.appliMobile == "") {
        Toast("投保人手机号不能为空");
        return;
      }
      if (this.formData.appliIdentity == "") {
        Toast("请选择与投保人关系");
        return;
      }
      // if (this.formData.placeInfo == "") {
      //   Toast("请选择地区");
      //   return;
      // }
      if (!this.allowedProtocol) {
        Toast("请阅读并同意保险条款和投保须知等");
        return;
      }
      this.setNewData(this.formData);
      this.zancun()
      this.$router.push({
        name: "stopCancerConfirm"
      });
      document.getElementById('app').style.overflow = ""

    },
    // 开票信息
    toggleChecked () {
      if (this.checked) {
        this.formData.invoiceInfo.mobile = this.formData.appliMobile;
        this.formData.invoiceInfo.email = this.formData.appliEMail;
        this.formData.invoiceInfo.companyName = this.formData.appliName;
        this.formData.invoiceInfo.invoiceType = "3";
        this.formData.invoiceInfo.customerType = "1";
        this.formData.invoiceInfo.taxPayerType = "4";
        this.formData.invoiceInfo.taxPayerNo = this.formData.insuredIdentifyNumber;

      } else {
        // this.formData.invoiceInfo.mobile = "";
        // this.formData.invoiceInfo.email = "";
        // this.formData.invoiceInfo.companyName = "";
        // this.formData.invoiceInfo.invoiceType = "";
        // this.formData.invoiceInfo.customerType = "";
        this.formData.invoiceInfo = {};
      }
    },
    // 同投被保人
    toggleSameInsured (a) {
      if (a == 1) {
        this.isSameInsured = true;
        this.formData.invoiceInfo.mobile = this.formData.appliMobile;
        this.formData.invoiceInfo.email = this.formData.appliEMail;
        this.formData.invoiceInfo.companyName = this.formData.appliName;
        this.formData.invoiceInfo.taxPayerNo = this.formData.appliIdentifyNumber;
      } else {
        this.isSameInsured = false;
        this.formData.invoiceInfo.mobile = this.formData.insuredMobile;
        this.formData.invoiceInfo.email = "";
        this.formData.invoiceInfo.companyName = this.formData.insuredName;
        this.formData.invoiceInfo.taxPayerNo = this.formData.insuredIdentifyNumber;
      }
    },
    // 是否电子发票
    toggleShould (a) {
      if (a == 1) {
        this.isShould = true;
        this.formData.invoiceInfo.mobile = this.formData.appliMobile;
        this.formData.invoiceInfo.email = this.formData.appliEMail;
        this.formData.invoiceInfo.invoiceType = "3";
      } else {
        this.isShould = false;
        this.formData.invoiceInfo.mobile = "";
        this.formData.invoiceInfo.email = "";
        this.formData.invoiceInfo.invoiceType = "2";
      }
    },
    ...mapMutations("million", {
      updtNewsumPremium: "UPDTNEWSUMPREMIUM",
      setNewData: "SETNEWDATA"
    })
  },
  computed: {
    ...mapGetters("million", ["newData"])
  },
  beforeRouteEnter: (to, from, next) => {
    next(vm => {
      if (
        from.name.indexOf("OrderDetail") != -1 ||
        from.name.indexOf("orderList") != -1
      ) {
        vm.goBack = "index";
      } else {
        vm.goBack = "prev";
      }
    });
  },
  components: {
    globalNav,
    // cityPicker
  }
};
</script>

<style lang="less" scoped>
.guardianLifeForm {
  background: #f7f7f7;
  padding-bottom: 50px;
  padding-top: 2rem;
  box-sizing: border-box;
  height: max-content !important;
  .title-img {
    margin: 9px 10px 5px 5px;
    img {
      width: 71.5px !important;
      height: 53px !important;
    }
  }

  .rowTitle {
    height: 60px;
    background-color: #fff;
  }
  .title-text {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;
  }

  .card {
    .card-img {
      margin: -4px 0 -4px 5px;
    }
    .title-container {
      height: 50px;
      margin-bottom: 0px;
      background-color: white;
    }
    .select-customer-text {
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: #3b8afe;
      line-height: 20px;
      margin-right: 12px;
      img {
        width: 20px;
        height: 20px;
      }
    }
  }
  .card-title-text {
    font-size: 14px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #333333;
    line-height: 25px;

    img {
      width: 22px;
      height: 22px;
      margin: -5px 3px;
    }
  }
  .tabbar {
    position: fixed; // todo suncao fixed
    -webkit-overflow-scroll: touch;
    bottom: 0;
    display: flex;
    width: 100%;
    z-index: 1;
    .price {
      flex: 1;
      text-align: center;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #fff;
      font-size: 16px;

      span {
        color: #fc820e;
        font-size: 17px;
        margin: 0 11px;
      }
    }

    .btn {
      flex: 1;
      font-size: 16px;
      button {
        width: 100%;
      }
    }
  }
  .left13 {
    display: flex;
    align-items: center;
    font-size: 16px;
    color: #969799;
  }
  /deep/ .van-field__control {
    text-align: right;
  }
  .date {
    .van-cell__title,
    .van-cell__value {
      flex: none;
    }
    .van-cell__title {
      width: 25%;
      flex-shrink: 0;
    }
    .van-cell__value {
      width: 75%;
      flex-shrink: 1;
    }
    .van-cell__value {
      color: #323233;
    }
  }
  .tip-container {
    margin: 5px 15px;
    height: 34px;
    background: #3b8afe1a;
    border-radius: 2px;
  }
  .select-button {
    width: 60px;
    height: 27px;
    margin-left: 10px;
    background: #eeeeee;
    border-radius: 6px;
    color: #b9b9b9;
    border: 1px solid #eeeeee;
    font-size: 11px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    line-height: 27px;
    white-space: nowrap;
    padding: 0;
  }
  .selected-button {
    color: #2b79ed;
    // border: 1px solid #3b8afe;
    background: #eef5ff;
  }
  select {
    appearance: none;
    border: none;
    color: #323233;
    background: #fff;
    outline: none;
    -moz-appearance: none; /* Firefox */
    -webkit-appearance: none; /* Safari 和 Chrome */
  }

  .after::after {
    position: absolute;
    box-sizing: border-box;
    content: " ";
    pointer-events: none;
    right: 0;
    bottom: 0;
    left: 0.42667rem;
    border-bottom: 0.02667rem solid #ebedf0;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
  }

  .allow-protocol-check-box {
    margin-right: 0.3rem;
  }
}
</style>