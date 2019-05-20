/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Lamiaa Abdrabou
 */
@Controller
public class TestController {
    @RequestMapping(value="/test")
    public String test(@RequestParam String testNum){
        System.out.println("The Test Number Received is: "+testNum);
        return "add_event";
    }
}
