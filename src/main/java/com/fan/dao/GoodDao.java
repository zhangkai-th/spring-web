package com.fan.dao;

import com.fan.domain.Good;

public interface GoodDao {
    //更新库存
    int updateGoods(Good good);

    //查询指定商品的信息
    Good selectGoodById(int id);
}
