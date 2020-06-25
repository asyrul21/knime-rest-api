# knime-rest-api

We attempt to setup Knime features using web servives API endpoints.

## Create a Maven Project
1. Right Click > New > Others
2. Under Maven select Maven Project
3. In the Catalog menu, type in 'Jersey' and choose : org.glassfish.jersey.archtypes and jersey-quickstart-webapp
4. Insert details and finish.

## Configure Project to Use Tom Cat
1. Right Click project > Properties
2. Targeted Runtimes > Select Tom Cat

## Running the App
1. Right Click project > Run As
2. Run on Server
3. Insert valid URL - view your web.xml to see endpoint

## Some Issues with Versioning
1. Use Jersey version 2.7.0

2. The dependencies that saved me:

https://stackoverflow.com/questions/58250336/jersey-return-500-when-trying-return-an-xml


```xml
 <dependencies>
            <dependency>
                <groupId>org.glassfish.jersey</groupId>
                <artifactId>jersey-bom</artifactId>
                <version>${jersey.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.glassfish.jersey.containers</groupId>
            <artifactId>jersey-container-servlet-core</artifactId>
            <!-- use the following artifactId if you don't need servlet 2.x compatibility -->
            <!-- artifactId>jersey-container-servlet</artifactId -->
        </dependency>
        <!-- uncomment this to get JSON support -->
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-json-binding</artifactId>
        </dependency>
        <dependency>
		  <groupId>javax.xml.bind</groupId>
		  <artifactId>jaxb-api</artifactId>
		  <version>2.3.0</version>
		</dependency>
		 <dependency>
        <groupId>javax.activation</groupId>
        <artifactId>activation</artifactId>
	        <version>1.1</version>
	    </dependency>
	    <dependency>
	        <groupId>org.glassfish.jaxb</groupId>
	        <artifactId>jaxb-runtime</artifactId>
	        <version>2.3.0-b170127.1453</version>
	    </dependency>
    </dependencies>
```

# Deployment on Heroku

https://devcenter.heroku.com/articles/java-webapp-runner

## 1. Configure Maven to Download Webapp Runner
In your pom.xml, update your plugins:
```xml
 <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.5.1</version>
            <inherited>true</inherited>
            <configuration>
                <source>1.7</source>
                <target>1.7</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals><goal>copy</goal></goals>
                    <configuration>
                        <artifactItems>
                            <artifactItem>
                                <groupId>com.heroku</groupId>
                                <artifactId>webapp-runner</artifactId>
                                <version>9.0.30.0</version>
                                <destFileName>webapp-runner.jar</destFileName>
                            </artifactItem>
                        </artifactItems>
                    </configuration>
                </execution>
            </executions>
        </plugin>
</plugins>
```

## 2. Build a .WAR file
```bash
mvn package
```

## 3. Test run your application
Make sure it works on : http://localhost:8080/api/hello
```bash
java -jar target/dependency/webapp-runner.jar target/*.war
```

## 4. Create Procfile
```Procfile
web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
```

## 5.Deploy
```bash
heroku create java-api-demo

git add .
git commit -m "deploy"
git push

git push heroku master
```

## Test Deployed API Endpoints
- https://knime-workflow-api.herokuapp.com/api/workflow
- https://knime-workflow-api.herokuapp.com/api/workflow/info


- POST Request
Endpoint: https://knime-workflow-api.herokuapp.com/api/workflow/
1. Set Request Body
Example Request Body (Raw)
```json
{
    "columnFilter": "Country",
    "columns": [
        "Country",
        "City",
        "State"
    ],
    "fileType": "csv",
    "outputFormat": "csv",
    "rowFilter": "Malaysia"
}
```

2. Set Header properties
- Content-Type : application/JSON
- Accept application.JSON

# References

https://stackoverflow.com/questions/37762877/pass-list-of-strings-to-java-method

You may encounter this error:

java.lang.ClassNotFoundException: org.glassfish.jersey.servlet.ServletContainer

Solution: https://howtodoinjava.com/jersey/solved-java-lang-classnotfoundexception-org-glassfish-jersey-servlet-servletcontainer/