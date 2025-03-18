package com.myevent.common.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;

@Slf4j
public class NetworkUtils {

    public static String getMacAddress(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            NetworkInterface network = NetworkInterface.getByInetAddress(address);
            byte[] mac = network.getHardwareAddress();

            if (mac == null) {
                return "Address doesn't exist or is not accessible";
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
