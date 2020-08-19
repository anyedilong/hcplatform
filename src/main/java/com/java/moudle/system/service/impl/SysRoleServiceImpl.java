package com.java.moudle.system.service.impl;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dao.SysFunctionDao;
import com.java.moudle.system.dao.SysRoleDao;
import com.java.moudle.system.dao.SysRoleFunctionDao;
import com.java.moudle.system.dao.SysUserRoleDao;
import com.java.moudle.system.domain.SysFunction;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysRoleFunction;
import com.java.moudle.system.domain.SysUserRole;
import com.java.moudle.system.dto.MeunAnniuDto;
import com.java.moudle.system.dto.SysRoleDto;
import com.java.moudle.system.service.SysRoleService;
import com.java.until.TreeUtil;
import com.java.until.UUIDUtil;
import com.java.until.dba.PageModel;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-17 11:23
 **/
@Transactional(rollbackFor = Exception.class)
@Named
public class SysRoleServiceImpl implements SysRoleService {

    @Inject
    private SysRoleDao sysRoleDao;

    @Inject
    private SysRoleFunctionDao sysRoleFunctionDao;

    @Inject
    private SysFunctionDao sysFunctionDao;

    @Inject
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<SysFunction> queryRoleFunction(String roleId) {
        // 查询角色所有的权限
        List<SysFunction> sysFunctions = sysFunctionDao.queryRoleFunction(roleId);

        // 查询所有权限, 然后开始判断角色权限
        List<SysFunction> sysFunctionList2 = sysFunctionDao.findList();

        //过滤角色没有的按钮
        List<SysFunction> sysFunctionList1 = new ArrayList<>();
        SysRole role = sysRoleDao.get(roleId);
        if("2".equals(role.getRoleType())) {
        	sysFunctionList1 = sysFunctionList2.stream().filter(info -> ((!"新增".equals(info.getName()) && !"新建".equals(info.getName()) 
        			&& !"修改".equals(info.getName()) && !"删除".equals(info.getName()) && !"撤回".equals(info.getName()) 
        			&& !"发布".equals(info.getName()) && !"发表".equals(info.getName()) && !"提交".equals(info.getName()) && !"置顶（取消置顶）".equals(info.getName()))) 
        			|| "c8929d9a3afc436da7ebbd8fc109ed54".equals(info.getParentId())).collect(Collectors.toList());
        }else if("3".equals(role.getRoleType())){
        	sysFunctionList1 = sysFunctionList2.stream().filter(info -> (!"审核".equals(info.getName())
        			&& !"20cf25a65bc84358906101353dcf1a92".equals(info.getId()) && !"20cf25a65bc84358906101353dcf1a92".equals(info.getParentId()))).collect(Collectors.toList());
        }
        
        // 短短出角色所拥有的权限并修改标识
        if (sysFunctionList1 != null && sysFunctions != null) {

            sysFunctionList1.forEach(sysFunction -> {
                sysFunctions.forEach(sysFunction1 -> {
                    // 如果相等说明该角色拥有该权限
                    if (sysFunction.getId().equals(sysFunction1.getId())) {
                        sysFunction.setAuthority(1);
                    }
                });
            });
        }

        // 返回权限树
        return TreeUtil.getTreeList(sysFunctionList1);
    }

    @Override
    public List<SysRole> getAllRole(String userId) {
        return sysRoleDao.getSysRolesByUserId(userId);
    }

