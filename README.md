# Groovy + Spring Boot App

### Requisitos

- [JDK 11](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
- [Gradle 7](https://gradle.org)

Configurar devidademte as variáveis de ambiente
- JAVA_HOME
- GRADLE_HOME
- PATH

### Executando Localmente

```shell
gradle build
```

```shell
gradle bootRun
```

### Executando via docker

```shell
gradle build
```

```shell
docker build -t app-groovy-spring-boot .
```

```shell
docker run -p 8080:8080 app-groovy-spring-boot 
```