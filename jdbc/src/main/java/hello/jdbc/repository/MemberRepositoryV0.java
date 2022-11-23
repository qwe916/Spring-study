package hello.jdbc.repository;

import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {
    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            //등록,수정,삭제처럼 데이터를 변경하는 쿼리는 executeUpdate() 조회는 executeQuery()
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize = {}", resultSize);
        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            //close()
            close(con, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            //등록,수정,삭제처럼 데이터를 변경하는 쿼리는 executeUpdate() 조회는 executeQuery()
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize = {}", resultSize);
        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            //close()
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if (rs.next()) {//데이터가 있는지 검증
                //빈 객체 만들어서 setting하기
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId =" + memberId);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            //close()
            close(con, pstmt, rs);
        }
    }

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
