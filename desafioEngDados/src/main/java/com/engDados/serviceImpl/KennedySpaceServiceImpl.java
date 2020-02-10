package com.engDados.serviceImpl;

import java.util.Comparator;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engDados.constants.KennedySapceConstants;
import com.engDados.dto.HttpStatusByDateDTO;
import com.engDados.dto.QuantityOfDistintsHostsDTO;
import com.engDados.dto.QuantityOfReturnedBytesDTO;
import com.engDados.dto.TopURLStatusByCodeDTO;
import com.engDados.helper.TupleComparator;
import com.engDados.service.KennedySpaceDataService;
import com.engDados.service.KennedySpaceService;

import scala.Tuple2;

@Service
public class KennedySpaceServiceImpl implements KennedySpaceService{


	@Autowired
	KennedySpaceDataService kennedySpaceDataService;
	/**
	 *  Total of request grouped by distincts hosts
	 */
	@Override
	public List<QuantityOfDistintsHostsDTO> getTotalDistincstHostsByHosts() {
		JavaRDD<String> dataFromFile = kennedySpaceDataService.loadData();
		JavaPairRDD<String, Integer> data = dataFromFile
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(" ")[KennedySapceConstants.HOST_NAME], 1));
		JavaPairRDD<String, Integer> dataGroupSummarized = data.reduceByKey((x, y) -> x + y);
		List<Tuple2<String, Integer>> allData = dataGroupSummarized.collect();
		List<QuantityOfDistintsHostsDTO> totalOfDistinctHostsList = QuantityOfDistintsHostsDTO.map(allData);
		return totalOfDistinctHostsList;
	}
	/**
	 *  Total of ditincts hosts .
	 */
	@Override
	public Long getTotalDistincstHosts() {
		JavaRDD<String> dataFromFile = kennedySpaceDataService.loadData();
		return dataFromFile.map(s -> s.split(" ")[KennedySapceConstants.HOST_NAME]) .count(); 
	}
	/**
	 * 
	 */
	@Override
	public JavaRDD<String> getRowsByHttpStatusCode(Integer httpCode) {
		JavaRDD<String> dataFromFile = kennedySpaceDataService.loadData();
		 JavaRDD<String> dataFiltered = dataFromFile.filter(s-> s.split(" ").length - 1 >= KennedySapceConstants.BYTES);
		return dataFiltered.filter(s -> s.split(" ")[KennedySapceConstants.HTTP_CODE].equalsIgnoreCase(httpCode.toString()));
	}
	/**
	 *  Return total of requests by httpcode
	 */
	@Override
	public Long getDataByHttpStatusCode(Integer httpCode) {
		JavaRDD<String> dataFromFile = kennedySpaceDataService.loadData();
		return dataFromFile.filter(s -> s.contains(httpCode.toString())).count();
	}
	/**
	 * Top n requests to  specific httpCode grouped by requsition URL
	 */
	@Override
	public List<TopURLStatusByCodeDTO> topUrlByStatusCode(Integer httpCode, Integer topNumber) {
		JavaRDD<String> dataFromFile = getRowsByHttpStatusCode(httpCode);
		JavaPairRDD<String, Integer> agrupaOnibus = dataFromFile
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(" ")[KennedySapceConstants.HOST_URL], KennedySapceConstants.CURRENT_ROW))
				.reduceByKey((x, y) -> x + y);
		List<Tuple2<String, Integer>> topDataGroupSummarized = agrupaOnibus.takeOrdered(topNumber,
				TupleComparator.INSTANCE);
		List<TopURLStatusByCodeDTO> urlStatusByCodeDTOList = TopURLStatusByCodeDTO.map(topDataGroupSummarized);
		return urlStatusByCodeDTOList;
	}
	/**
	 * Total of request to a specific httpCod grouped by date 
	 */
	@Override
	public List<HttpStatusByDateDTO> getHttpStatusByDate(Integer httpCode) {
		JavaRDD<String> dataFromFile = getRowsByHttpStatusCode(httpCode);
		JavaPairRDD<String, Integer> dataGroupSummarized = dataFromFile
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(" ")[KennedySapceConstants.TIMESTAMP].substring(0, 11), KennedySapceConstants.CURRENT_ROW))
				.reduceByKey((x, y) -> x + y);
		List<Tuple2<String, Integer>> data = dataGroupSummarized.collect();
		List<HttpStatusByDateDTO> httpStatusByDateList = HttpStatusByDateDTO.map(data);
		httpStatusByDateList.sort(Comparator.comparing(HttpStatusByDateDTO::getDate));
		return httpStatusByDateList;
	}
	/**
	 * Total of byte grouped by hostname
	 */
	@Override
	public QuantityOfReturnedBytesDTO getTotalReturnedBytes() {
		JavaRDD<String> dataFromFile = kennedySpaceDataService.loadData();
		List<Tuple2<String, String>> dataGroupSummarized = dataFromFile
				.mapToPair(s -> new Tuple2<String, String>(s.split(" ")[KennedySapceConstants.HOST_NAME],
						s.split(" ").length - 1 >= KennedySapceConstants.BYTES ? s.split(" ")[KennedySapceConstants.BYTES] : "no data"))
				.collect();
		  QuantityOfReturnedBytesDTO totalOfReturnedByteList = QuantityOfReturnedBytesDTO.map(dataGroupSummarized);
		return totalOfReturnedByteList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
