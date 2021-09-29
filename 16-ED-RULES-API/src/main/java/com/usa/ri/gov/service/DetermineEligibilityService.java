package com.usa.ri.gov.service;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.usa.ri.gov.bindings.IndvInfo;
import com.usa.ri.gov.bindings.PlanInfo;

@Service("eligService")
public class DetermineEligibilityService {

	public PlanInfo determineEligibility(IndvInfo indvInfo) {
		String planName = indvInfo.getPlanName();
		String clzName = "com.usa.ri.gov.service."+planName+"RulesExecutor";
		PlanInfo planInfo = null;
		try {
			Class<?> clz = Class.forName(clzName);
			
			Method method = clz.getDeclaredMethod("executeRules", IndvInfo.class);
			
			Object object = clz.newInstance();
			
			planInfo =  (PlanInfo) method.invoke(object, indvInfo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return planInfo;
	}
}
