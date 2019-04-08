package io.nakong.modules.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.project.entity.DonateAmountEntity;
import io.nakong.modules.project.entity.DonatorEntity;
import io.nakong.modules.project.service.DonateAmountService;
import io.nakong.modules.project.service.DonatorService;
import io.nakong.modules.project.service.ProjectService;
import io.nakong.modules.project.vo.ProjectVo;
import io.nakong.modules.sys.controller.AbstractController;



/**
 *
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:15
 */
@RestController
@RequestMapping("project/donateamount")
public class DonateAmountController extends AbstractController {
    @Autowired
    private DonateAmountService donateAmountService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DonatorService donatorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:donateamount:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = null;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("请求项目分类统计数据 参数");
            }
            page = donateAmountService.queryPage(params);
            if (logger.isDebugEnabled()) {
                logger.debug("请求项目分类统计数据返回");
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("project:donateamount:info")
    public R info(@PathVariable("id") Integer id){
        DonateAmountEntity donateAmount = donateAmountService.selectById(id);

        return R.ok().put("donateAmount", donateAmount);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("project:donateamount:save")
    public R save(@RequestBody DonateAmountEntity donateAmount){
        try {
            ProjectVo project = projectService.selectByIdV2(donateAmount.getProjectId());
            donateAmount.setContractNo(project.getProjectNo());

//            DonatorEntity donator = donatorService.selectById(project.getDonatorId());
//            donateAmount.setDonator(donator.getCompanyName());

            donateAmountService.insert(donateAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("project:donateamount:update")
    public R update(@RequestBody DonateAmountEntity donateAmount){
        try {
            ProjectVo project = projectService.selectByIdV2(donateAmount.getProjectId());
            donateAmount.setContractNo(project.getProjectNo());

            /*DonatorEntity donator = donatorService.selectById(project.getDonatorId());
            donateAmount.setDonator(donator.getCompanyName());*/
            // donateAmountService.updateById(donateAmount.getId(),donateAmount.getDonator());
            donateAmountService.updateById(donateAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:donateamount:delete")
    public R delete(@RequestBody Integer[] ids){
        donateAmountService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }



    @RequestMapping("/companyName/{id}")
    //@RequiresPermissions("project:donateamount:info")
    public R companyName(@PathVariable("id") Integer id) throws Exception{
        ProjectVo project = null;
        try {
            project = projectService.selectByIdV2(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DonatorEntity donator = donatorService.selectById(project.getDonatorId());
        List<DonateAmountEntity> donateAmountEntityList = donateAmountService.selectList(new EntityWrapper<DonateAmountEntity>().eq("project_id",project.getId()));
        if(donateAmountEntityList != null &&  donateAmountEntityList.size()>0){
            donator.setCompanyName(donateAmountEntityList.get(0).getDonator());
        }
        return R.ok().put("donator", donator);
    }
    
    @RequestMapping("/donateName/{id}")
    //@RequiresPermissions("project:donateamount:info")
    public R donateName(@PathVariable("id") Integer id) throws Exception{

    	  DonateAmountEntity entity = donateAmountService.selectById(id);
          DonatorEntity donator = donatorService.selectById(entity.getProjectId());
          if (entity != null && !StringUtils.isBlank(entity.getDonator())) {
          	donator.setCompanyName(entity.getDonator());
          }
        return R.ok().put("donator", donator);
    }
}