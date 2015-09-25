# stockwaage
Stockwaage für Bienenbeuten

![Basisaufbau](https://github.com/giselher9/stockwaage/blob/master/basic_buildup_Steckplatine.png)

## Messwerte erstellen
### Hardware
* Wägezelle [Bosche H30A](http://www.bosche.eu/sites/default/files/prospekte/H30A.pdf), Nennlast 200kg
* 24Bit ADC [HX711](https://github.com/sparkfun/HX711-Load-Cell-Amplifier)
* Arduino [Yun](https://www.arduino.cc/en/Main/ArduinoBoardYun?from=Products.ArduinoYUN)

### Software
#### Nützliche Bibliotheken
* [HX711 C-lib](https://github.com/bogde/HX711)
* [JSon C-lib](https://github.com/bblanchon/ArduinoJson/wiki)

## Aufbereitung der Daten
* Java Service als Single-Jar mittels [Dropwizard](http://www.dropwizard.io/getting-started.html)
* RaspberryPi (2)
