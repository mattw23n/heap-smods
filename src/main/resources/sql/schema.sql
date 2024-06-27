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
    mid VARCHAR(255) NOT NULL,
    mid2 VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE grad_requirement (
    mid VARCHAR(255) NOT NULL,
    requirement VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE module (
    mid VARCHAR(255) NOT NULL,
    cu FLOAT(23),
    mname VARCHAR(255),
    PRIMARY KEY (mid)
) ENGINE=InnoDB;

CREATE TABLE mutually_exclusive (
    mid VARCHAR(255) NOT NULL,
    mid2 VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE plan (
    pid BIGINT NOT NULL AUTO_INCREMENT,
    degree VARCHAR(255),
    pname VARCHAR(255),
    track VARCHAR(255),
    uid BIGINT,
    PRIMARY KEY (pid)
) ENGINE=InnoDB;

CREATE TABLE plan_module_gpa (
    gpa FLOAT(23),
    term VARCHAR(255),
    MID VARCHAR(255) NOT NULL,
    PID BIGINT NOT NULL,
    PRIMARY KEY (MID, PID)
) ENGINE=InnoDB;

CREATE TABLE plan_module_preassigned_gpa (
    gpa FLOAT(23),
    term VARCHAR(255),
    MID VARCHAR(255) NOT NULL,
    PID BIGINT NOT NULL,
    PRIMARY KEY (MID, PID)
) ENGINE=InnoDB;

CREATE TABLE pre_requisite (
    mid VARCHAR(255) NOT NULL,
    mid2 VARCHAR(255) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    email_verified BIT NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    verification_code VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;