package io.nakong.modules.project.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.common.utils.R;
import io.nakong.modules.project.entity.ProjectEntity;
import io.nakong.modules.project.service.ProjectStatisticsService;
import io.nakong.modules.project.vo.ProjectCategory;
import io.nakong.modules.project.vo.ProjectStatisticsCategoryVo;
import io.nakong.modules.sys.controller.AbstractController;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@RestController
@RequestMapping("project/statistics")
public class ProjectStatisticsController  extends AbstractController {

    @Autowired
    private ProjectStatisticsService projectStatisticsService;
    
    /**
     * 项目分类统计
     */
    @RequestMapping("/category")
    @RequiresPermissions("project:statistics:category")
    public R projectCategory(@RequestParam Map<String, Object> params){
    	
    	String startDate = (String)params.get("startDate");
        String endDate = (String)params.get("endDate");

    	if (logger.isDebugEnabled()) {
    		logger.debug("请求项目分类统计数据 参数");
    	}    	
    	ProjectStatisticsCategoryVo projectStatisticsCategoryVo = projectStatisticsService.getProjectStatisticsAmount(startDate,endDate,null);
    	List<ProjectCategory> projectCategoryList = projectStatisticsService.getProjectStatisticsByCategory(startDate,endDate);
    	
    	if (projectStatisticsCategoryVo != null && projectCategoryList != null) {
    		projectStatisticsCategoryVo.setData(projectCategoryList);
    	}
    	if (logger.isDebugEnabled()) {
    		logger.debug("请求项目分类统计数据返回");
    	} 
        return R.ok().put("data", projectStatisticsCategoryVo);
    }
    
    /**
     * 协议金额统计
     */
    @RequestMapping("/contractamount")
    @RequiresPermissions("project:statistics:contractamount")
    public R contractAmount(@RequestParam Map<String, Object> params){
    	if (logger.isDebugEnabled()) {
    		logger.debug("请求项目协议金额统计数据 ");
    	}   
    	 try {
             Query q  = new Query<ProjectStatisticsCategoryVo>(params);
             List<ProjectCategory> projectCategoryList = projectStatisticsService.getProjectStatisticsByProjectNoList(q);
             ProjectStatisticsCategoryVo projectStatisticsCategoryVo = projectStatisticsService.getProjectStatisticsAmountByProjectNo(
           		                                     (String)params.get("startDate"),(String)params.get("endDate"),
           		                                     (String)params.get("projectName"));
             int count = projectStatisticsService.getProjectStatisticsByProjectNoCount(q);
             
             //设置数据部分 
             q.getPage().setRecords(projectCategoryList);
             //设置总条数
             q.getPage().setTotal(count);

             PageUtils page =  new PageUtils(q.getPage());
             
             if (logger.isDebugEnabled()) {
         		logger.debug("请求项目到账金额统计数据返回");
         	  } 
             Map<Object,Object> retMap = new HashMap<>();
             retMap.put("page", page);
             retMap.put("listData", projectStatisticsCategoryVo);
             return R.ok().put("page", retMap);
	          
         } catch (Exception e) {
             this.logger.error(e.getMessage());
             return R.error();
         } 
    }
    
    /**
     * 到账金额统计
     */
    @RequestMapping("/donateamount")
    @RequiresPermissions("project:statistics:donateamount")
    public R donateAmount(@RequestParam Map<String, Object> params){

	    	if (logger.isDebugEnabled()) {
	    		logger.debug("请求项目到账金额统计数据 ");
	    	}    	
	    	
    	  try {
              Query q  = new Query<ProjectEntity>(params);
              List<ProjectCategory> projectCategoryList = projectStatisticsService.getProjectStatisticsDonateAmountList(q);
              ProjectStatisticsCategoryVo projectStatisticsCategoryVo = projectStatisticsService.getProjectStatisticsDonateAmount(q);
              int count = projectStatisticsService.getProjectStatisticsDonateAmountListCount(q);
              
              //设置数据部分 
              q.getPage().setRecords(projectCategoryList);
              //设置总条数
              q.getPage().setTotal(count);

              PageUtils page =  new PageUtils(q.getPage());
              
              if (logger.isDebugEnabled()) {
          		logger.debug("请求项目到账金额统计数据返回");
          	  } 
              Map<Object,Object> retMap = new HashMap<>();
              retMap.put("page", page);
              retMap.put("listData", projectStatisticsCategoryVo);
              return R.ok().put("page", retMap);
	          
          } catch (Exception e) {
              this.logger.error(e.getMessage());
              return R.error();
          }
    	
    }
    
    /**
     * 支出金额统计
     */
    @RequestMapping("/receiveramount")
    @RequiresPermissions("project:statistics:receiveramount")
    public R receiverAmount(@RequestParam Map<String, Object> params){

    	if (logger.isDebugEnabled()) {
    		logger.debug("请求项目支出金额统计数据 ");
    	}    	
    	try {
            
    		Query q  = new Query<ProjectEntity>(params);
         	ProjectStatisticsCategoryVo projectStatisticsCategoryVo = projectStatisticsService.getProjectStatisticsReceiveAmount(q);
        	List<ProjectCategory> projectCategoryList =   projectStatisticsService.getProjectStatisticsReceiveAmountList(q);
        	
             int count = projectStatisticsService.getProjectStatisticsReceiveAmountListCount(q);
             
             //设置数据部分
             q.getPage().setRecords(projectCategoryList);
             //设置总条数
             q.getPage().setTotal(count);

             PageUtils page =  new PageUtils(q.getPage());
             
             if (logger.isDebugEnabled()) {
         		logger.debug("请求项目到账金额统计数据返回");
         	  } 
             Map<Object,Object> retMap = new HashMap<>();
             retMap.put("page", page);
             retMap.put("listData", projectStatisticsCategoryVo);
             return R.ok().put("page", retMap);
	          
         } catch (Exception e) {
             this.logger.error(e.getMessage());
             return R.error();
         } 
    }
}
