/* ------------------------------------------全局filter------------------------------------------ */
import Vue from 'vue'
import codeData from '@/assets/data/codeData'

let allMap = {};
for (var key in codeData) {
  if (!allMap[key]) allMap[key] = {};
  for (var key_ in codeData[key]) {
    allMap[key][
      codeData[key][key_].code
    ] = codeData[key][key_].desc;
  }
}

/**
 * 全局公共过滤器 使用:{{'0' |commonFilter('relationFilter') }}
 * value 值
 * type 类型 对应码表(codeData)中的参数
 * defaultValue 默认值
 */
Vue.filter('commonFilter', (value, type, defaultValue) => {
  //显示优先级：code对应的值 => 用户定义的默认值 => 无法匹配码表的code
  return allMap[type][value] || defaultValue || value
});
