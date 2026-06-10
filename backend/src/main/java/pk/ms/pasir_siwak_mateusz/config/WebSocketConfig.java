package pk.ms.pasir_siwak_mateusz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import pk.ms.pasir_siwak_mateusz.websocket.GroupNotificationHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final GroupNotificationHandler groupNotificationHandler;

    public WebSocketConfig(GroupNotificationHandler groupNotificationHandler) {
        this.groupNotificationHandler = groupNotificationHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(groupNotificationHandler, "/ws/group-notifications")
                .setAllowedOrigins("*");
    }
}