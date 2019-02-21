package longLink2ShortLink;

import java.util.Random;

/**
* @Description: 10进制转换为62进制
* @Author: xjp
* @Date: 2019/2/21 
*/ 

public class toBase62 {
    private static final  String BASE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /** 
    * @Description: 转化为62进制 
    * @Param: [num] 
    * @return: java.lang.String 
    * @Author: xjp
    * @Date: 2019/2/21 
    */ 
    public static String toBase62(long num) {
        StringBuffer sb = new StringBuffer();
        int targerBase = BASE.length();
        do {
            int i = (int) (num % targerBase);
            sb.append(BASE.charAt(i));
            num /= targerBase;

        } while (num > 0);
        return sb.reverse().toString();
    }
    /** 
    * @Description: 转化为10进制
    * @Param: [input] 
    * @return: long 
    * @Author: xjp
    * @Date: 2019/2/21 
    */

    public static long toBase10(String input) {
        int srcBase = BASE.length();
        long id = 0;
        String r = new StringBuilder(input).reverse().toString();

        for (int i = 0; i < r.length(); i++) {
            int charIndex = BASE.indexOf(r.charAt(i));
            id += charIndex * (long) Math.pow(srcBase, i);
        }

        return id;
    }
    /**
    * @Description: 加入随机位数,防止被破译规律
    * @Param: [val]
    * @return: long
    * @Author: xjp
    * @Date: 2019/2/21
    */

    private static long insertRandomBitPer5Bits(long val) {
        long result = val;
        long high = val;
        for (int i = 0; i < 10; i++) {
            if (high == 0) {
                break;
            }
            int pos = 5 + 5 * i + i;
            high = result >> pos;
            result = ((high << 1 | new Random().nextInt( 2)) << pos)
                    | (result & (-1L >>> (64 - pos)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(toBase62(30000));
        System.out.println(insertRandomBitPer5Bits(999999));
    }



}
