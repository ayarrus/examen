FROM openjdk:8
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} examen-0.0.1.war
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "examen-0.0.1.war"]
