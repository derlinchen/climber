Ext.define("App.view.code.Code", {
	extend : "Ext.panel.Panel",
	xtype : "code",
	id : "codeform",
	layout : {
		type : 'hbox', // Arrange child items vertically
	},
	initComponent : function() {
		Ext.apply(this, {
			title : '代码生成',
			anchor : '100%',
			height : '100%',
			border:true,
			tbar : [ {
				xtype : "textfield",
				id : 'searchcompanyname',
				maxWidth : 205,
				fieldLabel : "数据库",
				labelWidth : 60
			}, {
				xtype : "button",
				text : "生成bean",
				glyph : 0xf002,
				handler : "search"
			} ],
			items : [ {
				xtype : 'form',
				name : 'message1',
				border : true,
				height : '100%',
				width : '30%',
				items : [ {
					xtype : 'textareafield',
					grow : true,
					name : 'message11',
					height : '100%',
					anchor : '100%'
				} ]
			}, {
				xtype : 'form',
				name : 'message2',
				border : true,
				height : '100%',
				width : '70%',
				items : [ {
					xtype : 'textareafield',
					grow : true,
					name : 'message22',
					height : '100%',
					anchor : '100%'
				} ]
			} ]
		})
		this.callParent(arguments);
	}

})