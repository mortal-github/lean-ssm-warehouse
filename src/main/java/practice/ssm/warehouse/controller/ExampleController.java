package practice.ssm.warehouse.controller;

import practice.ssm.warehouse.service.ExampleService;
import practice.ssm.warehouse.pojo.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping(value = "/example")
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @GetMapping( produces = {"application/json"})
    public @ResponseBody List<Example> get(){
        return exampleService.getAllExample();
    }

    @GetMapping( value = "/{id}", produces = {"application/json"})
    public  @ResponseBody Example get(@PathVariable("id") int id){
        return exampleService.getExample(id);
    }

    @PostMapping(
            consumes = {"application/json"})
    public ResponseEntity<Example> post(@RequestBody Example example , UriComponentsBuilder uriComponentsBuilder){
        example = exampleService.postExample(example);

        URI location = uriComponentsBuilder.path("/example/").path(String.valueOf(example.getId())).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(example, headers, HttpStatus.CREATED);
    }

    @PatchMapping( value = "/{id}", consumes = {"application/json"})
    public @ResponseBody Example patch(@PathVariable("id") int id, @RequestBody Example example , UriComponentsBuilder uriComponentsBuilder){
        example.setId(id);
        example = exampleService.patchExample(example);
        URI uri = uriComponentsBuilder.path("/example/").path(String.valueOf(id)).build().toUri();
        if(null == example){
            throw new IllegalArgumentException("resource : \"" + uri.toString() + "\" is not exists!");
        }
        return example;
    }

    @DeleteMapping( value = "/{id}" , produces = {"application/json"})
    public ResponseEntity delete(@PathVariable("id") int id){
        int count = exampleService.deleteExample(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
