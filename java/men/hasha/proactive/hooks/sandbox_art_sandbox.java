package men.hasha.proactive.hooks;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class sandbox_art_sandbox {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("sandbox.art.sandbox.repositories.entities.Account",
                lpparam.classLoader, "isSubscriptionActive", XC_MethodReplacement.returnConstant(true));
    }
}
