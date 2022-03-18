package com.davidnyan10.plugins;

import android.content.Context;

import com.aliucord.Logger;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.aliucord.fragments.SettingsPage;
import com.aliucord.patcher.Hook;
import com.aliucord.patcher.PinePatchFn;
import com.aliucord.utils.ReflectUtils;
import com.discord.models.message.Message;
import com.discord.widgets.chat.input.WidgetChatInputDiscoveryCommandsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

import dalvik.system.DexClassLoader;
import top.canyie.pine.utils.ReflectionHelper;

import daveuwu.Fractions;

@SuppressWarnings("unused")
@AliucordPlugin
public class UnicodeFractions extends Plugin {
	public static final Logger logger = new Logger("UnicodeFractions");

	@Override
	public void start(Context context) {
		patcher.patch("com.discord.models.message.Message", "getContent", null, new Hook(cf -> {
			Message _this = (Message) cf.thisObject;

			String cont = null;

			try {
				cont = (String) ReflectUtils.getField(_this, "content");
			} catch (Exception e) {
			}
			cf.setResult(Fractions.replaceFractionsInString(cont));

		}));

	}

	@Override
	public void stop(Context context) {
		patcher.unpatchAll();
	}

}
// renamed a folder to prevent conflicts