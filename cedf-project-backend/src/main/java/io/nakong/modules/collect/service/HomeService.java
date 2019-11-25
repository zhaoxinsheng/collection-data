package io.nakong.modules.collect.service;

import com.baomidou.mybatisplus.service.IService;
import io.nakong.modules.collect.entity.HomeEntity;
import io.nakong.modules.collect.entity.HomePipeEntity;
import io.nakong.modules.collect.entity.PipeLjEntity;

import java.util.ArrayList;
import java.util.Map;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public interface HomeService extends IService<HomeEntity> {

    HomeEntity  getAllList();

    Map<String, ArrayList> getAllPipeList();

    Map<String,ArrayList<Float>> getTotaltemplist();

    Map<String,ArrayList<Float>> getTotalpresslist();
}
