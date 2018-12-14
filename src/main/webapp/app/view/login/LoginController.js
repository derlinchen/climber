Ext.define("App.view.login.LoginController", {
  extend : "Ext.app.ViewController",
  alias : "controller.login",
  uses : [ "App.view.main.Main" ],

  onSpecialKey : function(field, e) {
	  if (e.getKey() == e.ENTER) {
		  this.doLogin();
	  }
  },

  onLogin : function() {
	  this.doLogin();
  },

  doLogin : function() {
	  var fr = Ext.getCmp("LoginForm").getForm();
	  if (fr.isValid()) {
	  	fr.submit({
		    clientValidation : true,
		    url : 'sys/userLogin.do',
		    async : false,
		    method : 'POST',
		    success : function(form, action) {
		    	climber.user = action.result.data;
		    	Ext.getCmp('loginWindow').destroy();
  			  Ext.create("App.view.main.Main");
		    },
		    failure : function(form, action) {
		    	Ext.Msg.alert('系统提示', action.result.msg);
		    }
		  });
	  }
  },
  
  onReset : function(){
  	 var form = Ext.getCmp("LoginForm").getForm();
  	 form.reset();
  }
});
