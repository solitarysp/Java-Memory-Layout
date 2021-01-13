package com.lethanh98.learn.javamemorylayout;


import org.openjdk.jol.vm.VM;

public class ExampleOneObject {

    public static void main(String[] args) {
        exampleSimpleObject();
        exampleSimpleArrayObject();
        exampleSimpleArrayObjectWithNumberSize();
    }

    /**
     * Nhớ rằng, mỗi một ref cần 4 byte để lưu trữ địa chỉ tham chiếu. Vì vậy dù ta chưa khai báo new object thì bản thân object array cũng sẽ tốn byte để lưu trữ ref
     */
    private static void exampleSimpleArrayObjectWithNumberSize() {
        System.out.println("exampleSimpleArrayObjectWithNumberSize");
        SimpleObject[] simpleObjectArray = new SimpleObject[1];
        // Vì một array emty sẽ tốn vừa đủ 8^2 = 16kb, nên khi tạo 1 mảng có 1 phần tử thì sẽ cần cấp thêm bộ nhớ
        // mặc định 1 padded  phải là bội của 8 nên bộ nhớ cấp sẽ là 16+8 = 24
        // Tuy nhiên object thì dùng hết 20byte thôi, còn 4 byte thừa.
        System.out.println("Array empty: " + VM.current().sizeOf(simpleObjectArray));

        // Như ở trên, vì mỗi một ref chúng ta cần 4 byte. 4 * 100 = 400 byte
        // 400 vừa tròn lại bộ của 8 nên không có dư thừa
        // 400 ref + 16 byte object hread = 416
        SimpleObject[] simpleObjectArray100 = new SimpleObject[100];
        System.out.println("Array 100: " + VM.current().sizeOf(simpleObjectArray100));

    }

    /**
     * Với array chúng ta cần lưu thêm 1 trường _length nữa trên Object header. trường dữ liệu này có data type là int với size là 4 byte
     * Object header One mark word:  8 byte
     * Object header Klass = 4 byte
     * Object header _lenghth = 4 byte
     * 8 +4 +4 = 16byte
     * Vì vậy 1 array rỗng mặc định sẽ tốn 16byte
     */
    private static void exampleSimpleArrayObject() {
        System.out.println("exampleSimpleArrayObject");

        SimpleObject[] simpleObjectArray = new SimpleObject[0];
        System.out.println("Array empty: " + VM.current().sizeOf(simpleObjectArray));

    }

    /**
     * Với một object đơn giản không chứa gì cả.
     * Với bản 64 bít thì một object khi được tạo sẽ chiếm 16byte
     * Object header One mark word:  8 byte
     * Object header Klass = 4 byte
     * Tổng Objecr Head là 12 byte.
     * Vì một padded có size phải là bội của 8 byte nên sẽ có 4 byte padded thừa
     */
    private static void exampleSimpleObject() {
        System.out.println("exampleSimpleObject");
        SimpleObject simpleObject = new SimpleObject();
        System.out.println(VM.current().sizeOf(simpleObject));
    }

    static class SimpleObject {

    }
}
