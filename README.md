<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
    <img src="src/main/resources/static/logo/FindTheRoad_logos_black.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Find The Road </h3>

  <p align="center">
   A platform designed exclusively for organizations that seek complete control over their projects!
    <br />
  </p>
</div>

# Table of contents

- [About The Project](#about-the-project)
    - [The Technologies](#the-technologies)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Launch](#launch)
- [Usage](#usage)
- [License](#license)
- [Team Members](#team-members)
- [Contact](#contact)

****<!-- ABOUT THE PROJECT -->

## About The Project

Our vision for this project is to provide organizations with the ability to document active projects, together with
supervision of the teams working on each project and its composition of employees.

Here's why:

As companies tend to grow and expand, so does the effort required for maintaining project management clarity. It can get
overwhelming very quickly, so here's where Find the Road comes in!

<p align="right">(<a href="#top">back to top</a>)</p>

### The Technologies

#### Front-End:
*JavaScript*, *HTML*,*CSS*

#### Back-End:
*Java 11*, *MongoDB*

#### Build System:
*Maven*

#### Frameworks:
*Spring Boot*

*Spring Security*

*Thymeleaf*

#### API documentation:

*Swagger*

We use Docker for containerization, and Docker-compose for deployment.



<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

You need to have Java SDK 11, IntelliJ, Docker and Docker-Compose to be able to run our project. Docker-Compose will
pull the latest mongodb image, so no need to install it manually.

### Launch

1. Clone the repo
   ```sh
   git clone https://github.com/SteliosGeorgalas/SpringBoot-Find-The-Road.git
   ```
2. Run docker-compose.yml. (You will either have to set the configuration to run Maven goals "clean" then "package"
, or you can do it yourself manually before running docker-compose-yml.)
3. Once everything is set and the respective docker containers are online, you are good to go!

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
### Usage

You may explore the application by navigating to localhost:8080 on your browser. You will be asked for username and
password. Use "user" and "password" respectively.

You should now be authorized to navigate the content of the application. Since the database starts empty, navigate using
the navbar on the top to any of the tabs "Employees", "Teams",
"Projects".

You should now see the entries from the database.

You can use the "Projects" tab to look up current projects and see the comments for each one, as well as the teams
currently working on it.

You can use the "Teams" tab to look up the employees for each team, as well as the projects that the particular team is
working on.

There are relevant action buttons in each of the tabs to insert new data by filling in the respective fields and
submitting, or to update existing data.


You can also navigate to:

localhost:8080/v2/api-docs

localhost:8080/v3/api-docs

localhost:8080/swagger-ui/

to check the swagger API documentation.

<p align="right">(<a href="#top">back to top</a>)</p>

## Known Issues

The mongodb database is stored inside the project's directory. If you're using Linux and try to stop the app and re-run
it, you will come across a build error. That's because when mongodb is generated, the permissions to modify the files
under that directory are not available to you. To fix it, you should:

```sh
cd ~/IdeaProjects/SpringBoot-Find-The-Road/ && sudo rm -rf mongodb
```

Then, build the application again.

<!-- LICENSE -->

## License

Distributed under the Apache 2.0 license.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- TEAM MEMBERS -->

## Team Members
Stelios Georgalas

Evangelos Poulios

Dimitris Alekopoulos

Helen Charitou

Evangelos Tsagkournis

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Project Link:
[https://github.com/SteliosGeorgalas/SpringBoot-Find-The-Road](https://github.com/SteliosGeorgalas/SpringBoot-Find-The-Road)

<p align="right">(<a href="#top">back to top</a>)</p>****
