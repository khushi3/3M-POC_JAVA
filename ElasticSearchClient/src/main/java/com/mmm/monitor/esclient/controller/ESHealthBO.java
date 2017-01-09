package com.mmm.monitor.esclient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;

import com.mmm.monitor.esclient.response.HealthResponse;
import com.mmm.monitor.esclient.util.ElasticSearchClientUtil;

/**
 * BO class to get Elastic Search Cluster Health 
 * 
 * @author domanp
 *
 */
public class ESHealthBO {
	
	final static Logger logger = LogManager.getLogger(ESHealthBO.class);

	ElasticSearchClientUtil esClientUtil = null; 
			
	/**
	 * Method to get Elastic Search CLuster Health info.
	 *  
	 * @return HealthResponse
	 */
	public HealthResponse getClusterHealthResponse() {
		ClusterHealthResponse response = getElasticSearchClientUtil().getClusterHealth();
		return convertToHealthResponse(response);
	}

	/**
	 * Helper method to convert ClusterHealthResponse from ElastciSearch to HealthResponse object 
	 * 
	 * @param healths -- response received from ElasticSearch 
	 * @return HealthResponse - response sent to caller
	 */
	private HealthResponse convertToHealthResponse(ClusterHealthResponse healthResp) {
		logger.info("converting HealthResponse received from Elastic Search client.");
		HealthResponse healthResponse = new HealthResponse();
		
		healthResponse.setClusterName(healthResp.getClusterName());
		healthResponse.setNumberOfDataNodes(healthResp.getNumberOfDataNodes());
		healthResponse.setNumberOfNodes(healthResp.getNumberOfNodes());
		healthResponse.setStatus(healthResp.getStatus()+"");	
		healthResponse.setActiveShards(healthResp.getActiveShards());
		
		return healthResponse;
	}

	/** 
	 * Helper method to return a ElasticSearchClientUtil object - we maintain only one object 
	 * 
	 * @return ElasticSearchClientUtil object
	 */
	private ElasticSearchClientUtil getElasticSearchClientUtil(){
		if(esClientUtil == null){
			esClientUtil = new ElasticSearchClientUtil();
		}
		return esClientUtil;
	}
}
