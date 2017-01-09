package com.mmm.monitor.esclient.util;

import static com.mmm.monitor.esclient.util.Config.HOST;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * Utility class for getting ElassticSearch Java client object
 * 
 * As of now, we are creating single client object, need to see whether we need to make a pool of client objects
 *  
 * @author RamuV
 *
 */
public class ESClientBuilder {


	TransportClient esClient = null;
	
	/**
	 * Method to create and return the client object to connect to ElasticSearchClient
	 * will return null, if unable to create a client
	 * 
	 * @return org.elasticsearch.client.Client
	 * 
	 */
	public Client getClient() {
		
		if(esClient == null){
			try {
				esClient = new PreBuiltTransportClient(Settings.EMPTY).
						addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST), 9300));
			} catch (UnknownHostException e) {
				System.out.println("Error occurred while creating ES Client : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return esClient;
	}
}
