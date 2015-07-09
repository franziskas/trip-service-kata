package org.craftedsw.tripservicekata.trip;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

    @Test
    public void
    show_no_trips_if_logged_in_user_is_not_friends_with_user() {
        User bob = new User();
        final User alice = new User();
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedInUser() {
                return alice;
            }

            @Override
            protected List<Trip> getTripsBy(User user) {
                return asList(new Trip(), new Trip());
            }
        };

        List<Trip> trips = tripService.getTripsByUser(bob);

        assertThat(trips.size(), is(0));
    }
}
