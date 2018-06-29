import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiiadmission.api.dto.QrDTO;
import com.fiiadmission.domain.User;
import com.fiiadmission.repository.UserRepository;
import com.fiiadmission.service.UserService;
import com.fiiadmission.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTest {
 
    @TestConfiguration
    static class TestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
 
    @Autowired
    private UserService userService;
 
    @MockBean
    private UserRepository userRepository;
    
    @Test
    public void testGenerateQrUrl() throws UnsupportedEncodingException
    {
    	User user= new User();
    	user.setFirstName("Ion");
    	user.setLastName("Popa");
    	user.setEmail("IonP@gmail.com");
    	user.setUsername("IonP");
    	QrDTO qr= userService.generateQRUrl(user);
    	assertNotNull(qr);
    }
      
}