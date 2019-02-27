package da;

import enit.Task;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateService {

    private HibernateService(){}//Создали конструктор к кторорому не сможем отсылаться тк как конструктор private

    private static  final     StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

    private static  final     SessionFactory sessionFactory = new MetadataSources( registry ) //.addAnnotatedClass() .buildMetadata().buildSessionFactory();
            //    .addAnnotatedClass(Empl.class)//Указывваем таблицу
                .addAnnotatedClass(Task.class)
                .buildMetadata()
                .buildSessionFactory();
    public static  SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
