package github;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pojo.GithubUserPojo;

public class GithubUsers {
	String URI="https://api.github.com/users";
	HttpGet get;
	HttpResponse response;

	@BeforeClass
	public void getRequest() throws ClientProtocolException, IOException{
		HttpClient client=HttpClientBuilder.create().build();
		get=new HttpGet(URI);
		response=client.execute(get);
	}
	
	@Test
	public void testStatusCode(){
		int statusCode=response.getStatusLine().getStatusCode();
		Assert.assertEquals(200, statusCode);
	}
	
	@Test
	public void testEntity() throws ParseException, IOException{
		GithubUserPojo[] githubUsers;
		String json;
		json=EntityUtils.toString(response.getEntity());
		ObjectMapper map=new ObjectMapper();
		githubUsers=map.readValue(json, GithubUserPojo[].class);
		for(GithubUserPojo obj:githubUsers){
			System.out.println(obj.getLogin());
		}
	}

}
