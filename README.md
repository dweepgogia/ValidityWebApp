# Validity Web App

To run the web application (inside Build directory): 

#### Method 1: 

`java -jar ValidityWebAPP-1.0-SNAPSHOT.jar`

Note: Make sure both the data files, normal.csv and advanced.csv are in the same folder as the JAR file. Also, the data files have to be in the CSV format and can not have names other than "normal.csv" and "advanced.csv" respectively. 

#### Method 2: 

`docker build -t webapp .`

Note: Make sure the JAR, normal.csv and advanced.csv files are in the same directory as the Dockerfile. 

`docker run -d -p 8080:8080 webapp`

#### Go to

`localhost:8080/normal`

`localhost:8080/advanced`

to see the running application.

