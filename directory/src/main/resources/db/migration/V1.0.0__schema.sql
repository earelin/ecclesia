CREATE TABLE organization (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(128),
  PRIMARY KEY (id)
);

CREATE TABLE person (
  id              BIGINT NOT NULL AUTO_INCREMENT,
  organization_id BIGINT NOT NULL REFERENCES organization (id) ON DELETE CASCADE,
  name            VARCHAR(64),
  surname         VARCHAR(255),
  email           VARCHAR(128),
  address1        VARCHAR(255),
  address2        VARCHAR(255),
  town            VARCHAR(128),
  country         CHAR(2),
  postcode        VARCHAR(64),
  PRIMARY KEY (id)
);
