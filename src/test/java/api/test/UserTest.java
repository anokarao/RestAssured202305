package api.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.number().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		Response response=UserEndPoints.createUser(userPayload);
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2,enabled=false)
	public void testGetUserByName() {
		Response response=UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 3,enabled=false)
	public void testUpdateUserByName() {
		
		//updating payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(userPayload, this.userPayload.getFirstName());
		response.then().log().body();
		assertEquals(response.getStatusCode(), 200);
		
		Response updatedResponse=UserEndPoints.readUser(this.userPayload.getFirstName());
		updatedResponse.then().log().body();
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4,enabled=false)
	public void testDeleteUserByName() {
		Response response=UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
}
