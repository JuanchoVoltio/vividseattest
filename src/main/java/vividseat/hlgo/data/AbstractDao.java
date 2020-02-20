package vividseat.hlgo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public abstract class AbstractDao<T> implements Dao<T> {

    @Autowired
    protected DataSource dataSource;

    protected abstract void toDTO(ResultSet resultSet, T dto) throws SQLException;
}
