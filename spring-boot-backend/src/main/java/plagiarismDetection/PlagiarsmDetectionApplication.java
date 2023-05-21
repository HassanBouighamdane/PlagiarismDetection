package plagiarismDetection;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.gwt.user.client.rpc.core.java.util.ArrayList_CustomFieldSerializer;

import java.io.IOException;
import java.util.List;
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
		public String handleFormSubmission(@RequestPart("text") String text, @RequestPart("file") MultipartFile file) {
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



		public List<String> convertMultipartFilesToStrings(List<MultipartFile> files) throws IOException {
			List<String> fileContents = new ArrayList();
		
			for (MultipartFile file : files) {
				String fileContent = convertMultipartFileToString(file);
				fileContents.add(fileContent);
			}
		
			return fileContents;
		}

		public String convertMultipartFileToString(MultipartFile file) throws IOException {
			StringBuilder fileContentBuilder = new StringBuilder();
		
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
				String line;
		
				while ((line = reader.readLine()) != null) {
					fileContentBuilder.append(line);
					fileContentBuilder.append("\n");
				}
			}
		
			return fileContentBuilder.toString();
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
