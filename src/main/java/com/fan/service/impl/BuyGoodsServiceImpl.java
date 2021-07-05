package com.fan.service.impl;

import com.fan.dao.GoodDao;
import com.fan.dao.SaleDao;
import com.fan.domain.Good;
import com.fan.domain.Sale;
import com.fan.except.NotEnoughException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BuyGoodsServiceImpl implements com.fan.service.BuyGoodsService {
    private SaleDao saleDao;
    private GoodDao goodDao;

    public void setSaleDao(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }


//    @Transactional(
//            propagation = Propagation.REQUIRED,
//            isolation = Isolation.DEFAULT,
//            readOnly = false,
//            rollbackFor = {
//                    NullPointerException.class,NotEnoughException.class
//            }
//    )
    @Transactional
    public void buy(int key,int goodId, int nums) {
        Sale sale = new Sale();
        sale.setId(key);
        sale.setGid(goodId);
        sale.setNums(nums);
        saleDao.insertSale(sale);

        //更新库存前进行验证
        Good goods = goodDao.selectGoodById(goodId);
        if(goods == null){
            throw  new NullPointerException(goodId+"商品不存在");
        }else if (goods.getAmount()<nums){
            throw new NotEnoughException(goodId+"商品库存不足");
        }

        //更新商品信息
        Good buyGoods = new Good();
        buyGoods.setId(goodId);
        buyGoods.setAmount(nums);
        goodDao.updateGoods(buyGoods);
        System.out.println("buy方法执行完成");
    }

    public void addSale(){};
    public void addGood(){};


    //查询方法
    public void querySale(){};
    public void selectGood(){};

}
