/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.KhoaHoc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.JDBCHelper;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    String INSERT_SQL = "INSERT INTO KhoaHoc\n"
            + "             ( TenKH ,MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao )\n"
            + "VALUES (?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc\n"
            + "SET TenKH=? ,MaCD=?, HocPhi =?, ThoiLuong =?, NgayKG =?, GhiChu =?, MaNV =?, NgayTao =?  WHERE MaKH =?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
    String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";
// private int maKH;
//    private String MaCD;
//    private double hocPhi;
//    private int thoiLuong;
//    private Date ngayKG;
//    private String ghiChu;
//    private String maNV;
//    private Date ngayTao;

    @Override
    public void insert(KhoaHoc e) {
        JDBCHelper.executeUpdate(INSERT_SQL, e.getTenKh(), e.getMaCD(), e.getHocPhi(), e.getThoiLuong(), e.getNgayKG(), e.getGhiChu(), e.getMaNV(), e.getNgayTao());
    }

    @Override
    public void update(KhoaHoc e) {
        JDBCHelper.executeUpdate(UPDATE_SQL,e.getTenKh(), e.getMaCD(), e.getHocPhi(), e.getThoiLuong(), e.getNgayKG(), e.getGhiChu(), e.getMaNV(), e.getNgayTao(), e.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
//                MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao , TenKH
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt(1));
                kh.setTenKh(rs.getString(2));
                kh.setMaCD(rs.getString(3));
                kh.setHocPhi(rs.getDouble(4));
                kh.setThoiLuong(rs.getInt(5));
                kh.setNgayKG(rs.getDate(6));
                kh.setGhiChu(rs.getString(7));
                kh.setMaNV(rs.getString(8));
                kh.setNgayTao(rs.getDate(9));

                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<KhoaHoc> selectByChuyenDe(String maCD) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaCD=?";
        return this.selectBySql(sql, maCD);
    }

    public boolean selectKey(String manv) {
        String sql = "SELECT * FROM KhoaHoc WHERE MaNV=?";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, manv);
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> selectYears() {
        String sql = "SELECT DISTINCT YEAR(NgayKG) N'YEAR' FROM KhoaHoc ORDER BY YEAR(NgayKG) DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    
    
   
}
