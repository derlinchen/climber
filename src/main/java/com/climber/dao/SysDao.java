package com.climber.dao;

import java.util.List;

import com.climber.bean.AuthMenu;
import com.climber.bean.PmtJudge;
import com.climber.bean.SetSelect;
import com.climber.bean.SysColumn;
import com.climber.bean.SysCompany;
import com.climber.bean.SysMenu;
import com.climber.bean.SysRole;
import com.climber.bean.SysRoleMenu;
import com.climber.bean.SysUser;
import com.climber.bean.UserRole;

public interface SysDao {

	// region SysMenu Methods

	public List<SysMenu> getSysMenu(UserRole item);
	
	public List<SysMenu> getAllSysMenu();

	public List<SysMenu> searchSysMenu(SysMenu item);

	public List<AuthMenu> getAuthMenu(SysRoleMenu item);

	public void saveSysMenu(SysMenu item);

	// endregion SysMenu Methods

	// region SysColumn Methods

	public List<SysColumn> getColumn(SysColumn item);

	public List<SysColumn> searchColumn(SysColumn item);

	public void saveColumn(SysColumn item);

	// endregion SysColumn Methods

	// region Pmt Methods

	public List<PmtJudge> getPmtJudge();

	// endregion Pmt Methods

	// region SysCompany Methods

	public List<SysCompany> searchCompany(SysCompany item);

	public void saveCompany(SysCompany item);

	// endregion SysCompany Methods

	// region SysRole Methods
	
	public List<SysRole> searchRole(SysRole item);

	public void saveRole(SysRole item);

	public void saveSysRoleMenu(SysRoleMenu item);

	// endregion SysRole Methods

	// region SysUser Methods

	public List<SysUser> searchUser(SysUser item);

	public void saveUser(SysUser item);

	public void saveUserRole(UserRole item);

	public SysUser getSysUser(SysUser item);

	// endregion SysUser Methods
	
	// region SetSelect Methods
	
	public List<SetSelect> getSetSelect(SetSelect item);

	// endregion SetSelect Methods

}
