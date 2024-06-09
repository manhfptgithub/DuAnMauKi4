/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.NguoiHoc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.JDBCHelper;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    String INSERT_SQL = "INSERT INTO NguoiHoc\n"
            + "(MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK)\n"
            + "VALUES (?,?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NguoiHoc\n"
            + "SET HoTen =?, NgaySinh =?, GioiTinh =?, DienThoai =?, Email =?, GhiChu =?, MaNV =?, NgayDK =? WHERE MaNH =?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH=?";

    //    private String maNH;
    //    private String hoTen;
    //    private Date ngaySinh;
    //    private boolean gioiTinh;
    //    private String dienThoai;
    //    private String Email;
    //    private String ghiChu;
    //    private String maNV;
    //    private Date ngayDK;
    @Override
    public void insert(NguoiHoc e) {
        JDBCHelper.executeUpdate(INSERT_SQL, e.getMaNH(), e.getHoTen(), e.getNgaySinh(), e.isGioiTinh(), e.getDienThoai(), e.getEmail(), e.getGhiChu(), e.getMaNV(), e.getNgayDK());
    }

    @Override
    public void update(NguoiHoc e) {
        JDBCHelper.executeUpdate(UPDATE_SQL, e.getHoTen(), e.getNgaySinh(),e.isGioiTinh() ,e.getDienThoai(), e.getEmail(), e.getGhiChu(), e.getMaNV(), e.getNgayDK(), e.getMaNH());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }
//private String maNH;
//    private String hoTen;
//    private Date ngaySinh;
//    private boolean gioiTinh;
//    private String dienThoai;
//    private String Email;
//    private String ghiChu;
//    private String maNV;
//    private Date ngayDK;

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString(1));
                nh.setHoTen(rs.getString(2));
                nh.setNgaySinh(rs.getDate(3));
                nh.setGioiTinh(rs.getBoolean(4));
                nh.setDienThoai(rs.getString(5));
                nh.setEmail(rs.getString(6));
                nh.setGhiChu(rs.getString(7));
                nh.setMaNV(rs.getString(8));
                nh.setNgayDK(rs.getDate(9));
                list.add(nh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<NguoiHoc> selectByKeyWord(String keyWord) {
        String sql = "select * from NguoiHoc WHERE HoTen LIKE ?";
        return selectBySql(sql, "%" + keyWord + "%");
    }
    
    // nó sẽ tìm kiếm các người học có tên trong ô tìm kiếm và không có trong khóa học đó
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND MaNH NOT IN(SELECT MaNH FROM HocVien WHERE MaKH=?)";

        return this.selectBySql(sql, "%" + keyword + "%", makh);
    }
}
