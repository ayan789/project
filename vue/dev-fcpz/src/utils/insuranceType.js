var insuranceType = [
  // {
  // 	code: "2",
  // 	text: "车险",
  // 		child:[
  // 		{
  // 			code:"20",
  // 			text:"交强险",
  // 			child:[
  // 				{
  // 					code:"200",
  // 					text:"交强险"
  // 				}
  // 			]
  // 		},{
  // 			code:"21",
  // 			text:"商业车险",
  // 			child:[
  // 				{
  // 					code:"210",
  // 					text:"商业车险"
  // 				}
  // 			]
  // 		}
  // 	]
  // },
  {
    code: "3",
    text: "非车险",
    child: [
      {
        code: "30",
        text: "保证保险",
        child: [
          {
            code: "300",
            text: "贷款类保证保险"
          },
          {
            code: "301",
            text: "个人贷款保证保险"
          },
          {
            code: "302",
            text: "其他保证保险"
          }
        ]
      },
      {
        code: "31",
        text: "船舶保险",
        child: [
          {
            code: "310",
            text: "船建险"
          },
          {
            code: "311",
            text: "内河船舶险"
          },
          {
            code: "312",
            text: "沿海船舶险"
          },
          {
            code: "313",
            text: "油污责任险"
          },
          {
            code: "314",
            text: "渔船保险"
          },
          {
            code: "315",
            text: "远洋船舶险"
          },
          {
            code: "316",
            text: "航运责任"
          }
        ]
      },
      {
        code: "32",
        text: "工程保险",
        child: [
          {
            code: "320",
            text: "工程保险"
          }
        ]
      },
      {
        code: "33",
        text: "货物运输保险",
        child: [
          {
            code: "330",
            text: "出口货运险"
          },
          {
            code: "331",
            text: "公路货运险"
          },
          {
            code: "332",
            text: "航空货运险"
          },
          {
            code: "333",
            text: "进口货运险"
          },
          {
            code: "334",
            text: "水路货运险"
          },
          {
            code: "335",
            text: "铁路货运险"
          }
        ]
      },
      {
        code: "34",
        text: "家庭财产保险",
        child: [
          {
            code: "340",
            text: "房贷险"
          },
          {
            code: "341",
            text: "普通家财险"
          },
          {
            code: "342",
            text: "投资型家财险"
          }
        ]
      },
      {
        code: "35",
        text: "农业保险",
        child: [
          {
            code: "350",
            text: "商业性林木险"
          },
          {
            code: "351",
            text: "商业性养殖险"
          },
          {
            code: "352",
            text: "商业性种植险"
          },
          {
            code: "353",
            text: "政策性林木险"
          },
          {
            code: "354",
            text: "政策性养殖险"
          },
          {
            code: "355",
            text: "政策性种植险"
          }
        ]
      },
      {
        code: "36",
        text: "企财险",
        child: [
          {
            code: "360",
            text: "财产险"
          },
          {
            code: "361",
            text: "其他财产险"
          },
          {
            code: "362",
            text: "一揽子综合保险"
          }
        ]
      },
      {
        code: "37",
        text: "特殊风险保险",
        child: [
          {
            code: "370",
            text: "航空险"
          },
          {
            code: "371",
            text: "航天险"
          },
          {
            code: "372",
            text: "核能险"
          },
          {
            code: "373",
            text: "石油险"
          },
          {
            code: "374",
            text: "航空器"
          }
        ]
      },
      {
        code: "38",
        text: "投资型非寿险",
        child: [
          {
            code: "380",
            text: "投资型非寿险"
          }
        ]
      },
      {
        code: "39",
        text: "信用保险",
        child: [
          {
            code: "390",
            text: "出口贸易信用保险"
          },
          {
            code: "391",
            text: "国内贸易信用保险"
          },
          {
            code: "392",
            text: "其他信用保险"
          }
        ]
      },
      {
        code: "40",
        text: "责任保险",
        child: [
          {
            code: "400",
            text: "产品责任险"
          },
          {
            code: "401",
            text: "承运人责任险"
          },
          {
            code: "402",
            text: "公众责任险"
          },
          {
            code: "403",
            text: "雇主责任险"
          },
          {
            code: "404",
            text: "其他责任险"
          },
          {
            code: "405",
            text: "校方责任险"
          },
          {
            code: "406",
            text: "职业责任险"
          }
        ]
      },
      {
        code: "41",
        text: "其他",
        child: []
      }
    ]
  },
  {
    code: "5",
    text: "人身险",
    child: [
      {
        code: "50",
        text: "短期健康保险",
        child: [
          {
            code: "500",
            text: "医疗保险"
          },
          {
            code: "501",
            text: "疾病保险"
          },
          {
            code: "502",
            text: "护理保险"
          },
          {
            code: "503",
            text: "失能保险"
          }
        ]
      },
      {
        code: "51",
        text: "意外伤害保险",
        child: [
          {
            code: "510",
            text: "普通意外保险"
          }
        ]
      }
    ]
  },
  {
    code: "7",
    text: "线上非车险",
    child: [
      {
        code: "70",
        text: "线上保证保险",
        child: [
          {
            code: "700",
            text: "线上贷款类保证保险"
          },
          {
            code: "701",
            text: "线上个人贷款保证保险"
          },
          {
            code: "702",
            text: "线上其他保证保险"
          }
        ]
      },
      {
        code: "71",
        text: "线上船舶保险",
        child: [
          {
            code: "710",
            text: "线上船建险"
          },
          {
            code: "711",
            text: "线上内河船舶险"
          },
          {
            code: "712",
            text: "线上沿海船舶险"
          },
          {
            code: "713",
            text: "线上油污责任险"
          },
          {
            code: "714",
            text: "线上渔船保险"
          },
          {
            code: "715",
            text: "线上远洋船舶险"
          },
          {
            code: "716",
            text: "线上航运责任"
          }
        ]
      },
      {
        code: "72",
        text: "线上工程保险",
        child: [
          {
            code: "720",
            text: "线上工程保险"
          }
        ]
      },
      {
        code: "73",
        text: "线上货物运输保险",
        child: [
          {
            code: "730",
            text: "线上出口货运险"
          },
          {
            code: "731",
            text: "线上公路货运险"
          },
          {
            code: "732",
            text: "线上航空货运险"
          },
          {
            code: "733",
            text: "线上进口货运险"
          },
          {
            code: "734",
            text: "线上水路货运险"
          },
          {
            code: "735",
            text: "线上铁路货运险"
          }
        ]
      },
      {
        code: "74",
        text: "线上家庭财产保险",
        child: [
          {
            code: "740",
            text: "线上房贷险"
          },
          {
            code: "741",
            text: "线上普通家财险"
          },
          {
            code: "742",
            text: "线上投资型家财险"
          }
        ]
      },
      {
        code: "75",
        text: "线上农业保险",
        child: [
          {
            code: "750",
            text: "线上商业性林木险"
          },
          {
            code: "751",
            text: "线上商业性养殖险"
          },
          {
            code: "752",
            text: "线上商业性种植险"
          },
          {
            code: "753",
            text: "线上政策性林木险"
          },
          {
            code: "754",
            text: "线上政策性养殖险"
          },
          {
            code: "755",
            text: "线上政策性种植险"
          }
        ]
      },
      {
        code: "76",
        text: "线上企财险",
        child: [
          {
            code: "760",
            text: "线上财产险"
          },
          {
            code: "761",
            text: "线上其他财产险"
          },
          {
            code: "762",
            text: "线上一揽子综合保险"
          }
        ]
      },
      {
        code: "77",
        text: "线上特殊风险保险",
        child: [
          {
            code: "770",
            text: "线上航空险"
          },
          {
            code: "771",
            text: "线上航天险"
          },
          {
            code: "772",
            text: "线上核能险"
          },
          {
            code: "773",
            text: "线上石油险"
          },
          {
            code: "774",
            text: "线上航空器"
          }
        ]
      },
      {
        code: "78",
        text: "线上投资型非寿险",
        child: [
          {
            code: "780",
            text: "线上投资型非寿险"
          }
        ]
      },
      {
        code: "79",
        text: "线上信用保险",
        child: [
          {
            code: "790",
            text: "线上出口贸易信用保险"
          },
          {
            code: "791",
            text: "线上国内贸易信用保险"
          },
          {
            code: "792",
            text: "线上其他信用保险"
          }
        ]
      },
      {
        code: "80",
        text: "线上责任保险",
        child: [
          {
            code: "800",
            text: "线上产品责任险"
          },
          {
            code: "801",
            text: "线上承运人责任险"
          },
          {
            code: "802",
            text: "线上公众责任险"
          },
          {
            code: "803",
            text: "线上雇主责任险"
          },
          {
            code: "804",
            text: "线上其他责任险"
          },
          {
            code: "805",
            text: "线上校方责任险"
          },
          {
            code: "806",
            text: "线上职业责任险"
          }
        ]
      },
      {
        code: "81",
        text: "线上其他",
        child: []
      }
    ]
  },
  {
    code: "9",
    text: "线上人身险",
    child: [
      {
        code: "90",
        text: "线上短期健康保险",
        child: [
          {
            code: "900",
            text: "线上医疗保险"
          },
          {
            code: "901",
            text: "线上疾病保险"
          },
          {
            code: "902",
            text: "线上护理保险"
          },
          {
            code: "903",
            text: "线上失能保险"
          }
        ]
      },
      {
        code: "91",
        text: "线上意外伤害保险",
        child: [
          {
            code: "910",
            text: "线上普通意外保险"
          }
        ]
      }
    ]
  }
];

