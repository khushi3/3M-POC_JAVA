package com.mmm.monitor.esclient.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model to represent the ES stats by Status code column
 * This need to be re-factored in case of more req. using an inheritance hierarchy 
 * 
 * @author domanp
 *
 */
@XmlRootElement()
public class StatusChartModel {
	
	public String value;
	public String color;
	public String highlight;
	public String label;
	public String percentage;
	public String order;

	public void setValue(String value) {
		this.value = value;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
