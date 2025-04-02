package com.pbl5.autoattendance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pbl5.autoattendance.embedded.AuthorityId;
import com.pbl5.autoattendance.model.Authority;
import com.pbl5.autoattendance.model.Student;
import com.pbl5.autoattendance.model.StudentVector;
import com.pbl5.autoattendance.model.User;
import com.pbl5.autoattendance.repository.AuthorityRepository;
import com.pbl5.autoattendance.repository.StudentRepository;
import com.pbl5.autoattendance.repository.StudentVectorRepository;
import com.pbl5.autoattendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AutoattendanceApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private StudentVectorRepository studentVectorRepository;
	@Autowired
	private AuthorityRepository authorityRepository;

	public static void main(String[] args) {
		SpringApplication.run(AutoattendanceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {

			ObjectMapper objectMapper = new ObjectMapper();
			Path jsonPath = Paths.get("identity_embeddings.json");
			File jsonFile = jsonPath.toFile();
			Map<String, Object> identityData = objectMapper.readValue(jsonFile, Map.class);

			List<String> usernameList = new ArrayList<>(List.of("trung", "tai", "minh_hoang", "trung_phong"));
			for (int i = 0  ; i < usernameList.size() ; i++) {
				User user = User.builder()
						.username(usernameList.get(i))
						.password("12345")
						.enabled(true)
						.build();

				// Save user first to get the ID
				userRepository.save(user);

				// Create authority for the user
				Authority authority = new Authority();
				AuthorityId authorityId = new AuthorityId();
				authorityId.setUsername(user.getUsername());
				authorityId.setAuthority("ROLE_USER");
				authority.setId(authorityId);
				authority.setUser(user);
				authorityRepository.save(authority);

				Student student = Student.builder()
						.name(usernameList.get(i))
						.phoneNumber("1234567890")
						.email("abc"+i+"@sv1.udn.vn")
						.user(user)
						.build();

				String featureVectorJson = objectMapper.writeValueAsString(identityData.get(usernameList.get(i)));

				StudentVector studentVector = StudentVector.builder()
								.featureVector(featureVectorJson)
								.student(student)
								.build();

				studentRepository.save(student);
				studentVectorRepository.save(studentVector);
			}
		};
	}
}
