# Stockwaage
Stockwaage für Bienenbeuten zur Erfassung von Honigertrag, Schwarmabgang...

Grundsätzliche Idee ist die Messung des Bienenbeutengewichts mittels einer Plattformwägezelle über die Arduino Plattform. Ein separater Service greift die Daten (von potentiell mehreren Waagen) ab und stellt sie über eine REST Schnittstelle bereit.

## Aufbau
![Basisaufbau](https://github.com/giselher9/stockwaage/blob/master/drawings/basic_buildup_Steckplatine.png)

## Messwerte erstellen
### Hardware
* Wägezelle [Bosche H30A](http://www.bosche.eu/sites/default/files/prospekte/H30A.pdf), Nennlast 200kg
* 24Bit ADC [HX711](https://github.com/sparkfun/HX711-Load-Cell-Amplifier)
* Arduino [Yun](https://www.arduino.cc/en/Main/ArduinoBoardYun?from=Products.ArduinoYUN)
  * Alternativ: Arduino Uno mit Ethernet/WLAN/GSM-Shield

### Software
#### Nützliche Bibliotheken
* [HX711 C-lib](https://github.com/bogde/HX711)
* [JSon C-lib](https://github.com/bblanchon/ArduinoJson/wiki)

## Aufbereitung der Daten
* Java Service als Single-Jar mittels [Dropwizard](http://www.dropwizard.io/getting-started.html) auf RaspberryPi (2), stellt Schnittstelle bereit für
 * Web Frontend
 * Smartphone App
