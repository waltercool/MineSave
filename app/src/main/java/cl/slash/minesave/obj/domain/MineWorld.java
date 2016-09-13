package cl.slash.minesave.obj.domain;

/**
 * Created by waltercool on 9/13/16.
 */
public class MineWorld {
    private String worldName;
    private String path;
    private int worldStorageLocation;

    public MineWorld(String worldName, String path, int worldStorageLocation) {
        this.worldName = worldName;
        this.path = path;
        this.worldStorageLocation = worldStorageLocation;
    }

    public String getWorldName() {
        return worldName;
    }

    public String getPath() {
        return path;
    }

    public int getWorldStorageLocation() {
        return worldStorageLocation;
    }
}
