package vividseat.hlgo.data;

import vividseat.hlgo.utils.Constants;
import vividseat.hlgo.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao extends AbstractDao<Person> {

    @Override
    protected void toDTO(ResultSet resultSet, Person dto) throws SQLException {
        dto.setName(resultSet.getString("name"));
        dto.setaCelebrity(resultSet.getBoolean("a_celebrity"));
    }

    @Override
    public Person get(Person id) throws DataLayerException {
        return null;
    }

    @Override
    public List<Person> getAll() throws DataLayerException {
        List<Person> result = new ArrayList<>();

        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement selectStatement = conn.prepareStatement(Constants.SELECT_ALL_SQL)
        ){
            ResultSet dbData = selectStatement.executeQuery();
            while (dbData.next()) {
                Person p = new Person();
                toDTO(dbData, p);
                result.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DataLayerException(ex.getMessage());
        }

        return result;
    }

    @Override
    public Person create(Person newElement) throws DataLayerException {
        return null;
    }
}
