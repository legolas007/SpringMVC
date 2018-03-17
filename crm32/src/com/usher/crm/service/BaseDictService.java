package com.usher.crm.service;

import java.util.List;

import com.usher.crm.pojo.BaseDict;

public interface BaseDictService {
	//查询
		public List<BaseDict> selectBaseDictListByCode(String code);
}
