package com.netcommon.mq;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
/**
 * 消费者监听
 * @author bing
 *
 */
//@Component
public class StringMessageListener implements MessageListenerConcurrently{

	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context) {
		for (MessageExt msg : msgs) {
            log.info("msg : " + new String(msg.getBody()));
        }
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}