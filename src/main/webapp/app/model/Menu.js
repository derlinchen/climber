/* 
 * 数据模型 - 角色
 */

Ext.define("App.model.Menu", {
  extend : "Ext.data.Model",
  fields : [ {
	  name : "id"
  }, {
	  name : "pid"
  }, {
	  name : "text"
  }, {
	  name : "module"
  }, {
	  name : "url"
  }, {
	  name : "expanded"
  }, {
	  name : "cls"
  } ]
});