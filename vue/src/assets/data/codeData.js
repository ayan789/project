/* ------------------------------------------码表------------------------------------------ */
const codeData = {
  //证件码表
  certType: [
    { code: '1', desc: '身份证' },
    { code: '2', desc: '港澳同胞证' },
  ],
  //活动类型
  actType: [
    {code: '1', desc: '一般活动'},
    {code: '2', desc: '特殊活动'}
  ],
  //新闻类型
  newsType: [
    {code: '1', desc: '普通新闻'},
    {code: '2', desc: '紧急新闻'}
  ],
  //状态
  stateType:[
    {code: 'NS', desc: '未开始'},
    {code: 'ON', desc: '进行中'},
    {code: 'OFF',desc: '已关闭'},
    {code: 'OV', desc: '已结束'}
  ],
  //测评类别
  evalutionType:[
    {code: '1', desc:'全部'},
    {code: '2', desc:'亚健康'},
    {code: '3', desc:'跑步'}
  ],
  //是否启用
  useType:[
    {code: '1', desc:'启用'},
    {code: '2', desc:'禁用'}
    
  ],
   //状态类型
   stateTypeByIAE: [
    { code: ' ', desc: '全部' },
    { code: '6', desc: '未发布' },
    { code: '1', desc: '待生效' },
    { code: '2', desc: '生效中' },
    { code: '3', desc: '已失效' },
    { code: '4', desc: '已关闭' },
    // { code: '5', desc:'永久' }
  ],
  //公开信息披露一级菜单
  InforLevelMenu: [{
    code: ' ',
    desc: '全部',
    children: [{
        code: ' ',
        desc: '全部'
      },
      {
        code: '4b82e514e3f311e88',
        desc: '公司治理概要'
      },
      {
        code: '423422e3f311e88',
        desc: '近3年股东大会主要决议'
      },
      {
        code: '4b8489d4e3f311e88',
        desc: '在售产品'
      },
      {
        code: '4b89878e3f311e88',
        desc: '停售产品'
      },
      {
        code: 'c86cadcae3f311e88',
        desc: '关联交易'
      },
      {
        code: 'f5f2ae93e3f311e88',
        desc: '股东股权'
      },
      {
        code: 'f5f442eae3f311e88',
        desc: '偿付能力'
      },
      {
        code: 'f5f5cb2ce3f311e88',
        desc: '互联网保险'
      },
      {
        code: 'f5f76fabe3f311e88',
        desc: '资金运用'
      },
      {
        code: 'f5f90904e3f311e88',
        desc: '新型产品'
      },
      {
        code: '2f84ef8de3f311e88',
        desc: '公司概况'
      }
    ]
  },
  {
    code: 'c4307348e3f211e88',
    desc: '基本信息',
    children: [{
        code: ' ',
        desc: '全部'
      },
      {
        code: '4b82e514e3f311e88',
        desc: '公司治理概要'
      },
      {
        code: '423422e3f311e88',
        desc: '近3年股东大会主要决议'
      },
      {
        code: '4b8489d4e3f311e88',
        desc: '在售产品'
      },
      {
        code: '4b89878e3f311e88',
        desc: '停售产品'
      },
      {
        code: '2f84ef8de3f311e88',
        desc: '公司概况'
      }
    ]
  },
  {
    code: '3c4322570e3f211e88',
    desc: '年度信息'
  },
  {
    code: 'c433c5d0e3f211e88',
    desc: '重大事项'
  },
  {
    code: 'c4355b11e3f211e88',
    desc: '专项信息',
    children: [{
        code: ' ',
        desc: '全部'
      },
      {
        code: 'c86cadcae3f311e88',
        desc: '关联交易'
      },
      {
        code: 'f5f2ae93e3f311e88',
        desc: '股东股权'
      },
      {
        code: 'f5f442eae3f311e88',
        desc: '偿付能力'
      },
      {
        code: 'f5f5cb2ce3f311e88',
        desc: '互联网保险'
      },
      {
        code: 'f5f76fabe3f311e88',
        desc: '资金运用'
      },
      {
        code: 'f5f90904e3f311e88',
        desc: '新型产品'
      }
    ]
  }
]
}



export default codeData;