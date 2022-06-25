package com.poc.opensource.product.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.opensource.product.exception.ProductNotFoundException;
import com.poc.opensource.product.service.ProductService;
import com.poc.opensource.product.vo.ProductRequestWrapper;
import com.poc.opensource.product.vo.ProductVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@RestController
public class ProductController {
	private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductVO>> getAllProducts() throws ProductNotFoundException{
		List<ProductVO> allProducts = productService.getAllProducts();
		if(allProducts.isEmpty()) {
			throw new ProductNotFoundException("Not products were found...");
		}
		return new ResponseEntity<List<ProductVO>>(allProducts,HttpStatus.OK);
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductVO> getProduct(@PathVariable(value ="id") long id){
		ProductVO product= productService.getProductById(id);
		return new ResponseEntity<ProductVO>(product, HttpStatus.OK);
	}
	@GetMapping("/products/productname/{prodname}")
	public ResponseEntity<ProductVO> getProductByName(@PathVariable(value ="prodname") String prodname){
		ProductVO product= productService.findByProductName(prodname);
		return new ResponseEntity<ProductVO>(product, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<ProductVO> createProduct(@Valid @RequestBody ProductVO productVO) {
		ProductVO createProduct = productService.createProduct(productVO);
		return new ResponseEntity<>(createProduct,HttpStatus.CREATED);
	}
	@PutMapping("/products")
	public ResponseEntity<ProductVO> updateProduct(@Valid @RequestBody ProductVO productVO) {
		ProductVO productVo = productService.updateProduct(productVO);
		return new ResponseEntity<ProductVO>(productVo,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("productId") long id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/products/daterange/{fromDate}/{toDate}")
	public ResponseEntity<List<ProductVO>> getAllProductsByDateRange(@PathVariable(value ="fromDate") String fromDate,
			@PathVariable(value ="toDate") String toDate){
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromDt = LocalDate.parse(fromDate,formatter);
		LocalDate toDt = LocalDate.parse(toDate,formatter);
		LocalDateTime toDateTime = toDt.atTime(23, 59, 59);
		List<ProductVO> allProducts = productService.getAllProductsByDateRange(toDateTime, fromDt.atStartOfDay());
				
		if(allProducts.isEmpty()) {
			throw new ProductNotFoundException("Not products were found...");
		}
		return new ResponseEntity<List<ProductVO>>(allProducts,HttpStatus.OK);
	}
	@PostMapping("/products/selected")
	public ResponseEntity<List<ProductVO>> getProducts(@Valid @RequestBody ProductRequestWrapper wrapper) {
		List<ProductVO> createProduct = productService.getProductByIds(wrapper.getIds());
		return new ResponseEntity<>(createProduct,HttpStatus.OK);
	}
	@GetMapping("/products/date/{createdDate}")
	public ResponseEntity<List<ProductVO>> getProductsByCreatedDate(@PathVariable("createdDate") String createdDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dt = LocalDate.parse(createdDate,formatter);
		List<ProductVO> allProducts = productService.getProductsByDate(dt);
				
		if(allProducts.isEmpty()) {
			logger.error("No product is avialbe with createdDate:  (" + createdDate + ") ");
			throw new ProductNotFoundException("Not products were found...");
		}
		return new ResponseEntity<List<ProductVO>>(allProducts,HttpStatus.OK);
		
	}

}
