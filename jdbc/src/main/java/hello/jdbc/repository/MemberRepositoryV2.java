package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - ConnectionParam
 */
@Slf4j
public class MemberRepositoryV2 {

    private final DataSource dataSource;

    public MemberRepositoryV2(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
    public void update(Connection con,String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            //등록,수정,삭제처럼 데이터를 변경하는 쿼리는 executeUpdate() 조회는 executeQuery()
            pstmt.executeUpdate();
        }catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally {
            //커넥션은 여기서 닫지 않는다.(닫으면 세션이 종료되니깐)
            JdbcUtils.closeStatement(pstmt);
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

    public Member findById(Connection con, String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
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
            //커넥션은 여기서 닫지 않는다.(닫으면 세션이 종료되니깐)
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(pstmt);
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


   private void close(Connection con, Statement stmt, ResultSet resultSet) {
        //JdbcUtils를 통하여 close()
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeConnection(con);
    }

    //커넥션 가져오는 메소드
    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        log.info("get connection={}, class={}",con);
        return con;
    }
}
