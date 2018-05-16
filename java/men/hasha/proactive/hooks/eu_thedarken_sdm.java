package men.hasha.proactive.hooks;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodExact;

public class eu_thedarken_sdm {
    public static void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        // Pro mode
        Class<?> c = findClass("eu.thedarken.sdm.tools.upgrades.e", lpparam.classLoader);
        Class<?> d = findClass("eu.thedarken.sdm.tools.upgrades.d", lpparam.classLoader);
        Method m = findMethodExact(c, "a", d);
        XposedBridge.hookMethod(m, XC_MethodReplacement.returnConstant(true));
        // TODO UI changes
    }
}
