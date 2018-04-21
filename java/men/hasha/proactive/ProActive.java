package men.hasha.proactive;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class ProActive implements IXposedHookLoadPackage {

    public void handleLoadPackage(final LoadPackageParam lpparam) {

        switch (lpparam.packageName) {
            case "another.music.player":
                Hooks.hookShuttle(lpparam);
                break;
            case "com.kabouzeid.gramophone":
                Hooks.hookPhonograph(lpparam);
                break;
            case "pl.solidexplorer2":
                Hooks.hookSolidExplorer(lpparam);
                break;
            case "nl.eerko.boardgamestats":
                Hooks.hookBGstats(lpparam);
                break;
            case "com.keramidas.TitaniumBackup":
                Hooks.hookTitaniumBackup(lpparam);
                break;
        }
    }
}
