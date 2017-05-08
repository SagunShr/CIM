package com.cim.adcampaign.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cim.adcampaign.model.AdCampaign;
import com.cim.adcampaign.model.AdCampaignRequestDTO;
import com.cim.adcampaign.util.AdCampaignPersistUtil;

@Service
public class AdCampaignService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AdCampaignService.class);

	public Boolean saveAdCampaign(final AdCampaignRequestDTO requestDTO){
		
		LOGGER.info("saving ad campaign for partnerId {} ", requestDTO.getPartnerId());
		
		final List<AdCampaign> persistedAdsList = AdCampaignPersistUtil.getPersistedList();
		
		//removing already expired ad for given partner and ensuring one partner should have always one ad
		if(!persistedAdsList.isEmpty()){
			
			Iterator<AdCampaign> itr = persistedAdsList.iterator();
			
			while (itr.hasNext()) {
				AdCampaign adCampaign = (AdCampaign) itr.next();
				if(adCampaign.getPartnerId().equalsIgnoreCase(requestDTO.getPartnerId()) && adCampaign.getExpiresOn().before(new Date())){
					itr.remove();
				}
			}
		}
		
		List<AdCampaign> afterCampaignList  = AdCampaignPersistUtil.getPersistedList();
		afterCampaignList.add(convertToAdCampaign(requestDTO));
		
		return true;
	}
	
	public List<AdCampaign> getAdCampaigns(final String partnerId){
		
		final List<AdCampaign> persistedAdsList = AdCampaignPersistUtil.getPersistedList();
		
		if(partnerId == null){
			return persistedAdsList;
		}
		
		List<AdCampaign> persitedAdsListByPartnerId = persistedAdsList.stream().filter( ad -> ad.getPartnerId().equalsIgnoreCase(partnerId)).collect(Collectors.toList());
		
		return persitedAdsListByPartnerId;
	}
	
	private AdCampaign convertToAdCampaign(final AdCampaignRequestDTO requestDTO){
		
		AdCampaign adCampaign = new AdCampaign();
		adCampaign.setPartnerId(requestDTO.getPartnerId());
		adCampaign.setExpiresOn(formatDurationtonValidPeriod(requestDTO.getDuration()));
		adCampaign.setAdContent(requestDTO.getAdContent());
		
		return adCampaign;
	}
	
	private Date formatDurationtonValidPeriod(final Integer duration){
		Calendar businessDate = Calendar.getInstance();
		businessDate.setTimeInMillis(businessDate.getTimeInMillis()+ (duration*1000));
		
		return businessDate.getTime();
	}
	
}
