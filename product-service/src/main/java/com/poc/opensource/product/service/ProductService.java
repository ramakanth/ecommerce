package com.poc.opensource.product.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.poc.opensource.product.entity.Product;
import com.poc.opensource.product.exception.ProductNotFoundException;
import com.poc.opensource.product.vo.ProductVO;

public interface ProductService {
  public ProductVO createProduct(ProductVO productVO) throws ProductNotFoundException;
  public List<ProductVO> getAllProducts();
  public ProductVO getProductById(long productId);
  public ProductVO updateProduct(ProductVO productVo);
  public void deleteProductById(long productId);
  
  public List<ProductVO> getAllProductsByDate(LocalDate createdDate);
  public List<ProductVO> getAllProductsByDateRange(LocalDateTime to, LocalDateTime from);
  public int getAllProductsCount();
  public double getProductPrice(long productId);
  public List<ProductVO> getProductByIds(List<Long> ids);
  public List<ProductVO> getProductsByDate(LocalDate date);
  public ProductVO findByProductName(String productName);
}
