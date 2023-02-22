package com.restful.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static int  userCount = 3;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"adam", LocalDate.now().minusYears(30) ));
        users.add(new User(2,"eve", LocalDate.now().minusYears(25) ));
        users.add(new User(3,"sid", LocalDate.now().minusYears(27) ));
    }
    public List<User> findAll() {
        return users;
    }
    public User findById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }
    public void deleteById(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
