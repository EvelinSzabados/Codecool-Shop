package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;
    DataSource dataSource;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void getDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) throws SQLException {
        String qr = "INSERT INTO supplier(name,description) VALUES(?,?);";
        PreparedStatement stmt = dataSource.getConnection().prepareStatement(qr);

        stmt.setString(1,supplier.getName());
        stmt.setString(2,supplier.getDescription());
        stmt.executeUpdate();


    }

    @Override
    public Supplier find(int id)throws SQLException {
        return data.stream().filter(t -> t.getId()==id).findFirst().orElse(null);

        String get = "SELECT * FROM supplier WHERE id=?;";
        PreparedStatement stmt =dataSource.getConnection().prepareStatement(get);

        stmt.setInt(1,id);
        ResultSet res = stmt.executeQuery();
        Supplier supplier = null;
        if(res.next()){
            supplier = new Supplier(res.getInt("id"),res.getString("name"),res.getString("description"));
        }
        return supplier;
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() throws SQLException{
    List<Supplier> allSupplier = new ArrayList<>();
    String getAll = "SELECT * FROM supplier;";
    PreparedStatement stmt = dataSource.getConnection().prepareStatement(getAll);
    ResultSet res = stmt.executeQuery();
    while(res.next()){
        Supplier supplier = new Supplier(res.getString("name"),res.getString("description"));
        allSupplier.add(supplier);
    }
        return allSupplier;
    }
}
