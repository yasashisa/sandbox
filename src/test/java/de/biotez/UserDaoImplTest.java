package de.biotez;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author n3on
 * @since 22/05/14 23:15
 */
public class UserDaoImplTest {

    private UserDao userDao;

    private User user0;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        user0 = new User(0l, "A", "A");
        user1 = new User(1l, "B", "B");
        user2 = new User(2l, "C", "C");
        userDao = new UserDaoImpl(Sets.newHashSet(user0, user1, user2));
    }

    @Test
    public void testGetById() throws Exception {
        User actual = userDao.getById(0l);
        assertEquals(user0, actual);
    }

    @Test
    public void testGetById_notFound() throws Exception {
        User notFound = userDao.getById(7l);
        assertNull(notFound);
    }

    @Test
    public void testGetById_nullId() throws Exception {
        User nullUser = userDao.getById(null);
        assertNull(nullUser);
    }
}
