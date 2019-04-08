package io.nakong.modules.project.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.excel.ReaderBuilder;
import io.nakong.common.exception.RRException;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.common.utils.R;
import io.nakong.modules.project.dao.AreaDao;
import io.nakong.modules.project.dao.DonateAmountDao;
import io.nakong.modules.project.dao.DonatorDao;
import io.nakong.modules.project.dao.ProjectDao;
import io.nakong.modules.project.dao.ProjectTypeDao;
import io.nakong.modules.project.dao.ReceiverAmountDao;
import io.nakong.modules.project.dao.ReceiverDao;
import io.nakong.modules.project.entity.AreaEntity;
import io.nakong.modules.project.entity.DonateAmountEntity;
import io.nakong.modules.project.entity.DonatorEntity;
import io.nakong.modules.project.entity.ProjectEntity;
import io.nakong.modules.project.entity.ProjectTypeEntity;
import io.nakong.modules.project.entity.ReceiverAmountEntity;
import io.nakong.modules.project.entity.ReceiverEntity;
import io.nakong.modules.project.service.ProjectService;
import io.nakong.modules.project.service.ProjectTypeService;
import io.nakong.modules.project.vo.DonatorVo;
import io.nakong.modules.project.vo.ProjectVo;
import io.nakong.modules.project.vo.ReceiverVo;

