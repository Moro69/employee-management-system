/** Departments **/

INSERT INTO department (department_id, name, description, phone_number, formation_date)
VALUES (1, 'Business', 'Department of business', '+88005553535', '2018-01-01');
INSERT INTO department (department_id, name, description, phone_number, formation_date)
VALUES (2, 'Accounts', 'Department of accounts', '+99006664646', '2013-04-22');
INSERT INTO department (department_id, name, description, phone_number, formation_date)
VALUES (3, 'Sales', 'Department of sales', '+77004442424', '2015-06-024');
INSERT INTO department (department_id, name, description, phone_number, formation_date)
VALUES (4, 'Economics', 'Department of economics', '+66003331313', '2014-05-05');
INSERT INTO department (department_id, name, description, phone_number, formation_date)
VALUES (5, 'Default', 'Department of unallocated employees', '+999999999999', '1111-11-11');

/** Addresses **/

INSERT INTO address (address_id, country, city, street, house_number, apartment_number, postal_code)
VALUES (1, 'Belgium', 'Kobrin', 'Lenina', '12A', '25', '224022');
INSERT INTO address (address_id, country, city, street, house_number, apartment_number, postal_code)
VALUES (2, 'France', 'Kamenec', 'Pushkina', '13', '13', '88005553535O5');
INSERT INTO address (address_id, country, city, street, house_number, postal_code)
VALUES (3, 'Russia', 'Zhabinka', 'Grove', '1', '789534AAA7');
INSERT INTO address (address_id, country, city, street, house_number, apartment_number, postal_code)
VALUES (4, 'Belarus', 'Brest', 'Masherova', '6a', '406', '654asd654asd');
INSERT INTO address (address_id, country, city, street, house_number, postal_code)
VALUES (5, 'Belarus', 'Pinsk', 'Centralnaya', '36', '225046');
INSERT INTO address (address_id, country, city, street, house_number, apartment_number, postal_code)
VALUES (6, 'Belarus', 'Minsk', 'Zibitskaya', '35', '22', '3360556');
INSERT INTO address (address_id, country, city, street, house_number, postal_code)
VALUES (7, 'Ukraine', 'Lviv', 'Poroshenko', '45', '374Ad489');
INSERT INTO address (address_id, country, city, street, house_number, apartment_number, postal_code)
VALUES (8, 'Poland', 'Terespol', 'Lukrowice', '14', '96', '1447782');

/** Employees **/

INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (1, 'Afanasiy Shket', '1976-04-15', '325195497855', 'shket@mail.ru', 'Manager', 2500, '2018-02-02', 'asdasd654654asdqweasd', 1, 1);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (2, 'Lev Trofimov', '1995-11-14', '365244649455', 'lev@mail.ru', 'Accountant', 3500, '2012-03-03', 'qweqwe59951asdasd195', 2, 2);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (3, 'Petr Chaikovsky', '1840-05-07', '745119549785', 'pert_tea@mail.ru', 'Writer', 7900, '2016-04-04', 'jhkhgjgjh159hgjgjh91195519', 3, 3);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (4, 'Somson Lindover', '1965-08-25', '369852147635', 'over@mail.ru', 'Manager', 2500, '2014-06-06', '78945612asdsad312456789', 4, 4);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (5, 'Red Hot', '2001-01-01', '19535649715694', 'chilly_pepper@gmail.com', 'Developer', 8100, '2015-07-24', '987654asd6549871321dasd0', 8, 4);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (6, 'Allic Met', '1994-03-23', '3752844156654', 'black_wolf@mail.ru', 'Cleaner', 1500, '2017-12-02', '98712asdasd12312123qwe', 7, 3);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (7, 'Modem Router', '1984-05-04', '65328984945154', 'provider@mail.ru', 'System administrator', 4600, '2015-03-14', 'kjhldfgh955959565fdgh', 6, 2);
INSERT INTO employee (employee_id, name, birth_date, phone_number, email, position, salary, hire_date, passport_identification_number, address_id, department_id)
VALUES (8, 'Coca Cola', '1976-04-15', '965491213654', 'pepsi@mail.ru', 'Advertiser', 3950, '2018-02-02', 'qwseasdasd4156456546564ads', 5, 1);


/** Educations **/

INSERT INTO education (education_id, school_name, grade, start_year, end_year, employee_id)
VALUES (1, 'Middle school №35', '11', '1988', '1999', 1);
INSERT INTO education (education_id, school_name, degree, field_of_study, grade, start_year, end_year, employee_id)
VALUES (2, 'Boston University', 'Bachelor', 'Math and Economics', '5', '2000', '2005', 1);
INSERT INTO education (education_id, school_name, grade, start_year, end_year, employee_id)
VALUES (3, 'School 46', '9', '1975', '1984', 4);

/** Supervisors **/

INSERT INTO supervisor (supervisor_id, employee_id, department_id)
VALUES (1, 1, 1);
INSERT INTO supervisor (supervisor_id, employee_id, department_id)
VALUES (2, 2, 2);
INSERT INTO supervisor (supervisor_id, employee_id, department_id)
VALUES (3, 6, 3);
INSERT INTO supervisor (supervisor_id, employee_id, department_id)
VALUES (4, 5, 4);

/** User roles **/

INSERT INTO user_role (user_role_id, name)
VALUES (1, 'ADMIN');
INSERT INTO user_role (user_role_id, name)
VALUES (2, 'USER');

/** Users **/

INSERT INTO user (user_id, name, email, password, user_role_id, enabled)
VALUES (1, 'ADMIN', 'admin@mail.ru', '$2a$10$I7qkgwz4MmgsAdq6xz8VO.SG61qmmATdCi5lYq7zWcCP11eT7EYG.', 1, 1);
INSERT INTO user (user_id, name, email, password, user_role_id, enabled)
VALUES (2, 'USER', 'user@mail.ru', '$2a$10$ZzCr7ARlC8zB8jTFSRZESOw420w4DcgiuYkhBcZcIhk5VZdi6irey', 2, 1);
INSERT INTO user (user_id, name, email, password, user_role_id, employee_id, enabled)
VALUES (3, 'Lev Trofimov', 'lev@mail.ru', '$2a$10$89y0dHnqHjmOY4OELZqBqOq1XiOKDizgPY5NKiY.8/voQYLTPjGd6=', 2, 2, 1);

