import entities.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class DbWorker {
  private SessionFactory sessionFactory;

  public DbWorker() {
    ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
           .configure()
           .build();
    Metadata metadata = new MetadataSources(serviceRegistry)
           .getMetadataBuilder().build();
    this.sessionFactory = metadata.getSessionFactoryBuilder().build();

  }

  public List<BookEntity> getAllBooks() {
    List resultList = null;
    try (final Session currentSession =  sessionFactory.openSession()) {

        resultList=  currentSession
              .createQuery("FROM "+BookEntity.class.getSimpleName(), BookEntity.class)
              .getResultList();
      System.out.println(resultList);
     // currentSession.getTransaction().commit();
    }
    return resultList;
  }
    public boolean save(BookEntity bookEntity){
      try (final Session currentSession = sessionFactory.openSession()) {
        final Transaction tr=currentSession.beginTransaction();
        currentSession.save(bookEntity);
        tr.commit();
        return true;
    }
  }
  public boolean delete(BookEntity bookEntity){
    try(final Session currentSession=sessionFactory.openSession()){
      return  currentSession
              .createQuery("DELETE FROM BookEntity WHERE id=1;").executeUpdate()==1;

    }
  }
}