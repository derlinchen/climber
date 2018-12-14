/*
 * 视图控制器 - 用户管理
 */

Ext.define("App.view.user.UserController", {
	extend : "Ext.app.ViewController",
	alias : "controller.user",

	uses : [ "App.view.user.UserWin" ],

	// 初始化
	init : function() {
		this.st = Ext.getCmp("userGrid").getStore(); // 通过Component获取Store
	},

	// 搜索
	search : function() {
		var username = Ext.getCmp('searchusername').getValue();
		var userstatus = Ext.getCmp('searchuserstatus').getValue();
		var store = this.getViewModel().getStore("user");
		store.on('beforeload', function(store, options) {
			Ext.apply(store.proxy.extraParams, {
				username : username,
				userstatus : userstatus
			});
		});
		store.loadPage(1);
	},

	// 新增
	add : function() {
		var win = Ext.create("App.view.user.UserWin", {
			action : climber.ACTION_NEW
		});
		win.show();
	},

	reset : function() {
		// alert(1);
		var grid = Ext.getCmp("userGrid");
		if (grid.getSelectionModel().hasSelection()) {
			var data = grid.getSelectionModel().getSelection()[0].data;
			var userid = data.userid;

			Ext.Ajax.request({
				url : 'sys/resetUserPassword.do',
				params : {
					userid : userid
				},
				success : function(response) {
					var data = Ext.decode(response.responseText);
					Ext.Msg.alert('系统提示', data.msg);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统提示', '重置失败！');
				}
			});

		} else {
			Ext.Msg.alert("信息", "请选择需重置密码的用户！");
		}
	},

	// 编辑
	edit : function(grid, rowIndex, colIndex) {
		var rec = grid.getStore().getAt(rowIndex);
		var userstore = tools.ComboStore('UserStatus');
		var win = Ext.create("App.view.user.UserWin", {
			title : "编辑用户 ",
			editable : false
		});
		win.down("form").loadRecord(rec);
		Ext.getCmp("roleids").setValue(rec.data.roleids.split(','), true);
		Ext.getCmp("action").setValue(climber.ACTION_EDIT);
		win.show();
	},

	// 删除
	del : function(grid, rowIndex, colIndex) {
		var rec = grid.getStore().getAt(rowIndex);
		var userid = rec.data.userid;
		var msg = "确认删除用户：" + rec.data.username + " ？";
		Ext.Msg.confirm("确认", msg, function(res) {
			if (res == "yes") {
				Ext.Ajax.request({
					url : 'sys/saveUser.do',
					params : {
						userid : userid,
						action : climber.ACTION_DEL
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
						Ext.getCmp("userGrid").getStore().load();
						Ext.Msg.alert('系统提示', data.msg);
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
		var grid = Ext.getCmp("userGrid");
		if (grid.getSelectionModel().hasSelection()) {
			var st = grid.getStore();
			var recs = grid.getSelectionModel().getSelection();
			var ids = '';
			for (var i = 0; i < recs.length; i++) {
				ids += ',' + recs[i].data.userid;
			}
			ids = ids.substring(1);
			Ext.Msg.confirm("确认", "确认删除所选用户？", function(res) {
				if (res == "yes") {
					Ext.Ajax.request({
						url : 'sys/batchDelUser.do',
						params : {
							ids : ids,
							action : climber.ACTION_DEL
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							Ext.getCmp("userGrid").getStore().load();
							Ext.Msg.alert('系统提示', data.msg);
						},
						failure : function(form, action) {
							Ext.Msg.alert('系统提示', '删除失败！');
						}
					});
				}
			});
		} else {
			Ext.Msg.alert("信息", "请选择要删除的用户！");
		}
	},

	// 保存
	save : function(btn) {
		var fr = Ext.getCmp("userForm").getForm();
		var rolenames = Ext.getCmp("roleids").getDisplayValue();
		Ext.getCmp('rolenames').setValue(rolenames);
		if (fr.isValid()) {
			fr.submit({
				clientValidation : true,
				url : 'sys/saveUser.do',
				async : false,
				method : 'POST',
				success : function(form, action) {
					btn.up("userwin").close();
					Ext.getCmp("userGrid").getStore().load();
					Ext.Msg.alert('系统提示', action.result.msg);
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统提示', action.result.msg);
				}
			});
		}
	},

	// 取消
	cancel : function(btn) {
		btn.up("userwin").close();
	}

});