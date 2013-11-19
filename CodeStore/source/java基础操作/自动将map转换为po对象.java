package util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FineTuUtil {
	public static <T> T map2Obj(Class<T> objclass,Map<String,Object> map){
		T obj = null;
		try {
			obj = objclass.newInstance();
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				StringBuffer key = new StringBuffer((String) it.next());
				String keyStr = key.toString();
				key.replace(0, 1, key.substring(0,1).toUpperCase());
				Class cls = objclass.getDeclaredField(keyStr).getType();
				obj.getClass().getMethod("set"+key.toString(),cls).invoke(obj, cls.cast(map.get(keyStr))); 
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void main(String args[]){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",1);
		map.put("name","fineTu");
		map.put("text","test");
		Talking talk = new FineTuUtil().map2Obj(Talking.class, map);
		System.out.println(talk.getText());
	}
}
