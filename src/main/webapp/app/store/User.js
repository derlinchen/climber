/* 
 * 数据存储 - 用户
 */

Ext.define("App.store.User", {
  extend : "Ext.data.Store",
  alias : "store.user",

  model : "App.model.User",
  proxy : {
    type : "ajax",
    url : "sys/searchUser.do", // 模拟后台访问地址
    extraParams : {
	    limit : climber.DEFAULT_LIMIT
    },
    reader : {
      type : "json",
      rootProperty : "data"
    }
  }
});