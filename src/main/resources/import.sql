/* Import devel data */
INSERT INTO Organization (id, name) VALUES (1, 'Anova'),(2, 'Podemos');
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (1, 'Asemblea Nacional', 1, NULL);
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (2, 'Comarca de Compostela', 1, 1);
INSERT INTO Groups (id, name, organization_id, parent_id) VALUES (3, 'Localidade de Santiago', 1, 2);