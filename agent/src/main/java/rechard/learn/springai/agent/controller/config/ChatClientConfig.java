package rechard.learn.springai.agent.controller.config;

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
                .apiKey("sk-febea108ae3a4de19500625e13c43f60")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode")
                .build();
        var openAiChatOptions = OpenAiChatOptions.builder()
                .model("qwen3.5-plus")
                .temperature(0.4)
                .maxTokens(200)
                .build();
        var chatModel = OpenAiChatModel.builder().openAiApi(openAiApi).defaultOptions(openAiChatOptions).build();

        ChatClient.Builder builder = ChatClient.builder(chatModel);
        return builder.build();
    }
}
