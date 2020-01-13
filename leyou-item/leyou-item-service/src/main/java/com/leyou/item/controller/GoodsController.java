package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: leyou
 * @Description: 商品
 * @Author: jiaxiong
 * @Version: 1.8.0_131
 * @Date: 2019-12-31 17:15
 **/
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * @Title: querySpuByPage
     * @Description: 查询商品分类信息
     * @param key: 
	 * @param saleable: 
	 * @param page: 
	 * @param rows: 
     * @Return: org.springframework.http.ResponseEntity<com.leyou.common.pojo.PageResult<com.leyou.item.bo.SpuBo>>
     */
    @GetMapping("spu/page") //http://api.leyou.com/api/item/spu/page?key=&saleable=true&page=1&rows=5
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows
    ){
        PageResult<SpuBo> pageResult = this.goodsService.querySpuByPage(key,saleable,page,rows);
        if (CollectionUtils.isEmpty(pageResult.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    /**
     * @Title: saveGoods
     * @Description: 保存新增商品
     * @param spuBo:
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo){
        this.goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * @Title: querySpuDetailBySpuId
     * @Description: 跟进spuId查询spuDetail
     * @param spuId: 
     * @Return: org.springframework.http.ResponseEntity<com.leyou.item.pojo.SpuDetail>
     */
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId")Long spuId){
        SpuDetail spuDetail = this.goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    
    /**
     * @Title: querySkuBySpuId
     * @Description: 根据skuId查询sku集合
     * @param spuId: 
     * @Return: org.springframework.http.ResponseEntity<java.util.List<com.leyou.item.pojo.Sku>>
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long spuId){
        List<Sku> skus = this.goodsService.querySkuBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }

    /**
     * @Title: updateGoods
     * @Description: 更新商品信息
     * @param spuBo:
     * @Return: org.springframework.http.ResponseEntity<java.lang.Void>
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        this.goodsService.updateGoods(spuBo);
        return ResponseEntity.noContent().build();
    }
}
