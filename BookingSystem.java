import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BookingSystem {
  private Admin admin;
  private Scanner scanner;
  private List<Hotel> hotels;
  private List<Client> clients;
  
  /**
   * Creates the main operational system.
   * @param username validated admin username
   * @param password validated admin password
   * @param scanner scanner obeject
   */
  public BookingSystem(String username, String password, Scanner scanner) {
    this.admin = new Admin(username, password);
    this.scanner = new Scanner(System.in);
    this.hotels = new ArrayList<Hotel>();
    this.clients = new ArrayList<Client>();
  }

  /**
   * Returns Hotel object based on search validation.
   * @param name hotel name
   * @return Hotel object
   */
  public Hotel searchHotelByName(String name) {
    if (this.hotels.isEmpty()) return null;

    for (Hotel hotel : this.hotels) {
      if (hotel.getName().equalsIgnoreCase(name)) return hotel;
    }
    return null;
  }

  /**
   * Collects the necessary information for creating hotel object.
   * @return hotel information HashMap
   */
  public HashMap<String, String> collectHotelInformation() {
    String name;
    String address;
    char option;

    do {
      System.out.print("Name: ");
      name = scanner.nextLine();

      if (searchHotelByName(name) != null) {
        System.out.println("Error: Hotel already registered.");
        return null;
      }
      
      System.out.print("Address: ");
      address = scanner.nextLine();
      
      System.out.print("Are you sure? (y/n): ");
      option = scanner.nextLine().charAt(0);

    } while (!(option == 'y' || option == 'Y'));

    HashMap<String, String> dataMap = new HashMap<>();
    dataMap.put("name", name);
    dataMap.put("address", address);

    return dataMap;
  }

  /**
   * Validates hotel information and calls admin method Admin.addHotel(hotel).
   */
  public void addHotel() {
    HashMap<String, String> hotelDataMap = collectHotelInformation();
    if (hotelDataMap == null) return;

    String name = hotelDataMap.get("name").strip();
    String address = hotelDataMap.get("address").strip();

    name = name.replaceAll("\\s+", " ");
    name = name.strip();

    address = address.replaceAll("\\s+", " ");
    address = address.strip();

    Hotel newHotel = admin.addHotel(name.toUpperCase(), address.toUpperCase());
    hotels.add(newHotel);
    System.out.println("Done!");
  }

   /**
    * Prints list of hotels.
    */
  public void listHotels() {
    if (this.hotels.isEmpty()) {
      System.out.println("Error: No registered hotels.");
      return;
    }

    System.out.println("Hotels: ");
    for (Hotel hotel : hotels) {
      System.out.println("\t" + hotel);
    }
  }

  /**
   * Collects the necessary information for creating room object.
   * @return room information HashMap
   */
  public HashMap<String, String> collectRoomInformation() {
    System.out.print("Hotel: ");
    String hotelName = scanner.nextLine();

    Hotel hotel = searchHotelByName(hotelName);
    if (hotel == null) {
      System.out.println("Hotel not found.");
      return null;
    }

    String number;
    String type;
    String price;
    char option;

    System.out.println();
    do {
      System.out.print("Number: ");
      number = scanner.nextLine();

      if (searchRoomByNumber(hotel, number) != null) {
        System.out.println("Error: Room already registered.");
        return null;
      }
      
      System.out.print("Type: ");
      type = scanner.nextLine();
      
      System.out.print("Price: ");
      price = scanner.nextLine();
      price = price.replace(",", ".");

      try {
        Double.parseDouble(price);
      } catch (Exception e) {
        System.out.println("Invalid price.");
        return null;
      }
      
      System.out.print("Are you sure? (y/n): ");
      option = scanner.nextLine().charAt(0);

    } while (!(option == 'y' || option == 'Y'));

    HashMap<String, String> dataMap = new HashMap<>();
    dataMap.put("hotelName", hotel.getName());
    dataMap.put("number", number);
    dataMap.put("type", type);
    dataMap.put("price", price);

    return dataMap;
  }

  /**
   * Validades room information and adds object to Hotel.roomList.
   */
  public void addRoom() {
    HashMap<String, String> roomDataMap = collectRoomInformation();
    if (roomDataMap == null) return;

    Hotel hotel = searchHotelByName(roomDataMap.get("hotelName"));
    String number = roomDataMap.get("number");
    String type = roomDataMap.get("type").strip();
    double price = Double.parseDouble(roomDataMap.get("price"));

    number = number.replaceAll("\\s+", "");
    type = type.replaceAll("\\s+", " ");
    type = type.strip();

    hotel.addRoom(number, type, price);

    System.out.println("Done!");
  }
  
  /**
   * Collects valid check-in and check-out information.
   * @param formatter formats date to dd/mm/yyyy
   * @return date information HashMap
   */
  public HashMap<String, LocalDate> collectValidDate(DateTimeFormatter formatter) {
    LocalDate checkIn;
    LocalDate checkOut;

    try {
      System.out.print("Check-In Date (d/m/y): ");
      checkIn = LocalDate.parse(scanner.nextLine(), formatter);
      if (checkIn.isBefore(LocalDate.now())) {
        throw new Exception();
      }
      
      System.out.print("Check-Out Date (d/m/y): ");
      checkOut = LocalDate.parse(scanner.nextLine(), formatter);
      if (checkOut.isBefore(checkIn)) {
        throw new Exception();
      }
    } catch (Exception e) {
      System.out.println("Invalid date.");
      return null;
    }

    HashMap<String, LocalDate> dataMap = new HashMap<>();
    dataMap.put("checkIn", checkIn);
    dataMap.put("checkOut", checkOut);

    return dataMap;
  }

  /**
   * Prints the availability of a room based on a valid check-in-out date span. 
   */
  public void listAvaiableRooms() {
    String hotelName;
    System.out.print("Hotel: ");
    hotelName = scanner.nextLine();

    Hotel hotel = searchHotelByName(hotelName);
    if (hotel == null) {
      System.out.println("Invalid Hotel.");
      return;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    var dateInterval = collectValidDate(formatter);
    if (dateInterval == null) return; 
    var checkIn = dateInterval.get("checkIn");
    var checkOut = dateInterval.get("checkOut");

    hotel.checkRoomVacancy(checkIn, checkOut);

    List<Room> availableRooms = hotel.getAvailableRoomList();
    if (availableRooms.isEmpty()) {
      System.out.println("There are no rooms available.");
      return;
    }
    
    hotel.listAvaiableRooms();
  }

  /**
   * Returns Room object based on search validation.
   * @param hotel hotel object -> room list
   * @param number string referencing room number
   * @return Room object
   */
  public Room searchRoomByNumber(Hotel hotel, String number) {
    for (Room room : hotel.getAvailableRoomList()) {
      if (number.equals(room.getNumber())) {
        return room;
      }
    }
    return null;
  }

  /**
   * Collects valid Booking information.
   * @return booking information HashMap
   */
  public HashMap<String, String> collectBookingInformation() {
    String clientName;
    Client client;
    char option;

    do {
      System.out.print("Client name: ");
      clientName = scanner.nextLine();

      client = searchClientByName(clientName);
      if (client == null) {
        System.out.print("Client not found. Create client? (y/n): ");
        option = scanner.nextLine().charAt(0);

        if (option == 'y' || option == 'Y') client = createClient();
        else return null; 
      }

    } while (client == null);

    String hotelName;

    System.out.print("Hotel: ");
    hotelName = scanner.nextLine();

    Hotel hotel = searchHotelByName(hotelName);
    if (hotel == null) {
      System.out.println("Hotel not found.");
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    var dateInterval = collectValidDate(formatter);
    if (dateInterval == null) return null; 
    var checkIn = dateInterval.get("checkIn");
    var checkOut = dateInterval.get("checkOut");

    hotel.checkRoomVacancy(checkIn, checkOut);

    List<Room> availableRooms = hotel.getAvailableRoomList();
    if (availableRooms.isEmpty()) {
      System.out.println("There are no rooms available.");
      return null;
    }
    
    hotel.listAvaiableRooms();
    System.out.println();

    String roomSelection;

    System.out.print("Please select a room: ");
    roomSelection = scanner.nextLine();

    Room room = searchRoomByNumber(hotel, roomSelection);
    if (room == null) {
      System.out.println("Invalid room.");
      return null;
    }

    HashMap<String, String> dataMap = new HashMap<>();
    dataMap.put("hotel", hotel.getName());
    dataMap.put("room", room.getNumber());
    dataMap.put("client", client.getName());
    dataMap.put("checkIn", checkIn.toString());
    dataMap.put("checkOut", checkOut.toString());

    return dataMap;

  }

  /**
   * Creates client object.
   * @return Client object
   */
  public Client createClient() {
    String clientName;
    String clientEmail;
    String clientPhone;
    
    System.out.print("Enter name: ");
    clientName = scanner.nextLine();
    System.out.print("Enter email: ");
    clientEmail = scanner.nextLine();
    System.out.print("Enter phone number: ");
    clientPhone = scanner.nextLine();

    clientName = clientName.replaceAll("\\s+", " ");
    clientName = clientName.strip();

    clientEmail = clientEmail.replaceAll("\\s+", "");
    clientPhone = clientPhone.replaceAll("\\s+", "");
    
    Client newClient = new Client(clientName.toUpperCase(), clientEmail, clientPhone);
    clients.add(newClient);

    System.out.println("Client created successfully!\n");

    return newClient;
  }

  /**
   * Returns Client object based on search validation.
   * @param clientName name for searching
   * @return Client object
   */
  public Client searchClientByName(String clientName) {
    if (clients.isEmpty()) return null;

    for (Client client : clients) {
      if (client.getName().equalsIgnoreCase(clientName)) return client; 
    }
    return null;
  }

  /**
   * Creates cross-referenced Booking object.
   */
  public void book() {
    HashMap<String, String> dataMap = collectBookingInformation();
    if (dataMap == null) return;

    Hotel hotel = searchHotelByName(dataMap.get("hotel"));
    Room room = searchRoomByNumber(hotel, dataMap.get("room"));
    Client client = searchClientByName(dataMap.get("client"));

    LocalDate checkIn = LocalDate.parse(dataMap.get("checkIn"));
    LocalDate checkOut = LocalDate.parse(dataMap.get("checkOut"));

    Booking booking = new Booking(room, client, checkIn, checkOut);

    booking.confirmBooking(booking);

    System.out.println("Booking was successfull!");

  }

  /**
   * Collects Client information and overloads to next method.
   */
  public void listClientBookings() {
    String clientName;

    System.out.print("Client Name: ");
    clientName = scanner.nextLine();

    var client = searchClientByName(clientName);
    listClientBookings(client);
  }

  /**
   * Prints a Client's booked schedule.
   * @param client search key -> Booking
   */
  public void listClientBookings(Client client) {  
    if (client == null) {
      System.out.println("Client not registered.");
      return;
    }
    if (client.getBookings().isEmpty()) {
      System.out.print("Client has no bookings.");
      return;
    }
  
    for (Booking booking : client.getBookings()) {
      System.out.println("\t" + booking);
    }
    System.out.println();
  }

  /**
   * Removes a Client's scheduled booking.
   */
  public void cancelBooking() {
    String clientName;

    System.out.print("Client name: ");
    clientName = scanner.nextLine();

    var client = searchClientByName(clientName);

    listClientBookings(client);

    int idSelect;

    try {
      System.out.print("Select booking (id): ");
      idSelect = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("Invalid Id.");
      return;
    }

    for (Booking booking : client.getBookings()) {
      if (booking.getId() == idSelect) {
        booking.cancelBooking(booking);
        System.out.println("Booking was removed succesfully!");
        return;
      }
    }
    System.out.println("Id not found.");
  }
}
