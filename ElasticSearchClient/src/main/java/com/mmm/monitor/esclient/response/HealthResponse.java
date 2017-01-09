package com.mmm.monitor.esclient.response;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model object for ES cluster health information response
 * 
 * @author domanp
 *
 */
@XmlRootElement(name = "Response")
public class HealthResponse {

	public String clusterName;
	public int numberOfDataNodes;
	public int numberOfNodes;
	public String status;
	public int activeShards;

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public void setNumberOfDataNodes(int numberOfDataNodes) {
		this.numberOfDataNodes = numberOfDataNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setActiveShards(int activeShards) {
		this.activeShards = activeShards;
	}

}
