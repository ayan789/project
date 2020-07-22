import CryptoJS from "crypto-js";
import $router from "../../src/router/index";
import * as native from "./JSBridge/index";
import { Toast } from "vant";
import {
  hyIdCardList,
  bwIdCardList,
  shebaoList,
  taxpayerTypeList,
  invoiceTypeList,
  vehicleModelCodeList,
  customerTypeList,
  hyIdCardCusList,
  bwRelateInsured,
  cardType_person,
  cardType_business,
  formIdentifyType
} from "@src/utils/dataDictionary.js";

//十六位十六进制数作为秘钥
let key = "9CF181E2CD72F8E6"; //CryptoJS.enc.Utf8.parse();
//十六位十六进制数作为秘钥偏移量
let iv = CryptoJS.enc.Utf8.parse("6666666666666666");

/** Date 格式化函数 */
Date.prototype.format = function(fmt = "yyyy-MM-dd HH:mm:ss") {
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "H+": this.getHours(), //小时
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    S: this.getMilliseconds(), //毫秒
  };
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(
      RegExp.$1,
      (this.getFullYear() + "").substr(4 - RegExp.$1.length)
    );
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")").test(fmt)) {
      fmt = fmt.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)
      );
    }
  }
  return fmt;
};

Date.prototype.getYearDate = function() {
  let y = this.getFullYear(), //年份
    s = 0, //总天数
    d; //临时变量
  for (let i = 1; i < 13; i++) {
    d = new Date(y, i, 0); //获取某一个月的天数
    s += d.getDate();
  }
  return s;
};
export default {
  //aes加密
  // Encrypt: function(word, options) {
  //   let tkey = key
  //   if (options && typeof options == 'object' && Object.keys(options).length) {
  //     tkey = options.hasOwnProperty('key') ? options.key : tkey
  //   }
  //   let srcs = CryptoJS.enc.Utf8.parse(word)
  //   let encrypted = CryptoJS.AES.encrypt(srcs, CryptoJS.enc.Utf8.parse(tkey), {
  //     mode: CryptoJS.mode.ECB,
  //     padding: CryptoJS.pad.Pkcs7,
  //   })
  //   console.log('加密\n', word, '\n得到: \n', encrypted.toString())
  //   return encrypted.toString()
  // },
  //aes解密
  // Decrypt: function(word, options) {
  //   let tkey = key
  //   if (options && typeof options == 'object' && Object.keys(options).length) {
  //     tkey = options.hasOwnProperty('key') ? options.key : tkey
  //   }
  //   let decrypt = CryptoJS.AES.decrypt(word, CryptoJS.enc.Utf8.parse(tkey), {
  //     mode: CryptoJS.mode.ECB,
  //     padding: CryptoJS.pad.Pkcs7,
  //   })
  //   let decryptedStr = decrypt.toString(CryptoJS.enc.Utf8)
  //   console.log('解密\n', word, '\n得到: \n', decryptedStr)
  //   return decryptedStr.toString()
  // },
  Encrypt(content) {
    var sKey = CryptoJS.enc.Utf8.parse(key);
    var sContent = CryptoJS.enc.Utf8.parse(content);
    var encrypted = CryptoJS.AES.encrypt(sContent, sKey, {
      iv: iv,
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    return encrypted.toString();
  },
  Decrypt(content) {
    var sKey = CryptoJS.enc.Utf8.parse(key);
    var decrypt = CryptoJS.AES.decrypt(content, sKey, {
      iv: iv,
      mode: CryptoJS.mode.ECB,
      padding: CryptoJS.pad.Pkcs7,
    });
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
  },
  //base64加密
  EnBase64: function(params) {
    return CryptoJS.enc.Base64.stringify(params);
  },
  //base64解密
  DeBase64: function(params) {
    return CryptoJS.enc.Base64.parse(params);
  },
  getCookie(cookieVal) {
    /**
     * 获取cookie的值
     * cookieVal 参数是cookie中的key返回value
     * @type {string}
     */
    var currentCookie = "";
    document.cookie.split(";").forEach((item) => {
      if (this.trim(item.split("=")[0]) === cookieVal) {
        currentCookie = item.split("=")[1] || "";
      }
    });
    return currentCookie;
  },
  getUrlKey(name) {
    /*
     * 获取 URL 后面拼接的参数数据
     *
     * 调用方式
     * let token = YHUtility.getUrlKey('token');
     *
     * 通过路由的方式（拓展）
     * 地址：http://localhost:3333/#/index?id=001
     * //console.log(this.$route.query.id)
     * 结果：001
     * */
    return (
      decodeURIComponent(
        (new RegExp("[?|&]" + name + "=" + "([^&;]+?)(&|#|;|$)").exec(
          location.href
        ) || [, ""])[1].replace(/\+/g, "%20")
      ) || null
    );
  },
  getTimeStamp(time) {
    /*
     * 获取时间戳
     * @param time
     * @returns {number}
     * */
    return new Date(time.replace(/-/g, "/")).getTime();
  },
  removeEmpty(object) {
    if (!object) return {};
    if (Array.isArray(object)) return {};
    if (typeof object !== "object") return {};
    let result = {
      ...object,
    };
    for (const key in result) {
      if (result.hasOwnProperty(key)) {
        const value = result[key];
        if (typeof value === "number") {
        } else if (!value) {
          delete result[key];
        }
      }
    }
    return result;
  },
  /*获取光标位置*/
  getCursorPos(el) {
    var CaretPos = 0;
    // IE Support
    if (document.selection) {
      el.focus(); //获取光标位置函数
      var Sel = document.selection.createRange();
      Sel.moveStart("character", -el.value.length);
      CaretPos = Sel.text.length;
    }
    // Firefox/Safari/Chrome/Opera support
    else if (el.selectionStart || el.selectionStart == "0")
      CaretPos = el.selectionEnd;
    return CaretPos;
  },
  /* 定位光标 */
  setCursorPos(el, pos) {
    if (el.setSelectionRange) {
      //Firefox/Safari/Chrome/Opera
      el.focus(); //
      el.setSelectionRange(pos, pos);
    } else if (el.createTextRange) {
      // IE
      var range = el.createTextRange();
      range.collapse(true);
      range.moveEnd("character", pos);
      range.moveStart("character", pos);
      range.select();
    }
  },
  findText(list, value) {
    if (value && Array.isArray(list)) {
      let item = list.find((i) => i.value == value);
      return item ? item.text : "请选择";
    } else {
      return "请选择";
    }
  },
};

/**
 * 验证车牌号,新能源车牌有8位
 * @param { string } value
 */
export const isLicensePlateNumber = (carNo) => {
  return /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{6}$/.test(
    carNo.trim().toUpperCase()
  );
};
/**
 * 续保车牌号校验 （新能源+非新能源）
 * @param { string } value
 */
export const isRenwalLicensePlateNumber = (value) =>
  /^(?:[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领 A-Z]{1}[A-HJ-NP-Z]{1}(?:(?:[0-9]{5}[DF])|(?:[DF](?:[A-HJ-NP-Z0-9])[0-9]{4})))|(?:[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领 A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9 挂学警港澳]{1})$/g.test(
    value
  );

//
export const licenseNo = (carNo) => {
  if (isNull(carNo)) {
    return false;
  }
  return /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/.test(
    carNo.trim()
  );
};
/**
 * 验证车架号
 * @param { string } value
 */
export const vinCheck = (vin) => {
  if (/^[0-9a-zA-Z]{17}$/.test(vin) && new Set(vin.split("")).size > 1) {
    return true;
  } else {
    return false;
  }
};
/**
 * 验证姓名
 * @param { string } value
 */
export const isName = (value) =>
  /^(?:[\u4e00-\u9fa5·]{2,16})$/g.test(value) ||
  /(^[a-zA-Z]{1}[a-zA-Z\s]{0,20}[a-zA-Z]{1}$)/g.test(value);

/**
 * 验证单号
 * @param { string } value
 */
export const isPolicyNo = (value) =>
  /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{22,24}$/g.test(value);

//判定是否为空
export function isNull(value) {
  return (
    typeof value == "undefined" ||
    value == "" ||
    value == null ||
    value == undefined ||
    value == "null" ||
    // value == "{}" ||
    JSON.stringify(value) == "{}"
  );
}

//验证手机号
export function mobilePhone(phone) {
  if (isNull(phone)) {
    return false;
  }
  return /^(?:(?:13[0-9]{1})|(?:15[0-9]{1})|(?:18[0-9]{1})|(?:14[0-9]{1})|(?:17[0-9]{1}))\d{8}$/.test(
    phone
  );
}

//验证邮箱地址
export function email(email) {
  if (isNull(email)) {
    return false;
  }
  return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(
    email
  );
}

//验证邮箱地址
export function emailCheck(email) {
  if (isNull(email)) {
    return false;
  }
  return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(
    email
  );
}

// 验证身份证/港澳台
export function checkIdcard(idcard) {
  if (isNull(idcard)) {
    return false;
  }
  idcard = idcard.toUpperCase();
  var Errors = [
    "验证通过",
    "证件号码位数不对",
    "证件号码出生日期超出范围或含有非法字符",
    "证件号码校验错误",
    "证件地区非法",
  ];
  var area = {
    11: "北京",
    12: "天津",
    13: "河北",
    14: "山西",
    15: "内蒙古",
    21: "辽宁",
    22: "吉林",
    23: "黑龙江",
    31: "上海",
    32: "江苏",
    33: "浙江",
    34: "安徽",
    35: "福建",
    36: "江西",
    37: "山东",
    41: "河南",
    42: "湖北",
    43: "湖南",
    44: "广东",
    45: "广西",
    46: "海南",
    50: "重庆",
    51: "四川",
    52: "贵州",
    53: "云南",
    54: "西藏",
    61: "陕西",
    62: "甘肃",
    63: "青海",
    64: "宁夏",
    65: "新疆",
    71: "台湾",
    81: "香港",
    82: "澳门",
    91: "国外",
  };
  var Y, JYM;
  var S, M;
  var idcard_array = [];
  idcard_array = idcard.split("");
  var ereg, eregNow;
  if (area[parseInt(idcard.substr(0, 2))] == null) return Errors[4];
  switch (idcard.length) {
    case 15:
      if (
        (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 ||
        ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 &&
          (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)
      ) {
        ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/; // 测试出生日期的合法性
      } else {
        ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/; // 测试出生日期的合法性
      }
      if (ereg.test(idcard)) {
        return Errors[0];
      } else {
        return Errors[2];
      }
    case 18:
      if (
        parseInt(idcard.substr(6, 4)) % 4 == 0 ||
        (parseInt(idcard.substr(6, 4)) % 100 == 0 &&
          parseInt(idcard.substr(6, 4)) % 4 == 0)
      ) {
        ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; // 闰年出生日期的合法性正则表达式
        eregNow = /^[1-9][0-9]{5}20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; // 闰年出生日期的合法性正则表达式
      } else {
        ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; // 平年出生日期的合法性正则表达式
        eregNow = /^[1-9][0-9]{5}20[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/; // 平年出生日期的合法性正则表达式
      }
      if (ereg.test(idcard) || eregNow.test(idcard)) {
        S =
          (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 +
          (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 +
          (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 +
          (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 +
          (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 +
          (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 +
          (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 +
          parseInt(idcard_array[7]) * 1 +
          parseInt(idcard_array[8]) * 6 +
          parseInt(idcard_array[9]) * 3;
        Y = S % 11;
        M = "F";
        JYM = "10X98765432";
        M = JYM.substr(Y, 1);
        if (M == idcard_array[17]) return Errors[0];
        else return Errors[3];
      } else return Errors[2];

    default:
      return Errors[1];
  }
}

//验证护照号码
export function checkPassport(code) {
  if (!code || !/^((1[45]\d{7})|(G\d{8})|(P\d{7})|(S\d{7,8}))?$/.test(code)) {
    return false;
  } else {
    return true;
  }
}

/**
 *得到地址栏参数
 *@param names 参数名称
 *@param cn 是否中文
 *@returns string
 **/
export function GetRequest(names, cn) {
  var urls = window.location.href;
  if (urls) {
    if (urls.indexOf("?") > -1) {
      urls = urls.substring(urls.indexOf("?") + 1);
    } else {
      return "";
    }
  } else {
    urls = window.location.search.substr(1);
  }

  var reg = new RegExp("(^|&)" + names + "=([^&]*)(&|$)", "i");
  var r = urls.match(reg);
  if (r && r[2]) {
    var ms = r[2].match(/(\<)|(\>)|(%3C)|(%3E)/g);
    if (ms && ms.length >= 4) {
      //如果检测到有2对及以上开始和结束尖括号
      r[2] = r[2].replace(/(\<)|(%3C)/g, "");
    }
    if (cn) {
      return r[2];
    }
    return unescape(r[2]);
  }
  return "";
}

//全局返回
export function globalBack() {
  if (GetRequest("source", true)) {
    Toast("原生返回");
    native.closeWeb();
  } else {
    $router.back();
    // Toast("H6返回");
  }
}

//全局获取设置缓存
export function setLocalStorageData() {
  console.log("###########");
  native.getData("userInfo", (data) => {
    // console.log("车险页面获取到userinfo的数据----------" + data);
    var u = window.navigator.userAgent.toLowerCase();
    if (u.indexOf("android") > -1 || u.indexOf("linux") > -1) {
      if (data && data != "{}") {
        localStorage.setItem("userInfo", data);
      }
    } else {
      let dataParam = JSON.parse(data);
      if (dataParam.resultCode == 1) {
        localStorage.setItem("userInfo", dataParam.responseBody.userInfo);
      } else {
        console.error(dataParam.resultMsg);
      }
    }
    var userStr = localStorage.getItem("userInfo");
    //用户信息赋值
    if (
      userStr != undefined &&
      userStr != null &&
      userStr != {} &&
      userStr != "{}"
    ) {
      var userObj = JSON.parse(userStr);
      if (userObj) {
        localStorage.setItem("userCode", userObj.userCode);
        localStorage.setItem("storeCode", userObj.storeCode);
        localStorage.setItem("comCode", userObj.comCode);
      }
    }
  });
  native.getData("ConfigParams", (data) => {
    // console.log("获取到ConfigParams----------" + data);
    var u = window.navigator.userAgent.toLowerCase();
    if (u.indexOf("android") > -1 || u.indexOf("linux") > -1) {
      localStorage.setItem("ConfigParams", data);
    } else {
      let dataParam = JSON.parse(data);
      if (dataParam.resultCode == 1) {
        localStorage.setItem(
          "ConfigParams",
          dataParam.responseBody.ConfigParams
        );
      } else {
        console.error(dataParam.resultMsg);
      }
    }
  });
}

export function beforeActive() {
  return new Promise((resolve, reject) => {
    let userInfo = localStorage.getItem("userInfo");
    let userCode = "";
    if (userInfo) {
      userInfo = JSON.parse(userInfo);
      userCode = userInfo.userCode;
    }
    native.getData("userInfo", (data) => {
      console.log("车险页面获取到userinfo的数据----------" + data);
      var u = window.navigator.userAgent.toLowerCase();
      try {
        let newData = JSON.parse(data);
        let newUserInfo = JSON.parse(newData.responseBody.userInfo);
        if (newUserInfo.userCode == userCode) {
          //用户未变更;
          resolve();
          console.warn("用户未变更");
          return;
        }
      } catch (error) {
        console.warn(error);
      }
      if (u.indexOf("android") > -1 || u.indexOf("linux") > -1) {
        if (data && data != "{}") {
          localStorage.setItem("userInfo", data);
        }
      } else {
        let dataParam = JSON.parse(data);
        if (dataParam.resultCode == 1) {
          localStorage.setItem("userInfo", dataParam.responseBody.userInfo);
        } else {
          console.error(dataParam.resultMsg);
        }
      }
      var userStr = localStorage.getItem("userInfo");
      //用户信息赋值
      if (
        userStr != undefined &&
        userStr != null &&
        userStr != {} &&
        userStr != "{}"
      ) {
        var userObj = JSON.parse(userStr);
        if (userObj) {
          localStorage.setItem("userCode", userObj.userCode);
          localStorage.setItem("storeCode", userObj.storeCode);
          localStorage.setItem("comCode", userObj.comCode);
        }
      }

      native.getData("ConfigParams", (data) => {
        console.log("获取到ConfigParams----------" + data);
        if (u.indexOf("Android") > -1 || u.indexOf("Linux") > -1) {
          localStorage.setItem("ConfigParams", data);
        } else {
          let dataParam = JSON.parse(data);
          if (dataParam.resultCode == 1) {
            localStorage.setItem(
              "ConfigParams",
              dataParam.responseBody.ConfigParams
            );
          } else {
            console.error(dataParam.resultMsg);
          }
        }
        resolve();
      });
    });
  });
}

//获取IP
export function getIP() {
  let url = location.href.split("#");
  let tempurl = url[0] + "#";
  return tempurl;
}

//codeOrName 获取码值或名称
/**
 * hyIdCardList,// 货运无忧个人证件类型
 * bwIdCardList,//百万医疗个人证件类型
 * customerTypeList,//客户类型
 * shebaoList,//社保枚举值
 * taxpayerTypeList,//纳税人证件类型枚举值
 * invoiceTypeList,//发票枚举值
 * vehicleModelCodeList//货运无忧车型枚举值
 * hyIdCardCusList //从客户列表跳转携带的证件类型
 *   bwRelateInsured //百万医疗投被保人关系
 *   cardType_person, //客户列表个人客户证件类型
 cardType_business////客户列表团体客户证件类型
 */
export function getCodeOrValue(table, codeOrName) {
  switch (table) {
    case "hyIdCardList":
      for (let key in hyIdCardList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == hyIdCardList[key].id) {
            return hyIdCardList[key].text;
          }
        } else {
          if (codeOrName == hyIdCardList[key].text) {
            return hyIdCardList[key].id;
          }
        }
      }
      break;
    case "cardType_business":
      for (let key in cardType_business) {
        if (parseInt(codeOrName)) {
          if (codeOrName == cardType_business[key].type) {
            return cardType_business[key].id;
          }
        } else {
          if (codeOrName == cardType_business[key].text) {
            return cardType_business[key].id;
          }
        }
      }
      break;
    case "cardType_person":
      for (let key in cardType_person) {
        if (parseInt(codeOrName)) {
          if (codeOrName == cardType_person[key].type) {
            return cardType_person[key].id;
          }
        } else {
          if (codeOrName == cardType_person[key].text) {
            return cardType_person[key].id;
          }
        }
      }
      break;
    case "hyIdCardCusList":
      for (let key in hyIdCardCusList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == hyIdCardCusList[key].type) {
            return hyIdCardCusList[key].id;
          }
        } else {
          if (codeOrName == hyIdCardCusList[key].text) {
            return hyIdCardCusList[key].id;
          }
        }
      }
      break;

    case "bwIdCardList":
      for (let key in bwIdCardList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == bwIdCardList[key].id) {
            return bwIdCardList[key].text;
          }
        } else {
          if (codeOrName == bwIdCardList[key].text) {
            return bwIdCardList[key].id;
          }
        }
      }
      break;
    case "customerTypeList":
      for (let key in customerTypeList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == customerTypeList[key].id) {
            return customerTypeList[key].text;
          }
        } else {
          if (codeOrName == customerTypeList[key].text) {
            return customerTypeList[key].id;
          }
        }
      }
      break;

    case "shebaoList":
      for (let key in shebaoList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == shebaoList[key].id) {
            return shebaoList[key].text;
          }
        } else {
          if (codeOrName == shebaoList[key].text) {
            return shebaoList[key].id;
          }
        }
      }
      break;
    case "taxpayerTypeList":
      for (let key in taxpayerTypeList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == taxpayerTypeList[key].id) {
            return taxpayerTypeList[key].text;
          }
        } else {
          if (codeOrName == taxpayerTypeList[key].text) {
            return taxpayerTypeList[key].id;
          }
        }
      }
      break;
    case "invoiceTypeList":
      for (let key in invoiceTypeList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == invoiceTypeList[key].id) {
            return invoiceTypeList[key].text;
          }
        } else {
          if (codeOrName == invoiceTypeList[key].text) {
            return invoiceTypeList[key].id;
          }
        }
      }
      break;
    case "vehicleModelCodeList":
      for (let key in vehicleModelCodeList) {
        if (parseInt(codeOrName)) {
          if (codeOrName == vehicleModelCodeList[key].id) {
            return vehicleModelCodeList[key].text;
          }
        } else {
          if (codeOrName == vehicleModelCodeList[key].text) {
            return vehicleModelCodeList[key].id;
          }
        }
      }
      break;
    case "bwRelateInsured":
      for (let key in bwRelateInsured) {
        if (parseInt(codeOrName)) {
          if (codeOrName == bwRelateInsured[key].id) {
            return bwRelateInsured[key].text;
          }
        } else {
          if (codeOrName == bwRelateInsured[key].text) {
            return bwRelateInsured[key].id;
          }
        }
      }
      break;
    case "formIdentifyType":
      for (let key in formIdentifyType) {
        if (parseInt(codeOrName)) {
          if (codeOrName == formIdentifyType[key].id) {
            return formIdentifyType[key].text;
          }
        } else {
          if (codeOrName == formIdentifyType[key].text) {
            return formIdentifyType[key].id;
          }
        }
      }
      break;

    default:
      break;
  }
}

