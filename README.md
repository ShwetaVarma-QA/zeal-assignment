# zeal-assignment

## Project Details:

    Project is about automating APIs. I have used Rest Assured Automation using Cucumber framework in JAVA


* Automated scenarios can be found at

       "src/resources/featureFiles/"

* Reports generated only after execution will be available at

      "report" folder

## Execution of Tests
### To run all scenarios use following command
       "./gradlew allScenarios"

### To run specific scenarios use following commands
* For executing scenarios of SearchPage

       "./gradlew cucumber -P tags=@SearchPage"
* For executing scenarios of PageDetails

       "./gradlew cucumber -P tags=@PageDetails"