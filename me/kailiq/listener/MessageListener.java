package me.kailiq.listener;

import me.kailiq.ImpBoolean;
import me.kailiq.manager.CheckURLManager;
import me.kailiq.manager.ImpressionAddManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getTextChannel();
        String[] args = content.split(" ");
        EmbedBuilder embed = new EmbedBuilder();
        if(content.contains(".help")) {
            embed.setTitle("ヘルプ");
            embed.setColor(Color.magenta);
            embed.addField(".add <URL> <amount>","URLでしていしたツイートのインプレッションを増加します。",false);
            sendMessage(channel,embed);
        }
        if(content.contains(".add")) {
            if(ImpBoolean.get()) {
                embed.setTitle("他の処理を実行中です。");
                embed.setColor(Color.RED);
                sendMessage(channel, embed);
            }
            else if(args.length == 3) {
                String link = args[1];
                String amount = args[2];
                if(CheckURLManager.check(link)) {
                    if(NumberUtils.isNumber(amount)) {
                        embed.setTitle("インプレッション増加を開始しました。");
                        embed.setColor(Color.YELLOW);
                        sendMessage(channel, embed);
                        ImpressionAddManager.add(link,Integer.parseInt(amount));
                        while (ImpBoolean.get()) {
                            // no thing
                        }
                        embed.setTitle("インプレッション増加完了しました。");
                        embed.setColor(Color.GREEN);
                        sendMessage(channel, embed);
                    }
                    else {
                        embed.setTitle("数値が正しくありません。");
                        embed.setColor(Color.RED);
                        sendMessage(channel, embed);
                    }
                }
                else {
                    embed.setTitle("URLが正しくありません。");
                    embed.setColor(Color.RED);
                    sendMessage(channel, embed);
                }
            }
        }
    }
    public void sendMessage(MessageChannel channel,EmbedBuilder builder) {
        channel.sendMessage(builder.build()).queue();
    }
}
