package com.cts.filesearch.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FileSearchResponse {

	private int statusCode;
	private String statusText;
	private ResultFile result;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public ResultFile getResult() {
		return result;
	}
	public void setResult(ResultFile result) {
		this.result = result;
	}
}