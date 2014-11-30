package me.vkonov.gamebook;


public class Constants {
    private Constants() {

    }

    public static final int varChestRequestCode = 90; // Used to call startActivityForResult(); for battles and inventory eventually

    public static final String varHeroEnergy = "Energy";
    public static final String varHeroStrength = "Strength";
    public static final String varHeroFavour = "Favour";
    public static final String varItems = "Items";

    public static enum varGameItems {
        Key,
        EnergyPotion,
        Sword
    }
    public static final String varCurrentChapter = "currentChapter";
    public static final ChapterData[] chapters = {
            new ChapterData(1, R.string.chapterOneTitle, R.string.chapterOneText, R.array.chapterOneChoices),
            new ChapterData(2, R.string.chapterTwoTitle, R.string.chapterTwoText, R.array.chapterTwoChoices)
    };


}
