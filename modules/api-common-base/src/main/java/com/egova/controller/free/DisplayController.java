package com.egova.controller.free;

import com.egova.model.DisplayItem;
import com.egova.model.PropertyDescriptorHolder;
import com.egova.web.annotation.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据字典查询
 *
 * @author 奔波儿灞
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = {"/free/display"})
@RequiredArgsConstructor
public class DisplayController {

    @Api
    @GetMapping("/{type}")
    public List<DisplayItem> display(@PathVariable String type) {
        return PropertyDescriptorHolder.get(type);
    }

}
