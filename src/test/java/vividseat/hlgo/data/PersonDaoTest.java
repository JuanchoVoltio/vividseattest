package vividseat.hlgo.data;

import vividseat.hlgo.app.Application;
import vividseat.hlgo.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest()
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PersonDaoTest {

    @Mock
    DataSource dataSource;

    @Mock
    ResultSet testResultSet;

    @Mock
    Connection conn;

    @Mock
    PreparedStatement statement;

    @Autowired
    @InjectMocks
    PersonDao testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject.dataSource = dataSource;
    }

    @Test
    public void should_fill_store_object_correctly_when_resultset_is_not_empty() throws SQLException {
        //GIVEN
        Person expectedData = new Person("W", Boolean.TRUE);
        Person result = new Person();
        ResultSet testResultSet = Mockito.mock(ResultSet.class);
        Mockito.when(testResultSet.getString(Mockito.anyString())).thenReturn("W");
        Mockito.when(testResultSet.getBoolean(Mockito.anyString())).thenReturn(Boolean.TRUE);

        //WHEN
        testSubject.toDTO(testResultSet, result);

        //THEN
        Assert.assertEquals("names don't match", expectedData.getName(), result.getName());
        Assert.assertEquals("isACelebrity fields don't match", expectedData.isACelebrity(), result.isACelebrity());
    }

    @Test
    public void should_return_a_list_when_people_are_found_in_db() throws SQLException, DataLayerException {
        //GIVEN
        Person expectedData1 = new Person("W", Boolean.TRUE);
        Person expectedData2 = new Person("X", Boolean.FALSE);
        List<Person> expectedResult = new ArrayList<>();
        expectedResult.add(expectedData1);
        expectedResult.add(expectedData2);
        List<Person> result;
        when(dataSource.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(testResultSet);
        when(testResultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        //Reading data for first element.
        when(testResultSet.getString(Mockito.anyString())).thenReturn("W")
                //Reading data for second element.
                .thenReturn("X");

        Mockito.when(testResultSet.getBoolean(Mockito.anyString())).thenReturn(Boolean.TRUE)
                .thenReturn(Boolean.FALSE);

        //WHEN
        result = testSubject.getAll();

        //THEN
        Assert.assertTrue("name's don't match", result.contains(expectedResult.get(0)) );
        Assert.assertTrue("name's don't match", result.contains(expectedResult.get(1)) );
    }

}