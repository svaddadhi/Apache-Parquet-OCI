# Apache-Parquet-OCI

This is the github repository for the Apache Parquet + OCI project. In this repo will be our work where we are getting to know Apache parquet. This will be done by trying to convert a CSV file into Parquet format, and we want to avoid using any libraries but the standard ones (guava, comnmons-lang, ...). Our primary focus is performance. Our next step for this project will be to get familliar with Helidon and write a REST-API that can extract information from Apache Parquet. Once we have gotten to this point, the next steps will be figured out accordingly. 

### How to build/run
Make sure you're in the director /parquet-oci/
`gradle build` 

`gradle runJar`

### To download an object
`curl -X GET http://localhost:8080/download/{namespace}/{bucketName}/{fileToDownload}`

### To upload an object
`curl -X PUT -H "Content-Type: application/json" -d '{"filePath" : "/home/../{file}"}' http://localhost:8080/upload/{namespace}/{bucket}/{fileToUpload}`
