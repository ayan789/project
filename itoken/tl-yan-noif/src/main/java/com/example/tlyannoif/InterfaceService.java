package com.example.tlyannoif;

public interface InterfaceService {

    public long countFansCountByUserId(Long userId);

    public long countMsgCountByUserId(Long userId);

    public long countCollectCountByUserId(Long userId);

    public long countFollowCountByUserId(Long userId) ;

    public long countRedBagCountByUserId(Long userId) ;

    public long countCouponCountByUserId(Long userId);
}