/**
 * 从码表获取对应的值
 * codeTable 获取对应哪一个码表如下
 * hyIdCardList,// 货运无忧个人证件类型
 * bwIdCardList,//百万医疗个人证件类型
 * shebaoList,//社保枚举值
 * taxpayerTypeList,//纳税人证件类型枚举值
 * invoiceTypeList,//发票枚举值
 * vehicleModelCodeList//货运无忧车型枚举值
 * customerTypeList //客户类型
 * hyIdCardCusList, //从客户列表跳转回来携带的证件类型
 * codeOrName 对应传过来的码值或name
 * return 返回对应code或名称
 */

export function getCodeOrName(codeTable, codeOrName) {
  return getCodeOrValue(codeTable, codeOrName);
}

//个人代理人登陆，默认为代理出单，代理人默认为本人，不可修改
export function isAgent() {
  let _u = JSON.parse(localStorage.getItem("userInfo"));
  if (
    _u.userCode.indexOf("agen") == 0 ||
    (_u.agentCode && _u.agentCode.substring(0, 1) == "P")
  ) {
    return true;
  } else {
    return false;
  }
}

//判断是否为微信环境
export function isWeiXin() {
  var ua = window.navigator.userAgent.toLowerCase();
  if (ua.match(/MicroMessenger/i) == "micromessenger") {
    return true; // 是微信端
  } else {
    return false;
  }
}

