# Weather Forecast App
## Open Weather Map API
Based on wikipedia definition, API or Application Programming Interface is 
> A set of subroutine definitions, protocols, and tools for building application software.

API helps software developer to gather the data from certain application/technology. Usually, the data format to communicate through API is a kind of semistructured format such as XML or JSON.
Open Weather Map API is an API that provides weather data. It can provides several data such as
- current weather data
- historical weather data
- daily forecast
- weather map layer
- UV index
- etc.

## Package Structure
Using MVC design pattern approach, I pack the classes in several packages.
# weather.forecast
This is the main package of this app. It contains main class and other packages that i will explain afterwards.
# weather.forecast.MainMenu
This is the package that contains several classes which is representing view and controller in the very first when app is executed.
# weather.forecast.DetailedCity
This package contains several classes which is representating view and controller of detailed information of specific city such as temperature, weather, wind, etc.
# weather.forecast.prediction
This package contains several classes which is representating view and controller of weather prediction in specific city for the next view days
# weather.forecast.model
This package contains classes which is used for gathering data from Open Weather Map API and send it back to views or controller

## Progress Checklist
# weather.forecast
- [ ] class WeatherForecast
# weather.forecast.MainMenu
- [ ] class SearchCityBox
- [ ] class SearchCityButton
- [ ] class MainMenuPaner
- [ ] class CityList
- [ ] class MainMenuController
# weather.forecast.DetailedCity
- [ ] class DetailedCityPanel
- [ ] class CityNameLabel
- [ ] class TempDetailedLabel
- [ ] class WeatherDetailedLabel
- [ ] class WindSpeedLabel
- [ ] class WindDirectionLabel
- [ ] class PredictionButton
- [ ] class DetailedCityController
# weather.forecast.prediction
- [ ] class PredictionPanel
- [ ] class DateLabel
- [ ] class TempPredictionLabel
- [ ] class WeatherPredictionLabel
# weather.forecast.model
- [ ] class WeatherData
