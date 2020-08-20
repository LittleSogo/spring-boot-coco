package com.luomor.coco.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.luomor.coco.admin.vo.RegionVo;
import com.luomor.coco.core.util.ResponseUtil;
import com.luomor.coco.db.domain.CocoRegion;
import com.luomor.coco.db.service.CocoRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/region")
@Validated
public class AdminRegionController {
    private final Log logger = LogFactory.getLog(AdminRegionController.class);

    @Autowired
    private CocoRegionService regionService;

    @GetMapping("/clist")
    public Object clist(@NotNull Integer id) {
        List<CocoRegion> regionList = regionService.queryByPid(id);
        return ResponseUtil.okList(regionList);
    }

    @GetMapping("/list")
    public Object list() {
        List<RegionVo> regionVoList = new ArrayList<>();

        List<CocoRegion> cocoRegions = regionService.getAll();
        Map<Byte, List<CocoRegion>> collect = cocoRegions.stream().collect(Collectors.groupingBy(CocoRegion::getType));
        byte provinceType = 1;
        List<CocoRegion> provinceList = collect.get(provinceType);
        byte cityType = 2;
        List<CocoRegion> city = collect.get(cityType);
        Map<Integer, List<CocoRegion>> cityListMap = city.stream().collect(Collectors.groupingBy(CocoRegion::getPid));
        byte areaType = 3;
        List<CocoRegion> areas = collect.get(areaType);
        Map<Integer, List<CocoRegion>> areaListMap = areas.stream().collect(Collectors.groupingBy(CocoRegion::getPid));

        for (CocoRegion province : provinceList) {
            RegionVo provinceVO = new RegionVo();
            provinceVO.setId(province.getId());
            provinceVO.setName(province.getName());
            provinceVO.setCode(province.getCode());
            provinceVO.setType(province.getType());

            List<CocoRegion> cityList = cityListMap.get(province.getId());
            List<RegionVo> cityVOList = new ArrayList<>();
            for (CocoRegion cityVo : cityList) {
                RegionVo cityVO = new RegionVo();
                cityVO.setId(cityVo.getId());
                cityVO.setName(cityVo.getName());
                cityVO.setCode(cityVo.getCode());
                cityVO.setType(cityVo.getType());

                List<CocoRegion> areaList = areaListMap.get(cityVo.getId());
                List<RegionVo> areaVOList = new ArrayList<>();
                for (CocoRegion area : areaList) {
                    RegionVo areaVO = new RegionVo();
                    areaVO.setId(area.getId());
                    areaVO.setName(area.getName());
                    areaVO.setCode(area.getCode());
                    areaVO.setType(area.getType());
                    areaVOList.add(areaVO);
                }

                cityVO.setChildren(areaVOList);
                cityVOList.add(cityVO);
            }
            provinceVO.setChildren(cityVOList);
            regionVoList.add(provinceVO);
        }

        return ResponseUtil.okList(regionVoList);
    }
}
