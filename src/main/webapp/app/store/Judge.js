Ext.define("App.store.Judge", {
  extend : "Ext.data.Store",
  alias : "store.judge",

  model : "App.model.Judge",
  proxy : {
    type : "ajax",
    url : "sys/getPmtJudge.do", // 模拟后台访问地址
    reader : {
	    type : "json"
    // rootProperty : "data"
    }
  }
});