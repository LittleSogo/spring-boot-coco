package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoOrder;
import com.luomor.coco.db.domain.CocoOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    long countByExample(CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int deleteByExample(CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int insert(CocoOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int insertSelective(CocoOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    CocoOrder selectOneByExample(CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    CocoOrder selectOneByExampleSelective(@Param("example") CocoOrderExample example, @Param("selective") CocoOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    List<CocoOrder> selectByExampleSelective(@Param("example") CocoOrderExample example, @Param("selective") CocoOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    List<CocoOrder> selectByExample(CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    CocoOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    CocoOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    CocoOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoOrder record, @Param("example") CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoOrder record, @Param("example") CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_order
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}