//调用方法，创建壳和JS通信桥接
setupWebViewJavascriptBridge(function (bridge) {
  window.WebViewJavascriptBridge = bridge;

  //注册壳调用方法，通知JS进行操作
  bridge.registerHandler("functionInJs", function (data, responseCallback) {
    if (responseCallback) {
      var responseData = "Javascript Says Right back aka!00-0-";
      responseCallback(responseData);
    }
  });

  bridge.registerHandler("shellGoBack", function () {
    window.history.go(-1);
  });
});

//方法定义
export function setupWebViewJavascriptBridge(callback) {
  var u = navigator.userAgent;
  if (u.indexOf("Android") > -1 || u.indexOf("Linux") > -1) {
    setupAndroidWebViewJavascriptBridge(function (bridge) {
      callback(bridge);
    });
  } else {
    setupiOSWebViewJavascriptBridge(function (bridge) {
      callback(bridge);
    });
  }
}

//Setting ios WebViewJavascriptBridge
function setupiOSWebViewJavascriptBridge(callback) {
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
  setTimeout(function () {
    document.documentElement.removeChild(WVJBIframe);
  }, 0);
}

/**
 * Setting Android WebViewJavascriptBridge
 */
function setupAndroidWebViewJavascriptBridge(callback) {
  if (window.WebViewJavascriptBridge) {
    callback(window.WebViewJavascriptBridge);
  } else {
    document.addEventListener(
      "WebViewJavascriptBridgeReady",
      function () {
        callback(window.WebViewJavascriptBridge);
      },
      false
    );
  }
}
