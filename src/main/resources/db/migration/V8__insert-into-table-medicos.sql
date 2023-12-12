DELETE FROM vollmed_api.medicos;

INSERT INTO vollmed_api.medicos (nombre, email, documento, especialidad, calle, distrito, complemento, numero, ciudad, telefono, activo)
VALUES 
  ('antonio', 'aa@mail.com', '121212', 'ORTOPEDIA', 'b', 'b', 'b', 'b', 'b', 'b', 1),
  ('bernardo', 'bb@mail.com', '212121', 'ORTOPEDIA', 'b', 'b', 'b', 'b', 'b', 'b', 1),
  ('carlos', 'cc@mail.com', '232323', 'CARDIOLOGIA', 'b', 'b', 'b', 'b', 'b', 'b', 0),
  ('diego', 'dd@mail.com', '323232', 'CARDIOLOGIA', 'b', 'b', 'b', 'b', 'b', 'b', 1),
  ('eduardo', 'ee@mail.com', '343434', 'GINECOLOGIA', 'b', 'b', 'b', 'b', 'b', 'b', 1),
  ('fernando', 'ff@mail.com', '434343', 'PEDIATRIA', 'b', 'b', 'b', 'b', 'b', 'b', 1);
