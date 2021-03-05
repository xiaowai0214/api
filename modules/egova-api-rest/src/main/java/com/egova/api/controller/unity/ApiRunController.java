package com.egova.api.controller.unity;

import com.egova.api.facade.ApiRunFacade;
import com.egova.api.model.ApiInfoModel;
import com.egova.api.service.ApiRunService;
import com.egova.web.annotation.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * created by huangkang
 */
@Slf4j
@RestController
@RequestMapping("/unity/api/run")
@RequiredArgsConstructor
public class ApiRunController implements ApiRunFacade {

    private final ApiRunService apiRunService;

    @Api
    @Override
    public String run(@PathVariable String apiId, @RequestBody ApiInfoModel model) {
        return apiRunService.run(apiId,model);
    }

    @Api
    @Override
    public Object outputFormat(@PathVariable String apiId) {
        return apiRunService.outputFormat(apiId);
    }
}
