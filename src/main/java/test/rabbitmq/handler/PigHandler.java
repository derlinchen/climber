package test.rabbitmq.handler;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PigHandler implements MessageListener {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Override
	public void onMessage(Message msg) {
		try {
			// msg����rabbitmq��������Ϣ����Ҫ��ͬѧ�Լ���ӡ��һ��
			// ʹ��jackson����
			JsonNode jsonData = MAPPER.readTree(msg.getBody());
			System.out.println("���ǿɰ���С��,�ҵ�id��" + jsonData.get("id").asText() + ",�ҵ�������" + jsonData.get("name").asText());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
