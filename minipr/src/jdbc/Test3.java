package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    static String pwd = "root";

    public static void main(String[] args) {

//      insertCustomer(6, "손흥민", "영국 토트넘", "010-6666-6666");
//      updateCustomer(6, "대한민국 서울");
//      deleteCustomer(6);
//      List<CustomerDto> list = listCustomer();  // 없으면 list 는 null 은 아니고 empty
//      for (CustomerDto dto : list) {
//          System.out.println(dto);
//      }
        CustomerDto dto = detailCustomer(2); // 없으면 null
        System.out.println(dto);
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
            pstmt = con.prepareStatement(deleteSql);
            pstmt.setInt(1, custId);

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

    static List<CustomerDto> listCustomer() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<CustomerDto> list = new ArrayList<>();

        String selectSql = "select custid, name, address, phone from customer; ";

        try {
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.prepareStatement(selectSql);

            rs = pstmt.executeQuery(selectSql);
            while(rs.next()) {
                // 각 row 를 CustomerDto 객체로 만들고 ArrayList 담는다.
                CustomerDto dto = new CustomerDto();
                dto.setCustId(rs.getInt("custid"));
                dto.setName(rs.getString("name"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));

                list.add(dto);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close(); // row 가 있는 상태를 전제
                pstmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    static CustomerDto detailCustomer(int custId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        CustomerDto dto = null;

        String selectSql = "select custid, name, address, phone from customer where custid = ?; ";

        try {
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.prepareStatement(selectSql);
            pstmt.setInt(1, custId);

            rs = pstmt.executeQuery();
            if(rs.next()) {
                // 각 row 를 CustomerDto 객체로 만들고 ArrayList 담는다.
                dto = new CustomerDto();
                dto.setCustId(rs.getInt("custid"));
                dto.setName(rs.getString("name"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close(); // row 가 있는 상태를 전제
                pstmt.close();
                con.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return dto;
    }
}