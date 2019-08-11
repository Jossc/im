package com.cn.im;

import com.cn.im.common.base.BeanProvider;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@SpringBootTest
public class ImApplicationTests extends AbstractTransactionalJUnit4SpringContextTests {

    @BeforeTransaction
    public void contextLoads() {
        BeanProvider.initialize(applicationContext);
    }

}
