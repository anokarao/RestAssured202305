package api.endpoints;

public class Routes {
	
	/* 
	Swagger URI --> https://petstore.swagger.io

	Create user(Post) : https://petstore.swagger.io/v2/user
	Get user (Get): https://petstore.swagger.io/v2/user/{username}
	Update user (Put) : https://petstore.swagger.io/v2/user/{username}
	Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

	*/
	public static String base_URI="https://petstore.swagger.io/v2";
	
	//user module
	public static String userPost_URL=base_URI+"/user";
	public static String userGet_URL=base_URI+"/user/{username}";
	public static String userPut_URL=base_URI+"/user/{username}";
	public static String userDelete_URL=base_URI+"/user/{username}";
	
	//Store module
    
			//Here you will create Store module URL's

	 //Pet module
	    	//Here you will create Pet module URL's
	    

}
