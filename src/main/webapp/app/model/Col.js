/* 
 * 数据模型 - 角色
 */

Ext.define("App.model.Col", {
  extend : "Ext.data.Model",
  fields : [ {
	  name : "columnID"
  }, {
	  name : "columnCode"
  }, {
	  name : "text"
  }, {
	  name : "module"
  }, {
	  name : "xtype"
  }, {
	  name : "dataIndex"
  }, {
	  name : "flex"
  }, {
	  name : "hidden"
  } ]
});