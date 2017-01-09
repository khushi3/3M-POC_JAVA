package com.mmm.monitor.esclient.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mmm.monitor.esclient.model.StatusChartModel;

/**
 * Response object containing ES data stats by statusCode
 * 
 * @author domanp
 *
 */
@XmlRootElement(name = "Response")
public class StatusChartResponse {
	
	public String total;
	
	public List<StatusChartModel> statusChartValues;

	public void setTotal(String total) {
		this.total = total;
	}

	public void setStatusChartValues(List<StatusChartModel> statusChartValues) {
		this.statusChartValues = statusChartValues;
	}

	
	
}
