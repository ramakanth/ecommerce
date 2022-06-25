package com.poc.opensource.product.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.poc.opensource.product.commons.keygen.service.SequenceGeneratorService;
import com.poc.opensource.product.controller.ProductController;
import com.poc.opensource.product.entity.Product;
import com.poc.opensource.product.exception.ProductConflictException;
import com.poc.opensource.product.exception.ProductNotFoundException;
import com.poc.opensource.product.repository.IProductRepository;
import com.poc.opensource.product.repository.ProductRepository;
import com.poc.opensource.product.vo.ProductVO;
@Service
public class ProductServiceImpl implements ProductService{
	private static final Logger logger = LogManager.getLogger(ProductController.class);
	@Autowired
	private ProductRepository repository;
	@Autowired
	private IProductRepository iRepository;
    @Autowired
	private ModelMapper modelMapper;
    @Autowired
    private SequenceGeneratorService sequenceGen;
    @Override
	public ProductVO createProduct(ProductVO productVO) throws ProductConflictException{
    	ProductVO product = findByProductName(productVO.getProductName());
    	if(ObjectUtils.isEmpty(product)) {
    		logger.error("Product with name "+ productVO.getProductName()+" is already exists!");
    		throw new ProductConflictException("Product with name is already exists!");
    	}
    	
		Product prodcut = modelMapper.map(productVO, Product.class);
		prodcut.setProductId(sequenceGen.generateSequence(Product.PRODUCT_ID_SEQ));
		prodcut.setCreatedBy("ADMIN");
		prodcut.setCreatedDate(LocalDateTime.now());
		return modelMapper.map(repository.save(prodcut),ProductVO.class);
	}
    @Override
	public ProductVO updateProduct(ProductVO productVo) {
		Optional<Product> productDb = repository.findById(productVo.getProductId());
		if(!productDb.isPresent()) {
			logger.error("Can't update the Product, product ID :  (" + productVo.getProductId() + ") not found!");
			throw new ProductNotFoundException("Product with product ID :  (" + productVo.getProductId() + ") not found!");
		}
		Product product = productDb.get();
		BeanUtils.copyProperties(productVo, product);
		product.setUpdatedBy("ADMIN");
		product.setUpdatedDate(LocalDateTime.now());
		return modelMapper.map(repository.save(product), ProductVO.class);
	}
	@Override
	public List<ProductVO> getAllProducts() {
		List<Product> products = repository.findAll();
		List<ProductVO> productVos = products.stream().map(product ->modelMapper.map(product, ProductVO.class)).collect(Collectors.toList());
		return productVos;
	}

	@Override
	public ProductVO getProductById(long productId) {
		Optional<Product> product = repository.findById(productId);
		ProductVO productVO = modelMapper.map(product.get(),ProductVO.class);
		return productVO;
	}
	public ProductVO getProductByName(long productId) {
		Optional<Product> product = repository.findById(productId);
		ProductVO productVO = modelMapper.map(product.get(),ProductVO.class);
		return productVO;
	}
	@Override
	public void deleteProductById(long productId) {
		Optional<Product> product = repository.findById(productId);
		if(!product.isPresent()) {
			logger.error("Cannot delete Product,  product ID :  (" + productId + ") not found!");
			throw new ProductNotFoundException("Product with product ID :  (" + productId+ ") not found!");
		}
		repository.delete(product.get());
	}

	@Override
	public List<ProductVO> getAllProductsByDate(LocalDate createdDate) {
		 List<Product> products = repository.findByCreatedDate(createdDate);
		 List<ProductVO> productVos = products.stream().map(product ->modelMapper.map(product, ProductVO.class)).collect(Collectors.toList());
		 return productVos;

	}

	@Override
	public List<ProductVO> getAllProductsByDateRange(LocalDateTime toDate, LocalDateTime fromDate) {
		 List<Product> products = repository.findProductBySDateRange(toDate, fromDate);
		 List<ProductVO> productVos = products.stream().map(product ->modelMapper.map(product, ProductVO.class)).collect(Collectors.toList());
		 return productVos;
	}
	@Override
	public int getAllProductsCount() {
		return 0;
	}

	@Override
	public double getProductPrice(long productId) {
		return 0;
	}
	@Override
	public List<ProductVO> getProductByIds(List<Long> ids) {
		List<ProductVO> products = new ArrayList<>();
		Iterable<Product> findAllById = repository.findAllById(ids);
		for(Product product : findAllById) {
			products.add(modelMapper.map(product, ProductVO.class));
		}
		return products;
	}
	
	public List<ProductVO> getProductsByDate(LocalDate date){
	   List<Product> products = iRepository.getProductsByDate(date);
		 List<ProductVO> productVos = products.stream().map(product ->modelMapper.map(product, ProductVO.class)).collect(Collectors.toList());
		 return productVos;

	}
	@Override
	public ProductVO findByProductName(String productName) {
		Optional<Product> product = repository.findByProductName(productName);
		if(!product.isPresent()) {
			logger.error("Product name :  (" + productName + ") not found!");
			throw new ProductNotFoundException("Product not found");
		}
		ProductVO productVO = modelMapper.map(product.get(), ProductVO.class);
		return productVO;
	}
}
