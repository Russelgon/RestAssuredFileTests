package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTests {

    FilesSystem fl = new FilesSystem();
    FilesData fData = new FilesData();

    @BeforeTest
    public static void setUpBaseURI() {
        // base file.io URI
        RestAssured.baseURI = "https://file.io";
    }

    @Test
    public void uploadFile() {
        // Uploiding Files with uniq formats
        try {
            for (int i = 0; i < fl.listOfFiles(fData.getPathFile()).size(); i++) {
                Response response =
                        given().multiPart(new File(fData.getPathFile() + fl.listOfFiles(fData.getPathFile()).get(i)))
                                .when()
                                    .post().
                                then()
                                    .log().all().extract().response();
                Assert.assertEquals(response.statusCode(), 200);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void fileSizeBorder() {
        // File size cheking for being 0GB > but <= 2GB
        for (int i = 0; i < fl.listOfFiles(fData.getPathFileForCreation()).size(); i++) {
            try {
                Response response =
                        given().multiPart(new File(fData.getPathFileForCreation() + fl.listOfFiles(fData.getPathFileForCreation()).get(i)))
                                .when()
                                    .post()
                                .then()
                                    .log().all().extract().response();
                if (fl.listOfFiles(fData.getPathFileForCreation()).get(i).contains("Zero_")){
                    Assert.assertEquals(response.statusCode(), 400);
                } else {
                    Assert.assertEquals(response.statusCode(), 200);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Test
    public void successAuthProfileTest() {
        // Succes of Authorization with getting profile information
        try {
            System.out.println(given()
                        .auth().oauth2(fData.getToken())
                    .when()
                        .get("/me")
                    .then()
                        .log().all().extract().jsonPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
