package Pharmacy;

import java.sql.Connection;
import java.util.Scanner;

public class PharmacyMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		int menu = 0;

		Client client = new Client();
		Family family = new Family();
		Goods goods = new Goods();
		Purchase purchase = new Purchase();
		PharmacySQL sql = new PharmacySQL();

		while (run) {
			System.out.println("=========================================");
			System.out.println("       1. 회원가입        2. 로그인        3. 관리자로그인");
			System.out.println("=========================================");
			System.out.print("메뉴선택 >> ");
			menu = sc.nextInt();

			switch (menu) {

			case 1: // 회원가입
				int clientCode = sql.clientCode() + 1;
				System.out.println("회원정보를 입력해주세요.");
				System.out.print("아이디 >> ");
				String new_id = sc.next();
				System.out.print("비밀번호 >> ");
				String new_pw = sc.next();
				System.out.print("이름 >> ");
				String name = sc.next();
				System.out.print("성별 >> ");
				String gender = sc.next();
				System.out.print("전화번호 >> ");
				String phone = sc.next();
				System.out.print("생년월일 >> ");
				String birth = sc.next();
				int point = 0;
				int fcode = 0;
				String fcheck = "x";
				client = new Client(clientCode, new_id, new_pw, name, gender, phone, birth, point, fcode, fcheck);
				sql.insertClient(client);
				break;

			case 2: // 로그인
				System.out.println("------------------로그인-----------------");

				System.out.print("아이디 >> ");
				String id = sc.next();
				System.out.print("비밀번호 >> ");
				String pw = sc.next();

				boolean check = sql.idCheck(id, pw);

				if (check) {
					System.out.println("아이디와 비밀번호가 일치합니다.\n");

					while (run) {
						System.out.println("============================================");
						System.out.println("1. 내정보         2. 가족정보추가         3. 구매         4. 로그아웃");
						System.out.println("============================================");
						System.out.print("메뉴선택 >> ");
						menu = sc.nextInt();
						

						switch (menu) {
						case 1: // 1. 내정보
							System.out.println("------------------내 정보 조회-----------------");
							sql.selectInfo(id);
							System.out.println();
							sql.selectpharchase(id);
							System.out.println();
							break;

						case 2: // 2. 가족정보추가
							System.out.println("가족의 대표자이신가요?");
							System.out.print("1. 네        2. 아니요        >>  ");
							menu = sc.nextInt();

							switch (menu) {
							case 1:
								sql.update(id);
								break;

							case 2:
								System.out.print("가족 대표자 아이디를 입력하세요 >> ");
								String head_id = sc.next();
								sql.update2(id, head_id);
								break;

							default:
								System.out.println("다시 입력하세요!");
								break;
							}
							System.out.println();
							break;

						case 3: // 3. 구매
							System.out.println("--------------------------- 제품 LIST --------------------------");
							sql.goods();
							System.out.print("원하시는 제품의 상품코드를 선택하세요 >> ");
							int pur_code = sc.nextInt();

							System.out.print("수량을 선택하세요 >> ");
							int pur_stock = sc.nextInt();

							System.out.println();
							
							System.out.println("결제하시겠습니까?");
							//System.out.println("만약, 65세 이상은 무료입니다! (포인트적립x)");
							System.out.print("1. 네        2. 아니요        >>  ");
							menu = sc.nextInt();

							switch (menu) {
							case 1:
								sql.gselect(id, pur_code, pur_stock);
								break;
							case 2:
								System.out.println("결제 취소합니다.");
								break;

							default:
								System.out.println("다시 입력하세요!");
								break;
							}

							break;

						case 4: // 4. 로그아웃
							System.out.println("프로그램을 종료합니다.");
							run = false;
							break;

						default:
							System.out.println("다시 입력하세요!");
							break;
						}
					}
				} else {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
				}

				break;

			case 3: // 관리자모드
				System.out.println("--------------관리자 로그인-----------------");

				System.out.print("아이디 >> ");
				String admin_id = sc.next();
				System.out.print("비밀번호 >> ");
				String admin_pw = sc.next();

				boolean admin = sql.AdminCheck(admin_id, admin_pw);

				if (admin) {
					System.out.println("아이디와 비밀번호가 일치합니다.\n");

					while (run) {
						System.out.println("==============관리자 메뉴 ====================");
						System.out.println("1. 회원정보 출력      2. 회원정보 수정 ");
						System.out.println("3. 제품 확인       4. 제품 추가           5. 제품 수정");
						System.out.println("6. 관리자 로그아웃");
						System.out.println("===========================================");
						System.out.print("메뉴선택 >> ");
						menu = sc.nextInt();

						switch (menu) {
						case 1:
							System.out.println("------------------회원정보 출력-----------------");
							System.out.println("1. 모든회원 검색           2. 특정회원 검색");
							System.out.println("--------------------------------------------------");
							System.out.print("메뉴선택 >> ");
							menu = sc.nextInt();
							System.out.println();

							switch (menu) {
							case 1:
								sql.selectAllInfo();
								break;

							case 2:
								System.out.print("이름을 입력하세요 >> ");
								String s_name = sc.next();
								sql.selectInfoByName(s_name);
								System.out.println();
								break;

							default:
								System.out.println("다시 입력하세요!");
								break;
							}
							break;

						case 2:
							
							System.out.println("------------------회원정보 수정-----------------");
							System.out.print("아이디 >> ");
							String s_id = sc.next();

							System.out.print("비밀번호 >> ");
							String s_pw = sc.next();							
							boolean checkLogin = sql.idCheck(s_id, s_pw);
							
							if (checkLogin) {
								System.out.println("아이디와 비밀번호가 일치합니다.\n");

								System.out.print("변경할 코드 입력 >> ");
								int m_clientCode = sc.nextInt();

								System.out.print("변경할 아이디 입력 >> ");
								String m_id = sc.next();

								System.out.print("변경할 패스워드 입력 >> ");
								String m_pw = sc.next();

								System.out.print("변경할 이름 입력 >> ");
								String m_name = sc.next();

								System.out.print("변경할 성별 입력 >> ");
								String m_gender = sc.next();

								System.out.print("변경할 전화번호 입력 >> ");
								String m_phone = sc.next();

								System.out.print("변경할 생년월일 입력 >> ");
								String m_birth = sc.next();

								System.out.print("변경할 포인트 입력 >> ");
								int m_point = sc.nextInt();

								System.out.print("변경할 가족코드 입력 >> ");
								int m_fcode = sc.nextInt();

								System.out.print("변경할 가족 마스크 수령여부 >> ");
								String m_fcheck = sc.next();
								
								sql.modify(s_id, m_clientCode, m_id, m_pw, m_name, m_gender, m_phone, m_birth, m_point, m_fcode, m_fcheck);
		
							} else {
								System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
							}
							break;
						case 3:
							System.out.println("------------------제품 확인--------------------");
							sql.selectMenu();
							System.out.println();
							break;
							
						case 4:
							System.out.println("------------------제품 추가--------------------");
							sql.selectMenu();

							System.out.print("추가할 제품코드 >> ");
							int addGcode = sc.nextInt();

							System.out.print("추가할 제품이름 >> ");
							String addGname = sc.next();

							System.out.print("추가할 제품재고 >> ");
							int addGstock = sc.nextInt();

							System.out.print("추가할 제품가격 >> ");
							int addGprice = sc.nextInt();

							sql.addMenu(addGcode, addGname, addGstock, addGprice);
							break;

						case 5:
							System.out.println("------------------제품 수정--------------------");
							sql.selectMenu();

							System.out.print("수정할 제품 이름 >> ");
							String gname = sc.next();

							boolean checkMenu = sql.menuCheck(gname);
							if (checkMenu) {

								System.out.print("변경할 상품코드 >> ");
								int m_gcode = sc.nextInt();

								System.out.print("변경할 상품이름 >> ");
								String m_gname = sc.next();

								System.out.print("변경할 상품재고 >> ");
								int m_gstock = sc.nextInt();

								System.out.print("변경할 상품가격 >> ");
								int m_gprice = sc.nextInt();

								sql.modifyMenu(gname, m_gcode, m_gname, m_gstock, m_gprice);
							} else {
								System.out.println("없는 상품입니다.");
							}
							break;

						case 6:
							System.out.println("------------------관리자 로그아웃----------------");
							run = false;
							break;

						default:
							System.out.println("다시 입력하세요!");
							break;

						}
					}
				} else {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
				}

				break;

			default:
				System.out.println("다시 입력하세요!");
				break;
			}

		}

	}
}
