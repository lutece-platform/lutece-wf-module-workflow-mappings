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

import fr.paris.lutece.plugins.workflow.modules.mappings.service.MappingsPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

import java.util.List;


/**
 *
 * CodeMappingHome
 *
 */
public final class CodeMappingHome
{
    private static final String BEAN_CODE_MAPPING_DAO = "workflow-mappings.codeMappingDAO";
    private static Plugin _plugin = PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME );
    private static ICodeMappingDAO _dao = (ICodeMappingDAO) SpringContextService.getBean( BEAN_CODE_MAPPING_DAO );

    /**
     * Private constructor
     */
    private CodeMappingHome(  )
    {
    }

    /**
    * Load the code mapping
    * @param nIdCode the id code
    * @return an instance of {@link ICodeMapping}
    */
    public static ICodeMapping findByPrimaryKey( int nIdCode )
    {
        return _dao.load( nIdCode, _plugin );
    }

    /**
     * Select all code mappings
     * @return a list of {@link ICodeMapping}
     */
    public static List<ICodeMapping> findAll(  )
    {
        return _dao.selectAll( _plugin );
    }

    /**
     * Select code mappings by filter
     * @param cmFilter the filter
     * @return a list of {@link ICodeMapping}
     */
    public static List<ICodeMapping> findByFilter( CodeMappingFilter cmFilter )
    {
        return _dao.selectByFilter( cmFilter, _plugin );
    }

    /**
     * Insert a new code mapping
     * @param codeMapping the code mapping
     */
    public static void create( ICodeMapping codeMapping )
    {
        _dao.insert( codeMapping, _plugin );
    }

    /**
     * Remove a code mapping
     * @param nIdCode the id code
     */
    public static void remove( int nIdCode )
    {
        _dao.remove( nIdCode, _plugin );
    }

    /**
     * Update a code mapping
     * @param codeMapping the code mapping
     */
    public static void update( ICodeMapping codeMapping )
    {
        _dao.update( codeMapping, _plugin );
    }

    /**
     * Check if the code mapping is valid
     * @param codeMapping the code mapping
     * @return true if the mapping does not exist yet, false otherwise
     */
    public static boolean checkCodeMapping( ICodeMapping codeMapping )
    {
        return _dao.checkCodeMapping( codeMapping, _plugin );
    }
}
