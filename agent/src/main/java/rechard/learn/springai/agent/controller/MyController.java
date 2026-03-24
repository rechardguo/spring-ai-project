package rechard.learn.springai.agent.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
class MyController {

    private final ChatClient chatClient;

    public MyController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    // http://localhost:8080/ai?msg=hello
    @GetMapping("/call")
    String call(@RequestParam("msg") String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .system("你是一个智能助手，你的名字叫小k")
                .call()
                .content();
    }

    @GetMapping("/stream")
    Flux<String> generation(@RequestParam("msg") String userInput, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return this.chatClient.prompt()
                .user(userInput)
                .system("""
                       你是一个智能助手，你的名字叫小k
                       返回markdown格式
                        """)
                .stream()
                .content();
    }
}
