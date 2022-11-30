package io.github.haappi.BoardGame;

public enum ClassTypes {
    // https://stackoverflow.com/questions/3978654/best-way-to-create-enum-of-strings
    TEST("TEST");

    private final String text;

    ClassTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text.toUpperCase();
    }
}
