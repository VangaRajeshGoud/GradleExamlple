package com.jcombat.controller;


import com.jcombat.entity.EmployeeDetails;
import com.jcombat.entity.TodoModel;


import com.jcombat.services.EmplyeeServicesImpl;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmplyeeServicesImpl emplyeeServices;

    @GetMapping("/getAllEmployees")
    public  List<EmployeeDetails> getAllEmployees(){
        RestTemplate restTemplate=new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(converter);

        String theUrl = "http://dummy.restapiexample.com/api/v1/employees";
        ResponseEntity<List<EmployeeDetails>> response = restTemplate.exchange(theUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<EmployeeDetails>>() {
        });
        List<EmployeeDetails> todoList = response.getBody();
      System.out.println(" response " +response);
        System.out.println(" todoList " +todoList);
        emplyeeServices.SaveAllEmployees(todoList);
        return todoList;
    }


    @GetMapping(value = "/getEmployeeByID/{eid}")
    public EmployeeDetails getByEmployeeId(@PathVariable("eid") String eid){
        return emplyeeServices.getEmployeeByID(eid);
    }
    @DeleteMapping(value = "/DeleteEmployeeByID/{eid}")
    public String DeleteEmployeeByID(@PathVariable("eid") String eid){
        emplyeeServices.DeleteEmployeeByID(eid);
        return "SuceesFully Deleted";
    }


    @GetMapping(value = "/getByEmployeeId/{eid}")
    public EmployeeDetails getByEmployeeIdConsume(@PathVariable("eid") String eid){
        RestTemplate restTemplate=new RestTemplate();
        MappingJackson2HttpMessageConverter con=new MappingJackson2HttpMessageConverter();
con.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(con);
        String uri="http://dummy.restapiexample.com/api/v1/employee/"+eid;
        System.out.println("uri test : " +uri);
        EmployeeDetails emp=restTemplate.getForObject(uri,EmployeeDetails.class);
        emplyeeServices.save(emp);
        HttpHeaders   headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("data "+emp);
        emp= emplyeeServices.save(emp);
        return emp;
    }

@GetMapping("/getByEmployeeIdInjSON")
    public EmployeeDetails prepareObj(){
        EmployeeDetails employeeDetails=new EmployeeDetails();
        String url="http://dummy.restapiexample.com/api/v1/employee/140476";
        RestTemplate restTemplate = new RestTemplate();
    MappingJackson2HttpMessageConverter con=new MappingJackson2HttpMessageConverter();
    con.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
    restTemplate.getMessageConverters().add(con);
        String resp = restTemplate.getForObject(url, String.class);
    System.out.println("Items found: " + resp);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> map = springParser.parseMap(resp);

        String mapArray[] = new String[map.size()];
        System.out.println("Items found: " + mapArray.length);

        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
            employeeDetails.setId((String) entry.getValue());
            employeeDetails.setEmployee_name((String) entry.getValue());
            employeeDetails.setEmployee_salary((String) entry.getValue());
            i++;
        }
        System.out.println("employeeDetails "+employeeDetails);
        return employeeDetails;
    }




    @GetMapping(value = "/getAllNames",produces = "application/json")
    public  List<EmployeeDetails>  getAllNames(){

EmployeeDetails emp=new EmployeeDetails();
emp.setEmployee_name("Rajesh1");
        EmployeeDetails emp1=new EmployeeDetails();
        EmployeeDetails emp2=new EmployeeDetails();
        EmployeeDetails emp3=new EmployeeDetails();
        EmployeeDetails emp4=new EmployeeDetails();

        emp1.setEmployee_name("Rajesh2");
        emp2.setEmployee_name("Rajesh3");
        emp3.setEmployee_name("Rajesh4");
        emp4.setEmployee_name("Rajesh5");
       // Map<String ,EmployeeDetails > list=new HashMap<String,EmployeeDetails>();
       List<EmployeeDetails> list=new ArrayList<EmployeeDetails>();

        list.add(emp);
        list.add(emp1);
        list.add(emp2);
        list.add(emp3);
        list.add(emp4);

//
//        list.put("a",emp);
//        list.put("b",emp1);
//        list.put("c",emp2);
//        list.put("d",emp3);
//        list.put("d",emp4);
        return  list;
    }

}
