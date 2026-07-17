# Automation Exercise

A Selenium WebDriver automation framework built using Java, TestNG, and Maven to automate end-to-end test scenarios for the Automation Exercise web application.

The framework follows the Page Object Model (POM) design pattern and implements reusable components for maintainable and scalable test automation.


## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Extent Reports
- JSON Test Data
- Git & GitHub
- Jenkins

## Application Under Test

Website:
https://automationexercise.com/

Automated scenarios include:

- User Login
- Product Search
- Product Listing Validation
- Add Products to Cart
- Remove Products from Cart
- Checkout Flow
- Payment Process
- Order Confirmation


## Framework Features

- Page Object Model design pattern

- Reusable page classes and components

- Thread-safe WebDriver management using ThreadLocal

- Cross-browser execution support

- Maven-based project structure

- TestNG test execution and grouping

- Smoke, sanity, and Regression test suites

- Data-driven testing using JSON files

- Custom TestNG listeners

- Automatic screenshot capture on test failure

- Retry mechanism for failed tests

- Extent HTML reporting

- Parallel test execution support

- Jenkins Freestyle job integration


## Project Structure

```text
AutomationExercise
│
├── src
│   ├── test
│   │   ├── java
│   │   │   └── seleniumFramework
│   │   │       ├── base
│   │   │       │
│   │   │       ├── dataProviders
│   │   │       │
│   │   │       ├── listeners
│   │   │       │
│   │   │       └── tests
│   │   │
│   │   └── resources
│   │       └── seleniumFramework
│   │   	   └── data
│   │				├── loginData.json
│   │				├── paymentData.json    
│   │
│   └── main
│       ├── java
│       │   └── seleniumFramework
│       │       │
│       │       ├── base
│       │       │
│       │       ├── pages
│       │       │
│       │       ├── reports
│       │       │
│       │       └── utilities
│       │
│	    └── resources
│           └── config.properties
│
├──testSuites
│   ├── testng.xml
│   ├── testng_sanity.xml
│	└── testng_smoke.xml
│
├── LICENSE
│
├── pom.xml
│
├── README.md
│
└── .gitignore
```

## Prerequisites

Before running the project, ensure the following software is installed:

- Java JDK 17 or later
- Apache Maven
- Git
- Google Chrome, Microsoft Edge, or Mozilla Firefox
- Jenkins (optional, for automated execution)

Verify the installation:

```bash
java -version
mvn -version
git --version
```


## Framework Design

### Page Object Model (POM)

The framework follows the Page Object Model design pattern where each web page is represented as a separate Java class.

Benefits:
- Improves code maintainability
- Separates test logic from page locators
- Provides reusable page methods

Example:

LoginTest.Java
	|
	|
LoginPage.Java
	|
	|
Selenium WebElements


### Base Page

BasePage contains common reusable methods used across all page classes.

Examples:
- Explicit waits
- Element visibility checks
- Click handling
- Common Selenium operations

All page classes extend BasePage.


### Test Base Setup

BaseTest manages common test setup and teardown operations.

Responsibilities:
- Loading configuration properties
- Initializing WebDriver
- Opening application URL
- Managing test preconditions


### WebDriver Management

DriverFactory is responsible for WebDriver creation and management.

Features:
- Supports multiple browsers
- Uses ThreadLocal WebDriver for thread safety
- Enables parallel test execution

Supported browsers:
- Chrome
- Edge
- Firefox


### Data Driven Testing

Test data is maintained separately from test scripts.

Implementation:
- JSON test data files
- TestNG DataProvider

Benefits:
- Avoids hardcoded test data
- Allows execution with multiple datasets


### Test Execution Management

TestNG is used for managing test execution.

Implementation:
- TestNG annotations
- Groups (Smoke, Regression, Sanity)
- Suite XML files
- Parallel execution


### Reporting and Logging

Custom TestNG listeners are implemented for test execution monitoring.

Features:
- Extent HTML reports
- Failure screenshots
- Test execution status tracking


### Retry Mechanism

TestNG Retry Analyzer is implemented to rerun failed tests.

Useful for handling:
- Temporary failures
- Synchronization issues
- Intermittent failures


## Test Execution

The framework supports execution using Maven profiles and browser parameterization.


### Run Regression Suite

Execute regression tests on Chrome:
```bash
mvn test -PRegression -DbrowserName=chrome
```

Execute regression tests on Edge:
```bash
mvn test -PRegression -DbrowserName=edge
```

Execute regression tests on Firefox:
```bash
mvn test -PRegression -DbrowserName=firefox
```

### Run Smoke Suite

Execute smoke suite:
```bash
mvn test -PSmoke -DbrowserName=edge
```

### Run Sanity Suite
Execute sanity suite:
```bash 
mvn test -PSanity -DbrowserName=chrome
```


## TestNG Groups

The framework uses TestNG groups to organize test cases based on their execution scope and purpose.

- **Smoke** - Validates the application's critical workflows to ensure the build is stable for further testing.
- **Sanity** - Verifies core functionalities after changes to confirm that key features continue to work as expected.
- **Regression** - Executes the complete test suite to verify that existing functionality remains unaffected by new changes or enhancements.


## Reporting

The framework uses **Extent Reports** to generate HTML execution reports after each test run.

Features:
- Test execution summary
- Passed, Failed, and Skipped test status
- Automatic screenshots for failed test cases
- Execution details and timestamps

Report location:
```text
reports/index.html
```


## Data-Driven Testing

The framework supports data-driven testing using **JSON** files and **TestNG DataProviders**.

Features:
- Externalized test data
- Reusable test methods with multiple datasets
- Separation of test logic and test data

Example test data files:
```text
loginData.json
paymentData.json
```

## Jenkins Integration

The framework can be executed through a Jenkins Freestyle project to automate test execution.

The Jenkins job performs the following steps:

- Pulls the latest source code from GitHub
- Builds the project using Maven
- Executes the selected test suite
- Generates execution reports

Example Maven command:
```bash
mvn test -PRegression -DbrowserName=edge
```


## Future Enhancements

Potential improvements for the framework include:

- Selenium Grid for distributed test execution
- Docker support for containerized execution
- GitHub Actions workflow for automated builds
- Allure Reports integration
- API automation using REST Assured
- Cucumber BDD integration
- Database validation support