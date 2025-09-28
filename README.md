<H2>Hotel Rating Management System</H2>
<span>A scalable microservices architecture for managing ratings of hotels rated buy users built with Spring Boot and Spring Cloud.</span>
<H2>🚀 About the Project</H2>
<span>The project consist of three following services
	<ul style="list-style-type:disc">
		<li><b>User Service : </b>This service deals with user related functionlities while using MySQL as database</li>
		<li><b>Hotel Service : </b>This service deals with Hotel records related functionlities while using MongoDB as database</li>
		<li><b>Ratings Service : </b>This service stores the records related to ratings given by user based on the userId and hotelId while using PostgreSQL as database</li>
	</ul>
	All the services are secured using OKTA as authentication as well as authorization server. The main goal behind this project is to mimic real world system architecture from a beginners perspective.
</span>
<H2>Application Architecture</H2>
<span><img width="1765" height="998" alt="image" src="https://github.com/user-attachments/assets/5d0ab858-7031-4bbd-a1a5-c72aebdbde1b" /></span>
<H2>✨ Features</H2>
<span><ul style="list-style-type:disc">
	<li><b>Service Registry :</b> For dynamic service discovery</li>
	<li><b>API Gateway : </b>API Gateway for centralized routing and security along with providing access token for accessing downstream services</li>
	<li><b>User Service : </b>Independent service for creating, fetching user related details along with fetching user details based on the provided user Id. This service also utilizes rest template to access an endpoint of Rating service which only allows request coming with scope internal.</li>
	<li><b>Hotel Service : </b>This service is responsible for creating a new Hotel along with other functionalities like fetching records based on HotelId</li>
	<li><b>Rating Service : </b>This service is responsible for creating ratings based on the provided userid and hotelId to which user has rated with other functionalities like fetching ratings based on hotelId and userId, also it utilizes rest template to get the hotel details from Hotel service. It acts as client for Hotel service for an endpoint which only allows API calls with scope internal.  
</ul></span>
<H2>🏗 Architecture Highlights</H2>
<span><ul style="list-style-type:disc">
	<b>Service Discovery & Routing</b>
	<li>Netflix Eureka for dynamic service registry</li>
	<li>Spring Cloud Gateway for centralized API routing and load balancing</li>
	<b>Security & Identity</b>
    <li>Okta as the Authentication & Authorization server
    <li>Spring Security for role-based access and JWT validation</li>
	<b>Resilience & Fault Tolerance</b>
    <li>Resilience4j for circuit breaker, retry, and rate limiter patterns
    <li>Graceful degradation and fallback mechanisms</li>
	<b>Observability & Monitoring</b>
    <li>Spring Boot Actuator for health checks, metrics, and application insights
</ul>
</span>
<H2>🛠 Tech Stack</H2>
<span><ul style="list-style-type:disc">
	<li><b>Backend : </b> Java 17, Spring Boot, Spring Cloud<br>
	<li><b>Service Discovery : </b> Netflix Eureka<br>
    <li><b>API Gateway : </b> Spring Cloud Gateway<br>
    <li><b>Security :</b> Okta, Spring Security, JWT<br>
    <li><b>Resilience :</b> Resilience4j<br>
    <li><b>Observability :</b> Spring Boot Actuator<br>
    <li><b>Database : </b>MySql, MongoDB, PostgreSQL<br>
    <li><b>Build & Dependency Management :</b> Maven<br>
</ul>
</span>
