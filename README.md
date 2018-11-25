# browsertestframework-rpetrina

## Step 1: Clone this repository

## Step 2: Set up docker

* Install docker (or Docker For Windows)

## Step 3: Set up the Selenium Docker images

```bash
$ docker-compose rm -f
$ docker-compose pull
$ docker-compose build --no-cache && docker-compose up -d
```

* This step may take some time to complete while docker creates the images needed.

## Step 4: Run the tests using the gaugejavaselenium container

* Note: On windows, use %cd% instead of $PWD

```bash
$ docker run --link browsertestframework_hub -it --net browsertestframework_default -v $PWD:/project browsertestframework_javagaugeselenium /bin/bash -c "cd /project && mvn test-compile && mvn gauge:execute -DspecsDir=specs -Dgaugeexecute=test"
```

## Step 5: Kill the docker containers for each browser to clean up

```bash
$ docker-compose down
```

* You can run the tests again by repeating Steps 3-5.
  * For repeated test runs, Step 3 can be simplified to "docker-compose build && docker-compose up -d".
  * It may not be necessary to run "mvn test-compile" for each test run.
* Note: you can specify the user's information in the [FindRate.spec](specs/FindRate.spec) file.
* You can specify the initial test URL and the browser under which to test in [user.properties](env/default/user.properties)
* Test reports are available in the [reports](reports) directory.
  * You can view the results of the test by opening FindRate.html in a browser after running the test. This file can be found in the html-report/specs directory.
* You can watch the text execution when the browser images in [docker-compose.yml](docker-compose.yml) are using the debug versions, by connecting to localhost:5900 (Firefox) or 5901 (Chrome) with VNC. When prompted for a password, use 'secret'