package tests.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraAddAttachmentToIssue {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://borealowl.atlassian.net/";

		String createJiraIssueResponse = given().header("Content-Type", "application/json").header("Authorization",
				"Basic Zm9yZXZlcmxlYXJuMkBnbWFpbC5jb206QVRBVFQzeEZmR0YwbnBJT3dSWTVGZkh1Z1BLOWZ6MlJfRUVRSTFrOHB2YkJxZHlTWXM0c3cyRnlrQUUwX1dHYkpycVJsZ3VyRHpxTm55NWpMOHp2M01pbFgyYW9EWXExTXFNQkctSk10ZDhTRHFNUmNBam1KQkp3bm5jYTBBeFdybnp1TEJuYUJQUURTaExLOTV4UUJLX3o0ZVVmRW43V1N5T01EVHNVUXhNU2QyQUk4b3VGVnhVPTM2QzcwNzMz")
				.body("{\r\n" + "  \"fields\": {\r\n" + "    \"project\":\r\n" + "    {\r\n"
						+ "        \"key\": \"SCRUM\"\r\n" + "    },\r\n"
						+ "    \"summary\": \"Testing Jira Attachment API through test automation - Add Attachment\",\r\n"
						+ "    \"issuetype\": {\r\n" + "      \"name\": \"Bug\"\r\n" + "    }\r\n" + "  }\r\n" + "}")
				.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response()
				.asString();

		JsonPath js = new JsonPath(createJiraIssueResponse);
		String issueId = js.getString("id");

		String addAttachemntResponse = given().header("Authorization",
				"Basic Zm9yZXZlcmxlYXJuMkBnbWFpbC5jb206QVRBVFQzeEZmR0YwbnBJT3dSWTVGZkh1Z1BLOWZ6MlJfRUVRSTFrOHB2YkJxZHlTWXM0c3cyRnlrQUUwX1dHYkpycVJsZ3VyRHpxTm55NWpMOHp2M01pbFgyYW9EWXExTXFNQkctSk10ZDhTRHFNUmNBam1KQkp3bm5jYTBBeFdybnp1TEJuYUJQUURTaExLOTV4UUJLX3o0ZVVmRW43V1N5T01EVHNVUXhNU2QyQUk4b3VGVnhVPTM2QzcwNzMz")
				.header("X-Atlassian-Token", "no-check")
				.multiPart("file", new File(
						"C:\\Users\\z004nhut\\Projects and Repositories\\Java\\eclipse-workspace\\My Repositories\\test-automation-api-restassured\\JiraAPIsAutomation\\JiraTestAttachment.png"))
				.pathParam("key", issueId).post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat()
				.statusCode(200).extract().asString();

		js = new JsonPath(addAttachemntResponse);
		System.out.println("ATTACHMENT ID:" + js.getString("id"));

	}

}
