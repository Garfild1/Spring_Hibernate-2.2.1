package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Alex = new User("Alex", "Alex_T", "VT@mail.ru");
      User Bob = new User("Bob", "Bob_T", "BT@mail.ru");
      User John = new User("John", "John_SS", "SS@gmail.com");
      User Mark = new User("Mark", "Mark_WV", "MV@mail.ru");

      Car Tesla = new Car("Tesla", 1);
      Car Mercedes = new Car("Mercedes", 6);
      Car Audi = new Car("Audi", 8);
      Car BMW = new Car("BMW", 7);

      userService.add(Alex.setCar(Tesla).setUser(Alex));
      userService.add(Bob.setCar(Mercedes).setUser(Bob));
      userService.add(John.setCar(Audi).setUser(John));
      userService.add(Mark.setCar(BMW).setUser(Mark));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }
      System.out.println("--------------------------------");

      System.out.println(userService.getUserByCar("Tesla", 1));
      System.out.println(userService.getUserByCar("Mercedes", 6));
      System.out.println(userService.getUserByCar("Audi", 8));
      System.out.println(userService.getUserByCar("BMW", 7));



      context.close();
   }
}