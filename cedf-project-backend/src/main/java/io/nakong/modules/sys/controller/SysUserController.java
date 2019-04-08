package io.nakong.modules.sys.controller;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.nakong.common.annotation.SysLog;
import io.nakong.common.utils.PageUtils;
import io.nakong.common.utils.R;
import io.nakong.common.validator.Assert;
import io.nakong.common.validator.ValidatorUtils;
import io.nakong.common.validator.group.AddGroup;
import io.nakong.common.validator.group.UpdateGroup;
import io.nakong.modules.sys.entity.SysRoleEntity;
import io.nakong.modules.sys.entity.SysUserEntity;
import io.nakong.modules.sys.form.PasswordForm;
import io.nakong.modules.sys.service.SysRoleService;
import io.nakong.modules.sys.service.SysUserRoleService;
import io.nakong.modules.sys.service.SysUserService;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysRoleService sysRoleService;


	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			params.put("create_User_Id", getUserId());
//		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		   SysUserEntity sysUserEntity  = getUser() ;
		
			List<Long> roleList = sysUserRoleService.queryRoleIdList(sysUserEntity.getUserId());
			if (roleList != null && roleList.size() > 0) {
				
				List<SysRoleEntity> roleEntity = sysRoleService.getListBatchId(roleList);
				if (roleEntity != null) {
					StringJoiner stringJoiner = new StringJoiner(",");
					for (SysRoleEntity role : roleEntity ) {
						stringJoiner.add(role.getRoleName());
					}
					 sysUserEntity.setRoleName(stringJoiner.toString());
				}
			}
			return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		String newPassword = form.getNewPassword();
		String comfirmPassword = form.getComfirmPassword();
		if (!newPassword.equals(comfirmPassword)){
			return R.error("新密码、确认密码不一致");
		}
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		 newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}else {
			return R.ok("保存成功");
		}
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.selectById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/updateUserName")
	@RequiresPermissions("sys:user:update")
	public R updateUserName(@RequestBody SysUserEntity user){
		
	 
		SysUserEntity sysUserEntity  = new SysUserEntity();
		sysUserEntity.setRealName(user.getRealName());
		sysUserEntity.setMobile(user.getMobile());
		sysUserEntity.setUserId(getUserId());
		sysUserService.updateUserNameByuserId(sysUserEntity);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
