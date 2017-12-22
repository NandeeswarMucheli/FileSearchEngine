# FileSearchEngine
Search all files with given search word's in directory and sub directories

This project is an rest service which provides a service to search for files with given word/multiple words in given root directory along with sub directories in root directory. Application configurations are configured in application.properties file. This is an production ready micro service developed using Spring Boot.

## Requirement Doc for reference:
```
The service should:
•	Present a simple rest API to allow perform a search
•	Allow searching with multiple words
•	Multiple word searches should return files that match all words
•	Should perform whole word matches
•	No fuzzy search required
•	Return results as quickly as possible
•	Code should be production ready code to a professional standard
```

## Service Details:
```
We need to pass comma seperated values as below: 
/search/file?searchWords=root,child_1
```

## Request Details:
```
URL:
http://localhost:8080/search/file?searchWords=root,child_1

Method: GET

Sample Response:
{
	"statusCode": 200,
	"statusText": "Successful.",
	"result": {
		"words": "root,child_1",
		"files": ["C:\\Users\\muchelnx\\Downloads\\Root\\child_1\\child_1.txt"]
	}
}
```

Above response testd with the below file structure and contents: Files and Contents:
```
C:\\Users\\Nandeeswar\\Downloads\\Root\\file1.txt content:
root
	
C:\\Users\\Nandeeswar\\Downloads\\Root\\child_1\\child_1.txt content:
root
child_1
	
C:\\Users\\Nandeeswar\\Downloads\\Root\\child_2\\child_2.txt content:
root
child_2
	
C:\\Users\\Nandeeswar\\Downloads\\Root\\log1.log content:
root
```
