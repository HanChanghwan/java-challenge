CREATE TABLE IF NOT EXISTS EMPLOYEE(
ID LONG AUTO_INCREMENT PRIMARY KEY,
EMPLOYEE_NAME VARCHAR(50),
EMPLOYEE_SALARY INT,
DEPARTMENT VARCHAR(50),
CREATED_USER VARCHAR(20),
CREATED_TIME DATETIME,
UPDATED_USER VARCHAR(20),
UPDATED_TIME DATETIME
);