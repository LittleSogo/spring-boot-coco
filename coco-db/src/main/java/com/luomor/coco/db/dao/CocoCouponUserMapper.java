package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoCouponUser;
import com.luomor.coco.db.domain.CocoCouponUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoCouponUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    long countByExample(CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int deleteByExample(CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int insert(CocoCouponUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int insertSelective(CocoCouponUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    CocoCouponUser selectOneByExample(CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    CocoCouponUser selectOneByExampleSelective(@Param("example") CocoCouponUserExample example, @Param("selective") CocoCouponUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    List<CocoCouponUser> selectByExampleSelective(@Param("example") CocoCouponUserExample example, @Param("selective") CocoCouponUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    List<CocoCouponUser> selectByExample(CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    CocoCouponUser selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoCouponUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    CocoCouponUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    CocoCouponUser selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoCouponUser record, @Param("example") CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoCouponUser record, @Param("example") CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoCouponUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoCouponUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoCouponUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_coupon_user
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}