# Use a base image with a JRE (Java Runtime Environment)
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/qp-0.0.1-SNAPSHOT.jar /app/qp-0.0.1-SNAPSHOT.jar

# setup psql db
# Copy the database setup script into the container
COPY run.sh /app/run.sh
COPY config/application-dev.properties /app/application-dev.properties

# Grant execute permissions to the script
RUN chmod +x /app/run.sh
RUN chmod +x /app/application-dev.properties

# Run the database setup script
RUN /app/run.sh


# cd /Users/sohit/Documents/Personal_GitHub_Repo/qp-assessment/Dockerfile
#qp-assessment/Dockerfile
# docker build -t qp-test1 .
# docker run -p 8280:8280 qp-test
# docker rmi qp-test