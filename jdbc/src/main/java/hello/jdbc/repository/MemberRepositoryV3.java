package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * 트랜잭션 - 트랜잭션 매니저
 * DataSourceUtils.getConnection()
 * DataSourceUtils.releaseConnection()
 */
@Slf4j
public class MemberRepositoryV3 {

    private final DataSource dataSource;

    public MemberRepositoryV3(DataSource dataSource) {
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


    /**
     * close()를 직접 사용하면 커넥션이 유지되지 않는다. 그러나 DataSourceUtils를 사용하면 커넥션을 닫지 않고 그대로 유지해 준다.
     * 동기화된 매니저가 없으면 그냥 종료한다.
     * @param con
     * @param stmt
     * @param resultSet
     */
   private void close(Connection con, Statement stmt, ResultSet resultSet) {
        //JdbcUtils를 통하여 close()
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(stmt);
        //주의 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
       DataSourceUtils.releaseConnection(con, dataSource);
        //JdbcUtils.closeConnection(con);
    }

    /**
     * 트랙잭션 동기화 매니저가 관리하는 매니저가 있으면  해당 커넥션을 반환한다.
     * 관리하는 매니저가 없으면 새로운 커넥션을 생성하여 반환한다.
     * @return
     * @throws SQLException
     */
    //커넥션 가져오는 메소드
    private Connection getConnection() throws SQLException {
        //주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get connection={}, class={}",con);
        return con;
    }
}
