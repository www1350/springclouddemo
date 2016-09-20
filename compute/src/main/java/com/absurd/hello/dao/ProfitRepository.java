package com.absurd.hello.dao;

import com.absurd.hello.eneity.Profit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20.
 */
public interface  ProfitRepository extends JpaRepository<Profit, Long> {
    Page<Profit> findAll(Pageable pageable);

    Page<Profit> findByName(String name,Pageable pageable);

    List<Profit> findByName(String name);


}
