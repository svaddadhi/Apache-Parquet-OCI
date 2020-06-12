# Apache-Parquet-OCI

A REST service aimed to leverage Parquet with CSV conversion and data extraction methods. Users can also upload and download from the Oracle Cloud Infastructure.

### How to build/run

`gradle build` 

`gradle runJar`

### To download an object
The file will be downloaded into your home directory

`curl -X GET http://localhost:8080/download/{namespace}/{bucketName}/{fileToDownload}`

### To upload an object
`curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/../{file}"}' http://localhost:8080/upload/{namespace}/{bucket}/{fileToUpload}`

### To convert a CSV file to Parquet

The file will then be converted and put in your home directory

`curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/../{csvFile}"}' http://localhost:8080/convert/{csvFile}`

### To filter a Parquet file by columns

The input file will be a Parquet file and the output file will be a filtered Parquet file (in /tmp/<tableName>)

`curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/phvle/nation.parquet", "columns" : ["N_NATIONKEY", "N_NAME"], "tableName" : "phuTable"}' http://localhost:8080/filter/column`
