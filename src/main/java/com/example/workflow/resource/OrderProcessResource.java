package com.example.workflow.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order-process")
@RequiredArgsConstructor
public class OrderProcessResource {

    private static final String PROCESS_ID = "order-process";

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    @PostMapping("/start")
    public VariableMap startProcess(@RequestParam String uuid) {

        log.info("Запуск процесса {}.", uuid);

        ProcessInstanceWithVariables processInstanceWithVariables = runtimeService.createProcessInstanceByKey(PROCESS_ID)
                .businessKey(uuid)
                .executeWithVariablesInReturn();

        return processInstanceWithVariables.getVariables();
    }

}
