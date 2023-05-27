package plagiarismDetection;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		/*
		 * @PostMapping("/api/form")
		 * public String handleFormSubmission(@RequestPart("text") String
		 * text, @RequestPart("file") MultipartFile file) {
		 * // Process the received text
		 * System.out.println(text);
		 * 
		 * // Send results
		 * return handleGetRequest(text);
		 * }
		 */
		@PostMapping("/get")
		public ArrayList<String> handleGetRequest(@RequestParam("text") String text, @RequestParam("files") ArrayList<MultipartFile> files) throws IOException {
			// Process the GET request and obtain the result
			ArrayList<String> t = convertMultipartFilesToStrings(files);
			

			return t;

		}

		public ArrayList<String> convertMultipartFilesToStrings(ArrayList<MultipartFile> files) throws IOException {
			ArrayList<String> fileContents = new ArrayList();

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

	}

}
