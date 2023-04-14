package io.github.haappi.packets;

import io.github.haappi.shared.Enums;
import javafx.scene.image.Image;

import java.io.Serial;

public class Card implements Packet {
    public static final String backCardURI = "file:src/main/resources/card-images/back.png";
    @Serial
    private static final long serialVersionUID = 5839422576067187289L;
    private final String fileURI;
    private final String cardName;
    private final Enums size;
    private final Enums color;
    private final Enums container;
    private final Enums pattern;
    private int row;
    private int col;
    private boolean selected = false;

    public Card(Enums size, Enums color, Enums container, Enums pattern) {
//        super("file:src/main/resources/card-images/back.png");

        this.size = size;
        this.color = color;
        this.container = container;
        this.pattern = pattern;
        this.fileURI =
                "file:src/main/resources/card-images/"
                        + constructFileName(size, color, container, pattern);
        this.cardName = constructFileName(size, color, container, pattern);
    }

    private static String constructFileName(
            Enums size, Enums color, Enums container, Enums pattern) {
        return String.format("%s_%s_%s_%s.png", pattern, size, color, container);
    }

    public static Image getImage(String fileUri) {
        return null;
        //        if (fileUri.equals(backCardURI)) {
        //            imageHashMap.get("back.png");
        //        }
        //
        //        String[] splitted = fileUri.split("/");
        //        final String fileName = splitted[splitted.length - 1];
        //        if (imageHashMap.get(fileName) != null) {
        //            return imageHashMap.get(fileName);
        //        }
        //        Image image = new Image(fileUri, true);
        //        imageHashMap.put(fileName, image);
        //        return image;
    }

    public static boolean isMatch(String against, Card... cards) {
        Object ref;
        switch (against) {
            case "size" -> ref = cards[0].size;
            case "color" -> ref = cards[0].color;
            case "container" -> ref = cards[0].container;
            case "pattern" -> ref = cards[0].pattern;
            default -> throw new IllegalStateException("Unexpected value: " + against);
        }

        for (Card card : cards) {
            switch (against) {
                case "size" -> {
                    if (card.size != ref) return false;
                }
                case "color" -> {
                    if (card.color != ref) return false;
                }
                case "container" -> {
                    if (card.container != ref) return false;
                }
                case "pattern" -> {
                    if (card.pattern != ref) return false;
                }
            }
        }

        return true;
    }

    public int getRow() {
        return row;
    }
//    /**
//     * If this is a False it means the card is showing its face.<br>
//     * Else if it's True, it means the card is showing its back.
//     */
//    private boolean isFlipped = true;

    public void setRow(int row) {
        this.row = row;
    }

//    public boolean isFlipped() {
//        return isFlipped;
//    }
//
//    private void setFlipped(boolean flipped) {
//        isFlipped = flipped;
//    }
//
//    public void flip() {
//        setFlipped(!isFlipped);
//    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCardName() {
        return cardName;
    }

    public String getFileURI() {
        return fileURI;
    }

    public Enums getSize() {
        return size;
    }

//    public boolean isBackCard() {
//        return isFlipped;
//    }

    public Enums getColor() {
        return color;
    }

    public Enums getContainer() {
        return container;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Card card = (Card) o;
//        if (this.isBackCard() || card.isBackCard()) {
//            return false;
//        }
//        return getNumberOfMatches(card) > 1;
//    }

    public Enums getPattern() {
        return pattern;
    }

    /**
     * Checks how many attrs between the two cards are the same. Having more of the same attrs leads to a higher score.
     *
     * @param o the other card
     * @return the number of matches
     */
    public int getNumberOfMatches(Object o) {
        int matches = 0;
        if (this.size == ((Card) o).size) {
            matches++;
        }
        if (this.color == ((Card) o).color) {
            matches++;
        }
        if (this.container == ((Card) o).container) {
            matches++;
        }
        if (this.pattern == ((Card) o).pattern) {
            matches++;
        }
        return matches;
    }

    public String matches(Card other) {
        if (this.size == other.size) {
            return "size";
        }
        if (this.color == other.color) {
            return "color";
        }
        if (this.container == other.container) {
            return "container";
        }
        if (this.pattern == other.pattern) {
            return "pattern";
        }
        return "none";
    }
}