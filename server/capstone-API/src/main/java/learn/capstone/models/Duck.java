package learn.capstone.models;

import java.util.Objects;

public class Duck {

    private int duckId;
    private String duckImage;

    private boolean hidden;

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }



    public int getDuckId() {
        return duckId;
    }

    public void setDuckId(int duckId) {
        this.duckId = duckId;
    }

    public String getDuckImage() {
        return duckImage;
    }

    public void setDuckImage(String duckImage) {
        this.duckImage = duckImage;
    }

    public Duck() {
    }

    public Duck(int duckId, String duckImage, boolean hidden) {
        this.duckId = duckId;
        this.duckImage = duckImage;
        this.hidden = hidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duck that = (Duck) o;
        return duckId == that.duckId &&
                Objects.equals(duckImage, that.duckImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duckId, duckImage);
    }
}
