package fr.tao.restclient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.tao.restclient.model.Course;

public class RestClient {
	
	private static final String GET_ALL_COURSES_API = "http://localhost:8082/api/courses";
	private static final String GET_COURSE_BY_ID_API = "http://localhost:8082/api/courses/{id}";
	private static final String CREATE_COURSE_API = "http://localhost:8082/api/courses";
	private static final String UPDATE_COURSE_API = "http://localhost:8082/api/courses/{id}";
	private static final String DELETE_COURSE_API = "http://localhost:8082/api/courses/{id}";

	static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {	
		System.out.println("******** callGetAllCoursesAPI() : ");
		callGetAllCoursesAPI();
		
		System.out.println("\n******** callGetCourseByIdAPI()");
		callGetCourseByIdAPI(2L);
		
		System.out.println("\n******** callCreateCourseAPI()");
		callCreateCourseAPI(new Course("RestTemplate Course", "Description for RestTemplate", false));
		
		System.out.println("\n******** callUpdateCourseAPI()");
		callUpdateCourseAPI(7L);
		
		System.out.println("\n******** callDeleteCourseAPI()");
		callDeleteCourseAPI(6L);
		
		System.out.println("\n******** callGetAllCoursesAPI() : ");
		callGetAllCoursesAPI();
	}
	
	private static void callGetAllCoursesAPI() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_COURSES_API, HttpMethod.GET, entity,
				String.class);
		System.out.println(result);
	}
	
	private static void callGetCourseByIdAPI(Long id) {
		Map<String, Long> map = new HashMap<>();
		map.put("id", id);
		
		Course course = restTemplate.getForObject(GET_COURSE_BY_ID_API, Course.class, map);
		
		System.out.println(course.getId());
		System.out.println(course.getTitle());
		System.out.println(course.getDescription());
		System.out.println(course.isAnimated());
	}
	
	private static void callCreateCourseAPI(Course course) {
		ResponseEntity<Course> response = restTemplate.postForEntity(CREATE_COURSE_API, course, Course.class);		
		System.out.println(response.getBody());
	}
	
	private static void callUpdateCourseAPI(Long id) {
		Map<String, Long> map = new HashMap<>();
		map.put("id", id);
		
		Course updatedCourse = new Course(id, "RestTemplate Course", "REST Client Consuming Restful API using RestTemplate", true);
		restTemplate.put(UPDATE_COURSE_API, updatedCourse, map);
		System.out.println(updatedCourse.getId());
		System.out.println(updatedCourse.getTitle());
		System.out.println(updatedCourse.getDescription());
		System.out.println(updatedCourse.isAnimated());
	}
	
	private static void callDeleteCourseAPI(Long id) {
		Map<String, Long> map = new HashMap<>();
		map.put("id", id);
		
		restTemplate.delete(DELETE_COURSE_API, map);
	}

}
