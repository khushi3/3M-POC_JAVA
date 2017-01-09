package com.mmm.monitor.esclient.services;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.mmm.monitor.esclient.controller.AbstractBO;
import com.mmm.monitor.esclient.exception.ValidationException;
import com.mmm.monitor.esclient.response.ElasticResponseData;
import com.mmm.monitor.esclient.response.HealthResponse;
import com.mmm.monitor.esclient.response.StatusChartResponse;
import com.mmm.monitor.esclient.util.BOFactory;

@Path("/elk")
public class ElasticResource {

	BOFactory boFactory = null;
	
	private final String DEF_SIZE = "1500"; // read this from config. file later
	private final String DEF_START = "0";
	
	/**
	 * Service to retrieve data from Elastic search for given appKey, given 'size' of records starting from a given value 
	 * Default is to search 1500 records starting from 0
	 * 
	 * @param appKey - Path param - from this appKey, we determine ES index
	 * @param size - Optional Query param - no. of records to limit, default is 1500
	 * @param from - Optional Query param - position of record to start search from, default is 0
	 * 
	 * @return - Customized response of ES data  
	 */
	@GET
	@Path("search/{appKey}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ElasticResponseData getElasticResponse(
			@PathParam("appKey") String appKey, 
			@QueryParam("size") @DefaultValue(DEF_SIZE) int size,
			@QueryParam("from") @DefaultValue(DEF_START) int from){
		
		return getAppBO(appKey).getElasticSearchResponse(from, size);
	}

	/**
	 * Service to retrieve the ElasticSearch cluster Health information
	 * 
	 * @return - Customized Health response  
	 */
	@GET
	@Path("clusterHealth")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public HealthResponse getclusterHealthResponse(){
		return getBOFactory().getESHealthBO().getClusterHealthResponse();
	}
	
	//TODO the below chart service need to be re-factored, in future based on diff. req. for charts 
	/**
	 * Service to get the ESData statistics based on "statusCode" column for chart purposes
	 * 
	 * @return - data containing statistics of "statusCode" field 
	 */
	@GET
	@Path("statusChart")
	@Produces({ MediaType.APPLICATION_JSON })
	public StatusChartResponse getStatusChartResponse(){
		return getBOFactory().getEncompass360BO().getStatusChartResponse();
	}
	
	private AbstractBO getAppBO(String appKey){
		AbstractBO bo = getBOFactory().getBOByAppKey(appKey);
		if(bo == null){
			throw new ValidationException("Invalid appkey !!!");
		}
		return bo;
	}
	
	private BOFactory getBOFactory(){
		if(boFactory == null){
			boFactory = new BOFactory();
		}
		return boFactory;
	}
}