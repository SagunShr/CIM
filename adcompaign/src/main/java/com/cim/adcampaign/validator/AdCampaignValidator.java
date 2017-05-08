package com.cim.adcampaign.validator;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cim.adcampaign.model.AdCampaign;
import com.cim.adcampaign.model.AdCampaignRequestDTO;
import com.cim.adcampaign.util.AdCampaignPersistUtil;

@Component
public class AdCampaignValidator implements Validator{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AdCampaignValidator.class);

	public boolean supports(Class<?> clazz) {
		return AdCampaignRequestDTO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		
		AdCampaignRequestDTO requestDto = (AdCampaignRequestDTO) target;
		
		LOGGER.info("validating request {}", requestDto);
		
		if(requestDto.getPartnerId() == null || requestDto.getPartnerId().trim().isEmpty()){
			errors.reject("partner_id", "PartnerId should not be empty");
		}
		
		if(requestDto.getDuration() == null || requestDto.getDuration() <=0){
			errors.reject("Duration", "Invalid value provided for duration");
		}
		
		if(requestDto.getAdContent() == null || requestDto.getAdContent().trim().isEmpty()){
			errors.reject("ad_content", "AdContent should not be empty");
		}
		
		if(!errors.hasErrors()){
			
			final List<AdCampaign> persistedAdsList = AdCampaignPersistUtil.getPersistedList();
			AdCampaign adCampaign = persistedAdsList.stream().filter( p -> p.getPartnerId().equalsIgnoreCase(requestDto.getPartnerId())).findFirst().orElse(null);
			
			if(adCampaign != null && adCampaign.getExpiresOn().after(new Date())){
				errors.reject("invalid-input", "Only one active campaign can exist for a given partner");
			}
			
		}
	}

	public void validatePartnerId(String partnerId){
		
		if(partnerId == null || partnerId.trim().isEmpty()){
			throw new RuntimeException("PartnerId should not be empty");
		}
		
		final List<AdCampaign> persistedAdsList = AdCampaignPersistUtil.getPersistedList();
		boolean isExists = persistedAdsList.stream().filter( p -> p.getPartnerId().equalsIgnoreCase(partnerId)).findFirst().isPresent();
		
		if(!isExists){
			throw new RuntimeException("PartnerId not exists");
		}
	}
}
