/*
 * Application's entrance
 */

//设置插件加载目录
Ext.Loader.setConfig({
  enabled : true,
  paths : {
	  "App.ux" : "app/ux"
  }
});

Ext.application({
  extend : "App.Application",
  name : "App",
  // autoCreateViewport : 'App.view.main.Main',
  launch : function() {
	  // 获取用户的登录信息，如果用户登录则跳转到主页面，否则返回登录页面
	  // Ext.create("App.view.main.Main");
	  Ext.create("App.view.login.Login");
  }

});