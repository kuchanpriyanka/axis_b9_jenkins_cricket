FROM openjdk:8

#Adding and renaming the jar file
ADD /target/springboot-demo-0.1.jar cricketerapp.jar

#Expose 8080 port for hitting REST end points
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "cricketerapp.jar" ]