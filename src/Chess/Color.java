package Chess;

public enum Color {
    BLACK, WHITE;

    public int direction() {
        int direction = 0;

        switch (this) {
            case BLACK:
                direction = -1;
                break;
            case WHITE:
                direction = 1;
                break;
        }

        return direction;
    }
}