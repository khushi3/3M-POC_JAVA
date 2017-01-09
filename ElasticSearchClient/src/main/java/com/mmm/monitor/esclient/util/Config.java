package com.mmm.monitor.esclient.util;

public class Config {

	//TODO move this host to props file
	//public final static String HOST = "localhost";
	public final static String HOST = "172.16.103.25";
	//public final static String ELASTIC_INDEX = "accesslogindex";
	public final static Integer SIZE = 1500;
	public final static Long ID_START_FROM = 1000L;
	public final static int START_FROM = 0;

	public final static String Colors[] = { 
			"#4CAF50",
			"#3c4eb9", 
			"#E74C3C",
			"#4A235A", 
			"#F7F9F9",			 
			"#1b70ef",
			"#00abff", 
			"#40daf1",
			"#ffffff"
			};

}
