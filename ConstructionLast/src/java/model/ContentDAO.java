/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import enity.Content;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ContentDAO {
    DBContext db;

    public ContentDAO() throws NamingException{
        db = new DBContext();
    }
    
    public Content getContent() throws SQLException, Exception{
        Content c = new Content();
        String query = "select * from Info";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = db.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setAddress(rs.getString("address"));
                c.setCity(rs.getString("city"));
                c.setCountry(rs.getString("country"));
                c.setTel(rs.getString("tel"));
                c.setEmail(rs.getString("email"));
            }
        }catch(Exception ex){
            db.closeConnection(rs, ps, con);
            throw ex;
        }
        return c;
    }
}
