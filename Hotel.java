
public class Hotel {

  private String name;
  private String address;
  // private Room[] roomList;

	public Hotel(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public void addRoom () {
		//asasas
	}

	public void removeRoom () {
		//asasas
	}

	public void listAvaiableRooms () {
		//asasas
	}

	@Override
	public String toString() {
		return this.name + " - " + this.address;
	}

  
}