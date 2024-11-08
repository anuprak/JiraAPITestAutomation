package tests.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import tests.support.TestPayload;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class JiraCreateIssue {

	@Test 
	public void createIssue()
	{
		RestAssured.baseURI = "https://borealowl.atlassian.net/";

		String createJiraIssueResponse = given().header("Content-Type", "application/json").header("Authorization",
				"Basic Zm9yZXZlcmxlYXJuMkBnbWFpbC5jb206QVRBVFQzeEZmR0YwbnBJT3dSWTVGZkh1Z1BLOWZ6MlJfRUVRSTFrOHB2YkJxZHlTWXM0c3cyRnlrQUUwX1dHYkpycVJsZ3VyRHpxTm55NWpMOHp2M01pbFgyYW9EWXExTXFNQkctSk10ZDhTRHFNUmNBam1KQkp3bm5jYTBBeFdybnp1TEJuYUJQUURTaExLOTV4UUJLX3o0ZVVmRW43V1N5T01EVHNVUXhNU2QyQUk4b3VGVnhVPTM2QzcwNzMz")
				.body(TestPayload.createBasicIssuePayload("SCRUM", "Issue Creation", "Bug"))
				.log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract()
				.response().asString();

		JsonPath js = new JsonPath(createJiraIssueResponse);
		String issueId = js.getString("id");
		System.out.println("ISSUE ID:" + issueId);
	}
}
