package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// Statement 사용
// CRUD 를 메소드화 - url, user, pwd 를 static
//   메소드내에서 Connection, Statement, ResutSet 객체화
//   메소드내에서 예외 처리
// Statement -> PreparedStatement 전환
// 하드코딩된 value --> 메소드의 parameter
// Dto -> select 에 적용
public class Test3 {
    // MySQL 에 접근하기 위해 필요한 3가지
    static String url = "jdbc:mysql://localhost:3306/madang";
    static String user = "root";
    static String pwd = "1234";

    public static void main(String[] args) {

//      insertCustomer(6, "손흥민", "영국 토트넘", "010-6666-6666");
//        updateCustomer(6, "대한민국 서울");
        deleteCustomer(6);
//      listCustomer();
//      detailCustomer();
    }
    static void insertCustomer(int custId, String name, String address, String phone) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String insertSql = "insert into customer values ( ?, ?, ?, ? ); "; // value 에 해당하는 부분을 ? 로 대체

        try {
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.prepareStatement(insertSql);
            pstmt.setInt(1, custId);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);

            int ret = pstmt.executeUpdate();
            System.out.println(ret);

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void updateCustomer(int custId, String address) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String updateSql = "update customer set address = ? where custid = ?; ";

        try {
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.prepareStatement(updateSql);
            pstmt.setString(1, address);
            pstmt.setInt(2, custId);

            int ret = pstmt.executeUpdate();
            System.out.println(ret);

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void deleteCustomer(int custId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String deleteSql = "delete from customer where custid = ?; ";

        try {
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.createStatement(deleteSql);
            pstmt.setInt(1, custId);

            int ret = pstmt.executeUpdate(deleteSql);
            System.out.println(ret);

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void listCustomer() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, pwd);
            stmt = con.createStatement();

            String selectSql = "select custid, name, address, phone from customer; ";
            rs = stmt.executeQuery(selectSql);
            while(rs.next()) {
                System.out.println(rs.getInt("custid") + " | " + rs.getString("name")+ " | " + rs.getString("address")+ " | " + rs.getString("phone"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close(); // row 가 있는 상태를 전제
                stmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void detailCustomer() {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, pwd);
            stmt = con.createStatement();

            String selectSql = "select custid, name, address, phone from customer where custid = 4; ";
            rs = stmt.executeQuery(selectSql);
            if(rs.next()) {
                System.out.println(rs.getInt("custid") + " | " + rs.getString("name")+ " | " + rs.getString("address")+ " | " + rs.getString("phone"));
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close(); // row 가 있는 상태를 전제
                stmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
