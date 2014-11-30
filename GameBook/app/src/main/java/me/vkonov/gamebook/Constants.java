package me.vkonov.gamebook;


public class Constants {
    private Constants() {

    }

    public static final int varChestRequestCode = 90; // Used to call startActivityForResult(); for battles and inventory eventually

    public static final String varHeroEnergy = "Energy";
    public static final String varHeroStrength = "Strength";
    public static final String varHeroFavour = "Favour";
    public static final String varHeroName = "Name";
    public static final String varItems = "Items";
    public static final String varSpecialItem = "Special Key";

    public static enum varGameItems {
        Key,
        EnergyPotion,
        Sword
    }
    public static final String varCurrentChapter = "currentChapter";
    public static final ChapterData[] chapters = {
            new ChapterData(1, R.string.chapterOneTitle, R.string.chapterOneText, R.array.chapterOneChoices),
            new ChapterData(2, R.string.chapterTwoTitle, R.string.chapterTwoText, R.array.chapterTwoChoices),
            new ChapterData(3, R.string.chapterThreeTitle, R.string.chapterThreeText, R.array.chapterThreeChoices),
            new ChapterData(4, R.string.chapterFourTitle, R.string.chapterFourText, R.array.chapterFourChoices),
            new ChapterData(5, R.string.chapterFiveTitle, R.string.chapterFiveText, R.array.chapterFiveChoices),
            new ChapterData(6, R.string.chapterSixTitle, R.string.chapterSixText, R.array.chapterSixChoices),
            new ChapterData(7, R.string.chapterSevenTitle, R.string.chapterSevenText, R.array.chapterSevenChoices),
            new ChapterData(8, R.string.chapterEightTitle, R.string.chapterEightText, R.array.chapterEightChoices),
            new ChapterData(9, R.string.chapterNineTitle, R.string.chapterNineText, R.array.chapterNineChoices)

    };


}
