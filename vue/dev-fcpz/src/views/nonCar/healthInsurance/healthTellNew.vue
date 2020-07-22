<template>
  <div class="healthTell">
    <globalNav
      :title="globalNav.title"
      @clickBack="goback"
    />
    <div class="card">
      <van-cell-group>
        <div class="tittle">
          <van-tag
            type="primary"
            class="tag"
          >重要</van-tag>
          <span>为保证被保人的保险权益在理赔时不受影响，请确认被保人健康及职业状况是否符合投保条件</span>
        </div>
        <div class="title-text">
          <p>投保人应在对所有被保险人健康，职业，历史投保记录等以下情况充分了解的基础上履行如实告知义务。投保人承诺完全知晓所有被保险人以下情况。若被保险人实际情况与下述告知内容不符：</p>
          <p>（1）本公司有权不同意承保。</p>
          <p>（2）若发生保险事故，本公司不承担赔偿或给付保险金的责任，并有权不退还保险费</p>
          <p>
            投保人需确认被保险人是否有以下情况？
          </p>

          <p>
            1.
            被保险人是否从事油漆、有毒物质生产运输职业？
          </p>
          <p>
            2.
            被保险人的直系亲属（父母子女兄弟姐妹）是否有2位或2位以上在60周岁前被诊断为同一癌症？
          </p>
          <p>
            3.被保险人是否曾被诊断为下列任一疾病，出现以下任一体征或检查异常：
          </p>
          <p>A、恶性肿瘤（包括白血病）、癌前病变、原位癌、类癌、乙型肝炎或乙肝病毒携带者、丙型肝炎或丙肝病毒携带者、溃疡性结肠炎、胰腺炎；</p>

          <p>
            B、 未明性质的肿块（如乳房肿块）/息肉/结节（如甲状腺结节）/肿瘤/新生物；
          </p>
          <p>C、吞咽困难、咳血、吐血、便血（非痔疮出血）或黑便、血尿、贫血、半年内非健身原因所致的体重减少5公斤以上。</p>
          <p>D、肿瘤标志物*或组织及细胞病理学检查异常；</p>

          <p>4、（女性适用）是否有不规则阴道出血、乳头异常溢液、糜烂或回缩、乳房表面皮肤凹陷、皱褶或皮肤收缩、宫颈肿物/上皮内瘤样病变、畸胎瘤、葡萄胎、绒毛膜癌或其他滋养细胞疾病？</p>
          <p>
            <b style="color:red;">注: </b>肿瘤标志物*：甲胎蛋白（AFP）、癌胚抗原（CEA）、前列腺特异性抗原（PSA）、癌抗原125（CA125）、癌抗原199（CA199）、癌抗原15-3（CA15-3）、癌抗原50（CA50）、糖类抗原242（CA242）、胃癌相关抗原（CA72-4）、铁蛋白（SF）、β2微球蛋白(β2-MG)、神经元特异性烯醇化酶（NSE）、鳞状细胞癌抗原（SCCA）、核基质蛋白-22（NMP-22）、.α-L-岩藻糖苷酶（AFU），人绒毛膜促性腺激素(HCG)。
          </p>
        </div>
        <div class="radio">
          <van-radio-group
            v-model="radio"
            direction="horizontal"
            ref="radio"
            :disabled="disabled"
          >
            <van-radio name="1">否</van-radio>
            <van-radio name="2">是</van-radio>
          </van-radio-group>
        </div>
        <van-checkbox
          v-model="checked"
          class="checkbox"
          :disabled="disabled"
        >声明内容</van-checkbox>
        <van-cell
          style="padding:0 14px 0 20px;"
          value="本人确认以上填写的各项内容均真实，未发生健康与职业声明中所有情形；日后如发现有不实告知，保险公司有权按《保险法》等有关法律规定解除或部分解除保险合同。
"
        />
      </van-cell-group>
    </div>
    <van-button
      type="info"
      size="large"
      @click="enter"
      :disabled="disabled"
    >下一步</van-button>
    <van-dialog
      v-model="show"
      title="标题"
      show-cancel-button
    >
      <img src="https://img.yzcdn.cn/vant/apple-3.jpg" />
    </van-dialog>
  </div>
</template>
<script>
import globalNav from "../../../components/globalNav";
import { Toast, Dialog } from "vant";
export default {
  data () {
    return {
      // 公共顶部
      globalNav: {
        title: "健康及职业告知",
        isShare: true
      },
      radio: "",
      checked: false,
      show: false,
      params: "",
      disabled: false
    };
  },
  beforeRouteEnter: (to, from, next) => {
    if (from.name === "signatureConfirm") {
      next(vm => {
        vm.radio = "1";
        vm.checked = true;
      });
    } else {
      next();
    }
  },
  mounted () {
    this.$nextTick(() => {
      if (this.radio == "1" && this.checked == true) {
        this.disabled = true;
      }
    });
  },
  methods: {
    goback () {
      this.$router.go(-1);
      // this.$router.push({name:'millionMedicalCare'})
    },
    enter () {
      if (this.radio == "") {
        Toast("请认真阅读并勾选");
        return;
      }
      if (this.radio == "2") {
        Dialog.confirm({
          message:
            "尊敬的客户，根据您目前的健康状态，我公司对您本次的投保不能支持，感谢对我公司的关爱与支持",
          confirmButtonText: "返回产品",
          cancelButtonText: "我再看看"
        })
          .then(() => {
            this.$router.go(-1);
          })
          .catch(() => {
            return;
          });
      } else if (!this.checked) {
        Toast("请勾选底部声明内容");
        return;
      } else {
        this.$router.push({
          name: "stopCancerForm"
        });
      }
    }
  },
  components: {
    globalNav
  }
};
</script>
<style lang="less" scoped>
.healthTell {
  background-color: #fff;
  width: 100%;
  height: 100%;
  overflow: scroll;
  -webkit-overflow-scrolling: touch;
  position: absolute;
  left:0;
  top:0;
  box-sizing: border-box;
  .card {
    padding: 2rem 0;
    background-color: #fff;
    .title-text {
      font-size: 14px;
      color: #999999;
      padding: 0 19px 14px;
    }
    .tittle {
      box-sizing: border-box;
      padding: 0.2rem;
      // padding: 14px 19px;
      // font-size: 14px;
      // line-height: 19px;
      .tag {
        width: 13.5%;
        text-align: center;
        font-size: 9px !important;
        font-weight: 500 !important;
        margin-right: 0.2rem;
        margin-left: 10px;
      }
      span {
        font-weight: 600;
        font-size: 14px;
        // margin-right: 14px;
      }

      font-size: 16px;
      display: flex;
      align-items: center;
    }
    /deep/ .van-cell__title,
    .van-cell__value {
      flex: auto;
      text-align: left;
      color: #999999;
    }
    .radio {
      background-color: #f7f7f7;
      margin: 0.5rem;
      border-radius: 14px;
      /deep/ .van-radio-group,
      .van-radio-group--horizontal {
        justify-content: space-around;
        padding: 0.6rem;
      }
    }
    .checkbox {
      padding: 0.2rem;
      padding-left: 0.5rem;
      font-size: 14px;
      font-weight: 600;
    }
  }
  .van-button,
  .van-button--info,
  .van-button--large {
    position: fixed;
    bottom: 0;
  }
}
</style>