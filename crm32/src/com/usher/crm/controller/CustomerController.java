package com.usher.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.usher.common.utils.Page;
import com.usher.crm.pojo.BaseDict;
import com.usher.crm.pojo.Customer;
import com.usher.crm.pojo.QueryVo;
import com.usher.crm.service.BaseDictService;
import com.usher.crm.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;

	// 注解在成员变量上
	@Value("${fromType.code}")
	private String fromTypeCode;

	// 入口
	@RequestMapping(value = "/customer/list")
	public String list(QueryVo vo, Model model) {

		// 硬编码问题
		List<BaseDict> fromType = baseDictService.selectBaseDictListByCode(this.fromTypeCode);
		List<BaseDict> industryType = baseDictService.selectBaseDictListByCode("001");
		List<BaseDict> levelType = baseDictService.selectBaseDictListByCode("006");
		// 把前端页面需要显示的数据放到模型中
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);

		// 通过四个条件 查询分页对象
		Page<Customer> page = customerService.selectPageByQueryVo(vo);
		model.addAttribute("page", page);
		model.addAttribute("custName", vo.getCustName());
		model.addAttribute("custSource", vo.getCustSource());
		model.addAttribute("custIndustry", vo.getCustIndustry());
		model.addAttribute("custLevel", vo.getCustLevel());
		// 编码
		try {
			if (!StringUtils.isEmpty(vo.getCustName())) {
				vo.setCustName(new String(vo.getCustName().getBytes("ISO-8859-1"), "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "customer";
	}

	// 去修改页面，@ResponseBody注解，使其不走视图解析器，返回JSON数据
	@RequestMapping(value = "/customer/edit.action")
	public @ResponseBody Customer edit(Integer id) {
		return customerService.selectCustomerById(id);
	}

	// 修改保存
	@RequestMapping(value = "/customer/update.action")
	public @ResponseBody String update(Customer customer) {
		customerService.updateCustomerById(customer);
		return "OK";
	}

	// 删除
	@RequestMapping(value = "/customer/delete.action")
	public @ResponseBody String delete(Integer id) {
		customerService.deleteCustomerById(id);
		return "OK";
	}

}
