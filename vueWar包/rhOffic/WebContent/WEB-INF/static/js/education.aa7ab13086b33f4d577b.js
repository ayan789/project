webpackJsonp([10],{"3i7g":function(t,a,i){var e=i("v13x");"string"==typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);i("FIqI")("5049c280",e,!0,{})},"5sWq":function(t,a,i){"use strict";function e(t){i("d05H")}var c=i("u3kz"),s=i("t9Cx"),n=i("C7Lr"),o=e,r=n(c.a,s.a,!1,o,"data-v-21f5fc34",null);a.a=r.exports},Cdph:function(t,a,i){var e=i("YGTK");"string"==typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);i("FIqI")("2dbbf648",e,!0,{})},"I/r+":function(t,a,i){a=t.exports=i("UTlt")(!1),a.push([t.i,".cus-header[data-v-21f5fc34]{width:100%;height:84px;background:#fff;position:relative}.cus-header a[data-v-21f5fc34]{color:#343d5c;font-size:14px}.cus-header .logo[data-v-21f5fc34]{width:164px;height:auto;margin:16px 30px 24px 60px;display:inline-block;cursor:pointer}.cus-header .head-bar[data-v-21f5fc34]{width:100%;height:4px;display:block;background:#1fdb84}.cus-header .title[data-v-21f5fc34]{font-size:24px;color:#343d5c;line-height:24px;display:inline-block;vertical-align:top;margin:26px 0 32px}.cus-header .back img[data-v-21f5fc34]{display:inline-block;vertical-align:top;width:15px;height:auto;margin:4px 5px 8px 4px}.cus-header .back[data-v-21f5fc34]{width:24px;height:24px;background:#1fdb84;margin:28px 0;display:inline-block}.cus-header .head-l[data-v-21f5fc34],.cus-header .head-r[data-v-21f5fc34]{display:inline-block;vertical-align:top;width:49%;height:80px}.cus-header .head-r[data-v-21f5fc34]{text-align:right;margin-left:-5px;font-size:0}.cus-header .back-text[data-v-21f5fc34]{font-size:14px;display:inline-block;vertical-align:top;margin:29px 10px 31px 0}",""])},"IOg+":function(t,a,i){"use strict";var e=function(){var t=this,a=t.$createElement;t._self._c;return t._m(0)},c=[function(){var t=this,a=t.$createElement,i=t._self._c||a;return i("div",{staticClass:"footer"},[t._v(" 瑞华健康险保险股份有限公司 | "),i("a",{attrs:{target:"_blank",href:"http://www.miitbeian.gov.cn"}},[t._v("陕ICP18008806号-1")]),t._v(" | ©Copyright©2018瑞华保险版权所有")])}],s={render:e,staticRenderFns:c};a.a=s},PUhB:function(t,a,i){"use strict";var e=i("htMJ"),c=i("5sWq");a.a={name:"education",components:{cusFooter:e.a,cusHeader:c.a},data:function(){return{activeTab:1}},methods:{changeActiveTab:function(t){this.activeTab=t,this.$router.push("educationItem"+t)}},mounted:function(){this.$store.commit("ISHEADER",!1),this.$store.commit("ISFOOTER",!1);var t=window.location.href,a=t.lastIndexOf("educationItem"),i=t.charAt(a+"educationItem".length);i&&(this.activeTab=i),document.title="消费者教育"},beforeRouteLeave:function(t,a,i){this.$store.commit("ISHEADER",!0),this.$store.commit("ISFOOTER",!0),i()},beforeRouteUpdate:function(t,a,i){this.activeTab=t.name.charAt(t.name.length-1),i()}}},"Uk/5":function(t,a,i){"use strict";var e=function(){var t=this,a=t.$createElement,i=t._self._c||a;return i("div",{staticClass:"wrap",style:{minHeight:t.screenHeight+"px"}},[i("cus-header",{attrs:{title:"消费者教育"}}),t._v(" "),i("div",{staticClass:"education"},[i("main",{staticClass:"education-main"},[i("aside",{staticClass:"education-aside"},[i("ul",{staticClass:"aside-wrap"},[i("li",{staticClass:"aside-item",class:{"aside-item-active":1==t.activeTab},on:{click:function(a){t.changeActiveTab(1)}}},[t._v("打击非法集资")]),t._v(" "),i("li",{staticClass:"aside-item",class:{"aside-item-active":2==t.activeTab},on:{click:function(a){t.changeActiveTab(2)}}},[t._v("反保险欺诈")]),t._v(" "),i("li",{staticClass:"aside-item",class:{"aside-item-active":3==t.activeTab},on:{click:function(a){t.changeActiveTab(3)}}},[t._v("反洗钱知识")]),t._v(" "),i("li",{staticClass:"aside-item",class:{"aside-item-active":4==t.activeTab},on:{click:function(a){t.changeActiveTab(4)}}},[t._v("销售误导")]),t._v(" "),i("li",{staticClass:"aside-item",class:{"aside-item-active":5==t.activeTab},on:{click:function(a){t.changeActiveTab(5)}}},[t._v("2018反洗钱宣传月")]),t._v(" "),i("li",{staticClass:"aside-item",class:{"aside-item-active":6==t.activeTab},on:{click:function(a){t.changeActiveTab(6)}}},[t._v("金融知识普及月宣传")])])]),t._v(" "),i("article",{staticClass:"education-article"},[i("section",[i("router-view")],1)]),t._v(" "),i("div",{staticClass:"clear"})])]),t._v(" "),i("cus-footer")],1)},c=[],s={render:e,staticRenderFns:c};a.a=s},YGTK:function(t,a,i){a=t.exports=i("UTlt")(!1),a.push([t.i,"[data-v-f0f4993a]{margin:0;padding:0}body[data-v-f0f4993a],html[data-v-f0f4993a]{width:100%;height:100%}ul li[data-v-f0f4993a]{list-style-type:none}h4[data-v-f0f4993a]{font-weight:400}.education[data-v-f0f4993a]{padding:20px 60px;background-color:#f8f9fc}.education-main[data-v-f0f4993a]{position:relative;width:100%}.education-main .education-aside[data-v-f0f4993a]{float:left;-webkit-box-sizing:border-box;box-sizing:border-box;width:200px}.education-main .education-article[data-v-f0f4993a]{float:left;-webkit-box-sizing:border-box;box-sizing:border-box;margin-left:20px;width:calc(100% - 220px);min-width:500px;background:#fff;padding:30px 50px}.education-aside .aside-wrap .aside-item[data-v-f0f4993a]{width:200px;margin-bottom:10px;height:40px;line-height:40px;font-size:16px;padding-left:14px;-webkit-box-sizing:border-box;box-sizing:border-box;cursor:pointer;background:#fff;border-left:3px solid #fff;color:#343d5c;-webkit-transition:all .3s;transition:all .3s}.education-aside .aside-wrap .aside-item[data-v-f0f4993a]:hover{color:#1fc478}.education-aside .aside-wrap .aside-item-active[data-v-f0f4993a]{border-left:3px solid #1fdb84;color:#1fc478}.clear[data-v-f0f4993a]{clear:both}",""])},d05H:function(t,a,i){var e=i("I/r+");"string"==typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);i("FIqI")("1d7e582b",e,!0,{})},htMJ:function(t,a,i){"use strict";function e(t){i("3i7g")}var c=i("w8+G"),s=i("IOg+"),n=i("C7Lr"),o=e,r=n(c.a,s.a,!1,o,"data-v-5436b21d",null);a.a=r.exports},jn5G:function(t,a,i){"use strict";function e(t){i("Cdph")}Object.defineProperty(a,"__esModule",{value:!0});var c=i("PUhB"),s=i("Uk/5"),n=i("C7Lr"),o=e,r=n(c.a,s.a,!1,o,"data-v-f0f4993a",null);a.default=r.exports},t9Cx:function(t,a,i){"use strict";var e=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"cus-header"},[e("div",{staticClass:"head-bar"}),t._v(" "),e("div",{staticClass:"head-l"},[e("img",{staticClass:"logo",attrs:{src:i("wrlu"),alt:"logo"},on:{click:function(a){t.goHome()}}}),t._v(" "),e("div",{staticClass:"title"},[t._v(t._s(t.title))])]),t._v(" "),e("div",{staticClass:"head-r"},[e("div",{staticClass:"back-text",on:{click:function(a){t.goHome()}}},[e("a",{staticClass:"link",attrs:{href:"javascript:void(0)"}},[t._v("返回瑞华保险首页")])]),t._v(" "),e("div",{staticClass:"back",on:{click:function(a){t.goHome()}}},[e("img",{staticClass:"back-img",attrs:{src:i("zk1I"),alt:"back"}})])])])},c=[],s={render:e,staticRenderFns:c};a.a=s},u3kz:function(t,a,i){"use strict";a.a={name:"cusHeader",props:["title"],data:function(){return{}},methods:{goHome:function(){this.go("/rhOffic/home")}}}},v13x:function(t,a,i){a=t.exports=i("UTlt")(!1),a.push([t.i,".footer[data-v-5436b21d]{text-align:center;width:100%;line-height:60px;height:60px}.footer[data-v-5436b21d],.footer a[data-v-5436b21d]{font-size:12px;color:#bdbdbd}",""])},"w8+G":function(t,a,i){"use strict";a.a={name:"cusFooter",data:function(){return{}}}},wrlu:function(t,a,i){t.exports=i.p+"static/img/logo_information.jpg"},zk1I:function(t,a){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAASCAYAAABWzo5XAAAABGdBTUEAALGPC/xhBQAAAKFJREFUOBFjYBiS4P///0xAbAnE7BR5AGhAPBCDwAkg5ifbMKBmTSD+DMQgQLFhNkBDhqJhQGcvBuIfROK/QHUwsB0U+CxIMaADZJMTvdIgMxhhBgGNZwOyVWF8PLQ8UG45EPMB8TMgdmBkZLwNpIkHQMvkgPge1E9PgTQxFqNaQC1DxCl2CchdQENSgRgEyPMOzHNAA/iAOAeIZWFig58GAKYK7h4Z4KrRAAAAAElFTkSuQmCC"}});