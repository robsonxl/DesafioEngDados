package com.engDados.service;

import org.apache.spark.api.java.JavaRDD;

public interface KennedySpaceDataService {
	
	public JavaRDD<String> loadData();

}
