var u = navigator.userAgent;
if (u.match(/AliApp/i) == "aliapp") {
  //支付宝环境
} else if (u.indexOf("Android") > -1 || u.indexOf("Linux") > -1) {
  (function() {
    if (window.WebViewJavascriptBridge) {
      return;
    }

    var messagingIframe;
    var sendMessageQueue = [];
    var receiveMessageQueue = [];
    var messageHandlers = {};

    var CUSTOM_PROTOCOL_SCHEME = "yy";
    var QUEUE_HAS_MESSAGE = "__QUEUE_MESSAGE__/";

    var responseCallbacks = {};
    var uniqueId = 1;

    function _createQueueReadyIframe(doc) {
      messagingIframe = doc.createElement("iframe");
      messagingIframe.style.display = "none";
      doc.documentElement.appendChild(messagingIframe);
    }

    //set default messageHandler
    function init(messageHandler) {
      if (WebViewJavascriptBridge._messageHandler) {
        throw new Error("WebViewJavascriptBridge.init called twice");
      }
      WebViewJavascriptBridge._messageHandler = messageHandler;
      var receivedMessages = receiveMessageQueue;
      receiveMessageQueue = null;
      for (var i = 0; i < receivedMessages.length; i++) {
        _dispatchMessageFromNative(receivedMessages[i]);
      }
    }

    function send(data, responseCallback) {
      _doSend(
        {
          data: data
        },
        responseCallback
      );
    }

    function registerHandler(handlerName, handler) {
      messageHandlers[handlerName] = handler;
    }

    function callHandler(handlerName, data, responseCallback) {
      _doSend(
        {
          handlerName: handlerName,
          data: data
        },
        responseCallback
      );
    }

    //sendMessage add message, 触发native处理 sendMessage
    function _doSend(message, responseCallback) {
      if (responseCallback) {
        var callbackId = "cb_" + uniqueId++ + "_" + new Date().getTime();
        responseCallbacks[callbackId] = responseCallback;
        message.callbackId = callbackId;
      }

      sendMessageQueue.push(message);
      messagingIframe.src = CUSTOM_PROTOCOL_SCHEME + "://" + QUEUE_HAS_MESSAGE;
    }

    // 提供给native调用,该函数作用:获取sendMessageQueue返回给native,由于android不能直接获取返回的内容,所以使用url shouldOverrideUrlLoading 的方式返回内容
    function _fetchQueue() {
      var messageQueueString = JSON.stringify(sendMessageQueue);
      sendMessageQueue = [];
      //android can't read directly the return data, so we can reload iframe src to communicate with java
      messagingIframe.src =
        CUSTOM_PROTOCOL_SCHEME +
        "://return/_fetchQueue/" +
        encodeURIComponent(messageQueueString);
    }

    //提供给native使用,
    function _dispatchMessageFromNative(messageJSON) {
      setTimeout(function() {
        var message = JSON.parse(messageJSON);
        var responseCallback;
        //java call finished, now need to call js callback function
        if (message.responseId) {
          responseCallback = responseCallbacks[message.responseId];
          if (!responseCallback) {
            return;
          }
          responseCallback(message.responseData);
          delete responseCallbacks[message.responseId];
        } else {
          //直接发送
          if (message.callbackId) {
            var callbackResponseId = message.callbackId;
            responseCallback = function(responseData) {
              _doSend({
                responseId: callbackResponseId,
                responseData: responseData
              });
            };
          }

          var handler = WebViewJavascriptBridge._messageHandler;
          if (message.handlerName) {
            handler = messageHandlers[message.handlerName];
          }
          //查找指定handler
          try {
            handler(message.data, responseCallback);
          } catch (exception) {
            if (typeof console != "undefined") {
              console.log(
                "WebViewJavascriptBridge: WARNING: javascript handler threw.",
                message,
                exception
              );
            }
          }
        }
      });
    }

    //提供给native调用,receiveMessageQueue 在会在页面加载完后赋值为null,所以
    function _handleMessageFromNative(messageJSON) {
      console.log(messageJSON);
      if (receiveMessageQueue && receiveMessageQueue.length > 0) {
        receiveMessageQueue.push(messageJSON);
      } else {
        _dispatchMessageFromNative(messageJSON);
      }
    }

    var WebViewJavascriptBridge = (window.WebViewJavascriptBridge = {
      init: init,
      send: send,
      registerHandler: registerHandler,
      callHandler: callHandler,
      _fetchQueue: _fetchQueue,
      _handleMessageFromNative: _handleMessageFromNative
    });

    var doc = document;
    _createQueueReadyIframe(doc);
    var readyEvent = doc.createEvent("Events");
    readyEvent.initEvent("WebViewJavascriptBridgeReady");
    readyEvent.bridge = WebViewJavascriptBridge;
    doc.dispatchEvent(readyEvent);
  })();
}
var setupiOSWebViewJavascriptBridge = function(callback) {
  if (window.WebViewJavascriptBridge) {
    return callback(window.WebViewJavascriptBridge);
  }
  if (window.WVJBCallbacks) {
    return window.WVJBCallbacks.push(callback);
  }

  window.WVJBCallbacks = [callback];
  var WVJBIframe = document.createElement("iframe");
  WVJBIframe.style.display = "none";
  WVJBIframe.src = "wvjbscheme://__BRIDGE_LOADED__";
  document.documentElement.appendChild(WVJBIframe);
  setTimeout(function() {
    document.documentElement.removeChild(WVJBIframe);
  }, 0);
};
/**
 * Setting Android WebViewJavascriptBridge
 */
