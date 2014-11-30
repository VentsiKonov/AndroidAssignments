package me.vkonov.gamebook;

public class ChapterData {

    public final int ID;
    public final int titleResource;
    public final int textResource;
    public final int choicesResource;

    ChapterData(int ID, int title, int text, int choices) {
        this.ID = ID;
        this.titleResource = title;
        this.textResource = text;
        this.choicesResource = choices;
    }
}
