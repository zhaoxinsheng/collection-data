package io.nakong.modules.project.dao;

import java.util.List;

import io.nakong.modules.project.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.nakong.common.utils.Query;

/**
 *
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@Mapper
public interface ProjectDao extends BaseMapper<ProjectEntity> {
	/**
     * 查询列表
     * */
    List<ProjectEntity> queryListByCriteria(@Param("query") Query q);

    /**
     * 统计条数
     * */
	Long countByCriteria(@Param("query") Query q);

	/**
     * 批量删除
     * */
	int deleteDonatorByProjects(@Param("list") List<Integer> idList);

	List<ProjectEntity> selectList(EntityWrapper<ProjectEntity> projectEntity);

	/**
	 * 根据id 查询project
	 * @param id
	 * @return
	 */
	ProjectEntity  queryProjectById(@Param("id") int id );

	/**
	 * 根据 project No 查询project 不等于当前项目id
	 * @param id
	 * @return
	 */
	ProjectEntity  queryProjectByPojectNo(@Param("id") int id,@Param("projectNo") String projectNo);

	Integer queryPrjIdByPrjNo(@Param("projectNo") String projectNo);

}
