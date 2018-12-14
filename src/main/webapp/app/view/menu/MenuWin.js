/*
 * 窗口视图 - 角色管理
 */

Ext.define("App.view.menu.MenuWin", {
  extend : "Ext.window.Window",
  xtype : "menuwin",

  requires : [ "App.view.menu.MenuModel", "App.view.menu.MenuController" ],
  viewModel : {
	  type : "menu"
  },

  controller : "menu",
  pid : '',
  action : '',
  title : "新增角色",
  width : 600,
  height : 160,
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
	      id : "menuForm",
	      reference : "menuForm",
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
	          name : "id",
	          value : ''
	        }, {
	          xtype : "hiddenfield",
	          name : "pid",
	          value : this.pid
	        }, {
	          id : 'action',
	          xtype : "hiddenfield",
	          name : "action",
	          value : this.action
	        }, {
	          xtype : "textfield",
	          name : "text",
	          fieldLabel : "菜单名称"
	        }, {
	          xtype : "textfield",
	          name : "module",
	          fieldLabel : "菜单模块",
	          grow : false,
	          allowBlank : true
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
	          name : "url",
	          fieldLabel : "菜单路径"
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
