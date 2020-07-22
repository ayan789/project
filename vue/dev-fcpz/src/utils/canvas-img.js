/*
 * @Author: King
 * @Page: LCanvasImg图像文字合成插件
 * @Date: 2020-02-06 16:59:16
 * @Last Modified by: King
 * @Last Modified time: 2020-04-12 16:37:39
 */
export default class LCanvasImg {
  constructor(params) {
    this.params = {
      cw: window.innerWidth,
      ch: window.innerHeight,
      iw: "100%",
      ih: "auto",
      itype: "image/jpeg",
      iquality: 1, //压缩比 默认0.1  范围0.1-1.0 越小压缩率越大
      display: "none"
    };
    for (var param in params) {
      this.params[param] = params[param];
    }
    this.init();
  }

  init = () => {
    var canvas = document.createElement("canvas");
    canvas.id = "LCanvasImg_canvas";
    canvas.width = this.params.cw;
    canvas.height = this.params.ch;
    canvas.style.display = this.params.display;
    document.body.appendChild(canvas);
    this.canvas = canvas;
    this.clear();
  };

  clear = () => {
    var canvas = this.canvas;
    var ctx = canvas.getContext("2d");
    ctx.clearRect(0, 0, canvas.width, canvas.height);
  };

  load = (arr, callback) => {
    var i = 1;
    arr.forEach(function(obj, index, array) {
      function onLoad() {
        this[obj.name] = img;
        if (i < array.length) {
          ++i;
        } else {
          callback();
        }
      }
      var img = new Image();
      img.onload = onLoad;
      img.onerror = onLoad;
      img.src = obj.src;
    });
  };

  addFont = obj => {
    var canvas = this.canvas;
    var ctx = canvas.getContext("2d");
    ctx.font = obj.fontsize + "px " + obj.fontfamily; //文字的字体大小和字体系列
    var ftop = obj.ftop; //文字top
    var fleft = obj.fleft; //文字left
    ctx.textBaseline = obj.textBaseline || "top"; //设置绘制文本时的文本基线。
    ctx.fillText(obj.txt, fleft, ftop);
    ctx.lineWidth = 1;
    ctx.fillStyle = obj.fillStyle || "#000";
    ctx.strokeStyle = obj.strokeStyle || "rgba(255,255,255,0.4)";
    ctx.strokeText(obj.txt, fleft, ftop);
  };

  addImg = obj => {
    var canvas = this.canvas;
    var ctx = canvas.getContext("2d");
    if (
      obj.hasOwnProperty("sx") &&
      obj.hasOwnProperty("sy") &&
      obj.hasOwnProperty("sw") &&
      obj.hasOwnProperty("sh") &&
      obj.hasOwnProperty("x") &&
      obj.hasOwnProperty("y") &&
      obj.hasOwnProperty("width") &&
      obj.hasOwnProperty("height")
    ) {
      ctx.drawImage(
        this[obj.name],
        obj.sx,
        obj.sy,
        obj.sw,
        obj.sh,
        obj.x,
        obj.y,
        obj.width,
        obj.height
      );
    } else if (
      obj.hasOwnProperty("x") &&
      obj.hasOwnProperty("y") &&
      obj.hasOwnProperty("width") &&
      obj.hasOwnProperty("height")
    ) {
      ctx.drawImage(this[obj.name], obj.x, obj.y, obj.width, obj.height);
    } else if (obj.hasOwnProperty("x") && obj.hasOwnProperty("y")) {
      ctx.drawImage(this[obj.name], obj.x, obj.y);
    } else {
      ctx.drawImage(this[obj.name], 0, 0);
    }
  };

  getImgDataURL = () => {
    var canvas = this.canvas;
    return canvas.toDataURL(this.params.itype, this.params.iquality);
  };
}
