# Use the official Ubuntu base image
FROM ubuntu:20.04

# Set the maintainer label
LABEL authors="waleed.elbarbary"

# Set environment variables for Maven installation
ENV MAVEN_VERSION=3.9.6
ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

# Install necessary tools and dependencies
RUN apt-get update && \
    apt-get install -y wget tar && \
    apt-get install -y openjdk-17-jdk && \
    wget https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz -O /tmp/maven.tar.gz && \
    tar -xzvf /tmp/maven.tar.gz -C /opt && \
    mv /opt/apache-maven-${MAVEN_VERSION} /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Set the working directory
WORKDIR /app

# Copy the contents of the current directory into the container's /app directory
COPY . .

# Verify Maven installation
RUN mvn --version

# Verify Java installation
RUN java --version

Run mvn clean test