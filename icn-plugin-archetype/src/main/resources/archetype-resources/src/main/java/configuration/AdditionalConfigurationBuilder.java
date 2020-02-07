#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configuration;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;

/**
 * 
 *
 */
public class AdditionalConfigurationBuilder {

	private final JSONObject configuration;

	private AdditionalConfigurationBuilder() {
		configuration = new JSONObject();
		configuration.put("ICM_ACTION_COMPATIBLE", true);
		configuration.put("context", null);
		configuration.put("properties", new JSONArray());
		configuration.put("events",new JSONArray());
	}

	public static AdditionalConfigurationBuilder newConfiguration() {
		return new AdditionalConfigurationBuilder();
	}
	
	public AdditionalConfigurationBuilder name(String name) {
		configuration.put("name", name);
		return this;
	}

	public AdditionalConfigurationBuilder description(String description) {
		configuration.put("description", description);
		return this;
	}
	
	public AdditionalConfigurationBuilder defaultValue(String defaultValue) {
		configuration.put("defaultValue", defaultValue);
		return this;
	}
	
	public AdditionalConfigurationBuilder property( PropertyBuilder propertyBuilder) {
		((JSONArray) configuration.get("properties")).add(propertyBuilder.get());
		return this;
	}
	
	public String toString() {
		return configuration.toString();
	}
	
	public JSONObject build() {
		return configuration;
	}
}
