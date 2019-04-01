package chadbot.bot.data.word;

import edu.mit.jwi.item.POS;

public enum SyntacticCategory {
    NOUN("Noun", 'n', POS.NOUN),
    VERB("Verb", 'v', POS.VERB),
    ADJECTIVE("Adjective", 'a', POS.ADJECTIVE),
    ADVERB("Adverb", 'r', POS.ADVERB)
    ;
    private String name;
    private char character;
    private POS wordNetPOS;

    SyntacticCategory(String name, char character, POS wordNetPOS) {
        this.name = name;
        this.character = character;
        this.wordNetPOS = wordNetPOS;
    }

    public String getName() {
        return name;
    }

    public char getCharacter() {
        return character;
    }

    public POS getWordNetPOS() {
        return wordNetPOS;
    }

    public static SyntacticCategory valueOf(char character) {
        for (SyntacticCategory v : values()) {
            if(character == v.getCharacter()) {
                return v;
            }
        }
        throw new IllegalArgumentException( "Unknown Syntactic Category: '" + character + "'." );
    }

    public static SyntacticCategory valueOf(PartOfSpeechTag tag) {
        SyntacticCategory value = null;
        switch (tag){
            case JJ:
                value = ADJECTIVE;
                break;
            case JJR:
                value = ADJECTIVE;
                break;
            case JJS:
                value = ADJECTIVE;
                break;
            case NN:
                value = NOUN;
                break;
            case NNS:
                value = NOUN;
                break;
            case NNP:
                value = NOUN;
                break;
            case NNPS:
                value = NOUN;
                break;
            case RB:
                value = ADVERB;
                break;
            case RBR:
                value = ADVERB;
                break;
            case RBS:
                value = ADVERB;
                break;
            case VB:
                value = VERB;
                break;
            case VBD:
                value = VERB;
                break;
            case VBG:
                value = VERB;
                break;
            case VBN:
                value = VERB;
                break;
            case VBP:
                value = VERB;
                break;
            case VBZ:
                value = VERB;
                break;
        }
        return value;
    }
}
