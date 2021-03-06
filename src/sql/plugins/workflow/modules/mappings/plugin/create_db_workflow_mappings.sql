--
-- Create table workflow_mappings_code
--
DROP TABLE IF EXISTS workflow_mappings_code;
CREATE TABLE workflow_mappings_code (
	id_code INT DEFAULT 0 NOT NULL,
	code VARCHAR(50) DEFAULT '' NOT NULL,
	label_code VARCHAR(255) DEFAULT '' NOT NULL,
	reference_code VARCHAR(255) DEFAULT 0 NOT NULL,
	mapping_type_key VARCHAR(255) DEFAULT '' NOT NULL,
	PRIMARY KEY (id_code)
);
