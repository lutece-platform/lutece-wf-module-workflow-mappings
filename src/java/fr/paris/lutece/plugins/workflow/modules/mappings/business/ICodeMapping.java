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

import java.util.Locale;


/**
 *
 * This interface represents the code mapping between a code
 * and its refence code.
 *
 */
public interface ICodeMapping
{
    /**
     * Get the id code
     * @return the id code
     */
    int getIdCode(  );

    /**
     * Set the id code
     * @param nIdCode the id code
     */
    void setIdCode( int nIdCode );

    /**
     * Get the code
     * @return the code
     */
    String getCode(  );

    /**
     * Set the code
     * @param strCode the code
     */
    void setCode( String strCode );

    /**
     * Get the label code
     * @return the label code
     */
    String getLabelCode(  );

    /**
     * Set the label code
     * @param strLabel the label code
     */
    void setLabelCode( String strLabel );

    /**
     * Get the reference code
     * @return the reference code
     */
    String getReferenceCode(  );

    /**
     * Set the reference code
     * @param strReferenceCode the reference code
     */
    void setReferenceCode( String strReferenceCode );

    /**
     * Get the labem reference code
     * @param locale the Locale
     * @return the label reference code
     */
    String getLabelReferenceCode( Locale locale );

    /**
     * Get the mapping type
     * @return the mapping type
     */
    IMappingType getMappingType(  );

    /**
     * Set the mapping type
     * @param mappingType the mapping type
     */
    void setMappingType( IMappingType mappingType );

    /**
     * Check if the code mapping is in strict mode.
     * @return true if the code mapping is in strict mode, false otherwise
     */
    boolean isStrict(  );

    /**
     * In strict mode, the reference code must be UNIQUE along with the code and
     * the mapping type key. In other words, when creating/modifying a code mapping,
     * the strict mode will ensure that the code is UNIQUE.
     * <br />
     * The mode is strict by default ({@link #isStrict()} == true)
     * @param bIsStrict true if the code mapping is in strict mode, false otherwise
     */
    void setStrict( boolean bIsStrict );
}
