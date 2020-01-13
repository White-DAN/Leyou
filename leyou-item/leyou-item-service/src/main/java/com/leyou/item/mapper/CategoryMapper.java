package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: jiaxiong
 * @Version 1.8.0_131
 **/
public interface CategoryMapper extends Mapper<Category> , SelectByIdListMapper<Category,Long> {

}
