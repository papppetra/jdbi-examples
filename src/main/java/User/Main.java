package User;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());

        List <User> users = jdbi.withExtension(UserDao.class, dao ->{
            dao.createTable();
            var faker = new UserGenerator(new Locale("hu"));
            for(int i = 1; i <= 50; i++){
                User newUser = faker.getUser();
                dao.insert(newUser);
            }
            dao.findById(15).ifPresent(System.out::println);

            dao.findByUserName("borbás.virág").ifPresent(System.out::println);

            Optional<User> optionalUser = dao.findById(32);
            User deleteUser;
            if(optionalUser.isPresent()){
                deleteUser = optionalUser.get();
                dao.delete(deleteUser);
            }

            //dao.list().forEach(System.out::println);
            return dao.list();
        });
        users.forEach(System.out::println);






    }

}
