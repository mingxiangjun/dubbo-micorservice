import com.ming.dubbo.microservice.api.PersonService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author MingXiangjun
 * @create 2018-07-01 14:41
 */
public class ConsumerStarter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();

        PersonService service = (PersonService) context.getBean("personService");
        System.out.println("=======================final result is:"+service.buildPersonInfo());
    }
}
