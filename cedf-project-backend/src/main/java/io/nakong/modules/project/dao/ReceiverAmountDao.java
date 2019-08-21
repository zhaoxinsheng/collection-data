package io.nakong.modules.project.dao;

import java.util.List;

import io.nakong.modules.project.entity.ReceiverAmountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nakong.common.utils.Query;
import io.nakong.modules.project.vo.ReceiverAmountVo;

/**
 *
 * @author robot
 */
@Mapper
public interface ReceiverAmountDao extends BaseMapper<ReceiverAmountEntity> {
	List<ReceiverAmountVo> selectReceiverAmountList(@Param("query") Query query);

	int selectReceiverAmountCount(@Param("query") Query query);
}
