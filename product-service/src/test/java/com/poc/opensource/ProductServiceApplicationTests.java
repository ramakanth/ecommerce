package com.poc.opensource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.opensource.product.controller.ProductController;
import com.poc.opensource.product.service.ProductService;
import com.poc.opensource.product.vo.ProductVO;
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductServiceApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ProductService productService;
	
    @Test
	public void testGetAllProducts() throws Exception {
    	List<ProductVO> products = buildProducts();
    	Mockito.when(productService.getAllProducts()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].productName", is("Santoor")))
				.andExpect(jsonPath("$[1].productName", is("Liril")));
		verify(productService,times(1)).getAllProducts();
    }
    @Test
	public void testGetAllProducts_ExceptionScenario() throws Exception {
    	List<ProductVO> products = new ArrayList<>();
    	Mockito.when(productService.getAllProducts()).thenReturn(products);

		mockMvc.perform(MockMvcRequestBuilders.get("/products").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
		verify(productService,times(1)).getAllProducts();
    }
    @Test
    public void testCreateProduct() throws Exception {
    	ProductVO productVO = buildProducts().get(0);
    	Mockito.when(productService.createProduct(Mockito.any(ProductVO.class))).thenReturn(productVO);
    	String customerJSON = mapper.writeValueAsString(productVO);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/products")
				.accept(MediaType.APPLICATION_JSON)
				.content(customerJSON).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isCreated());
		resultActions.andExpect(jsonPath("$.productId").exists());
		resultActions.andExpect(jsonPath("$.productName", is("Santoor")));
		resultActions.andExpect(jsonPath("$.description").value("Baby soap"));
		resultActions.andExpect(jsonPath("$.price").value(43.50));
    }
    @Test
    public void testCreateProduct_400() throws Exception {
    	ProductVO productVO = new ProductVO();
    	Mockito.when(productService.createProduct(Mockito.any(ProductVO.class))).thenReturn(productVO);
    	String customerJSON = mapper.writeValueAsString(productVO);
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/products")
				.accept(MediaType.APPLICATION_JSON)
				.content(customerJSON).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isBadRequest());
    }
    public List<ProductVO> buildProducts(){
    	ProductVO productVO1 = new ProductVO(10L,"Santoor","Baby soap",43.50);
    	ProductVO productVO2 = new ProductVO(null,"Liril","women soap",44.50);
    	List<ProductVO> products = new ArrayList<>();
    	products.add(productVO1);
    	products.add(productVO2);
    	return products;
    }

   
}
