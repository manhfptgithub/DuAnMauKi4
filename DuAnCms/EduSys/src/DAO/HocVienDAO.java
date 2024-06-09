/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.ChuyenDe;
import entity.HocVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author ADMIN
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    String INSERT_SQL = "INSERT INTO HocVien\n"
            + "             (MaKH, MaNH, Diem)\n"
            + "VALUES (?,?,?)";
    String UPDATE_SQL = "UPDATE HocVien\n"
            + "SET MaKH =?, MaNH =?, Diem =? WHERE MaHV = ?";
    String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";
// private int maHV ;
//    private int maKH ;
//    private String maNH ;
//    private double diem ;

    @Override
    public void insert(HocVien e) {
        JDBCHelper.executeUpdate(INSERT_SQL, e.getMaKH(), e.getMaNH(), e.getDiem());
    }

    @Override
    public void update(HocVien e) {
        JDBCHelper.executeUpdate(UPDATE_SQL, e.getMaKH(), e.getMaNH(), e.getDiem(), e.getMaHV());
    }

//    private int maHV ;
//    private int maKH ;
//    private String maNH ;
//    private double diem ;
    @Override
    public void delete(Integer id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiem(rs.getDouble(4));
                list.add(hv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        return null ;
    }
// Câu lệnh này lấy ra hocVien có maKH tương ứng 

    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }

    public List<HocVien> selectByNguoiHoc(String maNH) {
        String sql = "SELECT * FROM HocVien WHERE MaNH=?";
        return this.selectBySql(sql, maNH);
    }

}
