package com.luomor.coco.wx.service;

import com.luomor.coco.db.domain.CocoRegion;
import com.luomor.coco.db.service.CocoRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhy
 * @date 2019-01-17 23:07
 **/
@Component
public class GetRegionService {

	@Autowired
	private CocoRegionService regionService;

	private static List<CocoRegion> cocoRegions;

	protected List<CocoRegion> getCocoRegions() {
		if(cocoRegions==null){
			createRegion();
		}
		return cocoRegions;
	}

	private synchronized void createRegion(){
		if (cocoRegions == null) {
			cocoRegions = regionService.getAll();
		}
	}
}
