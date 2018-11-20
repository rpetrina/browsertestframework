# browsertestframework

## Step 1: Set up docker

* Install docker
* Set up the Gauge-Selenium docker image

```bash
$ docker pull circleci/openjdk:8-jdk-browsers
$ docker build --no-cache -t gaugejavaseleniumtest:latest .
```

## Step 2: Build the tests using the gaugejavaselenium container

* Note: On windows, use "cd" instead of $PWD

```bash
$ docker run -v $PWD:/project gaugejavaseleniumtest /bin/bash -c "cd /project && mvn test-compile"
```

## Step 3: Set up the selenium Docker images

```bash
$ docker-compose up -d
```

## Step 4: Run the tests using the gaugejavaselenium container

```bash
$ docker run --link selenium-hub:hub -v $PWD:/project gaugejavaseleniumtest mvn gauge:execute -DspecsDir=specs
docker run --link browsertestframework_hub_1 -it --net browsertestframework_default -v $PWD:/project gaugejavaseleniumtest /bin/bash -c "cd /project && mvn test-compile && mvn gauge:execute -DspecsDir=specs"
```

## Step 5: Kill the docker containers for each browser to clean up

```bash
$ docker-compose down
```

* You can run the tests again by repeating Steps 3-5. Note that you can specify the user's information in the [FindRate.spec](specs/FindRate.spec) file. New user information is required to reproduce the new user sign up functionality.
  * It may not be necessary to run "mvn test-compile" for each test run.
* Test reports are available in the reports directory.