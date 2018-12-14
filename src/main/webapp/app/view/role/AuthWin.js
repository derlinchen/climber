/*
 * 窗口视图 - 角色管理
 */

Ext.define("App.view.role.AuthWin", {
  extend : "Ext.window.Window",
  xtype : "authwin",

  requires : [ "App.view.role.RoleModel", "App.view.role.RoleController" ],

  viewModel : {
	  type : "role"
  },

  controller : "role",
  roleid : '',
  title : "角色授权",
  width : 280,
  height : 380,
  resizable : false,
  constrain : true,
  modal : true,
  /*
	 * tools: [{ type: "refresh", tooltip: "刷新数据" }],
	 */

  initComponent : function() {
	  var authstore = Ext.create("Ext.data.TreeStore", {
	    model : "Ext.data.TreeModel",
	    proxy : {
	      type : "ajax",
	      reader : "json",
	      extraParams : {
		      roleid : this.roleid
	      },
	      actionMethods : {
		      read : 'POST'
	      },
	      url : "sys/getAuthMenu.do"
	    }
	  });

	  Ext.apply(this, {
	    items : [ {
	      id : 'authtree',
	      xtype : "treepanel",
	      autoScroll : true,
	      width : 270,
	      height : 300,
	      store : authstore,
	      // 不能使用bind,bind有bug,反复打开关闭页面是会出现页面不正常
	      // bind:{
	      // store:"{authmenu}"
	      // },
	      rootVisible : false,
	      renderTo : Ext.getBody(),
	      listeners : {
		      checkchange : function(node, checked, obj) {
			      node.cascadeBy(function(n) {
				      n.set('checked', checked);
			      });
			      checkParent(node);
			      function checkParent(node) {
				      node = node.parentNode;
				      if (!node)
					      return;
				      var checkP = false;
				      node.cascadeBy(function(n) {
					      if (n != node) {
						      if (n.get('checked') == true) {
							      checkP = true;
						      }
					      }
				      });
				      node.set('checked', checkP);
				      checkParent(node);
			      }
		      }
	      }
	    }, {
	      id : 'roleid',
	      xtype : "hiddenfield",
	      name : "roleid",
	      value : this.roleid
	    } ],

	    buttonAlign : "center",
	    buttons : [ {
	      text : "授权",
	      handler : "authsave"
	    }, {
	      text : "取消",
	      handler : "authcancel"
	    } ]
	  });
	  this.callParent(arguments);
  }
});
