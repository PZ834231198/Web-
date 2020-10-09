package dao;

import tools.DatabaseConnection;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User findUserByUserName(String userName){
        User user = null;
        Connection con = DatabaseConnection.getConnection();

        String sql = "select * from t_user";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            while (rs.next()) {
                if (userName.equals(rs.getString("userName"))){
                    String password = rs.getString("password");
                    String chrName = rs.getString("chrName");
                    String role = rs.getString("role");
                    user = new User(userName,password,chrName,role);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        DatabaseConnection.closeAll(rs,ps,con);

        return user;
    }
}
