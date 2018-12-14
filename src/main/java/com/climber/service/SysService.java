package com.climber.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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

public interface SysService {

	// region SysMenu Methods

	public TreePanel getSysMenu(UserRole item);

	public TreePanel searchMenu(SysMenu item);

	public AuthTree getAuthMenu(SysRoleMenu item);

	public void saveSysMenu(SysMenu item);

	// endregion SysMenu Methods

	// region SysColumn Methods

	public List<SysColumn> getColumn(SysColumn item);

	public List<SysColumn> searchColumn(SysColumn syscolumn);

	public void saveColumn(SysColumn sysmenu);

	public void batchDelColumn(SysColumn item);

	// endregion SysColumn Methods

	// region Pmt Methods

	public List<PmtJudge> getPmtJudge();

	// endregion Pmt Methods

	// region SysCompany Methods

	public List<SysCompany> searchCompany(SysCompany item);

	public void saveCompany(SysCompany item);

	public void batchDelCompany(SysCompany item);

	// endregion SysCompany Methods

	// region SysRole Methods

	public List<SysRole> searchRole(SysRole item);

	public void saveRole(SysRole item);

	public void batchDelRole(SysRole item);

	public void authRoleMenu(SysRoleMenu item);

	// endregion SysRole Methods

	// region SysUser Methods

	public List<SysUser> searchUser(SysUser item);

	public void saveUser(SysUser item);

	public void batchDelUser(SysUser item);

	public void userLogin(HttpSession session, SysUser item);
	
	public void resetUserPassword(SysUser item);

	// endregion SysUser Methods
	
	// region SetSelect Methods
	
	public List<SetSelect> getSetSelect(SetSelect item);

	// endregion SetSelect Methods

}
