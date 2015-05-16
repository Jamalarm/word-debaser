package colours;

public enum PlayerColour {
    BLUE("\u001B[34m"),
    ORANGE("\u001B[31m"),
    NONE("\u001B[30m");

    private String colourCode;

    PlayerColour(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getColourCode() {
        return colourCode;
    }
}
