package dao;

import da.HibernateService;
import enit.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class TaskDao {
    private final SessionFactory sessionFactory= HibernateService.getSessionFactory();

    public TaskDao(SessionFactory sessionFactory) {

    }


    public List <Task> getAll(){
      Session session =sessionFactory.openSession(); //получение сессии
      Transaction transaction =session.beginTransaction();//получение сессии


      List <Task> list= (List <Task>) session.createCriteria (Task.class)//Логик
         .list();//Получение всего списка

      transaction.commit();//выполнение транзакции(заверщение)
      session.close();//закрытие сесси
      return list;
  }

  public void save (String task){
      Session session =sessionFactory.openSession();
      Transaction transaction =session.beginTransaction();

      session.save(new Task(task));//!!!!!

      transaction.commit();
      session.close();
  }

  public void delete (String task){
      Session session =sessionFactory.openSession(); //получение сессии
      Transaction transaction =session.beginTransaction();//получение сессии


      Task task1=(Task) session.createCriteria (Task.class)//Логика
            .add (Restrictions.eq("task",task))
              .list()
            .get(0);//Берёт первую попавшиесю

      session.delete(task1);


      transaction.commit();//выполнение транзакции(заверщение)
      session.close();//закрытие сесси
  }
}