//判断是否是IOS设备
export function isIOS() {
  const userAgent = window.navigator.userAgent;
  const isiOS = !!userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
  const isiOS2 = userAgent.indexOf("mac") > 0 ? true : false;
  const isiOS3 = userAgent.indexOf("safari") > 0 ? true : false;
  const isiOS4 = userAgent.indexOf("iphone") > 0 ? true : false;

  if (isiOS || isiOS2 || isiOS3 || isiOS4) {
    return true;
  } else {
    return false;
  }
}

//检查出生日期
export function checkBirth(val) {
  var pattern = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
  if (pattern.test(val)) {
    var date = new Date(val);

    var month = val.substring(val.indexOf("-") + 1, val.lastIndexOf("-"));

    return date && date.getMonth() + 1 == parseInt(month);
  }

  return false;
}

//获取年龄
export function getAge(str) {
  var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/);
  if (r == null) return false;

  var d = new Date(r[1], r[3] - 1, r[4]);
  var returnStr = "输入的日期格式错误！";

  if (
    d.getFullYear() == r[1] &&
    d.getMonth() + 1 == r[3] &&
    d.getDate() == r[4]
  ) {
    var date = new Date();
    var yearNow = date.getFullYear();
    var monthNow = date.getMonth() + 1;
    var dayNow = date.getDate();

    var largeMonths = [1, 3, 5, 7, 8, 10, 12], //大月， 用于计算天，只在年月都为零时，天数有效
      lastMonth = monthNow - 1 > 0 ? monthNow - 1 : 12, // 上一个月的月份
      isLeapYear = false, // 是否是闰年
      daysOFMonth = 0; // 当前日期的上一个月多少天

    if ((yearNow % 4 === 0 && yearNow % 100 !== 0) || yearNow % 400 === 0) {
      // 是否闰年， 用于计算天，只在年月都为零时，天数有效
      isLeapYear = true;
    }

    if (largeMonths.indexOf(lastMonth) > -1) {
      daysOFMonth = 31;
    } else if (lastMonth === 2) {
      if (isLeapYear) {
        daysOFMonth = 29;
      } else {
        daysOFMonth = 28;
      }
    } else {
      daysOFMonth = 30;
    }

    var Y = yearNow - parseInt(r[1]);
    var M = monthNow - parseInt(r[3]);
    var D = dayNow - parseInt(r[4]);
    if (D < 0) {
      D = D + daysOFMonth; //借一个月
      M--;
    }
    if (M < 0) {
      // 借一年 12个月
      Y--;
      M = M + 12; //
    }

    if (Y < 0) {
      returnStr = "出生日期有误！";
    } else if (Y === 0) {
      if (M === 0) {
        returnStr = D + "D";
      } else {
        returnStr = M + "M";
      }
      returnStr = 0;
    } else {
      // if (M === 0) {
      //   returnStr = Y + "Y";
      // } else {
      returnStr = Y;
      // }
    }
  }

  return returnStr;
}

