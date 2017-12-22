package com.cts.filesearch.response;

import java.util.List;

public class ResultFile {

	private String words;
	private List<String> files;
	
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
}