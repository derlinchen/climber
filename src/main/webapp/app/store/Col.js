/* 
 * 数据存储 - 角色
 */

Ext.define("App.store.Col", {
  extend : "Ext.data.Store",
  alias : "store.col",
  model : "App.model.Col",
  proxy : {
    type : "ajax",
    url : "sys/searchColumn.do", // 模拟后台访问地址
    extraParams : {
	    limit : climber.DEFAULT_LIMIT
    },
    reader : {
      type : "json",
      rootProperty : "data",
      totalProperty : 'total'
    }
  }
});