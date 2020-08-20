package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoAd;
import com.luomor.coco.db.domain.CocoAdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoAdMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    long countByExample(CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int deleteByExample(CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int insert(CocoAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int insertSelective(CocoAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    CocoAd selectOneByExample(CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    CocoAd selectOneByExampleSelective(@Param("example") CocoAdExample example, @Param("selective") CocoAd.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    List<CocoAd> selectByExampleSelective(@Param("example") CocoAdExample example, @Param("selective") CocoAd.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    List<CocoAd> selectByExample(CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    CocoAd selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoAd.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    CocoAd selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    CocoAd selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoAd record, @Param("example") CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoAd record, @Param("example") CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoAd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoAdExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_ad
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}