## 添加 Java 8 镜像来源
#FROM java:8
# 使用官方提供的Java基础镜像
FROM openjdk:8-jdk-alpine

## 设置工作目录
#WORKDIR /app

# 添加参数
#ARG JAR_FILE

# 添加 Spring Boot 包
#ADD target/${JAR_FILE} app.jar

#VOLUME /tmp

ADD target/module1-1.0-SNAPSHOT.jar app.jar

#EXPOSE 8081

# 执行启动命令
# Java系统属性设置"-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
