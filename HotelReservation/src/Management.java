import java.util.Scanner;

public class Management {

	private static final String STAY = "투숙";
	private static final String RESERVATION = "예약";
	private static final String EMPTY_ROOM = "빈방";

	Scanner in = new Scanner(System.in);
	Hotel hotel = new Hotel();

	public void startManagement() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println(" 호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();

		while (true) {
			int choice = Task();

			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				reservation();
				break;
			case 4:
				cancelReservation();
				break;
			case 5:
				roomStatement();
				break;
			case 6:
				sales();
				break;
			case 7:
				System.out.println("*********************************************");
				System.out.println(" 호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요.");
			}
		}
	}

	private int Task() {
		printLine();
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.print("1.체크인     ");
		System.out.print("2.체크아웃     ");
		System.out.print("3.예약     ");
		System.out.print("4.예약 취소     ");
		System.out.print("5.객실상태     ");
		System.out.print("6.매출확인     ");		
		System.out.println("7.업무종료");
		printLine();
		System.out.print("입력 >> ");
		int num = in.nextInt();

		return num;
	}

	// 1.체크인
	private void checkIn() {
		printLine();
		System.out.println(" Check-In");
		printLine();
		System.out.println("1.당일 방문     2.예약 방문     0.뒤로가기");
		System.out.print("입력 >> ");
		int select = in.nextInt();
		printLine();
		
		switch(select) {
		
		case 0:
			return;
			
		case 1:
			
			printSameRoom(EMPTY_ROOM);
			
			System.out.println("체크인 하실 객실번호를 입력해 주십시오.");
			System.out.print("방 번호 >> ");
			int roomNum1 = in.nextInt();
			printLine();
			
			boolean range1 = inputRoomNumberCheck(roomNum1);
			// 입력한 방 번호로 배열의 위치 값 r에 넣기

			if (range1) {
				Room r = hotel.rooms[(roomNum1 / 100) - 1][(roomNum1 % 100) - 1];

				if (r.getState().equals(RESERVATION)) {
					System.out.println("이미 예약된 방입니다..");
					checkIn();
				} else if (r.getState().equals(STAY)) {
					System.out.println("이미 체크인 된 방입니다..");
					checkIn();
				} else {
					dayCheckIn(r);
				}
			} else {
				System.out.println("존재하지 않는 방 번호입니다.");
				checkIn();
			}
			break;
		case 2:
			System.out.println("예약하신 객실번호를 입력해 주십시오.");
			System.out.print("방 번호 >> ");
			int roomNum2 = in.nextInt();
			
			printLine();
			
			boolean range2 = inputRoomNumberCheck(roomNum2);
			// 입력한 방 번호로 배열의 위치 값 r에 넣기

			if (range2) {
				Room r = hotel.rooms[(roomNum2 / 100) - 1][(roomNum2 % 100) - 1];

				if (r.getState().equals(EMPTY_ROOM)) {
					System.out.println("빈 방입니다..");
					checkIn();
				} else if (r.getState().equals(STAY)) {
					System.out.println("이미 체크인 된 방입니다..");
					checkIn();
				} else {
					System.out.println("예약자의 성함을 입력해 주십시오.");
					System.out.print("성함 >> ");
					String guestName = in.next();
					printLine();
					
					if (r.getName().equals(guestName)) {
						r.setState(STAY);
						System.out.print(r.toString());
						printLine();
						System.out.println("체크인이 완료되었습니다.");
						return;
					} else {
						System.out.println("성함을 잘못 입력 하셨습니다. 다시 입력해 주십시오.");
						checkIn();
					}

				}
			} else {
				System.out.println("존재하지 않는 방 번호입니다.");
				checkIn();
			}
			break;
		default :
			System.out.println("없는 번호 입니다. 다시 입력해 주세요.");
			checkIn();
		}
		  
	}

	// 2.체크아웃
	private void checkOut() {

		printLine();
		System.out.println(" Check-Out");
		printLine();
		System.out.println("체크아웃 하실 객실번호를 입력해 주십시오.");
		System.out.println("0.뒤로가기");
		System.out.print("방 번호 >> ");
		int roomNum = in.nextInt();
		if (roomNum == 0) {
			return;
		}

		boolean check = inputRoomNumberCheck(roomNum);

		if (check) {

			System.out.println("체크아웃 하실 손님의 성함을 입력해 주십시오.");
			System.out.print("성함 >> ");
			String guestName = in.next();
			printLine();

			Room r = hotel.rooms[(roomNum / 100) - 1][(roomNum % 100) - 1];

			if (r.getState().equals(RESERVATION)) {
				System.out.println("예약된 방은 체크아웃 하실 수 없습니다..");
				checkOut();
			} else if (r.getState().equals(EMPTY_ROOM)) {
				System.out.println("빈방은 체크아웃 하실 수 없습니다..");
				checkOut();
			} else {
				if (r.getName().equals(guestName)) {
					initializeRoom(hotel, roomNum);
					System.out.println("체크아웃 완료 되었습니다.");
					
					System.out.print("가격은 " + r.getPrice() +"원 입니다.");
					System.out.println();
				} else {
					System.out.println("성함을 잘못 입력 하셨습니다. 다시 입력해 주십시오.");
					checkOut();
				}
			}
		} else {
			System.out.println("존재하지 않는 방 번호입니다.");
			checkOut();
		}
	}

	// 3.예약
	private void reservation() {

		printLine();
		System.out.println(" Reservation");
		printSameRoom(EMPTY_ROOM);
		System.out.println("몇 호실을 예약 하시겠습니까?");
		System.out.println("0.뒤로가기");
		System.out.print("방 번호 >> ");
		int roomNum = in.nextInt();
		if (roomNum == 0) {
			return;
		}
		printLine();

		boolean range = inputRoomNumberCheck(roomNum);

		if (range) {
			Room r = hotel.rooms[(roomNum / 100) - 1][(roomNum % 100) - 1];

			if (r.getState().equals(RESERVATION)) {
				System.out.println("이미 예약된 방입니다..");
				reservation();
			} else if (r.getState().equals(STAY)) {
				System.out.println("이미 손님이 머물고 있는 방 입니다.");
				reservation();
			} else {
				System.out.println("예약자의 성함을 입력해 주십시오");
				System.out.print("성함 >> ");
				String guestName = in.next();
				printLine();
				System.out.println("예약자의 전화번호를 입력해 주십시오");
				System.out.print("전화번호 >> ");
				String guestPhoneNum = in.next();
				printLine();
				System.out.print("숙박 일 수 >> ");
				int days = in.nextInt();
				printLine();

				r.setName(guestName);
				r.setPhoneNumber(guestPhoneNum);
				r.setState(RESERVATION);
				r.setDays(days);
				lodging(r);
				System.out.println("예약이 완료됐습니다.");
			}
		} else {
			System.out.println("존재하지 않는 방 번호입니다.");
			reservation();
		}
	}

	// 4.예약취소
	private void cancelReservation() {

		printLine();
		System.out.println("Cancel Reservation");
		printLine();
		System.out.println("예약을 취소하실 객실의 호수를 입력해 주십시오.");
		System.out.println("0.뒤로가기");
		System.out.print("방 번호 >> ");
		int roomNum = in.nextInt();
		if (roomNum == 0) {
			return;
		}

		boolean check = inputRoomNumberCheck(roomNum);

		if (check) {

			System.out.println("예약자의 성함을 입력해 주십시오.");
			System.out.print("성함 >> ");
			String guestName = in.next();
			printLine();

			Room r = hotel.rooms[(roomNum / 100) - 1][(roomNum % 100) - 1];

			if (r.getName().equals(guestName) && r.getState().equals(RESERVATION)) {
				System.out.println(guestName + "님의 예약이 취소되었습니다.");
				initializeRoom(hotel, roomNum);
			} else if (r.getState().equals(EMPTY_ROOM)) {
				System.out.println("빈방은 예약 취소가 불가능 합니다.");
				cancelReservation();
			} else if (r.getState().equals(STAY)) {
				System.out.println("손님이 머물고 있는 방은 예약 취소가 불가능 합니다.");
				cancelReservation();
			} else {
				System.out.println("성함을 잘못 입력 하셨습니다. 다시 입력해 주십시오.");
				cancelReservation();
			}
		} else {
			System.out.println("존재하지 않는 방 번호입니다.");
			cancelReservation();
		}
	}

	// 5.방상태보기
	private void roomStatement() {

		printLine();
		System.out.println("Room Statement");
		printLine();
		for (int i = 1; i < hotel.rooms.length; i++) {
			for (int j = 0; j < hotel.rooms[i].length; j++) {
				System.out.print(hotel.rooms[i][j]);
			}
		}
		printLine();

		while (true) {
			
			System.out.println("1.빈 객실만 출력     2.예약된 객실만 출력     3.투숙 중인 객실만 출력");
			System.out.println("4.객실 검색     0.뒤로가기");
			printLine();
			System.out.print("입력 >> ");
			int select = in.nextInt();

			switch (select) {
			
			case 0:
				
					return;
			case 1:
				
				printSameRoom(EMPTY_ROOM);
				continue;
			case 2:
				
				printSameRoom(RESERVATION);
				continue;
			case 3:
				
				printSameRoom(STAY);
				continue;
			case 4:
				
				searchRoom();
				continue;
			default:
				System.out.println("없는 번호 입니다.. 다시 선택해 주십시오..");
				continue;
			}
		}
	}
	
	//매출액
		private void sales() {
			printLine();
			System.out.println("Sales Statement");
			printLine();
			System.out.println("1.객실 타입별 매출     2.총 매출      0.뒤로가기");
			printLine();
			System.out.print("입력 >> ");
			int select = in.nextInt();

			switch (select) {
			case 0:
				return;
			case 1:
				printSales("객실타입");
				break;
			case 2:
				printSales("모든타입");
				break;
			default:
				System.out.println("없는 번호 입니다.. 다시 선택해 주십시오..");
				break;
			}
			
		}

		private void printSales(String state) {
			int singleRoom =0 , doubleRoom = 0;
			
			for (int i = 1; i < hotel.rooms.length; i++) {
				for (int j = 0; j < hotel.rooms[i].length; j++) {
					
					Room r = hotel.rooms[i][j];
					
					if (r.getPrice() != 0) {
						if (r.getRoomType() == "싱글") {
							singleRoom += r.getPrice();
						} else {
							doubleRoom += r.getPrice();
						}
					}
				}
			}
			switch (state) {
			case "객실타입" :
				System.out.println(" 싱글룸 매출액은 " + singleRoom + "원 입니다.");
				System.out.println(" 더블룸 매출액은 " + doubleRoom + "원 입니다.");
				break;
			case "모든타입" :
				System.out.println(" 총매출액은 " + (singleRoom + doubleRoom) + "원 입니다.");
				break;
			}
		}

	private void printSameRoom(String state) {

		int count = 0;
		
		printLine();
		
		for (int i = 1; i < hotel.rooms.length; i++) {
			for (int j = 0; j < hotel.rooms[i].length; j++) {
				Room r = hotel.rooms[i][j];
				if (r.getState().equals(state)) {
					System.out.print(r);
					count++;
				}
			}
		}

		switch (state) {
		case "빈방":
			printLine();
			System.out.println("빈 객실의 개수는 총 " + count + "개 입니다.");
			printLine();
			break;
		case "예약":
			printLine();
			System.out.println("예약된 객실의 개수는 총 " + count + "개 입니다.");
			printLine();
			break;
		case "투숙":
			printLine();
			System.out.println("투숙 중인 객실의 개수는 총 " + count + "개 입니다.");
			printLine();
			break;
		}

	}

	private boolean inputRoomNumberCheck(int roomNum) {

		boolean range = true;
		int floor = roomNum / 100 - 1;
		int roomNo = roomNum % 100 - 1;

		if ((floor < 1 || floor > 4) || (roomNo < 0 || roomNo > 19)) {
			range = false;
		}

		return range;
	}

	private void initializeRoom(Hotel hotel, int roomNum) {

		int floor = roomNum / 100 - 1; // 302 -> 3 -> 2
		int roomNo = roomNum % 100 - 1; // 302 -> 2 -> 1

		hotel.rooms[floor][roomNo] = new Room(roomNum, "-", "-", EMPTY_ROOM, "", 0, 0);

		if (roomNum % 2 == 0) {
			hotel.rooms[floor][roomNo].setRoomType("더블");
		} else {
			hotel.rooms[floor][roomNo].setRoomType("싱글");
		}
	}
	
	private void searchRoom() {
		printLine();
		System.out.print("방 번호 >> ");
		int roomNum = in.nextInt();
		printLine();

		// 방번호가 존재하는지 알기위해 변수 설정
		boolean contain = inputRoomNumberCheck(roomNum);
		if (contain) { // 방번호 존재 할때
			System.out.print(hotel.rooms[(roomNum / 100) - 1][(roomNum % 100) - 1].toString());
			printLine();
		} else { // 방 번호 존재 X
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
		}
	}
	
	private void lodging(Room r) {
		
		if(r.getRoomType().equals("싱글")) {
			r.setPrice(100000 * r.getDays());
		} else {
			r.setPrice(200000 * r.getDays());
		}
	}

	
	private void dayCheckIn(Room r) {
		
		r.setState(STAY);
		System.out.println("성함을 입력해 주십시오.");
		
		System.out.print("성함 >> ");
		r.setName(in.next());
		printLine();
		System.out.println("전화번호 입력해 주십시오. (-없이 입력해 주십시오.)");
		System.out.print("전화번호 >> ");
		r.setPhoneNumber(in.next());
		printLine();
		System.out.print("숙박 일 수 >> ");
		r.setDays(in.nextInt());
		printLine();
		lodging(r);
		System.out.print(r.toString());
		System.out.println("체크인이 완료되었습니다.");
	}

	private void printLine() {
		System.out.println("-----------------------------------------------------------------------------------------------");
	}
}

