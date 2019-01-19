# BrebeufHx 2019 Hackathon Project - Micreau Management

Temperature and pH visualizer for the Micreau indoor farming initiative at Brebeuf.
To run, clone and do a `clean package`, then run the `jar` in `/target`.

Spring Boot + MVC and Thymeleaf are used for templating and displaying the endpoints (`home`). Also used to make the API for temperature and pH data.


Uses [Chart.js](https://www.chartjs.org/) to display the various graphs and jQuery to pull data from the APIs that the Spring Controllers expose, and [Bootstrap Material Design](https://fezvrasta.github.io/bootstrap-material-design/) for the various components of the webpage.
