/*
 * 视图 - 用户管理
 */

Ext.define("App.view.user.User", {
	extend : "Ext.grid.Panel",
	xtype : "user",
	id : "userGrid",

	requires : [ "App.view.user.UserModel", "App.view.user.UserController", "App.model.Pmt" ],
	viewModel : {
		type : "user"
	},
	controller : "user",

	bind : {
		store : "{user}"
	},
	initComponent : function() {
		var columns = tools.GetColumn('sys/getColumn.do?columnCode=C_USER');
		Ext.apply(this, {
			selType : "checkboxmodel",
			title : "用户管理",
			border : true,
			columns : columns,
			tbar : [ {
				xtype : "textfield",
				id : 'searchusername',
				maxWidth : 205,
				fieldLabel : "用户名称",
				labelWidth : 60
			}, {
				xtype : "combobox",
				labelWidth : 40,
				maxWidth : 180,
				fieldLabel : "状态",
				emptyText : "选择用户状态",
				id : 'searchuserstatus',
				store : tools.ComboStore('UserStatus'),
				displayField : "name",
				valueField : "id",
				editable : false
			}, {
				xtype : "button",
				text : "搜索",
				glyph : 0xf002,
				handler : "search"
			}, "->", "->", {
				xtype : "button",
				text : "新增",
				glyph : 0xf067,
				handler : "add"
			}, {
				xtype : "button",
				text : "重置密码",
				glyph : 0xf01e,
				handler : "reset"
			}, {
				xtype : "button",
				text : "批量删除",
				glyph : 0xf00d,
				handler : "batchDel"
			} ],
			bbar : {
				xtype : "pagingtoolbar",
				bind : {
					store : "{user}"
				},
				displayInfo : true
			},
			listeners : {
				itemclick : function(view, record, item, index, e, eOpts) {
				}
			}
		});
		this.callParent(arguments);
	}
});