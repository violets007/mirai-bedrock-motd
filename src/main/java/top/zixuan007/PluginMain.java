package top.zixuan007;

import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author zixuan007
 * @version 1.0
 * @description: 插件主类
 * @date 2021/2/5 12:12 下午
 */
public class PluginMain extends JavaPlugin {

    public static PluginMain INSTANCE = new PluginMain();

    private PluginMain() {
        super(new JvmPluginDescriptionBuilder(
                        "top.zixuan007.PluginMain",
                        "1.0.0"
                )
                        .author("zixuan007")
                        .name("bedrock-motd")
                        .info("获取我的世界基岩版服务器信息")
                        .build()
        );
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {

    }

    @Override
    public void onEnable() {
        this.getLogger().info("plugin loading ...");

        // 注册监听器
        Listener listener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            String msg = event.getMessage().contentToString();
            String domain = msg.replace("!motdpe", "").toLowerCase().trim();

            if (domain.equals("") || domain.length() < 1) {
                event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getSender().getId()))
                        .plus("请输入一个地址"));
                return;
            }

            String port = "19132";
            if (domain.contains(":")) {
                port = domain.split(":")[1];
                domain = domain.split(":")[0];
            }

            ServerInfo serverInfo = BedrockSocket.fetchData(domain, Integer.parseInt(port));
            if (serverInfo.getOnline() == null || serverInfo.getOnline().length() < 1) {
                event.getGroup().sendMessage(MessageUtils.newChain(new At(event.getSender().getId()))
                        .plus("当前服务器不在线"));
                return;
            }

            event.getGroup().sendMessage("[MCMotd]\nMotd: " + serverInfo.getMotd() + "\n" +
                    "协议版本: " + serverInfo.getAgreement() + "\n" +
                    "游戏版本: " + serverInfo.getVersion() + "\n" +
                    "在线: " + serverInfo.getOnline() + "/" + serverInfo.getMax() + "\n" +
                    "游戏模式: " + serverInfo.getGameMode());

        });
    }

    public static PluginMain getInstance() {
        return INSTANCE;
    }

    public static void setInstance(PluginMain INSTANCE) {
        PluginMain.INSTANCE = INSTANCE;
    }
}
