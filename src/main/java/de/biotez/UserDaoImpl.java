package de.biotez;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author n3on
 * @since 22/05/14 22:58
 */
public class UserDaoImpl implements UserDao {

    private Set<User> users = Sets.newHashSet(
            new User(0l, "Kasia", "Bednarek"),
            new User(1l, "Bartek", "Bednarek"),
            new User(2l, "Oliwia", "Bednarek")
    );

    public UserDaoImpl(Set<User> users) {
        this.users = users;
    }

    @Override
    public User getById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

}
