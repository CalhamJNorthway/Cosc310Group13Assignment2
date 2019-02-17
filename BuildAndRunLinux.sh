mkdir -p target/classes
javac -sourcepath src/main/java -d target/classes src/main/java/chadbot/Main.java
cp src/main/resources/chadbot/ChatBotResponses.xml target/classes/chadbot
cd target
cd classes
java chadbot.Main
read -rsp $'Press any key to continue...\n' -n 1 key
