import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Booking {
  // Atributos: cliente, quarto, dataCheckIn, dataCheckOut.
  // Métodos: confirmarReserva(), cancelarReserva().

  private int id = 0;
  private static int serialIdIncrementer = 0;
  private Room room;
  private Client client;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;

  /**
   * Cross-referenced object that creates a bind between client and room.
   * @param room Room object
   * @param client Client object
   * @param checkinDate allocated check-in date
   * @param checkoutDate allocated check-out date
   */
  public Booking(Room room, Client client, LocalDate checkinDate, LocalDate checkoutDate) {
    this.id = ++serialIdIncrementer;
    this.room = room;
    this.client = client;
    this.checkInDate = checkinDate;
    this.checkOutDate = checkoutDate;
  }

  /**
   * Calls respective Client and Room methods to allocate booking.
   * @param booking collects self-referencing object
   */
  public void confirmBooking(Booking booking) {
    this.room.book(booking);
    this.client.book(booking);
  }

  /**
   * Calls respective Client and Room methods to remove booking.
   * @param booking collects self-referencing object
   */
  public void cancelBooking(Booking booking) {
    this.room.vacate(booking);
    this.client.cancelBooking(booking);
  }

  /**
   * Returns id attribute.
   * @return Booking.id
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns Room attribute.
   * @return Booking.room
   */
  public Room getRoom() {
    return this.room;
  }

  /**
   * Returns CLient attribute
   * @return Booking.client
   */
  public Room getClient() {
    return this.room;
  }

  /**
   * Returns a formatted dd/mm/yyyydate string.
   * @param date ISO-8601 date format
   * @return formated string date dd/mm/yyyy
   */
  public String formatDate(LocalDate date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return date.format(formatter);
  }

  /**
   * Returns checkInDate attribute.
   * @return Booking.checkInDate
   */
  public LocalDate getCheckInDate() {
    return this.checkInDate;
  }

  /**
   * Returns checkOutDate attribute.
   * @return Booking.checkOutDate
   */
  public LocalDate getCheckOutDate() {
    return this.checkOutDate;
  }

  @Override
  public String toString(){
    return "Id: " + id + ", Room: " + room.getNumber() + 
    " | check-in: " + formatDate(this.checkInDate) + ", checkout: " + formatDate(this.checkOutDate);
  }
  
}
