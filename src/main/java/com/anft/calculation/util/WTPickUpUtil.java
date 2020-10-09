package com.anft.calculation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 *    Created with IntelliJ IDEA   2017/2/10   @author zys
 *  @version 1.0.0  
 *
 * @ 类的描述 文通相机发送抬杆命令接口
 */
public class WTPickUpUtil {


    private static final Logger logger = LoggerFactory
            .getLogger(WTPickUpUtil.class);

    public static void main(String[] args) {



        pickUpHandle("192.168.1.98", 9740);
    }

    public static void pickUpHandle(String ip, int port) {
        logger.info("=================进入WTPickUpUtil.pickUpHandle(String ip, int port)方法");
        logger.info("=================入参ip="+ip+",port="+port);
        logger.info("开始尝试连接==============================================");
        OutputStream out = null;
        Socket socket = null;
        try {
            logger.info("准备连接==============================================" );
            socket = new Socket(ip, port);
            logger.info("创建socket==============================================" +socket);
            boolean connected = socket.isConnected();
            logger.info("是否连接成功==============================================" + connected);
            //获取socket流中的输出流,向服务器输出消息，即发消息到服务器
            out = socket.getOutputStream();
            byte[] bytes_resu = new byte[47];
            bytes_resu[0] = 'E';
            bytes_resu[1] = 'P';
            bytes_resu[2] = 9;
//            bytes_resu[3] = 0;
//            bytes_resu[4] = 30;
//            bytes_resu[5] = 0;
//            bytes_resu[6] = 0;
//            bytes_resu[7] = 0;
//            bytes_resu[8]='[';
//            bytes_resu[9]='j';
//            bytes_resu[10]='p';
//            bytes_resu[11]='e';
//            bytes_resu[12]='g';
//            bytes_resu[13]='s';
//            bytes_resu[14]='t';
//            bytes_resu[15]='r';
//            bytes_resu[16]='e';
//            bytes_resu[17]='a';
//            bytes_resu[18]='m';
//            bytes_resu[19]=']';
//            bytes_resu[20]='\n';
//            bytes_resu[21]='i';
//            bytes_resu[22]='p';
//            bytes_resu[23]='=';
//            bytes_resu[24]='1';
//            bytes_resu[25]='9';
//            bytes_resu[26]='2';
//            bytes_resu[27]='.';
//            bytes_resu[28]='1';
//            bytes_resu[29]='6';
//            bytes_resu[30]='8';
//            bytes_resu[31]='.';
//            bytes_resu[32]='1';
//            bytes_resu[33]='.';
//            bytes_resu[34]='4';
//            bytes_resu[35]='1';
//            bytes_resu[36]='\n';
//            bytes_resu[37]='p';
//            bytes_resu[38]='o';
//            bytes_resu[39]='r';
//            bytes_resu[40]='t';
//            bytes_resu[41]='=';
//            bytes_resu[42]='8';
//            bytes_resu[43]='0';
//            bytes_resu[44]='8';
//            bytes_resu[45]='0';
//            bytes_resu[46]='\n';
            //使用输出流将指定的数据写出去。
            out.write(bytes_resu);
            out.flush();

            logger.info("==================================发送成功==============================");
        } catch (IOException ex) {
            logger.info("抬杆异常======================" + ex.getMessage());
        } finally {
            //关闭资源
            try {
                if (out != null) {
                    out.close();
                }

            } catch (IOException ex) {
                logger.info("关闭流失败======================" + ex.getMessage());
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
