package com.poc.opensource.product.repository;

import java.time.LocalDate;
import java.util.List;

import com.poc.opensource.product.entity.Product;

public interface IProductRepository {
	 public List<Product> getProductsByDate(LocalDate date);
}
