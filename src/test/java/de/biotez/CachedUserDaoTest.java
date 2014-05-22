package de.biotez;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author n3on
 * @since 22/05/14 23:36
 */
@RunWith(MockitoJUnitRunner.class)
public class CachedUserDaoTest {

    private UserDao cachedUserDao;
    private User expected1;
    private User expected2;

    @Mock
    private UserDao decorated;

    @Before
    public void setUp() throws Exception {
        expected1 = new User(0l, "R", "R");
        expected2 = new User(1l, "D", "D");
        when(decorated.getById(eq(0l))).thenReturn(expected1);
        when(decorated.getById(eq(1l))).thenReturn(expected2);
        cachedUserDao = new CachedUserDao(decorated);
    }

    @Test
    public void testGetById() throws Exception {
        User actual = cachedUserDao.getById(0l);
        assertEquals(expected1, actual);
    }

    @Test
    public void testGetById_cache() throws Exception {
        User actual = cachedUserDao.getById(0l);
        verify(decorated, times(1)).getById(eq(0l));
        assertEquals(expected1, actual);

        cachedUserDao.getById(0l);
        verify(decorated, times(1)).getById(eq(0l));
        assertEquals(expected1, actual);
        verifyNoMoreInteractions(decorated);
    }

    @Test
    public void testGetById_2ids() throws Exception {
        User actual = cachedUserDao.getById(0l);
        verify(decorated, times(1)).getById(eq(0l));
        assertEquals(expected1, actual);

        User actual2 = cachedUserDao.getById(1l);
        verify(decorated, times(1)).getById(eq(1l));
        assertEquals(expected2, actual2);
        verifyNoMoreInteractions(decorated);
    }

    @Test
    public void testGetById_cache2ids() throws Exception {
        User actual = cachedUserDao.getById(0l);
        verify(decorated, times(1)).getById(eq(0l));
        assertEquals(expected1, actual);

        User actual2 = cachedUserDao.getById(1l);
        verify(decorated, times(1)).getById(eq(1l));
        assertEquals(expected2, actual2);

        actual = cachedUserDao.getById(0l);
        verify(decorated, times(1)).getById(eq(0l));
        assertEquals(expected1, actual);

        actual2 = cachedUserDao.getById(1l);
        verify(decorated, times(1)).getById(eq(1l));
        assertEquals(expected2, actual2);

        verifyNoMoreInteractions(decorated);
    }
}
