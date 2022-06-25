package com.poc.opensource.order.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.opensource.order.commons.keygen.service.SequenceGeneratorService;
import com.poc.opensource.order.entity.Order;
import com.poc.opensource.order.repository.OrderRepository;
import com.poc.opensource.order.vo.OrderVO;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private OrderRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SequenceGeneratorService sequence;
	public OrderVO placeOrder(OrderVO orderVO) {
		Order entity = modelMapper.map(orderVO, Order.class);
		entity.setOrderId(sequence.generateSequence(Order.ORDER_ID_SEQ));
		Order order = repository.save(entity);
		return modelMapper.map(order, OrderVO.class);
	}
}
