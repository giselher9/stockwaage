#include <Bridge.h>
#include <YunServer.h>
#include <YunClient.h>

YunServer server;

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
  String command = client.readStringUntil('/');

  if (command == "api") {
    apiCommand(client);
  }
  else if (command == "test") {
    testCommand(client);
  }
}

void testCommand(YunClient client) {
  client.print(F("Greetings from Arduino!"));
}

void apiCommand(YunClient client) {
  String command = client.readStringUntil('/');

  if (command == "weight") {
    weightCommand(client);
  }
}

void weightCommand(YunClient client) {
  client.print(F("getting weight..."));
}
