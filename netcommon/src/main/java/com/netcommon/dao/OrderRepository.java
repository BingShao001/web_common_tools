package com.netcommon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.netcommon.entity.OrderEntity;
@Repository
public interface OrderRepository {
	public List<OrderEntity> getOrderList(@Param("currentPage")int currentPage,@Param("page")int page);
	public int addOrderBatch(List<OrderEntity> orderEntitys);
}
