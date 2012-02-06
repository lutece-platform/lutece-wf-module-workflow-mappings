<%@ page errorPage="../../../../ErrorPage.jsp" %>

<jsp:include page="../../../../AdminHeader.jsp" />

<jsp:useBean id="codeMapping" scope="session" class="fr.paris.lutece.plugins.workflow.modules.mappings.web.CodeMappingJspBean" />

<% codeMapping.init( codeMapping, mappings.RIGHT_MANAGE_MAPPINGS ); %>
<%= codeMapping.getError( request ) %>

<%@ include file="../../../../AdminFooter.jsp" %>
