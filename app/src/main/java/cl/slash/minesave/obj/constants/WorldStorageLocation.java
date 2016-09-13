package cl.slash.minesave.obj.constants;

/**
 * Created by waltercool on 9/13/16.
 */
public enum WorldStorageLocation {
    ON_DEVICE(0),
    DROPBOX(1),
    BOX(2),
    DRIVE(3);

    private int value;

    WorldStorageLocation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
