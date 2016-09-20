package com.absurd.hello.service;

import com.absurd.hello.dao.ProfitRepository;
import com.absurd.hello.eneity.Profit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
@Service
public class ProfitService {
    @Autowired
    ProfitRepository profitRepository;
    public List<Profit> getByName(String name){
        return profitRepository.findByName(name);
    }

    public String getByNameTest(String name){
        return profitRepository.findByName(name).get(0).getCity();
    }

}
