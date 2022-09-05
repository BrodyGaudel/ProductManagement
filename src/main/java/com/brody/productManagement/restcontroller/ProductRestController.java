package com.brody.productManagement.restcontroller;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brody.productManagement.dto.ProductRequestDTO;
import com.brody.productManagement.dto.ProductResponseDTO;
import com.brody.productManagement.exception.ProductNotFoundException;
import com.brody.productManagement.service.ProductService;



@RestController
@RequestMapping("/product")
@CrossOrigin(origins="*")
public class ProductRestController {
	
	private ProductService productService;

	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping(path="/save")
	@ResponseBody
	ProductResponseDTO addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
		
		return productService.addProduct(productRequestDTO);
	}
	
	@GetMapping(path="/findById/{id}")
	@ResponseBody
	ProductResponseDTO getProductById(@PathVariable String id) throws ProductNotFoundException{
		
		return productService.getProductById(id);
		
	}
	
	@GetMapping(path="/findByBarecode/{codebare}")
	@ResponseBody
	List<ProductResponseDTO> getProductByCodebare(@PathVariable(name="codebare") String codebare) throws ProductNotFoundException{
		
		return productService.getProductByCodebare(codebare);
	}
	
	@GetMapping(path="/findAll")
	@ResponseBody
	List<ProductResponseDTO> getAllProducts(){
		
		return productService.getAllProducts();
	}
	
	@GetMapping(path="/findByName/{name}")
	@ResponseBody
	List<ProductResponseDTO> getProductsByName(@PathVariable(name="name") String name){
		
		return productService.getProductsByName(name);
	}
	
	@GetMapping(path="/findByCategoryName/{name}")
	@ResponseBody
	List<ProductResponseDTO> getProductsByCategoryName(@PathVariable(name="name") String name){
		
		return productService.getProductsByCategoryName(name);
	}
	
	@GetMapping(path="/findByCategoryId/{id}")
	@ResponseBody
	List<ProductResponseDTO> getProductsCategoryId(@PathVariable(name="id") String id){
		
		return productService.getProductsByCategoryId(id);
	}
	
	@GetMapping(path="/findByDescription/{description}")
	@ResponseBody
	List<ProductResponseDTO> getProductsByDescription(@PathVariable(name="description") String description){
		
		return productService.getProductsByDescription(description);
	}
	
	@GetMapping(path="/findByNameOrDescription/{word}")
	@ResponseBody
	List<ProductResponseDTO> getProductsByNameOrDescription(@PathVariable(name="word") String word){
		
		return productService.getProductsByNameOrDescription(word);
	}
	
	@GetMapping(path="/findByMinPriceAndMaxPrice/{min}/{max}")
	@ResponseBody
	List<ProductResponseDTO> getProductsByPrice(@PathVariable(name="min") Double min, @PathVariable(name="max") Double max){
		
		return productService.getProductsByPrice(min,max);
		
	}
	
	@PutMapping(path="/update")
	@ResponseBody
	ProductResponseDTO updateProduct(@RequestBody ProductRequestDTO productRequestDTO) {
		
		return productService.updateProduct(productRequestDTO);
		
	}
	
	@DeleteMapping(path="/deleteById/{id}")
	void deleteProductById(@PathVariable String id) {
		
		productService.deleteProductById(id);
	}
	
	@DeleteMapping(path="/deleteAll")
	void deleteAllProducts() {
		
		productService.deleteAllProducts();
	}
	
	@PostMapping("/upload") 
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		try {
			file.transferTo( new File("C:\\upload\\" + fileName));
			return ResponseEntity.ok("File uploaded successfully.");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	

}
