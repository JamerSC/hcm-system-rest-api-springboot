create database if not exists hcm_test_db;

use hcm_test_db;

select * from employees;
select * from users;

insert into `employees`(`id`, `first_name`, `last_name`, `email`, `job_position`, `department`, `hired_date`, `salary`)
values
(1, "John", "Doe", "john@mail.com", "Assistant Admin", "Admin Department", "2024-1-1", 30000.00),
(2, "Mary", "Public", "mary@mail.com", "Assistant Admin", "Admin Department", "2024-6-1", 30000.00),
(3, "Susan", "Roses", "susan@mail.com", "Assistant Admin", "Admin Department", "2025-1-1", 30000.00);


insert into users (email, first_name, last_name, password, username)
values ('admin@mail.com', 'Super', 'Admin', '$2a$10$2csjgL7IFGGhH3fTRfPVpOmkZS/qcbUKsaW.kwFyflA97zeyzpa2O', 'admin@123');

ALTER TABLE hcm_test_db.employees
DROP COLUMN job_position;