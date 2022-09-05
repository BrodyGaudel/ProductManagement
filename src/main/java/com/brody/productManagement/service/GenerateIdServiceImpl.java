package com.brody.productManagement.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.brody.productManagement.entity.CompterProduct;
import com.brody.productManagement.entity.CompteurCategory;
import com.brody.productManagement.repository.CompterCategoryRepository;
import com.brody.productManagement.repository.CompterProductRepository;

@Service
public class GenerateIdServiceImpl implements GenerateIdService{
	
	private static final Logger log = Logger.getLogger(GenerateIdServiceImpl.class);
	private static final String ID_GENERATED = "Id Generated";
	private CompterProductRepository compterProductRepository;
	private CompterCategoryRepository compterCategoryRepository;
	
	
	
	public GenerateIdServiceImpl(CompterProductRepository compterProductRepository,
			CompterCategoryRepository compterCategoryRepository) {

		this.compterProductRepository = compterProductRepository;
		this.compterCategoryRepository = compterCategoryRepository;
	}

	@Override
	public String generateProductId() {
		
		log.info("In generateProductId()");
		try {
			List<CompterProduct> compters = compterProductRepository.findAll();
			if(compters.isEmpty()) {
				CompterProduct compter = new CompterProduct((long) 999);
				log.info(ID_GENERATED);
				return generateForProduct(compter);
			}
			else {
				CompterProduct compter = compters.get(compters.size()-1);
				compterProductRepository.deleteById(compter.getId());
				log.info(ID_GENERATED); 
				return generateForProduct(compter);
			}	
		}catch(Exception e) {
			log.info("In Not Generated: Exception :"+e);
			return null;
		}
	}

	@Override
	public String generateCategoryId() {
		log.info("In generateProductId()");
		try {
			List<CompteurCategory> compters = compterCategoryRepository.findAll();
			if(compters.isEmpty()) {
				CompteurCategory compter = new CompteurCategory((long) 999);
				log.info(ID_GENERATED);
				return generateForCategory(compter);
			}
			else {
				CompteurCategory compter = compters.get(compters.size()-1);
				compterCategoryRepository.deleteById(compter.getId());
				log.info(ID_GENERATED); 
				return generateForCategory(compter);
			}	
		}catch(Exception e) {
			log.info("In Not Generated: Exception :"+e);
			return null;
		}
	}
	
	private String generateForProduct(CompterProduct compter) {
		Long cpt = compter.getId()+1;
		CompterProduct compt = new CompterProduct(cpt);
		compterProductRepository.save(compt);
		String head = "PROD";
		String body = cpt.toString();
		return head+body;
	}
	
	private String generateForCategory(CompteurCategory compter) {
		Long cpt = compter.getId()+1;
		CompteurCategory compt = new CompteurCategory(cpt);
		compterCategoryRepository.save(compt);
		String head = "CAT";
		String body = cpt.toString();
		return head+body;
	}

}
