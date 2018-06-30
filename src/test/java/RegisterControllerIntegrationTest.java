import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fiiadmission.SpringbootJwtApplication;
import com.fiiadmission.api.dto.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = SpringbootJwtApplication.class)
@AutoConfigureMockMvc
public class RegisterControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	private UserDTO generateUser() {

		UserDTO user = new UserDTO();
		user.setFirstName("Ion");
		user.setLastName("Popa");
		user.setEmail("IonP2@gmail.com");
		user.setUsername("IonP2");
		user.setPassword("bla");
		user.setHas2FA(false);
		return user;
	}

	@Test
	public void registerUser() throws Exception {

		mvc.perform(post("/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(generateUser())))
				.andExpect(status().isCreated());
//				.andExpect(content()
//						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//				.andExpect(jsonPath("$[0].name", is("bob")));

	}

}