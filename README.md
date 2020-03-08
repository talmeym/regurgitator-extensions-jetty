# regurgitator-extensions-jetty

regurgitator is a lightweight, modular, extendable java framework that you configure to 'regurgitate' canned or clever responses to incoming requests; useful for quickly mocking or prototyping services without writing any code. simply configure, deploy and run.

start your reading here: [regurgitator-all](http://github.com/talmeym/regurgitator-all#regurgitator)

## running regurgitator using jetty

regurgitator-extensions-jetty allows you to run both the [regurgitator servlet](https://github.com/talmeym/regurgitator-extensions-web#regurgitator-servlet) and the [global metadata servlet](https://github.com/talmeym/regurgitator-extensions-web#global-metadata-servlet) in a small jetty server to allow for easy containerisation and use with virtualisation software such as [docker](https://www.docker.com)

### RegurgitatorJettyServer usage

0 - port - the port on which to accept connections
1 - config path - the path from which to load the regurgitator servlet's config file
2 - regurgitator context path - the context path for all calls that the regurgitator servlet should process
3 - global properties path - the path from which to load the optional global context properties file
4 - global context path - the context path for all calls the global context servlet should process

```java RegurgitatorJettyServer [port] [config path] [regurg. context path] [global properties path] [global context path]```

eg.

```java -cp rock-paper-scissors.jar com.emarte.regurgitator.extensions.jetty.RegurgitatorJettyServer 8090 "classpath:/rock-paper-scissors.xml" "/rockpaperscissors/*" "classpath:/global.properties" "/global/*"```

see the reference project here: [project](https://github.com/talmeym/rock-paper-scissors), [Dockerfile](https://github.com/talmeym/rock-paper-scissors/blob/master/Dockerfile)
