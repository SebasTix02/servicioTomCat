
package Model;

import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariel
 */
public class UserDAO implements ICRUD {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    ConnectionDB conec = new ConnectionDB();
    //querys
    private static final String SQL_SELECT="SELECT * FROM persons";
    private static final String SQL_LOGIN="SELECT COUNT(id) FROM admins WHERE username=? and password=?";
    private static final String SQL_INSERT="INSERT INTO persons (dni, name, lastname, age) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE="UPDATE persons SET name=?, lastname=?, age=? WHERE dni=?";
    private static final String SQL_DELETE="DELETE FROM persons WHERE dni=?";
    

    
    public String lists() throws SQLException{
        con = conec.getConnection();
        if(con != null){
            return "Succesfully "+con.getMetaData().getDatabaseProductName();
        }else {
            return "couldn't connect to db";
        }
    }

    @Override
    public List list() {
        List<User> data= new ArrayList<>();
        
        //get connection to db 
        try {
            con= conec.getConnection();
            ps=con.prepareStatement(SQL_SELECT);
            rs= ps.executeQuery();
            
            while(rs.next()){
                User u = new User();
                u.setDni(rs.getString("dni"));
                u.setName(rs.getString("name"));
                u.setLastName(rs.getString("lastname"));
                u.setAge(rs.getInt("age"));
                data.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

   

    @Override
    public String add(User user) {
        con = conec.getConnection();
        int cantRegistersAffected =0; 
        try {
            ps=con.prepareStatement(SQL_INSERT);
            ps.setString(1,user.getDni());
            ps.setString(2,user.getName());
            ps.setString(3,user.getLastName());
            ps.setInt(4,user.getAge());
            
            cantRegistersAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (cantRegistersAffected > 0) {
            return "User register succesfully "+ cantRegistersAffected;   
        }else{
            return "User register failed "+ cantRegistersAffected;
        }
    }

    @Override
    public String edit(User user) {
         con= conec.getConnection();
         int cantRegistersAffected =0; 
        try {
            ps=con.prepareStatement(SQL_UPDATE);
            ps.setString(1,user.getName());
            ps.setString(2,user.getLastName());
            ps.setInt(3,user.getAge());
            ps.setString(4,user.getDni());
            cantRegistersAffected=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if (cantRegistersAffected > 0) {
            return "User edit succesfully "+ cantRegistersAffected;   
        }else{
            return "User edit failed "+ cantRegistersAffected;
        }
          
    }

    
    
    @Override
    public String delete(String dni) {
        con= conec.getConnection();
        int cantRegistersAffected =0; 
        try {
            ps=con.prepareStatement(SQL_DELETE);
            ps.setString(1,dni);
              cantRegistersAffected=ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        if (cantRegistersAffected > 0) {
            return "User delete succesfully "+ cantRegistersAffected;   
        }else{
            return "User delete failed "+ cantRegistersAffected;
        }
    }
    
    
    public boolean adminExist(String username, String password){
          List<User> admins= new ArrayList<>();
        
        //get connection to db 
        try {
            con= conec.getConnection();
            ps=con.prepareStatement(SQL_LOGIN);
            ps.setString(1, username);
            ps.setString(2, password);
            rs=ps.executeQuery();
            
            if(rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }   

}
