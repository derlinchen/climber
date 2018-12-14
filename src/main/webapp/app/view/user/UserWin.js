/*
 * 窗口视图 - 用户管理
 */

Ext.define("App.view.user.UserWin", {
	extend : "Ext.window.Window",
	xtype : "userwin",
	requires : [ "App.view.user.UserModel", "App.view.user.UserController" ],
	viewModel : {
		type : "user"
	},
	controller : "user",
	action : '',
	title : "新增用户",
	width : 600,
	height : 230,
	resizable : false,
	constrain : true,
	modal : true,
	editable : true,
	/*
	 * tools: [{ type: "refresh", tooltip: "刷新数据" }],
	 */

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				id : 'userForm',
				xtype : "form",
				reference : "userForm",
				margin : 5,
				defaults : {
					anchor : "100%"
				},
				fieldDefaults : {
					flex : 1,
					margin : 5,
					labelWidth : 60,
					allowBlank : false
				},
				items : [ {
					xtype : "container",
					layout : {
						type : "hbox",
						align : "stretch"
					},
					items : [ {
						xtype : "hiddenfield",
						id : 'action',
						name : "action",
						value : this.action
					}, {
						xtype : "hiddenfield",
						name : "rolenames",
						id : 'rolenames'
					}, {
						xtype : "textfield",
						name : "userid",
						fieldLabel : "用户编号",
						editable : this.editable
					}, {
						xtype : "textfield",
						name : "username",
						fieldLabel : "用户名称"
					} ]
				}, {
					xtype : "container",
					layout : {
						type : "hbox",
						align : "top"
					},
					items : [ {
						xtype : "combobox",
						fieldLabel : "用户角色",
						emptyText : "选择角色",
						name : "roleids",
						id : 'roleids',
						mode : 'local',
						queryMode : 'local',
						multiSelect : true,
						store : tools.ComboStore('Role'),
						displayField : "name",
						valueField : "id",
						editable : false
					}, {
						xtype : "combobox",
						fieldLabel : "状态",
						emptyText : "选择用户状态",
						name : "userstatus",
						mode : 'local',
						queryMode : 'local',
						store : tools.ComboStore('UserStatus'),
						displayField : "name",
						valueField : "id",
						editable : false
					} ]
				}, {
					xtype : "container",
					layout : {
						type : "hbox",
						align : "top"
					},
					items : [ {
						xtype : "textarea",
						name : "userdesc",
						fieldLabel : "备注",
						grow : false,
						allowBlank : true
					} ]
				} ]
			} ],
			buttonAlign : "center",
			buttons : [ {
				text : "保存",
				handler : "save"
			}, {
				text : "关闭",
				handler : "cancel"
			} ]
		});
		this.callParent(arguments);
	}
});
