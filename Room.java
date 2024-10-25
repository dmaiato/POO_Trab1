import java.util.ArrayList;
import java.util.List;

public class Room {
  
  private String number;
  private String type;
  private double price;
  private boolean isAvailable;
  private List<Booking> bookings;

  /**
   * Creates Room object.
   * @param number Room number
   * @param type Room type
   * @param price Room price
   */
  public Room(String number, String type, double price) {
    this.number = number;
    this.type = type;
    this.price = price;
    this.isAvailable = true;
    this.bookings = new ArrayList<>();
  }

  /**
   * Returns Booking object List.
   * @return Room.bookings
   */
  public List<Booking> getBookingSchedule() {
    return this.bookings;
  }

  /**
   * Returns number attribute
   * @return Room.number
   */
  public String getNumber() {
    return this.number;
  }

  /**
   * Returns room type attribute
   * @return Room.type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Returns price attribute;
   * @return
   */
  public double getPrice() {
    return this.price;
  }
  
  /**
   * Adds cross-referenced Booking object to Room.bookings attribute.
   * @param booking Booking object
   */
  public void book(Booking booking) {
    this.bookings.add(booking);
  }
  
  /**
   * Removes cross-referenced Booking object to Room.bookings attribute.
   * @param booking Booking object
   */
  public void vacate(Booking booking) {
    this.bookings.remove(booking);
  }

  /**
   * Switches Room.isAvailable attribute to true.
   */
  public void makeRoomAvaiable() {
    this.isAvailable = true;
  }

  /**
   * Switches Room.isAvailable attribute to false.
   */
  public void makeRoomUnavaiable() {
    this.isAvailable = false;
  }

  /**
   * Returns boolean value referencing Room.isAvailable attribute.
   * @return
   */
  public boolean isRoomAvailable() {
    if (isAvailable) 
      return true;
    else
      return false;
  }
  
  @Override
	public String toString() {
		return "Room: " + number + " - " + type;
	}
}
