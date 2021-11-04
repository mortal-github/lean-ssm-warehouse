package practice.ssm.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.ssm.warehouse.exception.EmptyResultException;
import practice.ssm.warehouse.exception.NotChangeException;
import practice.ssm.warehouse.mapper.WarehouseMapper;
import practice.ssm.warehouse.pojo.Warehouse;
import practice.ssm.warehouse.service.WarehouseService;

import java.util.List;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private final WarehouseMapper warehouseMapper;

    public WarehouseServiceImpl(WarehouseMapper warehouseMapper){
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public List<Warehouse> getSome(int offset, int limit, String status_name, String address) {
        Warehouse.Status status = null;
        if(null != status_name){
            status = Warehouse.Status.valueOf(status_name);
        }
        List<Warehouse> list = warehouseMapper.selectSome(offset, limit, status, address);
        if(list.isEmpty()){
            throw new EmptyResultException("can not found any resource by these parameter, check your parameter!");
        }
        return list;
    }

    @Override
    public Warehouse get(int id) {
        Warehouse warehouse = warehouseMapper.select(id);
        if(null == warehouse){
            throw new EmptyResultException("can not found resource by this parameter id");
        }
        return warehouse;
    }

    @Override
    public int post(Warehouse warehouse) {
        if(null == warehouse.getStatus()){
            warehouse.setStatus(Warehouse.Status.create);
        }
        int count = warehouseMapper.insert(warehouse);
        assert count == 1;
        return warehouse.getId();
    }

    @Override
    public void patch(int id, Warehouse warehouse) {
        warehouse.setId(id);
        int count = warehouseMapper.update(warehouse);
        if(0 == count){
            throw new NotChangeException("have not change, may be can not found resource by parameter id");
        }
        assert 1 == count;
    }

    @Override
    public void delete(int id){
        int count = warehouseMapper.delete(id);
        if(0 == count){
            throw new NotChangeException("have not delete, maybe can not found resource by parameter id");
        }
        assert 1 == count;
    }
}
