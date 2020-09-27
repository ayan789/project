package com.ccic.salesapp.performance.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.performance.domain.vo.dto.EnchashmentConfig;

@Mapper
public interface EnchashmentMapper {

    EnchashmentConfig selectConfigByAgentCode(String agentCode);

    void insert(EnchashmentConfig config);

    void update(EnchashmentConfig config);
}
