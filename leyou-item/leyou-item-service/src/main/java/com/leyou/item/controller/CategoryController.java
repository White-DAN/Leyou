package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: jiaxiong
 * @Version 1.8.0_131
 **/
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @Title: queryCategoriesByPid
     * @Description: 根据父节点id查询子节点
     * @param pid:
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.Category>>
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid){

       if (pid == null || pid < 0){
           // 400:参数不合法
           //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();写法1
           //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);         写法2
           return ResponseEntity.badRequest().build();                  //写法3
       }
       List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
       if (CollectionUtils.isEmpty(categories)){
           // 404:资源服务器未找到
           //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
           return ResponseEntity.notFound().build();
       }
       // 200: 查询成功
       return ResponseEntity.ok(categories);
       // 500: (发生异常)服务器内部错误
       //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
