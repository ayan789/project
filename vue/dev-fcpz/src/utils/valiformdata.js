/**
 * 简易表单验证 在需要验证的输入框的class当中添加formCheck值，代表当前输入框需要进行表单验证。
 * 对于验证的格式可以在class当中添加methods当中已经定义的值， class中的值必须使用空格分开。示例（校验为必填且格式为日期格式）：
 * <input type="text" class="formCheck required date"/>
 */

var methods = {};
//输入0-10之间的数
methods["inputVal"] = function(p) {
  return /^(((\d)(\.(\d)+)?)|10)$/.test(p.value) ? "" : p.msg;
};
//上汽 start
methods["required"] = function(p) {
  return p.value ? "" : p.msg;
};
//姓名 2-100位字符
methods["name"] = function(p) {
  return p.value.length >= 2 && p.value.length <= 200 ? "" : p.msg;
};
//身份证
methods["idcard"] = function(p) {
  return checkIdcard(p.value.trim()) == "验证通过!" || !p.value ? "" : p.msg;
};
//护照  5-30位字符 数字,字母 不含空格 ????
methods["passport"] = function(p) {
  var rtn = p.value.length > 30 || p.value.length < 5;
  if (rtn) return p.msg;

  rtn = /^[0-9a-zA-Z]+$/.test(p.value) ? "" : p.msg; //数字 字母
  if (rtn !== "") return rtn;

  rtn = /^([\s]){1,30}$/.test(p.value); //含有空格
  if (rtn) return p.msg;

  return "";
};
//组织机构代码
/*methods["organization"] = function(p){
			//10位字符，前8位为数字或字母，第9位为“-”，第10位为数字或字母；
			return /^[0-9a-zA-Z]\d{8}[\/-]^[0-9a-zA-Z]\d{1}$/.test(p.value) ? "" : p.msg;
		};*/
//上汽 end

methods["minLength"] = function(p) {
  return p.value.length >= p.params.minLength ? "" : p.msg;
};
methods["maxLength"] = function(p) {
  return p.value.length <= p.params.maxLength ? "" : p.msg;
};
methods["date"] = function(p) {
  return /^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/.test(p.value) ? "" : p.msg;
};
methods["number"] = function(p) {
  return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(p.value) ? "" : p.msg;
};
methods["zmoney2"] = function(p) {
  return /^[1-9][0-9]*$|^[0-9]+\.[0-9]{2}$|^0$/.test(p.value) ? "" : p.msg;
};
methods["email"] = function(p) {
  return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(
    p.value
  ) || !p.value
    ? ""
    : p.msg;
};
methods["mobile"] = function(p) {
  return /^(1)\d{10}$/.test(p.value.trim()) || !p.value ? "" : p.msg;
};
methods["mobile2"] = function(p) {
  return /^1[3|4|5|6|8|7|9][0-9]\d{8}$/.test(p.value.trim()) || !p.value
    ? ""
    : p.msg;
};
methods["emailormobile"] = function(p) {
  return /^(13|14|15|16|17|18|19)\d{9}$|^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/.test(
    p.value
  )
    ? ""
    : p.msg;
};
methods["phoneormobile"] = function(p) {
  return /^\(?(0\d{2,3}-?)?\)?\d{7,8}$|^(13|14|15|16|17|18|19)\d{9}$/.test(
    p.value? p.value.trim() :""
  )
    ? ""
    : p.msg;
};
methods["string"] = function(p) {
  return /^[a-zA-Z\u4E00-\u9FA5]+$/.test(p.value) || !p.value ? "" : p.msg;
};
methods["licenseNo"] = function(p) {
  return /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5,7}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/.test(
    p.value.trim()
  )
    ? ""
    : p.msg;
};
methods["licenseNo2"] = function(p) {
  if (!p.value || typeof p.value != "string") return p.msg;
  if (p.value.length < 6 || p.value.length > 11) return p.msg;
  let firstChar = p.value.charAt(0);
  let secondChar = p.value.charAt(1);
  let valid = p.msg;
  try {
    let comCode = JSON.parse(localStorage.getItem("userInfo")).comCode;
    let areaCode = comCode.substr(0, 4);
    if (/^[\u4E00-\u9FA5]/.test(p.value) || /^[A-Z]{2}/.test(p.value)) {
      valid = "";
    } else if (areaCode == "2301") {
      //黑龙江
      if ((firstChar == "0" || firstChar == "3") && secondChar == "8") {
        valid = "";
      }
    } else if (/使$/.test(p.value)) {
      valid = "";
    } else if (/领$/.test(p.value)) {
      valid = "";
    } else if (secondChar == "领") {
      valid = "";
    }
    return valid;
  } catch (error) {
    return p.msg;
  }
};
// methods["licenseNo"] = function(p) {
//   return /^(?:[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领 A-Z]{1}[A-HJ-NP-Z]{1}(?:(?:[0-9]{5}[DF])|(?:[DF](?:[A-HJ-NP-Z0-9])[0-9]{4})))|(?:[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领 A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9 挂学警港澳]{1})$/g.test(
//     p.value
//   )
//     ? ""
//     : p.msg;
// };
methods["chineseChracter"] = function(p) {
  return /^[\u4e00-\u9fa5]+$/.test(p.value) ? "" : p.msg;
};

