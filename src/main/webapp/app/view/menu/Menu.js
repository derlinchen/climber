/*
 * 视图 - 角色管理
 */

Ext.define("App.view.menu.Menu", {
  // 使用treepanel时有加载页面，导致页面格式错乱的问题,原因，可能是使用bind导致的
  extend : "Ext.grid.Panel",
  xtype : "menu",
  id : "menuTree",

  requires : [ "App.view.menu.MenuModel", "App.view.menu.MenuController" ],

  viewModel : {
	  type : "menu"
  },

  rootVisible : false,

  controller : "menu",

  bind : {
	  store : "{menu}"
  },

  initComponent : function() {
	  var columns = tools.GetTreeColumn('sys/getColumn.do?columnCode=C_MENU');
	  Ext.apply(this, {
	    selType : "checkboxmodel",
	    title : "菜单管理",
	    border : true,
	    columns : columns,
	    tbar : [ {
	      xtype : "textfield",
	      id : 'searchtext',
	      maxWidth : 205,
	      fieldLabel : "菜单名称",
	      labelWidth : 60
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
	    } ]
	  });
	  this.callParent(arguments);
  }
});
