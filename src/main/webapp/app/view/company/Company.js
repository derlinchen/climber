/*
 * 视图 - 角色管理
 */

Ext.define("App.view.company.Company", {
  extend : "Ext.grid.Panel",
  xtype : "company",
  id : "companyGrid",

  requires : [ "App.view.company.CompanyModel", "App.view.company.CompanyController", "App.model.Pmt" ],
  viewModel : {
	  type : "company"
  },
  controller : "company",

  bind : {
	  store : "{company}"
  },
  initComponent : function() {
	  var columns = tools.GetColumn('sys/getColumn.do?columnCode=C_COMPANY');
	  Ext.apply(this, {
	    selType : "checkboxmodel",
	    title : "公司管理",
	    border : true,
	    columns : columns,
	    tbar : [ {
	      xtype : "textfield",
	      id : 'searchcompanyname',
	      maxWidth : 205,
	      fieldLabel : "公司名称",
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
	      text : "批量删除",
	      glyph : 0xf00d,
	      handler : "batchDel"
	    } ],
	    bbar : {
	      xtype : "pagingtoolbar",
	      bind : {
		      store : "{company}"
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