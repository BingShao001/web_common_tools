package com.netcommon.mq;
import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
/***
 * 消费者统一配置管理
 * @author bing
 *
 */
public class Consumer {

	private Logger log = Logger.getLogger(getClass());
	
	private DefaultMQPushConsumer consumer;
	
	private String consumerGroup;
	
	private String namesrvAddr;
	
	private String instanceName;
	
	private String topic;
	
	private MessageListenerConcurrently messageListener;
	
	public Consumer(String consumerGroup,String namesrvAddr,String instanceName,String topic,MessageListenerConcurrently messageListener){
		this.consumerGroup = consumerGroup;
		this.namesrvAddr = namesrvAddr;
		this.instanceName = instanceName;
		this.topic = topic;
		this.messageListener = messageListener;
	}
	
	public void init() throws Exception{
		log.info("start init DefaultMQPushConsumer...");
		consumer = new DefaultMQPushConsumer(consumerGroup);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); //从队列头部开始消费
		consumer.setNamesrvAddr(namesrvAddr);
		consumer.setInstanceName(instanceName);
		consumer.subscribe(topic, "*");
		consumer.registerMessageListener(messageListener);
		consumer.start();
		log.info("DefaultMQPushConsumer init ok.");
	}

	public void destroy(){
		log.info("start destroy DefaultMQPushConsumer...");
		consumer.shutdown();
		log.info("DefaultMQPushConsumer destroy success.");
	}
	
	public DefaultMQPushConsumer getConsumer() {
		return consumer;
	}

}
