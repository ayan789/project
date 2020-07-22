<template>
  <div class="insuranceInformation">
    <h3>投保信息填写</h3>
    <div class="insuranceInfoHeader">
      <h3>货运无忧</h3>
    </div>
    <div class="policyholdersInfo top10">
      <ul>
        <li>
          <span class="policyholdersInfoTitle">投保人信息</span>
          <span class="selectInsuredInfo rightCenter">
            <span class="right13">选择投保人</span>
            <van-icon name="user-o" />
          </span>
        </li>
        <li>
          <span>投保人姓名</span>
          <span class="rightCenter">
            <span class="right13 gary193">请选择</span>
            <van-icon name="arrow" class="grayBf" />
          </span>
        </li>
        <li>
          <span>证件类型</span>
          <span class="rightCenter">
            <span class="right13 gary102">身份证</span>
            <van-icon name="arrow" class="grayBf" />
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="证件号码"
            label="证件号码"
            placeholder="请输入证件号码"
            :rules="[{ required: true, message: '请输入证件号码' }]"
          />
          <span class="selectInsuredInfo inputRightContent">
            <span class="right13">身份证件识别</span>
            <van-icon name="photograph" />
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="手机号"
            label="手机号"
            placeholder="请输入手机号"
            input-align="right"
            :rules="[{ required: true, message: '请输入手机号' }]"
          />
        </li>
        <li>
          <van-field
            v-model="username"
            name="邮箱"
            label="邮箱"
            input-align="right"
            placeholder="请输入邮箱地址，可接收电子保单"
            :rules="[{ required: true, message: '请输入邮箱地址，可接收电子保单' }]"
          />
        </li>
      </ul>
    </div>
    <div class="insuredInfo policyholdersInfo top10">
      <ul>
        <li>
          <span class="policyholdersInfoTitle">被保人信息</span>
          <span class="selectInsuredInfo rightCenter">
            <span class="right13">选择被保人</span>
            <van-icon name="user-o" />
          </span>
        </li>
        <li>
          <span>同投保人</span>
          <span class="rightCenter">
            <van-switch v-model="checked" size="14px" />
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="被保人姓名"
            label="被保人姓名"
            input-align="right"
            placeholder="请输入姓名"
            :rules="[{ required: true, message: '请输入姓名' }]"
          />
        </li>
        <li>
          <span>证件类型</span>
          <span class="rightCenter">
            <span class="right13 gary102">身份证</span>
            <van-icon name="arrow" class="grayBf" />
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="证件号码"
            label="证件号码"
            placeholder="请输入证件号码"
            :rules="[{ required: true, message: '请输入证件号码' }]"
          />
          <span class="selectInsuredInfo inputRightContent">
            <span class="right13">身份证件识别</span>
            <van-icon name="user-o" />
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="手机号"
            label="手机号"
            placeholder="请输入手机号"
            input-align="right"
            :rules="[{ required: true, message: '请输入手机号' }]"
          />
        </li>
      </ul>
    </div>
    <div class="insuredVehicle policyholdersInfo top10">
      <ul>
        <li>
          <span class="policyholdersInfoTitle">被保车辆</span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="证件号码"
            label="证件号码"
            placeholder="请输入证件号码"
            :rules="[{ required: true, message: '请输入证件号码' }]"
          />
          <span class="selectInsuredInfo inputRightContent">
            <span class="right13">自动查询</span>
            <van-icon name="user-o" />
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="发动机号"
            label="发动机号"
            input-align="right"
            placeholder="请输入发动机"
            :rules="[{ required: true, message: '请输入发动机' }]"
          />
        </li>
        <li>
          <van-field
            v-model="username"
            name="车型"
            label="车型"
            placeholder="请输入车型"
            input-align="right"
            :rules="[{ required: true, message: '请输入车型' }]"
          />
        </li>
        <li>
          <van-field
            v-model="username"
            name="吨位(T)"
            label="吨位(T)"
            placeholder="请输入吨位"
            input-align="right"
            :rules="[{ required: true, message: '请输入吨位' }]"
          />
        </li>
      </ul>
    </div>
    <div class="coverPeriod policyholdersInfo top10">
      <ul>
        <li>
          <span class="policyholdersInfoTitle">保障期限</span>
        </li>
        <li>
          <span>保障年限</span>
          <span class="gary102">1年</span>
        </li>
        <li>
          <span>选择保险起期</span>
          <span class="rightCenter">
            <span class="right13 gary102">2020-01-16</span>
            <van-icon name="arrow" class="grayBf" />
          </span>
        </li>
      </ul>
    </div>
    <div class="billingInfo policyholdersInfo top10">
      <ul>
        <li>
          <span class="policyholdersInfoTitle">开票信息</span>
          <span class="selectInsuredInfo rightCenter">
            <van-switch v-model="checked" size="14px" />
          </span>
        </li>
        <li class="eleInvoiceTip">
          <van-icon name="question" class="right5 verticalMid" />
          <span>电子发票与纸质发票具有同等效力，可作为报销凭据</span>
        </li>
        <li>
          <span>是否需要电子发票</span>
          <span class="rightCenter">
            <button class="right10 btnActive">是</button>
            <button>否</button>
          </span>
        </li>
        <li>
          <span>发票抬头</span>
          <span class="rightCenter">
            <button class="right10 btnActive">同投保人</button>
            <button>同被保人</button>
          </span>
        </li>
        <li>
          <van-field
            v-model="username"
            name="纳税人识别号"
            label="纳税人识别号"
            placeholder="请输入纳税人识别号"
            input-align="right"
            :rules="[{ required: true, message: '请输入纳税人识别号' }]"
          />
        </li>
        <li>
          <van-field
            v-model="username"
            name="移动电话"
            label="移动电话"
            required
            placeholder="用来接收电子发票链接"
            input-align="right"
            :rules="[{ required: true, message: '用来接收电子发票链接' }]"
          />
        </li>
        <li>
          <van-field
            v-model="username"
            name="邮箱"
            label="邮箱"
            input-align="right"
            placeholder="用来接收电子发票邮箱"
            :rules="[{ required: true, message: '用来接收电子发票邮箱' }]"
          />
        </li>
      </ul>
    </div>
    <div class="insuranceInformationBottom top10">
      <div class="insuranceInformationBottomTop"></div>
      <div class="insuranceInformationBottomMid">
        <p>
          <input type="radio" name="agree" v-model="agree" @click="changeAgree" />
          <span>
            我确认并同意
            <strong>《保险条款》</strong>
            <strong>《投保须知》</strong>
            <strong>《投保人声明》</strong>和
            <strong>《特别约定》</strong>
          </span>
        </p>
      </div>
      <div class="insuranceInformationBottomBottom">
        <span class="premium">保费</span>
        <span>
          <span class="redCost">¥30,000.00</span>
          <button>立即投保</button>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import "@/style/nocar/insuranceInformation.less";
import Vue from "vue";
import { Field } from "vant";
import { Switch } from "vant";

Vue.use(Switch);
Vue.use(Field);
export default {
  name: "insuranceInformation",
  components: {},
  data: () => ({
    isBasicVersion: true,
    isProxy: true,
    activeNames: [],
    isCollage: false,
    username: "",
    password: "",
    checked: true,
    agree: false
  }),
  methods: {
    // 切换同意声明条款
    changeAgree() {
      if (this.agree) {
        this.agree = false;
      } else {
        this.agree = true;
      }
    }
  }
};
</script>

<style lang="less" scoped></style>
