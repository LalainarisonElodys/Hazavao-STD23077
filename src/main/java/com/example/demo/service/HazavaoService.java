@Service

public class HazavaoService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.project.id}")
    private String projectId;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public String getDefinition(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey); // "Authorization: Bearer ..."
        headers.add("OpenAI-Project", projectId);

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(OPENAI_API_URL, request, Map.class);
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            return message.get("content").toString().trim();
        } catch (Exception e) {
            return "Tsy afaka nandefa ny fangatahana. Mety nisy olana tamin'ny fifandraisana.";
        }
    }
}