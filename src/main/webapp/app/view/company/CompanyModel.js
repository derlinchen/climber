/*
 * 视图模型 - 角色管理
 */

Ext.define("App.view.company.CompanyModel", {
  extend : "Ext.app.ViewModel",
  alias : "viewmodel.company",
  requires : [ "App.store.Company" ],

  data : {},

  stores : {
    company : {
      type : "company",
      pageSize : climber.DEFAULT_LIMIT,
      autoLoad : true
    }
  }
});
