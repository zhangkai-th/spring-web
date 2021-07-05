package com.fan;

import com.fan.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext cxf = new ClassPathXmlApplicationContext(config);
        BuyGoodsService buy = (BuyGoodsService)cxf.getBean("buyService");
        System.out.println(buy);
        buy.buy(1003,1004,10);
    }
}
