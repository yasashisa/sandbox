package de.biotez;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author n3on
 * @since 22/05/14 23:31
 */
public class CachedUserDao implements UserDao {

    private final UserDao decorated;
    private final Map<Long, User> cache;

    public CachedUserDao(UserDao decorated) {
        this.decorated = decorated;
        this.cache = Maps.newHashMap();
    }

    @Override
    public User getById(Long id) {
        User user = cache.get(id);
        if (user == null) {
            user = decorated.getById(id);
            cache.put(id, user);
        }
        return user;
    }

}
