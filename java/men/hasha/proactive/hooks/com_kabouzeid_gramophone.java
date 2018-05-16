package men.hasha.proactive.hooks;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class com_kabouzeid_gramophone {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("com.kabouzeid.gramophone.App",
                lpparam.classLoader, "isProVersion", XC_MethodReplacement.returnConstant(true));
    }
}
