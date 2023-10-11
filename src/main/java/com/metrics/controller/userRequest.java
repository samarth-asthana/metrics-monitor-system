package com.metrics.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.metrics.metricService.MetricService;
import java.util.Map;

@RestController
class userRequest {
    @Autowired
    private MetricService metricService;

    @GetMapping("/getdata")
    @Timed(value = "api.example.latency")
    public String helloWorld() {
        try {
            return "Success!";
        } catch (Exception e) {
            return "Failure!";
        }
    }
    @GetMapping("/getdatafail")
    @Timed(value = "api.example.latency")
    public ResponseEntity<String> helloWorld1() throws Exception {
        throw new Exception("some Error");
    }

//    @GetMapping(value = "/status-metric")
//    @ResponseBody
//    public Map getStatusMetric() {
//        return metricService.getStatusMetric();
//    }
    @GetMapping(value = "/metric")
    @ResponseBody
    public Map getMetric() {
        return metricService.getFullMetric();
    }
}