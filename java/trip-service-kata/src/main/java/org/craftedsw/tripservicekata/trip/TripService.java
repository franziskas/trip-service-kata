package org.craftedsw.tripservicekata.trip;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import static java.util.Collections.emptyList;

public class TripService {
    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        if (loggedInUser == null) {
            throw new UserNotLoggedInException();
        }

        if (user.isFriendOf(loggedInUser)) {
            return getTripsBy(user);
        }
        return emptyList();
    }

    protected List<Trip> getTripsBy(User user) {
        return tripDAO.findTripsBy(user);
    }

}
