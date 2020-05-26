![wjlogo.png](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/blog.png)

---

![lisense](https://img.shields.io/github/license/Antabot/White-Jotter-Vue)
![Build Status](https://www.travis-ci.org/Antabot/White-Jotter-Vue.svg?branch=master)
![Version](https://img.shields.io/badge/release-v1.0.8-blue)
[![GITEE](https://img.shields.io/badge/Gitee-repo-green)](https://gitee.com/realdonald1994/WhiteJotter_Vue)



This is a simple front-end and back-end separation project, mainly developed using Vue.js + SpringBoot technologies.

In addition to being used as an introductory exercise, I also hope that the project can be used as a scaffold for some common Web projects to help you simplify the process of building a website. It is called a white jotter because it starts at 0 and gradually improves over time.

Frontend Repo：[https://github.com/realdonald1994/WhiteJotter_Vue](https://github.com/realdonald1994/WhiteJotter_Vue)

Backend Repo：[https://github.com/realdonald1994/WhiteJotter](https://github.com/realdonald1994/WhiteJotter)

Welcome to join the White Jotter!



# Overall display

## 1. Home

As a display page, including the main reference materials for the development of this project, recent updates and Slogan

![Home](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/wj_front%20-%20Copy.png)

## 2. Library

Provide book and movie information display function

![Library](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/library.png)

## 3. Jotter

Provide notes, blog posts display function

![Jotter](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/Jotter.png)

![Detail](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/jotterdetail.png)

## 4.Background management

Including dashboard, content management, user and authority management, etc.

![Admin](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/admin.png)

## 5.About Me

Personal introduction and related links

![AboutMe](https://raw.githubusercontent.com/realdonald1994/blog-resources/master/img/aboutme.png)

# Technology stack

## 1. Front-end technology stack

1.Vue  
2.ElementUI  
3.axios

## 2. Backend Technology Stack

1.Spring Boot  
2.Spring Data + JPA  
3.MySQL  
4.Shiro

# Deployment

1.clone project to local

front end:

`git clone https://github.com/realdonald1994/WhiteJotter_Vue`

back end:

`git clone https://github.com/realdonald1994/WhiteJotter`

2.Create a database `white_jotter` in mysql, run the project, and the data will be injected automatically.


The Redis port is 6379 (default port), and the password is blank.

3.The database is configured in the `application.properties` file in the` src \ main \ resources` directory of the backend project, and the mysql version is 8.0.15.

4.Run the backend project in IntelliJ IDEA. To ensure the project runs successfully, you can right-click `pom.xml` and select maven-> reimport and restart the project

At this point, the server is successfully started, at the same time, run the front-end project, visit `http: // localhost: 8080`, you can enter the login page, the default account is` admin`, the password is `123`

If you want to do secondary development, please continue to see the fifth and sixth steps.

5.Enter the root directory of the front-end project, and enter the following commands in order on the command line:

```

# Install dependencies
  npm install

# Start the project at localhost: 8080
  npm run dev

```

Since the port forwarding has been configured in the `wj-front` project to forward the data to SpringBoot, after the project starts, enter` http: // localhost: 8080` in the browser to access our front-end project, All requests forward data to SpringBoot through port forwarding (note that you should not close the SpringBoot project at this time).

6.Finally, you can use `WebStorm` and other tools to open the` wj-front` project and continue development. After the development is complete, when the project is going to go online, you still enter the `wj-front` directory, and then execute the following command:

```
npm run build
```

After the command is executed successfully, a dist folder is generated under the wj-front directory, and the two files static and index.html in the folder can be copied to nginx.Then enter the backend root directory and execute the command line：

```
mvn clean install
```

Finally, enter the command line `java -jar xxx.jar` in the newly generated `target` directory. Start back-end server.

Enter the command line under the nginx file: `start nginx`. Start front-end server.
