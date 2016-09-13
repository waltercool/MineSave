package cl.slash.minesave.toolkit;

import java.io.IOError;
import java.net.URI;

import cl.slash.minesave.obj.domain.MineWorld;

/**
 * Created by waltercool on 9/13/16.
 */
public class StorageUtils {

    public URI compressWorld(MineWorld world) {
        //TODO Compress into cache, so I can fetch it later.
        return null;
    }

    public MineWorld uncompressWorld(URI pathCompressedFile) throws IOError {
        //TODO Uncompress the file into MineWorlds path
        return null;
    }

}
