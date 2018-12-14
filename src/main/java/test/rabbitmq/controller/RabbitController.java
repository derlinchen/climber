package test.rabbitmq.controller;

import java.util.HashMap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="rabbit")
public class RabbitController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	public void test(){
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		map.put("name", "pig");
		//����key���͵���Ӧ�Ķ���
		rabbitTemplate.convertAndSend(map);
		
		map.put("id", "2");
		map.put("name", "cat");
		//����key���͵���Ӧ�Ķ���
		rabbitTemplate.convertAndSend(map);
	}
	
}
