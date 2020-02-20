DROP TABLE IF EXISTS app_users;

CREATE TABLE stores (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  mail VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  nit VARCHAR(250) NOT NULL
);

INSERT INTO stores (mail, name, nit) VALUES
  ('bla@bla.com', 'Empresa 1', '001'),
  ('plop@plop.com', 'Empresa 2', '002'),
  ('narf@narf.com', 'Empresa 3', '003');