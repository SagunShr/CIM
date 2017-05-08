package com.cim.adcampaign.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdCampaignRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("partner_id")
	private String partnerId;
	
	private Integer duration;
	
	@JsonProperty("ad_content")
	private String adContent;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	@Override
	public String toString() {
		return "AdCampaignRequestDTO [partnerId=" + partnerId + ", duration=" + duration + ", adContent=" + adContent
				+ "]";
	}

}
