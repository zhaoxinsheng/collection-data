package io.nakong.modules.project.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nakong.common.utils.DateUtils;
import io.nakong.common.utils.NumberUtils;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.Query;
import io.nakong.modules.project.dao.DonateAmountDao;
import io.nakong.modules.project.dao.DonatorDao;
import io.nakong.modules.project.dao.ProjectDao;
import io.nakong.modules.project.dao.ProjectSummaryDao;
import io.nakong.modules.project.dao.ReceiverAmountDao;
import io.nakong.modules.project.dao.ReceiverDao;
import io.nakong.modules.project.entity.AreaEntity;
import io.nakong.modules.project.entity.DonatorEntity;
import io.nakong.modules.project.entity.ProjectEntity;
import io.nakong.modules.project.entity.ReceiverEntity;
import io.nakong.modules.project.service.AreaService;
import io.nakong.modules.project.service.ProjectSummaryService;
import io.nakong.modules.project.vo.ProjectSummaryVo;

@Service("projectSummaryService")
public class ProjectSummaryServiceImpl extends ServiceImpl<ProjectSummaryDao, ProjectSummaryVo>
		implements ProjectSummaryService {

	@Autowired
	private ProjectSummaryDao projectSummaryDao;

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private DonatorDao donatorDao;

	@Autowired
	private ReceiverDao receiverDao;

	@Autowired
	private DonateAmountDao donateAmountDao;

	@Autowired
	private ReceiverAmountDao receiverAmountDao;

	@Autowired
	private AreaService areaService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) throws Exception {
		String projectNo = (String) params.get("projectNo");
		if (StringUtils.isNotEmpty(projectNo)) {
			params.put("projectNo", "%" + projectNo + "%");
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
			Date startDate = DateUtils.stringToDate((String) params.get("startDate"),  "yyyy-MM-dd");
			params.put("startDate", startDate);
		} else {
			params.put("startDate", null);
		}

		String endDateStr = (String) params.get("endDate");
		if (StringUtils.isNotEmpty(endDateStr)) {
			Date endDate = DateUtils.stringToDate((String) params.get("endDate"),  "yyyy-MM-dd" );
			params.put("endDate", endDate);
		} else {
			params.put("endDate", null);
		}

		Query q = new Query<ProjectEntity>(params);
		List<ProjectSummaryVo> projectList = projectSummaryDao.queryListByCriteria(q);
		Long projectCount = projectSummaryDao.countByCriteria(q);
		// 设置数据部分
		q.getPage().setRecords(projectList);
		// 设置总条数
		q.getPage().setTotal(projectCount.intValue());

		return new PageUtils(q.getPage());
	}

	@Override
	public List<ReceiverEntity> queryReceiversByProjectId(Integer projectId) {
		List<ReceiverEntity> receiverEntities = receiverDao.selectList(new EntityWrapper<ReceiverEntity>().eq("project_id", projectId));
		assembleFullAddress(receiverEntities);
		return receiverEntities;
	}

	/**
	 * 填充省市县信息
	 */
	private void assembleFullAddress(List<ReceiverEntity> receiverEntities) {
		for (ReceiverEntity receiverEntity : receiverEntities) {
			AreaEntity province = areaService.selectById(receiverEntity.getProvinceId());
			String prefixString = "";
			if (province != null) {
				prefixString += province.getAreaName();
			}
			AreaEntity city = areaService.selectById(receiverEntity.getCityId());
			if (city != null) {
				prefixString += city.getAreaName();
			}
			AreaEntity county = areaService.selectById(receiverEntity.getCountyId());
			if (county != null) {
				prefixString += county.getAreaName();
			}
			receiverEntity.setAddressDetail(prefixString + receiverEntity.getAddressDetail());
		}
	}

	@Override
	public DonatorEntity queryDonatorById(Integer donatorId) {
		DonatorEntity donatorEntity = donatorDao.selectById(donatorId);
		AreaEntity province = areaService.selectById(donatorEntity.getProvinceId());
		String prefixString = "";
		if (province != null) {
			prefixString += province.getAreaName();
		}
		AreaEntity city = areaService.selectById(donatorEntity.getCityId());
		if (city != null) {
			prefixString += city.getAreaName();
		}
		AreaEntity county = areaService.selectById(donatorEntity.getCountyId());
		if (county != null) {
			prefixString += county.getAreaName();
		}

		donatorEntity.setAddressDetail(prefixString + donatorEntity.getAddressDetail());

		return donatorEntity;
	}

	@Override
	public void deleteProject(Integer projectId) throws Exception {
		// 删除捐赠者
		// projectDao.deleteDonatorByProjects(ids);
		// //删除接收者
		// receiverDao.delete(new
		// EntityWrapper<ReceiverEntity>().in("project_id", ids));
		// //删除项目
		// projectDao.deleteBatchIds(ids);
	}

	public File exportToExcel(List projects) throws IOException, SAXException, InvalidFormatException {

		String expTplPath = "excel/export-template.xls";
		File expTplFile = new ClassPathResource(expTplPath).getFile();

		// TODO
		String outFolderPath = "tmp/rlt/";
		File outFolder = new File(outFolderPath);
		if (!outFolder.exists()) {
			outFolder.mkdirs();
		}
		File outFile = new File(outFolderPath + "project_output" + System.currentTimeMillis() + ".xls");
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(expTplFile);
			os = new FileOutputStream(outFile);
			Context context = new Context();
			context.putVar("projects", projects);
			JxlsHelper.getInstance().processTemplateAtCell(is, os, context, "Sheet1!A1");
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		outFile.deleteOnExit();
		return outFile;
	}

	private String makeFullAddress4Export(String address, String provinceId, String cityId, String countyId) {
		AreaEntity province = areaService.selectById(provinceId);
		StringBuilder fullAddress = new StringBuilder();
		if (province != null) {
			fullAddress.append(province.getAreaName()).append("/");
		}
		AreaEntity city = areaService.selectById(cityId);
		if (city != null) {
			if (!"市辖区".equals(city.getAreaName())) {
				fullAddress.append(city.getAreaName()).append("/");
			}
		}
		AreaEntity county = areaService.selectById(countyId);
		if (county != null) {
			fullAddress.append(county.getAreaName()).append("/");
		}
		return fullAddress.append(address).toString();
	}

	@Override
	public File exportProjectsByCondition(Map<String, Object> params) throws Exception {
		params.put("page", "1");
		params.put("limit", "0");
		PageUtils page = queryPage(params);
		List pjts = page.getList();
		for (Object entity : pjts) {
			ProjectSummaryVo pjt = (ProjectSummaryVo) entity;

			DonatorEntity donatorEntity = donatorDao.selectById(pjt.getDonatorId());
			pjt.setDonatorAddress(makeFullAddress4Export(pjt.getDonatorAddress(), donatorEntity.getProvinceId(),
					donatorEntity.getCityId(), donatorEntity.getCountyId()));
			
			List<String> rAddrs = new ArrayList<>();
			List<ReceiverEntity> recs = queryReceiversByProjectId(pjt.getId());
			for (ReceiverEntity rec: recs) {
				rAddrs.add(makeFullAddress4Export(rec.getAddressDetail(), rec.getProvinceId(),
					rec.getCityId(), rec.getCountyId()));
			}
			pjt.setReceiverAddressDetail(StringUtils.join(rAddrs, ","));
			
			pjt.setDateTimeStr(DateUtils.format(pjt.getDateTime()));
			pjt.setCashAmountStr(NumberUtils.formatNumber(pjt.getCashAmount()));
			pjt.setMaterialAmountStr(NumberUtils.formatNumber(pjt.getMaterialAmount()));
			pjt.setInMeterialAmountStr(NumberUtils.formatNumber(pjt.getInMeterialAmount()));
			pjt.setInCashAmountStr(NumberUtils.formatNumber(pjt.getInCashAmount()));
			pjt.setTotalOutAmountStr(NumberUtils.formatNumber(pjt.getTotalOutAmount()));
			pjt.setBalanceStr(NumberUtils.formatNumber(pjt.getBalance()));
			
	 
		}

		File expFile = exportToExcel(pjts);
		return expFile;
	}

}
