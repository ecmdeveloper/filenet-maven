#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configuration;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

public class PropertyBuilder {

	private final JSONObject property = new JSONObject();

	private PropertyBuilder(String type) {
		property.put("type", type);
		property.put("isLocalized", false);
		
		if ( "choicelist".equals(type) ) {
			property.put("options", new JSONArray() );
		}
	}
	
	public static PropertyBuilder newStringProperty() {
		return new PropertyBuilder("string");
	}
	
	public static PropertyBuilder newDateProperty() {
		return new PropertyBuilder("date");
	}

	public static PropertyBuilder newChoiceProperty() {
		return new PropertyBuilder("choicelist");
	}

	public PropertyBuilder id(String id) {
		property.put("id", id);
		return this;
	}
	
	public PropertyBuilder required(boolean required) {
		property.put("required", required);
		return this;
	}
	
	public PropertyBuilder title(String title) {
		property.put("title", title);
		return this;
	}
	
	public PropertyBuilder option(String id, String title) {
	
		JSONObject option = new JSONObject();
		option.put("id", id);
		option.put("title", title);
		
		((JSONArray)property.get("options")).add(option);
		
		return this;
	}
	
	JSONObject get() {
		return property;
	}
}
