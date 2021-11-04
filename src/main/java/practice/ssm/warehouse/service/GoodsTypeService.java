package practice.ssm.warehouse.service;

import practice.ssm.warehouse.pojo.Detail;
import practice.ssm.warehouse.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeService {

    List<GoodsType> getSome(GoodsType goodsType);

    GoodsType get(int id);

    int post(GoodsType goodsType);

    void post(int goods_type, List<Detail> details);

    void patch(GoodsType goodsType);

    void delete(int goods_type);

    void delete(int goods_type, List<Detail> details);
}
