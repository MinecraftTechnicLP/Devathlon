package tk.mctechniclp.devathlon.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * <b>THIS CLASS WAS CREATED BEFORE THE DEVATHLON BEGAN, I ASKED MULTIPLE TIMES, EVEN IN MY APPLICATION, WHETHER I WOULD BE ALLOWED TO COPY SELF MADE CLASSES AND GOT NO ANSWER. SO I JUST DID IT</b>
 * @author MinecraftTechnicLP
 * @version 1.0
 * 
 */
public class ReflectionUtils {
	
	public static String VERSION;
	
	static {
		VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
	
	public static void sendPacket(Player p, Object packet) {
		try {
			Object handle = p.getClass().getMethod("getHandle").invoke(p);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
			
			
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendToAll(Object packet) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			sendPacket(p, packet);
		}
	}
	
	public static Class<?> getNMSClass(String name) {
		try {
			return Class.forName("net.minecraft.server." + VERSION + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Class<?> getCraftbukkitClass(String name) {
		try {
			return Class.forName("org.bukkit.craftbukkit." + VERSION + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setFieldValue(Object obj, String name, Object value) {
		try {
			Field f = obj.getClass().getDeclaredField(name);
			f.setAccessible(true);
			f.set(obj, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getFieldValue(Object obj, String name) {
		try {
			Field f = obj.getClass().getDeclaredField(name);
			f.setAccessible(true);
			return f.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public static void setStaticFieldValue(Class<?> clazz, String name, Object value) {
		try {
			Field f = clazz.getDeclaredField(name);
			f.setAccessible(true);
			f.set(null, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getStaticFieldValue(Class<?> clazz, String name) {
		try {
			Field f = clazz.getDeclaredField(name);
			f.setAccessible(true);
			return f.get(null);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	public static Object injectMethod(Object obj, String methodName, Object... args) {
		try {
			Method method = obj.getClass().getMethod(methodName, getClasses(args));
			return method.invoke(obj, args);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Class<?>[] getClasses(Object[] values) {
		Class<?>[] classes = new Class<?>[values.length];
		
		for(int i = 0; i < values.length; i++) {
			classes[i] = values[i].getClass();
		}
		
		return classes;
	}
}
