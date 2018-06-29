import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fiiadmission.api.dto.QrDTO;
import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.User;
import com.fiiadmission.repository.AdmissionDataRepository;
import com.fiiadmission.repository.UserRepository;
import com.fiiadmission.service.AdmissionDataService;
import com.fiiadmission.service.UserService;
import com.fiiadmission.service.impl.AdmissionDataServiceImpl;
import com.fiiadmission.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class AdmissionDataServiceTest {
 
    @TestConfiguration
    static class TestContextConfiguration {
  
        @Bean
        public AdmissionDataService admissionDataService() {
            return new AdmissionDataServiceImpl();
        }
    }
    
    @Autowired
    AdmissionDataService admissiondataService;
    
    @MockBean
    private AdmissionDataRepository admissiondataRepository;
    
    private User generateUser()
    {

    	User user= new User();
    	user.setFirstName("Ion");
    	user.setLastName("Popa");
    	user.setEmail("IonP@gmail.com");
    	user.setUsername("IonP");
    	return user;
    }
    
    private AdmissionData generateAdmissionData()
    {

    	AdmissionData data=new AdmissionData();
    	data.setAddress("test");
    	data.setCnp("123456789");
    	data.setAdmissionType("Preadmission");
    	data.setGeneralGrade(new Float(10.00));
    	data.setBacGrade(new Float(10.00));
    	return data;
    }
    
    @Test
    public void createAdmissionData() 
    {
    	AdmissionData data=generateAdmissionData();
    	User user=generateUser();
    	data.setUser(user);
    	Mockito.when(admissiondataRepository.save(Matchers.any(AdmissionData.class))).thenReturn(generateAdmissionData());
    	assertNotNull(admissiondataService.create(data));
    }
    
   
}