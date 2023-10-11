package com.metrics.metricService;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricService {

//    private Map<Integer, Integer> statusMetric;
//
//    public MetricService() {
//        statusMetric = new ConcurrentHashMap<>();
//    }
//
//    public void increaseCount(String request, int status) {
//        Integer statusCount = statusMetric.get(status);
//        if (statusCount == null) {
//            statusMetric.put(status, 1);
//        } else {
//            statusMetric.put(status, statusCount + 1);
//        }
//    }
//
//    public Map getStatusMetric() {
//        return statusMetric;
//    }

    private Map<String, Map<Integer, Integer>> metricMap = new HashMap<>();

    @Autowired
    private MeterRegistry registry;
    public void increaseCount(String request, int status) {
       Map<Integer, Integer> statusMap = metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        metricMap.put(request, statusMap);
    }
    public Map getFullMetric() {

        return metricMap;
    }
    public void addSuccess(){
        registry.counter("RequestCountMetric", "success").increment();
    }
}