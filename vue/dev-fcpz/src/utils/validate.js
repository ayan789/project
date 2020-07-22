/**
 * Created by yuyang
 */

// 校验对象非空
export function convertNullToEmpty(obj) {
  const isEmpty = val => val == null || !(Object.keys(val) || val).length;
  const isObject = obj => obj === Object(obj);
  const isNull = val => val === null || val === '' || val === undefined;
  let types = [];
  const getEmptyType = (obj) => {
    if (isObject(obj)) {
      if (isEmpty(obj)) {
        types.push(true);
        return;
      }
      if (Array.isArray(obj)) {
        obj.map(item => {
          if (isObject(item)) {
            getEmptyType(item);
          } else {
            types.push(isNull(item));
          }
        });
      } else {
        for (let key in obj) {
          if (typeof obj[key] == 'object') {
            getEmptyType(obj[key]);
          } else {
            types.push(isNull(obj[key]));
          }
        }
      }
    } else {
      types.push(isNull(obj));
    }
  };
  getEmptyType(obj);
  let Resulttype = types.some(type => type === true);
  // //console.log(types)
  return {
    type_check: Resulttype, resultObj: obj
  };
};
