package io.nakong.modules.project.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.project.service.AreaService;
import io.nakong.modules.project.service.DonatorService;
import io.nakong.modules.project.service.ProjectService;
import io.nakong.modules.project.service.ProjectSummaryService;
import io.nakong.modules.project.service.ReceiverService;
import io.nakong.modules.sys.controller.AbstractController;
/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@RestController
@RequestMapping("project/summary")
public class ProjectSummaryController extends AbstractController {
    @Autowired
    private ProjectSummaryService projectSummaryService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DonatorService donatorService;
    @Autowired
    private ReceiverService receiverService;
    @Autowired
    private AreaService areaService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("project:summary:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = null;
        try {
            page = projectSummaryService.queryPage(params);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }

        return R.ok().put("page", page);
    }

    /**
     * 捐赠单位详情
     */
    @RequestMapping("/donator/info/{projectId}")
    @RequiresPermissions("project:summary:donatorInfo")
    public R donatorInfo(@PathVariable("projectId") Integer id){
        try {
            return R.ok().put("donatorEntity", projectSummaryService.queryDonatorById(id));
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
    }

    /**
     * 接收捐赠单位详情
     */
    @RequestMapping("/receiver/info/{projectId}")
    @RequiresPermissions("project:summary:receiverInfo")
    public R receiverInfo(@PathVariable("projectId") Integer id){
        try {
            return R.ok().put("list", projectSummaryService.queryReceiversByProjectId(id));
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("project:summary:delete")
    public R delete(@RequestBody Integer[] ids){
        try {
            projectService.deleteProject(Arrays.asList(ids));
            return R.ok();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/export")
    @RequiresPermissions("project:summary:export")
    public ResponseEntity<FileSystemResource> export(@RequestParam Map<String, Object> params) {
    	File file;
		try {
			file = projectSummaryService.exportProjectsByCondition(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");

        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyyMMdd_HHmm");
        logger.info("时间为：" + dateFormat.format(date));

        headers.add("Content-Disposition", "attachment; filename=ProjectList" + dateFormat.format(date) + ".xls");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

}
