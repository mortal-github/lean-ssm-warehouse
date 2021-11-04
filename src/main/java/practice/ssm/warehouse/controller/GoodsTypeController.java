package practice.ssm.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import practice.ssm.warehouse.pojo.Detail;
import practice.ssm.warehouse.pojo.Goods;
import practice.ssm.warehouse.pojo.GoodsType;
import practice.ssm.warehouse.service.GoodsTypeService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goodstypes")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping( produces = {"application/json"})
    public List<GoodsType> getAllGoodsType(@RequestParam(value = "name" ,required = false) String name,
                                     @RequestParam(value = "detail_name", required = false) String[] detail_name,
                                     @RequestParam(value = "detail_value", required = false) String[] detail_value){
        GoodsType goodsType = getGoodsType(name, detail_name, detail_value);
        List<GoodsType> list = goodsTypeService.getSome(goodsType);
        return list;
    }
    private static GoodsType getGoodsType(String name, String[] detail_name, String[] detail_value){
        if(null != detail_name){
            if(null == detail_value || detail_value.length != detail_name.length){
                throw new IllegalArgumentException("the count of detail_value must equals the count of detail_name");
            }
        }
        GoodsType goodsType = new GoodsType();
        goodsType.setName(name);
        if(null != detail_name && 0 != detail_name.length){
            List<Detail> details = new ArrayList<>();
            for(int i=0; i<detail_name.length; i++){
                Detail detail = new Detail(detail_name[i], detail_value[i]);
                details.add(detail);
            }
            goodsType.setDetails(details);
        }
        return goodsType;
    }

    @GetMapping( value = "/{id}", produces = {"application/json"})
    public GoodsType get(@PathVariable("id") int id){
        return goodsTypeService.get(id);
    }

    @PostMapping( consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<GoodsType> post(@RequestBody GoodsType goodstype, UriComponentsBuilder uriComponentsBuilder){
        int id = goodsTypeService.post(goodstype);
        URI uri = uriComponentsBuilder.path("/goodstypes/").path(String.valueOf(id)).build().toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        ResponseEntity<GoodsType> responseEntity = new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping( value = "/{id}/details", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Detail> post(@PathVariable("id") int id, @RequestBody List<Detail> detail){
        goodsTypeService.post(id, detail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping( value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public void patch(@PathVariable("id") int id, @RequestBody GoodsType goodsType){
        goodsType.setId(id);
        goodsTypeService.patch(goodsType);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<GoodsType> delete(@PathVariable("id") int id){
        goodsTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}/details", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Detail> delete(@PathVariable("id") int goods_type, @RequestBody List<Detail> details){
        goodsTypeService.delete(goods_type, details);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
