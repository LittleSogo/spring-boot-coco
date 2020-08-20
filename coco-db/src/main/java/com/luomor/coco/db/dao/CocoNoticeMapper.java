package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoNotice;
import com.luomor.coco.db.domain.CocoNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoNoticeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    long countByExample(CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int deleteByExample(CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int insert(CocoNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int insertSelective(CocoNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    CocoNotice selectOneByExample(CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    CocoNotice selectOneByExampleSelective(@Param("example") CocoNoticeExample example, @Param("selective") CocoNotice.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    List<CocoNotice> selectByExampleSelective(@Param("example") CocoNoticeExample example, @Param("selective") CocoNotice.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    List<CocoNotice> selectByExample(CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    CocoNotice selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoNotice.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    CocoNotice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    CocoNotice selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoNotice record, @Param("example") CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoNotice record, @Param("example") CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoNotice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoNoticeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_notice
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}