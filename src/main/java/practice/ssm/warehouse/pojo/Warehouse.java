package practice.ssm.warehouse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    public enum Status { create, available, work, suspend}

    private int id;
    private Status status;
    private String name;
    private String address;
    private String description;
    private Integer length;
    private Integer width;
    private Integer height;

    private LocalDateTime create_time;
    private LocalDateTime available_time;
    private LocalDateTime work_time;
    private LocalDateTime suspend_time;
    private LocalDateTime stop_time;

    private int last_complete_in;
    private int last_submit_in;
    private int last_create_in;
    private int last_complete_out;
    private int last_submit_out;
    private int last_create_out;

    private List<Cabinet> cabinets;
    private List<Goods> goodses;
    private List<InOrder> inorders;
    private List<OutOrder> outorders;
    private List<GetOrder> getorders;
}
