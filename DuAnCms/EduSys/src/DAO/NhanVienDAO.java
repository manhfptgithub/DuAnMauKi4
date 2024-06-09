/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.NhanVien;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    String INSERT_SQL = "INSERT INTO NhanVien(MaNV , MatKhau , HoTen , VaiTro) VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET MatKhau=? , HoTen=?, VaiTro=? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV=?";
// private String maNV ;
//    private String matKhau ;
//    private String hoTen ;
//    private boolean vaiTro ;
    @Override
    public void insert(NhanVien e) {
        JDBCHelper.executeUpdate(INSERT_SQL, e.getMaNV() , e.getMatKhau() , e.getHoTen() , e.isVaiTro());
    }

    @Override
    public void update(NhanVien e) {
        JDBCHelper.executeUpdate(UPDATE_SQL , e.getMatKhau() , e.getHoTen() , e.isVaiTro(), e.getMaNV());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setMatKhau(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setVaiTro(rs.getBoolean(4));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            System.out.println("Lỗi 2");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String s = "g";
        if (s.isEmpty()) {
            System.out.println("có gì");
        }
    }
}
