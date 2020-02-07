define([
		"dojo/_base/declare",
		"dojo/_base/json",
		"dijit/_TemplatedMixin",
		"dijit/_WidgetsInTemplateMixin",
		"ecm/widget/admin/PluginConfigurationPane",
		"dojo/text!./templates/ConfigurationPane.html",
		"dojo/i18n!desktopPluginDojo/nls/Resources",
	],
	function(declare, json, _TemplatedMixin, _WidgetsInTemplateMixin, PluginConfigurationPane, template, resources) {

		return declare("desktopPluginDojo.ConfigurationPane", [ PluginConfigurationPane, _TemplatedMixin, _WidgetsInTemplateMixin], {
		
		templateString: template,
		_resources: resources,
		widgetsInTemplate: true,
	
		load: function(callback) {
			
			this.configuration = this.configurationString && json.fromJson(this.configurationString) || {};
			
			this.webDavPath.set("value", this.configuration.webDavPath || "");
			
			if ( callback) {
				callback();
			}
		},
		
		_onFieldChange: function() {
			this.onSaveNeeded(true);
		},

		save: function(onComplete) {

			this.configuration.webDavPath = this.webDavPath.get("value");
			
			this.configurationString = json.toJson(this.configuration);
			
			if (onComplete) {
				onComplete();
			}
		},

		validate: function() {
			return this.webDavPath.isValid();
		}
	});
});
