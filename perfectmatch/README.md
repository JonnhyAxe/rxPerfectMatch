# perfectmatch

#How to run

mvn spring-boot:run 
mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

#How to Test
http://localhost:8080/login


#Add Embedded DB
https://github.com/JonnhyAxe/sample-spring-boot-data-jpa-embedded

Then, you can access h2's console at: localhost:8080/h2-console Simply type in the url jdbc:h2:mem:PM_TEST;MVCC=true;DB_CLOSE_DELAY=-1;MODE=Oracle (spring.datasource.url)  in JDBC URL field and perfect_match (spring.datasource.username) in the user name field

#ADD Embedded DB to latest Spring Release (activate console with Spring Security)

https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
http://localhost:8080/console

#Test Repository Endpoints

http://localhost:8080/sample/repo
http://localhost:8080/music/repo
http://localhost:8443/match/repo


#Maven download dependencies Spurces and JavaDocs 
#https://stackoverflow.com/questions/310720/get-source-jar-files-attached-to-eclipse-for-maven-managed-dependencies
mvn dependency:sources
OR 
mvn clean install dependency:sources -Dmaven.test.skip=true

mvn dependency:resolve -Dclassifier=javadoc


#SQL 
Select * from Music where artist = 'Latmun'

#Security 

user: JAxe
pws: *****123

The HTTP header contains "authorization: Basic SkF4ZTpqYXhlMTIz". Be aware of "Remember me"

http://www.studytrails.com/frameworks/spring/spring-security-method-level/


TODOS:
SAMPLE entity with two @ID


#Swagger 
http://localhost:8080/swagger-ui.html

#HATEOS

POST example 
{
"id": null,
"artist": "LatmunXPTO2",
"name": "Please Stop (Original Mix)XPTO2",
"style": "TECH_HOUSE",
"samples": []
}
POST response

{
"music": {
"id": 6,
"artist": "LatmunXPTO2",
"name": "Please Stop (Original Mix)XPTO2",
"style": "TECH_HOUSE",
"samples": [],
},
"_links": {
	"music": {
		"href": "http://localhost:8080/music"
		},
		"self": {
		"href": "http://localhost:8080/music/Please%20Stop%20(Original%20Mix)XPTO2"
		}
	}
}
 
#Actuator
http://localhost:8080/info
http://localhost:8080/health
http://localhost:8080/metrics



#MongoDB

C:\dev\tools\MongoDB\Server\3.4\bin>mongod.exe --dbpath C:\dev\tools\MongoDB\data
