package io.nakong.modules.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.nakong.common.exception.RRException;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.modules.project.entity.ProjectEntity;
import io.nakong.modules.project.entity.ProjectTypeEntity;
import io.nakong.modules.project.service.ProjectService;
import io.nakong.modules.project.service.ProjectTypeService;
import io.nakong.modules.project.vo.ProjectVo;
import io.nakong.modules.sys.controller.AbstractController;



/**
 * 
 *
 * @author tom
 * @email zhaoxinsheng@codyy.com
 * @date 2018-05-15 09:23:14
 */
@RestController
@RequestMapping("project/base")
public class ProjectController extends AbstractController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectTypeService projectTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("project:base:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = null;
        try {
            page = projectService.queryPage(params);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }

        return R.ok().put("page", page);
    }

	@RequestMapping("/listall")
//	@RequiresPermissions("project:base:list")
	public R listAll(@RequestParam Map<String, Object> params) throws Exception {
		List<ProjectEntity> projects = null;
		EntityWrapper wrapper = new EntityWrapper<ProjectEntity>();
		wrapper.allEq(params);
		wrapper.orderBy("project_no", false);
		projects = projectService.selectList(wrapper);
		return R.ok().put("projects", projects);
	}

    /**
     * 列表
     */
    @RequestMapping("/typeSelect")
//    @RequiresPermissions("project:base:typeSelect")
    public R typeList(@RequestParam Map<String, Object> params){
        List<ProjectTypeEntity> list = projectTypeService.selectList(new EntityWrapper<ProjectTypeEntity>());
        return R.ok().put("list", list);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("project:base:info")
    public R info(@PathVariable("id") Integer id){

        try {
            ProjectVo project = projectService.selectByIdV2(id);
            return R.ok().put("project", project);
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("project:base:save")
    public R save(@RequestBody ProjectVo project){
        PageUtils page = null;
        try {
			return projectService.insertProject(project, getUserId());
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error(e.getMessage());
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("project:base:update")
    public R update(@RequestBody ProjectVo project){
        try {
        
           return  projectService.updateProject(project, getUserId());
            
        } catch (RRException e) {
            this.logger.error(e.getMessage());
            return R.error( e.getMsg());
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("project:base:delete")
    public R delete(@RequestBody Integer[] ids){
        try {
            projectService.deleteProject(Arrays.asList(ids));
            return R.ok();
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return R.error();
        }
    }
    


	@PostMapping("/import")
//	@RequiresPermissions("project:base:import")
	public R importData(@RequestParam("file") MultipartFile file) {
		try {
			if (file.isEmpty()) {
				return R.error(1, "文件为空");
			}

			projectService.importByExcel(file.getInputStream(), getUserId());

			return R.ok();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return R.error(1, "文件解析失败:格式错误|" + e.getMessage());
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return R.error(1, "文件解析失败");
		} catch (RRException e) {
			e.printStackTrace();
			return R.error(1, "上传失败 \n" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(1, "上传失败 \n" + e.getMessage());
		}
	}
	

    /**
     * 删除
     */
    @RequestMapping("/downloadTemplate")
//    @RequiresPermissions("project:summary:downloadTemplate")
    public ResponseEntity<FileSystemResource> downloadTemplate() {
    	
    	String xmlConfig = "excel/import-example.xls";
    	File file = null;
		try {
			file = new ClassPathResource(xmlConfig).getFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        try {
			headers.add("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("项目导入模板.xls", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			headers.add("Content-Disposition", "attachment; filename=project-import-template.xls");
		}
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

	
	
    
    /**
     * 项目统计
     */
    @RequestMapping("/statistics")
//    @RequiresPermissions("project:base:statistics")
    public R statisticsData(){ 

        return R.ok();
    }

}
