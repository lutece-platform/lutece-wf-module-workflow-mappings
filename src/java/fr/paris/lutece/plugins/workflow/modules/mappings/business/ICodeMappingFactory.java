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

import fr.paris.lutece.plugins.workflow.modules.mappings.business.type.IMappingType;
import fr.paris.lutece.plugins.workflow.modules.mappings.web.component.IMappingTypeComponent;
import fr.paris.lutece.util.ReferenceList;

import java.util.Locale;
import java.util.Map;


/**
 *
 * ICodeMappingFactory
 *
 */
public interface ICodeMappingFactory
{
    /**
    * Create a new instance of {@link ICodeMapping}
    * @param strMappingTypeKey the mapping type key
    * @return a new instance of {@link ICodeMapping}
    */
    ICodeMapping newCodeMapping( String strMappingTypeKey );

    /**
     * Get the {@link IMappingTypeComponent}
     * @return a map of (mapping_type_key, {@link IMappingTypeComponent}
     */
    Map<String, IMappingTypeComponent> getMappingTypeComponents(  );

    /**
     * Get the {@link IMappingTypeComponent} type
     * @param strMappingTypeKey the mapping type key
     * @return a {@link IMappingTypeComponent}
     */
    IMappingTypeComponent getMappingTypeComponent( String strMappingTypeKey );

    /**
     * Get the mapping type
     * @param strMappingTypeKey the mapping type key
     * @return a {@link IMappingType}
     */
    IMappingType getMappingType( String strMappingTypeKey );

    /**
     * Get the list of mapping types
     * @param locale the locale
     * @return a {@link ReferenceList}
     */
    ReferenceList getListMappingTypes( Locale locale );
}
