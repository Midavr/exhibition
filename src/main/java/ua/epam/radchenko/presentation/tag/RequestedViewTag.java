package ua.epam.radchenko.presentation.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Business logic of my own custom tag.
 * Sends to jsp URI of requested view.
 */
public class RequestedViewTag extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        HttpServletRequest request = getRequest();
        sendViewUriToJsp(request);
    }

    /**
     * Get request from jspContext
     *
     * @return httpRequest
     */
    private HttpServletRequest getRequest() {
        PageContext pageContext = (PageContext) getJspContext();
        return (HttpServletRequest) pageContext.getRequest();
    }

    /**
     * Writes URI of current view to out stream of jspContext
     *
     * @param request HttpServletRequest
     * @throws IOException IOException
     */
    private void sendViewUriToJsp(HttpServletRequest request)
            throws IOException {
        JspWriter out = getJspContext().getOut();
        out.print(request.getRequestURI());
    }
}
