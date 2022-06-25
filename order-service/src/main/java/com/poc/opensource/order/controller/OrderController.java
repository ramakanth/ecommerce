package com.poc.opensource.order.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.opensource.order.exception.OrderNotFoundException;
import com.poc.opensource.order.service.OrderService;
import com.poc.opensource.order.vo.OrderVO;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public ResponseEntity<List<OrderVO>> getAllOrders(){
		List<OrderVO> allOrders = orderService.getAllOrders();
		if(allOrders.isEmpty()) {
			throw new OrderNotFoundException("Not orders were found...");
		}
		return new ResponseEntity<List<OrderVO>>(allOrders,HttpStatus.OK);
	}
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<OrderVO> getOrder(@PathVariable(value ="orderId") long orderId){
		OrderVO orderVo= orderService.getOrderById(orderId);
		return new ResponseEntity<OrderVO>(orderVo, HttpStatus.OK);
	}
	@PostMapping("/orders")
	public ResponseEntity<OrderVO> createOrder(@Valid @RequestBody OrderVO orderVO) {
		OrderVO orderVo = orderService.createOrder(orderVO);
		return new ResponseEntity<>(orderVo,HttpStatus.CREATED);
	}
	@PutMapping("/orders")
	public ResponseEntity<OrderVO> updateOrder(@Valid @RequestBody OrderVO orderVO) {
		OrderVO orderVo = orderService.updateOrder(orderVO);
		return new ResponseEntity<OrderVO>(orderVo,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/orders")
	public ResponseEntity<Object> deleteOrder(@PathVariable("orderId") long orderId) {
		orderService.deleteOrderById(orderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/orders/date")
	public ResponseEntity<List<OrderVO>> getAllOrdersByDate(@RequestParam("localDateTime") String localDateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime time = LocalDate.parse(localDateTime, formatter).atStartOfDay();

		List<OrderVO> allOrders = orderService.getOrdersByDate(time);
				
		if(allOrders.isEmpty()) {
			throw new OrderNotFoundException("Not orders were found...");
		}
		return new ResponseEntity<List<OrderVO>>(allOrders,HttpStatus.OK);
	}
	@GetMapping("/orders/daterange")
	public ResponseEntity<List<OrderVO>> getAllOrdersByDateRange(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime from = LocalDate.parse(fromDate, formatter).atTime(LocalTime.MIN);
		LocalDateTime to = LocalDate.parse(toDate, formatter).atTime(LocalTime.MAX);
		List<OrderVO> allOrders = orderService.getOrdersByDateRange(from, to);
				
		if(allOrders.isEmpty()) {
			throw new OrderNotFoundException("Not orders were found...");
		}
		return new ResponseEntity<List<OrderVO>>(allOrders,HttpStatus.OK);
	}

}
