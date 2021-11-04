package practice.ssm.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsType {

    private Integer id;
    private String name;
    private String description;
    private Integer length;
    private Integer width;
    private Integer height;
    private List<Detail> details;

}
