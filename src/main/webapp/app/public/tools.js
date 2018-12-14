Ext.namespace('tools');

Ext.apply(Ext.form.field.VTypes, {
  isint2 : function(value) {
	  return /^\d{1,2}$/.test(value);
  },
  isint2Text : '请输入2位整数'
});

tools.GetColumn = function(url) {
	var columns = new Array();
	var rownumberer = {
	  xtype : 'rownumberer',
	  align : 'center',
	  width : '50',
	  text : '序号'
	}
	columns.push(rownumberer);

	Ext.Ajax.request({
	  url : url,
	  scope : this,
	  async : false,
	  success : function(response) {
		  var items = Ext.decode(response.responseText);
		  Ext.each(items, function(item, index) {
			  // item为json数组
			  if (item.xtype == 'stringcolumn') {
				  item.xtype = '';
			  }
			  columns.push(item);
		  });
	  }
	});

	var actioncolumn = {
	  xtype : "actioncolumn",
	  text : "操作",
	  width : 100,
	  align : "center",
	  sortable : false,
	  menuDisabled : true,
	  items : [ {
	    iconCls : "opt-edit",
	    tooltip : "编辑",
	    handler : "edit"
	  }, {
	    iconCls : "opt-delete",
	    tooltip : "删除",
	    handler : "del"
	  } ]
	}

	columns.push(actioncolumn);
	return columns;
}

tools.GetTreeColumn = function(url) {
	var columns = new Array();
	Ext.Ajax.request({
	  url : url,
	  scope : this,
	  async : false,
	  success : function(response) {
		  var items = Ext.decode(response.responseText);
		  Ext.each(items, function(item, index) {
			  if (item.xtype == 'stringcolumn') {
				  item.xtype = '';
			  }
			  columns.push(item);
		  });
	  }
	});

	var actioncolumn = {
	  xtype : "actioncolumn",
	  text : "操作",
	  width : 100,
	  align : "center",
	  sortable : false,
	  menuDisabled : true,
	  items : [ {
	    iconCls : "opt-edit",
	    tooltip : "编辑",
	    handler : "edit"
	  }, {
	    iconCls : "opt-delete",
	    tooltip : "删除",
	    handler : "del"
	  } ]
	}

	columns.push(actioncolumn);
	return columns;
}

tools.ComboStore = function(sqlid){
	
	 var store = Ext.create('Ext.data.Store', {
     autoLoad: true,
     model : "App.model.Pmt",
     proxy: {
         type: 'ajax',
         url: 'sys/getSetSelect.do?sqlid='+sqlid,
         reader: {
             type: 'json',
             rootProperty: 'data'
         }
     }
	 });
	 return store;
}