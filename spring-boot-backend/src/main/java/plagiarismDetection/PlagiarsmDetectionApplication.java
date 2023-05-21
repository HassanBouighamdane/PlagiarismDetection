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
			return handleGetRequest(text,"is good");
		}

		@GetMapping("/get")
		public String handleGetRequest(String text,String t2) {
			// Process the GET request and obtain the result
			System.out.println(text);
			ApiResponse result = new ApiResponse(text,t2);


			return result.getMessage()+result.getT2();
		}


		public class ApiResponse {
		private String message;
		private String t2;


		public String getT2() {
			return t2;
		}

		public void setT2(String t2) {
			this.t2 = t2;
		}

		public ApiResponse(String message,String t2) {
			this.message = message;
			this.t2 = t2;
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
