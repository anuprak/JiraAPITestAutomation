package tests.support;

public class TestPayload {
	
	public static String createBasicIssuePayload(String key, String summary, String issueType)
	{
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"" + key + "\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"" + summary +"\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"" + issueType + "\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
