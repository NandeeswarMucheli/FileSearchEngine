package com.cts.filesearch.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cts.filesearch.response.ResultFile;

import util.PropertyReader;

public class FileSearchServiceImpl implements FileSearchService {

	static final Logger logger = Logger.getLogger(FileSearchServiceImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see com.cts.filesearch.service.FileSearchService#getFilesByWordSearch(java.lang.String)
	 * 
	 * Service which will search files by given words
	 */
	@Override
	public ResultFile getFilesByWordSearch(final String searchWords) {
		logger.info("file search started..");
		ResultFile resultFileListObj = null;
		
		Set<String> wordsSet = new HashSet<>();
		List<String> wordMatchFileList = new ArrayList<>();
		try {
			for (String searchWordOne : searchWords.split(",")) {
				wordsSet.add(searchWordOne.toLowerCase());
			}
			String[] ext = PropertyReader.getValue("INPUT_FILE_EXT").split(",");
			if (StringUtils.isNotBlank(PropertyReader.getValue("INPUT_DIRECTORY_PATH"))) {
				File filePath = new File(PropertyReader.getValue("INPUT_DIRECTORY_PATH"));
				List<File> listOfFiles = (List<File>)FileUtils.listFiles(filePath, ext, true);
				if((listOfFiles != null) && (listOfFiles.size() > 0)){
					for (File fileOne : listOfFiles) {
						searchBruteForce(wordsSet, fileOne, wordMatchFileList);
					}
				}
			}
			if (wordMatchFileList.size() > 0) {
				resultFileListObj = new ResultFile();
				resultFileListObj.setWords(searchWords);
				resultFileListObj.setFiles(wordMatchFileList);
			}
			logger.info("file search completed!");
		}catch (IOException e) {
			logger.error("There is some problem while reading file, detailed error message:"+e.getMessage());
		}catch (Exception e) {
			logger.error("Error occured while searching files for word, detailed error message:"+e.getMessage());
		}
		return resultFileListObj;
	}

	/*
	 * Searching given search words in one file at a time
	 */
	private void searchBruteForce(Set<String> wordsSet, File inputFile, List<String> wordMatchFileList) throws IOException {
		// Prepare modifiable set for checking all words matchs in file
		Set<String> searchWordTempSet = new HashSet<>();
		searchWordTempSet .addAll(wordsSet);
		FileInputStream fstream = new FileInputStream(inputFile);
		BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
		String readLine = "";
		while((readLine = in.readLine()) != null){
			String[] words = readLine.split("\\W");
			for (String text : words) {
				System.out.println("text:"+text);
				String fileContentWord = text.toLowerCase();
				// Check whether fileContentWord is there in the resultFileMap keys
				if (searchWordTempSet.contains(fileContentWord)) {
					// Removing fileContentWord from searchWordTempSet
					searchWordTempSet.remove(fileContentWord);
				}
				
				// if all words matches exit from loop and save file path
				if(searchWordTempSet.size() == 0){
					// Add file path to respective resultFileMap key value list
					wordMatchFileList.add(inputFile.getPath());
					// close stream
					in.close();
					// exit from reading file while loop as whole words matches in the file
					return;
				}
			}
		}
		// close stream
		in.close();
	}
}