package com.engDados.dto;

import java.util.ArrayList;
import java.util.List;

import scala.Tuple2;

public class TopURLStatusByCodeDTO {
	private String url;
	private Integer total;
	
	public TopURLStatusByCodeDTO() {
		
	}
	public TopURLStatusByCodeDTO(String url, Integer total) {
		this.url = url;
		this.total = total;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static List<TopURLStatusByCodeDTO> map(List<Tuple2<String, Integer>> data ){
		List<TopURLStatusByCodeDTO> httpReqList = new ArrayList<TopURLStatusByCodeDTO>();
		for (Tuple2<String, Integer> tuple2 : data) {
			TopURLStatusByCodeDTO request = new TopURLStatusByCodeDTO(tuple2._1(), tuple2._2());
			httpReqList.add(request);
		}
		return httpReqList;
	}
}
