DROP DATABASE `SMODS`;
CREATE DATABASE IF NOT EXISTS `smods`;
USE `smods`;

DROP TABLE IF EXISTS `PLAN`;
DROP TABLE IF EXISTS `MUTUALLY_EXCLUSIVE`;
DROP TABLE IF EXISTS `CO_REQUISITE`;
DROP TABLE IF EXISTS `PRE_REQUISITE`;
DROP TABLE IF EXISTS `GRAD_REQUIREMENT`;
DROP TABLE IF EXISTS `PLAN_MODULE_PREASSIGNED_GPA`;
DROP TABLE IF EXISTS `PLAN_MODULE_GPA`;
DROP TABLE IF EXISTS `USERS`;
DROP TABLE IF EXISTS `MODULE`;

CREATE TABLE co_requisite (
    module_id VARCHAR(255) NOT NULL,
    module_id2 VARCHAR(255) NOT NULL,
    PRIMARY KEY (module_id, module_id2)
) ENGINE=InnoDB;

CREATE TABLE grad_requirement (
    module_id VARCHAR(255) NOT NULL,
    requirement VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE module (
    module_id VARCHAR(255) NOT NULL,
    course_unit FLOAT,
    module_name VARCHAR(255),
    PRIMARY KEY (module_id)
) ENGINE=InnoDB;

CREATE TABLE mutually_exclusive (
    module_id VARCHAR(255) NOT NULL,
    module_id2 VARCHAR(255) NOT NULL,
    PRIMARY KEY (module_id, module_id2)
) ENGINE=InnoDB;

CREATE TABLE plan (
    plan_id BIGINT NOT NULL,
    degree VARCHAR(255),
    plan_name VARCHAR(255),
    track VARCHAR(255),
    user_id BIGINT NOT NULL,
    PRIMARY KEY (plan_id, user_id)
) ENGINE=InnoDB;

CREATE TABLE plan_module_gpa (
    gpa FLOAT,
    term INTEGER,
    plan_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    module_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (module_id, plan_id, user_id)
) ENGINE=InnoDB;

CREATE TABLE plan_module_preassigned_gpa (
    gpa FLOAT,
    term INTEGER,
    plan_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    module_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (module_id, plan_id, user_id)
) ENGINE=InnoDB;

CREATE TABLE pre_requisite (
    module_id VARCHAR(255) NOT NULL,
    module_id2 VARCHAR(255) NOT NULL,
    PRIMARY KEY (module_id, module_id2)
) ENGINE=InnoDB;

CREATE TABLE users (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    email_verified BIT NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    username VARCHAR(16) NOT NULL,
    verification_code INTEGER NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB;