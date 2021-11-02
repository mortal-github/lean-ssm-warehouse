package practice.ssm.warehouse.mapper;

import practice.ssm.warehouse.pojo.Example;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExampleMapper {

//    @Select("SELECT * FROM example WHERE id = #{id}")
    Example getExample(@Param("id") int id);

    List<Example> getAllExample();

    int postExample(Example examples);

    int patchExample(Example example);

    int deleteExample(int id);
}
