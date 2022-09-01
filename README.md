# Initial-Api

## Setup

### Run application

### After the initialization of Spring Boot:
Please run this SQL script after starting Spring Boot in your database to have a 
default admin user :

    insert into my_user values  (1,'jerry','micael',
    '$2a$10$hgRLFcAjGVGEQBGvD5Sg9eFhqxtKRFDa6Vl5A86GRp6pBRqcjbPkq',
    'admin','admin');

### You can now control the API with the credentials:

    {
        username: admin,
        password: admin
    }

### Swagger Api link: https://app.swaggerhub.com/apis-docs/HEIMINOSOA/Job-offers/1.0.0