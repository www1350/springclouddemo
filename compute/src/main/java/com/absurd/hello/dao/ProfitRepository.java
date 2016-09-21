package com.absurd.hello.dao;

import com.absurd.core.model.PageImplBean;
import com.absurd.hello.eneity.Profit;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public interface  ProfitRepository extends JpaRepository<Profit, Long> {

    @Cacheable(value = "usercache",keyGenerator = "keyGenerator")
    Page<Profit> findAll(Pageable pageable);

    Page<Profit> findByName(String name,Pageable pageable);

    @Cacheable(value = "usercache")
    List<Profit> findByName(String name);


}
