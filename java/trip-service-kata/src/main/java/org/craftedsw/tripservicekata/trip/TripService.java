package org.craftedsw.tripservicekata.trip;

import java.util.List;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import static java.util.Collections.emptyList;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = getLoggedInUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        if (isFriend(user, loggedUser)) {
            return getTripsBy(user);
        }
        return emptyList();


    }

    private boolean isFriend(User user, User loggedUser) {
        boolean isFriend = false;
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                isFriend = true;
                break;
            }
        }
        return isFriend;
    }

    protected List<Trip> getTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

}
