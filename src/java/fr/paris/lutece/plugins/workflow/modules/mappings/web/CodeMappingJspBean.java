/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.mappings.web;

import fr.paris.lutece.plugins.workflow.modules.mappings.business.CodeMappingFactory;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMappingFactory;
import fr.paris.lutece.plugins.workflow.modules.mappings.service.CodeMappingService;
import fr.paris.lutece.plugins.workflow.modules.mappings.service.ICodeMappingService;
import fr.paris.lutece.plugins.workflow.modules.mappings.web.component.IMappingTypeComponent;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;
import fr.paris.lutece.portal.web.constants.Messages;
import fr.paris.lutece.util.beanvalidation.BeanValidationUtil;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import javax.validation.ConstraintViolation;


/**
 *
 * MappingJspBean
 *
 */
public class CodeMappingJspBean extends PluginAdminPageJspBean
{
    public static final String RIGHT_MANAGE_MAPPINGS = "WORKFLOW_MAPPINGS_MANAGEMENT";

    // PROPERTIES
    private static final String PROPERTY_MANAGE_MAPPINGS_PAGE_TITLE = "module.workflow.mappings.manageMappings.pageTitle";
    private static final String PROPERTY_CREATE_MAPPING_PAGE_TITLE = "module.workflow.mappings.createMapping.pageTitle";
    private static final String PROPERTY_ERRROR_PAGE_TITLE = "module.workflow.mappings.error.pageTitle";

    // MESSAGES
    private static final String MESSAGE_ERROR_CODE_MAPPING_NOT_FOUND = "module.workflow.mappings.message.error.codeMapping.notFound";
    private static final String MESSAGE_ERROR_MAPPING_TYPE_COMPONENT_NOT_FOUND = "module.workflow.mappings.message.error.mappingTypeComponent.notFound";
    private static final String MESSAGE_ERROR_GENERIC_MESSAGE = "module.workflow.mappings.message.error.genericMessage";
    private static final String MESSAGE_CONFIRM_REMOVE_CODE_MAPPING = "module.workflow.mappings.message.removeCodeMapping";
    private static final String MESSAGE_INVALID_CODE_MAPPING = "module.workflow.mappings.message.invalidCodeMapping";

    // MARKS
    private static final String MARK_LIST_CODE_MAPPINGS = "listCodeMappings";
    private static final String MARK_ERROR_MESSAGE = "errorMessage";
    private static final String MARK_REFERENCE_CODE_FORM = "referenceCodeForm";
    private static final String MARK_LIST_MAPPING_TYPES = "listMappingTypes";
    private static final String MARK_MAPPING_TYPE_KEY = "mappingTypeKey";
    private static final String MARK_LOCALE = "locale";
    private static final String MARK_CODE_MAPPING = "codeMapping";
    private static final String MARK_CODE = "code";
    private static final String MARK_LABEL_CODE = "labelCode";

    // PARAMETERS
    private static final String PARAMETER_CODE = "code";
    private static final String PARAMETER_LABEL_CODE = "labelCode";
    private static final String PARAMETER_MAPPING_TYPE_KEY = "mappingTypeKey";
    private static final String PARAMETER_ERROR_MESSAGE = "errorMessage";
    private static final String PARAMETER_CANCEL = "cancel";
    private static final String PARAMETER_CREATE = "create";
    private static final String PARAMETER_MODIFY = "modify";
    private static final String PARAMETER_ID_CODE = "idCode";

    // TEMPLATES
    private static final String TEMPLATE_MANAGE_CODE_MAPPINGS = "/admin/plugins/workflow/modules/mappings/manage_code_mappings.html";
    private static final String TEMPLATE_CREATE_CODE_MAPPING = "/admin/plugins/workflow/modules/mappings/create_code_mapping.html";
    private static final String TEMPLATE_MODIFY_CODE_MAPPING = "/admin/plugins/workflow/modules/mappings/modify_code_mapping.html";
    private static final String TEMPLATE_ERROR = "/admin/plugins/workflow/modules/mappings/error.html";

