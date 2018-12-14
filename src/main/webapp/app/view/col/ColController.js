/*
 * 视图控制器 - 角色管理
 */

Ext.define("App.view.col.ColController", {
  extend : "Ext.app.ViewController",
  alias : "controller.col",

  uses : [ "App.view.col.ColWin" ],

  init : function() {
	  this.st = Ext.getCmp("colgrid").getStore(); // 通过Component获取Store
  },

  // 搜索
  search : function() {
	  var columncode = Ext.getCmp('searchcolumncode').getValue();
	  var store = this.getViewModel().getStore("col");
	  store.on('beforeload', function(store, options) {
		  Ext.apply(store.proxy.extraParams, {
			  columnCode : columncode
		  });
	  });
	  store.loadPage(1);
  },

  // 新增
  add : function() {
	  var win = Ext.create("App.view.col.ColWin", {
		  action : climber.ACTION_NEW
	  });
	  win.show();
  },

  // 编辑
  edit : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var win = Ext.create("App.view.col.ColWin", {
		  title : "编辑菜单 - " + rec.data.text
	  });
	  win.down("form").loadRecord(rec);
	  Ext.getCmp('action').setValue(climber.ACTION_EDIT);
	  win.show();
  },

  // 删除
  del : function(grid, rowIndex, colIndex) {
	  var rec = grid.getStore().getAt(rowIndex);
	  var name = rec.data.text;
	  var columnID = rec.data.columnID;
	  var msg = "确认删除菜单：" + rec.data.text + " ？";
	  Ext.Msg.confirm("确认", msg, function(res) {
		  if (res == "yes") {
			  Ext.Ajax.request({
			    url : 'sys/saveColumn.do',
			    params : {
			      columnID : columnID,
			      action : climber.ACTION_DEL
			    },
			    success : function(response) {
				    Ext.Msg.alert('系统提示', '删除成功！');
				    Ext.getCmp("colgrid").getStore().load();
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
	  var grid = Ext.getCmp("colgrid");
	  if (grid.getSelectionModel().hasSelection()) {
		  var st = grid.getStore();
		  var recs = grid.getSelectionModel().getSelection();
		  var ids = "";
		  for (var i = 0; i < recs.length; i++) {
			  ids += ',' + recs[i].data.columnID;
		  }
		  ids = ids.substring(1);
		  Ext.Msg.confirm("确认", "确认删除选择的表头", function(res) {
			  if (res == "yes") {
				  Ext.Ajax.request({
				    url : 'sys/batchDelColumn.do',
				    params : {
				      ids : ids,
				      action : climber.ACTION_DEL
				    },
				    success : function(response) {
					    var data = Ext.decode(response.responseText);
					    Ext.getCmp("colgrid").getStore().load();
					    Ext.Msg.alert('系统提示', data.msg);
				    },
				    failure : function(form, action) {
					    Ext.Msg.alert('系统提示', '删除失败！');
				    }
				  });
			  }
		  });
	  } else {
		  Ext.Msg.alert("信息", "请选择要删除的表头！");
	  }
  },

  // 保存
  save : function(btn) {
	  var fr = Ext.getCmp("colForm").getForm();
	  if (fr.isValid()) {
		  fr.submit({
		    clientValidation : true,
		    url : 'sys/saveColumn.do',
		    async : false,
		    method : 'POST',
		    success : function(form, action) {
			    btn.up("colwin").close();
			    Ext.getCmp("colgrid").getStore().load();
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
	  btn.up("colwin").close();
  }

});