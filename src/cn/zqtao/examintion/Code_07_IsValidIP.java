package cn.zqtao.examintion;

/**
 * 有效IP地址
 */
public class Code_07_IsValidIP {
    public static String getIp(String ip) {
        if (ip == null || ip.length() == 0) return "Neither";

        if (ip.startsWith(":") || ip.startsWith(".")
                || ip.endsWith(":") || ip.endsWith(".")
        )
            return "Neither";

        String[] split = ip.split("\\.");
        if (split.length == 4) {
            int n = 1;
            for (int i = 0; i < 4; i++) {
                try {
                    n = Integer.parseInt(split[i]);
                    if (n < 0 || n > 255) return "Neither";
                    if (split[i].length() > 1 && (split[i].startsWith("0") || split[i].startsWith(".")))
                        return "Neither";
                } catch (Exception e) {
                    return "Neither";
                }
            }
            return "IPv4";
        } else {
            split = ip.split(":");
            if (split.length == 8) {
                long n = -1;
                for (int i = 0; i < 8; i++) {
                    try {
                        if (split[i].length() > 4 || split[i].startsWith(".")) return "Neither";
                        n = Long.parseLong(split[i], 16);
                        if (n < 0) return "Neither";
                    } catch (Exception e) {
                        return "Neither";
                    }
                }
                return "IPv6";
            } else {
                return "Neither";
            }

        }

    }

    public static void main(String[] args) {
        System.out.println(getIp("256.256.256.256"));
        System.out.println(getIp("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(getIp("172.16.254.1"));
    }
}