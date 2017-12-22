package com.cts.filesearch.validator;

public class ValidationUtil {

	/*
	 * Validate the input request
	 */
	public static boolean isValidRequest(String searchString){
		if (!isWordValid(searchString)) {
            return Boolean.FALSE;
        }
		for (String searchWordOne : searchString.split(",")) {
			if (!isWordValid(searchWordOne)) {
				return Boolean.FALSE;
			}
		}
        return Boolean.TRUE;
	}
	
	/*
	 * Validate a word is null/empty
	 */
	private static boolean isWordValid(final String searchWord) {

        if (null != searchWord && !searchWord.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}