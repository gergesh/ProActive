package men.hasha.proactive.hooks;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

enum r {
    ADFREE,
    PRO
}

public class de_orrs_deliveries {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        findAndHookMethod("de.orrs.deliveries.c.a", lpparam.classLoader, "a", String.class, XC_MethodReplacement.returnConstant(r.PRO));
    }
}
