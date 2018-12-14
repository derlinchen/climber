/*
 * 窗口视图 - 角色管理
 */

Ext.define("App.view.col.ColWin", {
	extend : "Ext.window.Window",
	xtype : "colwin",

	requires : [ "App.view.col.ColModel", "App.view.col.ColController" ],
	viewModel : {
		type : "col"
	},

	controller : "col",
	action : '',
	title : "新增列信息管理",
	width : 600,
	height : 230,
	resizable : false,
	constrain : true,
	modal : true,
	/*
	 * tools: [{ type: "refresh", tooltip: "刷新数据" }],
	 */

	initComponent : function() {
		Ext.apply(this, {
			items : [ {
				xtype : "form",
				id : "colForm",
				reference : "colForm",
				margin : 10,
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
						name : "columnID",
						value : ''
					}, {
						id : 'action',
						xtype : "hiddenfield",
						name : "action",
						value : this.action
					}, {
						xtype : "textfield",
						name : "columnCode",
						fieldLabel : "列编码",
						maxLength : 20
					}, {
						xtype : "textfield",
						name : "text",
						fieldLabel : "列名称",
						grow : false,
						maxLength : 20,
						allowBlank : true
					} ]
				}, {
					xtype : "container",
					layout : {
						type : "hbox",
						align : "stretch"
					},
					items : [ {
						xtype : "combobox",
						fieldLabel : "列类型",
						emptyText : "选择列类型",
						name : "xtype",
						mode : 'local',
						queryMode : 'local',
						store : tools.ComboStore('Xtype'),
						displayField : "name",
						valueField : "id",
						editable : false
					}, {
						xtype : "textfield",
						name : "dataIndex",
						maxLength : 20,
						fieldLabel : "数据下标"
					} ]
				}, {
					xtype : "container",
					layout : {
						type : "hbox",
						align : "stretch"
					},
					items : [ {
						xtype : "textfield",
						name : "flex",
						fieldLabel : "列长度",
						vtype : 'isint2'
					}, {
						xtype : "combobox",
						name : "hidden",
						mode : 'local',
						queryMode : 'local',
						fieldLabel : "是否隐藏",
						emptyText : "选择是否隐藏",
						bind : {
							store : "{judge}"
						},
						displayField : "judgename",
						valueField : "judge",
						editable : false
					} ]
				}, {
					xtype : "container",
					layout : {
						type : "hbox",
						align : "stretch"
					},
					anchor : "50%",
					items : [ {
						xtype : "textfield",
						name : "format",
						allowBlank : true,
						fieldLabel : "格式化"
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
