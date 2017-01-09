package com.mmm.monitor.esclient.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class to hold ElasticSearch results 
 * This need to be re-factored using inheritance hierarchy, based on common attributes needed by different apps. 
 * 
 * @author domanp
 *
 */
@XmlRootElement()
public class ElasticSearchModel {

	public String index;
	public String type;
	public String id;
	public String score;
	public String path;
	public String timestamp;
	public String version;
	public String host;
	public String message;
	public String client;
	public String request;
	public String method;
	public String status;

	public void setIndex(String index) {
		this.index = index;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
