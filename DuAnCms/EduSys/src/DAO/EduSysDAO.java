/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.awt.List;

/**
 *
 * @author ADMIN
 */
abstract public class EduSysDAO<EntityType,KeyType> {
    abstract public void insert(EntityType entity);
    abstract public void update(EntityType entity);
    abstract public void delete(KeyType id);
    abstract public EntityType selectById(KeyType id);
    abstract public java.util.List<EntityType> selectAll();
    abstract protected java.util.List<EntityType> selectBySql(String sql , Object ...args);
    
}
