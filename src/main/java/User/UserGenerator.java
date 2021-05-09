package User;

import com.github.javafaker.Faker;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.ZoneId;
import java.util.Locale;

public class UserGenerator {
    private Faker faker;

    public UserGenerator(Locale locale) {

        this.faker = new Faker(locale);
    }

    public User getUser(){
        return User.builder()
                .username(faker.name().username())
                .password(DigestUtils.md5Hex(faker.internet().password()))
                .name(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .gender(faker.options().option(User.Gender.FEMALE, User.Gender.MALE))
                .birthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .enabled(faker.bool().bool())
                .build();

    }
}
