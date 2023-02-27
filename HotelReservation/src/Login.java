import java.util.Scanner;

public class Login {

	boolean log;
	
	public void adminLogin() {
		UserDB db = new UserDB();
		Scanner scan = new Scanner(System.in);

		바깥루프: for (int i = 0; i < 3 ; i++) {
			System.out.println("아이디를 입력하세요 ");
			String id = scan.next();
			System.out.println("비밀번호를 입력하세요");
			String pw = scan.next();
			////////////////////////////////
			for (int j = 0; j < db.users.length; j++) {
				if (db.users[j].getId().equals(id)) {
					if (db.users[j].getPw().equals(pw)) {
						System.out.println(id + "님 환영합니다! 오늘도 화이팅^-^7");
						log = true;
						break 바깥루프;
					}
				}

			}
			System.out.println("아이디 및 비밀번호가 다릅니다. 현재" + "3회중 " + (i + 1) + "번째오류");
			
			log = false;
		}
	}

}