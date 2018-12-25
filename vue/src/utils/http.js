import axios from 'axios'
import store from '@/store'
import globalConfig from '@/assets/data/globalConfig'

let instance = axios.create({
  baseURL: globalConfig.serverUrl,  //请求路径
  timeout: 10000,  //请求超时时长
  withCredentials:true, //跨域
});


//请求拦截器
instance.interceptors.request.use(config => {
  console.log(config);
  //开启loading框
  store.commit('LOADING', {
    isShow: true,
    msg: '正在加载...'
  })
  return config
}, error => {
  showError();
  return Promise.reject({
    isLogicError: false,
    errorMessage: "网络异常,请检查网络"
  })
});


//当请求异常，网络异常,返回异常时候进行提示
function showError() {
  //关闭loading框
  store.commit('LOADING', {
    isShow: false
  })
  //进行http错误提示
  store.commit('TOAST', {
    isShow: true,
    msg: '网络异常,请检查网络'
  })
};




//返回拦截器
instance.interceptors.response.use(response => {
  //关闭loading框
  store.commit('LOADING', {
    isShow: false
  })
  return response
}, error => {
  showError();
  return Promise.reject({
    isLogicError: false,
    errorMessage: "网络异常,请检查网络"
  })
})


export default {
  axios: axios,
  post(service, params) { //post方法
    return instance.post(service, params, {
      headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
      },
      transformRequest: [function (data) { 
        let ret = '' 
        for (let it in data) { 
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&' 
        } 
        return ret 
      }],
    })
  },
  get(service, params) { //get方法
    return instance.get(service, {
      params: params
    })
  }
}