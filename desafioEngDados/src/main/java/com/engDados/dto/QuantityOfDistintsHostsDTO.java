package com.engDados.dto;

import java.util.ArrayList;
import java.util.List;

import scala.Tuple2;

public class QuantityOfDistintsHostsDTO {
	
	public QuantityOfDistintsHostsDTO() {
	}

	public QuantityOfDistintsHostsDTO(String hostname, int totalHost) {
		this.hostname = hostname;
		this.totalRequests = totalHost;
	}

	private String hostname;
	private int totalRequests;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getTotalHostRequest() {
		return totalRequests;
	}

	public void setTotalHostRequest(int totalRequest) {
		this.totalRequests = totalRequest;
	}
	
	public static List<QuantityOfDistintsHostsDTO> map(List<Tuple2<String, Integer>> data) {
		List<QuantityOfDistintsHostsDTO> httpReqList = new ArrayList<QuantityOfDistintsHostsDTO>();
		for (Tuple2<String, Integer> tuple2 : data) {
			QuantityOfDistintsHostsDTO request = new QuantityOfDistintsHostsDTO(tuple2._1(), tuple2._2());
			httpReqList.add(request);
		}
		return httpReqList;
	}
}
