package com.engDados.service;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;

import com.engDados.dto.HttpStatusByDateDTO;
import com.engDados.dto.QuantityOfDistintsHostsDTO;
import com.engDados.dto.QuantityOfReturnedBytesDTO;
import com.engDados.dto.TopURLStatusByCodeDTO;

public interface KennedySpaceService {
	
	public List<QuantityOfDistintsHostsDTO> getTotalDistincstHostsByHosts();
	
	public Long getTotalDistincstHosts();
	
	public JavaRDD<String> getRowsByHttpStatusCode(Integer httpStatusCode);
	
	public Long getDataByHttpStatusCode(Integer errorCode);
	
	public List<TopURLStatusByCodeDTO> topUrlByStatusCode(Integer httpsStatusCode, Integer topNumber);
	
	public List<HttpStatusByDateDTO> getHttpStatusByDate(Integer errorCode);
	
	public QuantityOfReturnedBytesDTO getTotalReturnedBytes();
}
