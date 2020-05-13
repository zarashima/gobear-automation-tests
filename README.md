# gobear-automation-tests

## Test Plan
Refer to [this](https://github.com/zarashima/gobear-automation-tests/wiki/Test-Plan) Wiki page for more details

## Requirements
* JDK 8+
* [Maven](https://maven.apache.org/install.html)

## Framework architecture
![framework](https://github.com/zarashima/gobear-automation-tests/blob/master/framework-architecture.png)
## Execution
* Basic Tests: `mvn clean test -DtestSuite=BasicTestsSuite -DbrowserName=chrome`
* Stretch Tests: `mvn clean test -DtestSuite=StretchTestsSuite -DbrowserName=chrome`

Reports are generated in TestReport folder after execution is completed with details logs and executed browser
