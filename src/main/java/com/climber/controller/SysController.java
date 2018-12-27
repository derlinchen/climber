package com.climber.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.climber.mongobean.Student;
import com.climber.service.JedisService;
import com.climber.service.MongoService;
import com.climber.service.SysService;
import com.climber.utils.LoggerUtils;
import com.climber.utils.StringUtils;
import com.climber.utils.ToolUtils;

@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {

	private static Logger logger = LoggerUtils.getLogger(SysController.class);

	@Resource
	private SysService sysService;

	// region SysMenu Methods

	@RequestMapping("/getMenu")
	public void getMenu(HttpServletRequest request, HttpServletResponse response, Model model, PrintWriter out, UserRole item) {
		try {
			TreePanel tp = sysService.getSysMenu(item);
			JSONObject json = JSONObject.fromObject(tp);
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/searchMenu")
	public void searchMenu(HttpServletRequest request, HttpServletResponse response, SysMenu item, PrintWriter out) {
		try {
			String search = "";
			String text = item.getText();
			if (!StringUtils.isEmpty(text)) {
				search += ToolUtils.appendAnd(search) + " a.text like '%" + text + "%' ";
			}
			item.initSearchParam(search);
			TreePanel tp = sysService.searchMenu(item);
			JSONObject json = JSONObject.fromObject(tp);
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}

	}

	@RequestMapping("/getAuthMenu")
	public void getAuthMenu(HttpServletRequest request, HttpServletResponse response, SysRoleMenu item, PrintWriter out) {
		try {
			AuthTree tp = sysService.getAuthMenu(item);
			JSONObject json = JSONObject.fromObject(tp);
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/saveMenu")
	public void saveMenu(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysMenu item) {
		try {
			sysService.saveSysMenu(item);
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/delMenu")
	public void delMenu(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysMenu item) {
		try {
			sysService.saveSysMenu(item);
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	// endregion SysMenu Methods

	// region SysColumn Methods

	@RequestMapping("/getColumn")
	public void getColumn(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysColumn item) {
		try {
			List<SysColumn> sysColumns = sysService.getColumn(item);
			JSONArray json = JSONArray.fromObject(sysColumns);
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}

	}

	@RequestMapping("/searchColumn")
	public void searchColumn(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysColumn item) {
		try {
			String search = "";
			String columncode = item.getColumnCode();
			if (!StringUtils.isEmpty(columncode)) {
				search += ToolUtils.appendAnd(search) + " a.columncode = '" + columncode + "' ";
			}
			item.initSearchParam(search);
			List<SysColumn> lists = sysService.searchColumn(item);
			out.print(this.OutLists(lists, item.getTotal()));
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/saveColumn")
	public void saveColumn(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysColumn item) {
		try {
			sysService.saveColumn(item);
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/batchDelColumn")
	public void batchDelColumn(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysColumn item) {
		try {
			sysService.batchDelColumn(item);
			JSONObject json = JSONObject.fromObject(item.getRtv());
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	// endregion SysColumn Methods

	// region Pmt Methods

	@RequestMapping("/getPmtJudge")
	public void getPmtJudge(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
		try {
			List<PmtJudge> pmtJudges = sysService.getPmtJudge();
			JSONArray json = JSONArray.fromObject(pmtJudges);
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// endregion Pmt Methods

	// region SysCompany Methods

	@RequestMapping("/searchCompany")
	public void searchCompany(HttpServletRequest request, HttpServletResponse response, SysCompany item, PrintWriter out) {
		try {
			String search = "";
			String companyname = item.getCompanyname();
			if (!StringUtils.isEmpty(companyname)) {
				search += ToolUtils.appendAnd(search) + " a.companyname like '%" + companyname + "%' ";
			}
			item.initSearchParam(search);
			List<SysCompany> sysCompanys = sysService.searchCompany(item);
			out.print(this.OutLists(sysCompanys, item.getTotal()));
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/saveCompany")
	public void saveCompany(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysCompany item) {
		try {
			sysService.saveCompany(item);
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/batchDelCompany")
	public void batchDelCompany(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysCompany item) {
		try {
			sysService.batchDelCompany(item);
			JSONObject json = JSONObject.fromObject(item.getRtv());
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	// endregion SysCompany Methods

	// region SysRole Methods

	@RequestMapping("/searchRole")
	public void searchRole(HttpServletRequest request, HttpServletResponse response, SysRole item, PrintWriter out) {
		try {
			String search = "";
			String rolename = item.getRolename();
			if (!StringUtils.isEmpty(rolename)) {
				search += ToolUtils.appendAnd(search) + " a.rolename like '%" + rolename + "%' ";
			}
			item.initSearchParam(search);
			List<SysRole> sysRoles = sysService.searchRole(item);
			out.print(this.OutLists(sysRoles, item.getTotal()));
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/saveRole")
	public void saveRole(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysRole item) {
		try {
			sysService.saveRole(item);
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/batchDelRole")
	public void batchDelRole(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysRole item) {
		try {
			sysService.batchDelRole(item);
			JSONObject json = JSONObject.fromObject(item.getRtv());
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/authRoleMenu")
	public void authRoleMenu(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysRoleMenu item) {
		try {
			sysService.authRoleMenu(item);
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	// endregion SysRole Methods

	// region SysUser Methods

	@RequestMapping("/searchUser")
	public void searchUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysUser item) {
		try {
			String search = "";
			String username = item.getUsername();
			String userstatus = item.getUserstatus();
			if (!StringUtils.isEmpty(username)) {
				search += ToolUtils.appendAnd(search) + " a.username like '%" + username + "%' ";
			}

			if (!StringUtils.isEmpty(userstatus)) {
				search += ToolUtils.appendAnd(search) + " a.userstatus = '" + userstatus + "' ";
			}

			item.initSearchParam(search);
			List<SysUser> lists = sysService.searchUser(item);
			out.print(this.OutLists(lists, item.getTotal()));
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/saveUser")
	public void saveUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysUser item) {
		try {
			sysService.saveUser(item);
			JSONObject json = JSONObject.fromObject(item.getRtv());
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/batchDelUser")
	public void batchDelUser(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysUser item) {
		try {
			sysService.batchDelUser(item);
			JSONObject json = JSONObject.fromObject(item.getRtv());
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/userLogin")
	public void userLogin(HttpServletRequest request, HttpSession session, PrintWriter out, SysUser item) {
		try {
			sysService.userLogin(session, item);
			JSONObject json = JSONObject.fromObject(item.getRtv());
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	@RequestMapping("/resetUserPassword")
	public void resetUserPassword(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SysUser item) {
		try {
			System.out.println(request.getParameter("userid"));
			 sysService.resetUserPassword(item);
			 JSONObject json = JSONObject.fromObject(item.getRtv());
			 out.print(json.toString());
			 out.flush();
			 out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	// endregion SysUser Methods

	// region SetSelect Methods

	@RequestMapping("/getSetSelect")
	public void getSetSelect(HttpServletRequest request, HttpServletResponse response, PrintWriter out, SetSelect item) {
		try {
			List<SetSelect> lists = sysService.getSetSelect(item);
			JSONArray json = JSONArray.fromObject(lists);
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e);
			String msg = e.getCause().getMessage();
			item.getRtv().setSuccess(false);
			item.getRtv().setMsg(msg);
		}
	}

	// endregion SetSelect Methods

	// region JedisPool Methods

//	@Autowired
//	private JedisPool jedisPool;
	
	@Resource
	private JedisService jedisService;

	@RequestMapping("/redis")
	public void set() {
		jedisService.set("key2", "hello jedis one");
		jedisService.append("key2", "ssss");
		System.out.println(jedisService.get("key2"));
		jedisService.del("key2");
		System.out.println(jedisService.exists("key2"));
	}

	// endregion JedisPool Methods
	
	// region RabbitMQ Methods
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping(value="rabbit",method=RequestMethod.GET)
	public void test(){
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		map.put("name", "pig");
		//根据key发送到对应的队列
		rabbitTemplate.convertAndSend("queuekey", map);
		
		map.put("id", "2");
		map.put("name", "cat");
		//根据key发送到对应的队列
		rabbitTemplate.convertAndSend("queuekey", map);
		
	}
	
	// endregion RabbitMQ Methods
	
	// region MongoDB Methods
	
	@Resource
	private MongoService mongoService;
	
	@RequestMapping("/mongo")
	public void setmongo() {
		Student std = new Student("ss", "sdd");
		mongoService.add(std);
		Student std1 = new Student("ss", "sdd");
		List<Student> stds = mongoService.findByCondition(std1, Student.class, false);
		System.out.println(stds);
	}
	
	// endregion MongoDB Methods

}
