# Library-SpringHibernate
Использовал Spring, Hibernate, PostgreSQL, Apache Tomcat...

## Задача.
Веб-приложение для учёта книг и читателей в библиотеке. Приложение содержит список читателей с возможностью просмотра, удаления и редактирования. Также есть воможность просмотреть список всех книг, просмотреть, удалить, редактировать, закрепить за читателем и открепить от читателя каждую книгу. 

Приложение работает под управлением сервера приложений Apache Tomcat 11. Данные хранятся в базе данных под управлением PostgreSQL 15. Обращение к базе данных осуществляется посредством Hibernate.

При обращении к приложению отображается список записей читателей (/people). Обеспечена возможность создать запись, а также изменить или удалить любую запись в списке. При создании и изменении записи должна открываться описанная выше форма с возможностью сохранения изменений и отмены сохранения.
Список книг отображается при переходе по адресу "/books"

Страница обращается к web - сервису на сервере, используя REST протокол.

### После запуска приложения переход по следующим URL даст возможности:
`http://localhost:8081/people` - отображение списка читателей, где можно перейти по ссылке для добавления новой записи или перейти для просмотра, редактирования или удаления конкретного читателя

`http://localhost:8081/people/new` - форма для добавления нового читателя в БД

`http://localhost:8081/people/id` (где id это номер записи в БД) - подробный просмотр конкретной записи с возможностью редактирования или удаления записи 

`http://localhost:8081/books` - отображение списка книг, где можно перейти по ссылке для добавления новой записи или перейти для просмотра, редактирования или удаления конкретной книги

`http://localhost:8081/books/new` - форма для добавления новой книги в БД

`http://localhost:8081/books/id` (где id это номер книги в БД) - подробный просмотр конкретной книги с возможностью редактирования или удаления книги 

### При решении задачи использовался сервер приложений Tomcat 11.0, PostgreSQL 15, spring version 6.0.12, thymeleaf-spring6.

### Вы можете клонировать на свой компьютер программу и опробовать или изучить её. 
  Для того, чтобы клонировать себе программу выполните следующие дествия:

1. На GitHub.com перейдите на главную страницу репозитория.

2. Над списком файлов щелкните <>Code.

3. Скопируйте URL-адрес репозитория.

   * Чтобы клонировать репозиторий по протоколу HTTPS, в разделе "HTTPS" щелкните значёк "копировать".
   * Чтобы клонировать репозиторий с помощью ключа SSH, включая сертификат, выданный центром сертификации SSH вашей организации, щелкните SSH, а затем выберите "копировать".
   * Чтобы клонировать репозиторий с помощью GitHub CLI, щелкните GitHub CLI, а затем выберите "копировать".
     
4. Откройте GIT Bash.

5. Измените текущий рабочий каталог на расположение, где должен находиться клонированный каталог.

6. Введите git clone и вставьте URL-адрес, скопированный ранее.

   >git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY

7. Нажмите клавишу ВВОД, чтобы создать локальный клон.

   >$ git clone https://github.com/YOUR-USERNAME/YOUR-REPOSITORY

   >Cloning into Spoon-Knife...

   >remote: Counting objects: 10, done.

   >remote: Compressing objects: 100% (8/8), done.

   >remove: Total 10 (delta 1), reused 10 (delta 1)

   >Unpacking objects: 100% (10/10), done.

### Перед запуском программы необходимо:

Переименовать файл hibernate.properties.origin в файл hibernate.properties

В файле hibernate.propeties прописать имя пользователя и пароль для вашей БД.

### Пример кода для создания таблицы в БД: 

>CREATE TABLE Person(

  >person_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,

  >name varchar(100) NOT NULL UNIQUE,

  >age int CHECK ( age > 0 )

  >);


>CREATE TABLE Book(

    >book_id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    
    >person_id int REFERENCES Person(person_id) ON DELETE SET NULL,
    
    >title varchar(200) NOT NULL,
    
    >author varchar(100) NOT NULL,
    
    >year_production int
    
>);


### Пример кода для заполнения таблицы данными:

>INSERT INTO person(name, age) VALUES ('Петров Пётр Петрович', 1995);

>INSERT INTO person(name, age) VALUES ('Иванов Иван Иванович', 2000);

>INSERT INTO person(name, age) VALUES ('Путин Владимир Владимирович', 38);

>INSERT INTO person(name, age) VALUES ('Жириновский Владимир Вольфович', 41);


INSERT INTO Book(person_id, title, author, year_production) VALUES (1, 'Психопатология обыденной жизни', 'Зигмунд Фрейд', 1904);
INSERT INTO Book(person_id, title, author, year_production) VALUES (2, 'Война и мир', 'Лев Николаевич Толстой', 1873);
INSERT INTO Book(person_id, title, author, year_production) VALUES (3, 'Герой нашего времени', 'Михаил Юрьевич Лермонтов', 1841);
INSERT INTO Book(person_id, title, author, year_production) VALUES (4, 'му-му', 'Иван Сергеевич Тургенев', 1852);

INSERT INTO Book(title, author, year_production) VALUES ('Дубровский', 'Александр Сергеевич Пушкин', 1841);
INSERT INTO Book(title, author, year_production) VALUES ('Каштанка', 'Антон Павлович Чехов', 1887);
INSERT INTO Book(title, author, year_production) VALUES ('Лошадиная фамилия', 'Антон Павлович Чехов', 1885);
