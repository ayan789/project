export const familyInfo = {
  code: "1",
  data: {
    bizId: 1,
    formulaCode: "WTN2050024",
    planId: 1,
    planBatch: null,
    planElementGroup: [
      {
        id: 1,
        planId: 0,
        bizId: 0,
        sourceGroupId: 0,
        category: "policy",
        code: "applicant",
        name: "投保人信息",
        sort: 1,
        planElementList: Array[6]
      },
      {
        id: 2,
        planId: 0,
        bizId: 0,
        sourceGroupId: 0,
        category: "policy",
        code: "insured",
        name: "被保人信息",
        sort: 2,
        planElementList: [
          {
            id: 17,
            groupId: 0,
            sourceElementId: 17,
            code: "securityNum",
            name: "保障人数",
            requireInput: 1,
            defaultValue: "0",
            sort: 1,
            trialFlag: 0,
            planElementOptionList: [
              {
                id: 18,
                elementId: 0,
                sourceOptionId: 0,
                value: "2",
                label: "2人",
                selected: 1,
                sort: 1,
                children: []
              },
              {
                id: 19,
                elementId: 0,
                sourceOptionId: 0,
                value: "3",
                label: "3人",
                selected: 0,
                sort: 2,
                children: []
              },
              {
                id: 20,
                elementId: 0,
                sourceOptionId: 0,
                value: "4",
                label: "4人",
                selected: 0,
                sort: 3,
                children: []
              }
            ],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0,
            controlType: "select"
          },
          {
            id: 7,
            groupId: 0,
            sourceElementId: 7,
            code: "appliIdentity",
            name: "与投保人关系",
            requireInput: 1,
            defaultValue: "0",
            sort: 1,
            trialFlag: 0,
            planElementOptionList: [
              {
                id: 4,
                elementId: 0,
                sourceOptionId: 0,
                value: "01",
                label: "本人",
                selected: 0,
                sort: 1,
                children: []
              },
              {
                id: 5,
                elementId: 0,
                sourceOptionId: 0,
                value: "50",
                label: "父母",
                selected: 1,
                sort: 2,
                children: []
              },
              {
                id: 6,
                elementId: 0,
                sourceOptionId: 0,
                value: "10",
                label: "配偶",
                selected: 0,
                sort: 3,
                children: []
              },
              {
                id: 7,
                elementId: 0,
                sourceOptionId: 0,
                value: "40",
                label: "子女",
                selected: 0,
                sort: 4,
                children: []
              }
            ],
            minLength: 0,
            maxLength: 100,
            minValue: 0,
            maxValue: 100,
            elementType: "String",
            controlType: "select",
            eventScript: "0",
            validateType: "relation",
            placeholder: "0"
          },
          {
            id: 8,
            groupId: 0,
            sourceElementId: 0,
            code: "insuredName",
            name: "被保人姓名",
            requireInput: 1,
            defaultValue: "0",
            sort: 2,
            trialFlag: 0,
            planElementOptionList: [],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0
          },
          {
            id: 9,
            groupId: 0,
            sourceElementId: 2,
            code: "insuredIdentifyType",
            name: "证件类型",
            requireInput: 1,
            defaultValue: "0",
            sort: 3,
            trialFlag: 0,
            planElementOptionList: [
              {
                id: 8,
                elementId: 0,
                sourceOptionId: 0,
                value: "01",
                label: "身份证",
                selected: 1,
                sort: 1,
                children: []
              },
              {
                id: 9,
                elementId: 0,
                sourceOptionId: 0,
                value: "03",
                label: "护照",
                selected: 0,
                sort: 2,
                children: []
              },
              {
                id: 10,
                elementId: 0,
                sourceOptionId: 0,
                value: "28",
                label: "港澳台居民居住证",
                selected: 0,
                sort: 3,
                children: []
              }
            ],
            minLength: 0,
            maxLength: 100,
            minValue: 0,
            maxValue: 100,
            elementType: "String",
            controlType: "select",
            eventScript: "0",
            validateType: "idType",
            placeholder: "0"
          },
          {
            id: 10,
            groupId: 0,
            sourceElementId: 0,
            code: "insuredIdentifyNumber",
            name: "证件号码",
            requireInput: 1,
            defaultValue: "0",
            sort: 4,
            trialFlag: 0,
            planElementOptionList: [],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0
          },
          {
            id: 11,
            groupId: 0,
            sourceElementId: 0,
            code: "insuredBirthDate",
            name: "出生日期",
            requireInput: 1,
            defaultValue: "0",
            sort: 5,
            trialFlag: 0,
            planElementOptionList: [],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0
          },
          {
            id: 12,
            groupId: 0,
            sourceElementId: 0,
            code: "insuredMobile",
            name: "手机号",
            requireInput: 0,
            defaultValue: "0",
            sort: 6,
            trialFlag: 0,
            planElementOptionList: [],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0
          },
          {
            id: 13,
            groupId: 0,
            sourceElementId: 13,
            code: "0",
            name: "社保类型",
            requireInput: 1,
            defaultValue: "0",
            sort: 7,
            trialFlag: 0,
            planElementOptionList: [
              {
                id: 11,
                elementId: 0,
                sourceOptionId: 0,
                value: "1",
                label: "城镇职工基本医疗保险",
                selected: 0,
                sort: 1,
                children: []
              },
              {
                id: 12,
                elementId: 0,
                sourceOptionId: 0,
                value: "8",
                label: "城乡居民基本医疗保险",
                selected: 0,
                sort: 2,
                children: []
              },
              {
                id: 13,
                elementId: 0,
                sourceOptionId: 0,
                value: "2",
                label: "城镇居民基本医疗保险",
                selected: 0,
                sort: 3,
                children: []
              },
              {
                id: 14,
                elementId: 0,
                sourceOptionId: 0,
                value: "3",
                label: "新农合医疗保险",
                selected: 0,
                sort: 4,
                children: []
              },
              {
                id: 15,
                elementId: 0,
                sourceOptionId: 0,
                value: "7",
                label: "公费医疗",
                selected: 0,
                sort: 5,
                children: []
              },
              {
                id: 16,
                elementId: 0,
                sourceOptionId: 0,
                value: "4",
                label: "民政优抚医疗保险",
                selected: 0,
                sort: 6,
                children: []
              },
              {
                id: 17,
                elementId: 0,
                sourceOptionId: 0,
                value: "6",
                label: "其他",
                selected: 0,
                sort: 7,
                children: []
              }
            ],
            minLength: 0,
            maxLength: 100,
            minValue: 0,
            maxValue: 100,
            elementType: "String",
            controlType: "select",
            eventScript: "0",
            validateType: "sheBaoType",
            placeholder: "0"
          }
        ]
      },
      {
        id: 3,
        planId: 0,
        bizId: 0,
        sourceGroupId: 0,
        category: "policy",
        code: "assure",
        name: "保障期限",
        sort: 3,
        planElementList: [
          {
            id: 14,
            groupId: 0,
            sourceElementId: 0,
            code: "0",
            name: "保障年限",
            requireInput: 1,
            defaultValue: "0",
            sort: 1,
            trialFlag: 0,
            planElementOptionList: [],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0
          },
          {
            id: 15,
            groupId: 0,
            sourceElementId: 0,
            code: "0",
            name: "起保日期",
            requireInput: 1,
            defaultValue: "0",
            sort: 2,
            trialFlag: 0,
            planElementOptionList: [],
            minLength: 0,
            maxLength: 0,
            minValue: 0,
            maxValue: 0
          }
        ]
      }
    ]
  },
  msg: "SUCCESS"
};
