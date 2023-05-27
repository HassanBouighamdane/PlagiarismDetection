package plagiarismDetection;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.*;


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

		@PostMapping("/get")
		public ArrayList<String> handleGetRequest(@RequestParam("text") String text,
				@RequestParam("files") ArrayList<MultipartFile> files) throws IOException {
			ArrayList<String> rapport = new ArrayList<>();

			ArrayList<String> fichNames = new ArrayList<String>();
			for (MultipartFile file : files) {
				String filename = file.getOriginalFilename();
				fichNames.add(filename);
			}

			ArrayList<String> t = convertMultipartFilesToStrings(files);

			compare c = new compare();
			double percentage = c.countSimilarSentences(text, t);
			String perString = Double.toString(percentage);
			rapport.add(perString);

			for (int i = 0; i < t.size(); i++) {
				double taux1 = c.countSimilarSentences(text, t.get(i));
				String rappString = fichNames.get(i) + " est :" + taux1 + "%";
				rapport.add(rappString);
			}
			return rapport;

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

	}

}
