package com.mmm.monitor.esclient.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;

/**
 * Utility class to do all ElasticSearch client operations
 * 
 * @author domap
 * @author RamuV 
 *
 */
public class ElasticSearchClientUtil {
	
	final static Logger logger = LogManager.getRootLogger();
	
	ESClientBuilder esClientBuilder = null;
	Client esClient = null;
	
	/**
	 * Method which gets data from ElasticClient by a given Index - this method retrieves only data between specified range   
	 * 
	 * @param index - name of index to search from
	 * @param from - start position of search
	 * @param size - no. of records to fetch
	 * 
	 * @return SearchResponse - response returned from ElasticSearch
	 */
	public SearchResponse getElasticDataByIndexAndSize(String index, int from, int size){
		logger.info("Retrieving Elastic data- " + size + " records starting from "+ from + " for index : " + index);
		
		try{
			//Settings settings = Settings.builder().put("cluster.name", "my-application").build();
			esClient = getESClientBuilder().getClient();
			//return esClient.prepareSearch().get();// this default operation gets only 10 records
			return esClient.prepareSearch().
					setFrom(from).setSize(size).setExplain(true).get();			
						
		}catch(Exception e){
			logger.error("Error occurred while getting elastic search data : fetching  "+ size + " records starting from "
						+ from + " for index : " + index + " .Error is - " + e.getMessage());
			return null;
		}
	}

	/**
	 * Method which gets the ElasticSearch cluster health info. 
	 * 
	 * @return ClusterHealthResponse - Health response returned by Elastic Search
	 */
	public ClusterHealthResponse getClusterHealth() {
		logger.info("Retrieving the Elastci Cluster Health information.");
		try {
			esClient = esClientBuilder.getClient();
			return esClient.admin().cluster().prepareHealth().get();

		} catch (Exception e) {
			logger.error("Error occurred while getting cluster health information : " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Helper method to return a single ESClientBuilder object
	 * 
	 * @return ESClientBuilder
	 */
	private ESClientBuilder getESClientBuilder(){
		if(esClientBuilder == null){
			esClientBuilder = new ESClientBuilder();	
		}
		return esClientBuilder;
	}
	
}