//获取证件号对应的日期
export function getNum(idCard) {
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
}

//获取相差的天数  传入日期格式xxxx-xx-xx
export function getDayNum(s1) {
  s1 = new Date(s1.replace(/-/g, "/"));
  let s2 = new Date();
  var days = s2.getTime() - s1.getTime();
  var time = parseInt(days / (1000 * 60 * 60 * 24));
  return time;
}

//获取相差的天数  传入日期格式xxxx-xx-xx
export function getDayNumFutrue(s1, s2) {
  s1 = new Date(s1.replace(/-/g, "/"));
  s2 = new Date(s2.replace(/-/g, "/"));
  var days = s2.getTime() - s1.getTime();
  var time = parseInt(days / (1000 * 60 * 60 * 24)) + 1;
  return time;
}


//时间戳转换为 普通日期格式
export function formatDate(now_) {
  let now = new Date(now_);
  let year = now.getFullYear(); //获取获取当前年份
  let month = now.getMonth() + 1; //获取获取当前月份
  let date = now.getDate(); //获取获取当前日期
  let hour = now.getHours(); //获取时
  let minute = now.getMinutes(); //获取分
  let second = now.getSeconds(); //获取秒
  //时间格式 ：年-月-日
  return (
    year +
    "-" +
    (month < 10 ? "0" + month : month) +
    "-" +
    date +
    " " +
    hour +
    ":" +
    minute +
    ":" +
    second
  );
}

