webpackJsonp([23],{Ijh9:function(t,e,n){"use strict";var i=n("uda0");e.a=i.a},Rnyz:function(t,e,n){"use strict";var i=n("Ijh9");e.a={name:"editor",components:{VueUEditor:i.a},data:function(){return{}},methods:{editorReady:function(t){t.setContent("hello world")}},computed:{},mounted:function(){}}},YKzo:function(t,e,n){"use strict";e.a={name:"VueUEditor",props:{ueditorPath:{type:String,default:"/static/ueditor1_4_3_3/"},ueditorConfig:{type:Object,default:function(){return{}}}},data:function(){return{randomId:"editor_"+1e17*Math.random(),instance:null,scriptTagStatus:0}},created:function(){void 0!==window.UE?(this.scriptTagStatus=2,this.initEditor()):this.insertScriptTag()},beforeDestroy:function(){null!==this.instance&&this.instance.destroy&&this.instance.destroy()},methods:{insertScriptTag:function(){var t=this,e=document.getElementById("editorScriptTag"),n=document.getElementById("configScriptTag");if(null===e){n=document.createElement("script"),n.type="text/javascript",n.src=this.ueditorPath+"ueditor.config.js",n.id="configScriptTag",e=document.createElement("script"),e.type="text/javascript",e.src=this.ueditorPath+"ueditor.all.js",e.id="editorScriptTag";var i=document.getElementsByTagName("head")[0];i.appendChild(n),i.appendChild(e)}n.loaded?this.scriptTagStatus++:n.addEventListener("load",function(){t.scriptTagStatus++,n.loaded=!0,t.initEditor()}),e.loaded?this.scriptTagStatus++:e.addEventListener("load",function(){t.scriptTagStatus++,e.loaded=!0,t.initEditor()}),this.initEditor()},initEditor:function(){var t=this;2===this.scriptTagStatus&&null===this.instance&&this.$nextTick(function(){t.instance=window.UE.getEditor(t.randomId,t.ueditorConfig),t.instance.addListener("ready",function(){t.$emit("ready",t.instance)})})}}}},dMu2:function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement;return(t._self._c||e)("script",{attrs:{id:t.randomId,name:"content",type:"text/plain"}})},r=[],a={render:i,staticRenderFns:r};e.a=a},fWuM:function(t,e,n){"use strict";var i=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("VueUEditor",{on:{ready:t.editorReady}})],1)},r=[],a={render:i,staticRenderFns:r};e.a=a},pYPd:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=n("Rnyz"),r=n("fWuM"),a=n("C7Lr"),d=a(i.a,r.a,!1,null,null,null);e.default=d.exports},uda0:function(t,e,n){"use strict";var i=n("YKzo"),r=n("dMu2"),a=n("C7Lr"),d=a(i.a,r.a,!1,null,null,null);e.a=d.exports}});