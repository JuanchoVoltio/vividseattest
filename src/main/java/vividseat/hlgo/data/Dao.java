package vividseat.hlgo.data;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Dao<T> {

    T get(T id) throws DataLayerException;

    List<T> getAll() throws DataLayerException;

    T create(T newElement) throws DataLayerException;
}