//获取当前模式
export function isDevMode() {
  let curMode = false;

  let mode = localStorage.getItem("developmentMode");
  console.log("=====mode:" + mode);

  if (mode && mode == "1") {
    curMode = true;
  }

  if (native) {
    native.getData("developmentMode", (data) => {
      console.log("=====developmentMode:" + data);
      if (data) {
        localStorage.setItem("developmentMode", data);
      }
    });
  }

  return curMode;
}

/**
 * @method 设置埋点方法
 * @param {String} eventId [埋点事件]
 * @param {String} targetName  [事件名称]
 * @param {Object} paramsObj [自定义事件]
 */
export const trackMap = (eventId, targetName, paramsObj) => {
  try {
    _cl_tracker.track(eventId, targetName, "", {
      ...paramsObj,
      c_source: "大地行app",
      c_contentName: "大地行app",
      c_targetId: "super_market_app",
      c_targetName: "超级销售App",
      c_device_id: "",
      c_device_os: isIOS() ? "IOS" : "Andorid",
      c_device_brand: "",
    });
    console.log("埋点成功");
  } catch (error) {
    console.warn("埋点方法调用失败", error);
  }
};

/**
 * @method 获取跳转影像上传或者签名页面路由
 * @param obj  含有是否上传影像和是否签名标志的对象
 */
export function getToRoute(obj) {
  let routeArray = [];
  if (!isNull(obj.isNeedSign)) {
    if (obj.isNeedSign == "1" && obj.signStatus == "0") {
      routeArray.push("prSign");
    }
  }
  if (obj.notUploadImgList instanceof Array) {
    if (obj.notUploadImgList.length > 0) {
      routeArray.push("prSign");
    }
  }
  return routeArray;
}
