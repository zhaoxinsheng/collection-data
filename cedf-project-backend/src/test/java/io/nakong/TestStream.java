package io.nakong;

import io.nakong.modules.collect.entity.CompareDataEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 实现该接口的类，将获取应用配置信息实例
 *
 * @author allen
 * @version : AppConfigurationAware.java, v 0.1 2016年2月28日 上午4:48:23 allen Exp $
 */
public class TestStream {
    public static void main(String[] args) {
        CompareDataEntity entity = new CompareDataEntity();
        entity.setDate(new Date());
        entity.setName("test1");
        entity.setData(100);

        CompareDataEntity entity2 = new CompareDataEntity();
        entity2.setDate(new Date());
        entity2.setName("test1");
        entity2.setData(200);


        CompareDataEntity entity3 = new CompareDataEntity();
        entity3.setDate(new Date());
        entity3.setName("test1");
        entity3.setData(300);

        List<CompareDataEntity> list = new ArrayList<>();
        list.add(entity);
        list.add(entity2);
        list.add(entity3);

        Map<Date,List<CompareDataEntity>> map = list.parallelStream().collect(Collectors.groupingBy(CompareDataEntity::getDate));
        map.forEach((x,y) -> {
            System.out.println(map.get(x));
        });
        //            Map<String, Map<String, List<CompareDataEntity>>> result = entitys.parallelStream()
//                    .collect(Collectors.groupingBy(CompareDataEntity::getDate()));
//
//            entitys.parallelStream().collect(Collectors.groupingBy(CompareDataEntity::getDate()));
//
//            Map<Date, List<DeviceConfig>> listMap = deviceConfigs.stream().collect(Collectors.groupingBy(DeviceConfig::getParkingLot));

    }
}
