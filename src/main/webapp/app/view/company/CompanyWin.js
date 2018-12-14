/*
 * 窗口视图 - 角色管理
 */

Ext.define("App.view.company.CompanyWin", {
  extend : "Ext.window.Window",
  xtype : "companywin",

  requires : [ "App.view.company.CompanyModel", "App.view.company.CompanyController" ],
  viewModel : {
	  type : "company"
  },

  controller : "company",
  action : '',

  title : "新增公司",
  width : 600,
  height : 140,
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
	      reference : "companyForm",
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
	          name : "companyid"
	        }, {
	          id : 'action',
	          xtype : "hiddenfield",
	          name : "action",
	          value : this.action
	        } ]
	      }, {
	        xtype : "container",
	        layout : {
	          type : "hbox",
	          align : "stretch"
	        },
	        items : [ {
	          xtype : "textfield",
	          name : "companyname",
	          fieldLabel : "公司名称"
	        }, {
	        	
	        	xtype : "combobox",
						fieldLabel : "状态",
						emptyText : "选择用户状态",
						name : "companystatus",
						mode : 'local',
						queryMode : 'local',
						store : tools.ComboStore('CompanyStatus'),
						displayField : "name",
						valueField : "id",
						editable : false
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
