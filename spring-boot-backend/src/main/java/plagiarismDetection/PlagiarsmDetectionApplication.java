package plagiarismDetection;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@SpringBootApplication
@RestController
public class PlagiarsmDetectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlagiarsmDetectionApplication.class, args);
	}
	@GetMapping("/home")
	public String Hello(){
		return "Hi";
	}



	public class FormDto {
		private String name;
		private int age;

		// Getters and setters

		// Constructors
	}
	@Configuration
	public class CorsConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://localhost:3000")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedHeaders("*");
		}
	}

	@RestController
	public class FormController {

		@PostMapping("/api/form")
		public String handleFormSubmission(@RequestBody String text) {
			// Process the received text
			System.out.println(text);

			// Send results
			return handleGetRequest(text);
		}

		@GetMapping("/get")
		public String handleGetRequest(String text) {
			// Process the GET request and obtain the result
			System.out.println("get");
			ApiResponse result = new ApiResponse( "Your desired result");


			return result.getMessage();
		}


		public class ApiResponse {
		private String message;

		public ApiResponse(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	public class FormSubmissionData {

		private String text;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		// Add other fields as needed

	}


}}
