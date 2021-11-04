package practice.ssm.warehouse.mapper;

import org.apache.ibatis.annotations.Param;
import practice.ssm.warehouse.pojo.Detail;
import practice.ssm.warehouse.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeMapper {

    List<GoodsType> getSome(GoodsType goodsType);

    GoodsType get(int id);

    int insert(GoodsType goodsType);

    int update(GoodsType goodsType);

    int delete(int id);

    List<Detail> getDetail(@Param("goods_type") int goods_type, @Param("details") List<Detail> details);

    int insertDetail(@Param("goods_type") int goods_type, @Param("details") List<Detail> details);

    int deleteDetail(@Param("goods_type") int goods_type, @Param("details") List<Detail> details);
}
