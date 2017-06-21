package com.example.demo.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maksim on 6/21/17.
 */

@RestController
public class BeanRestController {

    private final ApplicationContext applicationContext;

    public BeanRestController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @RequestMapping("/beans")
    public Map<String, String[]> beans(@RequestParam(required = false) String q) {
        Map<String, String[]> retMap = new HashMap<>();

        String[] retArray = Arrays.stream(applicationContext.getBeanDefinitionNames())
                .filter(beanName ->
                        (q == null || q.length() == 0) ||
                                beanName.toLowerCase().contains(q.trim().toLowerCase())
                )
                .toArray(String[]::new);

        retMap.put("beans", retArray);
        return retMap;
    }

}