$(function() {
  $.fn.showInsuranceType = function() {
    var _thisNode = $(this);
    $(this).click(function() {
      if (!document.getElementById("_dialogs")) {
        var _html =
          "<div style='overflow: auto;position: absolute;top: 0;width: 100%;height: 100%;z-index: 99999999;' id='_dialogs'></div>";
        $("body").append(_html);
      }
      if (!document.getElementById("_dialog_shelter")) {
        $("#_dialogs").append(
          "<div id='_dialog_shelter' style='display:none; -webkit-transition:opacity .5s; opacity: 0;width: 100%;height: 100%;position: absolute;bottom: 0;right: 0;background-color:rgb(0, 0, 0);z-index: 99999999;'><div></div></div>"
        );
      }
      if (!document.getElementById("_showInsuranceType")) {
        var html =
          "<div id='_showInsuranceType' style='overflow-y: scroll;transition: height 0.5s;width: 100%;height: 0px;position: absolute;bottom: 0;z-index: 100000000;background: #fff;border-top: 1px solid #E7E7E7;'>  </div>";
        $("#_dialogs").append(html);
      }
      $("#_dialog_shelter")
        .show()
        .css("opacity", ".5");
      $("#_showInsuranceType")
        .show()
        .css("height", "50%");
      _fun01(insuranceType);
    });
    var _fun01 = function(data) {
      var _htmlInsuranceType = "<div><ul>";
      for (var i = 0; i < data.length; i++) {
        _htmlInsuranceType +=
          "<li data-code='" + data[i].code + "'>" + data[i].text + "</li>";
      }
      _htmlInsuranceType += "</ul></div>";

      $("#_showInsuranceType").html(_htmlInsuranceType);

      $("#_showInsuranceType")
        .find("ul li")
        .click(function(e) {
          e.stopPropagation();
          e.preventDefault();
          var _code = $(this).attr("data-code");
          var _data;
          for (var i in data) {
            if (data[i].code == _code) {
              _data = data[i];
            }
          }
          if (_data.child && _data.child.length > 0) {
            _fun01(_data.child);
          } else {
            _thisNode.val(_data.text);
            _thisNode.attr("data-code", _data.code);
            $("#_dialogs").remove();
          }
        })
        .on("touchstart", function() {
          $(this).css("background", "#63CEFF");
        })
        .on("touchend", function() {
          $(this).css("background", "#fff");
        });
    };
  };
});
