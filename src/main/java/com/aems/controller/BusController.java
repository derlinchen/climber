package com.aems.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aems.service.BusService;
import com.climber.controller.BaseController;

@Controller
@RequestMapping("/bus")
public class BusController extends BaseController {
	
	@Resource
	private BusService busService;
	
}
