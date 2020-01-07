package io.nakong.modules.collect.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.nakong.modules.collect.dao.HomeDao;
import io.nakong.modules.collect.entity.HomeEntity;
import io.nakong.modules.collect.entity.HomePipeEntity;
import io.nakong.modules.collect.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tom
 */
@Service("homeService")
public class HomeServiceImpl  extends ServiceImpl<HomeDao, HomeEntity> implements HomeService {

    @Autowired
    HomeDao   homeDao;

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        return null;
//    }

    @Override
    public HomeEntity getAllList() {
        List<HomeEntity> homeEntityList =  homeDao.getAllList();
        if(!CollectionUtils.isEmpty(homeEntityList) ) {
            HomeEntity retHome = new HomeEntity();
            for (HomeEntity he : homeEntityList) {
                setHomeVar(he.getEquipId(),he.getCollectValue(),he.getEquipName(),retHome);
            }
            return retHome;
        }
        return null;
    }

    @Override
    public Map<String,ArrayList> getAllPipeList() {
        List<HomePipeEntity> homeDaoAllPipeList =  homeDao.getAllPipeList();
        if(!CollectionUtils.isEmpty(homeDaoAllPipeList) ) {
            HashMap<String,ArrayList> allMap = new HashMap<>();

            ArrayList<Map<String,String>> devList = new ArrayList<>();
            allMap.put("devices",devList);

            ArrayList<Float> ssList = new ArrayList<>();
            ArrayList<Float> ljList = new ArrayList<>();
            Map<String , ArrayList<Float>> dataMap = new HashMap<>();
            dataMap.put("ssll",ssList);
            dataMap.put("ljll",ljList);
            allMap.putAll(dataMap);
            for (HomePipeEntity he : homeDaoAllPipeList) {
                Map<String,String> devMap = new HashMap<>();
                devMap.put("name",he.getEquipName());
                devMap.put("id",String.valueOf(he.getEquipId()));

                if (he.getType() == 8 ) {
                    devList.add(devMap);
                    ssList.add(he.getCollectValue());
                } else {
                    ljList.add(he.getCollectValue());
                }
              //  setHomePipeVar(he.getEquipId(),he.getCollectValue(),he.getEquipName(),retHome);
            }
            return allMap;
        }
        return null;

    }

    @Override
    public Map<String, ArrayList<Float>> getTotaltemplist() {

        List<HomePipeEntity> homeDaoAllPipeList =  homeDao.getTotaltemplist();
        return getResultMap(homeDaoAllPipeList);
    }

    @Override
    public Map getTotalpresslist() {
        List<HomePipeEntity> homeDaoAllPipeList =  homeDao.getTotalpresslist();
        return getResultMap(homeDaoAllPipeList);
    }

