/* 
 * 数据存储 - 角色
 */

Ext.define("App.store.Company", {
  extend : "Ext.data.Store",
  alias : "store.company",

  model : "App.model.Company",
  proxy : {
    type : "ajax",
    extraParams : {
	    limit : climber.DEFAULT_LIMIT
    },
    url : "sys/searchCompany.do", // 模拟后台访问地址
    reader : {
      type : "json",
      rootProperty : "data",
      totalProperty : 'total'
    }
  }
});