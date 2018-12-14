/*
 * 视图 - 角色管理
 */

Ext.define("App.view.col.Col", {
  extend : "Ext.grid.Panel",
  xtype : "col",
  id : "colgrid",

  requires : [ "App.view.col.ColModel", "App.view.col.ColController", "App.model.Pmt" ],

  viewModel : {
	  type : "col"
  },

  controller : "col",

  bind : {
	  store : "{col}"
  },

  initComponent : function() {
	  var columns = tools.GetColumn('sys/getColumn.do?columnCode=C_COLUMN');
	  Ext.apply(this, {
	    selType : "checkboxmodel",
	    title : "表头管理",
	    border : true,
	    columns : columns,
	    tbar : [ {
	      xtype : "textfield",
	      id : 'searchcolumncode',
	      maxWidth : 180,
	      fieldLabel : "列编码",
	      labelWidth : 50
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
	      text : "批量删除",
	      glyph : 0xf00d,
	      handler : "batchDel"
	    } ],
	    bbar : {
	      xtype : "pagingtoolbar",
	      bind : {
		      store : "{col}"
	      },
	      displayInfo : true
	    }
	  });
	  this.callParent(arguments);
  }
});
