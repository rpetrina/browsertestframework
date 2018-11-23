# browsertestframework-rpetrina

## Step 1: Set up docker

* Install docker (or Docker For Windows)

## Step 2: Set up the Selenium Docker images

```bash
$ docker-compose rm -f
$ docker-compose pull
$ docker-compose build --no-cache && docker-compose up -d
```

## Step 3: Run the tests using the gaugejavaselenium container

* Note: On windows, use %cd% instead of $PWD

```bash
$ docker run --link browsertestframework_hub_1 -it --net browsertestframework_default -v $PWD:/project gaugejavaseleniumtest /bin/bash -c "cd /project && mvn test-compile && mvn gauge:execute -DspecsDir=specs"
```

## Step 4: Kill the docker containers for each browser to clean up

```bash
$ docker-compose down
```

* You can run the tests again by repeating Steps 3-5. Note that you can specify the user's information in the [FindRate.spec](specs/FindRate.spec) file. New user information is required to reproduce the new user sign up functionality.
  * It may not be necessary to run "mvn test-compile" for each test run.
* Test reports are available in the [reports](reports) directory.
  * You can view the results of the test by opening FindRate.html in a browser after running the test. This file can be found in the html-report/specs directory.