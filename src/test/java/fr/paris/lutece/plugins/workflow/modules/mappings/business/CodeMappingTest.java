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
package fr.paris.lutece.plugins.workflow.modules.mappings.business;

import fr.paris.lutece.plugins.workflow.modules.mappings.service.MappingsPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.test.LuteceTestCase;


/**
 *
 * CodeMappingTest
 *
 */
public class CodeMappingTest extends LuteceTestCase
{
    private static final String BEAN_CODE_MAPPING_DAO = "workflow-mappings.codeMappingDAO";
    private static final String MAPPING_TYPE_SIMPE = "workflow-mappings.simpleMapping";
    private static final String CODE1 = "Code1";
    private static final String LABEL_CODE1 = "LabelCode1";
    private static final String LABEL_CODE2 = "LabelCode2";
    private static final String REFERENCE_CODE1 = "ReferenceCode1";
    private static final String REFERENCE_CODE2 = "ReferenceCode2";

    /**
     * Test business of class fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping
     */
    public void testBusiness(  )
    {
        // Initialize an object
        ICodeMappingFactory codeMappingFactory = SpringContextService.getBean( CodeMappingFactory.BEAN_FACTORY );
        ICodeMapping codeMapping = codeMappingFactory.newCodeMapping( MAPPING_TYPE_SIMPE );
        codeMapping.setCode( CODE1 );
        codeMapping.setLabelCode( LABEL_CODE1 );
        codeMapping.setReferenceCode( REFERENCE_CODE1 );

        // Test create
        ICodeMappingDAO dao = SpringContextService.getBean( BEAN_CODE_MAPPING_DAO );
        Plugin plugin = PluginService.getPlugin( MappingsPlugin.PLUGIN_NAME );
        dao.insert( codeMapping, plugin );

        ICodeMapping codeMappingStored = dao.load( codeMapping.getIdCode(  ), plugin );
        assertEquals( codeMapping.getIdCode(  ), codeMappingStored.getIdCode(  ) );
        assertEquals( codeMapping.getCode(  ), codeMappingStored.getCode(  ) );
        assertEquals( codeMapping.getLabelCode(  ), codeMappingStored.getLabelCode(  ) );
        assertEquals( codeMapping.getReferenceCode(  ), codeMappingStored.getReferenceCode(  ) );

        // Test update
        codeMapping.setLabelCode( LABEL_CODE2 );
        codeMapping.setReferenceCode( REFERENCE_CODE2 );
        dao.update( codeMapping, plugin );
        codeMappingStored = dao.load( codeMapping.getIdCode(  ), plugin );
        assertEquals( codeMapping.getCode(  ), codeMappingStored.getCode(  ) );
        assertEquals( codeMapping.getLabelCode(  ), codeMappingStored.getLabelCode(  ) );
        assertEquals( codeMapping.getReferenceCode(  ), codeMappingStored.getReferenceCode(  ) );

        // Test checks
        dao.checkCodeMapping( codeMappingStored, plugin );

        // Test finders
        dao.selectAll( plugin );

        // Test remove
        dao.remove( codeMapping.getIdCode(  ), plugin );
        codeMappingStored = dao.load( codeMapping.getIdCode(  ), plugin );
        assertNull( codeMappingStored );
    }
}
