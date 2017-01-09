package com.mmm.monitor.esclient.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mmm.monitor.esclient.model.ElasticSearchModel;

/**
 * Model class for Response of Elastic Search results
 * 
 * @author domanp
 *
 */
@XmlRootElement(name = "Response")
public class ElasticResponseData {
	
	public List<ElasticSearchModel> elasticSearchValues;

	public void setElasticSearchValues(List<ElasticSearchModel> elasticSearchValues) {
		this.elasticSearchValues = elasticSearchValues;
	}		

}
