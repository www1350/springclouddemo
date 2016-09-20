package com.absurd.hello.dao;

import com.absurd.Application;
import com.absurd.hello.eneity.Profit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2016/9/20.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.REQUIRED)
//@TransactionConfiguration( defaultRollback = false)
public class ProfitRepositoryTest {
    @Autowired
    ProfitRepository profitRepository;

//    @Test
    public void insertTest(){
        Random random = new Random();
        List<Profit> list = new ArrayList<Profit>();
        for(int i=0;i<10000;i++) {
            long a = (long)(random.nextInt(1000000)+100458);
            for(int j=0;j<5;j++)
                list.add(new Profit("点发"+i, a+j, "jiangxi"));
            for(int j=0;j<5;j++)
                list.add(new Profit("的肤色肥沃"+i, a+j, "jiangxi"));
            if(i % 1000 ==0) {
                profitRepository.save(list);
                //GC
                list = new ArrayList<Profit>();
            }

        }

    }

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new Profit("体验", 2222222L, "guangzhou"));
        List<Profit> user = profitRepository.findByName("体验");
        assertThat(user.get(0).getName()).isEqualTo("体验");
        assertThat(user.get(0).getFundkey()).isEqualTo(2222222L);
    }
}
