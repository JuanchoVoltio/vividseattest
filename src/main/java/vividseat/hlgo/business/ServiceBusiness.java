package vividseat.hlgo.business;

import vividseat.hlgo.model.Person;

import java.util.List;

public interface ServiceBusiness {
    List<Person> getAllPersons() throws BusinessException;
}
