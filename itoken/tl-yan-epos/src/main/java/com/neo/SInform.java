package com.neo;

import lombok.Data;

/**
 * Created by yangzhilei on 2017/10/20.
 * 告知信息
 */
@Deprecated
@Data
public class SInform {

    String insureId;
    String applicantInform;//投保人告知
    String insurantInform;//被保人告知
    String agentInform;//代理人告知

}
