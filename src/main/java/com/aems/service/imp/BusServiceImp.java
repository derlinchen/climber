package com.aems.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aems.dao.BusDao;
import com.aems.service.BusService;

@Service("busService ")
public class BusServiceImp implements BusService {

	@Resource
	private BusDao busDao;

}
