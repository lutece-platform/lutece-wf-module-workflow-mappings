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
package fr.paris.lutece.plugins.workflow.modules.mappings.web.component;

import fr.paris.lutece.plugins.workflow.modules.mappings.business.ICodeMapping;
import fr.paris.lutece.plugins.workflow.modules.mappings.business.type.IMappingType;
import fr.paris.lutece.util.url.UrlItem;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * This class represent the representation of the form for
 * each mapping type.
 *
 */
public interface IMappingTypeComponent
{
    /**
     * Get the mapping type
     * @return a {@link IMappingType}
     */
    IMappingType getMappingType(  );

    /**
     * Set the mapping type
     * @param mappingType the mapping type
     */
    void setMappingType( IMappingType mappingType );

    /**
     * Get the HTML code for creating a code mapping
     * @param request the HTTP request
     * @param locale the Locale
     * @return the HTML code
     */
    String getCreateCodeMappingHtml( HttpServletRequest request, Locale locale );

    /**
     * Get the HTML code for modifying a code mapping
     * @param codeMapping the code mapping
     * @param request the HTTP request
     * @param locale the Locale
     * @return the HTML code
     */
    String getModifyCodeMappingHtml( ICodeMapping codeMapping, HttpServletRequest request, Locale locale );

    /**
     * Add parameters to the url
     * @param request the HTTP request
     * @param url the url
     */
    void addParameter( HttpServletRequest request, UrlItem url );
}
