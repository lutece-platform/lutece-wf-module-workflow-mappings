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
package fr.paris.lutece.plugins.workflow.modules.mappings.business;

import fr.paris.lutece.plugins.workflow.modules.mappings.service.ICodeMappingService;
import fr.paris.lutece.portal.business.workflow.Action;
import fr.paris.lutece.portal.business.workflow.Workflow;
import fr.paris.lutece.portal.service.util.AppLogService;

import org.apache.commons.lang.StringUtils;

import java.util.Locale;


/**
 *
 * This class represent the mapping between codes and workflow actions.
 *
 */
public class ActionCodeMapping extends AbstractCodeMapping
{
    /** Position of the id action in the reference code */
    private ICodeMappingService _codeMappingService;

    /**
     * Set the code mapping service
     * @param codeMappingService the code mapping service
     */
    public void setCodeMappingService( ICodeMappingService codeMappingService )
    {
        _codeMappingService = codeMappingService;
    }

    /**
     * {@inheritDoc}
     */
    public String getLabelReferenceCode( Locale locale )
    {
        if ( StringUtils.isNotBlank( getReferenceCode(  ) ) && StringUtils.isNumeric( getReferenceCode(  ) ) )
        {
            int nIdAction = Integer.parseInt( getReferenceCode(  ) );
            Action action = _codeMappingService.getAction( nIdAction );

            if ( action != null )
            {
                Workflow workflow = _codeMappingService.getWorkflow( action.getWorkflow(  ).getId(  ) );

                if ( workflow != null )
                {
                    return workflow.getName(  ) + " - " + action.getName(  );
                }
                else
                {
                    AppLogService.error( "ActionCodeMapping - The workflow is not found for id workflow " +
                        action.getWorkflow(  ).getId(  ) );
                }
            }
            else
            {
                AppLogService.error( "ActionCodeMapping - The action is not found for id action " + nIdAction );
            }
        }
        else
        {
            AppLogService.error( "ActionCodeMapping - The reference code '" + getReferenceCode(  ) + "' is invalid." );
        }

        return getReferenceCode(  );
    }
}
