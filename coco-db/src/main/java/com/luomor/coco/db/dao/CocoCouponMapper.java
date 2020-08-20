package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoCoupon;
import com.luomor.coco.db.domain.CocoCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoCouponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    long countByExample(CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int deleteByExample(CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int insert(CocoCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int insertSelective(CocoCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    CocoCoupon selectOneByExample(CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    CocoCoupon selectOneByExampleSelective(@Param("example") CocoCouponExample example, @Param("selective") CocoCoupon.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    List<CocoCoupon> selectByExampleSelective(@Param("example") CocoCouponExample example, @Param("selective") CocoCoupon.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    List<CocoCoupon> selectByExample(CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    CocoCoupon selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoCoupon.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    CocoCoupon selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    CocoCoupon selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoCoupon record, @Param("example") CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoCoupon record, @Param("example") CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoCoupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoCouponExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}