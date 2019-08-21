package io.nakong.modules.project.dao;

import io.nakong.modules.project.entity.ReceiverEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 *
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@Mapper
public interface ReceiverDao extends BaseMapper<ReceiverEntity> {
	String selectRcvIdByRcvComAndPrjNo(@Param("projectNo") String projectNo,@Param("receiveCompany") String receiveCompany);

    void updateReceiveById(ReceiverEntity receiver);
}
