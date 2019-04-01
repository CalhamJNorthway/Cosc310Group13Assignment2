package chadbot.bot.data.word;

public enum PartOfSpeechTag {
    CC      ("Coordinating conjunction"),
    CD      ("Cardinal number"),
    DT      ("Determiner"),
    EX      ("Existential there"),
    FW      ("Foreign word"),
    IN      ("Preposition or subordinating conjunction"),
    JJ      ("Adjective"),
    JJR     ("Adjective, comparative"),
    JJS     ("Adjective, superlative"),
    LS 	    ("List item marker"),
    MD 	    ("Modal"),
    NN 	    ("Noun, singular or mass"),
    NNS     ("Noun, plural"),
    NNP     ("Proper noun, singular"),
    NNPS    ("Proper noun, plural"),
    PDT     ("Predeterminer"),
    POS     ("Possessive ending"),
    PRP     ("Personal pronoun"),
    PRP$    ("Possessive pronoun"),
    RB 	    ("Adverb"),
    RBR     ("Adverb, comparative"),
    RBS     ("Adverb, superlative "),
    RP 	    ("Particle"),
    SYM     ("Symbol"),
    TO 	    ("to"),
    UH 	    ("Interjection"),
    VB 	    ("Verb, base form"),
    VBD     ("Verb, past tense"),
    VBG     ("Verb, gerund or present participle"),
    VBN     ("Verb, past participle"),
    VBP     ("Verb, non-3rd person singular present"),
    VBZ     ("Verb, 3rd person singular present"),
    WDT     ("Wh-determiner"),
    WP 	    ("Wh-pronoun"),
    WP$     ("Possessive wh-pronoun"),
    WRB     ("Wh-adverb");

    private String description;

    PartOfSpeechTag(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "PartOfSpeechTag{Tag = '" + name() + '\'' +
                "\tdescription = '" + description + '\'' +
                '}';
    }
}
