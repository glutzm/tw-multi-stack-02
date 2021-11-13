# Dockerfile

# TODO: alterar versão de acordo com variável de ambiente
FROM  maven:3.8.3-openjdk-17-slim

MAINTAINER  Gustavo Antonio Lutz de Matos <gustavo.almatos@gmail.com>

RUN mkdir /src
RUN mkdir /app

COPY . /src

WORKDIR /src

RUN mvn -DskipTests=true install -f pom.xml

# TODO: alterar versão de acordo com variável de ambiente
RUN cp ./target/ediaristas-0.0.1.war /app

WORKDIR /app

RUN rm -Rf /src

ENTRYPOINT ["java", "-jar", "./ediaristas-0.0.1.war"]
