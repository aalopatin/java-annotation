package ru.rzn.javaschool.runtime;

import java.lang.reflect.Field;

public class ToStrings {
    //TODO Создание обработчика собственной аннотации в runtime
    public static String toString(Object obj) {

        Class<?> clazz = obj.getClass();
        ToString clazzAnnotation = clazz.getAnnotation(ToString.class);
        StringBuilder stringBuilder = new StringBuilder();
        if (clazzAnnotation != null) {
           boolean clazzIncludeName = clazzAnnotation.includeName();
           if (clazzIncludeName) {
               stringBuilder.append(clazz.getName());
           }

        }
        stringBuilder.append("[");
        int countField = 0;
        for (Field field: clazz.getDeclaredFields()) {
            ToString fieldAnnotation = field.getAnnotation(ToString.class);
            if (fieldAnnotation != null) {
                if (countField > 0) {
                    stringBuilder.append(", ");
                }
                boolean fieldIncludeName = fieldAnnotation.includeName();
                if (fieldIncludeName) {
                    stringBuilder.append(field.getName());
                    stringBuilder.append(" = ");
                }
                try {
                    field.setAccessible(true);
                    stringBuilder.append(field.get(obj));
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                countField++;
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}

