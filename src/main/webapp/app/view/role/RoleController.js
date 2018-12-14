/*
 * 视图控制器 - 角色管理
 */

Ext.define("App.view.role.RoleController", {
  extend : "Ext.app.ViewController",
  alias : "controller.role",

  uses : [ "App.view.role.RoleWin", "App.view.role.AuthWin" ],

  init : function() {
	  this.st = Ext.getCmp("roleGrid").getStore(); // 通过Component获取Store
  },

  // 搜索
  search : function() {
	  var rolename = Ext.getCmp('searchrolename').getValue();
	  var store = this.getViewModel().getStore("role");
	  store.on('beforeload', function(store, options) {
		  Ext.apply(store.proxy.extraParams, {
			  rolename : rolename
		  });
	  });
	  store.loadPage(1);
  },

  // 新增
  add : function() {
	  var win = Ext.create("App.view.role.RoleWin", {
		  action : climber.ACTION_NEW
	  });
	  win.show();
  },

  // 编辑
  edit : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var win = Ext.create("App.view.role.RoleWin", {
		  title : "编辑角色"
	  });
	  win.down("form").loadRecord(rec);
	  Ext.getCmp('action').setValue(climber.ACTION_EDIT);
	  win.show();
  },

  // 删除
  del : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var msg = "确认删除角色：" + rec.data.rolename + " ？";
	  var roleid = rec.data.roleid;
	  Ext.Msg.confirm("确认", msg, function(res) {
		  if (res == "yes") {
			  Ext.Ajax.request({
			    url : 'sys/saveRole.do',
			    params : {
			      roleid : roleid,
			      action : climber.ACTION_DEL
			    },
			    success : function(response) {
				    Ext.getCmp("roleGrid").getStore().load();
				    Ext.Msg.alert('系统提示', '删除成功！');
			    },
			    failure : function(form, action) {
				    Ext.Msg.alert('系统提示', '删除失败！');
			    }
			  });
		  }
	  });
  },

  // 批量删除
  batchDel : function() {
	  var grid = Ext.getCmp("roleGrid");
	  if (grid.getSelectionModel().hasSelection()) {
		  var st = grid.getStore();
		  var recs = grid.getSelectionModel().getSelection();
		  var ids = "";
		  for (var i = 0; i < recs.length; i++) {
			  ids += ',' + recs[i].data.roleid;
		  }
		  ids = ids.substring(1);
		  Ext.Msg.confirm("确认", "确认删除选择的角色", function(res) {
			  if (res == "yes") {
			  	 Ext.Ajax.request({
					    url : 'sys/batchDelRole.do',
					    params : {
					      ids : ids,
					      action : climber.ACTION_DEL
					    },
					    success : function(response) {
						    var data = Ext.decode(response.responseText);
						    Ext.getCmp("roleGrid").getStore().load();
						    Ext.Msg.alert('系统提示', data.msg);
					    },
					    failure : function(form, action) {
						    Ext.Msg.alert('系统提示', '删除失败！');
					    }
					  });
			  }
		  });
	  } else {
		  Ext.Msg.alert("信息", "请选择要删除的角色！");
	  }
  },

  // 保存
  save : function(btn) {
	  var fr = Ext.getCmp("roleForm").getForm();
	  if (fr.isValid()) {
		  fr.submit({
		    clientValidation : true,
		    url : 'sys/saveRole.do',
		    async : false,
		    method : 'POST',
		    success : function(form, action) {
			    btn.up("rolewin").close();
			    Ext.getCmp("roleGrid").getStore().load();
			    Ext.Msg.alert('系统提示', '保存成功！');
		    },
		    failure : function(form, action) {
			    Ext.Msg.alert('系统提示', '保存失败！');
		    }
		  });
	  }
  },

  // 取消
  cancel : function(btn) {
	  btn.up("rolewin").close();
  },

  authcancel : function(btn) {
	  btn.up("authwin").close();
  },

  // 授权
  auth : function() {
	  var grid = Ext.getCmp("roleGrid");
	  if (grid.getSelectionModel().hasSelection()) {
		  var data = grid.getSelectionModel().getSelection()[0].data;
		  var roleid = data.roleid;
		  var rolename = data.rolename;
		  var win = Ext.create("App.view.role.AuthWin", {
			  roleid : roleid
		  });
		  win.show();
	  } else {
		  Ext.Msg.alert("信息", "请选择要授权的角色！");
	  }
  },

  authsave : function() {
	  var roleid = Ext.getCmp('roleid').getValue();
	  var treenodes = Ext.getCmp('authtree').getChecked();
	  var ids = '';
	  var i = 0;
	  Ext.Array.forEach(treenodes, function(item) {
		  if (item.id != 'root') {
			  if (i == 0) {
				  ids = item.id;
			  } else {
				  ids = ids + ',' + item.id;
			  }
			  i++;
		  }
	  })
	  
	  console.log(roleid);
	  console.log(ids);
	  if (ids != '') {
		  Ext.Ajax.request({
		    url : 'sys/authRoleMenu.do',
		    params : {
		      roleid : roleid,
		      ids : ids
		    },
		    success : function(response) {
			    Ext.Msg.alert('系统提示', '授权成功！');
		    },
		    failure : function(form, action) {
			    Ext.Msg.alert('系统提示', '授权失败！');
		    }
		  });
	  } else {
		  Ext.Msg.alert('系统提示', '请选择授权菜单！');
	  }
  }

});