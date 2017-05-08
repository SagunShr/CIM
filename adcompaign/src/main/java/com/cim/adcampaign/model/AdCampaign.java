package com.cim.adcampaign.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AdCampaign implements Serializable {

	private static final long serialVersionUID = 1L;

	private String partnerId;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date expiresOn;

	private String adContent;

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public Date getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	@Override
	public String toString() {
		return "AdCampaign [partnerId=" + partnerId + ", expiresOn=" + expiresOn + ", adContent=" + adContent + "]";
	}

}
