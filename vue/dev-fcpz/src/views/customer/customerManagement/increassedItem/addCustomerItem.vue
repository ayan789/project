<template>
  <div>
    <van-field
      readonly
      clickable
      name="picker"
      :value="customerTypePicker.text"
      label="客户类型"
      right-icon="arrow"
      @click="customerTypePicker.show=(statusType=='2'&&editorCustomerData.tempData.custSourceType=='2')?false:true"
      input-align="right"
    />
    <van-popup v-model="customerTypePicker.show" position="bottom" get-container="#addMyCustomer">
      <van-picker
        title="客户类型"
        show-toolbar
        :columns="customerTypePicker.options"
        @confirm="onCustomerTypeConfirm($event,'custType')"
        @cancel="customerTypePicker.show=false"
      />
    </van-popup>

    <van-field v-model="editorCustomerData.tempData.custName" label="姓名" required input-align="right" maxlength="40"
               placeholder="请输入用户名" :disabled="statusType=='2'&&editorCustomerData.tempData.custSourceType=='2'">
      <div slot="button" v-show="editorCustomerData.tempData.custType==1">
        <div class="buttonView" @click="nativeClick('1')">
          <span class="textClass">证件识别</span>
          <van-icon :name="$imageUrl.photo"/>
        </div>
      </div>
    </van-field>
    <van-field
      readonly
      clickable
      name="picker"
      :value="cardTypePicker.text"
      label="证件类型"
      required
      right-icon="arrow"
      @click="cardTypePicker.show=(statusType=='2'&&editorCustomerData.tempData.custSourceType=='2')?false:true"
      input-align="right"
    />
    <van-popup v-model="cardTypePicker.show" position="bottom" get-container="#addMyCustomer">
      <van-picker
        title="证件类型"
        show-toolbar
        :columns="cardTypePicker.options"
        @confirm="onCustomerTypeConfirm($event,'cardType')"
        @cancel="cardTypePicker.show=false"
      />
    </van-popup>

    <van-field v-model="editorCustomerData.tempData.certificateNo" label="证件号码" required input-align="right"
               placeholder="请输入证件号码" clearable
               :disabled="statusType=='2'&&editorCustomerData.tempData.custSourceType=='2'"
               :maxlength="editorCustomerData.tempData.custType=='2'&&editorCustomerData.tempData.certificateType=='01'?'9':'18'"/>
    <!--    <van-field v-model="editorCustomerData.tempData.phoneNumber" label="手机号" required input-align="right" right-icon="plus"-->
    <!--               placeholder="请输入手机号" @click-right-icon="addMobile"/>-->

    <!--    <div v-for="(v,i) in editorCustomerData.mobileList">-->
    <!--      <van-field v-model="v.tempMobile" label="" input-align="right"-->
    <!--                 placeholder="请输入手机号" :right-icon="editorCustomerData.mobileList.length>1?'cross':'plue'"-->
    <!--                 @click-right-icon="deleteMobile(i)"/>-->
    <!--    </div>-->
    <div v-for="(v,i) in editorCustomerData.tempData.mobileList">
      <van-field v-model="v.phoneNumber" :label="i==0?'手机号':''" input-align="right" :required="i==0"
                 placeholder="请输入手机号" clearable type="digit"
                 :disabled="statusType=='2'&&editorCustomerData.tempData.custSourceType=='2'&&v.type=='Y'"
                 :right-icon="editorCustomerData.tempData.mobileList.length>1&&i!=0?$imageUrl.deleteIcon:$imageUrl.add"
                 @click-right-icon="phoneClick(v,i)"
                 maxlength="11"/>
    </div>
    <div v-show="editorCustomerData.tempData.custType==1">
      <van-field v-model="editorCustomerData.tempData.carNo" label="车牌号" input-align="right" clearable
                 @blur="blurClick('carNo')" maxlength="11"
                 placeholder="请输入车牌号" v-show="editorCustomerData.tempData.custSourceType!='2'">
        <div slot="button">
          <div class="buttonView" @click="nativeClick('2')">
            <span class="textClass">行驶证识别</span>
            <van-icon :name="$imageUrl.photo"/>
          </div>
        </div>
      </van-field>
      <van-field v-model="editorCustomerData.tempData.frameNo" label="车架号" input-align="right" clearable maxlength="17"
                 @blur="blurClick('frameNo')"
                 placeholder="请输入车架号" v-show="editorCustomerData.tempData.custSourceType!='2'"/>
      <van-field v-model="editorCustomerData.tempData.brandNo" label="厂牌型号" input-align="right" clearable
                 @blur="blurClick('brandNo')"
                 placeholder="请输入厂牌型号" v-show="editorCustomerData.tempData.custSourceType!='2'"/>
      <van-field v-model="editorCustomerData.tempData.engineNo" label="发动机号" input-align="right" clearable
                 @blur="blurClick('engineNo')"
                 placeholder="请输入发动机号" v-show="editorCustomerData.tempData.custSourceType!='2'"/>
      <van-field v-model="editorCustomerData.tempData.email" label="邮箱" input-align="right" clearable
                 placeholder="请输入邮箱"
                 :disabled="statusType == '2' && editorCustomerData.tempData.custSourceType == '2'"/>
      <van-field v-model="editorCustomerData.tempData.profession" label="职业" input-align="right" clearable
                 maxlength="40"
                 placeholder="请输入职业"/>
      <van-field v-model="editorCustomerData.tempData.address" label="联系地址" input-align="right" clearable
                 maxlength="100"
                 autosize rows="1" type="textarea"
                 placeholder="请输入联系地址">
        <div slot="button">
          <div class="buttonView" @click="nativeClick('3')">
            <span class="textClass">定位</span>
            <van-icon :name="$imageUrl.location"/>
          </div>
        </div>
      </van-field>
      <van-field
        readonly
        clickable
        name="picker"
        :value="addressTypePicker.text"
        label="地址类型"
        right-icon="arrow"
        @click="addressTypePicker.show=true"
        input-align="right"
      />
      <van-popup v-model="addressTypePicker.show" position="bottom" get-container="#addMyCustomer">
        <van-picker
          title="地址类型"
          show-toolbar
          :value="addressTypePicker.text"
          :columns="addressTypePicker.options"
          @confirm="onCustomerTypeConfirm($event,'addressType')"
          @cancel="addressTypePicker.show=false"
        />
      </van-popup>
      <!--      <div v-for="(v,i) in editorCustomerData.addressList">-->
      <!--        <van-field v-model="v.address" label="联系地址" input-align="right" right-icon="fail"-->
      <!--                   placeholder="请输入联系地址" @click-right-icon="deleteAddress(i)"/>-->
      <!--        <van-field-->
      <!--          readonly-->
      <!--          clickable-->
      <!--          name="picker"-->
      <!--          :value="v.text"-->
      <!--          label="地址类型"-->
      <!--          right-icon="arrow"-->
      <!--          @click="addressTypePicker.show=true"-->
      <!--          input-align="right"-->
      <!--        />-->
      <!--        <van-popup v-model="addressTypePicker.show" position="bottom">-->
      <!--          <van-picker-->
      <!--            title="地址类型"-->
      <!--            show-toolbar-->
      <!--            :columns="addressTypePicker.options"-->
      <!--            @confirm="onCustomerTypeConfirm($event,'addressType_',i)"-->
      <!--            @cancel="addressTypePicker.show=false"-->
      <!--          />-->
      <!--        </van-popup>-->
      <!--      </div>-->
      <div @click="toCarListView()"
           v-show="editorCustomerData.tempData.carList&&editorCustomerData.tempData.carList.length>0">
        <van-field label="车辆信息" input-align="right" right-icon="arrow" disabled clearable
                   v-show="editorCustomerData.tempData.custSourceType=='2'"/>
      </div>

      <div @click="portraitUpload">
        <van-field label="影像上传" input-align="right" right-icon="arrow" disabled clearable/>
      </div>
    </div>

    <div v-show="editorCustomerData.tempData.custType==2">
      <van-field v-model="editorCustomerData.tempData.email" label="邮箱" input-align="right" clearable
                 :disabled="statusType=='2'&&editorCustomerData.tempData.custSourceType=='2'"
                 placeholder="请输入邮箱"/>
      <van-field
        readonly
        clickable
        name="picker"
        :value="unitNatureTypePicker.text"
        label="单位性质"
        right-icon="arrow"
        @click="unitNatureTypePicker.show=(statusType=='2'&&editorCustomerData.tempData.custSourceType=='2')?false:true"
        input-align="right"
      />
      <van-popup v-model="unitNatureTypePicker.show" position="bottom" get-container="#addMyCustomer">
        <van-picker
          title="单位性质"
          show-toolbar
          :columns="unitNatureTypePicker.options"
          @confirm="onCustomerTypeConfirm($event,'unitNatureType')"
          @cancel="unitNatureTypePicker.show=false"
        />
      </van-popup>

      <van-field v-model="editorCustomerData.tempData.businessScope" label="企业经营范围" input-align="right" clearable
                 maxlength="60"
                 placeholder="请输入企业经营范围"/>
      <van-field v-model="editorCustomerData.tempData.contractedProducts" label="可承保产品" input-align="right" clearable
                 maxlength="60"
                 placeholder="请输入可承保产品"/>
      <van-field v-model="editorCustomerData.tempData.address" label="联系地址" input-align="right" clearable autosize
                 maxlength="100"
                 rows="1" type="textarea"
                 placeholder="请输入联系地址">
        <div slot="button">
          <div class="buttonView" @click="nativeClick('3')">
            <span class="textClass">定位</span>
            <van-icon :name="$imageUrl.location"/>
          </div>
        </div>
      </van-field>
      <van-field v-model="editorCustomerData.tempData.remark" label="备注" input-align="right" clearable maxlength="30"
                 placeholder="请输入备注"/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import mapper from "../../../../utils/mapper";
