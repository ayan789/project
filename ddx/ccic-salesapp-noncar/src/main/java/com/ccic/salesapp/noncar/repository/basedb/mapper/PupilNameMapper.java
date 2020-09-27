package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.dto.PupilName;

@Mapper
public interface PupilNameMapper {

	void insert(PupilName pupilName);
	
	List<PupilName> doSelect(BigDecimal id);

}
