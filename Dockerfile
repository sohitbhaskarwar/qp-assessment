# Use a base image with a JRE (Java Runtime Environment)
FROM openjdk:17-oracle

WORKDIR /app
COPY target/qp-0.0.1-SNAPSHOT.jar /app/qp-0.0.1-SNAPSHOT.jar
EXPOSE 8280
ENTRYPOINT ["java","-jar","qp-0.0.1-SNAPSHOT.jar"]

# cd /Users/sohit/Documents/Personal_GitHub_Repo/qp-assessment/Dockerfile
#qp-assessment/Dockerfile

# docker build --no-cache -t qp-test .
# docker run -p 8280:8280 qp-test
# docker rmi qp-test