package top.zixuan007;

/**
 * @author zixuan007
 * @version 1.0
 * @description: 基岩版服务器状态信息
 * @date 2021/2/5 12:29 下午
 */
public class ServerInfo {
    private String status;
    private String ip;
    private int port;
    private String motd;
    private String agreement;
    private String version;
    private String online;
    private String max;
    private String gameMode;


    public ServerInfo() {
    }

    public ServerInfo(String status, String ip, int port, String motd, String agreement, String version, String online, String max, String gameMode) {
        this.status = status;
        this.ip = ip;
        this.port = port;
        this.motd = motd;
        this.agreement = agreement;
        this.version = version;
        this.online = online;
        this.max = max;
        this.gameMode = gameMode;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "status='" + status + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", motd='" + motd + '\'' +
                ", agreement='" + agreement + '\'' +
                ", version='" + version + '\'' +
                ", online='" + online + '\'' +
                ", max='" + max + '\'' +
                ", gameMode='" + gameMode + '\'' +
                '}';
    }
}
