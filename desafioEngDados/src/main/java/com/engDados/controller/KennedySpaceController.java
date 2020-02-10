package com.engDados.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.engDados.dto.HttpStatusByDateDTO;
import com.engDados.dto.QuantityOfDistintsHostsDTO;
import com.engDados.dto.QuantityOfReturnedBytesDTO;
import com.engDados.dto.TopURLStatusByCodeDTO;
import com.engDados.serviceImpl.KennedySpaceServiceImpl;

@RestController
@RequestMapping("logs/request")
public class KennedySpaceController {
	@Autowired
	private KennedySpaceServiceImpl reqServerLogsService;
	
	/**
	 *  Returns total of unique hosts from dataset 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/totalHost")
	public Long getTotalOfDistincstHosts() {
		return reqServerLogsService.getTotalDistincstHosts();
	}
	
	/**
	 * Returns total of unique hosts grouped by hostname from dataset 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/totalGroupHost")
	public List<QuantityOfDistintsHostsDTO>  getTotalOfDistincstByHosts() {
		return reqServerLogsService.getTotalDistincstHostsByHosts();
	}
	
	/**
	 * Returns total of requests by http status code
	 * @param httpCode
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/totalHttpCode")
	public Long getDataByHttpStatusCode(@RequestParam("httpCode") Integer httpCode) {
		return reqServerLogsService.getDataByHttpStatusCode(httpCode);
	}
	/**
	 *  Returns top url by http status code
	 * @param httpCode
	 * @param topNumber
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/topUrl")
	public List<TopURLStatusByCodeDTO> top(@RequestParam("httpCode") Integer httpCode, @RequestParam("topNumber") Integer topNumber) {
		return reqServerLogsService.topUrlByStatusCode(httpCode,topNumber);
	}
	
	/**
	 * Returns data by https status code grouped by Date 
	 * @param httpStatusCode
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/totalHttpCodeByDate")
	public List<HttpStatusByDateDTO>  getHttpStatusByDate(@RequestParam("httpCode") Integer httpCode) throws ParseException {
		return reqServerLogsService.getHttpStatusByDate(httpCode);
	}
	/**
	 * Return total Bytes from each host
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "totalBytes")
	public QuantityOfReturnedBytesDTO getTotalOfReturnedBytes() {
		return reqServerLogsService.getTotalReturnedBytes();
	}
}
