package service;

import com.phystrix.demo.dao.daoobject.ProfileDO;
import com.phystrix.demo.service.ProfileService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class ProfileServiceTest {
	
	@Autowired
    private ProfileService service;

    
    @Test
    public void testGetProfile() throws Exception {
        ProfileDO profileDo = service.getUserProfile("1");
        System.out.println(profileDo.toString());
        assertThat(profileDo.getUserId(), equalTo("1"));
    }
    
}
