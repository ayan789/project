package com.ccic.salesapp.noncar.repository.basedb.mapper;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Mapper;
import com.ccic.salesapp.noncar.dto.InvoiceInfo;

@Mapper
public interface InvoiceInfoMapper {

	void insert(InvoiceInfo invoiceInfo);
	
	InvoiceInfo queryInvoiceInfo(BigDecimal id);
	
	InvoiceInfo selectInvoiceInfoByOrderId(Long orderId);
	
	com.ccic.salesapp.noncar.dto.request.noncar.InvoiceInfo selectInvoiceInfoByOrderId2(Long orderId);
	
	int deleteByOrderId(Long orderId);

}
