package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserShould {
    @Test
    public void
    tell_if_they_are_not_friends_with_another_user() {
        User alice = new User();
        User bob = new User();

        assertThat(alice.isFriendOf(bob), is(false));
    }

    @Test
    public void
    tell_if_they_are_friends_with_another_user() {
        User alice = new User();
        User bob = new User();
        alice.addFriend(bob);

        assertThat(alice.isFriendOf(bob), is(true));
    }
}
