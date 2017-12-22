package com.cts.filesearch.constants;

public class ResourceConstants {
	
	public static final String SEARCH_FILE_REQ_PATH = "/search/file"; 

	public static final int STATUS_OK = 200;
	public static final int STATUS_NO_DATA = 204;
	public static final int STATUS_BAD_REQUEST = 400;
	public static final int STATUS_EXPECTATION = 500;
	
	public static final String STATUS_OK_MSG = "Successful.";
	public static final String STATUS_NO_DATA_MSG = "No Results Found for given search words.";
	public static final String STATUS_BAD_REQUEST_MSG = "Bad Request, input request is not valid";
	public static final String STATUS_EXPECTATION_MSG = "System Error, please see logs for more details on exception occured.";
}