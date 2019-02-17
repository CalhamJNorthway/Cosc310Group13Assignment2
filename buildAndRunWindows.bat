mkdir target\classes
javac -sourcepath src\main\java -d target\classes src\main\java/chadbot/Main.java
copy src\main\resources\chadbot\ChatBotResponses.xml target\classes\chadbot
cd target
cd classes
java chadbot.Main
PAUSE