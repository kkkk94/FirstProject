package Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PharmacySQL {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null; // 조회 (Select) 결과를 저장하기 위한 변수 선언

	Connection con = DBC.DBConnect();

	public int clientCode() { // 회원번호부여
		String sql = "SELECT COUNT(*) FROM CLIENT";
		int clientCode = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				clientCode = rs.getInt(1);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return clientCode;
	}

	public void insertClient(Client client) { // 회원가입
		String sql = "INSERT INTO CLIENT VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, client.getClientCode());
			pstmt.setString(2, client.getId());
			pstmt.setString(3, client.getPw());
			pstmt.setString(4, client.getName());
			pstmt.setString(5, client.getGender());
			pstmt.setString(6, client.getPhone());
			pstmt.setString(7, client.getBirth());
			pstmt.setInt(8, client.getPoint());
			pstmt.setInt(9, client.getFcode());
			pstmt.setString(10, client.getFcheck());

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("회원등록 성공!");
			} else {
				System.out.println("회원등록 실패...");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	public boolean idCheck(String id, String pw) {
		boolean checkResult = false;

		String sql = "SELECT ID, PW FROM CLIENT WHERE ID = ? AND PW = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		return checkResult;
	}

	public void selectInfo(String id) {
		try {
			String sql = "SELECT * FROM CLIENT WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("회원번호 : " + rs.getInt(1));
				System.out.println("아이디 : " + rs.getString(2));
				System.out.println("비밀번호 : " + rs.getString(3));
				System.out.println("이름 : " + rs.getString(4));
				System.out.println("성별 : " + rs.getString(5));
				System.out.println("전화번호 : " + rs.getString(6));
				System.out.println("생년월일 : " + rs.getString(7));
				System.out.println("포인트 : " + rs.getInt(8));
				System.out.println("가족코드 : " + rs.getInt(9));
				System.out.println("마스크 수령 여부 : " + rs.getString(10));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void selectpharchase(String id) {
		try {
			String sql = "SELECT * FROM PURCHASE WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------------------------");
			System.out.println("상품코드          제품명          수량          가격");
			System.out.println("----------------------------------------------------------------");
			while (rs.next()) {
				System.out.println("     " + rs.getInt(2) + "              " + rs.getString(3) + "            "
						+ rs.getInt(4) + "            " + rs.getInt(5));
			}
			System.out.println("----------------------------------------------------------------");

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(String id) {
		String select_sql = "SELECT FCODE FROM CLIENT WHERE FCODE = ?";
		String update_sql = "UPDATE CLIENT SET FCODE = ? WHERE ID = ?";
		int clientNum = 0;

		try {
			int fcode = (int) (Math.random() * 100) + 1;
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, fcode);
			rs = pstmt.executeQuery();

			if (!rs.next()) {
				pstmt = con.prepareStatement(update_sql);
				pstmt.setInt(1, fcode);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				System.out.println("대표자 가족코드 부여 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update2(String id, String head_id) {
		String select_sql = "SELECT FCODE FROM CLIENT WHERE ID = ?";
		String update_sql = "UPDATE CLIENT SET FCODE = ? WHERE ID = ?";

		try {
			pstmt = con.prepareStatement(select_sql);
			pstmt.setString(1, head_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int fcode = rs.getInt(1);
				pstmt = con.prepareStatement(update_sql);
				pstmt.setInt(1, fcode);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
				System.out.println("가족코드 부여 성공!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void goods() {
		try {
			String sql = "SELECT * FROM GOODS";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------------------------");
			System.out.println("상품코드          제품명          수량          가격");
			System.out.println("----------------------------------------------------------------");
			while (rs.next()) {
				System.out.println("     " + rs.getInt(1) + "              " + rs.getString(2) + "            "
						+ rs.getInt(3) + "            " + rs.getInt(4));
			}
			System.out.println("----------------------------------------------------------------");

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void gselect(String id, int pur_code, int pur_stock) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월 dd일 HH시mm분ss초");
		Date time = new Date();
		String time1 = format1.format(time);

		String select_sql = "SELECT * FROM GOODS WHERE GCODE = ?";
		String insert_sql = "INSERT INTO PURCHASE VALUES(?, ?, ?, ?, ?)";
		String update_sql = "UPDATE CLIENT SET POINT = POINT+? WHERE ID = ?";
		String update_sql2 = "UPDATE GOODS SET GSTOCK = ? WHERE GCODE = ?";
		String maskcheck_sql = "UPDATE CLIENT SET FCHECK= 'o' WHERE ID = ?";
		String purselect_sql = "SELECT * FROM PURCHASE WHERE GCODE = ?";

		try {
			// 사용자가 선택한 제품코드의 정보 가져오기
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, pur_code);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String pur_name = rs.getString(2);
				int pur_price = rs.getInt(4) * pur_stock;

				// 구매내역에 데이터 삽입
				pstmt = con.prepareStatement(insert_sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, pur_code);
				pstmt.setString(3, pur_name);
				pstmt.setInt(4, pur_stock);
				pstmt.setInt(5, pur_price);
				pstmt.executeUpdate();

				// 포인트적립
				int point = (int) (pur_price * 0.1);
				pstmt = con.prepareStatement(update_sql);
				pstmt.setInt(1, point);
				pstmt.setString(2, id);
				pstmt.executeUpdate();

				// 구매한 수량만큼 제품테이블에서 수량 감소
				int gstock = rs.getInt(3) - pur_stock;
				pstmt = con.prepareStatement(update_sql2);
				pstmt.setInt(1, gstock);
				pstmt.setInt(2, pur_code);
				pstmt.executeUpdate();

				// 마스크를 구매했을 경우 마스크구매여부를 x로 바꿔준다.
				if (pur_code == 0) {
					pstmt = con.prepareStatement(maskcheck_sql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
				}
				
				System.out.println("결제 완료되었습니다.");
				System.out.println();
				System.out.println("----------------------------------------------------");
				System.out.println("----------------------------------------------------");
				System.out.println("                        [영수증]                           ");
				System.out.println("----------------------------------------------------");
				System.out.println("상품코드          제품명           수량          가격");
				System.out.println("----------------------------------------------------");
				
				
				
				
				System.out.println("결제금액 : " + pur_price);
				System.out.println("포인트 적립 : " + point);
				
				System.out.println("----------------------------------------------------");
				System.out.println("       거래일 : " + time1);
				System.out.println("       훈이 약국을 이용해주셔서 감사합니다.       ");
				System.out.println("----------------------------------------------------");
				System.out.println("----------------------------------------------------");
				System.out.println();
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean AdminCheck(String admin_id, String admin_pw) {
		boolean checkResult = false;
		if (admin_id.equals("admin") && admin_pw.equals("1234")) {
			checkResult = true;
		}
		return checkResult;
	}

	public void selectAllInfo() {
		try {
			String sql = "SELECT * FROM CLIENT";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("------------------------------------------");
				System.out.println("회원번호 : " + rs.getInt(1));
				System.out.println("아이디 : " + rs.getString(2));
				System.out.println("비밀번호 : " + rs.getString(3));
				System.out.println("이름 : " + rs.getString(4));
				System.out.println("성별 : " + rs.getString(5));
				System.out.println("전화번호 : " + rs.getString(6));
				System.out.println("생년월일 : " + rs.getString(7));
				System.out.println("포인트 : " + rs.getInt(8));
				System.out.println("가족코드 : " + rs.getInt(9));
				System.out.println("마스크 수령 여부 : " + rs.getString(10));
				System.out.println("------------------------------------------");
				System.out.println();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void selectInfoByName(String s_name) {
		try {
			String sql = "SELECT * FROM CLIENT WHERE NAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("회원번호 : " + rs.getInt(1));
				System.out.println("아이디 : " + rs.getString(2));
				System.out.println("비밀번호 : " + rs.getString(3));
				System.out.println("이름 : " + rs.getString(4));
				System.out.println("성별 : " + rs.getString(5));
				System.out.println("전화번호 : " + rs.getString(6));
				System.out.println("생년월일 : " + rs.getString(7));
				System.out.println("포인트 : " + rs.getInt(8));
				System.out.println("가족코드 : " + rs.getInt(9));
				System.out.println("마스크 수령 여부 : " + rs.getString(10));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void modify(String s_id, int m_clientCode, String m_id, String m_pw, String m_name, String m_gender,
			String m_phone, String m_birth, int m_point, int m_fcode, String m_fcheck) {
		String sql = "UPDATE CLIENT " + "SET CODE=?, ID=?, PW=?, NAME=?, GENDER=?, "
				+ "PHONE=?, BIRTH=?, POINT=?, FCODE=?, FCHECK=? WHERE ID=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, m_clientCode);
			pstmt.setString(2, m_id);
			pstmt.setString(3, m_pw);
			pstmt.setString(4, m_name);
			pstmt.setString(5, m_gender);
			pstmt.setString(6, m_phone);
			pstmt.setString(7, m_birth);
			pstmt.setInt(8, m_point);
			pstmt.setInt(9, m_fcode);
			pstmt.setString(10, m_fcheck);
			pstmt.setString(11, s_id);
			pstmt.executeUpdate();
			System.out.println("회원정보수정 성공! \n");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 상품기존메뉴
	public void selectMenu() {
		try {
			String sql = "SELECT * FROM GOODS";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------------------------");
			System.out.println("상품코드          제품명          수량          가격");
			System.out.println("----------------------------------------------------------------");
			while (rs.next()) {
				System.out.println("     " + rs.getInt(1) + "              " + rs.getString(2) + "            "
						+ rs.getInt(3) + "            " + rs.getInt(4));
			}
			System.out.println("----------------------------------------------------------------");

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 상품 추가
	public void addMenu(int addGcode, String addGname, int addGstock, int addGprice) {
		String sql = "INSERT INTO GOODS VALUES(?, ?, ?, ?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, addGcode);
			pstmt.setString(2, addGname);
			pstmt.setInt(3, addGstock);
			pstmt.setInt(4, addGprice);

			pstmt.executeUpdate();

			System.out.println("상품정보 추가 성공!");

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	// 있는 메뉴인지 체크
	public boolean menuCheck(String gname) {
		boolean checkResult = false;

		String sql = "SELECT GNAME FROM GOODS WHERE GNAME = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		return checkResult;
	}

	// 상품 편집
	public void modifyMenu(String gname, int m_gcode, String m_gname, int m_gstock, int m_gprice) {

		String sql = "UPDATE GOODS SET GCODE=?, GNAME=?, GSTOCK=?, GPRICE=? WHERE GNAME=?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, m_gcode);
			pstmt.setString(2, m_gname);
			pstmt.setInt(3, m_gstock);
			pstmt.setInt(4, m_gprice);
			pstmt.setString(5, gname);

			pstmt.executeUpdate();

			System.out.println("제품정보 수정 완료!");

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

}
