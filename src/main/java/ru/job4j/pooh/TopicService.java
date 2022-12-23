package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String httpRequest = req.httpRequestType();
        String name = req.getSourceName();
        String param = req.getParam();
        if (HttpMethod.POST.getRequest().equals(httpRequest)) {
            topics.get(name).forEach((key, value) -> value.add(param));
            return new Resp("", HttpStatus.OK_200.getState());
        }
        if (HttpMethod.GET.getRequest().equals(httpRequest)) {
            if (topics.containsKey(name) && topics.get(name).containsKey(param)) {
                var text = topics.get(name).getOrDefault(param, new ConcurrentLinkedQueue<>()).poll();
                return new Resp(text, HttpStatus.OK_200.getState());
            } else {
                topics.putIfAbsent(name, new ConcurrentHashMap<>());
                topics.get(name).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            }
        }
        return new Resp("", HttpStatus.NO_CONTEXT_204.getState());
    }
}