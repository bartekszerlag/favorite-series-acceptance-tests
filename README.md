# favorite-series-acceptance-tests

This project consists of two packages - frontend and backend. Frontend part includes UI tests in Selenium, 
backend part is responsible for API tests in RestAssured. 

To run acceptance tests you need to start the application first. All information you can find here:
- Backend: [favorite-series-service](https://github.com/bartekszerlag/favorite-series-service)
- Frontend: [favorite-series-frontend](https://github.com/bartekszerlag/favorite-series-frontend)

## How to run

To run frontend tests you need to go to the project directory and run:

### `./mvnw -Dtest="frontend.tests.**" test`

If you want to run backend tests:

### `./mvnw -Dtest="backend.tests.**" test`

To generate report of all tests you have to run:

### `./mvnw test allure:report`

You will find the report in **target/site/allure-maven-plugin/index.html** file.

