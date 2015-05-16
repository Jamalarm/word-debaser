package colours;

public enum PlayerColour {
    BLUE("\u001B[44;37m"),
    ORANGE("\u001B[41;37m"),
    NONE("\u001B[0m");

    private String colourCode;

    PlayerColour(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getColourCode() {
        return colourCode;
    }
}
