import connectDatabase.DatabaseConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class QL_Diem_Sv {
    Connection conn = DatabaseConnect.getConnection();
    public QL_Diem_Sv(){

    }
    public void themDiem(int student_id, int subject_id, double grade){
        String sql = "INSERT INTO grades (student_id,subject_id,grade) VALUE (?,?,?)";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1,student_id);
            st.setInt(2,subject_id);
            st.setDouble(10,grade);
            st.executeUpdate();
        } catch (SQLException err){
            err.printStackTrace();
        }
    }
    public void suaDiem(int student_id, int subject_id, double grade){
        String sql = "UPDATE grades SET grade = ? WHERE student_id = ? AND subject_id = ? ";
        try(PreparedStatement st = conn.prepareStatement(sql)){
            st.setInt(1,student_id);
            st.setInt(2,subject_id);
            st.setDouble(10,grade);
            st.executeUpdate();
        } catch (SQLException err){
            err.printStackTrace();
        }
    }
    public void xoaDiem(){

    }
}
