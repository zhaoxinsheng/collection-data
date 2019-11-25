package io.nakong.modules.collect.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.nakong.modules.collect.entity.HomeEntity;
import io.nakong.modules.collect.entity.HomePipeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author tom
 */
@Mapper
public interface HomeDao extends BaseMapper<HomeEntity> {

    List<HomeEntity>   getAllList();

    List<HomePipeEntity>  getAllPipeList();

    List<HomePipeEntity> getTotaltemplist();

    List<HomePipeEntity> getTotalpresslist();
}
