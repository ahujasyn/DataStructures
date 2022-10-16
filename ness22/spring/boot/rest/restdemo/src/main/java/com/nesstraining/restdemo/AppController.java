package com.nesstraining.restdemo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RequestMapping("/employees")
@RestController
public class AppController {

    private Map<Integer, Employee> store = new HashMap<>();

    private int generatedId;

    private int generateId() {
        return ++generatedId;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee requestData,@RequestHeader("Authorization")String authValue ) {
        int id = generateId();
        store.put(id, requestData);
        //String authValue=request.getHeader("Authorization");
        System.out.println("sent auth value in request="+authValue);
        //  String response= "employee added with " + id;
        // return  response;
        Employee response = requestData;
        return response;
    }



    /*
    @PostMapping(value = "/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee requestData) {
        int id = generateId();
        store.put(id, requestData);
        String response= "employee added with " + id;
        HttpHeaders headers=new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"text/plain);
        ResponseEntity<String>responseEntity=new ResponseEntity<>(response,headers, HttpStatus.CREATED);
        return responseEntity;
    }
*/

    @GetMapping("/byid/{id}")
    public Employee findById(@PathVariable int id) {
        Employee response = store.get(id);
        return response;
    }



    @GetMapping("/all")
    public List<Employee> fetchAll() {
        Collection<Employee> values = store.values();
        List<Employee> response = new ArrayList<>(values);
        return response;
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee requestData) {
        store.put(id, requestData);
        Employee response = requestData;
        return response;
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable int id) {
        if (!store.containsKey(id)) {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
        store.remove(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
