package com.neo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by yangzhilei on 2017/11/7.
 */
@Slf4j
@Repository
public class InsureDataDao {

    public SData loadInsureData(String insureId) {
        SData data = new SData();
        return data;
    }

}