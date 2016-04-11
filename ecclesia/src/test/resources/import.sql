/* Import devel data */

/* Organizations */
INSERT INTO Organizations (id, name, created, updated) VALUES (1, 'One organization', '2015-11-07 00:00:00', '2015-11-07 00:00:00');
INSERT INTO Organizations (id, name, created, updated) VALUES (2, 'Another Organization', '2015-11-07 00:00:00', '2015-11-07 00:00:00');
/* Groups */
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (1, 'Big group', 1, NULL);
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (2, 'Medium group', 1, 1);
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (3, 'Small group', 1, 2);
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (4, 'Another group', 2, NULL);

/* Users */
INSERT INTO Users (id, username, password, email, created, lastLogin, enabled, admin) VALUES (1, 'admin', '$2a$10$NdA9/GAFNK30w8uW0AVhBewxU100kUlbSfVHOM/b1GccIeejY.MwG', 'admin@localhost.local', '2015-11-07 00:00:00', '2015-11-07 00:00:00', true, true);
INSERT INTO Users (id, username, password, email, created, lastLogin, enabled, admin) VALUES (2, 'user', '$2a$10$8n8t3ghDi/A2Vf55ybYbluhEdtyAwweH8DaAwOcS89gVP/7l83PXG', 'user@localhost.local', '2015-11-07 00:00:00', '2015-11-07 00:00:00', true, false);
