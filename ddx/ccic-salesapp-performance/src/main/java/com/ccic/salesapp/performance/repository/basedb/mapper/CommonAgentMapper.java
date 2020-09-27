package com.ccic.salesapp.performance.repository.basedb.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.performance.domain.vo.dto.CommonAgent;
import java.util.List;

/**
 * 常用代理人dao
 */
@Mapper
public interface CommonAgentMapper {


    List<CommonAgent> searchByAgentCode(CommonAgent agent);

    void insert(CommonAgent agent);

    void update(CommonAgent agent);
}
