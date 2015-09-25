#include <ArduinoJson.h>
#include "HX711.h"
#include "dht.h"

#include <Bridge.h>
#include <YunServer.h>
#include <YunClient.h>

YunServer server;

HX711 scale(A0, A1);		// parameter "gain" is ommited; the default value 128 is used by the library
float calibration_factor = -23100;

dht DHT;

void setup() {
  Serial.begin(9600);
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
  Bridge.begin();
  digitalWrite(13, HIGH);

  scale.set_scale(calibration_factor); //Adjust to this calibration factor
  scale.tare();	//Reset the scale to 0

  server.listenOnLocalhost();
  server.begin();

}

void loop() {
  YunClient client = server.accept();

  if (client) {
    process(client);
    client.stop();
  }

  delay(50);
}

void process(YunClient client) {
  Serial.println("client read");
  String command = client.readStringUntil('/');
  command.trim();
  Serial.println(command);
  if (command == "api") {
    apiCommand(client);
  }
  else if (command == "test") {
    testCommand(client);
  }
  else {
    client.print(F("invalid uri"));
  }
}

void testCommand(YunClient client) {
  Serial.println("testCommand received");
  client.print(F("Greetings from Arduino!"));
}

void apiCommand(YunClient client) {
  String command = client.readStringUntil('/');
  command.trim();
  if (command == "weight") {
    weightCommand(client);
  }
  else   if (command == "temp") {
    temperatureCommand(client);
  }
   else   if (command == "hum") {
    humidityCommand(client);
  }
  else {
    client.print(F("wrong api cmd."));
  }
}

void weightCommand(YunClient client) {
  Serial.println("weightCommand received");
  
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  root["sensor"] = "loadcell01";
  root["type"] = "weight";
  //root["time"] = NULL;
  root["unit"] = "kg";
  root["value"] = readLoadCell();
  
  root.prettyPrintTo(Serial);
  root.prettyPrintTo(client);
}

void temperatureCommand(YunClient client) {
  Serial.println("temperatureCommand received");
  
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  root["sensor"] = "temp01";
  root["type"] = "temperature";
  //root["time"] = NULL;
  root["unit"] = "C";
  root["value"] = readDhtTemp();
  
  root.prettyPrintTo(Serial);
  root.prettyPrintTo(client);
}

void humidityCommand(YunClient client) {
  Serial.println("temperatureCommand received");
  
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  root["sensor"] = "hum01";
  root["type"] = "humidity";
  //root["time"] = NULL;
  root["unit"] = "%";
  root["value"] = readDhtHumidity();
  
  root.prettyPrintTo(Serial);
  root.prettyPrintTo(client);
}

float readLoadCell(){
  return scale.get_units(10);
}  

float readDhtTemp(){
  Serial.print("DHT11, \t");
  int chk = DHT.read11(4);
  switch (chk)
  {
    case DHTLIB_OK:  
		Serial.print("OK,\t"); 
		break;
    case DHTLIB_ERROR_CHECKSUM: 
		Serial.print("Checksum error,\t"); 
		break;
    case DHTLIB_ERROR_TIMEOUT: 
		Serial.print("Time out error,\t"); 
		break;
    default: 
		Serial.print("Unknown error,\t"); 
		break;
  }
 // DISPLAY DATA
  Serial.print(DHT.humidity,1);
  Serial.print(",\t");
  float temp = DHT.temperature;
  Serial.println(temp);
  
  return temp;
}  

float readDhtHumidity(){
  int chk = DHT.read11(4);
  switch (chk)
  {
    case DHTLIB_OK:  
		Serial.print("OK,\t"); 
                return DHT.humidity;
		break;
    case DHTLIB_ERROR_CHECKSUM: 
		Serial.print("Checksum error,\t"); 
		break;
    case DHTLIB_ERROR_TIMEOUT: 
		Serial.print("Time out error,\t"); 
		break;
    default: 
		Serial.print("Unknown error,\t"); 
		break;
  }
}  
