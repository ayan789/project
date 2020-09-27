package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.dto.AccidentalInjuryInfo;

@Mapper
public interface StoreAccidentalinjuryMapper {

	void doInsert(AccidentalInjuryInfo accidentalInjuryInfo);
	
	List<AccidentalInjuryInfo> doSelect(BigDecimal id);

}
