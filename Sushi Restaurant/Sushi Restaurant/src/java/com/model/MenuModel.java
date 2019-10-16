package com.model;

import com.connect.DBContext;
import com.entity.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuModel {

    private final DBContext db;

    public MenuModel() throws Exception {
        db = new DBContext();
    }

    public List<Menu> getMenusFromTo(int page, int pageSize) throws SQLException, Exception {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM (SELECT *,ROW_NUMBER() OVER (ORDER BY id) AS Number FROM dbo.Menu) AS Menus\n"
                + "WHERE Menus.Number BETWEEN ? AND ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String content = rs.getString(3);
                double price = rs.getFloat(4);
                menus.add(new Menu(id, name, content, price));
            }
        } catch (Exception ex) {
            throw ex;
        }
        return menus;
    }

    public int getTotalRows() throws Exception {
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "select count(*) from Menu";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                rows = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return rows;
    }
}
