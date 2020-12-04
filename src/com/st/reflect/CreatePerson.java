package com.st.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CreatePerson {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, ClassNotFoundException {
        Class clazz = Person.class;
        Constructor constructor = clazz.getDeclaredConstructor();
        Object person1 = constructor.newInstance();
        System.out.println(person1);

        Class clazz2 = Person.class;
        Constructor constructor2 = clazz2.getDeclaredConstructor(Integer.class,String.class);
        Object person2 = constructor2.newInstance(1,"张三");
        System.out.println(person2);
        Field age = clazz2.getDeclaredField("age");
        age.setAccessible(true);
        age.set(person2,11);
        System.out.println(person2);
        Method setAge = clazz2.getDeclaredMethod("setAge", Integer.class);
        setAge.setAccessible(true);
        setAge.invoke(person2,22);
        System.out.println(person2);

        Class clazz3 =Class.forName("com.st.reflect.Person");
        Constructor constructor3 = clazz3.getDeclaredConstructor();
        Object person3 = constructor3.newInstance();
        System.out.println(person3);

        Class clazz4 = CreatePerson.class.getClassLoader().loadClass("com.st.reflect.Person");
        Constructor constructor4 = clazz4.getDeclaredConstructor();
        Object person4 = constructor4.newInstance();
        System.out.println(person4);
    }
}
