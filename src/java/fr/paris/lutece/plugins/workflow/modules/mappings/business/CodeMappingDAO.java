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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * CodeMappingDAO
 *
 */
public class CodeMappingDAO implements ICodeMappingDAO
{
    private static final String SQL_QUERY_SELECT_BY_CODE_TO_MAP = " SELECT code, label_code, reference_code, mapping_type_key " +
        " FROM workflow_mappings_code WHERE code = ? AND mapping_type_key = ? ";
    private static final String SQL_QUERY_SELECT_ALL = " SELECT code, label_code, reference_code, mapping_type_key " +
        " FROM workflow_mappings_code ";
    private static final String SQL_QUERY_DELETE = " DELETE FROM workflow_mappings_code WHERE code = ? AND mapping_type_key = ? ";
    private static final String SQL_QUERY_UPDATE = " UPDATE workflow_mappings_code SET label_code = ?, reference_code = ? " +
        " WHERE code = ? AND mapping_type_key = ? ";
    private static final String SQL_QUERY_CHECK_MAPPING = " SELECT code FROM workflow_mappings_code " +
        " WHERE ( code = ? OR reference_code = ? ) AND mapping_type_key = ?";
    private static final String SQL_QUERY_INSERT = " INSERT INTO workflow_mappings_code (code, label_code, reference_code, mapping_type_key) " +
        " VALUES ( ?, ?, ?, ? ) ";
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_OR = " OR ";
    private static final String SQL_FILTER_CODE = " code = ? ";
    private static final String SQL_FILTER_LABEL_CODE = " label_code = ? ";
    private static final String SQL_FILTER_REFERENCE_CODE = " reference_code = ? ";
    private static final String SQL_FILTER_MAPPING_TYPE_KEY = " mapping_type_key = ? ";
    private CodeMappingFactory _codeMappingFactory;

    /**
     * Set the code mapping factory
     * @param codeMappingFactory the code mapping factory
     */
    public void setCodeMappingFactory( CodeMappingFactory codeMappingFactory )
    {
        _codeMappingFactory = codeMappingFactory;
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void insert( ICodeMapping codeMapping, Plugin plugin )
    {
        int nIndex = 1;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );
        daoUtil.setString( nIndex++, codeMapping.getCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getLabelCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getReferenceCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getMappingType(  ).getKey(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public ICodeMapping load( String strCode, String strMappingTypeKey, Plugin plugin )
    {
        ICodeMapping mapping = null;

        int nIndex = 1;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_BY_CODE_TO_MAP, plugin );
        daoUtil.setString( nIndex++, strCode );
        daoUtil.setString( nIndex++, strMappingTypeKey );
        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            nIndex = 1;

            mapping = _codeMappingFactory.newCodeMapping( strMappingTypeKey );
            mapping.setCode( daoUtil.getString( nIndex++ ) );
            mapping.setLabelCode( daoUtil.getString( nIndex++ ) );
            mapping.setReferenceCode( daoUtil.getString( nIndex++ ) );
        }

        daoUtil.free(  );

        return mapping;
    }

    /**
     * {@inheritDoc}
     */
    public List<ICodeMapping> selectAll( Plugin plugin )
    {
        List<ICodeMapping> listMappings = new ArrayList<ICodeMapping>(  );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_ALL, plugin );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            String strCode = daoUtil.getString( nIndex++ );
            String strLabelCode = daoUtil.getString( nIndex++ );
            String strRefenreceCode = daoUtil.getString( nIndex++ );
            String strMappingTypeKey = daoUtil.getString( nIndex++ );

            ICodeMapping mapping = _codeMappingFactory.newCodeMapping( strMappingTypeKey );
            mapping.setCode( strCode );
            mapping.setLabelCode( strLabelCode );
            mapping.setReferenceCode( strRefenreceCode );

            listMappings.add( mapping );
        }

        daoUtil.free(  );

