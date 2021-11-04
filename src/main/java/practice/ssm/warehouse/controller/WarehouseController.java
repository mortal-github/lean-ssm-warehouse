package practice.ssm.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import practice.ssm.warehouse.pojo.Warehouse;
import practice.ssm.warehouse.service.WarehouseService;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping(produces = {"application/json"})
    public List<Warehouse> get(
            @RequestParam(value = "offset", defaultValue = "0") int offset
            , @RequestParam(value = "limit", defaultValue = "10") int limit
            , @RequestParam(value = "status", required = false) String status
            , @RequestParam(value = "address", required = false) String address
            ){
        return warehouseService.getSome(offset, limit, address, status);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public Warehouse get(@PathVariable("id") int id){
        return warehouseService.get(id);
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity post(@RequestBody Warehouse warehouse, UriComponentsBuilder uriComponentsBuilder){
        int id = warehouseService.post(warehouse);

        URI uri = uriComponentsBuilder.path("/warehouses/").pathSegment(String.valueOf(id)).build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        ResponseEntity<Integer> responseEntity = new ResponseEntity(headers, HttpStatus.CREATED);
        return responseEntity;
    }

    @PatchMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public void patch(@PathVariable("id") int id, @RequestBody Warehouse warehouse){
        warehouseService.patch(id, warehouse);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<Warehouse> delete(@PathVariable("id") int id){
        warehouseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
