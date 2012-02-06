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

import fr.paris.lutece.plugins.workflow.modules.mappings.business.type.IMappingType;

import org.hibernate.validator.constraints.NotBlank;


/**
 *
 * AbstractCodeMapping
 *
 */
public abstract class AbstractCodeMapping implements ICodeMapping
{
    @NotBlank
    private String _strCode;
    private String _strLabelCode;
    @NotBlank
    private String _strReferenceCode;
    private IMappingType _mappingType;

    /**
     * {@inheritDoc}
     */
    public String getCode(  )
    {
        return _strCode;
    }

    /**
     * {@inheritDoc}
     */
    public void setCode( String strCodeToMap )
    {
        this._strCode = strCodeToMap;
    }

    /**
     * {@inheritDoc}
     */
    public String getReferenceCode(  )
    {
        return _strReferenceCode;
    }

    /**
     * {@inheritDoc}
     */
    public void setReferenceCode( String strRefenreceCode )
    {
        this._strReferenceCode = strRefenreceCode;
    }

    /**
     * {@inheritDoc}
     */
    public String getLabelCode(  )
    {
        return _strLabelCode;
    }

    /**
     * {@inheritDoc}
     */
    public void setLabelCode( String strLabelCode )
    {
        this._strLabelCode = strLabelCode;
    }

    /**
     * {@inheritDoc}
     */
    public void setMappingType( IMappingType mappingType )
    {
        this._mappingType = mappingType;
    }

    /**
     * {@inheritDoc}
     */
    public IMappingType getMappingType(  )
    {
        return _mappingType;
    }
}
