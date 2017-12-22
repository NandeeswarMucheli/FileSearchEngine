package com.cts.filesearch.service;

import com.cts.filesearch.response.ResultFile;

public interface FileSearchService {

	public ResultFile getFilesByWordSearch(final String searchWords);
}