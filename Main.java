import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    BookingSystem system = new BookingSystem();
    int option;

    do {
      System.out.println("\n======= Menu =======");
      System.out.println("1. Add New Hotel");
      System.out.println("2. List All Hotels");
      System.out.println("3. Add Room to Hotel");
      System.out.println("4. List Avaiable Rooms in a Hotel");
      System.out.println("5. Book");
      System.out.println("6. Cancel Booking");
      System.out.println("7. List a Client's bookings");
      System.out.println("8. Exit");
      System.out.print("Choose an option: ");
      option = scanner.nextInt();

      switch (option) {
        case 1:
          system.addHotel();
          break;
        case 2:
          system.listHotels();
          break;
        // case 3:
        //   system.addRoom();
        //   break;
        // case 4:
        //   system.listAvaiableRooms();
        //   break;
        // case 5:
        //   system.book();
        //   break;
        // case 6:
        //   system.cancelBooking();
        //   break;
        // case 7:
        //   system.listClientBookings();
        //   break;
        // case 8:
        //   System.out.println("Shutting down...");
        //   break;
        default:
          System.out.println("Invalid option. Try again.");
          break;
      }
    } while (option != 8);

    scanner.close();
  }
}
