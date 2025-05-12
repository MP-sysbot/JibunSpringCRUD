package Jibun.CRUD.DAO;

import Jibun.CRUD.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Person> index() {
        TypedQuery<Person> query = entityManager.createQuery("SELECT p FROM Person p", Person.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return entityManager.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personToUpdate = show(id);
        if (personToUpdate != null) {
            personToUpdate.setName(updatedPerson.getName());
            entityManager.merge(personToUpdate);
        }
    }

    @Transactional
    public void delete(int id) {
        Person person = show(id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}