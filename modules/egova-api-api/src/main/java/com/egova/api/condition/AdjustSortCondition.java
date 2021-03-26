package com.egova.api.condition;

import com.egova.model.annotation.Display;
import com.flagwind.persistent.annotation.Condition;
import lombok.Data;

@Display("模块功能")
@Condition
@Data
public class AdjustSortCondition {

    @Display("原始顺序值")
    private int original;

    @Display("调整后顺序值")
    private int target;

    @Display("类型")
    private String type;

    @Display("父id")
    private String parentId;

}
