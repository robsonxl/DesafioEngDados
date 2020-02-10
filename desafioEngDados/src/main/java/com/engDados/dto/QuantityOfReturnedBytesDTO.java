package com.engDados.dto;

import java.util.List;

import scala.Tuple2;

public class QuantityOfReturnedBytesDTO {
	
	private int totalBytesReturned;
	
	
	public int getTotalBytesReturned() {
		return totalBytesReturned;
	}

	public void setTotalBytesReturned(int totalBytesReturned) {
		this.totalBytesReturned = totalBytesReturned;
	}

	public void QuantityOfDistintsHostsDTO(){
		
	};
  
	
	 
	public static QuantityOfReturnedBytesDTO map(List<Tuple2<String, String>> data) {
		QuantityOfReturnedBytesDTO request = new QuantityOfReturnedBytesDTO();
		for (Tuple2<String, String> tuple2 : data) {
			int bytes =0;
			if(tuple2._2!=null) {
				try {
					bytes = Integer.valueOf(tuple2._2).intValue();
				} catch (Exception e) {
					
				}
			}
			request.setTotalBytesReturned(request.getTotalBytesReturned() + bytes);
		}
	
		return request;
	}


}
