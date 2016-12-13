package Place;

public class Exit {
    private String direction;
    private String description;
    private Room leadTo;
    private boolean isLocked;

    public Exit(String direction, String description, Room leadTo, boolean isLocked) {
        this.direction = direction;
        this.description = description;
        this.leadTo = leadTo;
        this.isLocked = isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public String getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }

    Room getLeadTo() {
        return leadTo;
    }
}
