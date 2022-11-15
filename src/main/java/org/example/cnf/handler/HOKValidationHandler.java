package org.example.cnf.handler;

import org.apache.axis2.context.MessageContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This handler will be used to filter specific APIs and
 * disable HOK validation if required
 */
public class HOKValidationHandler extends AbstractHandler {

    private static final Log log = LogFactory.getLog(HOKValidationHandler.class);

    public static final String DISABLE_CNF_VALIDATION = "disable_cnf_validation";

    @Override
    public InvocationResponse invoke(MessageContext messageContext) {

        String apiPath = (String) messageContext.getProperty("REST_URL_POSTFIX");

        // Skip  HOK validation for DCR API calls
        if (apiPath.toLowerCase().contains("/register")) {
           messageContext.setProperty(DISABLE_CNF_VALIDATION, Boolean.TRUE);
        }

        return InvocationResponse.CONTINUE;
    }

}
