package runer;

import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;


public class HibernateRunner {
    public static void main(String[] args) {
        Configuration conf = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
        StandardServiceRegistry registry = builder.build();
        SessionFactory factory = conf.buildSessionFactory(registry);
        Session session = factory.openSession();

        session.beginTransaction();
        Query query = session.createQuery("from UserEntity where id = :id");
        query.setParameter("id", 2);
        List list = query.list();
        list.stream().forEach(o -> System.out.println(((UserEntity) o).getSurname()));
        session.getTransaction().commit();

        session.close();
        factory.close();

    }
}
