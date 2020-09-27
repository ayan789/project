package com.ccic.salessapp.common.core.exception.handler.db;

import com.ccic.salessapp.common.core.exception.handler.db.mapper.SysErrorInfoMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class SysErrorInfoService {
    private ThreadPoolExecutor pool;
    private LinkedBlockingQueue<SysErrorInfo>[] queues;
    @Autowired
    SysErrorInfoMapper mapper;

    @PostConstruct
    public void init(){
        pool=new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        queues=new LinkedBlockingQueue[10];
        initPool();

    }


    private void initPool(){
        for (int i = 0; i <10 ; i++) {
            queues[i]=new LinkedBlockingQueue<>();
            pool.execute(new ErrorInfoHandler(mapper,i));
        }
    }


    public void put(SysErrorInfo data){
        try {
            Random random=new SecureRandom();
            int index=random.nextInt(10);
            queues[index].put(data);
        }catch (Exception e){

        }
    }



    @Data
    @AllArgsConstructor
    public class ErrorInfoHandler implements Runnable{
        private SysErrorInfoMapper mapper;
        private int index;
        @Override
        public void run() {
            for (; ; ) {
                try {
                    SysErrorInfo errorInfo=queues[index].take();
                    if(StringUtils.equals("99999",errorInfo.getErrorCode())){
                        Map<String, Object> data = errorInfo.trans();
                        if (!CollectionUtils.isEmpty(data)) mapper.insert(data);
                    }
                } catch (Exception e) {

                }
            }

        }
    }

}
