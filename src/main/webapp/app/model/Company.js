/* 
 * 数据模型 - 公司
 */

Ext.define("App.model.Company", {
  extend : "Ext.data.Model",
  fields : [ {
	  name : "companyid"
  }, {
	  name : "companyname"
  }, {
	  name : "companystatus"
  }, {
	  name : "companystatusname"
  } ]
});