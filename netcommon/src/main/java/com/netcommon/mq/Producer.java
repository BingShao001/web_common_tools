package com.netcommon.mq;

import org.apache.log4j.Logger;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

/**
 * 生产者统一属性
 * @author bing
 *
 */
public class Producer {
	
	protected Logger log = Logger.getLogger(getClass());
	
	private String producerGroup;
	
	private String namesrvAddr;
	
	private String instanceName;
	
	private DefaultMQProducer producer;
	
	public DefaultMQProducer getProducer() {
		return producer;
	}

	public Producer(String producerGroup,String namesrvAddr,String instanceName){
		this.producerGroup = producerGroup;
		this.namesrvAddr = namesrvAddr;
		this.instanceName = instanceName;
	}
	
	public void init() throws MQClientException{
		log.info("start init DefaultMQProducer...");
		producer = new DefaultMQProducer(producerGroup);
		producer.setNamesrvAddr(namesrvAddr);
		producer.setInstanceName(instanceName);
		producer.start();
		log.info("DefaultMQProducer init success.");
	}
	
	public void destroy(){
		log.info("start destroy DefaultMQProducer...");
		producer.shutdown();
		log.info("DefaultMQProducer destroy success.");
	}

}