package hello.jdbc.repository;

import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            //파라미터 바인딩
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();//쿼리 실행
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            //pstmt를 닫아줘야한다. 그후 connection을 닫아줘야 한다.
            close(con, pstmt, null);
        }
    }

    //statment와 connection, ResultSet 닫는 메소드드
   private void close(Connection con, Statement stmt, ResultSet resultSet) {
       if (resultSet != null) {
           try {
               resultSet.close();
           } catch (SQLException e) {
               log.info("error",e);
           }
       }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error",e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }

    //커넥션 가져오는 메소드
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
