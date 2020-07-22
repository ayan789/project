/*
 * @Author: King
 * @Page:
 * @Date: 2020-03-21 11:58:25
 * @Last Modified by: King
 * @Last Modified time: 2020-04-14 15:59:18
 * @FilePath: /src/utils/localStorage.js
 */
export default {
  get(key, defaultValue = "") {
    let result = defaultValue;
    try {
      result = localStorage.getItem(key);
      result = JSON.parse(result);
    } catch (error) {
      console.error(error);
    }
    return result || defaultValue;
  },
  set(key, value) {
    if (typeof value === "object") {
      localStorage.setItem(key, JSON.stringify(value));
    } else {
      localStorage.setItem(key, value);
    }
  },
  rm(key) {
    localStorage.removeItem(key);
  }
};
