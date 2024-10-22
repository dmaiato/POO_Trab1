import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingSystem {
  private List<Hotel> hotels;
  private List<Client> clients;
  private Scanner scanner; 
  
  public BookingSystem() {
    hotels = new ArrayList<Hotel>();
    clients = new ArrayList<Client>();
    scanner = new Scanner(System.in);
  }

  public void addHotel() {
    System.out.print("Name: ");
    String name = scanner.nextLine();
    
    System.out.print("Address: ");
    String address = scanner.nextLine();

    Hotel newHotel = new Hotel(name, address);
    hotels.add(newHotel);

    System.out.println("Done!");
  }

  public void listHotels() {
    if (hotels.isEmpty()) {
      System.out.println("Please add a hotel.");
      return;
    }

    System.out.println("Hotels: ");
    for (Hotel hotel : hotels) {
      System.out.println("\t" + hotel);
    }
  }
  
}