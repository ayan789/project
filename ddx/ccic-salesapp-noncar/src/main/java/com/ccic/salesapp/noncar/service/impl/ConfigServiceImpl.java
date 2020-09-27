package com.ccic.salesapp.noncar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccic.salesapp.noncar.repository.basedb.mapper.GeneralConfigMapper;
import com.ccic.salesapp.noncar.service.ConfigService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService{
	
	@Autowired
	GeneralConfigMapper generalConfigMapper;

	/**
	 * 判断地区是否为新核心试点地区
	 * @throws Exception 
	 * 
	 * */
	@Override
	public boolean isNewCore(String comCode,String riskCode) throws Exception{
		boolean isNewCore=false;//判断是否走新核心接口
		//总公司机构同上海分公司
		if(comCode.equals("3100")||comCode.equals("3103")||comCode.equals("3112")){
			comCode="3101";
		}
		List<String> newCoreAreas=this.getNewCoreInsuranceAreas(riskCode);
		if(newCoreAreas!=null&&newCoreAreas.size()>0){
			for(String area:newCoreAreas){
				if(area.equals(comCode)){
					isNewCore=true;
					break;
				}
			}    
	    }
		return isNewCore;
	}
	
	private List<String> getNewCoreInsuranceAreas(String riskCode) throws Exception {
		Map<String,String> paraMap = new HashMap<String,String>();
		String configType="";
		if("1".equals(riskCode)){//新核心车险试点地区
			configType="newCoreCarInsuranceArea";
		}else if("2".equals(riskCode)){//新核心非车险试点地区
			configType="newCorePropertyInsuranceArea";
		}else if("3".equals(riskCode)){//新核心意健险试点地区
			configType="newCorePersonalInsuranceArea";
		}else{
			return null;
		}
		paraMap.put("configType", configType);
		
		List<String> newCoreAreas=new ArrayList<String>();
		newCoreAreas = generalConfigMapper.getConfigValueNoCache(paraMap);
		return newCoreAreas;
	}
	
	@Override
	public boolean flagAddrInsurance(String comCode) throws Exception {
		boolean flagNoAddre=false;//判断是否走新核心接口
		Map<String,String> paraMap = new HashMap<String,String>();
		
		paraMap.put("configType", "notPalaceOrderArea");
		
		List<String> newCoreAreas=new ArrayList<String>();
		newCoreAreas = generalConfigMapper.getConfigValueNoCache(paraMap);
		if(newCoreAreas!=null&&newCoreAreas.size()>0){
			for(String area:newCoreAreas){
				if(area.equals(comCode)){
					flagNoAddre=true;
					break;
				}
			}    
	    }
		return flagNoAddre;
	}

	@Override
	public String yzmAreaFlag(String comCode,
			GeneralConfigMapper generalConfigMapper) {
		//总公司机构同上海分公司
		if(comCode.equals("3100")||comCode.equals("3103")||comCode.equals("3112")){
			comCode="3101";
		}
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("configType", "YZMArea");
		paraMap.put("comCode", comCode);
		String flag = generalConfigMapper.getConfigValueNoCache(paraMap).get(0);
		return flag;
	}
	
}
