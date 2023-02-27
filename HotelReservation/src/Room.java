public class Room {
	private int roomNumber;
	private String name;
	private String phoneNumber;
	private String state;
	private String roomType;
	private int days;
	private int price;
	


	public Room(int roomNumber, String name, String phoneNumber, String state, String roomType, int days, int price) {
		super();
		this.roomNumber = roomNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.state = state;
		this.roomType = roomType;
		this.days = days;
		this.price = price;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return roomNumber + "호" 
				+ "     상태 : " + state 
				+ "     방 종류 : " + roomType 
				+ "     이름 : " + name
				+ "     전화번호 : " + phoneNumber 
				+ "     숙박 일 수 : " + days + "일"
				+ "     가격 : " + price + "원" + "\n";
	}
}