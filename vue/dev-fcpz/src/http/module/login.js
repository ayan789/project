import { post } from "../axios";

export const login = params => post(process.env.VUE_APP_CSU_URL + "/user/login", params);

//获取配置接口
export const query = params => post(process.env.VUE_APP_CSU_URL + "/config/query", params);
