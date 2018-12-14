/*
 * 视图 - 角色管理
 */

Ext.define("App.view.role.Role", {
  extend : "Ext.grid.Panel",
  xtype : "role",
  id : "roleGrid",

  requires : [ "App.view.role.RoleModel", "App.view.role.RoleController" ],

  viewModel : {
	  type : "role"
  },

  controller : "role",

  bind : {
	  store : "{role}"
  },

  initComponent : function() {
	  var columns = tools.GetColumn('sys/getColumn.do?columnCode=C_ROLE');
	  Ext.apply(this, {
	    selType : "checkboxmodel",
	    title : "角色管理",
	    border : true,
	    columns : columns,
	    tbar : [ {
	      xtype : "textfield",
	      id : 'searchrolename',
	      maxWidth : 205,
	      fieldLabel : "角色名称",
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
	    }, {
	      xtype : "button",
	      text : "授权",
	      glyph : 0xf023,
	      handler : "auth"
	    }, {
	      xtype : "button",
	      text : "批量删除",
	      glyph : 0xf00d,
	      handler : "batchDel"
	    } ],
	    bbar : {
	      xtype : "pagingtoolbar",
	      bind : {
		      store : "{role}"
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
