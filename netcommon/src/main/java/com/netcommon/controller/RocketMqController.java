package com.netcommon.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.netcommon.mq.Consumer;
import com.netcommon.mq.Producer;

@Controller
public class RocketMqController {
	private Logger log = Logger.getLogger(getClass());
	@Autowired
	private Producer producer;
	@Autowired
	private Consumer consumer;

	@RequestMapping("sendMsg")
	public void sendMsg(String message) throws MQClientException, RemotingException, InterruptedException {
		DefaultMQProducer p = producer.getProducer();
		Message msg = new Message("testTopic", message.getBytes());
		log.info(message);
		p.send(msg, new SendCallback() {

			@Override
			public void onSuccess(SendResult sendResult) {
				log.info(sendResult.getSendStatus().name());
				log.info("onSuccess");

				producer.destroy();
			}

			@Override
			public void onException(Throwable e) {
				log.error("onException");
			}
		});

	}

	@ResponseBody
	@RequestMapping("receiveMsg")
	public String receiveMsg() throws MQClientException {
		DefaultMQPushConsumer c = consumer.getConsumer();
		c.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				for (MessageExt msg : msgs) {
					System.out.println(new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		c.start();
		return null;
	}
}
