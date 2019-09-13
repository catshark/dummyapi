Dummy REST API Tests

Unit Framework: TestNG
IDE: Intellij IDEA 2019
Java version: 1.8
Maven version: 3.6.1
OS used: Windows 10

Dependencies:
All dependencies needed for this framework are defined in the pom.xml file at the root of this project and are pulled
down when invoking the mvn command in the steps below.

How to run tests:

1) Navigate to root of this project in Command Prompt

2) To run the tests, invoke "mvn clean test"


LIMITATIONS/TODOS:
If I had more time, I would make the following improvements

1) Proper logging to console using log4j/slf4j for improved readability when running the tests
2) Add allure reporting so test results could be visually inspected after a run
3) Tests aren't independent from one another. Running mvn test will run all the tests in priority order as each test depends on the previous one. I would like to make the tests self-contained
4) Abstract testng assertions and RestAssured code away from the test class into separate classes