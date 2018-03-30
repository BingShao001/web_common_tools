package com.netcommon.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.netcommon.entity.OrderEntity;

public interface OrderService {
	public List<OrderEntity> getOrderList(int currentPage,int page);
	public int addOrderBatch(List<OrderEntity> orderEntitys,int flag) throws InterruptedException, ExecutionException;
}