var setupAndroidWebViewJavascriptBridge = function(callback) {
  if (window.WebViewJavascriptBridge) {
    callback(window.WebViewJavascriptBridge);
  } else {
    document.addEventListener(
      "WebViewJavascriptBridgeReady",
      function() {
        callback(window.WebViewJavascriptBridge);
      },
      false
    );
  }
};

var setupWebViewJavascriptBridge = function(callback) {
  var u = window.navigator.userAgent.toLowerCase();
  if (u.indexOf("android") > -1 || u.indexOf("linux") > -1) {
    setupAndroidWebViewJavascriptBridge(function(bridge) {
      callback(bridge);
    });
  } else {
    setupiOSWebViewJavascriptBridge(function(bridge) {
      callback(bridge);
    });
  }
};
//3. 获取数据API getData
var getData = function(key, callBack) {
  //调用方法，创建壳和JS通信桥接
  setupWebViewJavascriptBridge(function(bridge) {
    window.WebViewJavascriptBridge = bridge;
    //console.log("通信桥接成功");

    //注册壳调用方法，通知JS进行操作
    bridge.registerHandler("functionInJs", function(data, responseCallback) {
      if (responseCallback) {
        var responseData = "Javascript Says Right back aka!00-0-";
        responseCallback(responseData);
      }
    });
  });

  if (!window.WebViewJavascriptBridge) {
    //Toast("调用了getData方法，请在壳中调用！");
    return;
  }

  if (!key) {
    //Toast("缺少业务参数，请核对接口文档！");
    return;
  }

  //组装请求数据
  var jsonVal = {
    key: key,
    type: "2"
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
          callBack("");
        }
      }
    }
  );
};

getData("developmentMode", data => {
  console.log("=====developmentMode:" + data);
  if (data) {
    localStorage.setItem("developmentMode", data);
  }
});

getData("userInfo", data => {
  console.log("xxxxxxxuserInfoxxxxxxxx");
  var u = window.navigator.userAgent.toLowerCase();
  if (u.indexOf("android") > -1 || u.indexOf("linux") > -1) {
    if (data && data != "{}") {
      localStorage.setItem("userInfo", data);
    }
  } else {
    var dataParam = JSON.parse(data);
    if (dataParam.resultCode == 1) {
      localStorage.setItem("userInfo", dataParam.responseBody.userInfo);
    } else {
      console.error(dataParam.resultMsg);
    }
  }

  var userStr = localStorage.getItem("userInfo");
  //用户信息赋值
  if (
    userStr != undefined &&
    userStr != null &&
    userStr != {} &&
    userStr != "{}"
  ) {
    var userObj = JSON.parse(userStr);
    if (userObj) {
      localStorage.setItem("userCode", userObj.userCode);
      localStorage.setItem("storeCode", userObj.storeCode);
      localStorage.setItem("comCode", userObj.comCode);
    }
  }
});

getData("ConfigParams", data => {
  console.log("xxxxxxxConfigParamsxxxxxxxx");
  var u = window.navigator.userAgent.toLowerCase();
  if (u.indexOf("android") > -1 || u.indexOf("linux") > -1) {
    if (data && data != "{}") {
      localStorage.setItem("ConfigParams", data);
    }
  } else {
    var dataParam = JSON.parse(data);
    if (dataParam.resultCode == 1) {
      localStorage.setItem("ConfigParams", dataParam.responseBody.ConfigParams);
    } else {
      console.error(dataParam.resultMsg);
    }
  }
});