    private Map  getResultMap(List<HomePipeEntity> homeDaoAllPipeList) {
        HashMap<String,ArrayList> allMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(homeDaoAllPipeList) ) {
            ArrayList<Integer> hourList = new ArrayList<>();
            ArrayList<Float> dataList = new ArrayList<>();
            for (HomePipeEntity entity : homeDaoAllPipeList) {
                hourList.add(entity.getHour());
                dataList.add(entity.getCollectValue());
            }
            allMap.put("hours",hourList);
            allMap.put("data",dataList);
        }
        return allMap;
    }


    private void setHomePipeVar(int id,float collectValue,String equipName,HomePipeEntity retHome) {
        switch (id) {
            case 105:
                retHome.setDevSS1(collectValue);
                retHome.setDevName1(equipName);
                break;
            case 109:
                retHome.setDevSS2(collectValue);
                retHome.setDevName2(equipName);
                break;
            case 111:
                retHome.setDevSS3(collectValue);
                retHome.setDevName3(equipName);
                break;
            case 127:
                retHome.setDevSS4(collectValue);
                retHome.setDevName4(equipName);
                break;
            case 129:
                retHome.setDevSS5(collectValue);
                retHome.setDevName5(equipName);
                break;
            case 132:
                retHome.setDevSS6(collectValue);
                retHome.setDevName6(equipName);
                break;
            case 134:
                retHome.setDevSS7(collectValue);
                retHome.setDevName7(equipName);
                break;
        }
   }

    private void setHomeVar(int id,float collectValue,String equipName,HomeEntity  retHome) {
         switch (id) {
             // 压力
             case 68:
                 retHome.setDevPress1(collectValue);
                 retHome.setDevName1(equipName);
                 break;
             case 70:
                 retHome.setDevPress2(collectValue);
                 retHome.setDevName2(equipName);
                 break;
             case 73:
                 retHome.setDevPress3(collectValue);
                 retHome.setDevName3(equipName);
                 break;
             case 77:
                 retHome.setDevPress4(collectValue);
                 retHome.setDevName4(equipName);
                 break;
             case 80:
                 retHome.setDevPress5(collectValue);
                 retHome.setDevName5(equipName);
                 break;
             case 83:
                 retHome.setDevPress6(collectValue);
                 retHome.setDevName6(equipName);
                 break;
             case 88:
                 retHome.setDevPress7(collectValue);
                 retHome.setDevName7(equipName);
                 break;
             case 91:
                 retHome.setDevPress8(collectValue);
                 retHome.setDevName8(equipName);
                 break;
             case 94:
                 retHome.setDevPress9(collectValue);
                 retHome.setDevName9(equipName);
                 break;

                 // 电流
             case 69:
                 retHome.setDevPower1(collectValue);
                 break;
             case 71:
                 retHome.setDevPower2(collectValue);
                 break;
             case 74:
                 retHome.setDevPower3(collectValue);
                 break;
             case 78:
                 retHome.setDevPower4(collectValue);
                 break;
             case 81:
                 retHome.setDevPower5(collectValue);
                 break;
             case 84:
                 retHome.setDevPower6(collectValue);
                 break;
             case 89:
                 retHome.setDevPower7(collectValue);
                 break;
             case 92:
                 retHome.setDevPower8(collectValue);
                 break;
             case 95:
                 retHome.setDevPower9(collectValue);
                 break;
             case 103:
                 retHome.setDevLd1(collectValue);
                 break;

                 // 温度
             case 72:
                 retHome.setDevLd2(collectValue);
                 break;
             case 75:
                 retHome.setDevLd3(collectValue);
                 break;
             case 79:
                 retHome.setDevLd4(collectValue);
                 break;
             case 82:
                 retHome.setDevLd5(collectValue);
                 break;
             case 85:
                 retHome.setDevLd6(collectValue);
                 break;
             case 90:
                 retHome.setDevLd7(collectValue);
                 break;
             case 93:
                 retHome.setDevLd8(collectValue);
                 break;
             case 96:
                 retHome.setDevLd9(collectValue);
                 break;

              //空压机状态
             case 137:
                 retHome.setDevStatus1(collectValue);
                 retHome.setDevName1(equipName);
                 break;
             case 138:
                 retHome.setDevStatus2(collectValue);
                 retHome.setDevName2(equipName);
                 break;
             case 139:
                 retHome.setDevStatus3(collectValue);
                 retHome.setDevName3(equipName);
                 break;
             case 140:
                 retHome.setDevStatus4(collectValue);
                 retHome.setDevName4(equipName);
                 break;
             case 141:
                 retHome.setDevStatus5(collectValue);
                 retHome.setDevName5(equipName);
                 break;
             case 142:
                 retHome.setDevStatus6(collectValue);
                 retHome.setDevName6(equipName);
                 break;
             case 143:
                 retHome.setDevStatus7(collectValue);
                 retHome.setDevName7(equipName);
                 break;
             case 144:
                 retHome.setDevStatus8(collectValue);
                 retHome.setDevName8(equipName);
                 break;
             case 146:
                 retHome.setDevStatus9(collectValue);
                 retHome.setDevName9(equipName);
                 break;
         }
    }
}
