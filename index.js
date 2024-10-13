async function fetchLlamaResponse() {
    const response = await fetch('https://debian-voys.onrender.com/llama', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            system_message: "You are a helpful assistant.",
            user_message: "Tell me a story about a dragon.",
            max_tokens: 150
        }),
    });

    const reader = response.body.getReader();
    const decoder = new TextDecoder("utf-8");
    let result = '';

    while (true) {
        const { done, value } = await reader.read();
        if (done) break;
        result += decoder.decode(value, { stream: true });
        console.log(result);
    }
}

fetchLlamaResponse();
