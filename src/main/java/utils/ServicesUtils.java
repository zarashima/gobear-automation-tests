package utils;

import helper.StringConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ServicesUtils {

	public enum HttpMethod {
		GET("get"), POST("post");

		private final String method;

		HttpMethod(String method) {
			this.method = method;
		}
	}

	private static final RequestSpecification request = given().accept(ContentType.JSON);

	public static Response execute(String endpoint, HttpMethod method) {
		return execute(endpoint, method, true);
	}

	public static Response execute(String endpoint, HttpMethod method, boolean verifyStatusCode) {
		Response response = null;
		if (method == HttpMethod.GET) {
			response = request.urlEncodingEnabled(false).get(endpoint);
		} else if (method == HttpMethod.POST) {
			response = request.urlEncodingEnabled(false).post(endpoint);
		}
		assert response != null;
		if (verifyStatusCode) {
			response.then().statusCode(StringConstants.SUCCESS_RESPOND_CODE);
		}
		return response;
	}

	public static String getCurrentTravelEndpoint(String baseUrl) {
		return baseUrl.replaceAll("^[^?]+", PropertyUtils.getInstance().getTravelEndPoint());
	}
}