        return listMappings;
    }

    /**
     * {@inheritDoc}
     */
    public void remove( String strCode, String strMappingTypeKey, Plugin plugin )
    {
        int nIndex = 1;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setString( nIndex++, strCode );
        daoUtil.setString( nIndex++, strMappingTypeKey );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public void update( ICodeMapping codeMapping, Plugin plugin )
    {
        int nIndex = 1;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );
        daoUtil.setString( nIndex++, codeMapping.getLabelCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getReferenceCode(  ) );

        daoUtil.setString( nIndex++, codeMapping.getCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getMappingType(  ).getKey(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public boolean checkCodeMapping( ICodeMapping codeMapping, Plugin plugin )
    {
        int nIndex = 1;
        boolean bIsValid = false;
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_CHECK_MAPPING, plugin );
        daoUtil.setString( nIndex++, codeMapping.getCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getReferenceCode(  ) );
        daoUtil.setString( nIndex++, codeMapping.getMappingType(  ).getKey(  ) );
        daoUtil.executeQuery(  );

        if ( !daoUtil.next(  ) )
        {
            bIsValid = true;
        }

        daoUtil.free(  );

        return bIsValid;
    }

    /**
     * {@inheritDoc}
     */
    public List<ICodeMapping> selectByFilter( CodeMappingFilter cmFilter, Plugin plugin )
    {
        List<ICodeMapping> listCodeMappings = new ArrayList<ICodeMapping>(  );
        StringBuilder sbSQL = new StringBuilder( buildSQLQuery( cmFilter ) );

        DAOUtil daoUtil = new DAOUtil( sbSQL.toString(  ), plugin );
        setFilterValues( cmFilter, daoUtil );
        daoUtil.executeQuery(  );

        while ( daoUtil.next(  ) )
        {
            int nIndex = 1;
            String strCode = daoUtil.getString( nIndex++ );
            String strLabelCode = daoUtil.getString( nIndex++ );
            String strRefenreceCode = daoUtil.getString( nIndex++ );
            String strMappingTypeKey = daoUtil.getString( nIndex++ );

            ICodeMapping mapping = _codeMappingFactory.newCodeMapping( strMappingTypeKey );
            mapping.setCode( strCode );
            mapping.setLabelCode( strLabelCode );
            mapping.setReferenceCode( strRefenreceCode );

            listCodeMappings.add( mapping );
        }

        daoUtil.free(  );

        return listCodeMappings;
    }

    /**
    * Build the SQL query with filter
    * @param cmFilter the filter
    * @return a SQL query
    */
    private String buildSQLQuery( CodeMappingFilter cmFilter )
    {
        StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECT_ALL );
        int nIndex = 1;

        if ( cmFilter.containsCode(  ) )
        {
            nIndex = addSQLWhereOr( cmFilter.isWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_CODE );
        }

        if ( cmFilter.containsLabelCode(  ) )
        {
            nIndex = addSQLWhereOr( cmFilter.isWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_LABEL_CODE );
        }

        if ( cmFilter.containsReferenceCode(  ) )
        {
            nIndex = addSQLWhereOr( cmFilter.isWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_REFERENCE_CODE );
        }

        if ( cmFilter.containsMappingTypeKey(  ) )
        {
            nIndex = addSQLWhereOr( cmFilter.isWideSearch(  ), sbSQL, nIndex );
            sbSQL.append( SQL_FILTER_MAPPING_TYPE_KEY );
        }

        return sbSQL.toString(  );
    }

    /**
     * Add a <b>WHERE</b> or a <b>OR</b> depending of the index.
     * <br/>
     * <ul>
     * <li>if <code>nIndex</code> == 1, then we add a <b>WHERE</b></li>
     * <li>if <code>nIndex</code> != 1, then we add a <b>OR</b> or a <b>AND</b> depending of the wide search characteristic</li>
     * </ul>
     * @param bIsWideSearch true if it is a wide search, false otherwise
     * @param sbSQL the SQL query
     * @param nIndex the index
     * @return the new index
     */
    private int addSQLWhereOr( boolean bIsWideSearch, StringBuilder sbSQL, int nIndex )
    {
        if ( nIndex == 1 )
        {
            sbSQL.append( SQL_WHERE );
        }
        else
        {
            sbSQL.append( bIsWideSearch ? SQL_OR : SQL_AND );
        }

        return nIndex + 1;
    }

    /**
     * Set the filter values on the DAOUtil
     * @param dtFilter the filter
     * @param daoUtil the DAOUtil
     */
    private void setFilterValues( CodeMappingFilter dtFilter, DAOUtil daoUtil )
    {
        int nIndex = 1;

        if ( dtFilter.containsCode(  ) )
        {
            daoUtil.setString( nIndex++, dtFilter.getCode(  ) );
        }

        if ( dtFilter.containsLabelCode(  ) )
        {
            daoUtil.setString( nIndex++, dtFilter.getLabelCode(  ) );
        }

        if ( dtFilter.containsReferenceCode(  ) )
        {
            daoUtil.setString( nIndex++, dtFilter.getReferenceCode(  ) );
        }

        if ( dtFilter.containsMappingTypeKey(  ) )
        {
            daoUtil.setString( nIndex++, dtFilter.getMappingTypeKey(  ) );
        }
    }
}
