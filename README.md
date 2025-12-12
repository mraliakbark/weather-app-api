Java (Spring boot) mini-application that fetches weather data from OpenWeather API with caching and filtering.

Download the project ZIP, extract it, and import it in Spring Tool Suite or any other IDE as a Maven project. Locate your WeatherController and WeatherService files to run or modify the app.

**Add OpenWeather API Key**

Open src/main/resources/application.properties

Add your key:

openweather.api.key=YOUR_API_KEY_HERE


**Run the Project**

Right-click the project → Run As → Spring Boot App

Server will start on http://localhost:8080

Test the APIs

Use Postman or browser to call endpoints


**ENDPOINTS**
-- Fetch & store city weather
POST http://localhost:8080/weather/fetch/Mumbai 

-- Fetches all city data stored in cache
http://localhost:8080/weather/all

-- Fetches with condition (minimum temperature)
http://localhost:8080/weather/all?minTemp=25

-- Fetches single city
http://localhost:8080/weather/Mumbai
