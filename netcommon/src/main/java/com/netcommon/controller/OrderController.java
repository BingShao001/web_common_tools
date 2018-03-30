package com.netcommon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netcommon.bean.OrderBean;
import com.netcommon.entity.OrderEntity;
import com.netcommon.service.OrderService;
import com.netcommon.util.BeansConvert;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("getOrderList")
	@ResponseBody
	public List<OrderBean> getOrderList(int currentPage,int page) throws InterruptedException {
		List<OrderBean> orderBeans = new ArrayList<>();
		List<OrderEntity> orderEntitys = orderService.getOrderList(currentPage,page);
		for(OrderEntity orderEntity : orderEntitys){
			OrderBean orderBean = new OrderBean();
			BeansConvert.beansCopy(orderEntity, orderBean);
			orderBeans.add(orderBean);
		}
		return orderBeans;
	}

	@RequestMapping("addOrders")
	@ResponseBody
	public int addOrder() throws InterruptedException, ExecutionException {
		List<OrderEntity> orderEntitys = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setOrderId(i);
			orderEntity.setOrderName("bubbery格子外套"+i);
			orderEntity.setPrice(i);
			orderEntity.setSaller("Burberry"+i);
			orderEntitys.add(orderEntity);
		}
		return orderService.addOrderBatch(orderEntitys,0);
	}
}
