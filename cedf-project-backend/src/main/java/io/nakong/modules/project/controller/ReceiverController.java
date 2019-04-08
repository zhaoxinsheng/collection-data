package io.nakong.modules.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import io.nakong.modules.project.entity.ReceiverEntity;
import io.nakong.modules.project.service.ReceiverService;



/**
 *
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@RestController
@RequestMapping("project/receiver")
public class ReceiverController {
    @Autowired
    private ReceiverService receiverService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:receiver:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = receiverService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listbyprojectid/{id}")
//    @RequiresPermissions("generator:receiver:list")
    public R listByProjectId(@PathVariable(name = "id") String projectId){
        EntityWrapper entityWrapper = new EntityWrapper<ReceiverEntity>();
        entityWrapper.eq("project_id",projectId);
        List<ReceiverEntity> receivers = null;
        receivers = receiverService.selectList(entityWrapper);
        return R.ok().put("receivers", receivers);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("generator:receiver:info")
    public R info(@PathVariable("id") Integer id){
		ReceiverEntity receiver = receiverService.selectById(id);
        return R.ok().put("receiver", receiver);
    }

    @RequestMapping("/infoList/{receiveId}")
    public R infoList(@PathVariable("receiveId") Integer receiveId){
        ReceiverEntity receiver = receiverService.selectById(receiveId);
        List<ReceiverEntity> receivers = receiverService.selectList(new EntityWrapper<ReceiverEntity>().eq("project_id",receiver.getProjectId()));
        return R.ok().put("receivers", receivers);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:receiver:save")
    public R save(@RequestBody ReceiverEntity receiver){
			receiverService.insert(receiver);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:receiver:update")
    public R update(@RequestBody ReceiverEntity receiver){
			receiverService.updateById(receiver);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:receiver:delete")
    public R delete(@RequestBody Integer[] ids){
			receiverService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
