import { Toast } from "vant";
// 1 姓名非空验证
export const handlerName = (key, val) => {
  return new Promise(resolve => {
    if (val == "" || val.length >= 50) {
      console.log(key + "不能为空并且最大50字符");
      resolve(`null`);
    } else {
      resolve(`ok`);
    }
  });
};

// 防抖
export const debounce = (fn, delay = 500) => {
  let timer = null;
  return function() {
    let _this = this;
    let _arguments = arguments;
    timer && clearInterval(timer);
    timer = setTimeout(() => {
      fn.apply(_this, _arguments);
      timer = null;
    }, delay);
  };
};
// 3 手机号验证
export const handlerMobile =  (key, val) => {
  return new Promise(resolve => {
    if (val == "") {
      Toast("手机号不能为空");
      resolve("mobilenull");
    } else {
      // let reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
      // if (!reg.test(val)) {
      if (val.length != 11) {
        // Toast("手机号格式不正确");
        resolve("mobilerules");
      } else {
        resolve("ok");
      }
    }
  });
};
// 4 邮箱验证
export const handlerEmail = (key, val) => {
  return new Promise(resolve => {
    let reg = /^[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([-_.][a-zA-Z0-9]+)*\.[a-z]{2,}$/;
    if (!reg.test(val)) {
      Toast(`${key}` + "格式不正确");
      resolve("emailnull");
    } else {
      resolve("ok");
    }
  });
};
