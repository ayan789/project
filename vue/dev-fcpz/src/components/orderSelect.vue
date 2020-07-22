<template>
    <div class='god'>
        <div id="superPOP" v-for="(item,index) in datas" :key="index">
            <div class="father">
                <div>{{item.title}}</div>
                <div class="listItem">
                    <div class="item" v-for="(x,idx) in item.items" :key="idx" @click="clickItem(index,idx)"
                        :style="datas[index].selectIndex==idx+1?selectStyle:unSelectStyle">{{x}}</div>
                    <div class="timeSelector" v-if="index==1">
                        <div class="begin" @click="selectPicker(true)">{{beginTime}}</div>
                        <div class="separate"></div>
                        <div class="end" @click="selectPicker(false)">{{endTime}}</div>
                    </div>
                    <div class="inputAmount" v-if="index==2">
                        <input class="begin beginFee" type="text" placeholder="最低保费" v-model="lowestAmount" />
                        <div class="separate"></div>
                        <input class="end endFee" type="text" placeholder="最高保费" v-model="topestAmount" />
                    </div>
                </div>
            </div>
        </div>
        <div id="sureBotton" @click="sureAction">确定</div>
        <van-popup v-model="showPicker" round position="bottom" :overlay="true">
            <van-datetime-picker v-show="showPicker" v-model="currentDate" type="date" :min-date="minDate"
                :max-date="maxDate" @confirm="selectPickerComfirm" @cancel="cancelSelectPicker" />
        </van-popup>
    </div>
</template>
<script>
    export default {
        name: "orderSelect",
        props: {},
        data() {
            return {
                showPicker: false,
                minDate: new Date(2020, 0, 1),
                maxDate: new Date(2025, 10, 1),
                currentDate: new Date(),
                isBegin: true, //是否设置的起始时间
                beginTime: "开始日期",
                endTime: "结束日期",
                lowestAmount: "", //最低保费
                topestAmount: "", //最高保费
                unSelectStyle: {
                    background: "#F9FAFC",
                    color: "#666666"
                },
                selectStyle: {
                    background: "rgba(59,138,254,0.09)",
                    color: "#3B8AFE"
                },

                datas: [
                    {
                        selectIndex: 0,
                        title: "订单类型",
                        items: ["全部", "车险", "意健险", "财产险"]
                    },
                    {
                        selectIndex: 0,
                        title: "订单日期",
                        items: ["全部", "一周内", "一个月内", "3个月内", "6个月内"]
                    },
                    {
                        selectIndex: 0,
                        title: "保费金额",
                        items: ["全部", "0-2000元", "2000-5000元", "5000以上"]
                    },
                    {
                        selectIndex: 0,
                        title: "出单来源",
                        items: ["全部", "超级销售APP", "其他"]
                    }
                ]
            };
        },
        methods: {
            clickItem: function (index, idx) {
                console.log(index, idx);
                this.datas[index].selectIndex = idx + 1;
            },
            selectPickerComfirm: function (value) {
                var date = new Date(value);
                var date_value =
                    date.getFullYear() + "/" + (date.getMonth() + 1) + "/" + date.getDate();
                console.log(date_value);

                if (this.isBegin) {
                    this.beginTime = date_value;
                } else {
                    this.endTime = date_value;
                }
                this.showPicker = !this.showPicker;
            },
            cancelSelectPicker: function () {
                this.showPicker = !this.showPicker;
            },
            selectPicker: function (isStart) {
                this.isBegin = isStart;
                this.showPicker = !this.showPicker;
            },
            sureAction: function () {
                this.show = !this.show;
                var orderStyle =
                    this.datas[0].selectIndex > 0
                        ? this.datas[0].items[this.datas[0].selectIndex - 1]
                        : "未选中订单类型";
                var orderTime =
                    this.datas[1].selectIndex > 0
                        ? this.datas[1].items[this.datas[1].selectIndex - 1]
                        : "未选中保费日期";
                var insureAmount =
                    this.datas[2].selectIndex > 0
                        ? this.datas[2].items[this.datas[2].selectIndex - 1]
                        : "未选中保费金额";
                var orderFrom =
                    this.datas[3].selectIndex > 0
                        ? this.datas[3].items[this.datas[3].selectIndex - 1]
                        : "未选中出单来源";
                // console.log(
                //     orderStyle,
                //     orderTime,
                //     insureAmount,
                //     orderFrom,
                //     this.beginTime,
                //     this.endTime,
                //     this.lowestAmount,
                //     this.topestAmount
                // );
                this.$emit("selectSure",{
                    orderStyle:orderStyle,
                    orderTime:orderTime,
                    insureAmount:insureAmount,
                    orderFrom:orderFrom,
                    beginTime:this.beginTime,
                    endTime:this.endTime,
                    lowestAmount:this.lowestAmount,
                    topestAmount:this.topestAmount
                });
            }
        }
    };
</script>
<style scoped>
    * {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    #superPOP {
        margin: 0 15px;
    }
    
    .father {
        width: 100%;
    }
    .father> :first-child {
        text-align: left;
        margin: 25px 0px 0px 5px;
        height: 20px;
        font-size: 14px;
        font-family: PingFangSC-Semibold, PingFang SC;
        font-weight: 600;
        color: rgba(51, 51, 51, 1);
        line-height: 20px;
        width: calc(100% - 5px);
    }

    .father> :last-child {
        margin: 0px 3px 0px 4px;
        text-align: center;
        font-size: 14px;
        font-family: PingFangHK-Regular, PingFangHK;
        font-weight: 400;
        color: rgba(102, 102, 102, 1);
        line-height: 36px;
    }

    .listItem {
        width: 100%;
        /* background:red; */
    }

    .item {
        float: left;
        margin-right: 10px;
        margin-top: 10px;
        width: calc(33.3% - 10px);
        background: rgba(249, 250, 252, 1);
        border-radius: 2px;
    }

    .inputAmount,
    .timeSelector {
        float: left;
        width: calc(66.7% - 10px);
        /* background: yellow; */
    }

    .begin,
    .end,
    .separate {
        margin-top: 10px;
        margin-right: 2px;
        float: left;
        font-size: 14px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(190, 190, 190, 1);
        background: rgba(249, 250, 252, 1);
        border-radius: 2px;
    }

    .separate {
        width: 5px;
        height: 2px;
        margin-top: 27px;
        background: #979797;
    }

    .begin,
    .end {
        width: calc(50% - 5px);
    }

    .beginFee,
    .endFee {
        width: calc(50% - 5px);
    }

    .endFee,
    .end {
        margin-right: 0px;
    }

    input {
        text-align: center;
        height: 36px;
        line-height: 36px;
        border-radius: 0px;
        border-width: 0px;
        overflow: hidden;
        border: 0;
        outline: none;
        font-size: 14px;
    }

    input::-webkit-input-placeholder {
        font-size: 14px;
        text-align: center;
        color: rgba(190, 190, 190, 1);
    }

    #sureBotton {
        margin: 15px 15px 15px 15px;
        width: calc(100% - 30px);
        height: 46px;
        line-height: 46px;
        background: rgba(59, 138, 254, 1);
        border-radius: 23px;
        font-size: 16px;
        font-family: PingFangSC-Regular, PingFang SC;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        text-align: center;
    }
</style>