package com.poc.opensource.order.service;

import java.time.LocalDateTime;
import java.util.List;

import com.poc.opensource.order.entity.Order;
import com.poc.opensource.order.vo.OrderVO;
public interface OrderService {
  public OrderVO createOrder(OrderVO orderVO);
  public List<OrderVO> getAllOrders();
  public OrderVO getOrderById(long orderId);
  public OrderVO updateOrder(OrderVO orderVO);
  public void deleteOrderById(long orderId);
  public int getAllOrdersCount();
  public List<OrderVO> getOrdersByDate(LocalDateTime date);
  public List<OrderVO> getOrdersByDateRange(LocalDateTime from, LocalDateTime to);
}
