create database if not exists hcm_test_db;

use hcm_test_db;

select * from employees;

insert into `employees`(`id`, `first_name`, `last_name`, `email`, `job_position`, `department`, `hired_date`, `salary`)
values
(1, "John", "Doe", "john@mail.com", "Assistant Admin", "Admin Department", "2024-1-1", 30000.00),
(2, "Mary", "Public", "mary@mail.com", "Assistant Admin", "Admin Department", "2024-6-1", 30000.00),
(3, "Susan", "Roses", "susan@mail.com", "Assistant Admin", "Admin Department", "2025-1-1", 30000.00);