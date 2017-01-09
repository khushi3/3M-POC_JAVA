package com.mmm.monitor.esclient.util;

import java.util.HashMap;
import java.util.Map;

import com.mmm.monitor.esclient.controller.AbstractBO;
import com.mmm.monitor.esclient.controller.ESHealthBO;
import com.mmm.monitor.esclient.controller.Encompass360BO;

/**
 * Factory class to create BO class objects (We create only one object per class)
 * 
 * @author RamuV
 *
 */
public class BOFactory {

	static Map<String, AbstractBO> mapOfBO = null;
	
	private ESHealthBO esHealthBO = null;
	private Encompass360BO encompass360BO = null;
	
	/**
	 * Method to return BO class based on the appKey, 
	 * we've one BO class for each app.(identified by appkey)
	 * 
	 * @param appKey
	 * @return AbstractBO
	 */
	public AbstractBO getBOByAppKey(String appKey) {

		if(mapOfBO == null){
			mapOfBO = new HashMap<String, AbstractBO>();
			mapOfBO.put("enc360", new Encompass360BO());
			// mapOfBO.put("sts" , new STSBO());
			// mapOfBO.put("codeAssist" , new CodeAssistBO());
		}
		
		return mapOfBO.get(appKey);
	}

	public ESHealthBO getESHealthBO() {
		if(esHealthBO == null){
			esHealthBO = new ESHealthBO();
		}
		return esHealthBO;
	}

	public Encompass360BO getEncompass360BO(){
		if(encompass360BO == null){
			encompass360BO = new Encompass360BO();
		}
		return encompass360BO;
	}
	
}
