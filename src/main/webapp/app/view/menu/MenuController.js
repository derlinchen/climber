/*
 * 视图控制器 - 角色管理
 */

Ext.define("App.view.menu.MenuController", {
  extend : "Ext.app.ViewController",
  alias : "controller.menu",

  uses : [ "App.view.menu.MenuWin" ],

  init : function() {
	  this.st = Ext.getCmp("menuTree").getStore(); // 通过Component获取Store
  },

  // 搜索
  search : function() {
	  var text = Ext.getCmp('searchtext').getValue();
	  var store = this.getViewModel().getStore("menu");
	  store.on('beforeload', function(store, options) {
		  Ext.apply(store.proxy.extraParams, {
			  text : text
		  });
	  });
	  store.loadPage(1);
  },

  // 新增
  add : function() {
	  var datas = Ext.getCmp('menuTree').getSelectionModel().getSelection();
	  var pid = '';
	  if (datas.length > 0) {
		  pid = datas[0].data.id;
	  }
	  var win = Ext.create("App.view.menu.MenuWin", {
	    pid : pid,
	    action : climber.ACTION_NEW
	  });
	  win.show();
  },

  // 编辑
  edit : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var win = Ext.create("App.view.menu.MenuWin", {
		  title : "编辑菜单 - " + rec.data.text
	  });
	  win.down("form").loadRecord(rec);
	  Ext.getCmp('action').setValue(climber.ACTION_EDIT);
	  win.show();
  },

  menudel : function(menuid) {
	  alert(menuid);
  },

  // 删除
  del : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var name = rec.data.text;
	  var id = rec.data.id;
	  var msg = "确认删除菜单：" + rec.data.text + " ？";
	  Ext.Msg.confirm("确认", msg, function(res) {
		  if (res == "yes") {
			  Ext.Ajax.request({
			    url : 'sys/delMenu.do',
			    params : {
			      id : id,
			      action : climber.ACTION_DEL
			    },
			    success : function(response) {
				    Ext.Msg.alert('系统提示', '删除成功！');
				    Ext.getCmp("nav").getStore().load();
				    Ext.getCmp("menuTree").getStore().load();
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
	  // var grid = Ext.getCmp("roleGrid");
	  // if (grid.getSelectionModel().hasSelection()) {
	  // var st = grid.getStore();
	  // var recs = grid.getSelectionModel().getSelection();
	  // var names = "";
	  // for (var i = 0; i < recs.length; i++) {
	  // names += recs[i].data.roleName + "<br />";
	  // }
	  // Ext.Msg.confirm("确认", "确认删除以下角色？<br />" + names, function(res) {
	  // if (res == "yes") {
	  // st.remove(recs);
	  // }
	  // });
	  // } else {
	  // Ext.Msg.alert("信息", "请选择要删除的角色！");
	  // }
  },

  // 保存
  save : function(btn) {
	  var fr = Ext.getCmp("menuForm").getForm();
	  if (fr.isValid()) {
		  fr.submit({
		    clientValidation : true,
		    url : 'sys/saveMenu.do',
		    async : false,
		    method : 'POST',
		    success : function(form, action) {
			    btn.up("menuwin").close();
			    Ext.getCmp("nav").getStore().load();
			    Ext.getCmp("menuTree").getStore().load();
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
	  btn.up("menuwin").close();
  }

});