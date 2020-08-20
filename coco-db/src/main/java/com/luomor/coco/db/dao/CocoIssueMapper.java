package com.luomor.coco.db.dao;

import com.luomor.coco.db.domain.CocoIssue;
import com.luomor.coco.db.domain.CocoIssueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CocoIssueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    long countByExample(CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int deleteByExample(CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int insert(CocoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int insertSelective(CocoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    CocoIssue selectOneByExample(CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    CocoIssue selectOneByExampleSelective(@Param("example") CocoIssueExample example, @Param("selective") CocoIssue.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    List<CocoIssue> selectByExampleSelective(@Param("example") CocoIssueExample example, @Param("selective") CocoIssue.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    List<CocoIssue> selectByExample(CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    CocoIssue selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") CocoIssue.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    CocoIssue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    CocoIssue selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CocoIssue record, @Param("example") CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CocoIssue record, @Param("example") CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CocoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CocoIssue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") CocoIssueExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coco_issue
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}