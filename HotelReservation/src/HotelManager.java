public class HotelManager {
	public static void main(String[] args) {
		Management management = new Management();
		Login login = new Login();
		
		login.adminLogin();
		
		if (login.log) {
			management.startManagement();
		} else {
			System.out.println("로그인 3회 실패로 프로그램을 종료합니다.");
			return;
		}
		
	}
}