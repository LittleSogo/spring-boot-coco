package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoAddress;
import com.luomor.coco.db.domain.CocoAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    long countByExample(CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int deleteByExample(CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int insert(CocoAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int insertSelective(CocoAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    CocoAddress selectOneByExample(CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    CocoAddress selectOneByExampleSelective(@Param("example") CocoAddressExample example, @Param("selective") CocoAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    List<CocoAddress> selectByExampleSelective(@Param("example") CocoAddressExample example, @Param("selective") CocoAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    List<CocoAddress> selectByExample(CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    CocoAddress selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    CocoAddress selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    CocoAddress selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoAddress record, @Param("example") CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoAddress record, @Param("example") CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_address
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}