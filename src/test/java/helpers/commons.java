package helpers;

import io.restassured.response.ResponseBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class commons {

    public ResponseBody getResponseBodyAndSaveToFile(String apiURI, String searchTerm, String pathLocation) {
        ResponseBody jsonObject =
                given()
                        .when()
                        .get(apiURI + searchTerm)
                        .then()
                        .assertThat().statusCode(200)
                        .extract().response().body();
        Path path = Paths.get(pathLocation);
        try {
            Files.write(path, jsonObject.asByteArray());
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
        return jsonObject;
    }

    public boolean checkTitleFromFile(String titlename){
        JSONParser parser = new JSONParser(); // json parser used for read-only access to json data
        Object obj;  //Read json file path where json file in system
        Boolean result = false;
        int i=0;
        try {
            obj = parser.parse(new FileReader("./src/test/test.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray pages = (JSONArray) jsonObject.get("pages");
            Iterator iterator = pages.iterator();
            while (iterator.hasNext()) {
                if (((JSONObject) pages.get(i)).get("title").equals(titlename)){
                    System.out.println("Got the page with title " + titlename);
                    result = true;
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