    @Override
    public JsonResult roleAuthorization(MeunAnniuDto meunAnniuDto) {

        // 先根据角色id执行权限全删操作, 在执行添加权限操作
        sysRoleFunctionDao.delByRoleId(meunAnniuDto.getRoleId());

        SysRoleFunction sysRoleFunction = new SysRoleFunction();

        // 添加权限
        meunAnniuDto.getSysFunctionList().forEach(sysFunction -> {
            sysRoleFunction.setId(UUIDUtil.getUUID());
            sysRoleFunction.setRoleId(meunAnniuDto.getRoleId());
            sysRoleFunction.setFunctionId(sysFunction.getId());
            sysRoleFunctionDao.save(sysRoleFunction);
        });

        return new JsonResult();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult deleteRole(String roleId) {

        // 判断是否是超级管理员, 超级管理员不可删除
        SysRole sysRole = sysRoleDao.get(roleId);
        if (sysRole != null) {
            if ("1".equals(sysRole.getRoleType())) {
                return new JsonResult(null, 9001, "不能删除超级管理员!");
            }
        } else {
            return new JsonResult(null, 9001, "未查询到该角色!");
        }

        // 先查询角色是否被用户引用
        List<SysUserRole> sysUserRoles = sysUserRoleDao.getSysUserRole(roleId);
        if (sysUserRoles.size() > 0) {
            return new JsonResult(null, 9001, "该角色正在使用，不能删除!");
        }

        // 否则删除角色
        sysRoleDao.updateStatus(sysRole.getId(), "1");
        return new JsonResult();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addRole(SysRole sysRole) {

        //  判断当前机构的角色名称和角色code不能重复
        SysRole sysRole1 = sysRoleDao.getSysRoleByCodeAndUserId(sysRole);
        SysRole sysRole2 = sysRoleDao.getSysRoleByNameAndUserId(sysRole);
        if (sysRole1 != null) {
            return new JsonResult(null, 9001, "角色编码不能重复，请重新输入！");
        }
        if (sysRole2 != null) {
            return new JsonResult(null, 9001, "角色名称不能重复，请重新录入！");
        }

        sysRole.setId(UUIDUtil.getUUID());
        sysRole.setStatus("0");
        sysRoleDao.save(sysRole);

        // 为角色分配默认权限  修改密码
        List<SysRoleFunction> sysRoleFunctions = new ArrayList<>();
        // 封装修改密码及其父级关联表(输入要分配权限的id即可)
        defaultPermissions(sysRoleFunctions, sysRole.getId(), "20cf25a65bc84358906101353dcf1a91");
        sysRoleFunctions.forEach(sysRoleFunction -> {
            sysRoleFunctionDao.save(sysRoleFunction);
        });

        return new JsonResult();
    }

    @Override
    public List<SysFunction> queryRoleSomeFunctions(String userId) {

        // 查询用户所有的角色 (一个用户只有一个角色, 有时间再改)
        List<SysRole> sysRoles = sysRoleDao.getSysRoleByUserId(userId);
        List<SysFunction> sysFunctionList = new ArrayList<>();

        // 根据角色查询所有的权限
        if (sysRoles != null) {
            sysRoles.forEach(sysRole -> {
                // 查询角色所有的权限
                List<SysFunction> sysFunctions = sysFunctionDao.queryRoleFunction(sysRole.getId());
                if (sysFunctions != null) {
                    sysFunctionList.addAll(sysFunctions);
                }

            });
        }

        // 返回权限树
        return TreeUtil.getTreeList(sysFunctionList);
    }

    @Override
    public void getRoleList(SysRoleDto dto, PageModel pageModel) {
        sysRoleDao.getRoleList(dto, pageModel);
    }

	@Override
	public SysRole getRoleInfoByUserId(String userId) {
		return sysRoleDao.getRoleInfoByUserId(userId);
	}

	/**
	 * @Description 传入角色权限关联集合用来接收数据, 角色id, 权限id 返回菜单集合
	 * @Author zw
	 * @Date 16:17 2020-04-01
	 * @Param
	 * @return java.util.List<com.java.moudle.system.domain.SysRoleFunction>
	 **/
	private List<SysRoleFunction> defaultPermissions (List<SysRoleFunction> sysRoleFunctions, String roleId, String functionId) {

        // 查询权限表
        SysFunction sysFunction = sysFunctionDao.get(functionId);

        // 封装角色权限关联表
        SysRoleFunction sysRoleFunction = new SysRoleFunction();
        sysRoleFunction.setId(UUIDUtil.getUUID());
        sysRoleFunction.setRoleId(roleId);
        sysRoleFunction.setFunctionId(functionId);
        sysRoleFunctions.add(sysRoleFunction);

        // 判断是否还有父权限
        if (!"0".equals(sysFunction.getParentId())) {

            // 递归查询
            defaultPermissions(sysRoleFunctions, roleId, sysFunction.getParentId());
        }
        return sysRoleFunctions;
    }



}
