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

import org.apache.commons.lang.StringUtils;


/**
 *
 * CodeMappingFilter
 *
 */
public class CodeMappingFilter
{
    private String _strCode;
    private String _strLabelCode;
    private String _strMappingTypeKey;
    private String _strReferenceCode;
    private boolean _bIsWideSearch;

    /**
     * Constructor
     */
    public CodeMappingFilter(  )
    {
        _strCode = StringUtils.EMPTY;
        _strLabelCode = StringUtils.EMPTY;
        _strMappingTypeKey = StringUtils.EMPTY;
        _strReferenceCode = StringUtils.EMPTY;
    }

    /**
     * Get the code
     * @return the code
     */
    public String getCode(  )
    {
        return _strCode;
    }

    /**
     * Set the code
     * @param code the code
     */
    public void setCode( String code )
    {
        this._strCode = code;
    }

    /**
     * Check if the filter contains the code
     * @return true if it contains, false otherwise
     */
    public boolean containsCode(  )
    {
        return StringUtils.isNotBlank( _strCode );
    }

    /**
     * Get the label code
     * @return the label code
     */
    public String getLabelCode(  )
    {
        return _strLabelCode;
    }

    /**
     * Set the label code
     * @param labelCode the label code
     */
    public void setLabelCode( String labelCode )
    {
        this._strLabelCode = labelCode;
    }

    /**
     * Check if the filter contains the label code
     * @return true if it contains, false otherwise
     */
    public boolean containsLabelCode(  )
    {
        return StringUtils.isNotBlank( _strLabelCode );
    }

    /**
     * Get the mapping type key
     * @return the mapping type key
     */
    public String getMappingTypeKey(  )
    {
        return _strMappingTypeKey;
    }

    /**
     * Set the mapping type key
     * @param mappingTypeKey the mapping type key
     */
    public void setMappingTypeKey( String mappingTypeKey )
    {
        this._strMappingTypeKey = mappingTypeKey;
    }

    /**
     * Check if the filter contains the mapping type key
     * @return true if it contains, false otherwise
     */
    public boolean containsMappingTypeKey(  )
    {
        return StringUtils.isNotBlank( _strMappingTypeKey );
    }

    /**
     * Get the reference code
     * @return the reference code
     */
    public String getReferenceCode(  )
    {
        return _strReferenceCode;
    }

    /**
     * Set the reference code
     * @param referenceCode the reference code
     */
    public void setReferenceCode( String referenceCode )
    {
        this._strReferenceCode = referenceCode;
    }

    /**
     * Check if the filter contains the reference code
     * @return true if the it contains, false otherwise
     */
    public boolean containsReferenceCode(  )
    {
        return StringUtils.isNotBlank( _strReferenceCode );
    }

    /**
     * Set true if the search is wide, false otherwise
     * @param isWideSearch true if the search is wide, false otherwise
     */
    public void setWideSearch( boolean isWideSearch )
    {
        this._bIsWideSearch = isWideSearch;
    }

    /**
     * Return true if the search is wide, false otherwise
     * @return true if the search is wide, false otherwise
     */
    public boolean isWideSearch(  )
    {
        return _bIsWideSearch;
    }
}
