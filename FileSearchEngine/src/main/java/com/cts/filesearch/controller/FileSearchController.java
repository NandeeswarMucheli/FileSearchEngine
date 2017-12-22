package com.cts.filesearch.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.filesearch.constants.ResourceConstants;
import com.cts.filesearch.response.FileSearchResponse;
import com.cts.filesearch.response.ResultFile;
import com.cts.filesearch.service.FileSearchService;
import com.cts.filesearch.service.FileSearchServiceImpl;
import com.cts.filesearch.validator.ValidationUtil;

/*
 * Micro Service to search files with given search words
 */
@RestController
@EnableAutoConfiguration
public class FileSearchController {

	static final Logger logger = Logger.getLogger(FileSearchController.class);

	/*
	 * Restful api to search files using given search words
	 */
	@RequestMapping(value = ResourceConstants.SEARCH_FILE_REQ_PATH, method = RequestMethod.GET)
	public ResponseEntity<FileSearchResponse> searchFileResource(
			@RequestParam(value = "searchWords", required = true) String searchWords) {
		FileSearchResponse response = new FileSearchResponse();
		if (ValidationUtil.isValidRequest(searchWords)) {
			FileSearchService fileSearchService = new FileSearchServiceImpl();
			ResultFile resultFileObj = fileSearchService.getFilesByWordSearch(searchWords);
			if (resultFileObj == null) {
				response.setStatusCode(ResourceConstants.STATUS_EXPECTATION);
				response.setStatusText(ResourceConstants.STATUS_EXPECTATION_MSG);
				return new ResponseEntity<FileSearchResponse>(response, HttpStatus.EXPECTATION_FAILED);
			} else if((resultFileObj.getFiles() != null) && (resultFileObj.getFiles().size() == 0)){
				response.setStatusCode(ResourceConstants.STATUS_NO_DATA);
				response.setStatusText(ResourceConstants.STATUS_NO_DATA_MSG);
				return new ResponseEntity<FileSearchResponse>(response, HttpStatus.NOT_FOUND);
			}else{
				response.setStatusCode(ResourceConstants.STATUS_OK);
				response.setStatusText(ResourceConstants.STATUS_OK_MSG);
				response.setResult(resultFileObj);
				return new ResponseEntity<FileSearchResponse>(response, HttpStatus.OK);
			}
		} else {
			response.setStatusCode(ResourceConstants.STATUS_BAD_REQUEST);
			response.setStatusText(ResourceConstants.STATUS_BAD_REQUEST_MSG);
			return new ResponseEntity<FileSearchResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}

	/*
	 * Sping boot application startup
	 */
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FileSearchController.class, args);
		logger.info("Tomcat initialized with port: 8080(http)");
	}
	
}