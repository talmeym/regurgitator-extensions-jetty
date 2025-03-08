# regurgitator-extensions-jetty

regurgitator is a lightweight, modular, extendable java framework that you configure to 'regurgitate' canned or clever responses to incoming requests; useful for quickly mocking or prototyping services without writing any code. simply configure, deploy and run.

start your reading here: [regurgitator-all](https://talmeym.github.io/regurgitator-all#regurgitator)

[``apidocs``](https://regurgitator.emarte.uk/apidocs/regurgitator-extensions-jetty/0.1.4/){:target="_blank"}

## running regurgitator using jetty

regurgitator-extensions-jetty enables both the [regurgitator servlet](https://talmeym.github.io/regurgitator-extensions-web#regurgitator-servlet) and the [global metadata servlet](https://talmeym.github.io/regurgitator-extensions-web#global-metadata-servlet) to run in a small jetty server; allowing you to containerization mocks with software such as [docker](https://www.docker.com){:target="_blank"}

### RegurgitatorJettyServer usage

#### RegurgitatorJettyServer command line arguments

* port - the port on which to accept connections
* config file - the path from which to load the regurgitator servlet's config file
* regurgitator context path - the context path for all calls that the regurgitator servlet should process
* global properties file - the path from which to load the optional global context properties file
* global context path - the context path for all calls the global context servlet should process

```java RegurgitatorJettyServer [port] [config file] [context path] [global properties file] [context path]```

eg.

```java -cp rock-paper-scissors.jar uk.emarte.regurgitator.extensions.jetty.RegurgitatorJettyServer 8090 "classpath:/rock-paper-scissors.xml" "/rockpaperscissors/*" "classpath:/global.properties" "/global/*"```

above example from the reference project here: [project](https://github.com/talmeym/primeable-mock-server){:target="_blank"}, [Dockerfile](https://github.com/talmeym/primeable-mock-server/blob/master/Dockerfile){:target="_blank"}

regurgitator can alternatively be run in an application / web server, e.g. Tomcat, see [here](https://talmeym.github.io/regurgitator-extensions-web#tomcat)
