/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.connect.DBContext;
import com.entity.Information;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
public class InfoModel {

    private final DBContext db;
    
    public InfoModel() throws Exception {
        db = new DBContext();
    }

    public Map<String,String> getInfoPage() throws Exception {
        String query = "select * from Information";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
//        ArrayList<Information> output = new ArrayList<>();
        Map<String,String> map = new HashMap<String, String>();
        
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String content = rs.getString("content");
//                Information i = new Information(id, name, content);
//                output.add(i);
                map.put(name, content);
            }
        } catch (Exception ex) {
            throw ex;
        } 
        return map;
    }

    public String getAddress(List<Information> info) throws Exception {
        for(int i=0; i < info.size(); i++){
            if(info.get(i).getName().equals("address")){
                return info.get(i).getContent();
            }
        }
        return "";
    }

    public String getTel(List<Information> info) throws Exception {
         for(int i=0; i < info.size(); i++){
            if(info.get(i).getName().equals("tel")){
                return info.get(i).getContent();
            }
        }
        return "";
    }

    public String getMail(List<Information> info) throws Exception {
        for(int i=0; i < info.size(); i++){
            if(info.get(i).getName().equals("mail")){
                return info.get(i).getName() + ":" + info.get(i).getContent();
            }
        }
        return "";
    }

    public List<String> getOpenHours(List<Information> info) throws Exception {
        List<String> days = new ArrayList<>();
        for(int i=0; i < info.size(); i++){
            if(info.get(i).getName().contains("day")){
                days.add(info.get(i).getName()+": "+info.get(i).getContent());
            }
        }
        return days;
    }

    public String getUrlCover(List<Information> info) throws Exception {
        for(int i=0; i < info.size(); i++){
            if(info.get(i).getName().equals("imgCover")){
                return db.getResource() + info.get(i).getContent();
            }
        }
        return "";
    }
}
