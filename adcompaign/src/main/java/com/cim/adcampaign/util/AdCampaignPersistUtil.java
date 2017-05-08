package com.cim.adcampaign.util;

import java.util.ArrayList;
import java.util.List;

import com.cim.adcampaign.model.AdCampaign;

public class AdCampaignPersistUtil {

	private static List<AdCampaign> persistedAdsList = null;
	
	
	public static List<AdCampaign> getPersistedList(){
		
		if(persistedAdsList == null){
			persistedAdsList = new ArrayList<AdCampaign>();
		}
		
		return persistedAdsList;
	}
	
}
