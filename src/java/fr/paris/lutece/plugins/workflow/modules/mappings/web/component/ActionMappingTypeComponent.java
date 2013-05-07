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
package fr.paris.lutece.plugins.workflow.modules.mappings.web.component;

import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping;
import fr.paris.lutece.plugins.workflow.modules.mappings.service.ICodeMappingService;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.workflow.Workflow;
import fr.paris.lutece.plugins.workflowcore.service.action.IActionService;
import fr.paris.lutece.plugins.workflowcore.service.workflow.IWorkflowService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.url.UrlItem;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * This class represents the representation of the mapping between
 * a code and a workflow action.
 *
 */
public class ActionMappingTypeComponent extends AbstractMappingTypeComponent
{
    // MARKS
    private static final String MARK_REFERENCE_CODE = "referenceCode";
    private static final String MARK_LIST_ACTIONS = "listActions";
    private static final String MARK_LIST_WORKFLOWS = "listWorkflows";
    private static final String MARK_WORKFLOW = "workflow";

    // PARAMETERS
    private static final String PARAMETER_ID_WORKFLOW = "idWorkflow";

    // TEMPLATES
    private static final String TEMPLATE_CREATE_MAPPING = "/admin/plugins/workflow/modules/mappings/action_mapping/create_action_code_mapping.html";
    private static final String TEMPLATE_MODIFY_MAPPING = "/admin/plugins/workflow/modules/mappings/action_mapping/modify_action_code_mapping.html";

    // SERVICES
    @Inject
    private ICodeMappingService _codeMappingService;
    @Inject
    private IActionService _actionService;
    @Inject
    private IWorkflowService _workflowService;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCreateCodeMappingHtml( HttpServletRequest request, Locale locale )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );
        String strIdWorkflow = request.getParameter( PARAMETER_ID_WORKFLOW );
        Workflow workflow = null;

        if ( StringUtils.isNotBlank( strIdWorkflow ) && StringUtils.isNumeric( strIdWorkflow ) )
        {
            int nIdWorkflow = Integer.parseInt( strIdWorkflow );
            workflow = _workflowService.findByPrimaryKey( nIdWorkflow );

            if ( workflow != null )
            {
                model.put( MARK_LIST_ACTIONS,
                    _codeMappingService.getListActions( getMappingType(  ).getKey(  ), nIdWorkflow ) );
                model.put( MARK_WORKFLOW, workflow );
            }
        }

        model.put( MARK_LIST_WORKFLOWS, _codeMappingService.getListWorkflow(  ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_CREATE_MAPPING, locale, model );

        return template.getHtml(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModifyCodeMappingHtml( ICodeMapping codeMapping, HttpServletRequest request, Locale locale )
    {
        Map<String, Object> model = new HashMap<String, Object>(  );
        String strIdWorkflow = request.getParameter( PARAMETER_ID_WORKFLOW );
        int nIdWorkflow = WorkflowUtils.CONSTANT_ID_NULL;
        Action action = null;

        if ( StringUtils.isNotBlank( strIdWorkflow ) && StringUtils.isNumeric( strIdWorkflow ) )
        {
            nIdWorkflow = Integer.parseInt( strIdWorkflow );
        }
        else if ( StringUtils.isNotBlank( codeMapping.getReferenceCode(  ) ) &&
                StringUtils.isNumeric( codeMapping.getReferenceCode(  ) ) )
        {
            int nIdAction = Integer.parseInt( codeMapping.getReferenceCode(  ) );
            action = _actionService.findByPrimaryKey( nIdAction );

            if ( action != null )
            {
                nIdWorkflow = action.getWorkflow(  ).getId(  );
            }
        }

        Workflow workflow = _workflowService.findByPrimaryKey( nIdWorkflow );

        if ( workflow != null )
        {
            ReferenceList listActions = _codeMappingService.getListActions( getMappingType(  ).getKey(  ), nIdWorkflow );

            if ( action != null )
            {
                listActions.addItem( action.getId(  ), action.getName(  ) );
            }

            model.put( MARK_LIST_ACTIONS, listActions );
            model.put( MARK_WORKFLOW, workflow );
        }

        model.put( MARK_LIST_WORKFLOWS, _codeMappingService.getListWorkflow(  ) );
        model.put( MARK_REFERENCE_CODE, codeMapping.getReferenceCode(  ) );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MODIFY_MAPPING, locale, model );

        return template.getHtml(  );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addParameter( HttpServletRequest request, UrlItem url )
    {
        super.addParameter( request, url );

        String strIdWorkflow = request.getParameter( PARAMETER_ID_WORKFLOW );

        if ( StringUtils.isNotBlank( strIdWorkflow ) && StringUtils.isNumeric( strIdWorkflow ) )
        {
            url.addParameter( PARAMETER_ID_WORKFLOW, strIdWorkflow );
        }
    }
}
