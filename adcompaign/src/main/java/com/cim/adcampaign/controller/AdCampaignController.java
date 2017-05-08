package com.cim.adcampaign.controller;

import java.net.BindException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cim.adcampaign.model.AdCampaign;
import com.cim.adcampaign.model.AdCampaignRequestDTO;
import com.cim.adcampaign.service.AdCampaignService;
import com.cim.adcampaign.validator.AdCampaignValidator;

@RestController
public class AdCampaignController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AdCampaignController.class);

	@Autowired
	AdCampaignService service;

	@Autowired
	AdCampaignValidator validator;

	@RequestMapping(value = "/ad", method = RequestMethod.POST)
	public boolean saveAdCampaign(@RequestBody AdCampaignRequestDTO requestDto, BindingResult result)
			throws BindException {

		LOGGER.info("received request to save adCampaingn {}", requestDto);

		validator.validate(requestDto, result);

		if (result.hasErrors()) {
			throw new BindException();
		}

		boolean isSaved = service.saveAdCampaign(requestDto);

		return isSaved;
	}

	@RequestMapping(value = "/ad/{partnerId}", method = RequestMethod.GET)
	public List<AdCampaign> getAdsByPartnerId(@PathVariable String partnerId) {

		LOGGER.info("received request to get adCampaingn for partnerId{}", partnerId);

		validator.validatePartnerId(partnerId);

		final List<AdCampaign> adCampaignByPartnerId = service.getAdCampaigns(partnerId);

		return adCampaignByPartnerId;
	}

	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public List<AdCampaign> getAllActiveAdCampaigns() {

		final List<AdCampaign> adCampaignByPartnerId = service.getAdCampaigns(null);

		return adCampaignByPartnerId;
	}

}
