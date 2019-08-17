import axios from 'axios'

let instance = axios.create({
  baseURL: 'http://localhost:4444/',  //请求路径
  timeout: 10000,  //请求超时时长
  withCredentials:true, //跨域
});

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