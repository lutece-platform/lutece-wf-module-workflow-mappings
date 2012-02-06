--
-- Dumping data for table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url) VALUES 
('WORKFLOW_MAPPINGS_MANAGEMENT','module.workflow.mappings.adminFeature.workflowMappingsManagement.name',0,'jsp/admin/plugins/workflow/modules/mappings/ManageCodeMappings.jsp','module.workflow.mappings.adminFeature.workflowMappingsManagement.description',0,'workflow-mappings','APPLICATIONS','images/admin/skin/plugins/workflow/modules/mappings/mappings.png','');

--
-- Dumping data for table core_user_right
--
INSERT INTO core_user_right (id_right,id_user) VALUES ('WORKFLOW_MAPPINGS_MANAGEMENT',1);
INSERT INTO core_user_right (id_right,id_user) VALUES ('WORKFLOW_MAPPINGS_MANAGEMENT',2);
