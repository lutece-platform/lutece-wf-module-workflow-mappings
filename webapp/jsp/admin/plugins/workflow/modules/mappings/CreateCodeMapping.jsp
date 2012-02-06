<%@ page errorPage="../../../../ErrorPage.jsp" %>

<jsp:include page="../../../../AdminHeader.jsp" />

<jsp:useBean id="codeMapping" scope="session" class="fr.paris.lutece.plugins.workflow.modules.mappings.web.CodeMappingJspBean" />

<% codeMapping.init( request, codeMapping.RIGHT_MANAGE_MAPPINGS ); %>
<%= codeMapping.getCreateCodeMapping( request ) %>

<%@ include file="../../../../AdminFooter.jsp" %>
