public class Hotel {
	Room[][] rooms;

	public Hotel() {
		rooms = new Room[5][20];

		for (int i = 1; i < rooms.length; i++) {
			for (int j = 0; j < rooms[i].length; j++) {
				int room = ((i + 1) * 100) + (j + 1);
				rooms[i][j] = new Room(room, "-", "-", "빈방", "", 0, 0);
				Room r = rooms[i][j];
				
				if (room % 2 == 0) {;
					r.setRoomType("더블");
				} else {
					rooms[i][j].setRoomType("싱글");
				}
			}
		}
	}
}