#!/bin/bash

echo "Installing OpenJDK..."
sudo apt-get update
sudo apt-get install -y openjdk-17-jdk

#
echo "Downloading and installing Maven..."
MAVEN_VERSION="3.9.6"
wget https://downloads.apache.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -P /tmp
sudo tar xf /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz -C /opt
sudo ln -s /opt/apache-maven-$MAVEN_VERSION /opt/maven
export PATH=$PATH:/opt/maven/bin

echo "Verifying Maven installation..."
mvn -v


echo "Running Maven clean install..."
mvn clean install
echo "Maven build complete."