import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

  private String name;
  private String address;
  private List<Room> roomList;

	/**
	 * Creates Hotel object
	 * @param name Hotel name
	 * @param address Hotel address
	 */
	public Hotel(String name, String address) {
		this.name = name;
		this.address = address;
		this.roomList = new ArrayList<Room>();
	}

	/**
	 * Returns name attribute.
	 * @return Hotel.name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns roomList attribute
	 * @return Hotel.roomList
	 */
	public List<Room> getRoomList() {
		return this.roomList;
	}

	/**
	 * Returns List of available rooms based on Room.isAvailable attribute.
	 * @return List of available rooms
	 */
	public List<Room> getAvailableRoomList() {
		List<Room> availableRoomList = new ArrayList<>();

		for (Room room : roomList) {
			if (room.isRoomAvailable()) availableRoomList.add(room);	
		}
		return availableRoomList;
	}

	/**
	 * Creates and adds new Room object to roomList attribute.
	 * @param number Room number
	 * @param type Room type
	 * @param price Room price
	 */
	public void addRoom (String number, String type, double price) {
		Room newRoom = new Room(number, type, price);
		this.roomList.add(newRoom);
	}

	/**
	 * Removes Room object from roomList attribute.
	 * @param room Room object
	 */
	public void removeRoom(Room room) {
		//asasas
	}

	/**
	 * Receives valid check-in-out date span and checks if it overlaps
	 * with any other scheduled bookings.
	 * @param checkIn LocalDate check-in
	 * @param checkOut LocalDate check-out
	 * @param booking Booking object
	 * @return overlaps? true : false
	 */
	public boolean isOverlapping(LocalDate checkIn, LocalDate checkOut, Booking booking) {

		/* A check-out must happen before noon, therefore a new check-in is allowed on the same day.
		and a check-in must happen after noon...*/
		if (checkOut.equals(booking.getCheckInDate()) || checkIn.equals(booking.getCheckOutDate())) {
			return false;
		}

    if (checkOut.isBefore(booking.getCheckInDate()) || checkIn.isAfter(booking.getCheckOutDate())) {
			return false;
		}
		return true;
  }

	/**
	 * Checks if there are available rooms on a check-in-out date span,
	 * and sets Room.isAvailable attribute.
	 * @param checkIn LocalDate check-in
	 * @param checkOut LocalDate check-out
	 */
	public void checkRoomVacancy(LocalDate checkIn, LocalDate checkOut) {
    for (Room room : getRoomList()) {
      for (Booking booking : room.getBookingSchedule()) {
        if (isOverlapping(checkIn, checkOut, booking)) {
          room.makeRoomUnavaiable();
          break;
        } else room.makeRoomAvaiable();
      }
    }
  }

	/**
	 * Prints available rooms.
	 */
	public void listAvaiableRooms() {
		System.out.println("Available Rooms:");
		for (Room room : roomList) {
			if (room.isRoomAvailable()) System.out.println("\t" + room);
		}
	}
	
	@Override
	public String toString() {
		return this.name + " - " + this.address;
	}

  
}
