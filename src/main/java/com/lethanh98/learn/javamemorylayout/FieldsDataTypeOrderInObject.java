package com.lethanh98.learn.javamemorylayout;


import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Để tối ưu hóa object, thứ tự khai báo các fields của object được sắp xếp thành thứ tự dự trên các sau.
 */
public class FieldsDataTypeOrderInObject {
    public static Unsafe UNSAFE;

    static {
        try {
            @SuppressWarnings("ALL")
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private volatile byte a;
    private volatile Byte A;
    private volatile boolean b;
    private volatile char d;
    private volatile short c;
    private volatile int e;
    private volatile float f;
    private volatile double g;
    private volatile long h;
    private volatile String refA;
    private volatile String refB;
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        System.out.println("e:int    \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("e")));
        System.out.println("g:double \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("g")));
        System.out.println("h:long   \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("h")));
        System.out.println("f:float  \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("f")));
        System.out.println("c:short  \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("c")));
        System.out.println("d:char   \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("d")));
        System.out.println("a:byte   \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("a")));
        System.out.println("A:Byte   \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("A")));
        System.out.println("b:boolean\t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("b")));
        System.out.println("refA:reference \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("refA")));
        System.out.println("refB:reference \t" + UNSAFE.objectFieldOffset(FieldsDataTypeOrderInObject.class.getDeclaredField("refB")));

    }
}
