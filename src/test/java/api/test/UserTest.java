package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		logger=LogManager.getLogger(this.getClass());
		userPayload.setId(faker.number().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger.debug("debugging");
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("*********creating user*********");
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Created*********");
	}
	@Test(priority = 2,enabled=true)
	public void testGetUserByName() {
		logger.info("*********Reading user*********");
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User is displayed*********");
	}
	@Test(priority = 3,enabled=true)
	public void testUpdateUserByName() {
		logger.info("*********Updating user*********");
		//updating payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(userPayload, this.userPayload.getFirstName());
		response.then().log().body();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		Response updatedResponse=UserEndPoints.readUser(this.userPayload.getFirstName());
		updatedResponse.then().log().body();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User updated*********");
	}
	
	@Test(priority = 4,enabled=true)
	public void testDeleteUserByName() {
		logger.info("*********Delete user*********");
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Deleted*********");
	}
}
