package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: leyou
 * @Description: 品牌
 * @Author: jiaxiong
 * @Version: 1.8.0_131
 * @Date: 2019-12-11 10:59
 **/
@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    /**
     * @Title: queryBrandsByPage
     * @Description: 根据查询条件并分页查询品牌信息
     * @param key:
	 * @param page:
	 * @param rows:
	 * @param sortBy:
	 * @param desc:
     * @Return: org.springframework.http.ResponseEntity<com.leyou.common.pojo.PageResult<com.leyou.item.pojo.Brand>>
     */
    @GetMapping("page")//key=&page=1&rows=5&sortBy=id&desc=false
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
            ){
        PageResult<Brand> result = this.brandService.queryBrandByPage(key,page,rows,sortBy,desc);
        if (CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * @Title: saveBrand
     * @Description: 新增品牌
     * @param brand: 品牌名字
     * @param cids: 品牌所属分类
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids){

        this.brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Title: queryBrandByCid
     * @Description: 根据分类id查询品牌列表
     * @param cid: 
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.Brand>>
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid){
        List<Brand> brands = this.brandService.queryBrandByCid(cid);
        if (CollectionUtils.isEmpty(brands)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }
}
