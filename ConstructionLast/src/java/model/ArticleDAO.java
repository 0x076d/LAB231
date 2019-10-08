/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import enity.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ArticleDAO {

    DBContext db;

    public ArticleDAO() throws NamingException {
        db = new DBContext();
    }

    /**
     * 
     * @param page số trang
     * @param pageSize là số lượng các bài viết có trong 1 trang
     * @return danh sách các Articles có vị trí từ [page * pageSize - (pageSize - 1)]
     * đến [page * pageSize] trong bảng dữ liệu lấy ra từ câu query.
     * @throws Exception : nếu câu query có lỗi hoặc kết nối database có lỗi về kết nối sẽ
     * bắn ra 1 exception để controller xử lý bắn ra trang errorPage
     */
    public List<Article> getArticleInPageHasPageSizeInHome(int page, int pageSize) throws Exception {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<Article> articles = new ArrayList<>();
        //
        String query = "select * from (\n"
                + "select *, ROW_NUMBER() over (order by id asc) as rownumber \n"
                + "from Artical) as Artical\n"
                + "where Artical.rownumber >= ? and \n"
                + "Artical.rownumber <= ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = db.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, from);
            ps.setInt(2, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setType(rs.getString("Type"));
                a.setTitle(rs.getString("Title"));
                a.setShortDescription(rs.getString("SortDescription"));
                a.setFullDescription(rs.getString("LongDescription"));
                a.setImglink(db.getImagePath()+ rs.getString("imglink"));
                articles.add(a);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, con);
        }
        return articles;
    }

    /**
     * 
     * @param page
     * @param pageSize
     * @return
     * @throws Exception 
     */
    public List<Article> getArticleInPageHasPageSizeInSale(int page, int pageSize) throws Exception {
        int from = page * pageSize - (pageSize - 1);
        int to = page * pageSize;
        List<Article> articles = new ArrayList<>();
        String query = "select * from (\n"
                + "select *, ROW_NUMBER() over (order by id desc) as rownumber \n"
                + "from Artical where Type = 'sale') as Artical\n"
                + "where rownumber BETWEEN ? and ?";
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
                Article a = new Article();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("Title"));
                a.setType(rs.getString("Type"));
                a.setImglink(db.getImagePath()+ rs.getString("imglink"));
                a.setShortDescription(rs.getString("SortDescription"));
                a.setFullDescription(rs.getString("LongDescription"));
                articles.add(a);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return articles;
    }

    //Get Sale or no-sale products
    public Article getArticleByType(String Type) throws Exception {
        Article a = new Article();
        String query = "select * from Artical where Type=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Type);
            rs = ps.executeQuery();
            while (rs.next()) {
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("Title"));
                a.setType(rs.getString("Type"));
                a.setImglink(db.getImagePath()+ rs.getString("imglink"));
                a.setShortDescription(rs.getString("SortDescription"));
                a.setFullDescription(rs.getString("LongDescription"));
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return a;
    }

    //    get intro and about
    public Article getArticleIntroByType(String Type) throws Exception {
        Article a = new Article();
        String query = "select * from Intros where Type=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, Type);
            rs = ps.executeQuery();
            while (rs.next()) {

                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("Title"));
                a.setType(rs.getString("Type"));
                a.setImglink(db.getImagePath()+ rs.getString("imglink"));
                a.setShortDescription(rs.getString("SortDescription"));
                a.setFullDescription(rs.getString("LongDescription"));
            }
            db.closeConnection(rs, ps, conn);

        } catch (Exception ex) {

            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return a;
    }

    //Get detail of product
    public Article getArticleByID(int id) throws Exception {
        Article a = new Article();
        String query = "select * from Artical where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("Title"));
                a.setType(rs.getString("Type"));
                a.setImglink(db.getImagePath()+ rs.getString("imglink"));
                a.setShortDescription(rs.getString("SortDescription"));
                a.setFullDescription(rs.getString("LongDescription"));
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return a;
    }

    //Get totalrow to paging
    public int getTotalRowsInHome() throws Exception {
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select count(*) from Artical";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                rows = rs.getInt(1);
            }
            db.closeConnection(rs, ps, conn);
            return rows;
        } catch (Exception ex) {
            db.closeConnection(rs, ps, conn);
            throw ex;
        }
    }

    public int getTotalRowsSale() throws Exception {
        int rows = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select count(*) from Artical where Type ='sale'";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                rows = rs.getInt(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, ps, conn);
        }
        return rows;
    }
}
