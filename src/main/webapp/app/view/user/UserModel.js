/*
 * 视图模型 - 用户管理
 */

Ext.define("App.view.user.UserModel", {
  extend : "Ext.app.ViewModel",
  alias : "viewmodel.user",
  requires : [ "App.store.User" ],

  data : {},

  stores : {
    user : {
      type : "user",
      pageSize : climber.DEFAULT_LIMIT,
      autoLoad : true
    }
  }
});
