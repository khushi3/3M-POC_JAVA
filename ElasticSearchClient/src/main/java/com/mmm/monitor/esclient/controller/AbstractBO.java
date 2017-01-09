package com.mmm.monitor.esclient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;

import com.mmm.monitor.esclient.response.ElasticResponseData;
import com.mmm.monitor.esclient.util.ElasticSearchClientUtil;

/**
 * Base class for all app. specific BO classes (we should have one BO class for one app.)
 * This class has common code to retrieve data from Elastic Search based on index and 
 * sub-classes must provide indexName and also implement the conversion logic from ElasticResponse to Customized response 
 * 
 * @author RamuV
 *
 */
public abstract class AbstractBO {

	final static Logger logger = LogManager.getLogger(AbstractBO.class);
	
	ElasticSearchClientUtil esClientUtil = null; 
	
	/**
	 * Method to convert Elastic Search response to customized response.
	 * 
	 * @param SearchResponse - response returned by ElasticSearch client
	 */
	public abstract ElasticResponseData convertESResponseToESRespData(SearchResponse esSearchResponse);
	
	/**
	 * Method to return app. specific index name
	 */
	public abstract String getIndexName();
	
	
	/**
	 * Method to retrieve all records from Elastic search for a given index
	 *  
	 * @return ElasticResponseData - Customized response from ElasticSearch 
	 *//*
	public ElasticResponseData getAllElasticSearchResponse() {
		
		SearchResponse sResponse = getElasticSearchClientUtil().
				getAllElasticDataByIndex(getIndexName());
		return convertESResponseToESRespData(sResponse);
	}*/

	/**
	 * Method to retrieve data from Elastic search for a given index and 
	 * size (no. of) records from a "start" position   
	 *  
	 * @return ElasticResponseData - Customized response from ElasticSearch 
	 */
	public ElasticResponseData getElasticSearchResponse(int from, int size) {
		
		SearchResponse sResponse = getElasticSearchClientUtil().
				getElasticDataByIndexAndSize(getIndexName(), from , size);
		return convertESResponseToESRespData(sResponse);
	}

	
	/** 
	 * Helper method to return a ElasticSearchClientUtil object - we maintain only one object 
	 * 
	 * @return ElasticSearchClientUtil object
	 */
	public ElasticSearchClientUtil getElasticSearchClientUtil(){
		if(esClientUtil == null){
			esClientUtil = new ElasticSearchClientUtil();
		}
		return esClientUtil;
	}
}
