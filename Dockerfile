FROM circleci/openjdk:8-jdk-browsers

# install necessary system dependencies
# get the key for gauge
RUN sudo apt-key adv --keyserver hkp://ipv4.pool.sks-keyservers.net --recv-keys 023EDB0B && \
    echo deb https://dl.bintray.com/gauge/gauge-deb stable main | sudo tee -a /etc/apt/sources.list

RUN sudo apt-get install apt-transport-https # apt-get needs this to download from the above https url

#Install gauge
RUN sudo apt-get update && sudo apt-get install gauge=1.0.0
RUN sudo apt-get install libxss1 libappindicator1 libindicator7

#Install gauge plugins
RUN	gauge install java --version 0.6.9 && \
    gauge install xml-report && \
    gauge install html-report
ENV PATH=$HOME/.gauge:$PATH

#Install Maven dependencies
RUN sudo mkdir -p /project
RUN sudo chown circleci /project
USER circleci
WORKDIR /project
COPY pom.xml .
RUN mvn -B -e -f pom.xml dependency:resolve-plugins dependency:resolve test-compile -DskipTests -Dmaven.test.skip=true -Dgaugeexecute=none
COPY . .
RUN mvn gauge:execute -DspecsDir=specs -Dgaugeexecute=none