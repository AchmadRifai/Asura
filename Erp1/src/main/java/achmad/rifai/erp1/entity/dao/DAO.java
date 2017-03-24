/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.erp1.entity.dao;

/**
 *
 * @author ai
 */
public interface DAO<T>{
    void insert(T v)throws Exception;
    void createTable()throws Exception;
    void delete(T w)throws Exception;
    void update(T a,T b)throws Exception;
    java.util.List<T>all()throws Exception;
}