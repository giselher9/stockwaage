cd to javaservice

build jar:
mvn clean package

run jar:
java -jar target\com.stockwaage.service-1.0.0-SNAPSHOT.jar server stockwaage-config.yaml

current weights resource:
http://localhost:9000/api/rest/currentweights

historic weights resource:
http://localhost:9000/api/rest/historicweights

frontend:
http://localhost:9000/api/frontend/index.html