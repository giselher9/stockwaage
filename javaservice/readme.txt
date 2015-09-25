cd to javaservice

build jar:
mvn clean package

run jar:
java -jar target\com.stockwaage.service-1.0.0-SNAPSHOT.jar server stockwaage-config.yaml

hello world resource:
http://localhost:9000/api/helloworld/test

frontend:
http://localhost:9000/api/frontend/index.html