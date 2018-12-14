package com.climber.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.climber.bean.AuthMenu;
import com.climber.bean.AuthTree;
import com.climber.bean.PmtJudge;
import com.climber.bean.SetSelect;
import com.climber.bean.SysColumn;
import com.climber.bean.SysCompany;
import com.climber.bean.SysMenu;
import com.climber.bean.SysRole;
import com.climber.bean.SysRoleMenu;
import com.climber.bean.SysUser;
import com.climber.bean.TreePanel;
import com.climber.bean.UserRole;
import com.climber.dao.SysDao;
import com.climber.enums.DataAction;
import com.climber.service.SysService;
import com.climber.utils.StringUtils;

import net.sf.json.JSONObject;

@Service("sysService ")
public class SysServiceImp implements SysService {

	@Resource
	private SysDao sysDao;

	// region SysMenu Methods

	@Override
	public TreePanel getSysMenu(UserRole item) {
		List<SysMenu> resultList = new ArrayList<SysMenu>();
		TreePanel tp = new TreePanel();
		List<SysMenu> menus = sysDao.getSysMenu(item);

		for (SysMenu menu : menus) {
			if (menu.getPid() == null || "".equals(menu.getPid())) {// 父级菜单开始添加
				resultList.add(menu);
				if (ifChilds(menus, menu.getId())) {// 存在子集
					getChildList(menus, menu.getId(), menu.getChildren());
				} else {
					menu.setLeaf(true);
				}
			}
		}
		tp.setChildren(resultList);
		return tp;
	}

	@Override
	public TreePanel searchMenu(SysMenu item) {
		List<SysMenu> resultList = new ArrayList<SysMenu>();
		TreePanel tp = new TreePanel();
		if (!StringUtils.isEmpty(item.getSearch())) {
			List<SysMenu> menus = sysDao.searchSysMenu(item);
			for (SysMenu menu : menus) {
				menu.setLeaf(true);
			}
			tp.setChildren(menus);
		} else {
			List<SysMenu> menus = sysDao.getAllSysMenu();
			for (SysMenu menu : menus) {
				if (menu.getPid() == null || "".equals(menu.getPid())) {// 父级菜单开始添加
					resultList.add(menu);
					if (ifChilds(menus, menu.getId())) {// 存在子集
						getChildList(menus, menu.getId(), menu.getChildren());
					} else {
						menu.setLeaf(true);
					}
				}
			}
			tp.setChildren(resultList);
		}
		return tp;
	}

	private void getChildList(List<SysMenu> menus, String pid, List<SysMenu> reList) {
		for (SysMenu menu : menus) {
			if (menu.getPid().equals(pid)) {
				reList.add(menu);
				if (ifChilds(menus, menu.getId())) {
					getChildList(menus, menu.getId(), menu.getChildren());
				} else {
					menu.setLeaf(true);
				}
			}
		}
	}

