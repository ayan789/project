<template>
    <div class="discount_server">
        <div @click="equities(remindList.length>0 && remindList[0])" class="warn" >提醒</div>
        <van-dialog v-model="show" title="脱保原因" show-cancel-button confirm-button-text="保存" :closeOnClickOverlay="true"
                    :closeOnPopstate="true" :beforeClose="confirmClick">
            <div class="discount_dialog">
                <div class="discount_dialog1">
                    脱保原因
                </div>
                <van-field
                        readonly
                        clickable
                        name="picker"
                        :value="value"
                        label="请选择"
                        :right-icon="listShow?'arrow-up':'arrow-down'"
                        input-align="right"
                        required
                        @click-right-icon="clickright"
                />
                <div v-if="listShow" class="discount_list">
                    <van-list>
                        <div v-for="(item,index) in list" :key="index" class="discount_listItem" @click="clickItem(item)">
                            {{item.label}}
                        </div>
                    </van-list>
                </div>
                <div class="discount_dialog1">
                    详细内容
                </div>
                <van-field
                        v-model="reasonDetail"
                        rows="3"
                        autosize
                        type="textarea"
                        maxlength="50"
                        show-word-limit
                        placeholder="请输入脱保原因"
                />
            </div>
        </van-dialog>
    </div>
</template>
<script>
  import mapper from "../../../utils/mapper";
  import { updateUnRenewReason } from '../../../http/module/renewal';
  import { Toast } from 'vant';
  import { doneTaskClick } from "../../../http/module/customerApi";
  export default {
    name: "reasonsInsure",
    data() {
      return {
        show: false,
        value: '',
        list: mapper.cancelRenewal,
        listShow: false,
        reasonType: '',
        policyNo: '',
        reasonDetail: '',
        riskCode: '',
        id: '',
        custNo: "",
        remindList:[]
      };
    },
    props: {
      remind: Object,
      showProp:Boolean
    },
    created() {
      console.info("进来了，进来了")
      let tmp = [];
      tmp.push(this.remind)
      this.remindList = tmp
      console.info("remindList",this.remindList)
      // this.equities(this.remind)
    },
    methods: {
      async confirmClick(action, done) {
        console.log(action, '===');
        if (action === 'confirm') {
          if (this.value) {
            if (this.reasonType == 'RSK' && !this.reasonDetail) {
              Toast('请填写具体的脱保原因');
              return done(false);
            } else {
              let params = {
                policyNo: this.policyNo,
                reasonType: this.reasonType,
                riskCode: '',
                reasonDetail: this.reasonDetail,
                userCode: localStorage.getItem("userCode")
              };
              let res = await updateUnRenewReason(params);
              if (res.code == '1') {
                let params = {
                  remindId: this.id
                };
                doneTaskClick(params);
                this.show = false;
                done();
              } else {
                Toast(res.msg);
                done(false);
              }
            }
          } else {
            Toast('请选择脱保类型!');
            return done(false);
          }
        } else {
          return done();
        }
      },
      clickItem(item) {
        this.value = item.label;
        this.reasonType = item.value;
        this.listShow = false;
        console.log(item, '===item');
      },
      //显示下拉菜单
      clickright() {
        this.listShow = !this.listShow;
      },
      toCustomerInfo(item) {
        console.log(item, '=====');
        // 跳转客户信息的详情
        this.$router.push({ name: 'customerEquity', query: { viewType: '1', item: item } });
      },
      equities(item) {
        console.log(item, 'ooo===');
        // 提醒类型
        switch (item.remindType) {
          case '4':
            // 提醒
            this.$router.push({ name: 'registeredView', query: { item: item } });
            break;
          case '5':
            // 转发推荐码
            // this.flag = true;
            this.$router.push({ name: 'RegisteredManView', query: { item: item } });
            break;
          case '3':
            // 送上祝福
            this.$router.push({ name: 'birthdayCelebrate', query: { item: item } });
            break;
          case '2':
            this.policyNo = item.policyNo;
            this.custNo = item.custNo;
            this.id = item.id;
            if (item.outOfInsuranceStatus == '1') {
              this.show = true;
            } else {
              // 跟进 ==> 跳转续保详情 renewalContactDetails
              this.$router.push({ name: 'renewalContactDetails', query: { item: item } });
            }
            break;
          case '1':
            // 查看 ==> 理赔详情
            this.$router.push({ name: 'customerClaimProgress', query: { item: item } });
            break;
        }
      }
    }
  };
</script>
<style lang="less" spoced>
    .discount_server {
        background-color: #ffffff;
        padding: 0px;
        /**/
        /deep/ .van-cell {
            background-color: #F5F5F5;
            border-radius: 7px;
        }
        .warn {
            width: 60px;
            font-weight: 500;
            color: #3B8AFE;
            padding: 4px 0px;
            border-radius: 5px;
            font-size: 10px;
            border: 1px solid #3B8AFE;
            text-align: center;
        }
        ul {
            li {
                height: 72px;
                border-bottom: 1px solid #e5e6e8;
                padding-top: 12px;
                overflow: hidden;
                position: relative;

                .img {
                    width: 44px;
                    height: 44px;
                    margin-top: 3px;
                    background: PINK;
                    float: left;
                }

                .reduced {
                    float: left;
                    padding-left: 11px;

                    .give {
                        height: 26px;
                        font-size: 14px;
                        font-weight: 500;
                        color: rgba(51, 51, 51, 1);
                        line-height: 26px;
                    }

                    .pastdue {
                        height: 26px;
                        font-size: 13px;
                        font-weight: 500;
                        color: rgba(153, 153, 153, 1);
                        line-height: 26px;
                    }
                }


            }

            li:nth-last-child(1) {
                border-bottom: transparent;
            }
        }


        .discount_dialog {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            padding: 10px;

            .discount_list {
                position: absolute;
                z-index: 19;
                top: 140px;
                width: 100%;
                height: 120px;
                overflow: scroll;
            }

            .discount_listItem {
                background-color: white;
                padding: 0px 10px;
                font-size: 14px;
                color: #333333;
            }

            .discount_dialog1 {
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                align-items: center;
                font-size: 14px;
                color: #333333;
                width: 100%;
                margin: 8px 0px;
            }
        }
    }
</style>
