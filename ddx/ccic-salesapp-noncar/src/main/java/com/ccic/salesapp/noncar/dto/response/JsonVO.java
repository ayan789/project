package com.ccic.salesapp.noncar.dto.response;
import java.util.List;
import lombok.Data;
import com.ccic.salesapp.noncar.dto.DataVO;
import com.ccic.salesapp.noncar.dto.HeadVO;
import com.ccic.salesapp.noncar.dto.request.PageVO;


@Data
public class JsonVO  {
	HeadVO head = new HeadVO();
	PageVO page ;
	ResultVO result;
	DataVO data;
	List<?> list;
	StoreCertiNoticeResponseVO storeCertiNoticeResponseVO;
	StoreInsureInfoFormulasResponseVO storeInsureInfoFormulasResponseVO;
	
}
