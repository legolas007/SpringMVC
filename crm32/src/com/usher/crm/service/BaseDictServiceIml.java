package com.usher.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usher.crm.mapper.BaseDictDao;
import com.usher.crm.pojo.BaseDict;

@Service
public class BaseDictServiceIml implements BaseDictService{
	@Autowired
	private BaseDictDao baseDictDao;
	
	//查询
		public List<BaseDict> selectBaseDictListByCode(String code){
			return baseDictDao.selectBaseDictListByCode(code);
		}
}
