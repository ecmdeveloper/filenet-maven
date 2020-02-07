#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filenet.api.util.UserContext;
import com.ibm.ecm.extension.PluginService;
import com.ibm.ecm.extension.PluginServiceCallbacks;
import com.ibm.ecm.json.JSONResponse;

/**
 * Provides an abstract class that is extended to create a class implementing
 * each service provided by the plug-in. Services are actions, similar to
 * servlets or Struts actions, that perform operations on the IBM Content
 * Navigator server. A service can access content server application programming
 * interfaces (APIs) and Java EE APIs.
 * <p>
 * Services are invoked from the JavaScript functions that are defined for the
 * plug-in by using the <code>ecm.model.Request.invokePluginService</code>
 * function.
 * </p>
 * Follow best practices for servlets when implementing an IBM Content Navigator
 * plug-in service. In particular, always assume multi-threaded use and do not
 * keep unshared information in instance variables.
 */
public class SampleService extends PluginService {

	private static Logger logger = Logger.getLogger(SampleService.class.getCanonicalName() );
	
	/**
	 * Returns the unique identifier for this service.
	 * <p>
	 * <strong>Important:</strong> This identifier is used in URLs so it must
	 * contain only alphanumeric characters.
	 * </p>
	 * 
	 * @return A <code>String</code> that is used to identify the service.
	 */
	public String getId() {
		return "SampleService";
	}

	/**
	 * Returns the name of the IBM Content Navigator service that this service
	 * overrides. If this service does not override an IBM Content Navigator
	 * service, this method returns <code>null</code>.
	 * 
	 * @returns The name of the service.
	 */
	@Deprecated
	public String getOverriddenService() {
		return null;
	}

	/**
	 * Performs the action of this service.
	 * 
	 * @param callbacks
	 *            An instance of the <code>PluginServiceCallbacks</code> class
	 *            that contains several functions that can be used by the
	 *            service. These functions provide access to the plug-in
	 *            configuration and content server APIs.
	 * @param request
	 *            The <code>HttpServletRequest</code> object that provides the
	 *            request. The service can access the invocation parameters from
	 *            the request.
	 * @param response
	 *            The <code>HttpServletResponse</code> object that is generated
	 *            by the service. The service can get the output stream and
	 *            write the response. The response must be in JSON format.
	 * @throws Exception
	 *             For exceptions that occur when the service is running. If the
	 *             logging level is high enough to log errors, information about
	 *             the exception is logged by IBM Content Navigator.
	 */
	public void execute(PluginServiceCallbacks callbacks,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		logger.entering("SampleService", "execute");
		
		UserContext originalUserContext = setUserContext(request);
		
		try {
			doSomething(callbacks, request, response);
			logger.exiting("SampleService", "execute");
		}
		catch (Exception e) {

			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
			JSONResponse jsonResults = ServiceUtils.getErrorResponse(e, "Error executing service");
			callbacks.writeJSONResponse(jsonResults, response);

		} finally {
	        UserContext.set(originalUserContext);
		}
	}
		
	private void doSomething(PluginServiceCallbacks callbacks,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// TODO do service stuff...
	}
	
	private UserContext setUserContext(HttpServletRequest request) {
		UserContext originalUserContext = UserContext.get();
		UserContext userContext = new UserContext();
		userContext.setLocale(request.getLocale());
		UserContext.set(userContext);
		return originalUserContext;
	}	
}