methods["address"] = function(p) {
  return /^[0-9a-zA-Z\u4E00-\u9FA5]+$/.test(p.value) || !p.value ? "" : p.msg;
};
methods["postCode"] = function(p) {
  return /^[0-9]\d{5}$/.test(p.value) || !p.value ? "" : p.msg;
};
methods["telephone"] = function(p) {
  return /^\(?(0\d{2,3}-?)?\)?\d{7,8}$/.test(p.value) || !p.value ? "" : p.msg;
};
methods["policyNo"] = function(p) {
  return /^[A-z0-9]{22}$/.test(p.value) ? "" : p.msg;
};
methods["checkPwd"] = function(p) {
  return checkPwd(p.value) == true ? "" : p.msg;
};

methods["loginName"] = function(p) {
  return /^[0-9a-zA-Z\_\-\u4e00-\u9fa5]+$/.test(p.value) || !p.value
    ? ""
    : p.msg;
};
methods["lengthFromTo"] = function(p) {
  var reg = new RegExp("^.{" + p.params.from + "," + p.params.to + "}$");
  return reg.test(p.value) ? "" : p.msg;
};
methods["length"] = function(p) {
  var reg = new RegExp("^.{" + p.msg.length + "}$");
  return reg.test(p.value) ? "" : p.msg.msg;
};
methods["orsChineseChracter"] = function(p) {
  p.value = p.value.replace(/\s/g, "");
  return /^[\u4e00-\u9fa5|\u25cf|\u2022|%b7]+$/.test(p.value) ? "" : p.msg;
};
methods["consistentTo"] = function(p) {
  var to = $(p.params.to);
  return p.value == to[0].value ? "" : p.msg;
};
methods["passwordFormat"] = function(p) {
  //返回空字符串 校验通过
  //返回非空字符串 校验不通过
  var regChinese = /^[^\u4e00-\u9fa5]{0,}$/;
  return regChinese.test(p.value) ? "" : p.msg;
};
/**
 * 车牌号
 */
methods["carNumber"] = function(p) {
  return /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$/.test(
    p.value.trim().toUpperCase()
  )
    ? ""
    : p.msg;
};
/*
 * 车架号
 * */
