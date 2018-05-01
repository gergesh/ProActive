package men.hasha.proactive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class ProActive implements IXposedHookLoadPackage {
    Method hookMethod = null;
    Class hooksClass = Class.forname("men.hasha.proactive.Hooks");

    public void handleLoadPackage(final LoadPackageParam lpparam) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        // See if we have the hook
        try {
            hookMethod = hooksClass.getDeclaredMethod(lpparam.packageName.replace('.', '_'));
        } catch (NoSuchMethodException e) { }

        if (hookMethod != null) {
            hookMethod.invoke(lpparam);
            XposedBridge.log("Hooked " + lpparam.packageName);
        }
    }
}
