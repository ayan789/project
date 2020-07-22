//执行这个文件进行功能注册
import "./base/WebViewBridge";
import { Toast } from "vant";

//1.打开新网页API openWebView
export function openWebView(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openWebView方法，请在壳中调用！");
    return;
  }

  //必传参数校验
  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //组装请求数据
  const jsonVal = {
    url: jsonObj.url || "",
    titleBarTag: jsonObj.titleBarTag || "",
    titleBar: jsonObj.titleBar || "",
    sharePlatform: jsonObj.sharePlatform || [0, 1], //0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
    shareType: jsonObj.shareType || "",
    title: jsonObj.title || "",
    imgURL: jsonObj.imgURL || "",
    image: jsonObj.image || "",
    desc: jsonObj.desc || "",
    type: "0",
    shareUrl: jsonObj.shareUrl || "",
  };
  console.log("组装请求数据", JSON.stringify(jsonVal));
  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "openWebView", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//2.保存数据API setData
export function setData(key, value, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了setData方法，请在壳中调用！");
    return;
  }

  if (!key) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //组装请求数据
  const jsonVal = {
    key: key,
    val: value || "",
    type: "1",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "setData", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//3. 获取数据API getData
export function getData(key, callBack) {
  console.log(window.WebViewJavascriptBridge, "3333333333");
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了getData方法，请在壳中调用！");
    if (callBack && typeof callBack === "function") callBack("{}");
    return;
  }

  if (!key) {
    //Toast("缺少业务参数，请核对接口文档！");
    if (callBack && typeof callBack === "function") callBack("{}");
    return;
  }

  //组装请求数据
  const jsonVal = {
    key: key,
    type: "2",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "getData", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//4. 打开首页API openHome
export function openHome(data, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openHome方法，请在壳中调用！");
    return;
  }

  //组装请求数据
  const jsonVal = {
    data: data || "",
    type: "3",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "openHome", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//5. 关闭网页API closeWeb
export function closeWeb(callBack, jsonObj) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了closeWeb方法，请在壳中调用！");
    return;
  }

  //组装请求数据
  const jsonVal = {
    type: "4",
    refreshURLPath: (jsonObj && jsonObj.routerName) || "",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "closeWeb", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//6. 退回到上一级历史网页API back
export function back(callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了back方法，请在壳中调用！");
    return;
  }

  //组装请求数据
  const jsonVal = {
    type: "5",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "back", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//7. 分享（微信、QQ、短信）API share
export function share(jsonObject, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了share方法，请在壳中调用！");
    return;
  }

  //分享平台,分享类型
  if (!jsonObject || !jsonObject.sharePlatform || !jsonObject.shareType) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //组装请求数据
  const jsonVal = {
    sharePlatform: jsonObject.sharePlatform || ["0", "1", "2"], //"0"表示微信好友；"1"表示微信朋友圈；"2"表示QQ；"3"表示短信
    shareType: jsonObject.shareType || "text", //"url" - 链接； "img" - 图片； "text" - 文字
    title: jsonObject.title || "", //分享标题
    imgURL: jsonObject.imgURL || "", //分享图片地址, 与image二选一
    image: jsonObject.image || "", //分享图片（base64编码）, 与imgURL二选一
    url: jsonObject.url || "", //分享链接
    desc: jsonObject.desc || "", //分享描述
    phoneNum: jsonObject.phoneNum || "", //电话号码,当需要短信分享时获取
    type: "6",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "share", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//8. 打开相机相册API setAvatar
export function setAvatar(imgType, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了setAvatar方法，请在壳中调用！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    imgType: imgType || "0", //0:相机/相册(不传默认0)；1：相机；2：相册
    type: "7",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "setAvatar", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//9. 保存图片到手机相册API savePicture
export function savePicture(image, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了savePicture方法，请在壳中调用！");
    return;
  }

  //base64 图片
  if (!image) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    image: image,
    type: "8",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "savePicture", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//10. OCR识别API identifyOCR
export function identifyOCR(jsonObject, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了identifyOCR方法，请在壳中调用！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    identifyType: jsonObject.identifyType,
    type: "9",
    side: jsonObject.side,
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "identifyOCR", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//11. 分享电子保单PDF到微信API shareToWxBySystem
export function shareToWxBySystem(downloadUrl, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了shareToWxBySystem方法，请在壳中调用！");
    return;
  }

  if (!downloadUrl) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    downloadUrl: downloadUrl,
    type: "10",
  };
  console.log("调用分享pdf的参数======" + JSON.stringify(jsonVal));
  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "shareToWxBySystem", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//12. 获取定位API getLocation
export function getLocation(callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了getLocation方法，请在壳中调用！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    type: "11",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "getLocation", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//13. 拨打电话API getPhoneCall
export function getPhoneCall(phoneNo, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了getPhoneCall方法，请在壳中调用！");
    return;
  }

  if (!phoneNo) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    phoneNo: phoneNo,
    type: "12",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "getPhoneCall", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//14.打开登录页面重新登录 API openLoginView
export function openLoginView(callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openLoginView方法，请在壳中调用！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    type: "13",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "openLoginView", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//15.仅打开微信 API openWeChat
export function openWeChat(callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openWeChat，请在壳中调用！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    type: "14",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "openWeChat", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//16.保存生日祝福图片，并分享 API birthdayWishes
//参数传递一个jsonObj对象
export function birthdayWishes(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了birthdayWishes方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    imageURL: jsonObj.imageURL || "", //图片链接
    blessInfo: jsonObj.blessInfo || "", //被祝福人
    content: jsonObj.content || "", //祝福内容
    contactPer: jsonObj.contactPer || "", //联系人
    contactPhone: jsonObj.contactPhone || "", //联系电话
    type: "15",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "birthdayWishes", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//17.在特定页面显示或隐藏水印 API showOrCloseWatermarket
//state  true 或者false
export function showOrCloseWatermarket(state, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了showOrCloseWatermarket方法，请在壳中调用！");
    return;
  }

  let isOpenOrClose = "0";

  if (state) {
    isOpenOrClose = "1";
  }

  //封装默认固定参数
  const jsonVal = {
    isOpenOrClose,
    type: "17",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "showOrCloseWatermarket", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//18.分享海报 API sharePoster
//参数传递一个jsonObj对象
export function sharePoster(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了sharePoster方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    posterType: jsonObj.posterType || "", //海报类型 “0”表示文字和二维码，“1”表示仅有二维码（居中）
    name: jsonObj.name || "", //仅在 posterType 为 0 时有值
    phone: jsonObj.phone || "", //仅在 posterType 为 0 时有值
    qrcodeURL: jsonObj.qrcodeURL || "", //二维码链接（原生转化成二维码图片）
    sharePlatform: jsonObj.sharePlatform || "", //"0"表示微信好友；"1"表示微信朋友圈；”99“表示保存图片
    backImage: jsonObj.backImage || "", //背景图
    shareType: jsonObj.shareType || "", //img
    type: "20",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "sharePoster", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//19.发送短信 API sendMessage
//参数传递一个jsonObj对象
export function sendMessage(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了sendMessage方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    messageDes: jsonObj.messageDes || "", //短信模板
    phoneNo: jsonObj.phoneNo || "", //接收人手机号
    type: "18",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "sendMessage", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//20.打开原生特定页面 API openNativeWeb
//参数传递一个jsonObj对象
export function openNativeWeb(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openNativeWeb方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    route: jsonObj.route || "message", //默认跳转消息中心
    type: "19",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "openNativeWeb", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//21.打开原生特定页面 API shareInvite
//参数传递一个jsonObj对象
export function shareInvite(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openNativeWeb方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    qrcodeURL: jsonObj.qrcodeURL || "",
    qrcodeHeaderImage: jsonObj.qrcodeHeaderImage || "",
    rank: jsonObj.rank,
    name: jsonObj.name,
    backImage: jsonObj.backImage,
    sharePlatform: jsonObj.sharePlatform,
    shareType: jsonObj.shareType,
    type: "21",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "shareInvite", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

// 22.分享带app下载二维码的海报
export function sharePosterAndqrcode(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openNativeWeb方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    qrcodeURL: jsonObj.qrcodeURL || "",
    qrcodeHeaderImage: jsonObj.qrcodeHeaderImage || "",
    backImage: jsonObj.backImage,
    sharePlatform: jsonObj.sharePlatform,
    shareType: jsonObj.shareType,
    type: "22",
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "sharePosterAndqrcode", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}

//23原生调用 获取客户列表的 JS 方法;
export function rejectMyCustomerList(callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了savePicture方法，请在壳中调用！");
    return;
  }

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.registerHandler(
    "getMyCustomerList", //壳注册的方法名字，与壳里面保持一致
    function() {
      console.log("刷新开始-----");
      callBack();
      console.log("刷新结束-----");
    }
  );
}
//24 分享长截图
export function shareLongShot(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openNativeWeb方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    sharePlatform: jsonObj.sharePlatform.length ? jsonObj.sharePlatform : [],
    shareType: jsonObj.shareType || "",
    type: "24",
  };
  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "shareLongShot", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}
//25.发送短信 API sendMessage
//参数传递一个jsonObj对象
export function openNativePosterShare(jsonObj, callBack) {
  if (!window.WebViewJavascriptBridge) {
    console.log("调用了openNativePosterShare方法，请在壳中调用！");
    return;
  }

  if (!jsonObj) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //封装默认固定参数
  const jsonVal = {
    proImg: jsonObj.proImg || "",
    proTitle: jsonObj.proTitle || "",
    proDesc: jsonObj.proDesc || "",
    name: jsonObj.name || "",
    phone: jsonObj.phone || "",
    qrcodeURL: jsonObj.qrcodeURL || ""
  };

  //调用壳注册上来的方法
  window.WebViewJavascriptBridge.callHandler(
    "openNativePosterShare", //壳注册的方法名字，与壳里面保持一致
    jsonVal, //业务参数，调用壳方法传递的参数，根据接口规范传值
    function(responseData) {
      //调用壳方法返回数据回调函数
      //如果有返回值，把值转换为JSON对象
      if (callBack && typeof callBack === "function") {
        if (responseData) {
          callBack(responseData);
        } else {
          //如果返回为空，返回空字符串
          callBack("{}");
        }
      }
    }
  );
}
