import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
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
import org.springframework.web.client.RestTemplate;

import com.fiiadmission.SpringbootJwtApplication;
import com.fiiadmission.api.dto.UserDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJwtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterControllerIntegrationTest {

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	@TestConfiguration
	static class TestContextConfiguration {

		@Bean
		public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,
					SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf)
					.setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			requestFactory.setHttpClient(httpClient);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			return restTemplate;
		}
	}

	@Autowired
	private RestTemplate restTemplate;

	private UserDTO generateUser() {

		UserDTO user = new UserDTO();
		user.setFirstName("Ion");
		user.setLastName("Popa");
		user.setEmail("IonP@gmail.com");
		user.setUsername("IonP");
		user.setPassword("bla");
		user.setHas2FA(false);
		return user;
	}

	@Test
	public void registerUser() {

		headers.setContentType(MediaType.APPLICATION_JSON);

		UserDTO user = generateUser();

		HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(user, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/register"), HttpMethod.POST, entity,
				String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

	}

	private String createURLWithPort(String uri) {
		return "https://localhost:" + port + uri;
	}

}