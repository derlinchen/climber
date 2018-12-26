package com.climber.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * 对象进行序列化的类
 * @author Derlin
 *
 */
public class SerializeUtil {

	/**
	 * 将对象进行序列化
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] byt = baos.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将byte[]反序列化
	 * @param byt
	 * @return
	 */
	public static Object unserizlize(byte[] byt) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(byt);
			ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (bais != null) {
					bais.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
