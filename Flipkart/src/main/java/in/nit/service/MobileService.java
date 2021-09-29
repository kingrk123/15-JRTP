package in.nit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.nit.binding.SearchForm;
import in.nit.model.Mobile;
import in.nit.repo.MobileRepository;

@Service
public class MobileService {

	@Autowired
	private MobileRepository mobRepo;

	public List<Mobile> fetchAllMobiles() {
		return mobRepo.findAll();
	}
	
	
	public List<String> getBrandNames() {
		return mobRepo.findByBrandNames();
	}
	
	public List<Integer> getRam() {
		return mobRepo.findByRam();
	}
	
	public List<Integer> getRating() {
		return mobRepo.findByRating();
	}
	
	public List<Mobile> filterProducts(SearchForm formObj) {
		Mobile entity = new Mobile();
		
		if (null != formObj.getBrand() && !"".equals(formObj.getBrand())) {
			entity.setBrand(formObj.getBrand());
		}
		entity.setRating(formObj.getRating());
		entity.setRam(formObj.getRam());
		
		Example<Mobile> example = Example.of(entity);
		
		List<Mobile> findAll = mobRepo.findAll(example);
		
		if (null != formObj.getPrice()) {
			return filterMobilesByPrice(findAll, formObj.getPrice());
		}
		
		return findAll;
		
	}


	private List<Mobile> filterMobilesByPrice(List<Mobile> mobiles, Double price) {
		
		List<Mobile> filteredList = new ArrayList<>();
		
		mobiles.forEach(mobile -> {
			if (mobile.getPrice()< price) {
				filteredList.add(mobile);
			}
		});
		return filteredList;
	}
}
