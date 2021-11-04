package practice.ssm.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.ssm.warehouse.controller.ControllerExceptionAdvice;
import practice.ssm.warehouse.exception.EmptyResultException;
import practice.ssm.warehouse.exception.NotChangeException;
import practice.ssm.warehouse.mapper.GoodsTypeMapper;
import practice.ssm.warehouse.pojo.Detail;
import practice.ssm.warehouse.pojo.GoodsType;
import practice.ssm.warehouse.service.GoodsTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GoodsTypeServiceImpl implements  GoodsTypeService{

    private final GoodsTypeMapper goodsTypeMapper;

    @Autowired
    public GoodsTypeServiceImpl(GoodsTypeMapper goodsTypeMapper){
        this.goodsTypeMapper = goodsTypeMapper;
    }

    @Override
    public List<GoodsType> getSome(GoodsType goodsType) {
        List<GoodsType> list = goodsTypeMapper.getSome(goodsType);
        if(list.isEmpty()){
            throw new EmptyResultException("can not found any resource by these parameters.");
        }
        return list;
    }

    @Override
    public GoodsType get(int id) {
        GoodsType goodType = goodsTypeMapper.get(id);
        if(null == goodType){
            throw new EmptyResultException("can not found resource by parameter id.");
        }
        return goodType;
    }

    @Override
    public int post(GoodsType goodsType){
        int count = goodsTypeMapper.insert(goodsType);
        assert count == 1;
        if(null != goodsType.getDetails()){
            count = goodsTypeMapper.insertDetail(goodsType.getId(), goodsType.getDetails());
        }
        return goodsType.getId();
    }

    @Override
    public void post(int goods_type, List<Detail> details) {
        int count = goodsTypeMapper.insertDetail(goods_type, details);
        assert count == 1;
    }

    @Override
    public void patch(GoodsType goodsType) {
        int count = goodsTypeMapper.update(goodsType);
        if(count == 0) {
            throw new NotChangeException("have not change. may be can not found resource by parameter id.");
        }
    }

    @Override
    public void delete(int id) {

        int count = goodsTypeMapper.delete(id);
        if(count == 0){
            throw new NotChangeException("have not delete resource");
        }
    }

    @Override
    public void delete(int goods_type, List<Detail> details) {
        goodsTypeMapper.deleteDetail(goods_type, details);
    }

}
