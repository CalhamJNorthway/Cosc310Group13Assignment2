package chadbot.bot.data;

public class AIMLParser {
    private String AIMLFileLocation;

    public AIMLParser(String AIMLFileLocation) {
        this.AIMLFileLocation = AIMLFileLocation;
    }

    //Parse SynonymGroups:
    //Create a synonym group containing an array of synonyms
    //Rinse Repeat
    //Returns Array of SynonymGroups

    //Parse ResponseTemplates:
    //Get Pattern and Response
    //Create Array of ResponseTemplates
    //Return Array of ResponseTemplates

    //Get Default Response:
    //If called return default response
    //FOUND BY TAG OF <Default>


    //ITERATE FILE METHOD, USE TRY CATCH WITH SWITCH STATEMENTS:
    //CHECK IF TAG <AIML> is in file read only between those tags
    //File AIML
    //Read.line() "<Default>" parse "Default"
    //Iterate File
    //While(File.hasNext()) {
    //if(tag.equals("pattern"){
    //String[] prompts
    //String Response
    //GetResponseTemplate(prompts, Response);
    //}
    //}




}