	private boolean ifChilds(List<SysMenu> list, String pid) {
		boolean flag = false;
		for (SysMenu menu : list) {
			if (menu.getPid().equals(pid)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public AuthTree getAuthMenu(SysRoleMenu item) {
		List<AuthMenu> resultList = new ArrayList<AuthMenu>();
		AuthTree tp = new AuthTree();
		List<AuthMenu> menus = sysDao.getAuthMenu(item);

		for (AuthMenu menu : menus) {
			if (menu.getPid() == null || "".equals(menu.getPid())) {// 父级菜单开始添加
				resultList.add(menu);
				if (ifAuthChilds(menus, menu.getId())) {// 存在子集
					getAuthChildList(menus, menu.getId(), menu.getChildren());
				} else {
					menu.setLeaf(true);
				}
			}
		}
		tp.setChildren(resultList);
		return tp;
	}

	private void getAuthChildList(List<AuthMenu> menus, String pid, List<AuthMenu> reList) {
		for (AuthMenu menu : menus) {
			if (menu.getPid().equals(pid)) {
				reList.add(menu);
				if (ifAuthChilds(menus, menu.getId())) {
					getAuthChildList(menus, menu.getId(), menu.getChildren());
				} else {
					menu.setLeaf(true);
				}
			}
		}
	}

	private boolean ifAuthChilds(List<AuthMenu> list, String pid) {
		boolean flag = false;
		for (AuthMenu menu : list) {
			if (menu.getPid().equals(pid)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public void saveSysMenu(SysMenu item) {
		sysDao.saveSysMenu(item);
	}

	// endregion SysMenu Methods

	// region SysColumn Methods

	@Override
	public List<SysColumn> getColumn(SysColumn item) {
		List<SysColumn> sysColumns = sysDao.getColumn(item);
		return sysColumns;
	}

	@Override
	public List<SysColumn> searchColumn(SysColumn item) {
		List<SysColumn> sysColumns = sysDao.searchColumn(item);
		return sysColumns;
	}

	@Override
	public void saveColumn(SysColumn item) {
		sysDao.saveColumn(item);
	}

	@Override
	public void batchDelColumn(SysColumn item) {
		String ids = item.getIds();
		String[] columnids = ids.split(",");
		for (String columnid : columnids) {
			SysColumn delsyscolumn = new SysColumn();
			delsyscolumn.setColumnID(columnid);
			delsyscolumn.setAction(item.getAction());
			sysDao.saveColumn(delsyscolumn);
		}
		item.getRtv().setMsg("批量删除成功！");
	}

	// endregion SysColumn Methods

	// region Pmt Methods

	@Override
	public List<PmtJudge> getPmtJudge() {
		List<PmtJudge> pmtJudge = sysDao.getPmtJudge();
		return pmtJudge;
	}

	// endregion Pmt Methods

	// region SysCompany Methods

	@Override
	public List<SysCompany> searchCompany(SysCompany item) {
		List<SysCompany> sysCompanys = sysDao.searchCompany(item);
		return sysCompanys;
	}

	@Override
	public void saveCompany(SysCompany item) {
		sysDao.saveCompany(item);
	}

	@Override
	public void batchDelCompany(SysCompany item) {
		String ids = item.getIds();
		String[] companyids = ids.split(",");
		for (String companyid : companyids) {
			SysCompany delsyscompany = new SysCompany();
			delsyscompany.setCompanyid(companyid);
			delsyscompany.setAction(item.getAction());
			sysDao.saveCompany(delsyscompany);
		}
		item.getRtv().setMsg("批量删除成功！");
	}

	// endregion SysCompany Methods

	// region SysRole Methods

	@Override
	public List<SysRole> searchRole(SysRole item) {
		List<SysRole> sysRoles = sysDao.searchRole(item);
		return sysRoles;
	}

	@Override
	public void saveRole(SysRole item) {
		sysDao.saveRole(item);
	}

	@Override
	public void batchDelRole(SysRole item) {
		String ids = item.getIds();
		String[] roleids = ids.split(",");
		for (String roleid : roleids) {
			SysRole deluserrole = new SysRole();
			deluserrole.setRoleid(roleid);
			deluserrole.setAction(item.getAction());
			sysDao.saveRole(deluserrole);

			SysRoleMenu delSysRoleMenu = new SysRoleMenu();
			delSysRoleMenu.setRoleid(roleid);
			delSysRoleMenu.setAction(item.getAction());
			sysDao.saveSysRoleMenu(delSysRoleMenu);
		}
		item.getRtv().setMsg("批量删除成功！");
	}

	@Override
	public void authRoleMenu(SysRoleMenu item) {
		// 删除已授权的菜单信息
		item.setAction(DataAction.Delete.getAction());
		sysDao.saveSysRoleMenu(item);
		String[] ids = item.getIds().split(",");
		for (String id : ids) {
			item.setAction(DataAction.Create.getAction());
			item.setId(id);
			sysDao.saveSysRoleMenu(item);
		}

	}

	// endregion SysRole Methods

	// region SysUser Methods

	@Override
	public List<SysUser> searchUser(SysUser item) {
		List<SysUser> sysUsers = sysDao.searchUser(item);
		return sysUsers;
	}

	@Override
	public void saveUser(SysUser item) {
		String userid = item.getUserid();
		String userpwd = StringUtils.getMD5(userid);
		item.setUserpwd(userpwd);
		sysDao.saveUser(item);
		UserRole deluserrole = new UserRole();
		deluserrole.setUserid(userid);
		deluserrole.setAction(DataAction.Delete.getAction());
		sysDao.saveUserRole(deluserrole);
		if (item.getAction() != DataAction.Delete.getAction()) {
			String[] roleids = item.getRoleids().split(",");
			for (String roleid : roleids) {
				UserRole userrole = new UserRole();
				userrole.setUserid(userid);
				userrole.setRoleid(roleid);
				userrole.setAction(DataAction.Create.getAction());
				sysDao.saveUserRole(userrole);
			}
			item.getRtv().setMsg("保存成功！");
		} else {
			item.getRtv().setMsg("删除成功！");
		}
	}

	@Override
	public void batchDelUser(SysUser item) {
		String ids = item.getIds();
		String[] userids = ids.split(",");
		for (String userid : userids) {
			UserRole deluserrole = new UserRole();
			deluserrole.setUserid(userid);
			deluserrole.setAction(item.getAction());
			sysDao.saveUserRole(deluserrole);

			SysUser delsysuser = new SysUser();
			delsysuser.setUserid(userid);
			delsysuser.setAction(item.getAction());
			sysDao.saveUser(delsysuser);
		}
		item.getRtv().setMsg("批量删除成功！");
	}

	@Override
	public void userLogin(HttpSession session, SysUser item) {
		// 加密后的密码
		String password = StringUtils.getMD5(item.getUserpwd());
		SysUser sysuser = sysDao.getSysUser(item);
		if (sysuser != null) {
			if (password.equals(sysuser.getUserpwd())) {
				// 将用户的信息保存到session中
				session.setAttribute("user", sysuser);
				session.setMaxInactiveInterval(-1);
				item.getRtv().setData(JSONObject.fromObject(sysuser).toString());
			} else {
				item.getRtv().setSuccess(false);
				item.getRtv().setMsg("请输入正确密码！");
			}
		} else {
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg("请输入正确的用户名！");
		}
	}

	@Override
	public void resetUserPassword(SysUser item) {
		String password = StringUtils.getMD5(item.getUserid());
		item.setUserpwd(password);
		item.setAction(DataAction.Reset.getAction());
		sysDao.saveUser(item);
		item.getRtv().setMsg("重置成功！");
	}

	// endregion SysUser Methods

	// region SetSelect Methods

	@Override
	public List<SetSelect> getSetSelect(SetSelect item) {
		List<SetSelect> items = sysDao.getSetSelect(item);
		return items;
	}

	// endregion SetSelect Methods

}
