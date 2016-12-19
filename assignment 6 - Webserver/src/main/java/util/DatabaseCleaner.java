package util;

import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;

public class DatabaseCleaner {

    private static final Class<?>[] ENTITY_TYPES = {
        Item.class,
        Bid.class,
        User.class
    };
    private final EntityManager em;

    public DatabaseCleaner(EntityManager entityManager) {
        em = entityManager;
    }

    //http://stackoverflow.com/questions/15501673/how-to-temporarily-disable-a-foreign-key-constraint-in-mysql
    public void clean() throws SQLException{
        em.getTransaction().begin();
        Query bliep = em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;");
        bliep.executeUpdate();
        for (Class<?> entityType : ENTITY_TYPES) {
            deleteEntities(entityType);
        }
        bliep = em.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;");
        bliep.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    private void deleteEntities(Class<?> entityType) {
        em.createQuery("delete from " + getEntityName(entityType)).executeUpdate();
    }

    protected String getEntityName(Class<?> clazz) {
        EntityType et = em.getMetamodel().entity(clazz);
        return et.getName();
    }
}
