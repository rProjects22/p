# Multistage Dockerfile
# -------------------
# Build Stage
# -------------------

#base image:
FROM gradle:8-jdk17-alpine AS builder

#copy the application files from the host machine to the image filesystem.
COPY --chown=gradle:gradle . /home/gradle/src

#set the directory within the image, for executing future commands
WORKDIR /home/gradle/src

#Run gradle build within the container - NOT the host machine
#for RUN commands, the command only apply to images
RUN gradle build -x test

# -------------------
# Final Stage
# -------------------

FROM openjdk:17-alpine

RUN mkdir /app

# Copy the JAR from the build stage
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/user-service.jar

# Set environment variables
ENV MONGO_DB_USERNAME=gbcadmin \
    MONGO_DB_PWD=password

EXPOSE 8084

# Specify the default command to run when the container starts
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]