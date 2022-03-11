package com.king.security.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @program: mooc
 * @description: 图形验证码生成类
 * @author: King
 * @create: 2021-10-06 01:14
 */
public class VerifyCodeGen {
    private static int width = 150;// 定义图片的width
    private static int height = 48;// 定义图片的height
    private static int codeCount = 5;// 定义图片上显示验证码的个数
    // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
    private static StringBuffer randomCode;
    private static final int xx = 25;
    private static final int fontHeight = 42;
    private static final int codeY = 42;
//    private static final char[] codeSequence = {
//            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
//            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
//            's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] codeSequence = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',  'J', 'K', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',  'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    /**
     * 生成一个map集合
     * code为生成的验证码
     * codePic为生成的验证码BufferedImage对象
     *
     * @return
     */
    public static BufferedImage generateCodeAndPic() {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixes", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);

        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        gd.setFont(font);
        // 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
        int red;
        int green;
        int blue;
        randomCode = new StringBuffer();

        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        for (int i = 0; i < 60; i++) {
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(50);
            int yl = random.nextInt(50);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        return buffImg;
    }

    public static void outputImage(int width1, int height1, int length1, OutputStream os) throws IOException {
        width = width1;
        height = height1;
        codeCount = length1;
        BufferedImage image = VerifyCodeGen.generateCodeAndPic();
        ImageIO.write(image, "jpg", os);
    }

    /**
     * 生成验证码图片输出成图片，返回验证码字符串
     *
     * @param width
     * @param height
     * @param response
     * @return
     * @throws IOException
     */
    public static String outputImage(int width, int height, int length, HttpServletResponse response)
            throws IOException {
        // 随机生成验证码
        // 图形写给浏览器
        response.setContentType("image/jpeg");
        // 发头控制浏览器不要缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 获取响应输出流
        OutputStream out = response.getOutputStream();
        // 输出图片
        outputImage(width, height, length, out);
        return String.valueOf(randomCode);
    }

    /**
     * 输出宽度200、高度80、长度5位的验证码输出到页面，返回验证码字符串
     *
     * @param response
     * @return
     * @throws IOException
     */
    public static String outputImage(HttpServletResponse response) throws IOException {
        return outputImage(200, 80, 5, response);
    }


}