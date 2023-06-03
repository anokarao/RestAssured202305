package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenUserTests {
	
	@Test(priority = 1,dataProvider  = "data", dataProviderClass = DataProviders.class)
	public void testCreateUser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph) {
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void testGetuser(String username) {
		Response response=UserEndPoints.readUser(username);
		response.then().log().body();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3, dataProvider = "userNames", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username) {
		Response response=UserEndPoints.deleteUser(username);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

}
