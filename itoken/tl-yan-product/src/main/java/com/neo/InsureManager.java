package com.neo;

public interface InsureManager {
    /**
     * 读取完整的SInsure
     */
    SInsure loadFullInsure(String insureId);

    /**
     * 优先读取缓存中的SInsure，初始化创建时，不进行落库操作
     */
    SInsure loadInsure(String insureId);

    SCustomer loadInsurant(String insureId);
}
