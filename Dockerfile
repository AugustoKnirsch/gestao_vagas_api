FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y wget
RUN wget https://download.java.net/java/GA/jdk22.0.2/c9ecb94cd31b495da20a27d4581645e8/9/GPL/openjdk-22.0.2_linux-x64_bin.tar.gz \
    && tar -xvf openjdk-22.0.2_linux-x64_bin.tar.gz -C /usr/local/ \
    && rm openjdk-22.0.2_linux-x64_bin.tar.gz \
    && mv /usr/local/jdk-22.0.2 /usr/local/jdk-22 \
    && update-alternatives --install /usr/bin/java java /usr/local/jdk-22/bin/java 1
RUN java -version    
COPY . .
RUN apt-get install maven -y
RUN mvn clean install

FROM ubuntu:latest
COPY --from=build /usr/local/jdk-22 /usr/local/jdk-22

RUN update-alternatives --install /usr/bin/java java /usr/local/jdk-22/bin/java 1

EXPOSE 8080

COPY --from=build /target/gestao_vagas-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]