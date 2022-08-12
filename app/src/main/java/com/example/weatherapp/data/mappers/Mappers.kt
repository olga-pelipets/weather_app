package com.example.weatherapp.data.mappers

typealias CurrentWeatherDomain = com.example.weatherapp.domain.models.CurrentWeather
typealias ForecastWeatherDomain = com.example.weatherapp.domain.models.ForecastWeather
typealias MainInfoDomain = com.example.weatherapp.domain.models.MainInfo
typealias RainDomain = com.example.weatherapp.domain.models.Rain
typealias CityDomain = com.example.weatherapp.domain.models.City
typealias WeatherDomain = com.example.weatherapp.domain.models.Weather
typealias CloudsDomain = com.example.weatherapp.domain.models.Clouds
typealias SunDomain = com.example.weatherapp.domain.models.Sun
typealias WindDomain = com.example.weatherapp.domain.models.Wind
typealias CoordinatesDomain = com.example.weatherapp.domain.models.Coordinates
typealias ForecastItemDomain = com.example.weatherapp.domain.models.ForecastItem

typealias CurrentWeatherData = com.example.weatherapp.data.models.CurrentWeather
typealias ForecastWeatherData = com.example.weatherapp.data.models.ForecastWeather
typealias MainInfoData = com.example.weatherapp.data.models.MainInfo
typealias RainData = com.example.weatherapp.data.models.Rain
typealias CityData = com.example.weatherapp.data.models.City
typealias WeatherData = com.example.weatherapp.data.models.Weather
typealias CloudsData = com.example.weatherapp.data.models.Clouds
typealias SunData = com.example.weatherapp.data.models.Sun
typealias WindData = com.example.weatherapp.data.models.Wind
typealias CoordinatesData = com.example.weatherapp.data.models.Coordinates
typealias ForecastItemData = com.example.weatherapp.data.models.ForecastItem

fun CoordinatesData.toDomain(): CoordinatesDomain = CoordinatesDomain(
    lon = lon,
    lat = lat
)

fun WeatherData.toDomain(): WeatherDomain = WeatherDomain(
    main = main,
    description = description,
    icon = icon
)

fun CloudsData.toDomain(): CloudsDomain = CloudsDomain(
    all = all
)

fun SunData.toDomain(): SunDomain = SunDomain(
    sunrise = sunrise,
    sunset = sunset,
)

fun WindData.toDomain(): WindDomain = WindDomain(
    speed = speed
)

fun CurrentWeatherData.toDomain(): CurrentWeatherDomain = CurrentWeatherDomain(
    cityName = cityName,
    main = main.toDomain(),
    coordinates = coordinates.toDomain(),
    weather = weather.map{it.toDomain()},
    clouds = clouds.toDomain(),
    sys = sys.toDomain(),
    wind = wind.toDomain()
)

fun CityData.toDomain(): CityDomain = CityDomain(
    name = name,
    coordinates = coordinates.toDomain(),
    country = country,
    sunrise = sunrise,
    sunset = sunset
)

fun MainInfoData.toDomain(): MainInfoDomain = MainInfoDomain(
    temp = temp,
    feelsLike = feelsLike,
    pressure = pressure,
    humidity = humidity
)

fun RainData.toDomain(): RainDomain = RainDomain(
    the3H = the3H
)

fun ForecastItemData.toDomain(): ForecastItemDomain = ForecastItemDomain(
    main = main.toDomain(),
    weather = weather.map{it.toDomain()},
    clouds = clouds.toDomain(),
    wind = wind.toDomain(),
    date = date,
    rain = rain?.toDomain()
)

fun ForecastWeatherData.toDomain(): ForecastWeatherDomain = ForecastWeatherDomain(
    city = city.toDomain(),
    list = list.map{it.toDomain()}
)


