<!--
 * @Author: King
 * @Page: 
 * @Date: 2020-05-25 15:54:27
 * @Last Modified by: King
 * @Last Modified time: 2020-06-09 13:51:59
 * @FilePath: /src/views/vehicle/common/carLicenseInput.vue
-->
<template>
  <div class="container">
    <transition name="hehe">
      <div v-show="show" @click.stop="" @touchstart.stop="" @touchend.stop="">
        <!--车牌首字-->
        <div class="first-word-wrap" v-if="showFirst">
          <div class="first-word">
            <div
              class="word"
              v-for="item in allKeyWord.province"
              @click.stop="selectFirstWord(item)"
              :key="item.id"
            >
              <span>{{ item }}</span>
            </div>
            <div class="in-delete" @click.stop="deleteItem">
              <svg
                viewBox="0 0 32 22"
                xmlns="http://www.w3.org/2000/svg"
                style="width: 16px;height: 16px;"
              >
                <path
                  d="M28.016 0A3.991 3.991 0 0132 3.987v14.026c0 2.2-1.787 3.987-3.98 3.987H10.382c-.509 0-.996-.206-1.374-.585L.89 13.09C.33 12.62 0 11.84 0 11.006c0-.86.325-1.62.887-2.08L9.01.585A1.936 1.936 0 0110.383 0zm0 1.947H10.368L2.24 10.28c-.224.226-.312.432-.312.73 0 .287.094.51.312.729l8.128 8.333h17.648a2.041 2.041 0 002.037-2.04V3.987c0-1.127-.915-2.04-2.037-2.04zM23.028 6a.96.96 0 01.678.292.95.95 0 01-.003 1.377l-3.342 3.348 3.326 3.333c.189.188.292.43.292.679 0 .248-.103.49-.292.679a.96.96 0 01-.678.292.959.959 0 01-.677-.292L18.99 12.36l-3.343 3.345a.96.96 0 01-.677.292.96.96 0 01-.678-.292.962.962 0 01-.292-.68c0-.248.104-.49.292-.679l3.342-3.348-3.342-3.348A.963.963 0 0114 6.971c0-.248.104-.49.292-.679A.96.96 0 0114.97 6a.96.96 0 01.677.292l3.358 3.348 3.345-3.348A.96.96 0 0123.028 6z"
                  fill="currentColor"
                ></path>
              </svg>
            </div>
          </div>
          <div class="in-close" @touchstart.stop="close">
            收起
          </div>
        </div>

        <!--车牌余字-->
        <div class="keyboard-wrap" v-else>
          <div class="keyboard">
            <div class="in-alphabet">
              <div
                v-for="(item, index) in allKeyWord.alphabet"
                :key="index"
                @touchstart.stop="clickKeyBoard(item)"
              >
                <span v-if="item == 'I'" style="background:#888">
                  {{ item }}
                </span>
                <span v-else-if="String(item).length <= 1">{{ item }}</span>
                <div v-else-if="item == 'keyboard-down'" class="keyboard-down">
                  <svg
                    viewBox="0 0 30 24"
                    xmlns="http://www.w3.org/2000/svg"
                    style="width: 16px;height: 16px;"
                  >
                    <path
                      d="M25.877 12.843h-1.502c-.188 0-.188 0-.188.19v1.512c0 .188 0 .188.188.188h1.5c.187 0 .187 0 .187-.188v-1.511c0-.19 0-.191-.185-.191zM17.999 10.2c0 .188 0 .188.188.188h1.687c.188 0 .188 0 .188-.188V8.688c0-.187.004-.187-.186-.19h-1.69c-.187 0-.187 0-.187.19V10.2zm2.25-3.967h1.5c.188 0 .188 0 .188-.188v-1.7c0-.19 0-.19-.188-.19h-1.5c-.189 0-.189 0-.189.19v1.7c0 .188 0 .188.19.188zm2.063 4.157h3.563c.187 0 .187 0 .187-.189V4.346c0-.19.004-.19-.185-.19h-1.69c-.187 0-.187 0-.187.188v4.155h-1.688c-.187 0-.187 0-.187.189v1.514c0 .19 0 .19.187.19zM14.812 24l2.812-3.4H12l2.813 3.4zm-9-11.157H4.31c-.188 0-.188 0-.188.19v1.512c0 .188 0 .188.188.188h1.502c.187 0 .187 0 .187-.188v-1.511c0-.19.01-.191-.189-.191zm15.937 0H8.25c-.188 0-.188 0-.188.19v1.512c0 .188 0 .188.188.188h13.5c.188 0 .188 0 .188-.188v-1.511c0-.19 0-.191-.188-.191zm-11.438-2.454h1.5c.188 0 .188 0 .188-.188V8.688c0-.187 0-.187-.188-.189h-1.5c-.187 0-.187 0-.187.189V10.2c0 .188 0 .188.187.188zM27.94 0c.563 0 .917.21 1.313.567.518.466.748.757.748 1.51v14.92c0 .567-.188 1.134-.562 1.512-.376.378-.938.566-1.313.566H2.063c-.563 0-.938-.188-1.313-.566-.562-.378-.75-.945-.75-1.511V2.078C0 1.51.188.944.562.567.938.189 1.5 0 1.875 0zm-.062 2H2v14.92h25.877V2zM5.81 4.157c.19 0 .19 0 .19.189v1.762c-.003.126-.024.126-.188.126H4.249c-.126-.003-.126-.023-.126-.188v-1.7c-.187-.19 0-.19.188-.19zm10.5 2.077h1.503c.187 0 .187 0 .187-.188v-1.7c0-.19 0-.19-.187-.19h-1.502c-.188 0-.188.001-.188.19v1.7c0 .188 0 .188.188.188zM7.875 8.5c.187 0 .187.002.187.189V10.2c0 .188 0 .188-.187.188H4.249c-.126-.002-.126-.023-.126-.188V8.625c.003-.126.024-.126.188-.126zm7.875 0c.19.002.19.002.19.189v1.575c-.003.126-.024.126-.19.126h-1.563c-.126-.002-.126-.023-.126-.188V8.625c.002-.126.023-.126.189-.126zm-6-4.342c.187 0 .187 0 .187.189v1.7c0 .188 0 .188-.187.188H8.187c-.126-.003-.126-.023-.126-.188V4.283c.003-.126.024-.126.188-.126zm3.94 0c.185 0 .372 0 .372.189v1.762c-.002.126-.023.126-.187.126h-1.75C12 6.231 12 6.211 12 6.046v-1.7c0-.19.187-.19.187-.19z"
                      fill="currentColor"
                    ></path>
                  </svg>
                </div>
                <span v-else>{{ item }}</span>
              </div>
              <div class="in-delete" @touchstart.stop="deleteItem">
                <svg
                  viewBox="0 0 32 22"
                  xmlns="http://www.w3.org/2000/svg"
                  style="width: 16px;height: 16px;"
                >
                  <path
                    d="M28.016 0A3.991 3.991 0 0132 3.987v14.026c0 2.2-1.787 3.987-3.98 3.987H10.382c-.509 0-.996-.206-1.374-.585L.89 13.09C.33 12.62 0 11.84 0 11.006c0-.86.325-1.62.887-2.08L9.01.585A1.936 1.936 0 0110.383 0zm0 1.947H10.368L2.24 10.28c-.224.226-.312.432-.312.73 0 .287.094.51.312.729l8.128 8.333h17.648a2.041 2.041 0 002.037-2.04V3.987c0-1.127-.915-2.04-2.037-2.04zM23.028 6a.96.96 0 01.678.292.95.95 0 01-.003 1.377l-3.342 3.348 3.326 3.333c.189.188.292.43.292.679 0 .248-.103.49-.292.679a.96.96 0 01-.678.292.959.959 0 01-.677-.292L18.99 12.36l-3.343 3.345a.96.96 0 01-.677.292.96.96 0 01-.678-.292.962.962 0 01-.292-.68c0-.248.104-.49.292-.679l3.342-3.348-3.342-3.348A.963.963 0 0114 6.971c0-.248.104-.49.292-.679A.96.96 0 0114.97 6a.96.96 0 01.677.292l3.358 3.348 3.345-3.348A.96.96 0 0123.028 6z"
                    fill="currentColor"
                  ></path>
                </svg>
              </div>
            </div>
          </div>
          <div class="in-close" @touchstart.stop="close">
            收起
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>
<script>
import { Overlay } from "vant";
export default {
  components: {
    [Overlay.name]: Overlay
  },
  model: {
    prop: "licenseNo",
    event: "change"
  },
  props: {
    licenseNo: {
      type: String,
      default: ""
    },
    show: {
      type: Boolean,
      default: false
    },
    maxLength: {
      type: Number,
      default: 7
    }
  },
  data() {
    return {
      selectArr: [],
      allKeyWord: {
        province: [
          "京",
          "湘",
          "津",
          "鄂",
          "沪",
          "粤",
          "渝",
          "琼",
          "冀",
          "川",
          "晋",
          "贵",
          "辽",
          "云",
          "吉",
          "陕",
          "黑",
          "甘",
          "苏",
          "青",
          "浙",
          "皖",
          "藏",
          "闽",
          "蒙",
          "赣",
          "桂",
          "鲁",
          "宁",
          "豫",
          "新",
          "军",
          "北",
          "南",
          "广",
          "济",
          "沈",
          "兰",
          "成",
          "海",
          "空",
          "WJ",
          "使",
          "",
          "",
          "",
          ""
        ],
        alphabet: [
          1,
          2,
          3,
          4,
          5,
          6,
          7,
          8,
          9,
          0,
          "Q",
          "W",
          "E",
          "R",
          "T",
          "Y",
          "U",
          "I",
          "O",
          "P",
          "A",
          "S",
          "D",
          "F",
          "G",
          "H",
          "J",
          "K",
          "L",
          "",
          "keyboard-down",
          "Z",
          "X",
          "C",
          "V",
          "B",
          "N",
          "M",
          "车",
          "",
          "领",
          "警",
          "学",
          "挂",
          "使",
          "新",
          "确定"
        ]
      }
    };
  },
  computed: {
    showFirst() {
      return this.selectArr.length == 0;
    }
  },
  watch: {
    show(val) {
      if (val && typeof this.licenseNo == "string") {
        let selectArr = this.licenseNo.split("");
        this.selectArr = selectArr;
      }
    }
  },
  methods: {
    showWrap() {
      this.show = true;
    },
    selectFirstWord(item) {
      if (item === "") return;
      this.addSelect(item);
    },
    clickKeyBoard(item) {
      if (String(item).length == 1) {
        if (item == "I") {
        } else {
          this.addSelect(item);
        }
      } else {
        if (item === "keyboard-down") {
          this.close();
        } else if (item === "确定") {
          this.close();
        }
      }
    },
    addSelect(item) {
      // 点击自定义键盘
      if (this.selectArr.length < this.maxLength) {
        this.selectArr.push(item);
      } else {
        this.close();
      }
      this.$emit("change", this.selectArr.join(""));
    },
    deleteItem() {
      this.selectArr.pop();
      this.$emit("change", this.selectArr.join(""));
    },
    close() {
      console.log("emit close");
      this.$emit("closeCarLicenseInput");
    },
    clickMask() {
      // console.log('clickMask')
      this.close();
    }
  },
  mounted() {
    // this.selectArr = [];
    // let container = document.querySelector(".container");
    // container.addEventListener("click", event => {
    //   if (
    //     // event.target.id !== "carInput" &&
    //     // event.target.nodeName !== "SPAN" &&
    //     // event.target.nodeName !== "DIV" &&
    //     // event.target.nodeName !== "I"
    //     event.target.className == "license-no-input"
    //   ) {
    //     this.close();
    //   }
    // });
  }
};
</script>
<style lang="less" scoped>
.container {
  .in-mask {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100vh;
    background: rgba(0, 0, 0, 0);
  }
  .first-word-wrap {
    // height: 9.4rem;
    background-color: #d2d5db;
    padding: 10px;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    .first-word {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;
      &::after {
        //重要
        width: 41px;
        content: "";
      }
      .word {
        box-sizing: border-box;
        width: 30px;
        height: 30px;
        box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.35);
        text-align: center;
        margin: 5px;
        font-size: 14px;
        span {
          box-sizing: border-box;
          display: flex;
          align-items: center;
          justify-content: center;
          text-align: center;
          width: 100%;
          height: 100%;
          background-color: #fff;
          color: #000;
          // border: 1px solid #fff;
          border-radius: 0.125rem;
        }
      }
    }
  }
  .keyboard-wrap {
    background-color: #d2d5db;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 10px;
    .keyboard {
      display: flex;
      justify-content: space-between;
      align-items: center;
      .in-alphabet {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
        &::after {
          //重要
          width: 174px;
          content: "";
        }
        .keyboard-down {
          text-align: center;
          display: flex;
          align-items: center;
          justify-content: center;
          width: 64px;
          height: 30px;
          background-color: #fff;
          border-radius: 5px;
          margin: 5px 2px;
        }
        span {
          text-align: center;
          display: flex;
          align-items: center;
          justify-content: center;
          width: 30px;
          height: 30px;
          box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.35);
          background-color: #fff;
          border-radius: 5px;
          margin: 5px 2px;
          font-size: 14px;
          &:active {
            background-color: #e4e4e4;
          }
          &.bordernone {
            border: none;
            box-shadow: none;
            background-color: #d2d5db;
            &:active {
              background-color: #d2d5db;
            }
          }
          &.delete {
            background-color: #465266;
          }
          // &:last-child {
          //   flex: 1;
          // }
        }
      }
    }
  }

  .in-close {
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 30px;
    box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.35);
    background-color: #fff;
    border-radius: 5px;
    margin: 5px;
    &:active {
      background-color: #e4e4e4;
    }
    &.bordernone {
      border: none;
      box-shadow: none;
      background-color: #d2d5db;
      &:active {
        background-color: #d2d5db;
      }
    }
    &.delete {
      background-color: #465266;
    }
    &:last-child {
      flex: 1;
    }
  }

  .in-delete {
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 30px;
    height: 30px;
    box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.35);
    background-color: #fff;
    border-radius: 5px;
    margin: 5px;

    &:active {
      background-color: #e4e4e4;
    }
    &.bordernone {
      border: none;
      box-shadow: none;
      background-color: #d2d5db;
      &:active {
        background-color: #d2d5db;
      }
    }
    &.delete {
      background-color: #465266;
    }
  }
}

/*淡入淡出*/
.hehe-enter,
.hehe-leave-to {
  opacity: 0;
}
.hehe-enter-to,
.hehe-leave {
  opacity: 1;
}
.hehe-enter-active,
.hehe-leave-active {
  transition: all 0.5s;
}
</style>
