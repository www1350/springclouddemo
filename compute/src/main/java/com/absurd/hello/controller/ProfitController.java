package com.absurd.hello.controller;

import com.absurd.hello.dao.ProfitRepository;
import com.absurd.hello.eneity.Profit;
import com.absurd.hello.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
@RestController
@RequestMapping("/profit")
public class ProfitController {
    @Autowired
    ProfitRepository profitRepository;

    @Autowired
    ProfitService profitService;

    @RequestMapping(method= RequestMethod.GET)
    public Page<Profit> getAll(Pageable page){
       return profitRepository.findAll(page);
    }

    @RequestMapping(value="/name/{name}",method= RequestMethod.GET)
    @ResponseBody
    public List<Profit> getByName(@PathVariable("name") String name){
        return profitService.getByName(name);
    }

    @RequestMapping(value="/uniq",method= RequestMethod.GET)
    @ResponseBody
    public Page<Profit> getUnique(){
        return null;
    }


    @RequestMapping(value="/namett/{name}",method= RequestMethod.GET)
    public String getByNameTest(@PathVariable("name") String name){
        return profitService.getByNameTest(name);
    }
}
