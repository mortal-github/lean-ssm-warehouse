package practice.ssm.warehouse.service;

import practice.ssm.warehouse.pojo.Warehouse;

import java.util.List;

public interface WarehouseService {

    List<Warehouse> getSome(int offset, int limit, String address, String status);

    Warehouse get(int id);

    int post(Warehouse warehouse);

    void patch(int id, Warehouse warehouse);

    void delete(int id);
}