import { Toast } from "vant";
import * as native from "../../../../utils/JSBridge";

const typeOptions = mapper.custType;
const cardTypeOptions_1 = mapper.cardType_person;
const cardTypeOptions_2 = mapper.cardType_business;
const addressTypeOptions = mapper.addressType;
const unitNatureTypeOptions = mapper.unitNatureType;

export default {
  name: "addCustomerItem",
  data() {
    return {
      phoneList: [{ phoneNumber: '123' }],
      customerTypePicker: {
        show: false,
        text: '个人客户',
        options: typeOptions,
        indexType: "0"
      },
      cardTypePicker: {
        show: false,
        text: cardTypeOptions_1[0].text,
        options: cardTypeOptions_1,
        indexType: "0"
      },
      addressTypePicker: {
        show: false,
        text: addressTypeOptions[0].text,
        options: addressTypeOptions,
        indexType: "0"
      },
      unitNatureTypePicker: {
        show: false,
        text: unitNatureTypeOptions[0].text,
        options: unitNatureTypeOptions,
        indexType: "0"
      },
    };
  },
  props: {
    statusType: {
      type: String,
      default: "1"
    }
  },

  computed: {
    ...mapState(["editorCustomerData", "customerInfoData"])
  },

  watch: {
    editorCustomerData: {
      handler: function (val, oldVal) {
        this.$nextTick(function () {
          addressTypeOptions.map((v, i) => {
            if (v.value == val.tempData.addressType) {
              this.addressTypePicker.text = v.text;
            }
          });
          this.customerTypePicker.text = typeOptions[val.tempData.custType - 1].text;
          let arr = val.tempData.custType == '1' ? cardTypeOptions_1 : cardTypeOptions_2;
          arr.map((v, i) => {
            if (val.tempData.certificateType == v.value) {
              this.cardTypePicker.text = v.text;
            }
          });
          let type_ = arr.filter(v => {
            return this.cardTypePicker.text == v.text;
          });
          if (type_.length == 0) {
            this.cardTypePicker.text = arr[arr.length - 1].text;
          }
          this.cardTypePicker.options = arr;
          unitNatureTypeOptions.map((v, i) => {
            if (val.tempData.unitNature == v.value) {
              this.unitNatureTypePicker.text = v.text;
            }
          });
        });
      },
      // 深度观察监听
      deep: true,
      immediate: true
    }
  },
  methods: {
    blurClick(name) {
      let value_ = "";
      if (name == 'carNo') {
        value_ = this.editorCustomerData.tempData.carNo.trim().toUpperCase();
      } else if (name == 'brandNo') {
        value_ = this.editorCustomerData.tempData.brandNo.trim().toUpperCase();
      } else if (name == 'frameNo') {
        value_ = this.editorCustomerData.tempData.frameNo.trim().toUpperCase();
      } else if (name == 'engineNo') {
        value_ = this.editorCustomerData.tempData.engineNo.trim().toUpperCase();
      }
      this.changeForm(name, value_);
    },
    phoneClick(v, i) {
      if (i == 0) {
        console.log('add');
        this.$store.commit('editorCustomerData/addMobile_');
      } else {
        console.log('delete');
        if (this.statusType == '2' && this.editorCustomerData.tempData.custSourceType == '2' && v.type == 'Y') {
          return;
        } else {
          this.$store.commit('editorCustomerData/deleteMobile_', i);
        }
      }
    },
    nativeClick(index) {
      if (index == '1') {
        if (this.statusType == '2' && this.editorCustomerData.tempData.custSourceType == '2') {
          return;
        } else {
          //身份证识别 OCR
          const jsonObject = {
            identifyType: "1",//"0" - 识别行驶证; "1" - 识别身份证
            side: "face",//"face"：正面；"back": 反面;
          };
          this.$native.identifyOCR(jsonObject, data => {
            let tempData = JSON.parse(data);
            if (tempData.resultCode == '1') {
              const { name, cardNo } = tempData.responseBody;
              console.log(tempData.responseBody.name, tempData.responseBody.cardNo, '==身份证识别 ==');
              this.changeForm('certificateNo', cardNo);
              this.changeForm('custName', name);
            } else {
              Toast(tempData.resultMsg);
            }
          });
        }
      } else if (index == '2') {
        //行驶证识别 OCR
        const jsonObject = {
          identifyType: "0",//"0" - 识别行驶证; "1" - 识别身份证
          side: "back",//"face"：正面；"back": 反面;
        };
        this.$native.identifyOCR(jsonObject, data => {
          console.log("行驶证识别，壳响应数据:" + data);
          let tempData = JSON.parse(data);
          if (tempData.resultCode == '1') {
            this.changeForm('carNo', tempData.responseBody.cardNo);
          } else {
            Toast(tempData.resultMsg);
          }
        });
      } else {
        this.$native.getLocation(data => {
          let tempData = JSON.parse(data);
          const { City, Thoroughfare, subLocality } = tempData.responseBody;
          console.log(City, Thoroughfare, subLocality, 'p-----');
          if (tempData.resultCode == '1') {
            let value = City + '-' + subLocality + '-' + Thoroughfare;
            this.changeForm('address', value);
          } else {
            Toast(tempData.resultMsg);
          }
        });
      }
    },
    //新增手机号
    addMobile() {
      this.$store.commit('editorCustomerData/addMobile_');
    },
    //删除手机号
    deleteMobile(i) {
      this.$store.commit('editorCustomerData/deleteMobile_', i);
    },
    //新增地址
    addAddress() {
      this.$store.commit('editorCustomerData/addAddress_');
    },
    //删除地址
    deleteAddress(i) {
      this.$store.commit('editorCustomerData/deleteAddress_', i);
    },
    onCustomerTypeConfirm(item, type, index) {
      switch (type) {
        case 'custType':
          this.customerTypePicker.text = item.text;
          this.customerTypePicker.show = false;
          this.customerTypePicker.indexType = +item.value - 1;
          this.changeForm('custType', item.value);
          break;
        case 'cardType':
          this.cardTypePicker.text = item.text;
          console.log(this.cardTypePicker.text, 'card===');
          this.cardTypePicker.show = false;
          this.changeForm('certificateType', item.value);
          break;
        case 'addressType':
          this.addressTypePicker.text = item.text;
          this.addressTypePicker.show = false;
          this.changeForm('addressType', item.value);
          break;
        case 'addressType_':
          // this.addressTypePicker.text = item.text;
          this.addressTypePicker.show = false;
          this.$store.commit('editorCustomerData/changeForm_', { item, index });
          break;
        case 'unitNatureType':
          this.unitNatureTypePicker.text = item.text;
          this.unitNatureTypePicker.show = false;
          this.changeForm('unitNature', item.value);
          break;
      }
    },
    changeForm(name, value) {
      this.$store.commit('editorCustomerData/changeForm', { name, value });
    },
    toCarListView(item) {
      this.$router.push({ name: 'carListView' });
    },
    async portraitUpload() {
      let d = new Date();
      let monthTmp = d.getMonth() + 1;
      let dayTmp = d.getDate();
      let month = "";
      let day = "";
      if (monthTmp < 10) {
        month = "0" + monthTmp;
      } else {
        month = monthTmp;
      }
      if (dayTmp < 10) {
        day = "0" + dayTmp;
      } else {
        day = dayTmp;
      }


      let busiDate = d.getFullYear() + month + day + "";
      await this.$store.dispatch(
        "customerInfoData/imageUpload",
        {
          "comCode": this.$localStorage.comCode,
          "bussNo": localStorage.getItem("userCode") + "_" + this.editorCustomerData.tempData.certificateNo,
          "busiDate": busiDate,
          "imgType": "CJXS",//固定值
          "flag": '1'//0 内网 1外网
        }
      );
      let imageUpload = this.customerInfoData.imageUpload;
      console.info(imageUpload, "打开的");
      if (imageUpload && imageUpload.url) {
        let param = {
          type: "0",
          titleBarTag: "1",
          titleBar: '影像上传',
          sharePlatform: [],
          shareType: "url",
          title: "影像上传",
          imgURL: "",
          image: "",
          url: imageUpload.url,
          desc: "影像上传"
        };
        this.$native.openWebView(param, data => {
        });

      }
    }
  },
};
</script>

<style scoped>
.buttonView {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.textClass {
  font-size: 12px;
  color: #3B8AFE;
  margin-right: 5px;
}
</style>
