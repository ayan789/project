/*
 * @Author:
 * @since: 2020-02-25 11:07:41
 * @Last Modified by: King
 * @Last Modified time: 2020-04-09 13:46:08
 * @FilePath: /vue.config.js
 */
const path = require("path");

const os = require("os");

function checkip(form) {
  var pattern = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
  let flag_ip = pattern.test(form.ipv.value);
  if (!flag_ip) {
    alert("错误的ip!");

    return false;
  }
}

function getNetworkIp() {
  let needHost = ""; // 打开的host
  try {
    // 获得网络接口列表
    let network = os.networkInterfaces();
    for (let dev in network) {
      let iface = network[dev];
      for (let i = 0; i < iface.length; i++) {
        let alias = iface[i];
        if (
          alias.family === "IPv4" &&
          alias.address !== "127.0.0.1" &&
          !alias.internal
        ) {
          console.log("alias.address=======" + alias.address);
          if (checkip(alias.address)) {
            // needHost = "http://mobilesales-supsale-test.caas.ccic-test.com.cn"; //sit 内网
            needHost = "http://mobilesales-supsale.caas.ccic-net.com.cn"; 
            // needHost = "http://mobilesales-supsale-uat.caas.ccic-test.com.cn"; //uat 内网
            // needHost = "https://msupsale-test.ccic-net.com.cn"; //sit 外网
          }
        }
      }
    }
  } catch (e) {
    // needHost = "http://mobilesales-supsale-test.caas.ccic-test.com.cn"; //sit 内网
    needHost = "http://mobilesales-supsale.caas.ccic-net.com.cn"; //uat 内网
    // needHost = "https://msupsale-test.ccic-net.com.cn";//sit 外网
  }
  console.log("needHost=======" + needHost);
  return needHost;
}

function resolve(dir) {
  return path.join(__dirname, dir);
}

module.exports = {
  /* 部署生产环境和开发环境下的URL：可对当前环境进行区分
    // publicPath:'/',
    /* 输出文件目录：在npm run build时，生成文件的目录名称 */
  publicPath: process.env.NODE_ENV === "production" ? "./" : "/",
  outputDir: "mobilesales",
  /* 放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录 */
  assetsDir: "assets",
  /* 是否在构建生产包时生成 sourceMap 文件，false将提高构建速度 */
  productionSourceMap: false,
  /* 默认情况下，生成的静态资源在它们的文件名中包含了 hash 以便更好的控制缓存，你可以通过将这个选项设为 false 来关闭文件名哈希。(false的时候就是让原来的文件名不改变) */
  filenameHashing: false,
  /* 代码保存时进行eslint检测 */
  lintOnSave: false,
  /* 别名*/
  chainWebpack: config => {
    //修改文件引入自定义路径
    config.resolve.alias
      .set("@utils", resolve("src/utils"))
      .set("@src", resolve("src"));
  },
  devServer: {
    // /* 自动打开浏览器 */
    // open: false,
    // /* 设置为0.0.0.0则所有的地址均能访问 */
    host: "0.0.0.0",
    port: 8066,
    // https: false,
    // hotOnly: false,
    /* 使用代理 */
    proxy: {
      "/api": {
        /* 目标代理服务器地址 */
        //target: "http://mobilesales-supsale.caas.ccic-net.com.cn",
        target: "http://10.100.124.66:8085",
        // target: "http://10.100.125.221:8085",
        // target: "http://10.100.172.11:9999", //测试
        // target: "http://10.100.173.19:8081", //报价 高
        // target: "http://10.100.125.166:8081", //代理人 渠道 王
        // target: 'https://msupsale-uat.ccic-net.com.cn', //报价 周
        // target: 'http://mobilesales-supsale-test.caas.ccic-test.com.cn', //报价 周
        // target: "https://msupsale-test.ccic-net.com.cn", //报价 周
        // target: getNetworkIp() + "/#", // UAT and SIT
        /* 允许跨域 */
        changeOrigin: true,
        // ws: true,
        pathRewrite: {
          //"^/api": "/api" // 测试
          '^/api': '' //  开发
        }
      }
    }
    // proxy: {
    //   '/api': {
    //     /* 目标代理服务器地址 */
    //     // target: "http://10.100.172.11:9999",
    //     // target: "http://10.100.125.96:8081",
    //     // target: "http://10.100.125.166:8081", //代理人 渠道 王
    //     target: 'https://msupsale-test.ccic-net.com.cn', //报价 周
    //     /* 允许跨域 */
    //     changeOrigin: true,
    //     // ws: true,
    //     pathRewrite: {
    //       '^/api': '/api'
    //     }
    //   }
    // }
  }
};
//userInfo {"userCode":"8000506130","userName":"武慧","company":"34011902","comCName":"宿州中心支公司综合部","makeCompany":"34011902","makeCompanyCName":"宿州中心支公司综合部","mobile":"18305573672","valid":"1","password":"","comRole":"0","token":"AB0C903D6E361D722DF197130AEEC860","storeCode":"GWznde8FEh3Fq2LdxFCK","handlerCode":"8000506130","handlerName":"武慧","comCode":"34011902","comName":"宿州中心支公司综合部","channelCode":"C0000023","isSaleMan":"0","recommendCode":"https://test.95590.cn/vas-wechat-web/weChatConn/beforLoginRedict?appid=wxbb0debc7b58ef133&invitationCode=1%7C8000506130%7C%E6%AD%A6%E6%85%A7","homePermissionInfo":"CXTB,FCTB,XBGZ,YWXC,XBTX,CXCP,RMHD","teamcode":"","comLevel":"6","branchCode":"34010000","branchName":"安徽分公司","userPhone":"18221024989","isTeamLeader":"0","entrydate":"2015-02-25 00:00:00"}