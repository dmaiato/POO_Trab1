import java.util.List;

public class Admin {
  
  private String name;
  private String id;


  /** System admin: can add and remove hotels from the system.*/
  public Admin(String name, String id) {
    this.name = name;
    this.id = id;
  }

  /**
   * Returns name attribute.
   * @return Admin.name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns id attribute.
   * @return Admin.id
   */
  public String getId() {
    return this.id;
  }

  /**
   * adds a new hotel to the booking system.
   * @param name hotel name
   * @param address hotel address
   */
  public Hotel addHotel(String name, String address) {
    return new Hotel(name, address);
  }

  /**
   * removes a hotel from the booking system.
   * @param hotel hotel object to be removed
   * @param hotelList list to have hotel removed from
   * @return returns modified list
   */
  public List<Hotel> removeHotel(Hotel hotel, List<Hotel> hotelList) {
    hotelList.remove(hotel);
    return hotelList;
  }
}
