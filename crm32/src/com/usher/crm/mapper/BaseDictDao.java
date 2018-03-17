package com.usher.crm.mapper;
import java.util.List;

import com.usher.crm.pojo.BaseDict;
public interface BaseDictDao {

	//查询
	public List<BaseDict> selectBaseDictListByCode(String code);
	
}
