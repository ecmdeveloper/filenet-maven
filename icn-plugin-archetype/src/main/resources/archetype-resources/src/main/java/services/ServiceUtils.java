#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 
 */
package ${package}.services;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import com.filenet.api.util.UserContext;
import com.ibm.ecm.json.JSONMessage;
import com.ibm.ecm.json.JSONResponse;

/**
 *
 */
public class ServiceUtils {

	public static UserContext setUserContext(HttpServletRequest request) {
		UserContext originalUserContext = UserContext.get();
		UserContext userContext = new UserContext();
		userContext.setLocale(request.getLocale());
		UserContext.set(userContext);
		return originalUserContext;
	}	
	
	public static JSONResponse getErrorResponse(Exception e, String message) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		
		JSONResponse jsonResults = new JSONResponse();
		JSONMessage errorMessage = new JSONMessage(0,message, e.getLocalizedMessage(), "Please contact the administrtor", exceptionAsString, "More information");
		jsonResults.addErrorMessage(errorMessage);
		return jsonResults;
	}

	
}
