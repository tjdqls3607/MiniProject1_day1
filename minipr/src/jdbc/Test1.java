package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
// Statement 사용
public class Test1 {
    public static void main(String[] args) throws Exception{
        // MySQL 에 접근하기 위해 필요한 3가지
        String url = "jdbc:mysql://localhost:3306/madang";
        String user = "root";
        String pwd = "1234";
        // Driver 테스트 ( DriverManager 에 자동 등록 )
//
        // JDBC 드라이버 객체를 DriverManager 에 등록 단계 필요 <= 자동으로 처리

        // Connection ( DB 연결 )
        Connection con = DriverManager.getConnection(url, user, pwd);

        // Statement ( sql 전달 객체 )
        Statement stmt = con.createStatement();

        // insert
        // dup 확인
//      {
//          String insertSql = "insert into customer values ( 6, '손흥민', '영국 토트넘', '010-6666-6666' ); ";
//          int ret = stmt.executeUpdate(insertSql);
//          System.out.println(ret);
//      }
        // update
//      {
//          String updateSql = "update customer set address = '대한민국 서울' where custid = 7; ";
//          int ret = stmt.executeUpdate(updateSql);
//          System.out.println(ret);
//      }
        // delete
        {
            String deleteSql = "delete from customer where custid = 6; ";
            int ret = stmt.executeUpdate(deleteSql);
            System.out.println(ret);
        }


        con.close();
    }
}