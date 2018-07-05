# NASA Image and Video Library
**Nasa Project** is a java application designed to act as Database Mysql Application that manages Users and the favorit. 

It's programmed developed in Java with Spring boot and ready to using more than one NASA Open APIs.

There is the possibility to add multiple user and each user can choose what kind of image or page and the ending can add/remove to their favorite and view the users favourites. 

There is a possibility to click on the link's image in favorit, for full screen image viewing.
Some filtering options for the favorits Image items.

## To install the software on a computer, follow the instructions below:

First database must be created:

Type it in the terminal in your opened mysql root
CREATE DATABASE company;

Create the tables in the company database:

CREATE TABLE `Favorite` (
  `user_id` int (11) NOT NULL,
  `photo_id` int (11) NOT NULL,
  `image` longblob NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

CREATE TABLE `photo` (
  `photo_id` int (11) NOT NULL,
  `camera` varchar (32) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

Enter value and id into photo table:

INSERT IN `photo` (` photo_id`, `camera`) VALUES
(1, 'FHAZ'),
(2, 'MAST'),
(3, 'CHEMCAM'),
(12, 'NAVCAM'),
(15, 'RHAZ');

CREATE TABLE `users` (
  `user_id` int (11) NOT NULL,
  `username` varchar (32) NOT NULL,
  `password` varchar (64) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

ALTER TABLE `photo`
  ADD PRIMARY KEY (`photo_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (` username`);

ALTER TABLE `users`
  MODIFY `user_id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT = 1;
Commit;

## The database is now created. Now the application should be installed on computer.
Follow the instructions below:

* Open the link below:
https://github.com/kami83h/NasaProject

* Click on the clone or download
* Download ZIP
* Go to your Eclipse and click on file and after open projects from file system
* Click "Directory" and navigate to the file that you have downloaded from the github
* Click on the finish
* Right click on project and click on "Run As" and click on "Spring Boot App"
* Open browser and access http://localhost:8080/
* When the page opens, create new admin with a new username and new password
* Sign in with creative user name and password
* You can choose images on two different options
1. Camera List
2. Page List
* After selecting, a link shows that was linked to Nasa Open Image.
* Click on the link for view and full size image
* Click Add, located right of the link's image
* Now the image added to favorite users
* Click on favorites along the top of the menu
* It shows all pictures in favorite if there are any images that users have added before
* Click on sign out to log out and possibly create a new user.

Thank you!

## Technical method
Java, REST API, Spring Boot, Maven, Bootstrap, Thymeleaf.

## Supported Platforms
This project support Mobile Devices. 
 
 * Windows 
 * Linux 
 * Mac OS X
 
 **Requires Java to run!**
