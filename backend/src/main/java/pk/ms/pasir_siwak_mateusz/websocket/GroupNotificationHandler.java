package pk.ms.pasir_siwak_mateusz.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pk.ms.pasir_siwak_mateusz.security.JwtUtil;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GroupNotificationHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final JwtUtil jwtUtil;

    public GroupNotificationHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();
        if (query != null && query.contains("token=")) {
            String token = query.split("token=")[1].split("&")[0];

            if (jwtUtil.validateToken(token)) {
                String userEmail = jwtUtil.extractUsername(token);

                if (userEmail != null && !userEmail.isBlank()) {
                    sessions.put(userEmail, session);
                    return;
                }
            }
        }
        session.close(CloseStatus.POLICY_VIOLATION);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.values().remove(session);
    }

    public void sendNotification(String email, String jsonMessage) {
        WebSocketSession session = sessions.get(email);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(jsonMessage));
            } catch (IOException e) {
                System.err.println("Nie udało się wysłać wiadomości przez WebSocket: " + e.getMessage());
            }
        }
    }
}