package com.engDados.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import scala.Tuple2;

public class HttpStatusByDateDTO {

	private LocalDate date;
	
	private Integer totalDataByStatusCode;
	
	public HttpStatusByDateDTO() {
		
	}
	
	public HttpStatusByDateDTO(LocalDate date, Integer totalDataByStatusCode) {
		this.date = date;
		this.totalDataByStatusCode = totalDataByStatusCode;
	}
	public int getTotalDataByStatusCode() {
		return totalDataByStatusCode;
	}
	public void setTotalDataByStatusCode(Integer totalDataByStatusCode) {
		this.totalDataByStatusCode = totalDataByStatusCode;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public static List<HttpStatusByDateDTO> map (List<Tuple2<String, Integer>> data) {
		List<HttpStatusByDateDTO> httpReqList = new ArrayList<HttpStatusByDateDTO>();
		for (Tuple2<String, Integer> tuple2 : data) {
			LocalDate ocurrenceDate = getDate(tuple2._1());
			HttpStatusByDateDTO request = new HttpStatusByDateDTO(ocurrenceDate, tuple2._2());
			httpReqList.add(request);
		}
		return httpReqList;
	}
	
	
	private  static LocalDate getDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy",new Locale("en,EN"));
		LocalDate formattedDate = null; 
		try {
			Date ocurrenceDate = formatter.parse(date);
			formattedDate = convertToLocalDateTime(ocurrenceDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formattedDate;
	}

	private static  LocalDate convertToLocalDateTime(Date dateToConvert) {
		return dateToConvert.toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
	}
}
