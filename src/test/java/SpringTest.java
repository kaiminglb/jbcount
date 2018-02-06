
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTest {
	private ApplicationContext ac =new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    //SessionFactory测试
    @Test
    public void testSessionFactory() throws Exception{
        SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
        System.out.println(sessionFactory);
    }

}
