Database
-----------
```
CREATE ROLE demouser LOGIN password 'demopwd';
CREATE DATABASE demodb ENCODING 'UTF8' OWNER demouser;
```

Import Database Dump : [dump.sql](https://phabricator.pusilkom.com/file/data/bf42j4r2mngafgxohtg6/PHID-FILE-4bwxnlfrqr7fzx7t3vlk/dump.sql) 
```
$ psql -U postgres -h localhost < dump.sql
```

Layer
------

  - Model : Representasi data di DB
  - Model Mapper : Query data dan mapping hasilnya menjadi Model atau DTO.
  - Service : Business process
  - Controller : Routing request untuk diproses oleh Service kemudian mengembalikan response
  - DTO (Data Transfer Object) : Representasi data input/output
    - Command / Cmd : Enkapsulasi input dari user untuk mengubah data di DB
    - Form :  Enkapsulasi input dari user untuk query data dari DB
    - Item : Enkapsulasi hasil query untuk per row data
    - Detail : Enkapsulasi hasil query untuk single data


Running
----------
```
$ mvn clean spring-boot:run
```

username: `user`

password: `password`