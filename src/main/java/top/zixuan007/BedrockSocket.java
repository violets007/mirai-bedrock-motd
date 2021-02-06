package top.zixuan007;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author zixuan007
 * @version 1.0
 * @description: 通过UDP Socket 发包进行获取服务器信息
 * @date 2021/2/5 12:34 下午
 */
public class BedrockSocket {
    public static int TIME_OUT = 1500;

    public static ServerInfo fetchData(String address, int port) {
        ServerInfo serverInfo = new ServerInfo();
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(TIME_OUT);
            socket.connect(InetAddress.getByName(address), port);
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);

            // 数据包
            byte[] bytes = toByteArray("0100000000240D12D300FFFF00FEFEFEFEFDFDFDFD12345678");
            socket.send(new DatagramPacket(bytes, 0, bytes.length));

            socket.receive(receivePacket);

            System.out.println(receivePacket);
            String string = new String(receivePacket.getData(), "utf-8");

            System.out.println(string);

            String[] split = string.split(";");

            String status = "online";
            String motd = split[1].replaceAll("§[a-z A-Z 0-9]{1}", "");
            String agreement = split[2];
            String version = split[3];
            String online = split[4];
            String max = split[5];
            String gameMode = split[8];

            serverInfo.setStatus(status);
            serverInfo.setIp(address);
            serverInfo.setPort(port);
            serverInfo.setMotd(motd);
            serverInfo.setAgreement(agreement);
            serverInfo.setVersion(version);
            serverInfo.setOnline(online);
            serverInfo.setMax(max);
            serverInfo.setGameMode(gameMode);

            socket.close();
        } catch (SocketException exception) {
            return serverInfo;
        } catch (IOException exception) {
            return serverInfo;
        }

        return serverInfo;
    }

    public static byte[] toByteArray(String hexString) {
        if (hexString == null || hexString.length() < 1)
            return null;
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index > hexString.length() - 1)
                return byteArray;
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }
}
