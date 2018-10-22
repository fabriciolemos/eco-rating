package com.eco.rating.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    private UserRepository fixture;

    @Before
    public void setUp() {
        fixture = new UserRepository();
    }

    @Test
    public void addAndGetOneUser() {
        String userName = "Mary";
        assertNull(fixture.get(userName));

        User user = new User(userName, 1.14);
        fixture.add(user);

        assertSame(user, fixture.get(userName));
    }

    @Test
    public void addAndGetTwoUsers() {
        String userName1 = "Mary";
        assertNull(fixture.get(userName1));

        User user1 = new User(userName1, 1.14);
        fixture.add(user1);

        assertSame(user1, fixture.get(userName1));

        String userName2 = "Elisabeth";
        assertNull(fixture.get(userName2));

        User user2 = new User(userName2, 1.01);
        fixture.add(user2);

        assertSame(user2, fixture.get(userName2));
        assertSame(user1, fixture.get(userName1));
    }

}