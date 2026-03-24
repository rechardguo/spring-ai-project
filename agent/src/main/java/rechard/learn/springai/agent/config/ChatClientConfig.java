package rechard.learn.springai.agent.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatClient myChatClient(){

        var openAiApi = OpenAiApi.builder()
                .apiKey("sk-a93cee135086494bb2f7e2bb0a63b0a3")
                .baseUrl("https://api.deepseek.com")
                .build();
        var openAiChatOptions = OpenAiChatOptions.builder()
                .model("deepseek-chat")
                .temperature(0.4)
                .maxTokens(200)
                .build();
        var chatModel = OpenAiChatModel.builder().openAiApi(openAiApi).defaultOptions(openAiChatOptions).build();

        ChatClient.Builder builder = ChatClient.builder(chatModel);
        return builder.build();
    }
}
