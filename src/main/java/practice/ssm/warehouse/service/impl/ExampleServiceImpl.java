package practice.ssm.warehouse.service.impl;

import practice.ssm.warehouse.service.ExampleService;
import practice.ssm.warehouse.mapper.ExampleMapper;
import practice.ssm.warehouse.pojo.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExampleServiceImpl implements ExampleService {
    private final ExampleMapper exampleMapper;

    @Autowired
    public ExampleServiceImpl(ExampleMapper exampleMapper){
        this.exampleMapper = exampleMapper;
    }

    @Override
    public List<Example> getAllExample() {
        return this.exampleMapper.getAllExample();
    }

    @Override
    public Example getExample(int id) {
        return this.exampleMapper.getExample(id);
    }

    @Override
    public Example postExample(Example example) {
        int count = this.exampleMapper.postExample(example);
        return this.exampleMapper.getExample(example.getId());
    }

    @Override
    public Example patchExample(Example example) {
        int count = this.exampleMapper.patchExample(example);
        example = this.exampleMapper.getExample(example.getId());
        return example;
    }

    @Override
    public int deleteExample(int id) {
        return this.exampleMapper.deleteExample(id);
    }
}
