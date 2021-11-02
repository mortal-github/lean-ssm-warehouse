package practice.ssm.warehouse.service;

import practice.ssm.warehouse.pojo.Example;

import java.util.List;

public interface ExampleService {

   List<Example> getAllExample();
   Example getExample(int id);
   Example postExample(Example examples);
   Example patchExample(Example example);
   int deleteExample(int id);

}
