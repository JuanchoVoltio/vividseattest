package vividseat.hlgo.app;

import vividseat.hlgo.business.BusinessException;
import vividseat.hlgo.business.ServiceBusiness;
import vividseat.hlgo.model.Person;
import vividseat.hlgo.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class ServiceControllerTest {

    @Mock
    ServiceBusiness mockedServiceBusiness;

    @InjectMocks
    ServiceController testSubject;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllPersons_should_return_error_message_and_http_500_code_when_exception_is_thrown() throws BusinessException {
        //GIVEN
        String errorMessage = "Exception for test purposes";
        String expectedErrMsg = "An error has ocurred at business layer. Exception for test purposes";
        Mockito.when(mockedServiceBusiness.getAllPersons()).thenThrow(new BusinessException(new Exception(), errorMessage));

        //WHEN
        ResponseEntity<List<Person>> result = testSubject.getAllPeople();

        //THEN
        assertEquals("Oops, messages aren't the same.", expectedErrMsg, result.getHeaders().get(Constants.HTTP_HEADER_ERR_MSG_FIELD_NAME).get(0));
    }

    @Test
    public void getTheCelebrity_should_return_ok() throws BusinessException {
        //GIVEN
        String errorMessage = "Exception for test purposes";
        String expectedErrMsg = "An error has ocurred at business layer. Exception for test purposes";
        Person celebrity = new Person("Zardu Hasselfrau", Boolean.TRUE);
        Person person1 = new Person("Procyon Lottor", Boolean.FALSE);
        Person person2 = new Person("Zardu Hasselfrau", Boolean.FALSE);
        List<Person> people = Arrays.asList(celebrity, person1, person2);

        Mockito.when(mockedServiceBusiness.getAllPersons()).thenReturn(people);

        //WHEN
        ResponseEntity<Person> result = testSubject.getTheCelebrity();

        //THEN
        assertTrue("Oops, couldn't find the celebrity.", result.getBody().isACelebrity());
    }
}