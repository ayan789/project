import { post } from "../axios";

/**
 * 版本管理
 * @param {*} params
 */
export const manageVersion = params =>
  post((process.env.VUE_APP_CST_URL || "") + "/content/manageVersion", params);
