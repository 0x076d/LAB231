/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import enity.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class MessageDAO {
    DBContext db;

    public MessageDAO() throws NamingException{
        db = new DBContext();
    }
    
    public boolean insertMessage(Message m) throws Exception{
        String sql = "Insert into [Message](Name, Email, Message)"
                +"values(?,?,?)";
        int check = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        
        try{
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getMessage());
            check = ps.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage().toString());
        }finally{
            db.closeConnection(rs, ps, con);
        }
        return check > 0; 
    }
}
