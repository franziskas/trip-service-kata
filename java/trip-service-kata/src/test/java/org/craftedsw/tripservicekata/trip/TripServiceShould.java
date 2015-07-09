package org.craftedsw.tripservicekata.trip;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TripServiceShould {
    private static final List<Trip> TRIPS = asList(new Trip(), new Trip());
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private User loggedInUser;
    private TripService tripService;

    @Before
    public void initialise() {
        tripService = new TripService() {
            @Override
            protected User getLoggedInUser() {
                return loggedInUser;
            }

            @Override
            protected List<Trip> getTripsBy(User user) {
                return TRIPS;
            }
        };
    }

    @Test
    public void
    throw_exception_if_user_is_not_logged_in() {
        loggedInUser = null;

        thrown.expect(UserNotLoggedInException.class);

        tripService.getTripsByUser(null);
    }

    @Test
    public void
    show_no_trips_if_logged_in_user_is_not_friends_with_user() {
        loggedInUser = new User();
        User bob = new User();

        List<Trip> trips = tripService.getTripsByUser(bob);

        assertThat(trips.size(), is(0));
    }

    @Test
    public void
    show_trips_if_logged_in_user_is_friends_with_user() {
        loggedInUser = new User();
        User bob = new User();
        bob.addFriend(loggedInUser);

        List<Trip> trips = tripService.getTripsByUser(bob);

        assertThat(trips, is(TRIPS));
    }
}
