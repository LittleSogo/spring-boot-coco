package com.luomor.coco.db.service;

import com.luomor.coco.db.dao.CocoSystemMapper;
import com.luomor.coco.db.domain.CocoSystem;
import com.luomor.coco.db.domain.CocoSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CocoSystemConfigService {
    @Resource
    private CocoSystemMapper systemMapper;

    public Map<String, String> queryAll() {
        CocoSystemExample example = new CocoSystemExample();
        example.or().andDeletedEqualTo(false);

        List<CocoSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> systemConfigs = new HashMap<>();
        for (CocoSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    public Map<String, String> listMail() {
        CocoSystemExample example = new CocoSystemExample();
        example.or().andKeyNameLike("coco_mall_%").andDeletedEqualTo(false);
        List<CocoSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(CocoSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        CocoSystemExample example = new CocoSystemExample();
        example.or().andKeyNameLike("coco_wx_%").andDeletedEqualTo(false);
        List<CocoSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(CocoSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listOrder() {
        CocoSystemExample example = new CocoSystemExample();
        example.or().andKeyNameLike("coco_order_%").andDeletedEqualTo(false);
        List<CocoSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(CocoSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listExpress() {
        CocoSystemExample example = new CocoSystemExample();
        example.or().andKeyNameLike("coco_express_%").andDeletedEqualTo(false);
        List<CocoSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(CocoSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            CocoSystemExample example = new CocoSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            CocoSystem system = new CocoSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    public void addConfig(String key, String value) {
        CocoSystem system = new CocoSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }
}
