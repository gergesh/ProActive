package men.hasha.proactive.hooks;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class another_music_player {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("com.simplecity.amp_library.ShuttleApplication",
                lpparam.classLoader, "d", XC_MethodReplacement.returnConstant(true));
    }
}
