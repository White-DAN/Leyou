package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @program: leyou
 * @Description:
 * @Author: jiaxiong
 * @Version: 1.8.0_131
 * @Date: 2019-12-31 10:52
 **/
@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * @Title: queryGroupByCid
     * @Description: 
     * @param cid: 
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecGroup>>
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> groups = this.specificationService.queryGroupsByCid(cid);
        if (CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * @Title: queryParams
     * @Description: 根据条件查询规格参数
     * @param gid:
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.SpecParam>>
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid", required = false) Long gid ,
            @RequestParam(value = "cid", required = false) Long cid ,
            @RequestParam(value = "generic", required = false) Boolean generic ,
            @RequestParam(value = "searching", required = false) Boolean searching
    ){
        List<SpecParam> params = this.specificationService.queryParams(gid,cid,generic,searching);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(params);
        //
    }
}
