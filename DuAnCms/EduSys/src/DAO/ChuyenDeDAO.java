/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.ChuyenDe;
import entity.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author ADMIN
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    String INSERT_SQL = "INSERT INTO ChuyenDe\n"
            + "             (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa)\n"
            + "VALUES (?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChuyenDe\n"
            + "SET TenCD =?, HocPhi =? ,ThoiLuong =?, Hinh =?, MoTa = ? WHERE MaCD =?";
    String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD =?";
    String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe WHERE HocPhi > 10000";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD=?";
//    private String maCD;
//    private String tenCD;
//    private double hocPhi;
//    private int thoiLuong;
//    private String hinh;
//    private String moTa;

    @Override
    public void insert(ChuyenDe e) {
        JDBCHelper.executeUpdate(INSERT_SQL, e.getMaCD(), e.getTenCD(), e.getHocPhi(), e.getThoiLuong(), e.getHinh(), e.getMoTa());
    }

    @Override
    public void update(ChuyenDe e) {
        JDBCHelper.executeUpdate(UPDATE_SQL, e.getTenCD(), e.getHocPhi(), e.getThoiLuong(), e.getHinh(), e.getMoTa(), e.getMaCD());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getDouble(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                list.add(cd);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
