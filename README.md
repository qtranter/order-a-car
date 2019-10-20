# Order A Car

This is the foundation to our Order A Car program. Basic logic has been added to the JavaFX to lay the foundation to a fully running program.

## Requirements

In order to run this program, an H2 database is required. Our project utilizes an [H2 Database](https://www.h2database.com/html/download.html), version 1.4.199. The jar executable must be added to the IDE's dependencies. In order for it to work on another machine, the `DB_URL` needs to be updated for the users file system. For example, when testing on our machine, one used `jdbc:h2:D:/trant/Documents/Java Practice/Order A Car2/res/userDatabase`. The `DB_URL` needs to be updated on your machine. We simply organized it into a folder "res/". H2 automatically creates the database if it does not exist.

After installing the database, it is necessary to add the following to the console: 
```
CREATE TABLE USER (
    id INT NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    user_type INT NOT NULL,
    username VARCHAR(20),
    password VARCHAR(20),
);
```

## Video Of GUI
[Screen capture of our project thus far](https://www.youtube.com/watch?v=G10OfJIhees&feature=youtu.be)
