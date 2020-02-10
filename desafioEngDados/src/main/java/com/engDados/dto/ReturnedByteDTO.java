package com.engDados.dto;

public class ReturnedByteDTO {
	
	
	private String hostname;
	private Integer bytesReturned;
	
	
	public Integer getBytesReturned() {
		return bytesReturned;
	}
	public void setBytesReturned(Integer bytesReturned) {
		this.bytesReturned = bytesReturned;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public ReturnedByteDTO () {}
	public ReturnedByteDTO (String hostname, Integer bytesReturned ) {}

}
