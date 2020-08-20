package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoRole;
import com.luomor.coco.db.domain.CocoRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    long countByExample(CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int deleteByExample(CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int insert(CocoRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int insertSelective(CocoRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    CocoRole selectOneByExample(CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    CocoRole selectOneByExampleSelective(@Param("example") CocoRoleExample example, @Param("selective") CocoRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    List<CocoRole> selectByExampleSelective(@Param("example") CocoRoleExample example, @Param("selective") CocoRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    List<CocoRole> selectByExample(CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    CocoRole selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    CocoRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    CocoRole selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoRole record, @Param("example") CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoRole record, @Param("example") CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_role
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}