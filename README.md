# weather_sim_test

## Description 

Create a toy simulation of the environment (taking into account things like atmosphere, topography, geography, oceanography, or similar) 
that evolves over time. 

Then take measurements at various locations and times, and have your program emit that data, as in the following:

 |Location   |Position          | Local Time          |Conditions  |Temperature| Pressure | Humidity|
 |-----------|---------|---------|---------|---------|---------|------|
 |Sydney   | -33.86,151.21,39   | 2015-12-23 16:02:12   |   Rain        |+12.5     | 1010.3    |  97|
 | Melbourne| -37.83,144.98,7   |2015-12-25 02:30:55     | Snow       | -5.3        |998.4    |  55|
 |Adelaide  |-34.92,138.62,48  |2016-01-04 23:05:37     | Sunny     |  +39.4     | 1114.1     | 12|

Submit your data to us in the following format

```
Sydney|-33.86,151.21,39|2015-12-23T05:02:12Z|Rain|+12.5|1004.3|97
Melbourne|-37.83,144.98,7|2015-12-24T15:30:55Z|Snow|-5.3|998.4|55
Adelaide|-34.92,138.62,48|2016-01-03T12:35:37Z|Sunny|+39.4|1114.1|12
```

Where

-	Location is an optional label describing one or more positions,
-	Position is a comma-separated triple containing latitude, longitude, and elevation in metres above sea level,
-	Local time is an ISO8601 date time,
-	Conditions is either Snow, Rain, Sunny,
-	Temperature is in °C,
-	Pressure is in hPa, and
-	Relative humidity is a %.

Your toy weather simulation should report data from a reasonable number of positions; 10±. 

The weather simulation will be used for games and does not need to be meteorically accurate, it just needs to be emit weather data that looks plausible to a layperson.

So far we have assumed that our game takes place on Earth, leading to the use of latitude and longitude for co-ordinates and earth-like conditions. 

If you choose to assume that the game takes place elsewhere, please document any corresponding changes to the output format.

## How to run the program 

    java -jar weather_simulator.jar
    
## Configuration
The module expecting a configuration file with name application.conf in class path with following format

```
etl {
  extractor_file_path = "src/main/resources/World_Cities_Location_table_MS-EXCEL.csv"
  publisher_file_path = "src/main/resources/weather_report.txt"
}
```
extractor_file_path : input file path 
publisher_file_path : output file path

Both paths should be configured with relative paths

## Input File Format
The input file should have comma separated values as mentioned below

```
"id","country","city","lattitude","longitude","altitude"
```

Ex: "1","Afghanistan","Kabul","34.5166667","69.1833344","1808.0"


## Models
### Axil Tilt Model
In astronomy, axial tilt, also known as obliquity, is the angle between an object's rotational axis and its orbital axis, or, equivalently, the angle between its equatorial plane and orbital plane. It differs from orbital inclination.

At an obliquity of zero, the two axes point in the same direction; i.e., the rotational axis is perpendicular to the orbital plane. Over the course of an orbit, the obliquity usually does not change considerably, and the orientation of the axis remains the same relative to the background stars. This causes one pole to be directed more toward the Sun on one side of the orbit, and the other pole on the other side — the cause of the seasons on the Earth. Earth's obliquity oscillates between 22.1 and 24.5 degrees on a 41,000-year cycle. It is currently 23°26′13.5″ (or 23.43709°) and decreasing.

### Temperature Model
tl = range of temperatures related to latitude [max, min] sh = solar hour. max at noon, min at midnight. alt = altitude in meters

    temp = t1 * sh * alt
    
### Humidity Model
hl = humidity related to latitude

If rain forest:

    humidity = hl * 1.5

If desert:

    humidity = hl * 0.2
    
### Air Presure Model
Uses the Barometric Formula
