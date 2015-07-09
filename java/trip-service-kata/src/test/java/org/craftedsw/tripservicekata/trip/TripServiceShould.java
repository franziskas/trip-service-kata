package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TripServiceShould {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void
    throw_exception_if_user_is_not_logged_in() {
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedInUser() {
                return null;
            }
        };

        thrown.expect(UserNotLoggedInException.class);

        tripService.getTripsByUser(null);
    }
}
