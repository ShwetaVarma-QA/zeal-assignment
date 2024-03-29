package stepDefinition;

import helpers.commons;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.io.FileReader;
import java.time.LocalDate;

public class stepDefinitionImplementation {

    @Given("A client of the API")
    public void getTheResponse() {
    }

    @When("A search for pages containing for {string} is executed")
    public void response(String searchTerm) {
        commons object = new commons();
        Assert.assertNotNull(
                object.getResponseBodyAndSaveToFile("https://api.wikimedia.org/core/v1/wikipedia/en/search/page?q=", searchTerm, "./src/test/test.json")
        );
    }

    @Then("A page with the title {string} is found")
    public void responseVerify(String string) {
        commons object = new commons();
        Assert.assertTrue(object.checkTitleFromFile(string));
    }

    @Given("The result for {string} search contains {string}")
    public void checkSearchTerm(String searchTerm, String titleName) {
        commons object = new commons();
        object.getResponseBodyAndSaveToFile("https://api.wikimedia.org/core/v1/wikipedia/en/search/page?q=", searchTerm, "./src/test/searchResult.json");
        Assert.assertTrue(object.checkTitleFromFile(titleName));
    }

    @When("The page details for {string} are requested")
    public void getPageDetails(String pageName) {
        pageName = pageName.replace(" ", "_");
        commons object = new commons();
        Assert.assertNotNull(
                object.getResponseBodyAndSaveToFile("https://api.wikimedia.org/core/v1/wikipedia/en/page/", pageName, "./src/test/pageDetails.json")
        );
    }

    @Then("It has a latest timestamp > {string}")
    public void checkCondition(String providedDate) {
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader("./src/test/pageDetails.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject getLatest = (JSONObject) jsonObject.get("latest");
            String string1 = getLatest.get("timestamp").toString();
            LocalDate date = LocalDate.parse(string1.split("T")[0]);
            Assert.assertTrue(date.isAfter(LocalDate.parse(providedDate)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}