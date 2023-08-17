package parking;

import java.util.ArrayList;
import java.util.List;


public class OnlineParkingSystem {

}
class ParkingSpot{
	  private int spotId;
	    private boolean isAvailable;

	    public ParkingSpot(int spotId) {
	        this.spotId = spotId;
	        this.isAvailable = true;
	    }

	    public int getSpotId() {
	        return spotId;
	    }

	    public boolean isAvailable() {
	        return isAvailable;
	    }

	    public void book() {
	        isAvailable = false;
	    }

	    public void release() {
	        isAvailable = true;
	    }
	}

	class User {
	    private String username;
	    private String password;
	    private List<ParkingSpot> bookings;

	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	        this.bookings = new ArrayList<>();
	    }

	    public String getUsername() {
	        return username;
	    }

	    public boolean authenticate(String password) {
	        return this.password.equals(password);
	    }

	    public List<ParkingSpot> getBookings() {
	        return bookings;
	    }

	    public void addBooking(ParkingSpot spot) {
	        bookings.add(spot);
	    }

	    public void removeBooking(ParkingSpot spot) {
	        bookings.remove(spot);
	    }
	}