    // JSP
    private static final String JSP_CREATE_CODE_MAPPING = "CreateCodeMapping.jsp";
    private static final String JSP_MODIFY_CODE_MAPPING = "ModifyCodeMapping.jsp";
    private static final String JSP_MANAGE_CODE_MAPPINGS = "ManageCodeMappings.jsp";
    private static final String JSP_URL_DO_REMOVE_CODE_MAPPING = "jsp/admin/plugins/workflow/modules/mappings/DoRemoveCodeMapping.jsp";

    // SERVICES
    private ICodeMappingService _codeMappingService = SpringContextService.getBean( CodeMappingService.BEAN_SERVICE );
    private ICodeMappingFactory _codeMappingFactory = SpringContextService.getBean( CodeMappingFactory.BEAN_FACTORY );

    // GET

    /**
     * Get manage code mappings
     * @param request the HTTP request
     * @return the HTML code
     */
    public String getManageCodeMappings( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_MANAGE_MAPPINGS_PAGE_TITLE );

        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_LIST_CODE_MAPPINGS, _codeMappingService.getListCodeMappings(  ) );
        model.put( MARK_LOCALE, getLocale(  ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MANAGE_CODE_MAPPINGS, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Get create a code mapping
     * @param request the HTTP request
     * @return the HTML code
     */
    public String getCreateCodeMapping( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_CREATE_MAPPING_PAGE_TITLE );

        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_LIST_MAPPING_TYPES, _codeMappingFactory.getListMappingTypes( getLocale(  ) ) );

        String strMappingTypeKey = request.getParameter( PARAMETER_MAPPING_TYPE_KEY );

        if ( StringUtils.isNotBlank( strMappingTypeKey ) )
        {
            IMappingTypeComponent mappingTypeComponent = _codeMappingFactory.getMappingTypeComponent( strMappingTypeKey );

            if ( mappingTypeComponent != null )
            {
                model.put( MARK_MAPPING_TYPE_KEY, strMappingTypeKey );
                model.put( MARK_REFERENCE_CODE_FORM,
                    mappingTypeComponent.getCreateCodeMappingHtml( request, getLocale(  ) ) );
            }
        }

        String strCode = request.getParameter( PARAMETER_CODE );

        if ( StringUtils.isNotBlank( strCode ) )
        {
            model.put( MARK_CODE, strCode );
        }

        String strLabelCode = request.getParameter( PARAMETER_LABEL_CODE );

        if ( StringUtils.isNotBlank( strLabelCode ) )
        {
            model.put( MARK_LABEL_CODE, strLabelCode );
        }

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_CODE_MAPPING, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    /**
     * Get modify a code mapping
     * @param request the HTTP request
     * @return the HTML code
     */
    public String getModifyCodeMapping( HttpServletRequest request )
    {
        setPageTitleProperty( PROPERTY_CREATE_MAPPING_PAGE_TITLE );

        String strIdCode = request.getParameter( PARAMETER_ID_CODE );

        if ( StringUtils.isNotBlank( strIdCode ) && StringUtils.isNumeric( strIdCode ) )
        {
            int nIdCode = Integer.parseInt( strIdCode );
            ICodeMapping codeMapping = _codeMappingService.getCodeMapping( nIdCode );

            if ( codeMapping != null )
            {
                Map<String, Object> model = new HashMap<String, Object>(  );
                model.put( MARK_CODE_MAPPING, codeMapping );
                model.put( MARK_LOCALE, getLocale(  ) );

                IMappingTypeComponent mappingTypeComponent = _codeMappingFactory.getMappingTypeComponent( codeMapping.getMappingType(  )
                                                                                                                     .getKey(  ) );

                if ( mappingTypeComponent != null )
                {
                    model.put( MARK_REFERENCE_CODE_FORM,
                        mappingTypeComponent.getModifyCodeMappingHtml( codeMapping, request, getLocale(  ) ) );
                }
                else
                {
                    AppLogService.error( "CodeMappingJspBean - MappingTypeComponent is null for key '" +
                        codeMapping.getMappingType(  ).getKey(  ) + "'" );

                    String strErrorMessage = I18nService.getLocalizedString( MESSAGE_ERROR_MAPPING_TYPE_COMPONENT_NOT_FOUND,
                            request.getLocale(  ) );

                    return getError( request, strErrorMessage );
                }

                HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_CODE_MAPPING, getLocale(  ),
                        model );

                return getAdminPage( template.getHtml(  ) );
            }

            AppLogService.debug( "CodeMappingJspBean - CodeMapping is null for id code '" + strIdCode + "'" );

            String strErrorMessage = I18nService.getLocalizedString( MESSAGE_ERROR_CODE_MAPPING_NOT_FOUND,
                    request.getLocale(  ) );

            return getError( request, strErrorMessage );
        }

        String strErrorMessage = I18nService.getLocalizedString( Messages.MANDATORY_FIELDS, request.getLocale(  ) );

        return getError( request, strErrorMessage );
    }

    /**
     * Get confirm remove code mapping
     * @param request the HTTP request
     * @return the HTML code
     */
    public String getConfirmRemoveCodeMapping( HttpServletRequest request )
    {
        String strIdCode = request.getParameter( PARAMETER_ID_CODE );

        if ( StringUtils.isBlank( strIdCode ) || !StringUtils.isNumeric( strIdCode ) )
        {
            return AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        UrlItem url = new UrlItem( JSP_URL_DO_REMOVE_CODE_MAPPING );
        url.addParameter( PARAMETER_ID_CODE, strIdCode );

        return AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_CODE_MAPPING, url.getUrl(  ),
            AdminMessage.TYPE_CONFIRMATION );
    }

    /**
     * Get error
     * @param request the HTTP request
     * @return the HTML code
     */
    public String getError( HttpServletRequest request )
    {
        String strErrorMessage = request.getParameter( PARAMETER_ERROR_MESSAGE );

        if ( StringUtils.isBlank( strErrorMessage ) )
        {
            strErrorMessage = I18nService.getLocalizedString( MESSAGE_ERROR_GENERIC_MESSAGE, request.getLocale(  ) );
        }

        return getError( request, strErrorMessage );
    }

    /**
     * Get error
     * @param request the HTTP request
     * @param strErrorMessage the error message
     * @return the HTML code
     */
    public String getError( HttpServletRequest request, String strErrorMessage )
    {
        setPageTitleProperty( PROPERTY_ERRROR_PAGE_TITLE );

        Map<String, Object> model = new HashMap<String, Object>(  );
        model.put( MARK_ERROR_MESSAGE, strErrorMessage );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_ERROR, getLocale(  ), model );

        return getAdminPage( template.getHtml(  ) );
    }

    // DO

    /**
     * Do create the code mapping
     * @param request the HTTP request
     * @return the JSP return
     */
    public String doCreateCodeMapping( HttpServletRequest request )
    {
        String strCancel = request.getParameter( PARAMETER_CANCEL );

        if ( StringUtils.isNotBlank( strCancel ) )
        {
            return JSP_MANAGE_CODE_MAPPINGS;
        }

        String strMappingTypeKey = request.getParameter( PARAMETER_MAPPING_TYPE_KEY );

        if ( StringUtils.isBlank( strMappingTypeKey ) )
        {
            return AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        String strCreate = request.getParameter( PARAMETER_CREATE );

        // Only modify the code mapping if the user has clicked on the submit button "create"
        if ( StringUtils.isBlank( strCreate ) )
        {
            IMappingTypeComponent mappingTypeComponent = _codeMappingFactory.getMappingTypeComponent( strMappingTypeKey );

            if ( mappingTypeComponent != null )
            {
                UrlItem url = new UrlItem( JSP_CREATE_CODE_MAPPING );
                url.addParameter( PARAMETER_MAPPING_TYPE_KEY, strMappingTypeKey );
                mappingTypeComponent.addParameter( request, url );

                return url.getUrl(  );
            }
            else
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_ERROR_MAPPING_TYPE_COMPONENT_NOT_FOUND,
                    AdminMessage.TYPE_STOP );
            }
        }

        ICodeMapping codeMapping = _codeMappingFactory.newCodeMapping( strMappingTypeKey );
        // Populate the bean
        populate( codeMapping, request );

        // Check mandatory fields
        Set<ConstraintViolation<ICodeMapping>> constraintViolations = BeanValidationUtil.validate( codeMapping );

        if ( constraintViolations.size(  ) > 0 )
        {
            return AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        if ( !_codeMappingService.createCodeMapping( codeMapping ) )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_INVALID_CODE_MAPPING, AdminMessage.TYPE_STOP );
        }

        return JSP_MANAGE_CODE_MAPPINGS;
    }

    /**
     * Do modify the code mapping
     * @param request the HTTP request
     * @return the JSP return
     */
    public String doModifyCodeMapping( HttpServletRequest request )
    {
        String strCancel = request.getParameter( PARAMETER_CANCEL );

        if ( StringUtils.isNotBlank( strCancel ) )
        {
            return JSP_MANAGE_CODE_MAPPINGS;
        }

        String strMappingTypeKey = request.getParameter( PARAMETER_MAPPING_TYPE_KEY );
        String strIdCode = request.getParameter( PARAMETER_ID_CODE );

        if ( StringUtils.isBlank( strMappingTypeKey ) || StringUtils.isBlank( strIdCode ) ||
                !StringUtils.isNumeric( strIdCode ) )
        {
            return AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        String strModify = request.getParameter( PARAMETER_MODIFY );

        // Only modify the code mapping if the user has clicked on the submit button "modify"
        if ( StringUtils.isBlank( strModify ) )
        {
            IMappingTypeComponent mappingTypeComponent = _codeMappingFactory.getMappingTypeComponent( strMappingTypeKey );

            if ( mappingTypeComponent != null )
            {
                UrlItem url = new UrlItem( JSP_MODIFY_CODE_MAPPING );
                url.addParameter( PARAMETER_MAPPING_TYPE_KEY, strMappingTypeKey );
                mappingTypeComponent.addParameter( request, url );

                return url.getUrl(  );
            }
            else
            {
                return AdminMessageService.getMessageUrl( request, MESSAGE_ERROR_MAPPING_TYPE_COMPONENT_NOT_FOUND,
                    AdminMessage.TYPE_STOP );
            }
        }

        int nIdCode = Integer.parseInt( strIdCode );
        ICodeMapping codeMapping = _codeMappingService.getCodeMapping( nIdCode );

        if ( codeMapping == null )
        {
            return AdminMessageService.getMessageUrl( request, MESSAGE_ERROR_CODE_MAPPING_NOT_FOUND,
                AdminMessage.TYPE_STOP );
        }

        // Populate the bean
        populate( codeMapping, request );

        // Check mandatory fields
        Set<ConstraintViolation<ICodeMapping>> constraintViolations = BeanValidationUtil.validate( codeMapping );

        if ( constraintViolations.size(  ) > 0 )
        {
            return AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        _codeMappingService.updateCodeMapping( codeMapping );

        return JSP_MANAGE_CODE_MAPPINGS;
    }

    /**
     * Do remove the code mapping
     * @param request the HTTP request
     * @return the JSP return
     */
    public String doRemoveCodeMapping( HttpServletRequest request )
    {
        String strIdCode = request.getParameter( PARAMETER_ID_CODE );

        if ( StringUtils.isBlank( strIdCode ) || !StringUtils.isNumeric( strIdCode ) )
        {
            return AdminMessageService.getMessageUrl( request, Messages.MANDATORY_FIELDS, AdminMessage.TYPE_STOP );
        }

        int nIdCode = Integer.parseInt( strIdCode );

        _codeMappingService.removeCodeMapping( nIdCode );

        return JSP_MANAGE_CODE_MAPPINGS;
    }
}
