#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * Copyright (C) 2019 ECM Partners - All Rights Reserved
 */
package ${package}.nls;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Resource manager for the plug-in.
 * 
 * @author Ricardo Belfor
 *
 */
public class PluginResources {

	private static Logger logger = Logger.getLogger(PluginResources.class.getCanonicalName() );
	
	private static final Map<String, ResourceBundle> RESOURCE_MAP = new HashMap<>();
	
	static {
		Collections.synchronizedMap(RESOURCE_MAP);
	}
	  
	private static final String BUNDLE_NAME = "${package}.nls.Resources";

	private PluginResources() {
	}

	/**
	 * Returns the localized value of the key.
	 * 
	 * @param key the key
	 * @param locale the locale
	 * @return the value of the key
	 */
	public static String get(String key, Locale locale ) {

		try {
			ResourceBundle resourceBundle;
			if (!RESOURCE_MAP.containsKey(locale.getLanguage() ) ) {
				resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
				RESOURCE_MAP.put(locale.getLanguage(), resourceBundle);
			} else {
				resourceBundle = RESOURCE_MAP.get(locale.getLanguage() );
			}
			return resourceBundle.getString(key);
		} catch (MissingResourceException mre) {
			logger.log(Level.SEVERE, "Error loading key '" + key + "'", mre);
			return "!" + key + "!";
		}
	}
	
	/**
	 * Returns the localized value of the action/key pair.
	 * 
	 * @param action the action
	 * @param key the key
	 * @param locale the locale
	 * @return the value of the action/key pair
	 */
	public static String get(String action, String key, Locale locale ) {
		return get( action + "." + key, locale);
	}
}
