package cl.slash.minesave.obj.domain;

/**
 * Created by waltercool on 9/13/16.
 */
public class MineWorld {
    private String worldName;
    private String path;

    public MineWorld(String worldName, String path) {
        this.worldName = worldName;
        this.path = path;
    }

    public String getWorldName() {
        return worldName;
    }

    public String getPath() {
        return path;
    }
}
