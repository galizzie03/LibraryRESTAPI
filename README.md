# JAVA Simple REST API Book Library 
# Made With
```
Embedded Tomcat
```
```
Maven
```
```
Jersey
```
# How to build
Because it is maven project, to build the project just use
```
mvn clean package
```
Then run it
```
sh target/bin/webapp
```
How to use /add
```
curl -X POST localhost:8080/api/book/add -d "ISBN=123&Titolo=ciao&Autore=mondo"
```
