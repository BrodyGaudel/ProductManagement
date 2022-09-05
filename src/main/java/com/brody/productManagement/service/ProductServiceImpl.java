package com.brody.productManagement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.brody.productManagement.dto.ProductRequestDTO;
import com.brody.productManagement.dto.ProductResponseDTO;
import com.brody.productManagement.entity.Category;
import com.brody.productManagement.entity.Product;
import com.brody.productManagement.exception.ProductNotFoundException;
import com.brody.productManagement.mapping.Mappers;
import com.brody.productManagement.repository.CategoryRepository;
import com.brody.productManagement.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger log = Logger.getLogger(ProductServiceImpl.class);
	private static final String PRODUCT_NOT_FOUND = "Product(s) Not Found : ";
	private static final String LIST_OF_PRODUCTS_FOUND = "List Of Products Found : ";
	
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private Mappers mappers;
	private GenerateIdService generateIdService;
	
	

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			Mappers mappers, GenerateIdService generateIdService) {
		
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.mappers = mappers;
		this.generateIdService = generateIdService;
	}

	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
		
		try {
			Product product = mappers.fromProductRequestDTO(productRequestDTO);
			String id = generateIdService.generateProductId();
			product.setId(id);
			String idCategory = productRequestDTO.getCategoryId();
			Category c = categoryRepository.findById(idCategory).orElse(null);
			product.setCategory(c);
			Product prod = productRepository.save(product);
			return mappers.fromProduct(prod);
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public ProductResponseDTO getProductById(String id) throws ProductNotFoundException {
		log.info("In getProductById()");
		Product product = productRepository.findById(id)
				.orElseThrow( () -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
		
		log.info("Product Found");
		return mappers.fromProduct(product);
	}

	@Override
	public List<ProductResponseDTO> getProductByCodebare(String codebare) throws ProductNotFoundException {
		log.info("In getProductByCodebare()");
		try {
			List<Product> products = productRepository.findAll();
			List<Product> prods = products.stream()
					.filter(product -> product.getBarcode().equals(codebare))
					.collect(Collectors.toList());
			log.info(LIST_OF_PRODUCTS_FOUND);
			return mappers.fromListOfProduct(prods);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		log.info("In getAllProducts()");
		try {
			List<Product> products = productRepository.findAll();
			log.info("List Of Products Found But Can Be Empty");
			return mappers.fromListOfProduct(products);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductResponseDTO> getProductsByName(String name) {
		log.info("In getProductsByName()");
		try {
			List<Product> products = productRepository.findAll();
			List<Product> prods = products.stream()
					.filter(product -> product.getName().contains(name))
					.collect(Collectors.toList());
			log.info(LIST_OF_PRODUCTS_FOUND);
			return mappers.fromListOfProduct(prods);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductResponseDTO> getProductsByDescription(String description) {
		log.info("In getProductsByDescription()");
		try {
			List<Product> products = productRepository.findAll();
			List<Product> prods = products.stream()
					.filter(product -> product.getDescription().contains(description))
					.collect(Collectors.toList());
			log.info(LIST_OF_PRODUCTS_FOUND);
			return mappers.fromListOfProduct(prods);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductResponseDTO> getProductsByNameOrDescription(String word) {
		log.info("In getProductsByNameOrDescription()");
		try {
			List<ProductResponseDTO> prodsByName =  getProductsByName(word);
			List<ProductResponseDTO> prodsByDescription = getProductsByDescription(word);
			prodsByName.addAll(prodsByDescription);
			log.info(LIST_OF_PRODUCTS_FOUND);
			return prodsByName;
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<ProductResponseDTO> getProductsByPrice(Double minPrice, Double maxPrice) {
		
		log.info("In getProductsByPrice()");
		try {
			List<Product> products = productRepository.findAll();
			List<Product> prods = new ArrayList<>();
			for(Product p: products) {
				Double price = p.getPrice();
				if(price>=minPrice && price<=maxPrice) {
					prods.add(p);
				}
			}
			log.info(LIST_OF_PRODUCTS_FOUND);
			return mappers.fromListOfProduct(prods);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
		
	}

	@Override
	public List<ProductResponseDTO> getProductsByCategoryId(String id) {
		log.info("In getProductsByCategoryId()");
		try {
			List<Product> products = productRepository.findByCategoryId(id);
			log.info(LIST_OF_PRODUCTS_FOUND);
			return mappers.fromListOfProduct(products);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
		
	}

	@Override
	public List<ProductResponseDTO> getProductsByCategoryName(String name) {
		log.info("In getProductsByCategoryName()");
		try {
			
			Category category = categoryRepository.findByName(name);
			List<Product> products = productRepository.findByCategoryId(category.getId());
			
			log.info(LIST_OF_PRODUCTS_FOUND);
			return mappers.fromListOfProduct(products);
		}catch(Exception e) {
			log.error(PRODUCT_NOT_FOUND+e);
			return Collections.emptyList();
		}
	}

	@Override
	public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) {
		log.info("In updateProduct()");
		try {
			Product product = mappers.fromProductRequestDTO(productRequestDTO);
			Product prod = productRepository.save(product);
			log.info("Product updated");
			return mappers.fromProduct(prod);
		}catch(Exception e) {
			log.info(PRODUCT_NOT_FOUND+e);
			return null;
		}
		
	}

	@Override
	public void deleteProductById(String id) {
		log.info("In deleteProduct()");
		try {
			productRepository.deleteById(id);
			log.info("Product Deleted");
		}catch(Exception e) {
			log.error("Product Not Deleted : "+e);
		}	
		
	}

	@Override
	public void deleteAllProducts() {
		log.info("In deleteAllProducts()");
		try {
			productRepository.deleteAll();
			log.info("Products Deleted");
		}catch(Exception e) {
			log.error("Products Not Deleted : "+e);
		}	
		
	}

}
