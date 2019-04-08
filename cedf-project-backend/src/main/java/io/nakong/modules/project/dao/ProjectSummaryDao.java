package io.nakong.modules.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nakong.common.utils.Query;
import io.nakong.modules.project.vo.ProjectSummaryVo;

/**
 * 
 * 
 * @author tom
 * @email maozhengwen@codyy.com
 * @date 2018-05-15 09:23:14
 */
@Mapper
public interface ProjectSummaryDao extends BaseMapper<ProjectSummaryVo> {
	/**
     * 查询列表
     * */
    List<ProjectSummaryVo> queryListByCriteria(@Param("query") Query q);

    /**
     * 统计条数
     * */
	Long countByCriteria(@Param("query") Query q);

	/**
     * 批量删除
     * */
	int deleteDonatorByProjects(@Param("list") List<Integer> idList);
}
