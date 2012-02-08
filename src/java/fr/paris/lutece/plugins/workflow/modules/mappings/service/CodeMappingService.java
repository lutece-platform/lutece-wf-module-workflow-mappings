/*
 * Copyright (c) 2002-2011, Mairie de Paris
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

import fr.paris.lutece.plugins.workflow.business.ActionFilter;
import fr.paris.lutece.plugins.workflow.business.ActionHome;
import fr.paris.lutece.plugins.workflow.business.WorkflowFilter;
import fr.paris.lutece.plugins.workflow.business.WorkflowHome;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.CodeMappingFilter;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.CodeMappingHome;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping;
import fr.paris.lutece.plugins.workflow.service.WorkflowPlugin;
import fr.paris.lutece.portal.business.workflow.Action;
import fr.paris.lutece.portal.business.workflow.Workflow;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;


/**
 *
 * CodeMappingService
 *
 */
public class CodeMappingService implements ICodeMappingService
{
    // GET

    /**
     * {@inheritDoc}
     */
    public List<ICodeMapping> getListCodeMappings(  )
    {
        return CodeMappingHome.findAll(  );
    }

    /**
     * {@inheritDoc}
     */
    public List<ICodeMapping> getListCodeMappingsByFilter( CodeMappingFilter cmFilter )
    {
        return CodeMappingHome.findByFilter( cmFilter );
    }

    /**
     * {@inheritDoc}
     */
    public ICodeMapping getCodeMapping( int nIdCode )
    {
        return CodeMappingHome.findByPrimaryKey( nIdCode );
    }

    /**
     * {@inheritDoc}
     */
    public Workflow getWorkflow( int nIdWorkflow )
    {
        Plugin pluginWorkflow = PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME );

        return WorkflowHome.findByPrimaryKey( nIdWorkflow, pluginWorkflow );
    }

    /**
     * {@inheritDoc}
     */
    public ReferenceList getListWorkflow(  )
    {
        Plugin pluginWorkflow = PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME );
        ReferenceList listWorkflows = new ReferenceList(  );

        for ( Workflow workflow : WorkflowHome.getListWorkflowsByFilter( new WorkflowFilter(  ), pluginWorkflow ) )
        {
            listWorkflows.addItem( workflow.getId(  ), workflow.getName(  ) );
        }

        return listWorkflows;
    }

    /**
     * {@inheritDoc}
     */
    public Action getAction( int nIdAction )
    {
        Plugin pluginWorkflow = PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME );

        return ActionHome.findByPrimaryKey( nIdAction, pluginWorkflow );
    }

    /**
     * {@inheritDoc}
     */
    public ReferenceList getListActions( String strMappingTypeKey, int nIdWorkflow )
    {
        Plugin pluginWorkflow = PluginService.getPlugin( WorkflowPlugin.PLUGIN_NAME );
        CodeMappingFilter cmFilter = new CodeMappingFilter(  );
        cmFilter.setMappingTypeKey( strMappingTypeKey );

        List<ICodeMapping> listCodeMappings = getListCodeMappingsByFilter( cmFilter );
        ReferenceList listActions = new ReferenceList(  );
        ActionFilter aFilter = new ActionFilter(  );
        aFilter.setIdWorkflow( nIdWorkflow );

        for ( Action action : ActionHome.getListActionByFilter( aFilter, pluginWorkflow ) )
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
    public boolean isCodeMappingValid( ICodeMapping codeMapping )
    {
        if ( codeMapping != null )
        {
            if ( codeMapping.isStrict(  ) )
            {
                return CodeMappingHome.checkCodeMapping( codeMapping );
            }

            return true;
        }

        return false;
    }

    // CRUD OPERATIONS

    /**
     * {@inheritDoc}
     */
    public void updateCodeMapping( ICodeMapping codeMapping )
    {
        if ( codeMapping != null )
        {
            CodeMappingHome.update( codeMapping );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeCodeMapping( int nIdCode )
    {
        CodeMappingHome.remove( nIdCode );
    }

    /**
     * {@inheritDoc}
     */
    public boolean createCodeMapping( ICodeMapping codeMapping )
    {
        if ( ( codeMapping != null ) && isCodeMappingValid( codeMapping ) )
        {
            CodeMappingHome.create( codeMapping );

            return true;
        }

        return false;
    }
}
