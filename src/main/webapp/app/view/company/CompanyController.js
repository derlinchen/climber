/*
 * 视图控制器 - 角色管理
 */

Ext.define("App.view.company.CompanyController", {
  extend : "Ext.app.ViewController",
  alias : "controller.company",

  uses : [ "App.view.company.CompanyWin" ],

  init : function() {
	  this.st = Ext.getCmp("companyGrid").getStore(); // 通过Component获取Store
  },

  // 搜索
  search : function() {
	  var companyname = Ext.getCmp('searchcompanyname').getValue();
	  var store = this.getViewModel().getStore("company");
	  store.on('beforeload', function(store, options) {
		  Ext.apply(store.proxy.extraParams, {
			  companyname : companyname
		  });
	  });
	  store.loadPage(1);
  },

  // 新增
  add : function() {
	  var win = Ext.create("App.view.company.CompanyWin", {
		  action : climber.ACTION_NEW
	  });
	  win.show();
  },

  // 编辑
  edit : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var win = Ext.create("App.view.company.CompanyWin", {
		  title : "编辑公司"
	  });
	  win.down("form").loadRecord(rec);
	  Ext.getCmp('action').setValue(climber.ACTION_EDIT);
	  win.show();
  },

  // 删除
  del : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var companyname = rec.data.companyname;
	  var companyid = rec.data.companyid;
	  var msg = "确认删除菜单：" + companyname + " ？";
	  Ext.Msg.confirm("确认", msg, function(res) {
		  if (res == "yes") {
			  Ext.Ajax.request({
			    url : 'sys/saveCompany.do',
			    params : {
			      companyid : companyid,
			      action : climber.ACTION_DEL
			    },
			    success : function(response) {
				    Ext.Msg.alert('系统提示', '删除成功！');
				    Ext.getCmp("companyGrid").getStore().load();
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
	  var grid = Ext.getCmp("companyGrid");
	  if (grid.getSelectionModel().hasSelection()) {
		  var st = grid.getStore();
		  var recs = grid.getSelectionModel().getSelection();
		  var ids = "";
		  for (var i = 0; i < recs.length; i++) {
			  ids += ',' + recs[i].data.companyid;
		  }
		  ids = ids.substring(1);
		  Ext.Msg.confirm("确认", "确认删除已选择公司？", function(res) {
			  if (res == "yes") {
				  Ext.Ajax.request({
				    url : 'sys/batchDelCompany.do',
				    params : {
				      ids : ids,
				      action : climber.ACTION_DEL
				    },
				    success : function(response) {
					    var data = Ext.decode(response.responseText);
					    Ext.getCmp("companyGrid").getStore().load();
					    Ext.Msg.alert('系统提示', data.msg);
				    },
				    failure : function(form, action) {
					    Ext.Msg.alert('系统提示', '删除失败！');
				    }
				  });
			  }
		  });
	  } else {
		  Ext.Msg.alert("信息", "请选择要删除的公司！");
	  }
  },

  // 保存
  save : function(btn) {
	  var fr = this.lookupReference("companyForm").getForm();
	  if (fr.isValid()) {
		  fr.submit({
		    clientValidation : true,
		    url : 'sys/saveCompany.do',
		    async : false,
		    method : 'POST',
		    success : function(form, action) {
			    btn.up("companywin").close();
			    Ext.getCmp("companyGrid").getStore().load();
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
	  btn.up("companywin").close();
  }
});