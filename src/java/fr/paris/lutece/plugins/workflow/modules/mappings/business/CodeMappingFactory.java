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
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.ReferenceList;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 *
 * This factory is used for :
 * <ul>
 * <li>
 * creating new instance of {@link ICodeMapping} depending of the mapping type key
 * </li>
 * <li>
 * getting the {@link IMappingTypeComponent}
 * </li>
 * </ul>
 *
 */
public class CodeMappingFactory
{
    private Map<String, IMappingTypeComponent> _mapMappingTypeComponents;

    /**
     * Create a new instance of {@link ICodeMapping}
     * @param strMappingTypeKey the mapping type key
     * @return a new instance of {@link ICodeMapping}
     */
    public ICodeMapping newCodeMapping( String strMappingTypeKey )
    {
        ICodeMapping codeMapping = null;

        try
        {
            codeMapping = (ICodeMapping) SpringContextService.getBean( strMappingTypeKey );
        }
        catch ( BeanDefinitionStoreException e )
        {
            AppLogService.debug( "CodeMappingFactory ERROR : could not load bean '" + e.getBeanName(  ) +
                "' - CAUSE : " + e.getMessage(  ) );
        }
        catch ( NoSuchBeanDefinitionException e )
        {
            AppLogService.debug( "CodeMappingFactory ERROR : could not load bean '" + e.getBeanName(  ) +
                "' - CAUSE : " + e.getMessage(  ) );
        }
        catch ( CannotLoadBeanClassException e )
        {
            AppLogService.debug( "CodeMappingFactory ERROR : could not load bean '" + e.getBeanName(  ) +
                "' - CAUSE : " + e.getMessage(  ) );
        }

        // If no mapping type is defined for strMappingTypeKey, then create a SimpleMappingType
        if ( codeMapping == null )
        {
            codeMapping = new SimpleCodeMapping(  );
        }

        return codeMapping;
    }

    /**
     * Get the {@link IMappingTypeComponent}
     * @return a map of (mapping_type_key, {@link IMappingTypeComponent}
     */
    public Map<String, IMappingTypeComponent> getMappingTypeComponents(  )
    {
        if ( _mapMappingTypeComponents == null )
        {
            init(  );
        }

        return _mapMappingTypeComponents;
    }

    /**
     * Get the {@link IMappingTypeComponent} type
     * @param strMappingTypeKey the mapping type key
     * @return a {@link IMappingTypeComponent}
     */
    public IMappingTypeComponent getMappingTypeComponent( String strMappingTypeKey )
    {
        if ( _mapMappingTypeComponents == null )
        {
            init(  );
        }

        return _mapMappingTypeComponents.get( strMappingTypeKey );
    }

    /**
     * Get the mapping type
     * @param strMappingTypeKey the mapping type key
     * @return a {@link IMappingType}
     */
    public IMappingType getMappingType( String strMappingTypeKey )
    {
        IMappingTypeComponent mappingTypeComponent = getMappingTypeComponent( strMappingTypeKey );

        if ( mappingTypeComponent != null )
        {
            return mappingTypeComponent.getMappingType(  );
        }

        return null;
    }

    /**
     * Get the list of mapping types
     * @param locale the locale
     * @return a {@link ReferenceList}
     */
    public ReferenceList getListMappingTypes( Locale locale )
    {
        ReferenceList listMappingTypes = new ReferenceList(  );

        for ( ICodeMapping mappingType : SpringContextService.getBeansOfType( ICodeMapping.class ) )
        {
            listMappingTypes.addItem( mappingType.getMappingType(  ).getKey(  ),
                mappingType.getMappingType(  ).getLabel( locale ) );
        }

        return listMappingTypes;
    }

    /**
     * Init in case the map is null
     */
    private void init(  )
    {
        _mapMappingTypeComponents = new HashMap<String, IMappingTypeComponent>(  );

        for ( IMappingTypeComponent mappingTypeComponent : SpringContextService.getBeansOfType( 
                IMappingTypeComponent.class ) )
        {
            _mapMappingTypeComponents.put( mappingTypeComponent.getMappingType(  ).getKey(  ), mappingTypeComponent );
        }
    }
}