methods.vin = function(p) {
  if (
    /^[0-9a-zA-Z]{17}$/.test(p.value) &&
    new Set(p.value.split("")).size > 1
  ) {
    return "";
  }
  return p.msg;
};
methods["numberUppLetter"] = function(p) {
  //数字或大写字母
  var reg = /^[A-Z0-9]+$/;
  return reg.test(p.value) ? "" : p.msg;
};
methods["chinese"] = function(p) {
  var min = p.params ? p.params.min || 4 : 4,
    max = p.params ? p.params.max || 30 : 30;
  //一个汉字算三个字符，所以全汉字的个数是2-10个汉字
  var _name = p.value.replace(/[\u4E00-\u9FA5]/g, "xxx");
  var len = _name.length;
  if (len > max || len < min) {
    return p.msg;
  } else {
    return "";
  }
};
methods["complex"] = function(p) {
  var reg = /[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/; //  复杂度检测
  return reg.test(p.value) ? "" : p.msg;
};
methods["sumFromTo"] = function(p) {
  //数值大小的范围检测   (from,to]
  var From = p.params ? p.params.from || 0 : 0,
    To = p.params ? p.params.to || 100 : 100,
    flag_L = p.params ? p.params.flag_L || false : false,
    flag_R = p.params ? p.params.flag_R || false : false,
    num = Number(p.value);
  if (From == "min" && To != "max") {
    //（负无穷，M)
    if (flag_R) {
      return num > To ? p.msg : "";
    } else {
      return num >= To ? p.msg : "";
    }
    // return flag_R?((num>To)? p.msg :""):((num>=To)? p.msg :"");
  } else if (From != "min" && To == "max") {
    //(M,正无穷)
    return flag_L ? (num < From ? p.msg : "") : num <= From ? p.msg : "";
  } else if (From != "min" && To != "max") {
    // (m,n)
    if (flag_L && flag_R) {
      return num < From || num > To ? p.msg : "";
    } else if (flag_L && !flag_R) {
      return num < From || num >= To ? p.msg : "";
    } else if (!flag_L && flag_R) {
      return num <= From || num > To ? p.msg : "";
    } else if (!(flag_L || flag_R)) {
      return num <= From || num >= To ? p.msg : "";
    }
  }
};
methods["positiveNumber"] = function(p) {
  //  正整数检测
  return /^[1-9]\d*$/.test(Number(p.value)) ? "" : p.msg;
};

// 非空数组
methods["array"] = function(p) {
  return Array.isArray(p.value) && p.value.length ? "" : p.msg;
};

methods["companyId"] = function(p) {
  const result1 = /^[0-9A-Z]{8}([0-9]|X)$/.test(p.value);
  const result2 = /[0-9A-z]{18}/.test(p.value);
  return result1 || result2 ? "" : p.msg;
};
// 中介机构代码
methods["agencyOrgCode"] = function(p){
  return /[0-9A-Za-z-]*[-][0-9A-Za-z]{1}$/.test(p.value) ? "" : p.msg;
};

// 验证身份证、港澳台居民居住证
function checkIdcard(idcard) {
  idcard = idcard.toUpperCase();
  var Errors = new Array(
    "验证通过!",
    "证件号码位数不对!",
    "证件号码出生日期超出范围或含有非法字符!",
    "证件号码校验错误!",
    "地区非法!"
  );
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
    81: "香港",
    82: "澳门",
    83: "台湾",
    91: "国外"
  };
  var Y, JYM;
  var S, M;
  var ereg, eregNow;
  var idcard_array = new Array();
  idcard_array = idcard.split("");
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
      if (ereg.test(idcard)) return Errors[0];
      else return Errors[2];
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

// 验证密码
function checkPwd(value) {
  if (value == "") {
    return false;
  }
  var _reg = "^[\\w@\\-\\.]{6,16}$";
  var re = new RegExp(_reg);
  if (!re.test(value)) {
    return false;
  }
  return true;
}

function validate(rules, value) {
  try {
    Object.keys(rules).forEach(rule => {
      const msg = rules[rule];
      let result = methods[rule]({ value, msg });
      //console.log(msg, rule, value, result);
      if (result) {
        if (rule === "required") {
          throw msg + "不能为空";
        } else if (rules.hasOwnProperty("required")) {
          throw rules.required + result;
        }
        throw result;
      }
    });
  } catch (error) {
    console.warn(error);
    if (error.__proto__ === Error.prototype) {
      return { status: false, msg: "" };
    }
    return { status: false, msg: error };
  }
  //console.log();
  return { status: true };
}

export default validate;
