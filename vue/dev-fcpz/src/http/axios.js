/*
 * @Author: King
 * @Page:
 * @Date: 2020-02-24 16:42:36
 * @Last Modified by: chenjiafeng
 * @Last Modified time: 2020-06-16 15:08:29
 * @FilePath: /src/http/axios.js
 */
import axios from "axios";
import { Toast } from "vant";
import Vue from "vue";
import utils from "../utils";
import _ from "lodash";

const service = axios.create();

// 环境的切换
// if (process.env.NODE_ENV == "development") {
//   service.defaults.baseURL = "http://192.168.8.234:8081";
// } else if (process.env.NODE_ENV == "production") {
//   service.defaults.baseURL = "http://192.168.8.234:8081";
// }
service.defaults.baseURL = process.env.VUE_APP_BASE_URL;
// 设置请求超时时间
service.defaults.timeout = 60000;
//跨域是否携带凭证
service.defaults.withCredentials = true;

// 设置post请求头
service.defaults.headers["Content-Type"] = "application/json;charset=UTF-8";
service.defaults.headers.post["X-Access-Token"] = "00";

// 请求拦截
service.interceptors.request.use(
  config => {
    // 在发送请求之前做些什么 验证token之类的
    return config;
  },
  error => {
    // 对请求错误做些什么
    return Promise.error(error);
  }
);

// 响应拦截
service.interceptors.response.use(
  response => {
    // endLoading();
    // 对响应数据做点什么
    return response;
  },
  error => {
    // 对响应错误做点什么
    return Promise.reject(error);
  }
);

// 封装get方法和post方法
let toast = null;
let loadingCount = 0;
// 设置是否加密开关 1:加密  0:不加密
let isEncrypt = 0;
/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function get(url, params, { loading = true } = {}) {
  /**
   * loading 默认开启
   * {ladding = false} 关闭
   */
  loading
    ? Toast.loading({
        duration: 0,
        message: "加载中..."
      })
    : "";
  return new Promise((resolve, reject) => {
    service
      .get(url, {
        params: params
      })
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err.data);
      })
      .finally(() => {
        Toast.clear();
      });
  });
}

const str2utf8 = window.TextEncoder
  ? function(str) {
      var encoder = new TextEncoder("utf8");
      var bytes = encoder.encode(str);
      var result = "";
      for (var i = 0; i < bytes.length; ++i) {
        result += String.fromCharCode(bytes[i]);
      }
      return result;
    }
  : function(str) {
      return eval("'" + encodeURI(str).replace(/%/gm, "\\x") + "'");
    };
/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function post(
  url,
  params,
  { loading = true, vehicle = false, getResponseHeader = false } = {},
  { contentType = "" } = {}
) {
  /**
   * loading 默认开启
   * {ladding = false} 关闭
   */
  loadingCount++;
  if (loading && !toast) {
    toast = Toast.loading({
      duration: 0,
      message: "加载中..."
    });
  }
  return new Promise((resolve, reject) => {
    let config = {};
    try {
      let userInfo = JSON.parse(localStorage.getItem("userInfo"));
      let token = userInfo.token || localStorage.getItem("token");
      let { userCode, comCode } = userInfo;
      userCode = params.userCode || userCode;
      comCode = params.comCode || comCode;
      if (contentType === "") {
        config.headers = {
          "X-Access-Token": token
        };
      } else {
        config.headers = {
          "X-Access-Token": token,
          "Content-Type": contentType
        };
      }
      // config.headers = { "X-Access-Token": token };
      if (vehicle) {
        params = Object.assign({}, params, {
          comCode
        });
      }
      params.userCode = userCode;
      // if(contentType==='multipart/form-data'){
      //   params=qs.stringify(params)
      // }
      // eslint-disable-next-line no-empty
      // eslint-disable-next-line no-empty
    } catch (error) {}
    let eString = "";
    if (url == "/cst/content/manageVersion") {
      isEncrypt = 0;
    }
    if (isEncrypt) {
      eString = utils.Encrypt(JSON.stringify(params));
    } else {
      eString = params;
    }
    service
      .post(url, eString, config)
      .then(res => {
        //解密
        let resdata = res.data.data;
        try {
          if (url == "/cst/content/manageVersion") {
            isEncrypt = 0;
          }
          if (isEncrypt) {
            console.log("解密数据", utils.Decrypt(resdata));
            try {
              resdata = JSON.parse(utils.Decrypt(resdata));
              if (typeof resdata == "object") {
                res.data.data = resdata;
              } else {
                res.data.data = utils.Decrypt(res.data.data);
              }
            } catch (error) {
              res.data.data = utils.Decrypt(res.data.data);
            }
          }
          // eslint-disable-next-line no-empty
        } catch (error) {
        } finally {
          console.groupCollapsed(`%c 接口地址  ${url}`, "background:yellow;");
          console.info(`${url}:接口请求报文 ==>\n`, _.cloneDeep(JSON.stringify(params)));
          console.info(`${url}:接口返回数据 ==>\n`, _.cloneDeep(JSON.stringify(res.data.data) ));
          console.groupEnd();
        }
        if (res.data && res.data.code != "1") {
          let msg = res.data.message ? res.data.message : res.data.msg;
          // Toast(msg);
        }
        if (res.data.code == "10000") {
          const userCode = params.userCode;
          const token = config.headers["X-Access-Token"];

          //token过期或者token与用户不匹配,清空数据
          // localStorage.clear();
          Vue.prototype.$native.getData("LoginAuth", val => {
            const curAuth = `\n当前usercode:${userCode}, 对应的token：${token}`;

            const totalAuth = val + curAuth;
            //记录当前token失效退出数据
            Vue.prototype.$native.setData("LoginAuth", totalAuth);
          });

          Vue.prototype.$native.openHome("", data => {});
        }
        if (
          res.data.hasOwnProperty("message") &&
          !res.data.hasOwnProperty("msg")
        ) {
          res.data.msg = res.data.message;
        }
        /**
         * 获取服务器时间塞进res.data
         */
        getResponseHeader
          ? resolve(
              (res.data = Object.assign(
                {},
                {
                  ...res.data
                },
                {
                  responseDate: res.headers.date
                }
              ))
            )
          : resolve(res.data);

        // resolve(res.data);
      })
      .catch(err => {
        console.group(
          `%c POST %c FAILED %c ${url}`,
          "padding:2px;background:green;color:white;font-size:10px;",
          "padding:2px;color:white;font-size:10px;background:red;",
          ""
        );
        console.log("url", url);
        console.log("params: ", {
          ...params
        });
        console.log("error: ", err);
        console.groupEnd();
        reject(err);
      })
      .finally(() => {
        loadingCount--;
        if (loadingCount <= 0) {
          toast && toast.clear();
          toast = null;
        }
      });
  });
}
