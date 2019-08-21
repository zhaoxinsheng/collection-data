package io.nakong.modules.project.dao;

import java.util.List;

import io.nakong.modules.project.entity.DonateAmountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nakong.common.utils.Query;

/**
 * 
 * 
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:15
 */
@Mapper
public interface DonateAmountDao extends BaseMapper<DonateAmountEntity> {
    /**
     * 查询列表
     * */
    List<DonateAmountEntity> queryListByCriteria(@Param("query") Query q);

    /**
     * 统计条数
     * */
    Long countByCriteria(@Param("query") Query q);

    void updateByProjectId(@Param("projectId") Integer projectId,@Param("donator") String donator);

    /**
     * 项目协议号更新后 需要更新 到账项目编号
     * @param projectNo
     * @param id
     */
	void updateProjectNoByProjectId(@Param("projectNo")String projectNo, @Param("projectId")Integer id);
}
