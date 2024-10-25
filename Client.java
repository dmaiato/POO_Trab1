import java.util.ArrayList;
import java.util.List;

public class Client {
  // Atributos: nome, email, telefone.
  // Métodos: fazerReserva(), cancelarReserva().

  private String name;
  private String phone;
  private String email;
  private List<Booking> bookings;

  /**
   * Creates Client object.
   * @param name Client name
   * @param email Client email
   * @param phone Client phone
   */
  public Client(String name, String email, String phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.bookings = new ArrayList<>();
  }

  /**
   * Returns name attribute.
   * @return Client.name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns phone attribute.
   * @return Client.phone
   */
  public String getPhone() {
    return this.phone;
  }

  /**
   * Returns email attribute.
   * @return
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Returns a Client's booked schedule.
   * @return
   */
  public List<Booking> getBookings() {
    return bookings;
  }

  /**
   * Adds cross-refenced Booking object to attribute List.
   * @param booking Booking object
   */
  public void book(Booking booking) {
    this.bookings.add(booking);
  }

  /**
   * Removes cross-refenced Booking object from attribute List.
   * @param booking Booking object
   */
  public void cancelBooking(Booking booking) {
    this.bookings.remove(booking);
  }
}
