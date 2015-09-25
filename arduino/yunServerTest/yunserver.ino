#include <ArduinoJson.h>

#include <Bridge.h>
#include <YunServer.h>
#include <YunClient.h>

YunServer server;
StaticJsonBuffer<200> jsonBuffer;

void setup() {
  Serial.begin(9600);
  pinMode(13, OUTPUT);
  digitalWrite(13, LOW);
  Bridge.begin();
  digitalWrite(13, HIGH);

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
}

void weightCommand(YunClient client) {
  
  JsonObject& root = jsonBuffer.createObject();
  root["sensor"] = "loadcell01";
  root["type"] = "weight";
  //root["time"] = NULL;
  root["unit"] = "kg";
  root["value"] = 33.7;
  
  root.prettyPrintTo(client);
}
