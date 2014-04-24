/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.workflow.modules.mappings.service;

import fr.paris.lutece.plugins.workflow.modules.mappings.business.CodeMappingFilter;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMappingDAO;
import fr.paris.lutece.plugins.workflowcore.business.action.Action;
import fr.paris.lutece.plugins.workflowcore.business.action.ActionFilter;
import fr.paris.lutece.plugins.workflowcore.business.workflow.Workflow;
import fr.paris.lutece.plugins.workflowcore.business.workflow.WorkflowFilter;
import fr.paris.lutece.plugins.workflowcore.service.action.IActionService;
import fr.paris.lutece.plugins.workflowcore.service.workflow.IWorkflowService;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.util.ReferenceList;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.inject.Inject;


/**
 *
 * CodeMappingService
 *
 */
public class CodeMappingService implements ICodeMappingService
{
    public static final String BEAN_SERVICE = "workflow-mappings.codeMappingService";
    @Inject
    private ICodeMappingDAO _codeMappingDAO;
    @Inject
    private IActionService _actionService;
    @Inject
    private IWorkflowService _workflowService;

    /**
    * {@inheritDoc}
    */
    @Override
    @Transactional( "workflow-mappings.transactionManager" )
    public void updateCodeMapping( ICodeMapping codeMapping )
    {
        if ( codeMapping != null )
        {
            _codeMappingDAO.update( codeMapping, PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( "workflow-mappings.transactionManager" )
    public void removeCodeMapping( int nIdCode )
    {
        _codeMappingDAO.remove( nIdCode, PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional( "workflow-mappings.transactionManager" )
    public boolean createCodeMapping( ICodeMapping codeMapping )
    {
        if ( ( codeMapping != null ) && isCodeMappingValid( codeMapping ) )
        {
            _codeMappingDAO.insert( codeMapping, PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );

            return true;
        }

        return false;
    }

    // GET

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ICodeMapping> getListCodeMappings(  )
    {
        return _codeMappingDAO.selectAll( PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ICodeMapping> getListCodeMappingsByFilter( CodeMappingFilter cmFilter )
    {
        return _codeMappingDAO.selectByFilter( cmFilter, PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ICodeMapping getCodeMapping( int nIdCode )
    {
        return _codeMappingDAO.load( nIdCode, PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferenceList getListWorkflow(  )
    {
        ReferenceList listWorkflows = new ReferenceList(  );

        for ( Workflow workflow : _workflowService.getListWorkflowsByFilter( new WorkflowFilter(  ) ) )
        {
            listWorkflows.addItem( workflow.getId(  ), workflow.getName(  ) );
        }

        return listWorkflows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReferenceList getListActions( String strMappingTypeKey, int nIdWorkflow )
    {
        CodeMappingFilter cmFilter = new CodeMappingFilter(  );
        cmFilter.setMappingTypeKey( strMappingTypeKey );

        List<ICodeMapping> listCodeMappings = getListCodeMappingsByFilter( cmFilter );
        ReferenceList listActions = new ReferenceList(  );
        ActionFilter aFilter = new ActionFilter(  );
        aFilter.setIdWorkflow( nIdWorkflow );

        for ( Action action : _actionService.getListActionByFilter( aFilter ) )
        {
            boolean bIsCodeMapped = false;

            // Do not add existing mapping in the list
            for ( ICodeMapping codeMapping : listCodeMappings )
            {
                if ( ( codeMapping != null ) &&
                        codeMapping.getReferenceCode(  ).equals( Integer.toString( action.getId(  ) ) ) )
                {
                    bIsCodeMapped = true;

                    continue;
                }
            }

            if ( !bIsCodeMapped )
            {
                listActions.addItem( action.getId(  ), action.getName(  ) );
            }
        }

        return listActions;
    }

    // CHECKS

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCodeMappingValid( ICodeMapping codeMapping )
    {
        if ( codeMapping != null )
        {
            if ( codeMapping.isStrict(  ) )
            {
                return _codeMappingDAO.checkCodeMapping( codeMapping,
                    PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME ) );
            }

            return true;
        }

        return false;
    }
}
