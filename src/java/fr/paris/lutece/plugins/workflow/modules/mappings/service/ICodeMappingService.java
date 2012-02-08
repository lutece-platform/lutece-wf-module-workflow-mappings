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

import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping;
import fr.paris.lutece.portal.business.workflow.Action;
import fr.paris.lutece.portal.business.workflow.Workflow;
import fr.paris.lutece.util.ReferenceList;

import java.util.List;


/**
 *
 * ICodeMappingService
 *
 */
public interface ICodeMappingService
{
    // GET

    /**
     * Get the list of {@link ICodeMapping}
     * @return a list of {@link ICodeMapping}
     */
    List<ICodeMapping> getListCodeMappings(  );

    /**
     * Get the {@link ICodeMapping}
     * @param nIdCode the id code
     * @return a {@link ICodeMapping}
     */
    ICodeMapping getCodeMapping( int nIdCode );

    /**
     * Get the workflow action
     * @param nIdAction the id action
     * @return the workflow action
     */
    Action getAction( int nIdAction );

    /**
     * Get the workflow
     * @param nIdWorkflow the id workflow
     * @return the workflow
     */
    Workflow getWorkflow( int nIdWorkflow );

    /**
     * Get the list of workflows
     * @return a {@link ReferenceList}
     */
    ReferenceList getListWorkflow(  );

    /**
     * Get the list of actions that are not used in the mapping yet.
     * @param strMappingTypeKey the mapping type key
     * @param nIdWorkflow the id workflow
     * @return a {@link ReferenceList}
     */
    ReferenceList getListActions( String strMappingTypeKey, int nIdWorkflow );

    // CHECKS

    /**
     * Check if the code mapping is valid
     * @param codeMapping the code mapping
     * @return true if the code mapping does not exist yet, false otherwise
     */
    boolean isCodeMappingValid( ICodeMapping codeMapping );

    // CRUD OPERATIONS

    /**
     * Update the code mapping
     * @param codeMapping the code mapping
     */
    void updateCodeMapping( ICodeMapping codeMapping );

    /**
     * Remove the code mapping
     * @param nIdCode the id code
     */
    void removeCodeMapping( int nIdCode );

    /**
     * Create a code mapping
     * @param codeMapping the code mapping
     * @return true if the code mapping is created, false otherwise
     */
    boolean createCodeMapping( ICodeMapping codeMapping );
}
