package auction.dao;

import auction.domain.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryDAOJPAImpl implements CategoryDAO {

    EntityManagerFactory ef = Persistence.createEntityManagerFactory("db");
    EntityManager categories = ef.createEntityManager();

    public CategoryDAOJPAImpl() {
    }

    @Override
    public int count() {
        return (Integer) categories.createNativeQuery("SELECT count(*) FROM Category")
                .getSingleResult();
    }

    @Override
    public void create(Category category) {
        categories.getTransaction().begin();
        categories.persist(category);
        categories.getTransaction().commit();
    }

    @Override
    public void edit(Category category) {
        categories.merge(category);
    }

    @Override
    public List<Category> findAll() {
        return categories.createQuery("SELECT * FROM Item").getResultList();
    }

    @Override
    public void remove(Category category) {
        categories.remove(category.getDiscription());
    }

    @Override
    public Category find(String description) {
        return categories.find(Category.class, description);
    }

    @Override
    public List<Category> findByDescription(String description) {
        return categories.createQuery("SELECT cat FROM Category WHERE cat.description = ':description'").setParameter("description", description).getResultList();
    }
}
