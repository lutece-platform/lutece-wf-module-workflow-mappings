/*
 * Copyright (c) 2002-2012, Mairie de Paris
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

import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.List;


/**
 *
 * ICodeMappingDAO
 *
 */
public interface ICodeMappingDAO
{
    /**
     * Get new primary key
     * @param plugin the plugin
     * @return a new primary key
     */
    int newPrimaryKey( Plugin plugin );

    /**
     * Load the code mapping
     * @param nIdCode the id code
     * @param plugin the plugin
     * @return an instance of {@link ICodeMapping}
     */
    ICodeMapping load( int nIdCode, Plugin plugin );

    /**
     * Select all code mappings
     * @param plugin the plugin
     * @return a list of {@link ICodeMapping}
     */
    List<ICodeMapping> selectAll( Plugin plugin );

    /**
     * Select code mappings by filter
     * @param cmFilter the filter
     * @param plugin the plugin
     * @return a list of {@link ICodeMapping}
     */
    List<ICodeMapping> selectByFilter( CodeMappingFilter cmFilter, Plugin plugin );

    /**
     * Insert a new code mapping
     * @param codeMapping the code mapping
     * @param plugin the plugin
     */
    void insert( ICodeMapping codeMapping, Plugin plugin );

    /**
     * Remove a code mapping
     * @param nCode the id code
     * @param plugin the plugin
     */
    void remove( int nCode, Plugin plugin );

    /**
     * Update a code mapping
     * @param codeMapping the code mapping
     * @param plugin the plugin
     */
    void update( ICodeMapping codeMapping, Plugin plugin );

    /**
     * Check if the code mapping is valid
     * @param codeMapping the code mapping
     * @param plugin the plugin
     * @return true if the mapping does not exist yet, false otherwise
     */
    boolean checkCodeMapping( ICodeMapping codeMapping, Plugin plugin );
}
