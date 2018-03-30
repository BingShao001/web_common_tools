package com.netcommon.service.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.netcommon.dao.OrderRepository;
import com.netcommon.entity.OrderEntity;
import com.netcommon.service.OrderService;
import com.netcommon.util.ArrayUtil;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private DataSourceTransactionManager transactionManager;
	public List<OrderEntity> getOrderList(int currentPage, int page) {
		return orderRepository.getOrderList(currentPage, page);
	}

	@Override
    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
	public int addOrderBatch(List<OrderEntity> orderEntitys,int flag) throws InterruptedException, ExecutionException {
		int bath = 0;
		long begin = System.currentTimeMillis();
		if(flag == 1){
			bath = this.getThread(orderEntitys);
		}else{
			orderRepository.addOrderBatch(orderEntitys);
		}
		long end = System.currentTimeMillis();
		System.out.println("#time# : "+(end-begin)+"/ms");
		return bath;
	}

	public Integer getThread(final List<OrderEntity> orderEntitys) throws InterruptedException, ExecutionException {
		int nthread = Runtime.getRuntime().availableProcessors();
		List<List<OrderEntity>> orderEntitysMix =  ArrayUtil.arrayChunk(orderEntitys, nthread);
		ExecutorService executorService = Executors.newFixedThreadPool(nthread);
		Integer t = null;
		for(final List<OrderEntity> entities : orderEntitysMix){
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
			t = executorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
				int batch = orderRepository.addOrderBatch(entities);
					return batch;
				}

			}).get();
			transactionManager.commit(status);
		}
		executorService.shutdown();
		return t;
	}

}
