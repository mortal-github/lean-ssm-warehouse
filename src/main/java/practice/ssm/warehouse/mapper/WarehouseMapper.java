package practice.ssm.warehouse.mapper;

import org.apache.ibatis.annotations.Param;
import practice.ssm.warehouse.pojo.Warehouse;

import java.util.List;

public interface WarehouseMapper {

    List<Warehouse> selectSome( @Param("offset") int offset,
                            @Param("limit") int limit,
                            @Param("status") Warehouse.Status status,
                            @Param("address") String address);

    Warehouse select(@Param("id") int id);

    int insert(Warehouse warehouse);

    int update(Warehouse warehouse);

    int delete(@Param("id") int id);
}
