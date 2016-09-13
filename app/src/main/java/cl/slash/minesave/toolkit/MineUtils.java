package cl.slash.minesave.toolkit;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import cl.slash.minesave.exception.NoPermissionError;
import cl.slash.minesave.obj.constants.WorldStorageLocation;
import cl.slash.minesave.obj.domain.MineWorld;

/**
 * Created by waltercool on 9/13/16.
 */
public class MineUtils {

    private static final String WORLDS_PATH ="/games/com.mojang/minecraftWorlds";
    private static MineUtils instance;

    private Context mContext;

    private MineUtils(Context context) {
        mContext = context;
    }

    public static void init(Context context) {
        instance = new MineUtils(context);
    }

    public static MineUtils getInstance() {
        return instance;
    }

    public List<MineWorld> listWorlds() throws NoPermissionError {
        String mediaStatus = Environment.getExternalStorageState();
        if(!mediaStatus.equals(Environment.MEDIA_MOUNTED) &&
                !mediaStatus.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            //No SD Card in use.
            return null;
        }
        File minecraftWorldPath = new File(Environment.getExternalStorageDirectory(), WORLDS_PATH);
        List<MineWorld> results = new ArrayList<>();

        File[] dirFiles = minecraftWorldPath.listFiles();
        if (dirFiles == null) throw new NoPermissionError(null);
        for(File dirWorld : dirFiles) {
            if(dirWorld.isDirectory()) {
                String worldName = readWorldName(dirWorld);
                if (worldName != null) {
                    results.add(new MineWorld(worldName, dirWorld.getAbsolutePath(),
                            WorldStorageLocation.ON_DEVICE.getValue()));
                }
            }
        }
        return results;
    }

    public void uploadWorld(MineWorld world, WorldStorageLocation storageService) {
        StorageUtils storage = new StorageUtils();
        URI fileCompressed = storage.compressWorld(world);

        //TODO Something to upload the world into remote services.

    }

    public void downloadWorld(MineWorld world) {
        //TODO Something to fetch the data into cache.

        //TODO Something to uncompress the game world.
    }

    private String readWorldName(File dirWorldPath) {
        File levelNamePath = new File(dirWorldPath, "levelname.txt");
        try {
            StringBuilder text = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(levelNamePath));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
            return text.toString();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            return null;
        }
    }
}
