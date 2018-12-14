package com.climber.controller;

import java.util.List;

import net.sf.json.JSONArray;

import com.climber.bean.ReturnList;

public class BaseController {
	
	public <T> String OutLists(List<T> lists, int total) {
		ReturnList rtn = new ReturnList();
		rtn.setTotal(total);
		JSONArray json = JSONArray.fromObject(lists);
		rtn.setData(json.toString());
		return rtn.toString();
	}
	
}
