package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String httpRequest = req.httpRequestType();
        String name = req.getSourceName();
        String param = req.getParam();
        if (HttpMethod.POST.getRequest().equals(httpRequest)) {
            queue.putIfAbsent(name, new ConcurrentLinkedQueue<>());
            queue.get(name).add(param);
        }
        if (HttpMethod.GET.getRequest().equals(httpRequest)) {
            var text = queue.getOrDefault(name, new ConcurrentLinkedQueue<>()).poll();
            return new Resp(text, HttpStatus.OK_200.getState());
        }
        return new Resp("", HttpStatus.NO_CONTEXT_204.getState());
    }
}