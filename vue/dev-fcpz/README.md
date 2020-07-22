# vue-sales 项目

## 项目启动安装依赖文件

```
npm install
```

### 启动服务

```
npm run serve
```

### 项目打包

```
npm run build
```

### 项目语法校验

```
npm run lint
```

## 目录规范

```
   project tree
/ - node_modules 依赖文件
/ - public 公共文件
    / - favicon.ico 项目ico图标
    / - static 静态资源文件
    / - index.html 项目打包时会以该文件为母板
/ - src 资源文件夹
    / - assets 资源文件夹，存放图片之类的资源
    / - components 组件文件夹，自己写的组件都放在这个文件夹下
    / - views 我们所写的页面都放在这里
    / - App.vue 应用组件，所有自己写的组件都是在这个组件之上运行
    / - main.js webpack入口文件
    / - router.js 路由文件夹，决定了页面的跳转规则
    / - store.js vuex状态管理文件
    / - utils 工具类
    / - http api管理
/ - package.json 项目描述文件
/ - vue.config.js webpack配置文件
/ - postcss.config.js px转换rem插件
/ - babel.config.js babel配置文件
```

## 配置文件介绍

    area.json   主要省和城市代码、名称
    cardtype.json   证件类型代码、名称
    channelDataDictionary.json   //职业/行业  //国籍//驾驶员车上人员责任险 保额选择//第三者责任保险 保额选择  //证件标识类型//交管车辆类型 //车辆使用性质//车辆使用性质-新核心//车辆种类-新核心//能源类型
    codeMappingConfigNewToOld.json  //新核心代码映射老核心代码配置文件
    dataDictionary.json   //数据字典
    newCoreDataDictionary.json  //新核心数据字典
    product_premium.json        //掌中宝各行业对应保费
    productjson
    questionList.json
    questionListByAudit.json
    tab.json
    tabByAudit.json
    trade.json  //各行各业的代码、名称

## 项目运行依赖

```
    clipboard  剪贴板工具  https://github.com/zenorocha/clipboard.js
    crypto-js  加密解密工具 https://github.com/brix/crypto-js
    html2canvas 生成图片 https://github.com/niklasvh/html2canvas
    jsencrypt  非对称RSA加密工具 https://github.com/travist/jsencrypt
    lrz 在客户端压缩好要上传的图片可以节省带宽更快的发送给后端，特别适合在移动设备上使用。 https://github.com/think2011/localResizeIMG
    qrcodejs2 生成二维码工具 https://www.npmjs.com/package/qrcodejs2
    signature_pad 手写板, 电子签名用 https://github.com/szimek/signature_pad
```

## views 下面的目录

    customer：客户管理
           子模块
           InteractiveRights ： 互动权益
           claimIllegal ：理赔违章
           customerManagement ：客户管理
           followUpremind ：跟进提醒
           birthdayCelebrate:生日祝福

    noncar：非车险
          子模块
          common:公共模块
          healthInsurance：健康险
          propertyInsurance：财产险

    order：车险/非车险订单
          子模块
          common：订单公共组件
          orderList:订单列表
          healthInsurance：健康险
          propertyInsurance：财产险
          vehicleInsurance：车险
    performance：业绩
    renewal：续保
            子模块
            renewalManager：续保管理
            renwalNotice：续保消息
    user：用户中心
    vehicle：车险投保
           子模块
           businessPlan：商业计划书
           insureConfirm：投保确认
           payment：支付

    activities：营销活动
           子模块
           activity：活动
           myBenefits：我的福利
           common:公共组件
           renewalTaskTips：续保任务


## components 下面的组件：

    globalNav.vue:      通用导航组件
    insureMessage.vue:  核保信息弹窗
    orderInput.vue:     备注信息弹窗
    orderSelect.vue:    订单列表筛选弹窗
    paymentCode.vue:    支付获取验证码弹窗 

    paymentSheet.vue:   支付底部选项弹窗
    paymentWechat.vue:  支付使用微信打开弹窗
    whichSelected.vue:  并排按钮选项弹窗