/*
 * 视图模型 - 角色管理
 */

Ext.define("App.view.col.ColModel", {
	extend : "Ext.app.ViewModel",
	alias : "viewmodel.col",
	requires : [ "App.store.Col", "App.store.Judge" ],

	data : {

	},

	stores : {
		col : {
			type : "col",
			pageSize : climber.DEFAULT_LIMIT,
			autoLoad : true
		},
		judge : {
			type : "judge",
			autoLoad : true
		}
	}

});
