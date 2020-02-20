package vividseat.hlgo.business;

import vividseat.hlgo.data.Dao;
import vividseat.hlgo.data.DataLayerException;
import vividseat.hlgo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceBusinessImpl implements ServiceBusiness{

    @Autowired
    Dao<Person> personDao;

    @Override
    public List<Person> getAllPersons() throws BusinessException {
        try {
            List<Person> people = personDao.getAll();
            Person celebrity = people.stream()
                                .filter(p -> p.isACelebrity())
                                .findAny()
                                .orElse(null);

            List<Person> nonCelebrities = people.stream()
                    .filter(p -> !p.isACelebrity())
                    .collect(Collectors.toList());

            nonCelebrities.forEach(p -> p.setKnownCelebrity(celebrity));

            return people;

        } catch (DataLayerException e) {
            e.printStackTrace();
            throw new BusinessException(e.getCause(), e.getMessage());
        }
    }
}
