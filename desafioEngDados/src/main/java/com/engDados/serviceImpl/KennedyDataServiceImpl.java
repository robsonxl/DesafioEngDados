package com.engDados.serviceImpl;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.engDados.service.KennedySpaceDataService;

@Service
public class KennedyDataServiceImpl implements KennedySpaceDataService {
	
	
	@Value("${resourceLoader.file.location}")
	private String path;
	
	@Autowired 
	JavaSparkContext sc;
	
	private JavaRDD<String> dataLoaded;

	@Override
	public JavaRDD<String> loadData() {
		if(this.dataLoaded==null) {
			JavaRDD<String> data =  sc.textFile(path);
			this.dataLoaded = data.map(s -> (s.replaceAll("[\\[\\]]", "")).replaceAll("\\-\\s", "").replaceAll("\"","")).cache();
			return dataLoaded;
		}
		return this.dataLoaded;
	}
}
