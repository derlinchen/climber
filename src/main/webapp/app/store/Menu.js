/* 
 * 数据存储 - 角色
 */

Ext.define("App.store.Menu", {
  extend : "Ext.data.TreeStore",
  alias : "store.menu",
  model : "App.model.Menu",

  proxy : {
    type : "ajax",
    url : "sys/searchMenu.do", // 模拟后台访问地址
    reader : {
	    type : "json"
    // rootProperty : "data"
    }
  }
});