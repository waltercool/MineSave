package cl.slash.minesave.obj.domain;

import java.io.File;

import cl.slash.minesave.obj.constants.WorldStorageLocation;

/**
 * Created by waltercool on 9/13/16.
 */
public class MineWorld {
    private String worldName;
    private String path;
    private WorldStorageLocation worldStorageLocation;

    public MineWorld(String worldName, String path, WorldStorageLocation worldStorageLocation) {
        this.worldName = worldName;
        this.path = path;
        this.worldStorageLocation = worldStorageLocation;
    }

    public String getFolderName() {
        File f = new File(path);
        return f.getName();
    }

    public String getWorldName() {
        return worldName;
    }

    public String getPath() {
        return path;
    }

    public WorldStorageLocation getWorldStorageLocation() {
        return worldStorageLocation;
    }
}
