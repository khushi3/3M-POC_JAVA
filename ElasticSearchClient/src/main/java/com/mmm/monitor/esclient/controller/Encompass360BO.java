package com.mmm.monitor.esclient.controller;


import static com.mmm.monitor.esclient.util.Config.SIZE;
import static com.mmm.monitor.esclient.util.Config.START_FROM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

import com.mmm.monitor.esclient.model.ElasticSearchModel;
import com.mmm.monitor.esclient.model.StatusChartModel;
import com.mmm.monitor.esclient.response.ElasticResponseData;
import com.mmm.monitor.esclient.response.StatusChartResponse;
import com.mmm.monitor.esclient.util.Config;
import com.mmm.monitor.esclient.util.ElasticSearchClientUtil;

/**
 * BO class for operations specific to Encompass360 application
 * 
 * @author domanp
 * @author RamuV
 *
 */
public class Encompass360BO extends AbstractBO {

	final static Logger logger = LogManager.getLogger(Encompass360BO.class);
	
	private static final String ENC_360_INDEX_NAME = "accesslogindex";
	
	private static final String KEY_MESSAGE = "message"; 
	private static final String KEY_HOST = "host";
	private static final String KEY_CLIENT = "clientip";
	private static final String KEY_REQUEST = "request";
	private static final String KEY_VERB = "verb";
	private static final String KEY_RESPONSE = "response";
			
	ElasticSearchClientUtil esClientUtil = null; 
	
	/**
	 * Method to return app. specific index name
	 */
	public String getIndexName(){
		return ENC_360_INDEX_NAME;
	}
	
	/**
	 * Method to convert Elastic Search response to customized response 
	 */
	public ElasticResponseData convertESResponseToESRespData(SearchResponse sResponse) {
		logger.info("converting ElasticSearch response received from Elastic Search client to ElasticResponseData.");
		
		ElasticResponseData response = new ElasticResponseData();

		if (sResponse != null) {
			List<ElasticSearchModel> model = new ArrayList<ElasticSearchModel>();
			ElasticSearchModel res = null;
			
			for (SearchHit hits : sResponse.getHits().getHits()) {
				res = new ElasticSearchModel();

				for (String key : hits.sourceAsMap().keySet()) {
					if(KEY_MESSAGE.equalsIgnoreCase(key)){
						res.setMessage(hits.sourceAsMap().get(key) + "");
					}
					if(KEY_HOST.equalsIgnoreCase(key)){
						res.setHost(hits.sourceAsMap().get(key) + "");
					}
					if(KEY_CLIENT.equalsIgnoreCase(key)){
						res.setClient(hits.sourceAsMap().get(key) + "");
					}
					if(KEY_REQUEST.equalsIgnoreCase(key)){
						res.setRequest(hits.sourceAsMap().get(key) + "");
					}
					if(KEY_VERB.equalsIgnoreCase(key)){
						res.setMethod(hits.sourceAsMap().get(key) + "");
					}
					if(KEY_RESPONSE.equalsIgnoreCase(key)){
						res.setStatus(hits.sourceAsMap().get(key) + "");
					}
				}
				model.add(res);
			}

			response.setElasticSearchValues(model);
		}

		return response;
	}

	/***********************************/
	//TODO refactoring candidate - based on requirements, either generalize or move to separate class
	/**
	 * Method to retrieve ES data statistics based on "statusCode" column
	 * 
	 * @return StatusChartResponse
	 */
	public StatusChartResponse getStatusChartResponse() {
		SearchResponse sResponse = getElasticSearchClientUtil().getElasticDataByIndexAndSize(getIndexName(), START_FROM, SIZE);
		return convertToStatusChartObject(sResponse);
	}

	/**
	 * Helper method to convert do operations on Elastic response to retrieve statistics based on "statusCode" column 
	 * 
	 * @param sResponse
	 * @return
	 */
	private StatusChartResponse convertToStatusChartObject(SearchResponse sResponse) {
		logger.info("converting ElasticSearch response to get stats based on status code.");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		int totalCount = 0;
		int order = 0;
		float percent;
		StatusChartResponse response = new StatusChartResponse();

		if (sResponse != null) {
						
			for (SearchHit hits : sResponse.getHits().getHits()) {											
				
				for (String key : hits.sourceAsMap().keySet()) {
					if (key.equals("response")) {
						
						totalCount = totalCount+1;
						
						if(map.containsKey(hits.sourceAsMap().get(key))){
							map.put(hits.sourceAsMap().get(key)+"", map.get(hits.sourceAsMap().get(key))+1);
						}else{
							map.put(hits.sourceAsMap().get(key)+"", 1);
						}
					}
				}
			}
			
			List<StatusChartModel> statusModelList = new ArrayList<StatusChartModel>();
			
			for(String key : map.keySet()){
				
				StatusChartModel res = new StatusChartModel();	
				if(order<8){
					res.setLabel("Status "+key);
					res.setValue(map.get(key)+"");
					res.setOrder(order+"");
					res.setColor(Config.Colors[order]);
					res.setHighlight(Config.Colors[order]);
					percent = ((map.get(key)/(float) totalCount)*100);
					//percent = Math.round(percent,2);
					//percent = 
					//res.setPercentage(percent+"");
					res.setPercentage(String.format("%.2f", percent));
					statusModelList.add(res);					
				}
				order++;
			}
			response.setTotal(totalCount+"");
			response.setStatusChartValues(statusModelList);
		}

		return response;
	}

}
