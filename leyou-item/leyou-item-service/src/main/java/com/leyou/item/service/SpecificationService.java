package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @program: leyou
 * @Description:
 * @Author: jiaxiong
 * @Version: 1.8.0_131
 * @Date: 2019-12-31 10:53
 **/
@Service
public class SpecificationService {
    
    @Autowired
    private SpecGroupMapper groupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;
    
    /**
     * @Title: queryGroupsByCid
     * @Description: 根据分类id查询分组
     * @param cid: 
     * @Return: java.util.List<com.leyou.item.pojo.SpecGroup>
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.groupMapper.select(specGroup);
    }

    /**
     * @Title: queryParams
     * @Description: 根据条件查询规格参数
     * @param cid
     * @param gid :
     * @param generic
     * @param searching
     * @Return: java.util.List<com.leyou.item.pojo.SpecParam>
     */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);
    }
}
