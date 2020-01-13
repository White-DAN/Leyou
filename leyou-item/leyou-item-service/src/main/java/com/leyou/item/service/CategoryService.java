package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiaxiong
 * @Version 1.8.0_131
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *  @Description: 根据父节点查询子节点
     *  @param: pid
     *  @return: List<Category>
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    /**
     * @Title: queryNamesByIds
     * @Description: 查询（页面需要的）商品的分类名称
     * @param ids:
     * @Return: java.util.List<java.lang.String>
     */
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> list = this.categoryMapper.selectByIdList(ids);
        List<String> names = new ArrayList<>();
        for (Category category : list){
            names.add(category.getName());
        }
        return names;
    }
}