@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private DonatorDao donatorDao;

    @Autowired
    private ReceiverDao receiverDao;

	@Autowired
    private AreaDao areaDao;
	
	@Autowired
	private ProjectTypeDao projectTypeDao;

	@Autowired
	private DonateAmountDao donateAmountDao;

	@Autowired
	private ReceiverAmountDao receiverAmountDao;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception{
        String projectNo = (String)params.get("projectNo");
        if (StringUtils.isNotEmpty(projectNo)) {
            params.put("projectNo", "%"+projectNo+"%");
        }

		String projectType = (String) params.get("projectType");
		if (StringUtils.isNotEmpty(projectType)) {
			params.put("projectType", projectType);
		}

		String donatorName = (String) params.get("donatorName");
		if (StringUtils.isNotEmpty(donatorName)) {
			params.put("donatorName", "%" + donatorName + "%");
		}

		String receiverName = (String) params.get("receiverName");
		if (StringUtils.isNotEmpty(receiverName)) {
			params.put("receiverName", "%" + receiverName + "%");
		}

		if (StringUtils.isNotEmpty(receiverName)) {
			params.put("receiverName", "%" + receiverName + "%");
		}

		String startDateStr = (String) params.get("startDate");
		if (StringUtils.isNotEmpty(startDateStr)) {
			Date startDate = DateUtils.parseDate((String) params.get("startDate"), new String[] { "yyyy-MM-dd" });
			params.put("startDate", startDate);
		} else {
			params.put("startDate", null);
		}

		String endDateStr = (String) params.get("endDate");
		if (StringUtils.isNotEmpty(endDateStr)) {
			Date endDate = DateUtils.parseDate((String) params.get("endDate"), new String[] { "yyyy-MM-dd" });
			params.put("endDate", endDate);
		} else {
			params.put("endDate", null);
		}

		Query q = new Query<ProjectEntity>(params);
		List<ProjectEntity> projectList = projectDao.queryListByCriteria(q);
		
		for (ProjectEntity pjt : projectList) {
			ProjectTypeEntity pt = projectTypeService.selectById(pjt.getProjectType());
			if (pt != null) {
				pjt.setTypeName(pt.getName());
			}
		}
		
		
		Long projectCount = projectDao.countByCriteria(q);
		// 设置数据部分
		q.getPage().setRecords(projectList);
		// 设置总条数
		q.getPage().setTotal(projectCount.intValue());

		return new PageUtils(q.getPage());
	}

    @Override
    public R insertProject(ProjectVo project, Long userId) throws Exception {
    	if (project == null) {
			return R.error("传入项目为空");
		}
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("project_no", project.getProjectNo());
		List<ProjectEntity> existPjt = projectDao.selectByMap(columnMap);
		if (existPjt != null && !existPjt.isEmpty()) {
//			System.out.println(project.getProjectNo() + "已存在，跳过");
//			throw new RRException(project.getProjectNo() + "已存在", 1001);
			return R.error("协议号已存在");
		}


        Date now = new Date();
        //先保存捐赠者
        DonatorEntity donatorEntity = project.getDonatorEntity();
        donatorEntity.setInsertTime(now);
        donatorEntity.setUpdateTime(now);
        donatorEntity.setInsertUser(String.valueOf(userId));
        donatorDao.insert(donatorEntity);

        //再保存项目
        Integer donatorId = donatorEntity.getId();
        project.setInsertTime(now);
        project.setUpdateTime(now);
        project.setDonatorId(donatorId);
        project.setInsertUser(String.valueOf(userId));
        projectDao.insert(project);
        Integer projectId = project.getId();

        //再保存接受捐赠者
        for (ReceiverEntity receiver : project.getReceiverEntity()) {
            receiver.setInsertTime(now);
            receiver.setUpdateTime(now);
            receiver.setProjectId(projectId);
            receiver.setInsertUser(String.valueOf(userId));
            receiverDao.insert(receiver);
        }
        
        return R.ok();
    }

	@Override
	public ProjectVo selectByIdV2(Integer projectId) throws Exception {
    	ProjectEntity project = projectDao.selectById(projectId);
    	ProjectVo projectVo = new ProjectVo();
		BeanUtils.copyProperties(project, projectVo);

    	DonatorEntity donatorEntity = donatorDao.selectById(project.getDonatorId());

		DonatorVo donatorVo = new DonatorVo();
		BeanUtils.copyProperties(donatorEntity, donatorVo);
		List<AreaEntity> cityList = new ArrayList<>();
		List<AreaEntity> countyList = new ArrayList<>();
		if (StringUtils.isNotBlank(donatorEntity.getProvinceId())) {
			//城市
			cityList = areaDao.selectList(new EntityWrapper<AreaEntity>().eq(
					"parent_id", donatorVo.getProvinceId()
			));
		}

		donatorVo.setCityList(cityList);

    	projectVo.setDonatorEntity(donatorVo);

		if (StringUtils.isNotBlank(donatorEntity.getCityId())) {
			//区县
			countyList = areaDao.selectList(new EntityWrapper<AreaEntity>().eq(
					"parent_id", donatorVo.getCityId()
			));
		}
		donatorVo.setCountyList(countyList);


    	List<ReceiverEntity> receiverEntities = receiverDao.selectList(new EntityWrapper<ReceiverEntity>().eq(
    			"project_id", project.getId()
		));

    	List<ReceiverVo> receiverVoList = new ArrayList<>();

    	for (ReceiverEntity receiver : receiverEntities) {
			ReceiverVo temp = new ReceiverVo();
			BeanUtils.copyProperties(receiver, temp);


			if (StringUtils.isNotBlank(receiver.getProvinceId())) {
				//城市
				cityList = areaDao.selectList(new EntityWrapper<AreaEntity>().eq(
						"parent_id", receiver.getProvinceId()
				));
			} else {
				cityList = new ArrayList<>();
			}

			temp.setCityList(cityList);

			if (StringUtils.isNotBlank(receiver.getCityId())) {
				//区县
				countyList = areaDao.selectList(new EntityWrapper<AreaEntity>().eq(
						"parent_id", receiver.getCityId()
				));
			} else {
				countyList = new ArrayList<>();
			}

			temp.setCountyList(countyList);

			receiverVoList.add(temp);
		}

		projectVo.setReceiverEntity(receiverVoList);

		return projectVo;
	}

	@Override
	public R updateProject(ProjectVo project, Long userId) throws Exception {
		
		ProjectEntity projectEntity = projectDao.queryProjectById(project.getId());		
		ProjectEntity existProject = projectDao.queryProjectByPojectNo(project.getId(),project.getProjectNo());
		if (projectEntity != null && existProject != null) {
			return R.error("协议号已存在");
		}

    	//修改项目基本信息
		projectDao.updateById((ProjectEntity)project);
		
		DonatorEntity donatorEntity = (DonatorEntity)project.getDonatorEntity();
		donatorEntity.setId(projectEntity.getDonatorId());
		//修改捐赠单位信息
		donatorDao.updateById(donatorEntity);
		
		
         // 修改到账信息的 项目编号
		donateAmountDao.updateProjectNoByProjectId(project.getProjectNo(),project.getId());
		
		List<ReceiverEntity> oldRecs = receiverDao.selectList(new EntityWrapper<ReceiverEntity>().eq("project_id", project.getId()));
		Map<String, Integer> oldRecNames = new HashMap<>();
		List<ReceiverVo> newRecs = project.getReceiverEntity();
		Set<String> newRecNames = new HashSet<>();
		
		List<ReceiverEntity> toAddRecs = new ArrayList<>();
		List<ReceiverEntity> toUpdateRecs = new ArrayList<>();
		List<ReceiverEntity> toDeleteRecs = new ArrayList<>();
		
		for (ReceiverEntity newRec : newRecs) {
			if (newRecNames.contains(newRec.getReceiveCompany())) {
				return R.error("接受捐赠单位名称重复: " + newRec.getReceiveCompany());
			}
			newRecNames.add(newRec.getReceiveCompany());
		}
		
		for (ReceiverEntity oldRec : oldRecs) {
			oldRecNames.put(oldRec.getReceiveCompany(), oldRec.getId());
		}
		
		for (ReceiverEntity newRec : newRecs) {
			if (oldRecNames.containsKey(newRec.getReceiveCompany())) {
				if (newRec.getId() == null) {
					newRec.setId(oldRecNames.get(newRec.getReceiveCompany()));
				}
				toUpdateRecs.add(newRec);
			} else {
				toAddRecs.add(newRec);
			}
		}
		
		for (ReceiverEntity oldRec : oldRecs) {
			if (newRecNames.contains(oldRec.getReceiveCompany())) {
				continue;
			} else {
				toDeleteRecs.add(oldRec);
			}
		}
		
		
		
//		//删除接收单位
//		receiverDao.delete(new EntityWrapper<ReceiverEntity>().eq("project_id", project.getId()));
		//重新插入
		Date now = new Date();
		//Delete
		for (ReceiverEntity receiver : toDeleteRecs) {
			int count = receiverAmountDao.selectCount(new EntityWrapper<ReceiverAmountEntity>().eq("receiver_id", receiver.getId()));
			if (count > 0) {
				// throw new RRException(receiver.getReceiveCompany() + "-已有支出信息，单位名称不能变更");
				return R.error(receiver.getReceiveCompany() + "-已有支出信息，单位名称不能变更");
			}
			receiverDao.deleteById(receiver.getId());
		}
		//Insert
		for (ReceiverEntity receiver : toAddRecs) {
			receiver.setInsertTime(now);
			receiver.setUpdateTime(now);
			receiver.setProjectId(project.getId());
			receiver.setInsertUser(String.valueOf(userId));
			receiverDao.insert(receiver);
		}
		//Update
		for (ReceiverEntity receiver : toUpdateRecs) {
			receiver.setUpdateTime(now);
			receiverDao.updateById(receiver);
	}
      return R.ok("修改成功");
	}

	@Override
	public void deleteProject(List<Integer> ids) throws Exception {
		//删除捐赠者
    	projectDao.deleteDonatorByProjects(ids);
    	//删除到账列表
		donateAmountDao.delete(new EntityWrapper<DonateAmountEntity>().in("project_id", ids));
		//删除接收者
		receiverDao.delete(new EntityWrapper<ReceiverEntity>().in("project_id", ids));
		//删除支出列表
		receiverAmountDao.delete(new EntityWrapper<ReceiverAmountEntity>().in("project_id", ids));
		//删除项目
		projectDao.deleteBatchIds(ids);

	}

	public List<ProjectVo> parsePjtByExcel(String filePath) throws IOException, SAXException, InvalidFormatException {
		File file = new File(filePath);
		return parsePjtByExcel(file);
	}

	public List<ProjectVo> parsePjtByExcel(File file) throws IOException, SAXException, InvalidFormatException {
		FileInputStream fis = new FileInputStream(file);
		return parsePjtByExcel(fis);
	}
	

	public List<ProjectVo> parsePjtByExcel(InputStream excelInputStream) throws IOException, SAXException, InvalidFormatException {

		
		String xmlConfig = "excel/import-config.xml";
		InputStream inputXML = new BufferedInputStream(new ClassPathResource(xmlConfig).getInputStream());
		XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
		InputStream inputXLS = new BufferedInputStream(excelInputStream);
		List<ProjectVo> projects = new ArrayList<>();
		Map beans = new HashMap();
		beans.put("projects", projects);
		XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
		
		return projects;
	}

	public void importByExcel(InputStream excelInputStream, Long userId) throws Exception {
		List<ProjectVo> projects = parsePjtByExcel(excelInputStream);
		if (projects == null || projects.isEmpty()) {
			throw new RRException("未解析到项目信息");
		}
		StringBuilder rtmMsg = new StringBuilder();
		for (ProjectVo project : projects) {
			String donatorAddress = project.getDonatorEntity().getAddressDetail();
			if (StringUtils.isNotBlank(donatorAddress)) {

				String[] areaStrs = donatorAddress.split("/");
				if (areaStrs.length > 1) {
					project.getDonatorEntity().setProvinceId(getAreaIdByName(areaStrs[0], 2));
				}
				if (areaStrs.length > 2) {
					project.getDonatorEntity().setCityId(getAreaIdByName(areaStrs[1], 3));
				}
				if (areaStrs.length > 3) {
					project.getDonatorEntity().setCountyId(getAreaIdByName(areaStrs[2], 4));
				}
				project.getDonatorEntity().setAddressDetail(areaStrs[areaStrs.length-1]);
			}
			
			for (ReceiverVo recVo : project.getReceiverEntity()) {
				String receiverAddress = recVo.getAddressDetail();
				if (StringUtils.isNotBlank(receiverAddress)) {
	
					String[] areaStrs = receiverAddress.split("/");
					String provinceName = areaStrs[0];
					provinceName = provinceName.replaceAll("省", "").replaceAll("市", "");
					if (areaStrs.length > 1) {
						recVo.setProvinceId(getAreaIdByName(provinceName, 2));
					}
					if (areaStrs.length > 2) {
						String areaName = areaStrs[1];
						if ("|北京|上海|天津|重庆|".contains(provinceName) && !"市辖区".equals(areaName)) {
							recVo.setCityId(getAreaIdByName("市辖区", 3, recVo.getProvinceId()));
							recVo.setCountyId(getAreaIdByName(areaName, 4, recVo.getCityId()));
						} else {
							recVo.setCityId(getAreaIdByName(areaName, 3));
						}
					}
					if (areaStrs.length > 3) {
						recVo.setCountyId(getAreaIdByName(areaStrs[2], 4, recVo.getCityId()));
					}
					
					recVo.setAddressDetail(areaStrs[areaStrs.length - 1]);
				}
			}
			String projectTypeName = project.getProjectTypeName();
			List<ProjectTypeEntity> pt = projectTypeDao.selectList(new EntityWrapper<ProjectTypeEntity>().eq(
					"name", projectTypeName));
			if (!pt.isEmpty()) {
				project.setProjectType(pt.get(0).getId());
			}
			try {
				insertProject(project, userId);
			} catch (Exception e) {
				rtmMsg.append(project.getProjectNo());
				rtmMsg.append(" | ");
				rtmMsg.append(e.getMessage());
				rtmMsg.append("\n");
			}
		}
		if (rtmMsg.length() > 0) {
			throw new RRException(rtmMsg.toString());
		}
	}
	
	private String getAreaIdByName(String name, int level) {
		return getAreaIdByName(name, level, null);
	}
	
	private String getAreaIdByName(String name, int level, String parentId) {
		EntityWrapper wrapper = new EntityWrapper<AreaEntity>();
		wrapper.eq(
				"area_name", name).eq("area_level", level);
		if (parentId != null) {
			wrapper.eq("parent_id", parentId);
		}
		List<AreaEntity> areaList = areaDao.selectList(wrapper);
		if (areaList.isEmpty()) {
			return null;
		}
		return areaList.get(0).getBaseAreaId();
	}

	
	public List<ProjectEntity> queryProjectList(Map<String, Object> params)  {
		
		try {
			String projectNo = (String)params.get("projectNo");
	        if (StringUtils.isNotEmpty(projectNo)) {
	            params.put("projectNo", "%"+projectNo+"%");
	        }
	
			String projectType = (String) params.get("projectType");
			if (StringUtils.isNotEmpty(projectType)) {
				params.put("projectType", projectType);
			}
	
			String donatorName = (String) params.get("donatorName");
			if (StringUtils.isNotEmpty(donatorName)) {
				params.put("donatorName", "%" + donatorName + "%");
			}
	
			String receiverName = (String) params.get("receiverName");
			if (StringUtils.isNotEmpty(receiverName)) {
				params.put("receiverName", "%" + receiverName + "%");
			}
	
			if (StringUtils.isNotEmpty(receiverName)) {
				params.put("receiverName", "%" + receiverName + "%");
			}
	
			String startDateStr = (String) params.get("startDate");
			if (StringUtils.isNotEmpty(startDateStr)) {
				Date startDate = DateUtils.parseDate((String) params.get("startDate"), new String[] { "yyyy-MM-dd" });
				params.put("startDate", startDate);
			} else {
				params.put("startDate", null);
			}
	
			String endDateStr = (String) params.get("endDate");
			if (StringUtils.isNotEmpty(endDateStr)) {
				Date endDate = DateUtils.parseDate((String) params.get("endDate"), new String[] { "yyyy-MM-dd" });
				params.put("endDate", endDate);
			} else {
				params.put("endDate", null);
			}
	
			Query<ProjectEntity> q = new Query<ProjectEntity>(params);
			return  projectDao.queryListByCriteria(q);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	 
	}



//	public static void main(String[] args) throws InvalidFormatException, IOException, SAXException {
//		String path ="D:/git/cedf/cedf-project-backend/src/main/resources/excel";
//		String filePath = path + File.separator + "import-example.xls";
//		List<ProjectEntity> projects = importByExcel(filePath);
//
//		exportToExcel(projects);
//	}

	public  List<ProjectEntity> selectList(EntityWrapper<ProjectEntity> projectEntity) throws Exception{
		List<ProjectEntity> projects = projectDao.selectList(projectEntity);
		return projects;
	};

}
